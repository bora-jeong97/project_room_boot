<template>
  <div id="app">
    <!-- <div id="header"></div> -->
    <!-- //header -->
    <div id="banner">
      <slot name="banner" class="container">banner</slot>
    </div>
    <!-- banner -->
    <div id="contents">
      <div id="content1">
        <slot name="content1" class="container">content1</slot>
      </div>
      <div id="content2">
        <slot name="content2" class="container">content2</slot>
      </div>
      <div id="content3">
        <slot name="content3" class="container">content3</slot>
      </div>
    </div>
    <!-- //contents -->

    <!-- //footer -->
  </div>
</template>
<script>
export default {
  name: "app",
  components: {},
  data() {
    return {};
  },
  mounted() {}
};
</script>
<style scoped>
/* #app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}
/* 
#nav {
  padding: 30px;
}

#nav a {
  font-weight: bold;
  color: #2c3e50;
}

#nav a.router-link-exact-active {
  color: #42b983;
} */

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
  /* height: 450px; */
  background: #ffff;
  display: block;
}

/* #contents {
  /* height: 880px; 
} */

/* #footer {
  height: 220px;
} */
/* 레이아웃 */

/* #header-top {
  height: 70px;
  line-height: 70px;
  background: #b2ebf2;
} */
/* #header-nav {
         height: 70px;
         line-height: 70px;
         background: #80deea;
     } */

#content1 {
  height: 420px;
  /* line-height: 350px; */
  background-color: #fff;
}

#content2 {
  height: 460px;
  /* overflow: hidden; */
  /* line-height: 480px; */
  margin-bottom: 40px;
}

#content3 {
  height: 380px;

  background-color: #fff;
}
/* 
#footer-nav {
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
  width: 1180px;
  margin: 0 auto;
  height: inherit;
  /* background: rgba(0, 0, 0, 0.3); */
}
</style>
