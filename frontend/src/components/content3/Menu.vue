<template>
  <div class="menu-container">
    <div>
      <div class="menu-title">형태별검색</div>
      <div class="menu-item">
        <div>원룸</div>
        <div>투 쓰리룸</div>
        <div>오피스텔</div>
        <div>아파트</div>
      </div>
    </div>
    <div>
      <div class="menu-title">공인중개사 회원가입</div>
      <div class="menu-item">
        <div>구해방프로</div>
      </div>
    </div>
    <div>
      <div class="menu-title">상품소개</div>
      <div class="menu-item">
        <div>패키지상품</div>
        <div>일반 상품</div>
        <div>단지 상품</div>
        <div>프리미엄 상품</div>
        <div>플러스 상품</div>
        <div>이 지역 부동산 상품</div>
      </div>
    </div>
    <div>
      <div class="menu-title">구해방 뉴스</div>
      <div class="menu-item">
        <div>공지사항</div>
        <div>이벤트</div>
      </div>
    </div>
    <div>
      <div class="menu-title">고객 지원</div>
      <div class="menu-item">
        <div>자주묻는 질문</div>
        <div>Android</div>
        <div>IOS</div>
        <div>구해방 이용 설명서</div>
      </div>
    </div>
    <div>
      <div class="menu-title">About 구해방</div>
      <div class="menu-item">
        <div>회사소개</div>
        <div>오시는 길</div>
        <div>제휴문의</div>
      </div>
    </div>
  </div>
</template>

<script>
export default {};
</script>

<style>
.menu-container {
  padding-top: 30px;
  border-top: 1px solid #cccccc;
  display: flex;
  color: black;
  justify-content: space-between;
}
.menu-title {
  font-size: 18px;
}
.menu-item {
  margin-top: 26px;
}
.menu-item > div {
  font-weight: 100;
  font-size: 16px;
  margin-top: 8px;
  cursor: pointer;
}
</style>