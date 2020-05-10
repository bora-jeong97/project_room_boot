<template>
  <div id="app">
    <!-- //footer -->
    <AppContainer>
      <div slot="banner" class="container">aa</div>
      <div slot="content1" class="container">aa1</div>
      <div slot="content2" class="container">aa2</div>
      <div slot="content3" class="container">aa3</div>
    </AppContainer>
  </div>
</template>
<script>
import AppContainer from "./AppContainer";
export default {
  name: "app",
  components: {
    AppContainer
  },
  data() {
    return {};
  },
  mounted() {}
};
</script>
<style scoped>
* {
  margin: 0;
  padding: 0;
  box-sizing: border-box;
}
button {
  background-color: #fff;
  /* border: 0; */
}
/* 전체 레이아웃 */

#app {
  font-size: 20px;
  color: #fff;
  /* text-align: center; */
}

#header {
  height: 140px;
}

#banner {
  height: 450px;
  line-height: 450px;
  background: #4dd0e1;
}

#contents {
  height: 880px;
}

#content1 {
  height: 90px;
  line-height: 90px;
  background-color: #26c6da;
}

#content2 {
  height: 480px;
  line-height: 480px;
  background-color: #00bcd4;
}

#content3 {
  height: 380px;
  line-height: 380px;
  background-color: #00acc1;
}

/* #footer-nav {
  height: 60px;
  line-height: 60px;
  background-color: #0097a7;
}

#footer-info {
  height: 160px;
  line-height: 160px;
  background-color: #00838f;
} */
/* 컨테이너 */

.container {
  width: 1100px;
  margin: 0 auto;
  height: inherit;
  background: rgba(0, 0, 0, 0.3);
}
</style>
