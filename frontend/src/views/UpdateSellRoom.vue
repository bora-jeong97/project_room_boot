<template>
  <EtcContainer class="container">
    <div slot="banner">
      <div class="sell-room-container">
        <div class="sell-room-title">방 수정하기</div>
        <div class="sell-room-select-wrapper">
          <RoomUpdate />
          <!-- <div class="sell-room-select-item">최근본방</div> -->
          <!-- <router-link class="sell-room-select-item" :to="`/sellRoom/`">방 내놓기</router-link> -->
          <!-- <router-link class="sell-room-select-item" :to="`aa`">연락한 부동산</router-link> -->

          <!-- <div class="sell-room-select-item">찜한 방</div> -->
        </div>
        <!-- router view 넣기 -->
        <router-view />
      </div>
    </div>
  </EtcContainer>
</template>

<script>
import EtcContainer from "../views/EtcContainer";
import RoomUpdate from "../components/banner/RoomUpdate";
export default {
  components: {
    EtcContainer,
    RoomUpdate
  }
};
</script>

<style>
.sell-room-container {
  margin-top: 60px;
  padding-bottom: 80px;
}
.sell-room-title {
  color: black;
  text-align: center;
  font-size: 33px;
}
.sell-room-select-wrapper {
  margin-top: 60px;
  display: flex;
  justify-content: space-between;

  /* border-bottom: 1px solid #dddddd; */
}

.sell-room-select-item {
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
