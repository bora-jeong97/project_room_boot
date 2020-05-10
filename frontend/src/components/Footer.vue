<template>
  <div id="footer">
    <div id="footer-nav">
      <div class="container">
        <div class="footer-nav-container">
          <div class="footer-nav-wrapper1">
            <div>회사소개</div>
            <div>이용약관</div>
            <div>개인정보처리방침</div>
            <div>매물관리규정</div>
            <div>자동저장서비스</div>
          </div>
          <!-- <div class="footer-nav-wrapper2">
            <div>Top</div>
            <!-- <div></div>
          </div> 
          -->
        </div>
      </div>
    </div>
    <div id="footer-info">
      <div class="container info-container">
        <div>(주)구해방</div>
        <div>대표: 이온유, 황밝으리, 정보라, 이지예, 최용호</div>

        <div>주소:서울시 강남구</div>
        <div>고객센터: 010-9536-5780</div>
        <div>팩스: 000-000-000 사업 제휴문의: dhsdb1541@naver.com</div>
        <div>자주묻는 질문</div>
        <div>구해방, Inc. All rights reserved.</div>
      </div>
    </div>
  </div>
</template>

<script>
export default {};
</script>

<style scoped>
#footer {
  min-height: 340px;
  background-color: #373737;
}
#footer-nav {
  height: 70px;
  line-height: 70px;
  /* background-color: #0097a7; */
}
.footer-nav-container {
  display: flex;
  justify-content: space-between;
}
.footer-nav-wrapper1 {
  display: flex;
  align-items: center;
}

.footer-nav-wrapper1 > div {
  color: #aaaaaa;
  font-size: 13px;
}
.footer-nav-wrapper1 > div:hover {
  color: #aaaaaa;
  color: #fff;
  font-weight: bold;
  cursor: pointer;
}

.footer-nav-wrapper1 > div:not(:first-child) {
  margin-left: 20px;
}

.footer-nav-wrapper2 {
  display: flex;
}
#footer-info {
  height: 270px;

  /* background-color: #373737; */
  /* background-color: #00838f; */
}
.container {
  width: 1100px;
  margin: 0 auto;
  height: inherit;
  background-color: #373737;
}
.info-container {
  border-top: 1px solid rgb(70, 70, 70);
  padding-top: 40px;
}

.info-container > div {
  margin-top: 6px;
  font-size: 12px;
  color: #aaaaaa;
}

.info-container > div:nth-child(6) {
  margin-top: 30px;
}

.info-container > div:last-child {
  margin-top: 20px;
}
</style>