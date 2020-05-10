<template>
  <div class="easy-search-room-container">
    <div>
      <div class="easy-search-room-title">쉬운 방찾기</div>
      <div class="easy-search-room-sub-title">방찾기 초보를 위한 초간단 솔루션!</div>
      <div class="easy-searh-room-wrapper">
        <router-link
          style="text-decoration:none; outline: none; color:#222222;"
          :to="`/search`"
          v-for="(easyRoomData, index) in easyRoomDatas"
          v-bind:key="index"
          class="easy-search-room"
        >
          <div class="eash-search-room-image-wrapper">
            <img :src="easyRoomData.img" alt="없음" />
          </div>
          <div class="easy-search-room-content-wrapper">
            <div class="easy-search-room-content-title">{{easyRoomData.title}}</div>
            <div class="easy-search-room-content">{{easyRoomData.content}}</div>
          </div>
        </router-link>
      </div>
    </div>
    <div></div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      easyRoomDatas: [
        {
          img: require("@/assets/room1.jpg"),
          title: "월세 + 관리비",
          content: "월 50이하 원룸"
        },
        {
          img: require("@/assets/room2.jpg"),
          title: "보증금",
          content: "월 500이하 원룸"
        },
        {
          img: require("@/assets/room3.jpg"),
          title: "반려동물",
          content: "가능한 원룸"
        },
        {
          img: require("../../assets/room4.jpg"),
          title: "낭만적인",
          content: "옥탑방원룸"
        },
        {
          img: require("../../assets/room5.jpg"),
          title: "2억 이하",
          content: "전세 투쓰리룸"
        },
        {
          img: require("../../assets/room6.jpg"),
          title: "전세자금 대출",
          content: "가능한 투쓰리룸"
        }
      ]
    };
  }
};
</script>

<style >
* {
  box-sizing: border-box;
}
.easy-search-room-container {
  padding-top: 30px;
  border-top: 1px solid #dddddd;
  height: 100%;
  width: 100%;
  color: black;
}
.easy-search-room-title {
  text-align: center;
  font-size: 25px;
  font-weight: bold;
}
.easy-search-room-sub-title {
  margin-top: 7px;
  text-align: center;
  font-size: 16px;
}
.easy-searh-room-wrapper {
  margin-top: 25px;
  display: flex;
  justify-content: center;
}
.easy-search-room {
  border: 1px solid #cccccc;
  border-radius: 10px;
  width: 185px;
  cursor: pointer;
}
.easy-search-room:hover {
  opacity: 0.6;
}

.easy-search-room:not(:first-child) {
  margin-left: 14px;
}

.eash-search-room-image-wrapper {
  height: 100px;
  width: 100%;
}
.eash-search-room-image-wrapper > img {
  width: 100%;
  width: 185px;
  height: 100%;
}
.easy-search-room-content-wrapper {
  padding: 10px 15px;
}
.easy-search-room-content-title {
  font-size: 15px;
}
.easy-search-room-content {
  margin-top: 5px;
  font-size: 15px;
}
</style>