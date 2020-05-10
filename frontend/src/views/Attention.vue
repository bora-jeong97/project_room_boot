<template>
  <EtcContainer class="container">
    <div slot="banner">
      <div class="attention-container">
        <div class="attention-title">관심목록</div>
        <div class="attention-select-wrapper">
          <!-- <div class="attention-select-item">최근본방</div> -->
          <!-- <router-link class="attention-select-item" :to="`/attention`">최근 본방</router-link> -->
          <router-link class="attention-select-item" :to="`/attention/keep`">찜한 방</router-link>
          <!-- <div class="attention-select-item">찜한 방</div> -->
        </div>
        <!-- router view 넣기 -->
        <router-view />
      </div>
    </div>
  </EtcContainer>
</template>
<script>
import EtcContainer from "./EtcContainer";
export default {
  name: "app",
  components: {
    EtcContainer
  },
  data() {
    return {};
  },
  mounted() {}
};
</script>
<style>
.attention-container {
  color: black;
}
.container {
  width: 1100px;
  margin: 0 auto;
  height: inherit;
  /* background: rgba(0, 0, 0, 0.3); */
}
.attention-title {
  text-align: center;
  font-size: 32px;
}
.attention-select-wrapper {
  border-bottom: 1px solid #bbbbbb;
  padding-bottom: 10px;
  margin-top: 20px;
  display: flex;
  justify-content: center;
}
.attention-select-item {
  color: black;
  text-decoration: none;
  font-weight: 100;
}
.attention-select-item:not(:first-child) {
  margin-left: 30px;
}
.router-link-exact-active {
  color: black;
  font-weight: bold;
}
</style>