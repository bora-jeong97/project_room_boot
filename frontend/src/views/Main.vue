<template>
  <div id="app">
    <!-- //footer -->
    <AppContainer>
      <div slot="banner" class="container">
        <MainBanner />
      </div>
      <div slot="content1" class="container">
        <EasySearchRoom />
      </div>
      <div v-if="loginData.user_id" slot="content2" class="container">
        <div class="room-selector">
          <!-- <router-link class="room-selector1" :to="`/`">최근 본방</router-link> -->
          <router-link class="room-selector2" :to="`/`">찜한 방</router-link>
          <div class="line-divider"></div>
        </div>
        <div class="room-selector-title">찜한 방들을 볼수 있어요</div>
        <router-view />
      </div>

      <div v-else slot="content2" class="container" @click="SET_LOGIN_MODAL(true)">
        <div class="room-selector">
          <!-- <router-link class="room-selector1" :to="`/`">최근 본방</router-link> -->
          <router-link class="room-selector2" :to="`/`">찜한 방</router-link>
          <div class="line-divider"></div>
        </div>
        <div class="room-selector-title">찜한 방들을 볼수 있어요</div>
        <div class="not-login-kepp-wrapper">
          <p class="not-login-keep">유저 로그인 하시면 찜 목록을 보실수 있어요!</p>
        </div>
        <div class="not-login-kepp-wrapper">
          <p class="not-login-keep">유저 로그인 하시면 찜 목록을 보실수 있어요!</p>
        </div>
        <div class="not-login-kepp-wrapper">
          <p class="not-login-keep">유저 로그인 하시면 찜 목록을 보실수 있어요!</p>
        </div>
        <div class="not-login-kepp-wrapper">
          <p class="not-login-keep">유저 로그인 하시면 찜 목록을 보실수 있어요!</p>
        </div>
      </div>
      <div slot="content3" class="container">
        <Menu />
      </div>
    </AppContainer>
  </div>
</template>
<script>
import AppContainer from "./AppContainer";
import MainBanner from "../components/banner/MainBanner";
import EasySearchRoom from "../components/content1/EasySearchRoom";
import Menu from "../components/content3/Menu";
import { mapState, mapMutations } from "vuex";
export default {
  name: "app",
  components: {
    AppContainer,
    MainBanner,
    EasySearchRoom,
    Menu
  },
  data() {
    return {
      clickedSelector: true
    };
  },
  methods: {
    ...mapMutations(["SET_LOGIN_MODAL"]),
    clickSelector() {
      !this.clickedSelector;
    }
  },
  computed: {
    ...mapState(["loginData"])
  },
  mounted() {}
};
</script>
<style scoped>
.room-selector {
  border-top: 1px solid #cccccc;
  padding-top: 50px;
  display: flex;
  align-items: center;
  width: 1100px;
  margin: 0 auto;
}
.room-selector1 {
  text-decoration: none;
  color: #cccccc;
  font-size: 28px;
}

.line-divider {
  display: block;
  width: 1px;
  height: 30px;
  background-color: #cccccc;
  content: "";
  margin: 0 20px;
}

.room-selector2 {
  text-decoration: none;
  color: #cccccc;
  font-size: 28px;
}
.router-link-exact-active {
  color: black;
  font-weight: bold;
}
.room-selector-title {
  margin-top: 20px;
  margin-bottom: 20px;
  color: black;
  font-weight: 100;
  font-size: 16px;
}
.not-login-keep {
  color: rgb(148, 152, 160);
  font-size: 13px;
  font-weight: 300;
  line-height: 186px;
  text-align: center;
}
.not-login-kepp-wrapper {
  float: left;
  width: 280px;
  height: 186px;
  background-color: rgb(251, 251, 252);
  border-width: 1px;
  border-style: dashed;
  border-color: rgb(214, 216, 219);
  border-image: initial;
  border-radius: 3px;
  margin-right: 10px;
}
</style>
