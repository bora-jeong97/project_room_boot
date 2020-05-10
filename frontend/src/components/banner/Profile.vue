<template>
  <div class="profile-container">
    <div class="image-upload-wrapper">
      <div class="filebox">
        <div class="image-profile">
          <img v-if="profileImage" :src="profileImage" />
          <img v-else src="../../assets/room1.jpg" />
        </div>

        <label for="file">프로필 사진 변경</label>
        <input v-if="loginData.user_id" type="file" id="file" ref="file" @change="profileUpload" />
        <input
          v-if="loginData.broker_id"
          type="file"
          id="file"
          ref="file"
          @change="profileBrokerUpload"
        />
      </div>
    </div>

    <div class="profile-information-wrapper">
      <div class="profile-wrapper">
        <div class="profile-title">닉네임</div>
        <div>
          <input class="profile-input" v-model="name" />
        </div>
      </div>
      <div class="profile-wrapper">
        <div class="profile-title">이메일 주소</div>
        <div>
          <input class="profile-input" v-model="userId" />
        </div>
      </div>
      <div class="profile-wrapper">
        <div class="profile-title">휴대폰 번호</div>
        <div>
          <input class="profile-input-phone" v-model="phoneNumber" />
          <!-- <span class="profile-phone-divider">-</span>
          <input class="profile-input-phone" />
          <span class="profile-phone-divider">-</span>
          <input class="profile-input-phone" />-->
        </div>
      </div>
    </div>
    <div class="profile-button-container">
      <div class="profile-button-wrapper">
        <button class="profile-cancel-button">취소</button>
        <button v-if="loginData.user_id" class="profile-apply-button" @click="updateUser">확인</button>
        <button v-if="loginData.broker_id" class="profile-apply-button" @click="updateBroker">확인</button>
      </div>
      <button class="secession-button">회원탈퇴</button>
    </div>
  </div>
</template>

<script>
//axios를 모듈화해서 가져온다
import { request, requestFile } from "../../util/axios";
import { mapState, mapMutations } from "vuex";
export default {
  data() {
    return {
      name: "",
      userId: "",
      file: "",
      phoneNumber: ""
    };
  },
  mounted() {
    this.name = this.loginData.name;

    if (this.loginData.user_id) {
      this.userId = this.loginData.user_id;
    } else if (this.loginData.broker_id) {
      this.userId = this.loginData.broker_id;
    }

    this.phoneNumber = this.loginData.phone_number;
  },
  computed: {
    ...mapState(["loginData", "profileImage"])
  },
  methods: {
    ...mapMutations(["SET_LOGIN", "SET_PROFILE_IMAGE"]),
    profileUpload() {
      console.log(this.$refs.file.files[0]);
      this.file = this.$refs.file.files[0];
      let formData = new FormData();
      formData.append("userId", this.loginData.user_id);
      formData.append("file", this.file);
      requestFile("POST", "user/updateProfile", formData)
        .then(res => {
          console.log(res);
          console.log(this.loginData);
          console.log(this.loginData.user_id);

          //세션및 vuex 업데이트
          this.updateInformation();
          this.$toasted.show("프로필 업로드가 완료되었습니다.", {
            type: "success",
            position: "top-right",
            duration: 2500
          });
        })
        .catch(error => {
          console.log(error);
        });
    },
    profileBrokerUpload() {
      console.log(this.$refs.file.files[0]);
      this.file = this.$refs.file.files[0];
      let formData = new FormData();
      formData.append("brokerId", this.loginData.broker_id);
      formData.append("file", this.file);
      requestFile("POST", "broker/updateProfile", formData)
        .then(res => {
          console.log(res);
          console.log(this.loginData);
          console.log(this.loginData.broker_id);

          //세션및 vuex 업데이트
          this.updateBrokerInformation();
          this.$toasted.show("프로필 업로드가 완료되었습니다.", {
            type: "success",
            position: "top-right",
            duration: 2500
          });
        })
        .catch(error => {
          console.log(error);
        });
    },
    updateInformation() {
      let params = new URLSearchParams();
      params.append("id", this.loginData.id);
      request("POST", "user/getInformation", params)
        .then(data => {
          if (data !== "") {
            sessionStorage.setItem("login", JSON.stringify(data));
            this.SET_LOGIN(data);
            this.SET_PROFILE_IMAGE(this.loginData.profile_image);
          }
        })
        .catch(error => {
          console.log(error);
        });
    },
    // 브로커 프로필 사진 업로드후 갱신
    updateBrokerInformation() {
      let params = new URLSearchParams();
      params.append("id", this.loginData.id);
      request("POST", "broker/getInformation", params)
        .then(data => {
          if (data !== "") {
            sessionStorage.setItem("login", JSON.stringify(data));
            this.SET_LOGIN(data);
            this.SET_PROFILE_IMAGE(this.loginData.profile_image);
          }
        })
        .catch(error => {
          console.log(error);
        });
    },
    // updateUser
    updateUser() {
      let params = new URLSearchParams();
      params.append("id", this.loginData.id);
      params.append("name", this.name);
      params.append("userId", this.userId);
      params.append("phoneNumber", this.phoneNumber);
      console.log(this.loginData.id);
      request("post", "user/updateUser", params).then(res => {
        console.log(res);
        console.log("성공적 으로 수정이 완료되었습니다");
        if (res === "OK") {
          this.$toasted.show("회원 정보 수정이 완료되었습니다", {
            type: "success",
            position: "top-right",
            duration: 2500
          });
          this.updateInformation();
        } else {
          this.$toasted.show("회원 정보 수정에 실패하였습니다", {
            type: "error",
            position: "top-right",
            duration: 2500
          });
        }
      });
    },
    updateBroker() {
      let params = new URLSearchParams();
      params.append("id", this.loginData.id);
      params.append("name", this.name);
      params.append("brokerId", this.userId);
      params.append("phoneNumber", this.phoneNumber);
      console.log(this.loginData.id);
      request("post", "broker/updateUser", params).then(res => {
        console.log(res);
        console.log("성공적 으로 수정이 완료되었습니다");
        if (res === "OK") {
          this.$toasted.show("회원 정보 수정이 완료되었습니다", {
            type: "success",
            position: "top-right",
            duration: 2500
          });
          this.updateBrokerInformation();
        } else {
          this.$toasted.show("회원 정보 수정에 실패하였습니다", {
            type: "error",
            position: "top-right",
            duration: 2500
          });
        }
      });
    }
  }
};
</script>

<style>
.profile-container {
  margin: 0 auto;
  width: 850px;
}

.image-profile {
  margin: 0 auto 12px auto;
  width: 76px;
  height: 76px;
  border-radius: 50%;
  background-color: beige;
  border: 1px solid #dddddd;
}

.image-profile > img {
  width: 100%;
  height: 100%;
  border-radius: 50%;
}

.profile-information-wrapper {
  margin-top: 20px;
  padding: 50px 0;
  border-top: 1px solid #dddddd;
  border-bottom: 1px solid #dddddd;
}
.profile-wrapper:not(:last-child) {
  margin-bottom: 30px;
}

.profile-title {
  margin-left: 200px;
  margin-bottom: 8px;
  color: #222222;
  text-align: left;
  font-size: 14px;
}
.profile-input {
  padding-left: 15px;
  margin-left: 200px;
  width: 450px;
  height: 44px;
}
.profile-input-phone:first-child {
  margin-left: 200px;
  width: 450px;
}

.profile-input-phone {
  width: 140px;
  height: 44px;
  padding: 10px;
  text-align: center;
}
.profile-phone-divider {
  display: inline-block;
  color: #666666;
  font-size: 14px;
  margin: 0 5px;
}
.profile-button-container {
  margin-top: 50px;
  text-align: center;
  color: #222222;
}

.profile-button-wrapper {
  display: flex;
  justify-content: center;
}

.profile-cancel-button {
  border: none;
  border: 1px solid #dddddd;
  width: 218px;
  height: 50px;
  font-size: 15px;
  cursor: pointer;
}
.profile-apply-button {
  margin-left: 20px;
  border: none;
  border: 1px solid #dddddd;
  width: 218px;
  height: 50px;
  color: #fff;
  background-color: #1564f9;
  font-size: 15px;
  cursor: pointer;
}

.secession-button {
  border: none;
  margin-top: 50px;
  font-size: 14px;
  color: #666666;
  text-decoration: underline;
  padding-bottom: 80px;
}

.filebox {
  margin-top: 60px;
  padding-bottom: 20px;
  text-align: center;
}

.filebox input[type="file"] {
  position: absolute;
  width: 0;
  height: 0;
  padding: 0;
  overflow: hidden;
  border: 0;
}

.filebox label {
  display: inline-block;
  padding: 10px 20px;
  color: #1564f9;
  vertical-align: middle;
  font-size: 15px;
  cursor: pointer;
  border-radius: 5px;
}
</style>
