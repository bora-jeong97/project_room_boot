<template>
  <div id="app">
    <div id="banner">
      <slot name="banner" class="container">banner</slot>

      <!-- <div id="contents">
        <div id="content1">
          <slot name="content1" class="container">content1</slot>
        </div>
      </div>-->
    </div>
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
<style>
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

#banner1 {
  height: 1200px;
  background: #ffff;
}

#content1 {
  height: 420px;
  /* line-height: 350px; */
  background-color: #fff;
}

.container {
  width: 1180px;
  margin: 0 auto;
  height: inherit;
  /* background: rgba(0, 0, 0, 0.3); */
}
</style>
