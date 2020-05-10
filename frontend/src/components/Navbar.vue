<template>
  <!-- 해당 공간은 vue-cli 방식에서 component방식으로 넘겨줘서 spring의 tiles 처럼 해당 부분을 컴포넌트화 시켜서 붙일수 있다.-->
  <div id="app" class="header-container">
    <div id="header-top">
      <div class="header">
        <div class="header-left">
          <div>
            <router-link style="border: none;" class="room-selector1" :to="`/`">
              <img src="../assets/logo.png" />
            </router-link>
          </div>
          <div class="header-left-site-button1">
            <button>모든 방을 구해방!</button>
          </div>
          <!-- <div class="vertical-line"></div> -->
          <!-- <div class="header-left-site-button2">
            <button>구해방</button>
          </div>-->
        </div>
      </div>
    </div>
    <div class="header-right">
      <div class="header-menu">
        <div class="header-menu-item header-menu-item1">
          search
          <router-link style="border: none;" class="room-selector1" :to="`/search`">
            <button class="router-item">방찾기</button>
          </router-link>
        </div>
        <div v-if="loginData.auth === '3'" class="header-menu-item header-menu-item2">
          <router-link class="room-selector1" :to="`/attention/keep`">
            <button class="router-item">찜한 목록</button>
          </router-link>
        </div>
        <div v-if="loginData.auth === '3'" class="header-menu-item header-menu-item3">
          <router-link style="border: none;" class="room-selector1" :to="`/mypage`">
            <button>마이페이지</button>
          </router-link>
        </div>
        <div
          v-if="loginData.auth === '4' || loginData.auth === '5'"
          class="header-menu-item header-menu-item3"
        >
          <router-link style="border: none;" class="room-selector1" :to="`/sellRoom`">
            <button>방내놓기</button>
          </router-link>
        </div>
        <div
          v-if="loginData.auth === '4' || loginData.auth === '5'"
          class="header-menu-item header-menu-item3"
        >
          <router-link style="border: none;" class="room-selector1" :to="`/mypage/payment`">
            <button>결제 페이지</button>
          </router-link>
        </div>
        <!-- 로그인 처리하기 -->
        <div class="header-menu-item header-menu-item4">
          <router-link style="border: none;" class="room-selector1" :to="`/mypage/qna/write`">
            <button>문의 게시판</button>
          </router-link>
        </div>
      </div>

      <!-- 로그인 세션이 있을시에 -->
      <div v-if="loginData" class="header-login">
        <div class="header-login-item header-login-item1">
          <router-link style="border: none;" class="attention-select-item" :to="`/mypage`">
            <button>{{ loginData.name }}님 환영합니다</button>
          </router-link>

          <button @click="logout">로그아웃</button>
        </div>
      </div>

      <!-- 로그인시 -->
      <div v-else class="header-login">
        <div class="header-login-item header-login-item1">
          <button @click="SET_LOGIN_MODAL(true)">회원가입 및 로그인</button>
        </div>
        <!-- <div class="header-login-item header-login-item2">
          <button>로그인</button>
        -->
        <Login v-if="loginModal" />
        <Register v-if="registerModal" />
      </div>
    </div>
  </div>
</template>

<script>
import Login from "./Login";
import Register from "./Register";
import { mapState, mapMutations } from "vuex";
export default {
  components: {
    Login,
    Register
  },
  data() {
    //로그인 데이터
    return {};
  },
  mounted() {},
  computed: {
    ...mapState(["loginData", "loginModal", "registerModal"])
    // ...mapMutations(["SET_LOGIN_MODAL"])
  },
  methods: {
    ...mapMutations(["SET_LOGIN", "SET_LOGIN_MODAL", "SET_REGISTER_MODAL"]),
    getLoginData() {
      // this.loginData = localStorage.getItem('login');
      // this.SET_LOGIN(this.loginData);
    },
    logout() {
      console.log("test");
      sessionStorage.removeItem("login");
      this.SET_LOGIN("");
      //중복 이동 방지
      if (this.$route.path !== "/") {
        this.$router.push("/");
      }
    }
  }
};
</script>

<style scoped>
.header-container {
  position: relative;
  background-color: #fff;
  padding: 0 20px;
  display: flex;
  justify-content: space-between;
  height: 70px;
  color: #888888;
  border-bottom: 1px solid rgb(235, 235, 235);
  z-index: 99999;
}

.header-left {
  display: flex;
  align-items: center;
  height: 70px;
}

.header-left-site-button1 {
  display: flex;
  align-items: flex-start;
  margin-left: 20px;
  min-width: 90px;
}

.header-left-site-button2 {
  min-width: 90px;
  display: flex;
  align-items: flex-start;
}
.vertical-line {
  display: inline-block;
  width: 1px;
  height: 15px;
  background: rgb(221, 221, 221);
  margin: 8px 12px 0 12px;
}

.header-left-site-button1 > button,
.header-left-site-button2 > button {
  color: #888888;
  font-size: 13px;
}

.header-left-site-button2 {
  margin-left: 8px;
}

.header-right {
  display: flex;
  align-items: center;
}

.header-menu {
  display: flex;
}

.header-menu-item {
  min-width: 40px;
  margin-left: 35px;
}
.header-menu-item > button:hover {
  cursor: pointer;
}

.header-menu-item > button {
  color: rgb(34, 34, 34);
}
.router-item {
  cursor: pointer;
}

.header-login {
  display: flex;
  margin-left: 50px;
}
.header-login-item {
  min-width: 120px;
}
.header-login-item > button {
  margin-left: 8px;
  color: #888888;
  font-size: 13px;
  cursor: pointer;
}

button {
  background-color: white;
  border: 0;
}

img {
  height: 70px;
  /* height: auto; */
}
</style>
