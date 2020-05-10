<template>
  <ModalRegister>
    <div slot="header">
      <div @click="SET_REGISTER_MODAL(false)" class="register-header">
        <span v-if="selectRegister === 'user'">회원가입(일반 회원)</span>
        <span v-else>회원가입(공인중개사)</span>
        <div class="close-button">
          <img src="../assets/close.png" />
        </div>
      </div>
    </div>
    <hr />
    <div slot="body">
      <notifications group="notifyApp" position="right right" style="margin-right: 30vh;" />
      <div class="register-information-container">
        <div class="register-information-wrapper">
          <div class="register-title">이메일(아이디)</div>
          <div class="register-input-wrapper">
            <input v-model="id" class="register-input register-email" />
            <span class="space">@</span>
            <input v-if="selectEtc" class="register-email-domain-input" />
            <select v-model="selectedDomain" class="register-input register-email-domain">
              <option value disabled selected>선택해주세요</option>
              <option>{{ emailDomain.naver }}</option>
              <option>{{ emailDomain.google }}</option>
              <option>{{ emailDomain.daum }}</option>
              <option>{{ emailDomain.hanmail }}</option>
              <option>{{ emailDomain.etc }}</option>
            </select>
          </div>
        </div>

        <div class="register-information-wrapper">
          <div class="register-title">비밀번호</div>
          <div class="register-input-wrapper">
            <input v-model="userPassword" class="register-input" type="password" />
          </div>
        </div>
        <div class="register-information-wrapper">
          <div class="register-title">비밀번호확인</div>
          <div class="register-input-wrapper">
            <input v-model="userPasswordCheck" class="register-input" type="password" />
          </div>
        </div>

        <div class="register-information-wrapper">
          <div class="register-title">이름</div>
          <div class="register-input-wrapper">
            <input v-model="name" class="register-input" />
          </div>
        </div>

        <div class="register-information-wrapper">
          <div class="register-title">나이</div>
          <div class="register-input-wrapper">
            <input v-model="age" class="register-input" maxlength="3" />
            <span v-if="age" class="space-age">만 {{ age - 1 }}세</span>
          </div>
        </div>
        <div class="register-information-wrapper">
          <div class="register-title">휴대폰 번호</div>
          <div class="register-input-wrapper">
            <input
              v-model="phone1"
              class="register-input register-input-phone"
              value="010"
              maxlength="3"
            />
            <span class="space">-</span>
            <input v-model="phone2" class="register-input register-input-phone" maxlength="4" />
            <span class="space">-</span>
            <input v-model="phone3" class="register-input register-input-phone" maxlength="4" />
          </div>
        </div>

        <!-- <div class="register-information-wrapper">
          <div class="register-title">이메일</div>
          <div class="register-input-wrapper">
            <input class="register-input" />
          </div>
        </div>-->
      </div>
    </div>
    <div slot="footer">
      <div
        v-if="selectRegister === 'user'"
        @click="registerUser"
        class="register-button"
      >이메일 회원가입(일반 회원)</div>
      <div v-else @click="registerBroker" class="register-button">이메일 회원가입(공인중개사)</div>
    </div>
  </ModalRegister>
</template>

<script>
import ModalRegister from "./ModalRegister";
import { mapMutations, mapState } from "vuex";
import { request } from "../util/axios";
import { success, error, normal } from "../util/notification";
export default {
  components: {
    ModalRegister
  },
  data() {
    return {
      id: "",
      userPassword: "",
      userPasswordCheck: "",
      name: "",
      age: 0,
      selectedDomain: "",
      phone1: "010",
      phone2: "",
      phone3: "",
      emailDomain: {
        naver: "naver.com",
        google: "gmail.com",
        daum: "daum.net",
        hanmail: "hanmail.com",
        etc: "직접입력"
      }
    };
  },
  computed: {
    ...mapState(["selectRegister"]),
    selectEtc() {
      return this.selectedDomain === "직접입력" ? true : false;
    }
  },
  //Observer패턴(감시), 숫자가 입력되지 않도록 한다
  watch: {
    age() {
      //정규식을 활용해서 나이에 숫자가 들어갈수 없도록 한다.
      return (this.age = this.age.replace(/[^0-9]/g, ""));
    },
    phone1() {
      return (this.phone1 = this.phone1.replace(/[^0-9]/g, ""));
    },
    phone2() {
      return (this.phone2 = this.phone2.replace(/[^0-9]/g, ""));
    },
    phone3() {
      return (this.phone3 = this.phone3.replace(/[^0-9]/g, ""));
    }
  },
  methods: {
    ...mapMutations(["SET_REGISTER_MODAL"]),
    registerUser() {
      //최소 1개의 숫자혹은 특수문자를 포함해야 함

      const passwordCheckReg = /^(?=.*[a-zA-Z])((?=.*\d)|(?=.*\W)).{6,20}$/;
      let passwordValidation = passwordCheckReg.test(this.userPassword);
      if (this.id === "" || this.selectedDomain === "") {
        error("이메일이 공백입니다. 다시 입력해주세요", this);

        return;
      } else if (this.userPassword !== this.userPasswordCheck) {
        error("비밀번호가 일치하지 않습니다. 다시 입력해주세요", this);
        //비밀번호 초기화
        this.userPassword = this.userPasswordCheck = "";
        return;
      } else if (this.userPassword === "" || this.userPasswordCheck === "") {
        error("비밀번호를 입력해주세요", this);
        //비밀번호 초기화
        this.userPassword = this.userPasswordCheck = "";
        return;
      } else if (this.name === "") {
        error("이름을 입력해주세요", this);
        return;
        //휴대폰번호 비어있을때
      } else if (this.age === 0) {
        error("나이를 입력해주세요", this);
        return;
      } else if (this.age < 15) {
        error("15세 미만은 가입할 수 없습니다", this);
        return;
      } else if (
        this.phone1 === "" ||
        this.phone2 === "" ||
        this.phone3 === ""
      ) {
        error("휴대폰 번호를 모두 입력해주세요", this);
        return;
      } else if (passwordValidation === false) {
        error(
          "비밀번호는 최소 1개의 숫자 및 문자 그리고 특수문자를 포함해야합니다.",
          this
        );
        return;
      }
      this.registerRequest();
    },
    registerRequest() {
      //!axios 호출(util의 axios에 있음)
      const userId = this.id + "@" + this.selectedDomain;
      const phoneNumber = this.phone1 + "-" + this.phone2 + "-" + this.phone3;

      // console.log(userId, phoneNumber);
      let params = new URLSearchParams();
      params.append("userId", userId);
      params.append("userPassword", this.userPassword);
      params.append("name", this.name);
      params.append("age", this.age);
      //email 파라미터와 아이디를 동일시화
      params.append("email", userId);
      params.append("phoneNumber", phoneNumber);

      request("post", "user/register", params)
        .then(res => {
          console.log(res);
          if (res === "OK") {
            this.$toasted.show("회원가입이 완료되었습니다.", {
              type: "success",
              position: "top-right",
              duration: 2500
            });
            this.SET_REGISTER_MODAL(false);
          } else {
            error("중복된 아이디입니다", this);
          }
        })
        .catch(error => {
          console.log(error);
          error("중복된 아이디입니다", this);
        });
    },

    registerBroker() {
      //최소 1개의 숫자혹은 특수문자를 포함해야 함

      const passwordCheckReg = /^(?=.*[a-zA-Z])((?=.*\d)|(?=.*\W)).{6,20}$/;
      let passwordValidation = passwordCheckReg.test(this.userPassword);
      if (this.id === "" || this.selectedDomain === "") {
        error("이메일 이 공백입니다. 다시 입력해주세요", this);
        return;
      } else if (this.userPassword !== this.userPasswordCheck) {
        error("비밀번호가 일치하지 않습니다. 다시 입력해주세요", this);
        //비밀번호 초기화
        this.userPassword = this.userPasswordCheck = "";
        return;
      } else if (this.userPassword === "" || this.userPasswordCheck === "") {
        error("비밀번호를 입력해주세요", this);
        //비밀번호 초기화
        this.userPassword = this.userPasswordCheck = "";
        return;
      } else if (this.name === "") {
        error("이름을 입력해주세요", this);
        return;
        //휴대폰번호 비어있을때
      } else if (this.age === 0) {
        error("나이를 입력해주세요", this);
        return;
      } else if (this.age < 15) {
        error("15세 미만은 가입할 수 없습니다", this);

        return;
      } else if (
        this.phone1 === "" ||
        this.phone2 === "" ||
        this.phone3 === ""
      ) {
        error("휴대폰 번호를 모두 입력해주세요", this);
        return;
      } else if (passwordValidation === false) {
        error(
          "비밀번호는 최소 1개의 숫자 및 문자 그리고 특수문자를 포함해야합니다.",
          this
        );
        return;
      }
      this.registerBrokerRequest();
    },
    registerBrokerRequest() {
      //!axios 호출(util의 axios에 있음)
      const userId = this.id + "@" + this.selectedDomain;
      const phoneNumber = this.phone1 + "-" + this.phone2 + "-" + this.phone3;

      // console.log(userId, phoneNumber);
      let params = new URLSearchParams();
      params.append("brokerId", userId);
      params.append("brokerPassword", this.userPassword);
      params.append("name", this.name);
      params.append("age", this.age);
      //email 파라미터와 아이디를 동일시화
      params.append("email", userId);
      params.append("phoneNumber", phoneNumber);

      request("post", "broker/register", params)
        .then(res => {
          console.log(res);
          if (res === "OK") {
            this.$toasted.show("회원가입이 완료되었습니다.", {
              type: "success",
              position: "top-right",
              duration: 2500
            });
            this.SET_REGISTER_MODAL(false);
          } else {
            error("중복된 아이디입니다", this);
          }
        })
        .catch(error => {
          console.log(error);
          error("중복된 아이디입니다", this);
        });
    }
  }
};
</script>

<style scoped>
* {
  color: black;
}

.close-button > img {
  cursor: pointer;
  width: 20px;
  height: auto;
}

.register-header {
  display: flex;
  align-items: center;
  justify-content: space-between;
  border: none;
  background: transparent;
  text-align: left;
  margin-bottom: 20px;
  padding-bottom: 5px;
  font-size: 22px;
}
.register-information-container {
  border-top: 1px solid rgba(210, 210, 210);
  padding-top: 5px;
}

.register-information-wrapper {
  display: flex;
  margin-top: 20px;
  align-items: center;
}
.register-title {
  display: flex;
  align-items: center;
  width: 120px;
  height: 46px;
  font-size: 14px;
  /* line-height: 46px; */
  color: #222222;
}
.space {
  margin: 0 7px;
  font-size: 14px;
  color: #888888;
}

.space-age {
  width: 80px;
  margin: 0 7px;
  font-size: 14px;
  color: #888888;
}

.register-input-wrapper {
  margin-left: 10px;
  display: flex;
  align-items: center;
  width: 100%;
}
.register-input {
  width: 100%;
  height: 46px;
  padding: 4px 8px;
}

.register-email {
  width: 45%;
}

select {
  width: 200px;
  padding: 0.8em 0.5em;
  border: 1px solid #999;
  font-family: inherit;
  background: url("../assets/arrow.jpeg") no-repeat 95% 50%;
  background-size: 25px;
  border-radius: 0px;
  -webkit-appearance: none;
  -moz-appearance: none;
  appearance: none;
}

select::-ms-expand {
  display: none;
}

.register-email-domain-input {
  width: 60%;
  height: 46px;
  margin-right: 15px;
  padding: 4px 8px;
}

.register-input-phone {
  text-align: center;
}

.register-button {
  width: 100%;
  height: 70px;
  background-color: #1a5ae8;
  color: white;
  font-size: 17px;
  margin: 0;
  display: flex;
  justify-content: center;
  align-items: center;
  cursor: pointer;
}
</style>
