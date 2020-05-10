<template>
  <!-- <div id="app">
    <Navbar />
    <div id="nav">
      <router-link to="/">Home</router-link>|
      <router-link to="/about">About</router-link>
      <div>
        <a href="./index.html">aaaa</a>
      </div>
    </div>
    <router-view />
  </div>-->
  <div id="app">
    <!-- <div id="header"></div> -->
    <Navbar style="z-index: 99999;"></Navbar>
    <!-- <div id="header-nav">
                <div class="container">header-nav</div>
    </div>-->
    <Spinner />
    <router-view />
    <!-- //header -->

    <!-- <div id="banner">
      <div class="container">banner</div>
    </div>-->
    <!-- banner -
    <div id="contents">
      <div id="content1">
        <div class="container">content1</div>
      </div>
      <div id="content2">
        <div class="container">content2</div>
      </div>
      <div id="content3">
        <div class="container">content3</div>
      </div>
    </div>
    -->
    <!-- //contents -->
    <!-- <div id="footer">
      <div id="footer-nav">
        <div class="container">footer-nav</div>
      </div>
      <div id="footer-info">
        <div class="container">footer-info</div>
      </div>
    </div>-->
    <Footer />

    <!-- //footer -->
  </div>
</template>
<script>
import Navbar from "./components/Navbar";
import Footer from "./components/Footer";
import Spinner from "./components/etc/Spinner";
export default {
  name: "app",
  components: {
    Navbar,
    Footer,
    Spinner
  },
  data() {
    return {};
  },
  mounted() {}
};
</script>
<style>
.toasted toasted-primary {
  position: relative;

  z-index: 99999 !important;
}
/* #app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}

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
/* * {
  margin: 0;
  padding: 0;
  box-sizing: border-box;
}
button {
  background-color: #fff;

}


#app {
  font-size: 20px;
  color: #fff;

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
} */
/* #footer {
  height: 220px;
} */ /* 레이아웃 */
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
/* #content1 {
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
} */
/* #footer {
  height: 220px;
}
#footer-nav {
  height: 60px;
  line-height: 60px;
  background-color: #0097a7;
} */
/* #footer-info {
  height: 160px;
  line-height: 160px;
  background-color: #00838f;
} */
/* 컨테이너 */
/* 
.container {
  width: 1100px;
  margin: 0 auto;
  height: inherit;
  background: rgba(0, 0, 0, 0.3);
} */
</style>
