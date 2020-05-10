<template>
  <EtcContainer>
    <div slot="banner">
      <div class="search-option-container">
        <div class="search-option-wrapper1">
          <div @click="openEvent('option6')" class="search-option-search-input-wrapper">
            <input placeholder="신림동" @input="axiosSearch" v-model="keyword2" />
            <div
              :class="{ 'search-option-active' : optionModalType.option6}"
              v-if="optionModalType.option6"
              class="option-modal-wrapper-input"
            >
              <ul>
                <li
                  style="list-style:none; margin-top:8px;"
                  v-for="(res, index) in searchList"
                  :key="index"
                >
                  <div
                    style="margin-top:3px;"
                    v-on:click.prevent.stop="moveSearch(res.d_name , res.latitude , res.longitude)"
                    class="router-item router-item-search"
                  >{{res.s_name}} {{res.g_name}} {{res.d_name}}</div>
                </li>
              </ul>
            </div>
          </div>
          <div
            :class="{ 'search-option-active' : optionModalType.option1}"
            class="search-option-option search-option-option1"
          >
            <span @click="openEvent('option1')" class="search-option-option-wrapper">원룸, 투·쓰리룸, 오피스텔</span>
            <i @click="openEvent('option1')" class="search-option-option-arrow-wrapper">
              <img v-if="!optionModalType.option1" src="../assets/down-arrow.png" />
              <img v-else src="../assets/up-arrow.png" />
            </i>
            <div>
              <div v-if="optionModalType.option1" class="option-modal-wrapper">
                <div class="option-modal-type-title">방종류</div>
                <div class="option-modal-type-sub-title">중복선택이 가능합니다</div>
                <div class="option-modal-type-wrapper">
                  <div class="option-modal-check-wrapper">
                    <label class="login-check checkbox">
                      <input
                        v-model="roomTypeOption"
                        type="checkbox"
                        value="one"
                        @change="valueSetting()"
                      />
                      <span>원룸</span>
                    </label>
                    <label class="login-check checkbox">
                      <input
                        v-model="roomTypeOption"
                        type="checkbox"
                        value="twoOrThree"
                        @change="valueSetting()"
                      />
                      <span>투·쓰리룸</span>
                    </label>
                    <label class="login-check checkbox">
                      <input
                        v-model="roomTypeOption"
                        type="checkbox"
                        value="officetel"
                        @change="valueSetting()"
                      />
                      <span>오피스텔</span>
                    </label>
                  </div>
                </div>
              </div>
            </div>
          </div>
          <div
            :class="{ 'search-option-active' : optionModalType.option2}"
            class="search-option-option search-option-option2"
          >
            <span @click="openEvent('option2')" class="search-option-option-wrapper">월세,전세,매매</span>
            <i @click="openEvent('option2')" class="search-option-option-arrow-wrapper">
              <img v-if="!optionModalType.option2" src="../assets/down-arrow.png" />
              <img v-else src="../assets/up-arrow.png" />
            </i>
            <div>
              <div v-if="optionModalType.option2" class="option-modal-wrapper">
                <div class="option-modal-type-title">매물 종류</div>
                <div class="option-modal-type-sub-title">중복선택이 가능합니다</div>
                <div class="option-modal-type-wrapper">
                  <div class="option-modal-check-wrapper">
                    <label class="login-check checkbox">
                      <input
                        v-model="roomPayOption"
                        type="checkbox"
                        value="monthly"
                        @change="valueSetting()"
                      />
                      <span>월세</span>
                    </label>
                    <label class="login-check checkbox">
                      <input
                        v-model="roomPayOption"
                        type="checkbox"
                        value="charter"
                        @change="valueSetting()"
                      />
                      <span>전세</span>
                    </label>
                    <label class="login-check checkbox">
                      <input
                        v-model="roomPayOption"
                        type="checkbox"
                        value="Trading"
                        @change="valueSetting()"
                      />
                      <span>매매</span>
                    </label>
                  </div>
                </div>
              </div>
            </div>
          </div>

          <div
            :class="{ 'search-option-active' : optionModalType.option3}"
            class="search-option-option search-option-option3"
          >
            <span @click="openEvent('option3')" class="search-option-option-wrapper">가격대</span>
            <i @click="openEvent('option3')" class="search-option-option-arrow-wrapper">
              <img v-if="!optionModalType.option3" src="../assets/down-arrow.png" />
              <img v-else src="../assets/up-arrow.png" />
            </i>
            <div>
              <div v-if="optionModalType.option3" class="option-modal-wrapper1">
                <div class="option-modal-type-wrapper">
                  <div class="option-modal-check-wrapper">
                    <div class="option-modal-type-pay-wrapper">
                      <label class="login-check checkbox">
                        <div class="option-modal-type-title option-modal-type-title-pay">
                          전세
                          <span class="option-modal-pay-text">({{ lease }}만원)</span>
                        </div>
                      </label>
                      <vue-slider :min="0" :max="100000" v-model="lease" @change="valueSetting()"></vue-slider>
                    </div>

                    <div class="option-modal-type-pay-wrapper">
                      <label class="login-check checkbox">
                        <div class="option-modal-type-title option-modal-type-title-pay">
                          보증금
                          <span
                            class="option-modal-pay-text"
                          >({{ deposit > 9999 ? '1억원' : deposit + '만원' }})</span>
                        </div>
                      </label>

                      <vue-slider :min="0" :max="10000" v-model="deposit" @change="valueSetting()"></vue-slider>
                    </div>
                    <div class="option-modal-type-pay-wrapper">
                      <label class="login-check checkbox">
                        <div class="option-modal-type-title option-modal-type-title-pay">
                          월세
                          <span class="option-modal-pay-text">({{ monthRent }}만원)</span>
                        </div>
                      </label>
                      <vue-slider :min="0" :max="1000" v-model="monthRent" @change="valueSetting()"></vue-slider>
                    </div>
                    <div>
                      <div class="option-modal-delete-option-wrapper">
                        <div class="option-modal-delete-option" @click="deleteOption">조건 삭제</div>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>

          <div
            :class="{ 'search-option-active' : optionModalType.option4}"
            class="search-option-option search-option-option4"
          >
            <span @click="openEvent('option4')" class="search-option-option-wrapper">방크기</span>
            <i @click="openEvent('option4')" class="search-option-option-arrow-wrapper">
              <img v-if="!optionModalType.option4" src="../assets/down-arrow.png" />
              <img v-else src="../assets/up-arrow.png" />
            </i>
            <div>
              <div v-if="optionModalType.option4" class="option-modal-wrapper2">
                <div class="option-modal-type-wrapper">
                  <div class="option-modal-check-wrapper">
                    <div class="option-modal-type-pay-wrapper">
                      <label class="login-check checkbox">
                        <div class="option-modal-type-title option-modal-type-title-pay">
                          방크기
                          <span class="option-modal-pay-text">({{ roomSpace }}평)</span>
                        </div>
                      </label>
                      <vue-slider :min="0" :max="300" v-model="roomSpace" @change="valueSetting()"></vue-slider>
                    </div>
                    <div class="option-modal-delete-option-wrapper">
                      <div class="option-modal-delete-option" @click="deleteRoomOption">조건 삭제</div>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>

          <!-- <div class="search-option-option search-option-option5">추가옵션</div> -->
        </div>
        <div class="search-option-wrapper2">
          <!-- <div>전체필터</div>
          <div>초기화</div>-->
        </div>
      </div>
      <div @click="closeEvent" class="search-result-container">
        <div class="search-room-list-container">
          <div class="search-room-list-all-count">전체방 {{roomListsCount}}개</div>
          <div class="search-room-list-wrapper-wrapper">
            <div class="search-room-list-wrapper">
              <!-- v-for로 묶을부분 -->

              <!-- 동적 라우팅 -->
              <router-link
                v-for="(roomList, index) in roomLists"
                :key="index"
                class="search-room-list"
                :id="roomList.id"
                :to="`search/detail/${roomList.id}`"
                style="text-decoration:none; outline: none; color:#222222;"
              >
                <div class="search-room-image-wrapper">
                  <img
                    v-if="roomList.room_picture_dto_list[0]"
                    class="search-room-image"
                    :src="roomList.room_picture_dto_list[0].file_name"
                    alt="이미지없음"
                  />
                  <img v-else class="search-room-image" src="../assets/room1.jpg" />
                  <div
                    v-if="loginData.auth === '3' && filterMyLikeImage(roomList.room_wish_list).length"
                  >
                    <img
                      @click.prevent.stop="like($event, roomList.id)"
                      class="heart-image"
                      :src="fillHeart"
                      alt
                    />
                  </div>
                  <div v-else-if="loginData.auth === '3'">
                    <img
                      @click.prevent.stop="like($event, roomList.id)"
                      class="heart-image"
                      :src="heart"
                      alt
                    />
                  </div>

                  <!-- 로그인 안했을때 -->
                  <div v-else-if="!loginData">
                    <img @click.prevent.stop="goToLogin()" class="heart-image" :src="heart" />
                  </div>
                  <!-- 일반 유저가 아닌 사람이 로그인했을때 하트 보여주지 않기 -->
                  <div v-else-if="loginData.auth !== '3'"></div>
                  <!--  -->

                  <!-- {{filterMyLikeImage(roomList.room_wish_list)}} -->
                  <!-- <img
            v-else
            class="heart-image"
            src="../assets/heart.png"
            @click="heartClick(recentData)"
            alt
                  />-->
                </div>
                <div class="search-room-list-check">
                  <!-- <div>플러스+</div> -->
                  <div class="search-room-list-certification">공인중개사 확인</div>
                </div>
                <!-- 타입 처리부분  -->
                <div v-if="roomList.room_type === 'apartment'" class="search-room-list-type">아파트</div>
                <div v-if="roomList.room_type === 'studio'" class="search-room-list-type">오피스텔</div>
                <div v-if="roomList.room_type === 'house'" class="search-room-list-type">단독주택</div>
                <div
                  v-if="roomList.room_type === 'multiunit'"
                  class="search-room-list-type"
                >빌라/연립/다세대</div>
                <div v-if="roomList.room_type === 'flatwithshop'" class="search-room-list-type">상가주택</div>

                <div
                  v-if="roomList.deposit"
                  class="search-room-pay-type"
                >월세 {{roomList.deposit}}/{{roomList.month_rent}}</div>
                <div v-else-if="roomList.lease" class="search-room-pay-type">전세 {{roomList.lease}}</div>
                <div class="search-room-detail">{{roomList.floor}}층. {{roomList.room_space}}평</div>
                <div class="search-room-content">{{roomList.content}}</div>
              </router-link>
            </div>
            <div>
              <el-pagination
                class="profile-payment-table-page qna-list-page"
                background
                layout="prev, pager, next"
                :page-size="10"
                :total="roomListsCount"
                :current-page.sync="currentPage"
                @current-change="refreshRoomList"
              ></el-pagination>
            </div>
          </div>
        </div>
        <div class="search-room-map-container">
          <div>
            <Map />
          </div>
        </div>
      </div>
    </div>
  </EtcContainer>
</template>
<script scoped>
import EtcContainer from "./EtcContainer";
import Map from "../components/map/Map";
import { request, requestParams } from "../util/axios";
import { error } from "../util/notification";
import { mapState, mapMutations } from "vuex";
export default {
  name: "app",
  components: {
    EtcContainer,
    Map
  },
  computed: {
    ...mapState(["loginData", "swLat", "swLng", "neLat", "neLng", "searchList"])

    //데이터 처리
    // roomType(element) {
    //   console.log(element);
    //   if (element === "apartment") {
    //     return "아파트";
    //   } else if (element === "studio") {
    //     return "오피스텔";
    //   } else if (element === "house") {
    //     return "단독주택";
    //   } else if (element === "multiunit") {
    //     return "빌라/연립/다세대";
    //   } else if (element === "flatwithshop") {
    //     return "상가주택";
    //   }
    // }
  },
  watch: {
    swLat: function() {
      this.getRoomList();
      //alert("값 변경 감지!");
    },
    roomTypeOption: function() {
      this.getRoomList();
    },
    roomPayOption: function() {
      this.getRoomList();
    },
    deposit: function() {
      this.getRoomList();
    },
    lease: function() {
      this.getRoomList();
    },
    monthRent: function() {
      this.getRoomList();
    },
    roomSpace: function() {
      this.getRoomList();
    }
  },
  data() {
    return {
      roomTypeOption: [],
      roomPayOption: ["monthly", "charter"],
      optionModalType: {
        option1: false,
        option2: false,
        option3: false,
        option4: false,
        option5: false,
        option6: false
      },
      deposit: 0, //보증금
      lease: 0, //전세
      monthRent: 0, //월세
      roomSpace: 0, //몇평
      roomLists: [],
      roomListsCount: 0,
      heart: require("../assets/heart.png"),
      fillHeart: require("../assets/fillHeart.png"),
      currentPage: 1,
      keyword2: ""
    };
  },
  mounted() {
    this.SET_ROOM_TYPE_OPTION(this.roomTypeOption);
    this.SET_ROOM_PAY_OPTION(this.roomPayOption);
    this.SET_ROOM_SPACE(this.roomSpace);
    this.SET_MONTH_RENT(this.monthRent);
    this.SET_LEASE(this.lease);
    this.SET_DEPOSIT(this.deposit);
    this.getRoomList();
    console.log("aa");
  },
  methods: {
    moveSearch(d_name, latitude, longitude) {
      //alert(d_name + " " + latitude + " " + longitude);
      this.closeEvent();
      this.SET_DONG_LATITUDE(latitude);
      this.SET_DONG_LONGITUDE(longitude);
    },
    axiosSearch() {
      //console.log(this.keyword);

      let params = new URLSearchParams();
      params.append("keyword", this.keyword2);

      request("post", "search/getRoomList", params)
        .then(res => {
          console.log(res);

          this.SET_SEARCH_LIST(res);

          console.log(this.searchList);
          //리스트를 data안에 집어넣기
          //this.roomLists = res;
        })
        .catch(error => console.log(error));
    },
    refreshRoomList() {
      //alert("test" + this.currentPage);
      let cpTemp = this.currentPage;
      const a = this;
      setTimeout(function() {
        if (cpTemp == a.currentPage) {
          let params = new URLSearchParams();

          params.append("swLat", a.swLat);
          params.append("swLng", a.swLng);
          params.append("neLat", a.neLat);
          params.append("neLng", a.neLng);

          params.append("roomTypeOption", JSON.stringify(a.roomTypeOption)); //방 종류
          params.append("roomPayOption", JSON.stringify(a.roomPayOption)); // 전세 매매 등
          params.append("deposit", a.deposit); // 보증금
          params.append("lease", a.lease); // 전세
          params.append("monthRent", a.monthRent); //월세
          params.append("roomSpace", a.roomSpace); //몇평
          params.append("currentPage", (a.currentPage - 1) * 10); //몇쪽

          request("post", "room/getRoomMapList", params)
            .then(res => {
              console.log(res);
              //리스트를 data안에 집어넣기
              a.roomLists = res;
            })
            .catch(error => console.log(error));
        }
      }, 750); //750ms
    },
    ...mapMutations([
      "SET_LOGIN_MODAL",
      "SET_ROOM_TYPE_OPTION",
      "SET_ROOM_PAY_OPTION",
      "SET_ROOM_SPACE",
      "SET_MONTH_RENT",
      "SET_LEASE",
      "SET_DEPOSIT",
      "SET_SEARCH_LIST",
      "SET_DONG_LATITUDE",
      "SET_DONG_LONGITUDE"
    ]),

    valueSetting() {
      //alert(this.roomTypeOption);
      this.SET_ROOM_TYPE_OPTION(this.roomTypeOption);
      this.SET_ROOM_PAY_OPTION(this.roomPayOption);
      this.SET_ROOM_SPACE(this.roomSpace);
      this.SET_MONTH_RENT(this.monthRent);
      this.SET_LEASE(this.lease);
      this.SET_DEPOSIT(this.deposit);
    },
    openEvent(element) {
      if (this.optionModalType[element] === true) {
        this.closeEvent();
      } else {
        this.closeEvent();
        this.optionModalType[element] = true;
      }
    },
    closeEvent() {
      this.optionModalType.option1 = false;
      this.optionModalType.option2 = false;
      this.optionModalType.option3 = false;
      this.optionModalType.option4 = false;
      this.optionModalType.option5 = false;
      this.optionModalType.option6 = false;
    },
    deleteOption() {
      this.deposit = this.lease = this.monthRent = 0;
      this.SET_MONTH_RENT(this.monthRent);
      this.SET_LEASE(this.lease);
      this.SET_DEPOSIT(this.deposit);
    },
    deleteRoomOption() {
      this.roomSpace = 0;
      this.SET_ROOM_SPACE(this.roomSpace);
    },
    getRoomList() {
      let swLatTemp = this.swLat;
      let swLngTemp = this.swLng;
      let neLatTemp = this.neLat;
      let neLngTemp = this.neLng;
      const a = this;
      setTimeout(function() {
        if (
          swLatTemp == a.swLat &&
          swLngTemp == a.swLng &&
          neLatTemp == a.neLat &&
          neLngTemp == a.neLng
        ) {
          let params = new URLSearchParams();
          console.log("test");

          params.append("swLat", a.swLat);
          params.append("swLng", a.swLng);
          params.append("neLat", a.neLat);
          params.append("neLng", a.neLng);

          params.append("roomTypeOption", JSON.stringify(a.roomTypeOption)); //방 종류
          params.append("roomPayOption", JSON.stringify(a.roomPayOption)); // 전세 매매 등
          params.append("deposit", a.deposit); // 보증금
          params.append("lease", a.lease); // 전세
          params.append("monthRent", a.monthRent); //월세
          params.append("roomSpace", a.roomSpace); //몇평

          request("post", "room/getRoomMapListCount", params)
            .then(res => {
              console.log(res);
              //리스트를 data안에 집어넣기
              a.roomListsCount = res;
            })
            .catch(error => console.log(error));

          a.currentPage = 1; //초기화
          params.append("currentPage", (a.currentPage - 1) * 10); //몇쪽

          request("post", "room/getRoomMapList", params)
            .then(res => {
              console.log(res);
              //리스트를 data안에 집어넣기
              a.roomLists = res;
            })
            .catch(error => console.log(error));
        }
      }, 750); //750ms
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
    // 로그인 유저와
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
.search-option-container {
  color: #222222;
  display: flex;
  align-items: center;
  height: 66px;
  /* position: fixed;
  top: 66px; */
  width: 100%;
  border: 1px solid #dddddddd;
  box-shadow: rgba(0, 0, 0, 0.2) 0px 3px 1px -2px;
}

.search-option-wrapper1 {
  display: flex;
  align-items: center;
  height: 100%;
}
.search-option-wrapper2 {
  margin-left: auto;
  display: flex;
}
.search-result-container {
  color: #222222;
  display: flex;
}

.search-room-list-container {
  flex: 6.8;
  padding-left: 15px;
}

.search-room-list-all-count {
  padding: 33px 0 20px;
  margin-left: 30px;
  font-size: 19px;
  border-bottom: 1px solid #dddddd;
}
.search-room-map-container {
  flex: 3.2;
}
.search-option-search-input-wrapper {
  width: 248px;
  height: 100%;
  position: relative;
}

.search-option-search-input-wrapper > input {
  width: 248px;
  height: 100%;
  border: none;
  border-right: 1px solid #dddddd;
  padding: 0 30px;
  font-size: 16px;
}

.search-option-option {
  margin-left: 15px;
  height: 36px;
  font-size: 14px;
  display: flex;
  align-items: center;
  border: 1px solid #dddddd;
  padding: 0 10px;
  border-radius: 5px;
}

.search-option-option:hover {
  /* background-color: #eeeeee; */
}

.search-option-option1 {
  width: 150px;
  position: relative;
}
.search-option-option2 {
  width: 130px;
  position: relative;
}
.search-option-option3 {
  width: 82px;
  position: relative;
}
.search-option-option4 {
  width: 82px;
  position: relative;
}
.search-option-option5 {
  width: 82px;
  position: relative;
}

.search-option-option-arrow-wrapper {
  margin-left: 8px;
  width: 8px;
}
.search-option-option-arrow-wrapper > img {
  width: 100%;
}

.search-option-option-wrapper {
  text-overflow: ellipsis;
  overflow: hidden;
  white-space: nowrap;
}
.search-option-active {
  background-color: #006cff;
  color: #fff;
  border: none;
}

.option-modal-wrapper {
  position: absolute;
  border: 1px solid #dddddd;
  top: 40px;
  left: -10px;
  width: 250px;
  height: 250px;
  background-color: #fff;
  z-index: 101;
  box-shadow: rgba(0, 0, 0, 0.25) 0px 15px 18px -10px;
  padding: 20px 30px 0;
  color: #222222;
}

.option-modal-wrapper1 {
  position: absolute;
  border: 1px solid #dddddd;
  top: 40px;
  left: -10px;
  width: 450px;
  height: 490px;
  background-color: #fff;
  z-index: 101;
  box-shadow: rgba(0, 0, 0, 0.25) 0px 15px 18px -10px;
  padding: 20px 30px 0;
  color: #222222;
}

.option-modal-wrapper2 {
  position: absolute;
  border: 1px solid #dddddd;
  top: 40px;
  left: -10px;
  width: 350px;
  height: 230px;
  background-color: #fff;
  z-index: 101;
  box-shadow: rgba(0, 0, 0, 0.25) 0px 15px 18px -10px;
  padding: 20px 30px 0;
  color: #222222;
}
.option-modal-type-title {
  font-size: 20px;
}

.option-modal-type-sub-title {
  margin-top: 5px;
  font-weight: 100;
  font-size: 13px;
  color: #aaaaaa;
}

.option-modal-check-wrapper {
  margin-top: 20px;
}
.search-room-list-wrapper-wrapper {
  overflow-x: hidden;
  overflow-y: auto;
  height: 80vh;

  /* z-index: 101; */
}
.search-room-list-wrapper {
  margin-left: 20px;
  padding-top: 20px;
  display: flex;
  flex-wrap: wrap;
}

.search-room-list {
  width: 240px;
  font-size: 14px;
  padding-left: 15px;
  margin-bottom: 50px;
  position: relative;
  cursor: pointer;
}
.search-room-image-wrapper {
  height: 145px;
}
.search-room-image {
  width: 100%;
  height: 100%;
  position: relative;
  z-index: 99;
}
.search-room-image:hover {
  opacity: 0.5;
}
.heart-image {
  position: relative;
  z-index: 100;
}

.search-room-list-check {
  margin-top: 10px;
  display: flex;
}

.search-room-list-type {
  margin-top: 6px;
}

.search-room-pay-type {
  margin-top: 5px;
  font-size: 20px;
  font-weight: bold;
}

.search-room-detail {
  color: #666666;
  font-size: 14px;
  margin-top: 5px;
  font-weight: 100;
}
.search-room-content {
  margin-top: 2px;
  color: #666666;
  font-size: 14px;
  width: 100%;

  overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap;
}

.search-room-list-certification {
  border: 1px solid #326cf9;
  color: #326cf9;
  font-weight: bold;
  font-size: 11px;
  padding: 3px;
}

.option-modal-type-pay-wrapper {
  padding-top: 20px;
  padding-bottom: 50px;
  border-bottom: 1px solid #dddddd;
}

.option-modal-type-title-pay {
  margin-bottom: 15px;
}

.option-modal-pay-text {
  color: #666666;
  font-size: 16px;
}

.option-modal-delete-option-wrapper {
  margin-top: 10px;
  text-align: right;
  display: flex;
  justify-content: flex-end;
}

.option-modal-delete-option {
  width: 80px;
  height: 30px;
  line-height: 30px;
  text-align: center;
  border: 1px solid #dddddd;
  font-size: 13px;
  cursor: pointer;
}
.option-modal-delete-option:hover {
  opacity: 0.5;
}
.option-modal-wrapper-input {
  position: absolute;
  border: 1px solid #dddddd;
  top: 70px;
  left: 3px;
  width: 250px;
  height: 250px;
  background-color: #fff;
  z-index: 101;
  box-shadow: rgba(0, 0, 0, 0.25) 0px 15px 18px -10px;
  padding: 20px 30px 0;
  color: #222222;
}

.router-item-search {
  font-size: 14px;
  margin-top: 3px;
}

.router-item-search {
  cursor: pointer;
  margin-top: 3px;
}
.router-item-search:hover {
  font-weight: bold;
}
</style>
