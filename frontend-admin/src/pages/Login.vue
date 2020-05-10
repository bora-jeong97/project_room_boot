<template>
  <Modal>
    <div slot="header">
      <notifications group="notifyApp" position="right right" style="margin-right: 30vh;" />
      <div class="header-top-wrapper">
        <div class="login-selector">
          <div
            @click="loginSelect"
            class="login-selector-user"
            :class="{ selectedLogin: loginSelector }"
          >관리자</div>
          <!-- <div
            @click="brokerlLoginSelect"
            class="login-selector-broker"
            :class="{ selectedLogin: !loginSelector }"
          >
            공인 중개사
          </div>-->
        </div>
        <div @click="SET_LOGIN_MODAL(false)" class="close-button">
          <img src="../assets/close.png" />
        </div>
      </div>
      <div v-if="loginSelector" class="login-title">로그인(관리자)</div>
      <!-- <div v-else class="login-title">로그인(공인중개사)</div> -->
    </div>
    <div slot="body">
      <div class="login-input-wrapper">
        <input v-model="adminId" class="login-id" placeholder="아이디" />
        <input v-model="adminPassword" type="password" class="login-password" placeholder="비밀번호" />
      </div>
      <div class="login-check-wrapper"></div>
      <div class="login-button-wrapper">
        <div>
          <button v-if="loginSelector" @click="login" class="login-button">로그인(관리자)</button>
          <!-- <button v-else @click="loginBroker" class="login-button">
            로그인(공인중개사)
          </button>-->
        </div>
        <div>
          <!-- <button>로그인</button>
          <button>로그인</button>-->
        </div>
        <!-- <div class="register-wrapper">
          아직 회원이 아니세요?
          <button @click="registerClick('user')" class="register-button">
            이메일로 회원가입
          </button>
        </div>-->
      </div>
    </div>
    <div slot="footer">
      <!-- <div class="move-broker">
        방을 판매하세요?
        <div>
          <button @click="registerClick('broker')" class="move-broker-button">
            공인중개사 회원가입
          </button>
        </div>
      </div>-->
    </div>
  </Modal>
</template>

<script>
import Modal from "./Modal";
import { mapSate, mapMutations } from "vuex";
import { request } from "../util/axios";
import { success, error, normal } from "../util/notification";
export default {
  components: {
    Modal
  },
  data() {
    return {
      loginSave: false,
      adminId: "",
      adminPassword: "",
      loginSelector: true
    };
  },
  mounted() {
    this.adminId = localStorage.getItem("loginId");
    localStorage.getItem("loginId") === null
      ? (this.loginSave = false)
      : (this.loginSave = true);
  },
  computed: {},
  methods: {
    ...mapMutations([
      "SET_REGISTER_MODAL",
      "SET_LOGIN",
      "SET_PROFILE_IMAGE",
      "SET_SELECT_REGISTER",
      "SET_LOGIN_MODAL"
    ]),
    loginSelect() {
      this.loginSelector = true;
    },
    brokerlLoginSelect() {
      this.loginSelector = false;
    },
    //로그인 아이디 localStorage 저장
    setLoginSave() {
      if (this.loginSave === false) {
        localStorage.setItem("loginId", this.adminId);
      } else {
        localStorage.removeItem("loginId");
      }
    },

    // loginAfter(res) {
    //   //로그인이 안되었을때

    // },
    login() {
      console.log("aaa");
      if (this.adminId === "") {
        error("아이디를 입력해주세요", this);

        return;
      } else if (this.adminPassword === "") {
        error("비밀번호를 입력해주세요", this);

        return;
      }
      let params = new URLSearchParams();
      params.append("adminId", this.adminId);
      params.append("adminPassword", this.adminPassword);
      request("post", "admin/adminLogin", params).then(res => {
        console.log(res);
        console.log("aaaaaa");
        if (res === "") {
          sessionStorage.removeItem("login");
          error(
            "로그인에 실패하였습니다 아이디및 비밀번호를 확인해주세요.",
            this
          );

          return;
        } else {
          // console.log(res);
          //로그인정보 세션에 저장

          this.$toasted.show(`${res.name}님 환영합니다`, {
            type: "success",
            position: "top-right",
            duration: 2500
          });
          //로그인 정보를 세션에 담는다
          sessionStorage.setItem("login", JSON.stringify(res));
          //로그인시 현재 아이디 값을 localStroage에 다시 저장
          //아이디 저장용도로 localStorate 활용
          localStorage.setItem("loginId", this.adminId);
          //vuex 에 저장하기
          this.SET_LOGIN(res);
          this.SET_REGISTER_MODAL(false);
          this.SET_PROFILE_IMAGE(res.profile_image);
        }
      });
    }
  }
};
</script>

<style scoped>
* {
  color: black;
}
.header-top-wrapper {
  display: flex;
  align-items: center;
  margin-bottom: 10px;
}
.login-selector-broker {
  margin-left: 20px;
}
.close-button {
  /* margin-left: auto; */
  cursor: pointer;
}
.close-button > img {
  width: 20px;
  height: auto;
}
.login-selector {
  width: 100%;
  display: flex;
  margin-top: 10px;
  margin-bottom: 10px;
  /* margin-left: 60px; */
  justify-content: center;
  padding-left: 20px;
}

.login-selector > div {
  font-size: 14px;
}

.selectedLogin {
  font-weight: bold;
  padding-bottom: 2px;
  border-bottom: 2px solid #4caf50;
}
.login-input-wrapper {
  margin: 0 auto;
}
.login-title {
  text-align: center;
  margin-bottom: 20px;
}
.login-id {
  padding: 0 15px;
  width: 100%;
  height: 46px;
}
.login-password {
  margin-top: 8px;
  padding: 0 15px;
  width: 100%;
  height: 46px;
}

.login-check-wrapper {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin: 18px 0;
  border: 0;
}

.login-check-wrapper > * {
  color: #888888;
  font-size: 14px;
}
.find-password-button {
  cursor: pointer;
  border: 0;
  color: #444444;
}

.login-button {
  cursor: pointer;
  width: 100%;
  height: 60px;
  background-color: #4caf50;
  border: 0;
  color: #fff;
}

.register-wrapper {
  text-align: center;
  color: #888888;
  padding: 24px 0 5px 0;
  font-size: 14px;
}

.register-button {
  cursor: pointer;
  margin-left: 6px;
  color: #1564f9;
  border: 0;
}

.move-broker {
  color: #888888;
  text-align: center;
  padding-bottom: 10px;
}
.move-broker-button {
  cursor: pointer;
  margin-top: 10px;
  padding: 10px 20px;
  border: 1px solid #888888;
  /* border: 0; */
}

/* 체크박스 스타일링 */
.checkbox {
  display: inline-flex;
  cursor: pointer;
  position: relative;
}

.checkbox > span {
  /* color: #34495e; */
  padding: 0.5rem 0.25rem;
}

.checkbox > input {
  height: 20px;
  width: 20px;
  -webkit-appearance: none;
  -moz-appearance: none;
  -o-appearance: none;
  appearance: none;
  border: 1px solid #34495e;
  border-radius: 4px;
  outline: none;
  transition-duration: 0.3s;
  background-color: #fff;
  cursor: pointer;
}

.checkbox > input:checked {
  border: 1px solid #1564f9;
  background-color: #1564f9;
}

.checkbox > input:checked + span::before {
  content: "\2713";
  font-size: 15px;
  display: block;
  text-align: center;
  color: #fff;
  position: absolute;
  left: 0.35rem;
  top: 0.4rem;
}
.login-check {
  display: flex;
  align-items: center;
}
.checkbox > input:active {
  border: 2px solid #34495e;
}
</style>
