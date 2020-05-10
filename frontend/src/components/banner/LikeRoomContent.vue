<template>
  <div class="recent-room-container">
    <div v-if="recentDatas.length" class="recent-room-wrapper">
      <router-link
        :to="`/search/detail/${recentData.roomid}`"
        v-for="(recentData, index) in recentDatas"
        :key="index"
        class="recent-room"
        style="text-decoration:none; outline: none; color:#222222;"
      >
        <div class="recent-image-wrapper">
          <!-- v-if로 클릭시 class 추가 -->
          <!-- <img class="recent-image" :src="recentData.img" /> -->
          <img
            v-if="
              recentData.room_picture_dto_list &&
                recentData.room_picture_dto_list[0]
            "
            class="recent-image"
            :src="recentData.room_picture_dto_list[0].file_name"
            alt="이미지없음"
          />
          <img src="../../assets/room1.jpg" v-else class="recent-image" />
          <div
            v-if="
              loginData.auth === '3' &&
                recentData.userid === loginData.id"
          >
            <img
              @click.prevent.stop="like($event, recentData.room_dto.id)"
              class="heart-image"
              :src="fillHeart"
              alt
            />
            <!-- <img
              @click.prevent.stop="like($event, recentData.room_dto.id)"
              class="heart-image"
              :src="heart"
              alt
            />-->
          </div>
          <!-- <div v-else-if="loginData.auth === '3'">
            
          </div>-->

          <!-- 로그인 안했을때 -->
          <div v-else-if="!loginData">
            <img @click.prevent.stop="goToLogin()" class="heart-image" :src="heart" />
          </div>
          <!-- 일반 유저가 아닌 사람이 로그인했을때 하트 보여주지 않기 -->
          <div v-else-if="loginData.auth !== '3'"></div>
          <!-- <img
            class="heart-image"
            src="../../assets/fillHeart.png"
            @click.prevent.stop="heartClick(recentData)"
            alt
          />-->
          <!-- <img
            v-else
            class="heart-image"
            src="../../assets/heart.png"
            @click="heartClick(recentData)"
            alt
          />-->
        </div>
        <div class="recent-information-wrapper">
          <div v-if="recentData.room_dto" class="recent-information">
            <div class="recent-information-room-type">{{ recentData.room_dto.room_type }}</div>
            <div class="recent-information-room-deposit">{{ recentData.room_dto.deposit }}</div>
            <div class="recent-information-floor">{{ recentData.room_dto.floor }}</div>
            <div class="recent-information-content">{{ recentData.room_dto.content }}</div>
          </div>
        </div>
      </router-link>
    </div>
    <div v-else class="recent-room-wrapper">
      <div class="nothing-like">목록이 없습니다</div>
    </div>
  </div>
</template>

<script>
import { mapMutations, mapState } from "vuex";
import { requestParams } from "../../util/axios";
export default {
  // props: ["roomLists"],
  mounted() {
    console.log("마운트");
    this.getWishList();
  },
  data() {
    return {
      heart: require("../../assets/heart.png"),
      fillHeart: require("../../assets/fillHeart.png"),
      recentDatas: []
    };
  },
  computed: {
    ...mapState(["loginData"])
  },
  methods: {
    ...mapMutations(["SET_LOGIN_MODAL"]),
    getWishList() {
      requestParams("get", "wishlist/getWishFourList", {
        USERId: this.loginData.id
      }).then(res => {
        console.log(res);
        this.recentDatas = res;
        // this.roomLists = res;
      });
    },
    like(e, id) {
      // 이미지 처리
      // 하트가 차있을때 비우기
      if (e.target.src.split("img/")[1] === this.fillHeart.split("img/")[1]) {
        e.target.src = this.heart;
        console.log(id);
        requestParams("get", "wishlist/delWishList", {
          USERId: this.loginData.id,
          ROOMId: id
        }).then(res => {
          console.log(res);
        });
      }
      // 하트가 비어있을때 채우기
      else if (e.target.src.split("img/")[1] === this.heart.split("img/")[1]) {
        e.target.src = this.fillHeart;
        console.log(id);
        requestParams("get", "wishlist/addLike", {
          USERId: this.loginData.id,
          ROOMId: id
        }).then(res => {
          console.log(res);
        });
      }
    },
    filterMyLikeImage(list) {
      let filterLike = list.filter(li => {
        return li.userid === this.loginData.id;
      });
      return filterLike;
    },
    goToLogin() {
      this.SET_LOGIN_MODAL(true);
    }
  }
};
</script>

<style>
.recent-room-container {
  margin-top: 20px;
  color: #222;
  min-height: 400px;
}
.recent-room-wrapper {
  display: flex;
}
.recent-room {
  width: 280px;
  cursor: pointer;
}
.recent-room:hover {
  opacity: 0.6;
}

.recent-image-wrapper {
  position: relative;
  width: 280px;
  height: 186px;
}
.recent-image {
  width: 100%;
  height: 186px;
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

.nothing-like {
  margin: 0 auto;
  height: 400px;
  display: flex;
  align-items: center;
  justify-content: center;
  padding-right: 50px;
}
</style>
