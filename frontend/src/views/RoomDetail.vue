<template>
  <EtcContainer class="container">
    <div slot="banner">
      <notifications group="notifyApp" position="right right" style="margin-right: 30vh;" />
      <div class="room-detail-container">
        <div class="room-detail-header">
          <div class="room-detail-header-container">
            <div class="room-detail-header-wrapper">
              <div class="room-detail-header-item">
                <!-- <div class="room-detail-header-type">원룸</div> -->
                <div v-if="roomDetail.room_type === 'apartment'" class="room-detail-header-type">아파트</div>
                <div v-if="roomDetail.room_type === 'studio'" class="room-detail-header-type">오피스텔</div>
                <div v-if="roomDetail.room_type === 'house'" class="room-detail-header-type">단독주택</div>
                <div
                  v-if="roomDetail.room_type === 'multiunit'"
                  class="room-detail-header-type"
                >빌라/연립/다세대</div>
                <div
                  v-if="roomDetail.room_type === 'flatwithshop'"
                  class="room-detail-header-type"
                >상가주택</div>
                <div v-if="roomDetail.deposit" class="room-detail-header-information">
                  월세 {{ roomDetail.deposit }}/{{ roomDetail.month_rent }}
                  <span
                    class="gray-font-8 money-type"
                  >만원</span>
                </div>
                <div v-else-if="roomDetail.lease" class="room-detail-header-information">
                  전세 {{ roomDetail.lease }}
                  <span class="gray-font-8 money-type">만원</span>
                </div>
              </div>
              <div class="room-detail-header-item">
                <div class="room-detail-header-type">전용면적</div>
                <div class="room-detail-header-information">{{ roomDetail.room_space }} 평</div>
              </div>
              <div class="room-detail-header-item">
                <div class="room-detail-header-type">한달 생활비</div>
                <div
                  class="room-detail-header-information blue-font"
                >{{ Number(roomDetail.month_rent) + 5 }}만 원 + α</div>
              </div>
            </div>
            <div class="room-detail-broker-info">
              <div class="room-detail-broker-name">{{ brokerData.name }}</div>
              <div class="room-detail-broker-information" @click="brokerModalShow = true">연락처 보기</div>
            </div>
          </div>
          <div class="room-detail-header-service">
            <!-- <div class="room-detail-icon-wrapper">
              <img src="../assets/fillHeart.png" />
              <span class="room-detail-icon-text">0</span>
            </div>
            <div class="distance-dot">•</div>-->
            <div class="room-detail-icon-wrapper" @click="copyClipBoard($event)">
              <img src="../assets/link.png" />
            </div>
            <div class="distance-dot">•</div>
            <!-- mypage/qna/list -->
            <router-link
              :to="`/mypage/qna/write`"
              class="room-detail-icon-wrapper"
              style="border:none; text-decoration:none; color:#222"
            >
              <img src="../assets/crisis.png" />
              <span class="room-detail-icon-text">허위매물신고</span>
            </router-link>
            <div
              v-if="brokerData.broker_id &&loginData.broker_id === brokerData.broker_id"
              class="distance-dot"
            >•</div>
            <router-link
              v-if="brokerData.broker_id && loginData.broker_id === brokerData.broker_id"
              :to="`/search/detail/update/${$route.params.roomId}`"
              class="room-detail-icon-wrapper"
              style="border:none; text-decoration:none; color:#222"
            >수정하기</router-link>

            <div
              v-if="brokerData.broker_id &&loginData.broker_id === brokerData.broker_id"
              class="distance-dot"
            >•</div>
            <div
              v-if="brokerData.broker_id &&loginData.broker_id === brokerData.broker_id"
              class="room-detail-icon-wrapper"
              @click="deleteRoom"
            >삭제하기</div>
          </div>
        </div>
        <div class="room-detail-content-container">
          <div class="room-detail-content-check-wrapper">
            <div class="room-detail-content-check-title">확인매물</div>
            <div class="room-detail-content-check-content">방주인과 공인중개사가 거래정보를 확인한 매물입니다</div>
          </div>
          <div class="room-detail-content-header">
            <div class="room-detail-content-header-wrapper">
              <div class="room-detail-content-header-title">해당층</div>
              <div class="room-detail-content-header-content">{{ roomDetail.floor }}층</div>
            </div>
            <div class="room-detail-content-header-wrapper">
              <div class="room-detail-content-header-title">전용/공급면적</div>
              <div class="room-detail-content-header-content">
                <!-- computed -->
                {{ roomSpaceToM }} / {{ supplySpaceToM }}㎡
              </div>
            </div>
            <div class="room-detail-content-header-wrapper">
              <div class="room-detail-content-header-title">전용/공급면적(평)</div>
              <div
                class="room-detail-content-header-content"
              >{{ roomDetail.room_space }}/{{ roomDetail.supply_space }}평</div>
            </div>
            <div class="room-detail-content-header-wrapper">
              <div class="room-detail-content-header-title">입주가능일</div>
              <div class="room-detail-content-header-content">
                <!-- {{ roomDetail.move_day }} -->
                <div style="width:150px; ">
                  <v-date-picker
                    style="pointer-events:none;"
                    v-model="moveDate"
                    :input-props="{
                      placeholder: '입주가능한 날짜를 선택하세요',
                      readonly: true,
                      hidePopover: true,
                    }"
                    :formatInput="false"
                  />
                </div>
              </div>
            </div>
          </div>
          <Modal v-if="brokerModalShow">
            <div slot="header">
              <div class="modal-broker-class-wrapper">
                <div class="modal-broker-header">연락처 보기</div>
                <div class="modal-broker-close-button" @click="brokerModalShow = false">X</div>
              </div>
            </div>
            <div slot="body">
              <div class="modal-broker-information-wrapper">
                <div class="modal-broker-profile-image-wrapper">
                  <img class="modal-broker-profile-image" :src="imageProfile" />
                </div>
                <div class="modal-broker-information-item-wrapper">
                  <div class="modal-broker-information-item">성함</div>
                  <div class="modal-broker-information-item1">{{ brokerData.name }}</div>
                </div>
                <div class="modal-broker-information-item-wrapper">
                  <div class="modal-broker-information-item">대표 번호</div>
                  <div class="modal-broker-information-item1">{{ brokerData.phone_number }}</div>
                </div>
              </div>
            </div>
            <div slot="footer">
              <div class="modal-broker-information-button-wrapper">
                <router-link
                  style="text-decoration:none;"
                  :to="`/broker/${brokerData.id}`"
                  class="modal-broker-information-button"
                >공인중개사 리뷰 확인하기</router-link>
              </div>
            </div>
          </Modal>
          <ModalImageView v-if="imageListModal" />
          <div class="room-detail-content-image-container" @click="SET_IMAGE_LIST_MODAL(true)">
            <div class="room-detail-content-image-wrapper1">
              <!-- <img src="../assets/room1.jpg" /> -->
              <img v-if="imagePrint(0)" :src="roomDetail.room_picture_dto_list[0].file_name" />
              <!-- {{ roomDetail.room_picture_dto_list[3].file_name }} -->
            </div>
            <div class="room-detail-content-image-wrapper2">
              <div class="room-detail-content-image-wrapper3">
                <img v-if="imagePrint(1)" :src="roomDetail.room_picture_dto_list[1].file_name" />
                <img v-if="imagePrint(2)" :src="roomDetail.room_picture_dto_list[2].file_name" />
              </div>
              <div class="room-detail-content-image-wrapper3">
                <img v-if="imagePrint(3)" :src="roomDetail.room_picture_dto_list[3].file_name" />
                <div v-else></div>
                <div class="room-detail-content-click-image">
                  <div class="room-detail-content-click-image-item1">+</div>
                  <div class="room-detail-content-click-image-item2">
                    {{
                    roomDetail.room_picture_dto_list
                    ? roomDetail.room_picture_dto_list.length
                    : ''
                    }}개 전체보기
                  </div>
                </div>
              </div>
            </div>
          </div>

          <div class="room-detail-content-text-container">
            <div class="room-detail-content-text-title">
              <!-- 방크기좋고~가성비굿 💟현관문따로~방문따로~이중문원룸 -->
              {{ roomDetail.title }}
            </div>
            <div class="room-detail-content-text-content">
              {{ roomDetail.content }}
              <!-- 단기시 보증금 10만원 실제로 가능하구요, 장기시 보증금
              100만원까지만 채워주시면 됩니다. 그리고 침대도 넣어드릴 수
              있습니다. 방 호수별로 금액 5만원 정도 차이는 있습니다~ 실제
              있는방이니 안심하시고 문의 주세요~^^-->
            </div>
          </div>

          <div class="room-detail-content-pay-information-container">
            <div class="room-detail-content-pay-information-title">가격정보</div>
            <div class="room-detail-content-pay-information-wrapper">
              <div class="room-detail-content-pay-information">
                <div class="room-detail-content-pay-title">월세</div>
                <div
                  class="room-detail-content-pay-content"
                >{{ roomDetail.deposit }}/{{ roomDetail.month_rent }} 만원</div>
              </div>
              <div class="room-detail-content-pay-information">
                <div class="room-detail-content-pay-title">전세</div>
                <div
                  class="room-detail-content-pay-content"
                >{{ roomDetail.lease ? roomDetail.lease + ' 만원' : '' }}</div>
              </div>
              <div class="room-detail-content-pay-information">
                <div class="room-detail-content-pay-title">관리비</div>
                <div class="room-detail-content-pay-content">5만원</div>
              </div>
            </div>

            <div class="room-detail-content-pay-life-wrapper">
              <div class="room-detail-content-pay-life-title">한달 생활비</div>
              <div class="room-detail-content-pay-life-content">
                {{ Number(roomDetail.month_rent) + 5 }}만원 + α
                <span
                  class="room-detail-content-pay-life-content-sub"
                >(월세+ 관리비)</span>
              </div>
            </div>
          </div>

          <div class="room-detail-content-option-container">
            <div class="room-detail-content-option-title">옵션</div>
            <div class="room-detail-content-option-item-wrapper">
              <div v-if="iconPrint('tv')" class="room-detail-content-option-item">
                <img src="../assets/television.png" />
                <div class="room-detail-content-option-item-name">TV</div>
              </div>
              <div v-if="iconPrint('airconditioner')" class="room-detail-content-option-item">
                <img src="../assets/air-conditioner.png" />
                <div class="room-detail-content-option-item-name">에어컨</div>
              </div>
              <div v-if="iconPrint('refrigerator')" class="room-detail-content-option-item">
                <img src="../assets/fridge.png" />
                <div class="room-detail-content-option-item-name">냉장고</div>
              </div>
              <div v-if="iconPrint('aircleaner')" class="room-detail-content-option-item">
                <img src="../assets/air-purifier.png" />
                <div class="room-detail-content-option-item-name">공기청정기</div>
              </div>
              <div v-if="iconPrint('bed')" class="room-detail-content-option-item">
                <img src="../assets/bed.png" />
                <div class="room-detail-content-option-item-name">침대</div>
              </div>
              <div v-if="iconPrint('microwave')" class="room-detail-content-option-item">
                <img src="../assets/microwave.png" />
                <div class="room-detail-content-option-item-name">전자렌지</div>
              </div>
              <div v-if="iconPrint('washer')" class="room-detail-content-option-item">
                <img src="../assets/washer.png" />
                <div class="room-detail-content-option-item-name">세탁기</div>
              </div>
            </div>
          </div>
          <div class="room-detail-content-location-container">
            <div class="room-detail-content-location-title">위치 및 주변 시설</div>
            <div class="room-detail-content-location-title-sub">{{ address_detail_zibun }}</div>
            <div class="room-detail-content-location-wrapper">
              <div id="kakao-map2" style="width:100%;height:400px;"></div>
            </div>
          </div>

          <div class="room-detail-content-other-room">
            <div class="room-detail-content-other-header">이 공인중개사의 다른 방</div>
            <KeepRoomContent v-if="roomLists" :roomLists="roomLists" />
          </div>
        </div>
      </div>
    </div>
  </EtcContainer>
</template>
<script scoped>
import KeepRoomContent from "../components/banner/KeepRoomContent";
import EtcContainer from "./EtcContainer";
import { request } from "../util/axios";
import { mapState, mapMutations } from "vuex";
import ModalImageView from "../components/ModalImageView";
import Modal from "../components/Modal";
import { success } from "../util/notification";
export default {
  name: "app",
  components: {
    EtcContainer,
    ModalImageView,
    Modal,
    KeepRoomContent
  },
  data() {
    return {
      roomDetail: [],
      moveDate: "",
      brokerData: "",
      imageProfile: "",
      brokerModalShow: false,
      roomLists: "",
      address_detail_zibun: "",
      latitude: 0.0,
      longitude: 0.0
    };
  },
  mounted() {
    console.log(this.$route.params.roomId); //넘겨 받은 아이디
    this.getRoomDetail();
    this.getRoomBrokerList();
    window.kakao && window.kakao.maps ? this.initMap() : this.addScript();
  },
  computed: {
    ...mapState(["imageListModal", "loginData"]),
    supplySpaceToM() {
      return Math.round(this.roomDetail.supply_space * 3.3058);
    },
    roomSpaceToM() {
      return Math.round(this.roomDetail.room_space * 3.3058);
    }
  },
  methods: {
    ...mapMutations(["SET_IMAGE_LIST", "SET_IMAGE_LIST_MODAL"]),
    //방 detail 출력
    getRoomDetail() {
      request("post", `room/getRoomDetail/${this.$route.params.roomId}`)
        .then(res => {
          console.log(res);

          this.latitude = res.latitude;
          this.longitude = res.longitude;
          this.address_detail_zibun = res.address_detail_zibun;

          this.roomDetail = res;
          //vuex에 이미지리스트 담기
          this.SET_IMAGE_LIST(res.room_picture_dto_list);
          this.moveDate = new Date(res.move_day);
          this.initMap();
          return res;
        })
        .then(res => {
          console.log(res.brokerid);
          let params = new URLSearchParams();
          params.append("id", res.brokerid);
          request("post", "broker/getInformation", params).then(resp => {
            console.log(resp);
            this.brokerData = resp;
            this.imageProfile = resp.profile_image;
          });
        });
    },
    //broker의 다른 방 리스트 출력
    getRoomBrokerList() {
      request(
        "post",
        `room/getRoomBrokerList/${this.$route.params.roomId}`
      ).then(res => {
        this.roomLists = res;
        console.log(res);
      });
    },
    imagePrint(number) {
      if (
        this.roomDetail &&
        this.roomDetail.room_picture_dto_list &&
        this.roomDetail.room_picture_dto_list.length > number
      ) {
        return true;
      } else {
        return false;
      }
    },
    iconPrint(element) {
      if (
        this.roomDetail.room_option_dto &&
        this.roomDetail.room_option_dto[element]
      ) {
        return true;
      } else {
        return false;
      }
    },
    copyClipBoard(e) {
      let t = this;
      this.$copyText(window.location.href).then(function(e) {
        success("URL이 클립보드에 복사되었습니다.", t);
      });
    },
    deleteRoom() {
      console.log("hello");
      let con = confirm("해당 게시글을 삭제하시겠습니까?");
      if (con) {
        let param = new URLSearchParams();
        param.append("id", this.$route.params.roomId);
        request("post", "room/deleteRoom", param).then(res => {
          this.$toasted.show(`글 삭제가 완료되었습니다`, {
            type: "error",
            position: "top-right",
            duration: 2500,
            singleton: true
          });
          this.$router.push("/");
        });
      }
    },
    initMap() {
      //alert("init")
      let container = document.getElementById("kakao-map2");
      let center = new kakao.maps.LatLng(this.latitude, this.longitude);
      //alert("위도 : " + this.latitude);
      //alert(this.longitude)
      let mapOption = {
        center: center, // 지도의 중심좌표
        level: 3, // 지도의 초기 레벨
        disableDoubleClickZoom: true
      };
      let map = new kakao.maps.Map(container, mapOption);
      map.relayout();

      map.setMinLevel(2); //확대 최소 레벨 2
      map.setMaxLevel(10); //확대 최대 레벨 10

      let marker = new kakao.maps.Marker({ position: center });
      marker.setMap(map);
    },
    addScript() {
      //alert("addScript");
      const script = document.createElement("script");
      script.onload = () => kakao.maps.load(this.initMap);
      script.src =
        "http://dapi.kakao.com/v2/maps/sdk.js?appkey=0fe1d5fd101ab6d2078168510cdb7237&libraries=services,clusterer,drawing&autoload=false";
      document.head.appendChild(script);
    }
  }
};
</script>
<style>
.room-detail-container {
  color: #222222;
}
.room-detail-header-container {
  padding-top: 35px;
  display: flex;
}

.room-detail-header-wrapper {
  display: flex;
}
.room-detail-header-item {
  padding-right: 25px;
  border-right: 1px solid #dddddd;
  min-width: 150px;
}
.room-detail-header-item:not(:first-child) {
  margin-left: 25px;
}

.room-detail-header-type {
  font-size: 14px;
  color: #666666;
}

.room-detail-broker-info {
  margin-left: auto;
}

.room-detail-header-information {
  margin-top: 6px;
  font-size: 30px;
}
.blue-font {
  color: #1476fc;
}
.money-type {
  margin-left: 3px;
}

.gray-font-8 {
  color: #666666;
  font-size: 15px;
}

.room-detail-broker-info {
  display: flex;
  align-items: center;
}
.room-detail-broker-name {
  font-size: 12px;
  color: #666666;
}

.room-detail-broker-information {
  font-size: 12px;
  width: 106px;
  height: 38px;
  border: 1px solid #dddddd;
  border-radius: 40px;
  display: flex;
  align-items: center;
  justify-content: center;
  margin-left: 15px;
  background-color: #1476fc;
  color: #fff;
  cursor: pointer;
}

.room-detail-broker-information:hover {
  opacity: 0.5;
}

.room-detail-header-service {
  margin-top: 35px;
  display: flex;
}
.distance-dot {
  display: flex;
  -webkit-box-align: center;
  align-items: center;
  height: 22px;
  color: rgb(34, 34, 34);
  font-size: 15px;
  line-height: 22px;
  position: relative;
  user-select: none;
  margin: 0 12px;
}
.room-detail-icon-wrapper {
  display: flex;
  align-items: center;
  font-size: 15px;
  cursor: pointer;
}

.room-detail-icon-wrapper:hover {
  opacity: 0.5;
}
.room-detail-icon-wrapper > img {
  height: 18px;
}
.room-detail-icon-text {
  margin-left: 10px;
  font-size: 14px;
}
.room-detail-content-container {
  margin-top: 15px;
}

.room-detail-content-check-wrapper {
  display: flex;
  color: #fff;
  height: 36px;
  background-color: #3665ce;
  font-size: 12px;
  align-items: center;
  border: 2px solid #0649c0;
}
.room-detail-content-check-title {
  height: inherit;
  width: 18vh;
  display: flex;
  align-items: center;
  justify-content: center;
  background-color: #0649c0;
}
.room-detail-content-check-content {
  width: 80vh;
  padding-left: 20px;
}
.room-detail-content-header {
  display: flex;
  font-size: 15px;
  height: 50px;
  border-bottom: 1px solid #dddddd;
}

.room-detail-content-header-wrapper {
  display: flex;
  flex: 1;
  align-items: center;
}
.room-detail-content-header-title {
  color: #888888;
  padding-left: 10px;
}

.room-detail-content-header-title:before {
  content: "·";
  color: rgb(34, 34, 34);
  margin-right: 7px;
}
.room-detail-content-header-content {
  padding-left: 20px;
  color: #222222;
}

.room-detail-content-image-container {
  margin-top: 40px;
  display: flex;
  height: 360px;
  cursor: pointer;
  border: 1px solid #dddddd;
}

.room-detail-content-image-wrapper1 {
  flex: 1;
  height: 100%;
}
.room-detail-content-image-wrapper1 > img {
  width: 550px;
  height: 360px;
}
.room-detail-content-image-wrapper1 > img:hover {
  opacity: 0.5;
}
.room-detail-content-image-wrapper2 {
  flex: 1;
  height: 360px;
}
.room-detail-content-image-wrapper3 {
  display: flex;
  height: 180px;
}
.room-detail-content-image-wrapper3 > * {
  flex: 1;
  width: 50%;
}

.room-detail-content-image-wrapper3 > *:hover {
  opacity: 0.5;
}

.room-detail-content-image-wrapper3 > img {
  width: 50%;
}

.room-detail-content-click-image {
  background-color: rgba(0, 0, 0, 0.75);
  color: #fff;
  display: flex;
  align-items: center;
  justify-content: center;
  flex-direction: column;
}

.room-detail-content-click-image-item1 {
  font-size: 60px;
  font-weight: 100;
}
.room-detail-content-click-image-item2 {
  font-size: 15px;
}

.room-detail-content-text-container {
  display: flex;
  margin-top: 30px;
}
.room-detail-content-text-container > div {
  flex: 1;
}
.room-detail-content-text-title {
  font-size: 26px;
  padding-right: 140px;
}
.room-detail-content-text-content {
  font-size: 16px;
  padding-right: 300px;
  white-space: pre-wrap;
}
.room-detail-content-pay-information-container {
  margin-top: 100px;
  padding-bottom: 100px;
  padding-left: 100px;
  padding-right: 100px;
}
.room-detail-content-pay-information-title {
  font-size: 28px;
  text-align: center;
}
.room-detail-content-pay-information-wrapper {
  margin-top: 40px;
  display: flex;
  border-top: 1px solid #dddddd;
  border-bottom: 1px solid #dddddd;
}
.room-detail-content-pay-information {
  flex: 1;
}
.room-detail-content-pay-information:not(:last-child) {
  border-right: 1px solid #dddddd;
}
.room-detail-content-pay-title,
.room-detail-content-pay-content {
  display: flex;
  align-items: center;
  justify-content: center;
  font-size: 15px;
}
.room-detail-content-pay-title {
  height: 50px;
}
.room-detail-content-pay-content {
  border-top: 1px solid #dddddd;
  height: 150px;
}
.room-detail-content-pay-life-wrapper {
  margin-top: 80px;
  display: flex;
  align-items: center;
  height: 112px;
  padding: 0 55px;
  border: 1px solid #1476fc;
  color: #1476fc;
}
.room-detail-content-pay-life-title {
  font-size: 20px;
  flex: 2;
}

.room-detail-content-pay-life-content {
  flex: 8;
  font-size: 30px;
  display: flex;
  align-items: center;
}

.room-detail-content-pay-life-content-sub {
  margin-left: 10px;
  color: #444444;
  font-size: 15px;
}
.room-detail-content-option-container {
  padding: 50px 100px 50px 100px;
  border-top: 1px solid #dddddddd;
}
.room-detail-content-option-title {
  font-size: 27px;
  text-align: center;
}

.room-detail-content-option-item-wrapper {
  margin-top: 40px;
  display: flex;
}
.room-detail-content-option-item {
  text-align: center;
  flex: 1;
}

.room-detail-content-option-item > img {
  width: 60px;
}
.room-detail-content-option-item-name {
  margin-top: 15px;
  text-align: center;
  font-size: 15px;
}

.room-detail-content-location-container {
  padding: 100px;
  border-top: 1px solid #dddddd;
}

.room-detail-content-location-title {
  text-align: center;
  font-size: 28px;
}
.room-detail-content-location-title-sub {
  margin-top: 20px;
  text-align: center;
  font-size: 15px;
}
.room-detail-content-location-wrapper {
  margin-top: 30px;
  height: 400px;
  border: 1px solid #dddddd;
}

.room-detail-content-other-room {
  border-top: 1px solid #dddddd;
  padding: 30px 0;
}
.modal-broker-class-wrapper {
  display: flex;
  align-items: center;
}

.modal-broker-close-button {
  margin-left: auto;
  border: 1px solid #dddddd;
  width: 30px;
  height: 30px;
  border-radius: 50%;
  display: flex;
  justify-content: center;
  align-items: center;
  font-size: 15px;
  font-weight: bold;
  cursor: pointer;
}
.modal-broker-close-button:hover {
  border: 1px solid #bbbbbbbb;
}

.modal-broker-header {
  font-size: 27px;
  font-weight: bold;
  text-align: center;
  margin-left: auto;
}

.modal-broker-profile-image-wrapper {
  display: flex;
  justify-content: center;
  margin-top: 20px;
  margin-bottom: 30px;
}

.modal-broker-profile-image {
  width: 80px;
  height: 80px;
  border-radius: 50%;
}
.modal-broker-information-wrapper {
  border-top: 1px solid #dddddd;
  margin-top: 20px;
}
.modal-broker-information-item-wrapper {
  display: flex;
  margin-top: 20px;
  align-items: center;
}

.modal-broker-information-item {
  font-size: 17px;
  width: 100px;
}
.modal-broker-information-item1 {
  font-size: 17px;
  margin-left: 10px;
  font-weight: bold;
}

.modal-broker-information-button-wrapper {
  height: 120px;
  display: flex;
  align-items: center;
  justify-content: center;
}

.modal-broker-information-button {
  cursor: pointer;
  background-color: #1476fc;
  color: #fff;
  padding: 20px 10px;
  font-size: 17px;
}

.modal-broker-information-button:hover {
  opacity: 0.5;
}

.room-detail-content-other-header {
  margin: 30px auto;
  text-align: center;
  font-size: 27px;
}
</style>
