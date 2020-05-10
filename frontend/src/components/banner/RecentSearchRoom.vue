<template>
  <div class="recent-room-container">
    <div class="recent-room-wrapper">
      <div v-for="(recentData, index) in recentDatas" :key="index" class="recent-room">
        <div class="recent-image-wrapper">
          <!-- v-if로 클릭시 class 추가 -->
          <img class="recent-image" :src="recentData.img" />

          <img
            v-if="recentData.heart"
            class="heart-image"
            src="../../assets/fillHeart.png"
            @click="heartClick(recentData)"
            alt
          />
          <img
            v-else
            class="heart-image"
            src="../../assets/heart.png"
            @click="heartClick(recentData)"
            alt
          />
        </div>
        <div class="recent-information-wrapper">
          <div class="recent-information">
            <div class="recent-information-room-type">{{recentData.type}}</div>
            <div class="recent-information-room-deposit">{{recentData.deposit}}</div>
            <div class="recent-information-floor">{{recentData.fllor}}</div>
            <div class="recent-information-content">{{recentData.content}}</div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      recentDatas: [
        {
          img: require("../../assets/room1.jpg"),
          type: "원룸",
          deposit: "월세 300/40",
          floor: "2층 17평",
          content: "야옹이",
          heart: true
        },
        {
          img: require("../../assets/room3.jpg"),
          type: "쓰리룸",
          deposit: "전세 3억",
          floor: "2층 32평",
          content: "깔끔하게 리모델링 된 주차걱정 없는 3룸 2억에 모십니다",
          heart: false
        },
        {
          img: require("../../assets/room6.jpg"),
          type: "원룸",
          deposit: "월세 300/38",
          floor: "2층 15평",
          content: "귀가길 안전한 역세권 방",
          heart: true
        },
        {
          img: require("../../assets/room3.jpg"),
          type: "쓰리룸",
          deposit: "매매 2억 30000",
          floor: "2층 50평",
          content: "남향, 일렬주차, 전세, 투자금 2천",
          heart: false
        }
      ]
    };
  },
  methods: {
    heartClick(recentData) {
      // console.log(recentData);
      recentData.heart = !recentData.heart;
    }
  }
};
</script>

<style>
.recent-room-container {
  margin-top: 20px;
  color: black;
}
.recent-room-wrapper {
  display: flex;
}
.recent-room {
  width: 280px;
  cursor: pointer;
  margin: 5px 10px 5px 0;
}
.recent-room:hover {
  opacity: 0.6;
}
.recent-image-wrapper {
  position: relative;
  height: 186px;
}
.recent-image {
}
.heart-image {
  cursor: pointer;
  width: 25px;
  position: absolute;
  top: 5px;
  right: 12px;
}

.heartClicked {
  display: block;
  width: 25px;
  height: 25px;
}

.recent-information-wrapper {
}
.recent-information {
}
.recent-information-room-type {
  margin-top: 20px;
  font-size: 13px;
}

.recent-information-room-deposit {
  margin-top: 9px;
  font-size: 20px;
  font-weight: bold;
}

.recent-information-floor {
  margin-top: 9px;
  font-size: 14px;
}

.recent-information-content {
  margin-top: 4px;
  font-size: 14px;
  width: 280px;
  overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap;
}
</style>