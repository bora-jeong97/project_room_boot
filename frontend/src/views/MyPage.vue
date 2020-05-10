<template>
  <EtcContainer class="container">
    <div slot="banner">
      <div class="mypage-container">
        <div v-if="loginData.user_id" class="mypage-title">MY 구해방</div>
        <div v-else-if="loginData.broker_id" class="mypage-title">MY 구해방(공인중개사)</div>
        <div class="mypage-select-wrapper">
          <!-- <div class="mypage-select-item">최근본방</div> -->
          <router-link class="mypage-select-item" :to="`/mypage/`">정보수정</router-link>
          <!-- <router-link class="mypage-select-item" :to="`aa`"
            >연락한 부동산</router-link
          >-->
          <router-link
            v-if="loginData.user_id"
            class="mypage-select-item"
            :to="`/mypage/wishList`"
          >내가 찜한 목록</router-link>

          <!-- <router-link v-if="loginData.user_id" class="mypage-select-item" :to="`bb`">내가 쓴 리뷰</router-link> -->

          <router-link
            v-if="loginData.broker_id"
            class="mypage-select-item"
            :to="`/mypage/payment`"
          >결제 페이지(글쓰기)</router-link>

          <router-link
            v-if="loginData.broker_id"
            class="mypage-select-item"
            :to="`/mypage/broker/${loginData.id}`"
          >내가 올린 방 목록</router-link>

          <router-link class="mypage-select-item" :to="`/mypage/qna/write`">문의 게시판</router-link>
          <!-- <div class="mypage-select-item">찜한 방</div> -->
        </div>
        <!-- router view 넣기 -->
        <router-view />
      </div>
    </div>
  </EtcContainer>
</template>
<script scoped>
import EtcContainer from "./EtcContainer";
import { mapState } from "vuex";
import { request } from "../util/axios";
export default {
  name: "app",
  components: {
    EtcContainer
  },
  data() {
    return {};
  },
  computed: {
    ...mapState(["loginData"])
  },
  mounted() {},
  methods: {}
};
</script>
<style>
.mypage-container {
  margin-top: 60px;
  padding-bottom: 80px;
}
.mypage-title {
  color: black;
  text-align: center;
  font-size: 33px;
}
.mypage-select-wrapper {
  margin-top: 60px;
  display: flex;
  justify-content: space-between;

  /* border-bottom: 1px solid #dddddd; */
}

.mypage-select-item {
  color: black;
  text-decoration: none;
  font-weight: 100;
  margin: 0 auto;
  border-bottom: 1px solid #dddddd;
  width: 100%;
  text-align: center;
  padding-bottom: 12px;
  font-size: 16px;
}
.router-link-exact-active {
  color: black;
  font-weight: bold;
  border-bottom: 2px solid rgb(72, 72, 72);
}
</style>
