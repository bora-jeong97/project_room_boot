
<template>
  <!-- 해당 공간은 vue-cli 방식에서 component방식으로 넘겨줘서 spring의 tiles 처럼 해당 부분을 컴포넌트화 시켜서 붙일수 있다.-->
  <div>
    <div id="header-top">
      <div class="header-container">
        <div class="header-left">
          <div>
            <button>구해방</button>
          </div>
          <div>
            <button>프로중개사이트</button>
          </div>
        </div>
        <div>
          <button>방주인사이트</button>
        </div>
      </div>
    </div>
    <div class="header-right">
      <div class="header-menu">
        <div>
          <button>방찾기</button>
        </div>
        <div>
          <button>분양</button>
        </div>
        <div>
          <button>관심목록</button>
        </div>
        <div>
          <button>방내놓기</button>
        </div>
        <div>
          <button>알림</button>
        </div>
      </div>
      <div class="header-login">
        <div>
          <button>회원가입</button>
        </div>
        <div>
          <button>로그인</button>
        </div>
      </div>
    </div>

    <div id="header-nav">
      <div class="container">header-nav</div>
    </div>
  </div>
</template>

<script>
export default {};
</script>

<style>
.header-container {
  background-color: #fff;
  color: rgba(0, 0, 0, 0.3);
  padding: 0 20px;
  display: flex;
  justify-content: space-between;
}

.header-left {
  display: flex;
}

.header-right {
  display: flex;
}

.header-menu {
  display: flex;
}

.header-login {
  display: flex;
  margin-left: 30px;
}

button {
  background-color: white;
  border: 0;
}
</style>