<template>
  <div class="qna-container">
    <div class="qna-link-container">
      <router-link
        style="text-decoration:none; color:#222222; font-size:15px;"
        :to="`/mypage/qna/write`"
      >문의 남기기</router-link>
      <router-link
        style="text-decoration:none; color:#222222;  font-size:15px;"
        :to="`/mypage/qna/list`"
      >문의한 내역</router-link>
    </div>
    <router-view></router-view>
  </div>
</template>

<script>
export default {};
</script>

<style>
.qna-container {
  padding: 40px 120px;
}
.qna-link-container {
  display: flex;
  align-items: center;
  justify-content: center;
  margin-bottom: 60px;
}
.qna-link-container > a:not(:first-child) {
  margin-left: 30px;
}
</style>