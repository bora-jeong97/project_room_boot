<template>
  <div>
    <notifications group="notifyApp" position="right right" style="margin-right: 30vh;" />
    <div class="room-write-notification-wrapper">
      <ul class="room-write-wrapper">
        <li>등록한 매물은 30일 동안 노출됩니다.</li>
        <li>글을 작성할시 마다 글쓰기 횟수가 차감됩니다.</li>
        <li>고양이</li>
      </ul>
    </div>
    <div class="room-write-type-container">
      <div class="radio-wrap"></div>

      <div class="room-write-type-header">매물 종류</div>
      <div class="room-write-type-wrapper">
        <div class="room-write-type-title">종류 선택</div>
        <div class="room-write-type-selctor">
          <label class="room-write-type">
            <input type="radio" v-model="roomCount" value="one" />
            <p>원룸</p>
          </label>
          <label class="room-write-type">
            <input type="radio" v-model="roomCount" value="two" />
            <p>투룸</p>
          </label>
          <label class="room-write-type">
            <input type="radio" v-model="roomCount" value="three" />
            <p>쓰리룸</p>
          </label>
          <label class="room-write-type">
            <input type="radio" v-model="roomCount" value="four" />
            <p>포룸</p>
          </label>
          <label class="room-write-type">
            <input type="radio" v-model="roomCount" value="more" />
            <p>그 이상</p>
          </label>
        </div>
      </div>
      <div class="room-write-type-wrapper">
        <div class="room-write-type-title">건물 유형</div>
        <div class="room-write-type-selctor">
          <label class="room-write-type">
            <input type="radio" v-model="roomType" value="apartment" />
            <p>아파트</p>
          </label>
          <label class="room-write-type">
            <input type="radio" v-model="roomType" value="studio" />
            <p>오피스텔</p>
          </label>
          <label class="room-write-type">
            <input type="radio" v-model="roomType" value="house" />
            <p>단독주택</p>
          </label>
          <label class="room-write-type">
            <input type="radio" v-model="roomType" value="multiunit" />
            <p>빌라/연립/다세대</p>
          </label>
          <label class="room-write-type">
            <input type="radio" v-model="roomType" value="flatwithshop" />
            <p>상가주택</p>
          </label>
        </div>
      </div>
    </div>
    <div class="room-location-container">
      <div class="room-location-title-wrapper">
        <div></div>
        <div class="room-location-title">위치 정보</div>
        <div class="room-location-sub-title">*등기부등본 상의 주소를 입력해주세요</div>
      </div>
      <div class="room-location-content-container">
        <div class="room-location-content-title">주소</div>
        <div class="room-location-content-wrapper">
          <div class="room-location-content">
            <div class="room-location-content-notification">도로명 건물명 지번에 대해 통합검색이 가능합니다.</div>
            <div class="room-location-input-wrapper">
              <input
                class="room-location-input"
                v-model="locationSearch"
                type="text"
                placeholder="예)번동 10-1,강북구 번동"
              />
              <div @click="sample5_execDaumPostcode()" class="room-location-search-button">주소검색</div>
            </div>
            <div class="room-location-search-result" id="sample5_address">
              <div>
                <span v-if="sample5_address" class="room-location-search-type">도로명:</span>
                {{ sample5_address }}
              </div>

              <div>
                <span v-if="sample5_address_zibun" class="room-location-search-type">지번:</span>
                {{ sample5_address_zibun }}
              </div>
            </div>

            <div class="room-location-result-detail-wrapper">
              <diV v-if="!checkDong" class="room-location-result-detail">
                <input
                  v-model="dong"
                  class="room-location-result-detail-input"
                  placeholder="예)101동"
                />
                <div class="room-location-result-detail-sub">동</div>
              </diV>
              <diV class="room-location-result-detail">
                <input v-model="ho" class="room-location-result-detail-input" placeholder="예)201호" />
                <div class="room-location-result-detail-sub">호</div>
              </diV>
            </div>
            <label class="room-location-dong-check-wrapper room-checkbox checkbox">
              <input v-model="checkDong" type="checkbox" />
              <span>등본에 동정보가 없는경우 선택해주세요</span>
            </label>
          </div>
        </div>
        <div class="room-location-map-wrapper">
          <div class="room-location-map" id="map" ref="map">
            <div class="room-location-img-wrapper">
              <img src="../../assets/gps.png" />
            </div>
            <div>주소 검색을 하시면</div>
            <div>해당 위치가 지도에 표시됩니다.</div>
          </div>
        </div>
      </div>
    </div>

    <div class="room-deal-information-container">
      <div class="room-deal-information-title">거래 정보</div>
      <div class="room-deal-information-content-wrapper">
        <div class="room-deal-informtaion-content-title">거래종류</div>
        <div class="room-deal-information-content">
          <div class="room-deal-information">
            <div class="room-deal-information-wrapper">
              <div v-if="showMonth" class="room-deal-information-option">
                <div class="room-deal-information-item-type">월세</div>
                <div class="room-deal-information-item-wrapper">
                  <input v-model="deposit" placeholder="보증금" />
                  <span class="room-deal-inforamtion-divide">/</span>
                  <input v-model="monthRent" placeholder="월세" />
                  <span class="room-deal-inforamtion-won">만원</span>
                  <span
                    v-if="deposit || monthRent"
                    class="room-deal-information-example"
                  >(예 월세{{ deposit }}만원/{{ monthRent }}만원)</span>
                  <span v-else class="room-deal-information-example">(예 월세 1000만원/50만원 )</span>
                  <div class="room-deal-close-button-wrapper">
                    <div class="room-deal-close-button" @click="showMonth = false">X</div>
                  </div>
                </div>
              </div>
              <div v-if="showLease" class="room-deal-information-option">
                <div class="room-deal-information-item-type">전세</div>
                <div class="room-deal-information-item-wrapper">
                  <input v-model="lease" placeholder="전세" />
                  <span class="room-deal-inforamtion-won">만원</span>
                  <span v-if="lease" class="room-deal-information-example">(예 전세 {{ lease }}만원)</span>
                  <span v-else class="room-deal-information-example">(예 전세 2000만원)</span>
                  <div class="room-deal-close-button-wrapper">
                    <div class="room-deal-close-button" @click="showLease = false">X</div>
                  </div>
                </div>
              </div>
            </div>
          </div>

          <div>
            <div class="room-deal-option-selector">
              <div
                class="room-deal-option-item"
                @click="clickMonth"
                :class="{ 'room-deal-cliked': showMonth }"
              >월세</div>
              <div
                class="room-deal-option-item room-deal-option-item-deposit"
                @click="clickLease"
                :class="{ 'room-deal-cliked': showLease }"
              >전세</div>
              <div class="room-deal-option-notice">
                처음에 선택한 거래 종류가 우선노출됩니다. 예치금이 있는 경우
                보증금 입력란에 필히 입력하세요.
              </div>
            </div>
            <div></div>
          </div>
        </div>
      </div>
    </div>

    <div class="room-deal-information-container">
      <div class="room-deal-information-title">기본 정보</div>
      <div class="room-deal-information-content-wrapper">
        <div class="building-information-wrapper">
          <div class="bulding-information-scale">
            <div class="bulding-information-title">
              <div>건물크기</div>
              <div>(1P = 3.3058㎡)</div>
            </div>
            <div class="bulding-information-option-wrapper">
              <div class="bulding-information-input-wrapper">
                <span>공급면적</span>
                <input v-model="supplySpace" @input="p" />
                <span>평</span>
                <input v-model="supplyM2" @input="m" />
                <span>㎡</span>
              </div>
              <div class="bulding-information-option1">
                <div class="bulding-information-input-wrapper">
                  <span>전용면적</span>
                  <input v-model="roomSpace" @input="p2" />
                  <span>평</span>
                  <input v-model="squareMeter" @input="m2" />
                  <span>㎡</span>
                </div>
              </div>
            </div>
          </div>
          <div class="bulding-information-scale">
            <div class="bulding-information-title">건물층수</div>
            <div class="bulding-information-option-wrapper">
              <div class="bulding-inforamtion-floor-wrapper">
                <div class="bulding-information-floor">
                  <div class="bulding-information-floor-title">해당 층수</div>
                  <select v-model="floor" class="bulding-information-floor-select">
                    <option value="-2" disabled>건물 층수 선택</option>
                    <option value="-1">반지하</option>
                    <option value="0">옥탑</option>
                    <option
                      v-for="(floor, index) in 50"
                      :key="index"
                      :value="index + 1"
                    >{{ index + 1 }}층</option>
                  </select>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
      <div class="bulding-information-calendar-wrapper">
        <div class="bulding-information-calendar-title">입주가능일</div>
        <div class="bulding-information-calendar-selector">
          <div>
            <!-- <el-date-picker v-model="moveDay" type="date" placeholder="날짜를 선택해 주세요"></el-date-picker> -->
            <v-date-picker
              v-model="date"
              :input-props="{
                placeholder: '입주가능한 날짜를 선택하세요',
                readonly: true,
              }"
            />
          </div>
        </div>
      </div>
    </div>
    <!-- tv 에어컨 냉장고 공기청정기침대 전자레인지 세탁기 -->
    <div class="room-deal-information-container">
      <div class="room-deal-information-title">추가 정보</div>
      <div class="room-deal-information-content-wrapper">
        <div class="room-deal-informtaion-content-title">옵션항목</div>

        <div class="room-deal-information-content">
          <div class="room-deal-information">
            <div class="room-deal-option-wrapper">
              <label class="room-option-type">
                <input type="checkbox" v-model="roomOption" value="tv" />
                <p>TV</p>
              </label>
              <label class="room-option-type">
                <input type="checkbox" v-model="roomOption" value="airconditioner" />
                <p>에어컨</p>
              </label>
              <label class="room-option-type">
                <input type="checkbox" v-model="roomOption" value="refrigerator" />
                <p>냉장고</p>
              </label>
              <label class="room-option-type">
                <input type="checkbox" v-model="roomOption" value="aircleaner" />
                <p>공기청정기</p>
              </label>
              <label class="room-option-type">
                <input type="checkbox" v-model="roomOption" value="bed" />
                <p>침대</p>
              </label>
              <label class="room-option-type">
                <input type="checkbox" v-model="roomOption" value="microwave" />
                <p>전자레인지</p>
              </label>
              <label class="room-option-type">
                <input type="checkbox" v-model="roomOption" value="washer" />
                <p>세탁기</p>
              </label>
            </div>
          </div>
          <div>
            <div></div>
          </div>
        </div>
      </div>
    </div>

    <div class="room-deal-information-container">
      <div class="room-deal-information-title">상세 설명</div>
      <div class="room-deal-information-content-wrapper room-write-title-container">
        <div class="room-deal-informtaion-content-title">제목</div>

        <div class="room-deal-information-content">
          <div class="room-deal-information room-write-title-input-wrapper">
            <input v-model="title" placeholder="예)신논현역 도보 5분거리, 혼자살기 좋은 방입니다." />
          </div>
        </div>
      </div>
      <div class="room-deal-information-content-wrapper room-write-content-container">
        <div class="room-deal-informtaion-content-title">상세설명</div>

        <div class="room-deal-information-content">
          <div class="room-deal-information room-write-title-input-wrapper">
            <textarea
              name="memo"
              v-model="content"
              placeholder="상세설명 작성 주의사항

- 방 정보와 관련없는 홍보성 정보는 입력하실 수 없습니다. (홈페이지 주소, 블로그, SNS, 메신저ID, 전화번호, 이메일 등)
- 중개수수료를 언급한 내용은 입력할 수 없습니다. (중개수수료 무료, 공짜, 반값 등)

* 주의사항 위반시 허위매물로 간주되어 매물 삭제 및 이용의 제한이 있을 수 있습니다.
* 구해방의 매물등록 규정에 위반되는 금칙어는 등록이 불가합니다."
              class="Textarea-sc-6jro6l-0 styled__Memo-sc-1tnvlzf-2 cHRaGb"
            ></textarea>
          </div>
        </div>
      </div>
    </div>

    <div class="room-deal-information-container">
      <div class="room-deal-information-title">사진 등록</div>
      <div class="room-picture-notice">
        <ul class="room-write-wrapper">
          <li>사진은 가로로 찍은 사진을 권장합니다. (가로 사이즈 최소 800px)</li>
          <li>사진 용량은 사진 한 장당 10MB 까지 등록이 가능합니다.</li>
          <li>
            사진은 최소 3장 이상 등록해야하며, 최대 15장 까지 권장합니다. 그
            이상 등록할 경우 업로드 시간이 다소 지연될 수 있습니다.
          </li>
        </ul>
      </div>
      <div class="room-file-upload-wrapper">
        <div v-if="!files.length" class="room-file-upload-example-container">
          <div class="room-file-upload-example">
            <div class="room-file-image-example-wrapper">(글 수정시 이미지는 새로 등록해주세요!)</div>
            <div class="room-file-notice-item">실사진 최소 3장 이상 등록하셔야 하며, 가로사진을 권장합니다.</div>
            <div class="room-file-notice-item room-file-notice-item-red">
              구해방 로고를 제외한 불필요한 정보(워터마크,상호,전화번호 등)가
              있는 매물은 비공개처리됩니다
            </div>
            <div class="room-file-notice-item room-file-upload-button">
              <div class="image-box">
                <!-- <div class="image-profile">
                <img :src="profileImage" />
                </div>-->
                <label for="file">일반 사진 등록</label>
                <input type="file" id="file" ref="files" @change="imageUpload" multiple />
              </div>
            </div>
          </div>
        </div>
        <div v-else class="file-preview-content-container">
          <div class="file-preview-container">
            <div v-for="(file, index) in files" :key="index" class="file-preview-wrapper">
              <div class="file-close-button" @click="fileDeleteButton" :name="file.number">x</div>
              <img :src="file.preview" />
            </div>
            <div class="file-preview-wrapper-upload">
              <div class="image-box">
                <label for="file">추가 사진 등록</label>
                <input type="file" id="file" ref="files" @change="imageAddUpload" multiple />
              </div>
              <!-- <div class="file-close-button" @click="fileDeleteButton" :name="file.number">x</div> -->
            </div>
          </div>
        </div>
      </div>
    </div>
    <div class="room-write-button-wrapper">
      <div class="room-write-button-cancel" @click="moveMain">취소</div>
      <div class="room-write-button" @click="roomWrite">매물등록</div>
    </div>
  </div>
</template>

<script>
import { request, requestFile } from "../../util/axios";
import { error } from "../../util/notification";
import { mapState } from "vuex";
// import { kakaoMap } from "../../util/kakaoMap";

export default {
  data() {
    return {
      roomCount: "one",
      roomType: "apartment",
      checkDong: false,
      sample5_address: "",
      address: "",
      dong: "",
      ho: "",
      map: "",
      locationSearch: "",
      sample5_address_zibun: "",
      lease: "",
      deposit: "",
      monthRent: "",
      showMonth: false,
      showLease: false,
      floor: -2, //floor -2는 미선택,
      roomSpace: "",
      squareMeter: "",
      supplySpace: "",
      supplyM2: "",
      moveDay: "", //이사가능한 날짜
      roomOption: [], //옵션
      title: "",
      content: "", //내용
      files: [], //업로드용 파일
      filesPreview: [],
      date: new Date(),
      uploadImageIndex: 0 // 이미지 업로드를 위한 변수
    };
  },
  watch: {
    roomSpace() {}
  },
  mounted() {
    this.getRoomDetail();
    console.log(this.loginData);
    const script1 = document.createElement("script");
    script1.src =
      "https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js";
    document.head.appendChild(script1);
    // if (window.kakao && window.kakao.maps) {
    //   // this.initMap();
    // } else {
    const script = document.createElement("script");
    /* global kakao */
    script.onload = () => kakao.maps.load();
    script.src =
      "http://dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=0fe1d5fd101ab6d2078168510cdb7237&libraries=services";
    document.head.appendChild(script);
    // }
  },
  computed: {
    ...mapState(["loginData"]),
    createObject() {
      return URL.createObjectURL(file);
    }
  },
  methods: {
    getRoomDetail() {
      request("post", `room/getRoomDetail/${this.$route.params.roomId}`).then(
        res => {
          console.log(res);
          this.roomDetail = res;
          this.roomCount = res.room_count;
          this.room_type = res.room_type;
          this.sample5_address = res.address_detail.split("/")[0];
          this.sample5_address_zibun = res.address_detail_zibun.split("/")[0];
          this.dong = res.address_detail.split("/")[1];
          this.ho = res.address_detail.split("/")[2];
          this.showMonth = true;
          this.deposit = res.deposit;
          this.monthRent = res.month_rent;
          this.showLease = true;
          this.lease = res.lease;
          this.supplySpace = res.supply_space;
          this.roomSpace = res.room_space;
          this.floor = res.floor;
          this.moveDay = res.move_day;
          // console.log(aa);
          //옵션정하기
          let option = [
            res.room_option_dto.aircleaner,
            res.room_option_dto.airconditioner,
            res.room_option_dto.bed,
            res.room_option_dto.microwave,
            res.room_option_dto.refrigerator,
            res.room_option_dto.tv,
            res.room_option_dto.washer
          ];
          console.log(option);
          this.roomOption = option;
          this.title = res.title;
          this.content = res.content;
          let aa = res.room_picture_dto_list.map(li => li.file_name);
          console.log(aa);
          // this.dong =
          //vuex에 이미지리스트 담기
          // this.SET_IMAGE_LIST(res.room_picture_dto_list);
          this.moveDate = new Date(res.move_day);
          return res;
        }
      );
    },

    sample5_execDaumPostcode() {
      // console.log(this);
      let vueData = this; //vue의 this 값 바인딩 new daum.Postcode에서는 this를 사용시 자체 함수를 가리킨다
      let mapContainer = this.$refs.map;
      // console.log(this.$route);
      let searchKeyword = this.locationSearch;

      new daum.Postcode({
        oncomplete: function(data) {
          // let mapContainer = document.getElementById("map"); // 지도를 표시할 div
          // let mapContainer = this.$refs.map; // 지도를 표시할 div
          let mapOption = {
            center: new daum.maps.LatLng(37.537187, 127.005476), // 지도의 중심좌표
            level: 4 // 지도의 확대 레벨
          };
          let map = new daum.maps.Map(mapContainer, mapOption);
          //주소-좌표 변환 객체를 생성
          let geocoder = new daum.maps.services.Geocoder();
          //마커를 미리 생성
          let marker = new daum.maps.Marker({
            position: new daum.maps.LatLng(37.537187, 127.005476),
            map: map
          });
          let addr = data.address; // 최종 주소 변수
          //
          console.log("a");
          console.log(data.jibunAddress);
          console.log(data.bname);
          // 주소 정보를 해당 필드에 넣는다.
          // document.getElementById("sample5_address").value = addr;
          // document.getElementById("sample5_address").innerHTML = addr;
          vueData.sample5_address = addr;
          vueData.sample5_address_zibun = data.jibunAddress;
          // d.address = addr;
          // 주소로 상세 정보를 검색
          geocoder.addressSearch(data.address, function(results, status) {
            // 정상적으로 검색이 완료됐으면
            if (status === daum.maps.services.Status.OK) {
              let result = results[0]; //첫번째 결과의 값을 활용

              // 해당 주소에 대한 좌표를 받아서
              let coords = new daum.maps.LatLng(result.y, result.x);
              // 지도를 보여준다.
              mapContainer.style.display = "block";
              map.relayout();
              map.setLevel(4);
              // 지도 중심을 변경한다.
              map.setCenter(coords);

              // 마커를 결과값으로 받은 위치로 옮긴다.
              marker.setPosition(coords);
            }
          });
        }
      }).open({
        //검색어 넘기기
        popupName: "구해방", //이름 설정시 여러개의 팝업 방지
        q: this.locationSearch
      });
    },
    clickMonth() {
      if (this.showMonth) {
        this.showMonth = false;
      } else {
        this.showMonth = true;
      }
    },
    clickLease() {
      if (this.showLease) {
        this.showLease = false;
      } else {
        this.showLease = true;
      }
    },
    p() {
      if (this.supplySpace !== "") {
        this.supplyM2 = Math.round(this.supplySpace * 3.3058);
      }
    },
    //전용면적
    m() {
      if (this.supplyM2 !== "") {
        this.supplySpace = Math.round(this.supplyM2 / 3.3058);
      }
      //    return nn / 3.3058;
    },
    p2() {
      if (this.roomSpace !== "") {
        this.squareMeter = Math.round(this.roomSpace * 3.3058);
      }
    },
    //공급면적
    m2() {
      if (this.squareMeter !== "") {
        this.roomSpace = Math.round(this.squareMeter / 3.3058);
      }
    },
    imageUpload() {
      console.log(this.$refs.files.files);

      // this.files = [...this.files, this.$refs.files.files];
      //하나의 배열로 넣기
      let num = -1;
      for (let i = 0; i < this.$refs.files.files.length; i++) {
        this.files = [
          ...this.files,
          //이미지 업로드
          {
            //실제 파일
            file: this.$refs.files.files[i],
            //이미지 프리뷰
            preview: URL.createObjectURL(this.$refs.files.files[i]),
            //삭제및 관리를 위한 number
            number: i
          }
        ];
        num = i;
        //이미지 업로드용 프리뷰
        // this.filesPreview = [
        //   ...this.filesPreview,
        //   { file: URL.createObjectURL(this.$refs.files.files[i]), number: i }
        // ];
      }
      this.uploadImageIndex = num + 1; //이미지 index의 마지막 값 + 1 저장
      console.log(this.files);
      // console.log(this.filesPreview);
    },

    imageAddUpload() {
      console.log(this.$refs.files.files);

      // this.files = [...this.files, this.$refs.files.files];
      //하나의 배열로 넣기c
      let num = -1;
      for (let i = 0; i < this.$refs.files.files.length; i++) {
        console.log(this.uploadImageIndex);
        this.files = [
          ...this.files,
          //이미지 업로드
          {
            //실제 파일
            file: this.$refs.files.files[i],
            //이미지 프리뷰
            preview: URL.createObjectURL(this.$refs.files.files[i]),
            //삭제및 관리를 위한 number
            number: i + this.uploadImageIndex
          }
        ];
        num = i;
      }
      this.uploadImageIndex = this.uploadImageIndex + num + 1;

      console.log(this.files);
      // console.log(this.filesPreview);
    },
    fileDeleteButton(e) {
      const name = e.target.getAttribute("name");
      this.files = this.files.filter(data => data.number !== Number(name));
      // console.log(this.files);
    },
    moveMain() {
      this.$router.push("/");
    },
    roomWrite() {
      if (!this.sample5_address) {
        error("주소를 입력해주세요", this);
        return;
      } else if ((!this.monthRent && !this.deposit) || !this.lease) {
        error("보증금 + 월세 혹은 전세를 입력하세요 ", this);
        return;
      } else if (!this.roomSpace) {
        error("전용면적을 입력하세요", this);
        return;
      } else if (!this.supplySpace) {
        error("공급면적을 입력하세요", this);
        return;
      } else if (this.files.length < 3) {
        error("최소 3장의 사진을 업로드 하세요", this);
        return;
      }
      console.log(this.roomCount); //방의갯수
      console.log(this.roomType);

      let params = new URLSearchParams();

      params.append("title", this.title);
      params.append("content", this.content);
      params.append("roomType", this.roomType);
      params.append("lease", this.lease); //전세
      params.append("roomCount", this.roomCount);
      params.append(
        "addressDetail",
        this.sample5_address + " " + this.dong + "동 " + this.ho + "호 "
      );
      params.append(
        "addressDetailZibun",
        this.sample5_address_zibun + " " + this.dong + " " + this.ho
      );
      params.append("deposit", this.deposit); //보증금
      params.append("monthRent", this.monthRent); //월세
      params.append("supplySpace", this.supplySpace);
      params.append("roomSpace", this.roomSpace);
      params.append("floor", this.floor);
      params.append("moveDay", this.date);
      params.append("BROKERId", this.loginData.id);
      params.append("roomId", this.$route.params.roomId);
      params.append("id", this.$route.params.roomId);
      //roomOption
      for (let i = 0; i < this.roomOption.length; i++) {
        params.append(this.roomOption[i], this.roomOption[i]);
      }
      // params.append('tv', this.roomOption[0]);
      // params.append('airconditioner', this.roomOption[1]);
      // params.append('refrigerator', this.roomOption[2]);
      // params.append('aircleaner', this.roomOption[3]);
      // params.append('bed', this.roomOption[4]);
      // params.append('microwave', this.roomOption[5]);
      // params.append('washer', this.roomOption[6]);
      // 글쓰기 방지처리

      // 글 내용 업데이트 + 사진 내역 다 지우기 + 사진 새로 업로드 하기
      // 업데이트 백엔드 생성하기
      // 사진 내역 지우기및 새로 업로드하는 백엔드 생성하고
      request("post", "room/updateRoom", params)
        //성공시 파일업로드 실행
        .then(res => {
          //res에는 roomid가 담겨있다
          console.log(res);
          for (let i = 0; i < this.files.length; i++) {
            let params = new FormData();
            params.append("roomId", res);
            params.append("file", this.files[i].file);
            console.log(res);
            requestFile("post", "room/upload", params)
              .then(response => {
                if (response !== "FAIL") {
                } else {
                  error("글 작성에 실패했습니다", this);
                }
              })
              .catch(error => {
                console.log(error);
              });
          }
          this.$toasted.show(`글 수정이 완료되었습니다`, {
            type: "success",
            position: "top-right",
            duration: 2500,
            singleton: true
          });
          if (this.$route.path !== "/") {
            this.$router.push("/");
          }
        });
      // .then((res) => {
      //   console.log(res);
      //   if (res !== 'FAIL') {
      //     this.$toasted.show(`글 작성에 성공했습니다`, {
      //       type: 'success',
      //       position: 'top-right',
      //       duration: 2500,
      //     });
      //     this.$router.push('/');
      //   } else {
      //     error('글 작성에 실패했습니다', this);
      //   }
      // });
    }
  }
};
</script>

<style>
* {
  box-sizing: border-box;
}
.room-write-notification-wrapper {
  margin-top: 30px;
  border: 1px solid #dddddd;
  padding: 20px 40px;
  box-shadow: rgba(188, 196, 219, 0.77) 5px 5px 13px 0px;
}
.room-write-wrapper > li {
  color: #222222;
  list-style: none;
  font-size: 15px;
  position: relative;
  font-weight: 100;
}
.room-write-wrapper > li:not(:first-child) {
  margin-top: 8px;
}

.room-write-wrapper > li::before {
  content: "";
  width: 3px;
  height: 3px;
  background-color: rgb(34, 34, 34);
  position: absolute;
  top: 50%;
  left: -10px;
  transform: translateY(-50%);
  border-radius: 50%;
}
.room-write-type-container {
  border: 1px solid #bbbbbb;
  box-shadow: rgba(188, 196, 219, 0.77) 5px 5px 13px 0px;
  color: #222222;
  margin-top: 50px;
}

.room-write-information-wrapper {
  margin-top: 20px;
}

.room-write-type-header {
  text-align: center;
  border-bottom: 1px solid #cccccc;
  box-shadow: rgba(0, 0, 0, 0.05) 0px 1px 5px 0px;
  height: 60px;
  line-height: 60px;
  font-size: 18px;
}

.room-write-type-wrapper {
  height: 70px;
  display: flex;
  align-items: center;
}
.room-write-type-wrapper:not(:last-child) {
  border-bottom: 0.8px solid #dddddd;
  /* box-shadow: rgba(0, 0, 0, 0.05) 0px 1px 5px 0px; */
}
.room-write-type-title {
  width: 150px;
  height: 100%;
  font-size: 15px;
  line-height: 70px;
  text-align: center;
  background-color: rgb(249, 249, 249);
  font-weight: 400;
}

.room-write-type-selctor {
  display: flex;
  padding-left: 20px;
}
.room-write-type {
  text-align: center;
  min-width: 82px;
  height: 46px;
  font-size: 15px;
  line-height: 46px;
  /* border: 0.5px solid #dddddd; */
  border-radius: 5px;
  cursor: pointer;
}
.room-write-type:not(:first-child) {
  margin-left: 10px;
}

.room-write-type > input[type="radio"] {
  position: relative;
  user-select: none;
}

.room-write-type > input[type="radio"],
input[type="radio"]:checked {
  position: absolute;
  appearance: none;
  width: 0.9rem;
  height: 0.9rem;
  border-radius: 100%;
  margin-right: 0.1rem;
}
.room-write-type > p {
  min-width: 82px;
  height: 100%;
  border-radius: 5px;
  padding: 0 15px;
  border: 1px solid #dddddd;
}

.room-write-type > input[type="radio"]:checked + p {
  color: white;
  background-color: #1a5ae8;
  border: 1px solid #1a5ae8;
}

.room-location-container {
  color: #222222;
  margin-top: 30px;
  border: 1px solid #dddddd;
  box-shadow: rgba(188, 196, 219, 0.77) 5px 5px 13px 0px;
}

.room-location-title-wrapper {
  display: flex;
  justify-content: space-between;
  align-items: center;
  height: 60px;
  border-bottom: 1px solid #dddddd;
}
.room-location-title-wrapper > div {
  flex: 1;
}
.room-location-title {
  text-align: center;
  font-size: 18px;
}
.room-location-sub-title {
  text-align: right;
  font-size: 14px;
  color: #888888;
  transform: translateX(-7%);
}

.room-location-content-container {
  display: flex;
  height: 370px;
  box-shadow: rgba(188, 196, 219, 0.77) 5px 5px 13px 0px;
}
.room-location-content-title {
  font-size: 15px;
  width: 150px;
  text-align: center;
  line-height: 370px;
  background-color: #f9f9f9;
}
.room-location-content-wrapper {
  width: 560px;
  padding: 20px;
  display: flex;
  align-items: center;
}

.room-location-content-notification {
  font-size: 14px;
  color: #888888;
}
.room-location-input-wrapper {
  margin-top: 15px;
  display: flex;
  height: 46px;
}
.room-location-input {
  height: 100%;
  width: 430px;
  border: none;
  border: 1px solid #cccccc;
  border-radius: 4px;
  padding: 0 15px;
}
.room-location-search-button {
  height: 100%;
  cursor: pointer;
  margin-left: 10px;
  background-color: #232d4a;
  color: white;
  min-width: 90px;
  font-size: 14px;
  text-align: center;
  line-height: 46px;
  border-radius: 4px;
}
.room-location-search-type {
  display: inline-block;
  width: 60px;
}

.room-location-search-result {
  display: flex;
  justify-content: center;
  flex-direction: column;
  margin-top: 12px;
  width: 528px;
  height: 100px;
  border: 1px solid #cccccc;
  padding: 20px 15px;
  font-size: 15px;
  color: #666666;
}
.room-location-search-result > div {
  margin-top: 5px;
}

.room-location-result-detail-wrapper {
  margin-top: 10px;
  height: 46px;
  display: flex;
  align-items: center;
}

.room-location-result-detail {
  display: flex;
  height: 46px;
  align-items: center;
}

.room-location-result-detail:not(:first-child) {
  margin-left: 8px;
}
.room-location-result-detail-input {
  border: none;
  border: 1px solid #cccccc;
  width: 212px;
  height: 100%;
  padding: 0 15px;
}
.room-location-result-detail-sub {
  color: #888888;
  font-size: 14px;
  width: 48px;
  height: 100%;
  line-height: 46px;
  text-align: center;
  border: 1px solid #cccccc;
  border-left: none;
}

.room-location-dong-check-wrapper {
  padding-top: 30px;
  display: flex;
  align-items: center;
  font-size: 14px;
}

.checkbox {
  display: inline-flex;
  cursor: pointer;
  position: relative;
}

.checkbox > span {
  /* color: #34495e; */
  padding: 0.5rem 0.25rem;
  margin-left: 5px;
}

.checkbox > input {
  height: 20px;
  width: 20px;
  -webkit-appearance: none;
  -moz-appearance: none;
  -o-appearance: none;
  appearance: none;
  border: 1px solid #dddddd;
  border-radius: 4px;
  outline: none;
  transition-duration: 0.3s;
  background-color: #fff;
  cursor: pointer;
}

.checkbox > input[type="checkbox"]:checked {
  border: 1px solid #1564f9;
  background-color: #1564f9;
}

.checkbox > input[type="checkbox"]:checked + span::before {
  content: "\2713";
  font-size: 15px;
  display: block;
  text-align: center;
  color: #fff;
  position: absolute;
  left: 0.35rem;
  top: 0.4rem;
}
.login-check {
  display: flex;
  align-items: center;
}
.checkbox > input:active {
  border: 2px solid #34495e;
}
.room-location-map-wrapper {
  padding: 10px 10px 10px 0;
  /* width: 440px; */

  height: 100%;
}
#map {
  width: 380px;
  height: 100%;
}

.room-location-map {
  border: 1px solid #dddddd;
  width: 440px;
  height: 100%;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  color: #888888;
  font-size: 15px;
}
.room-location-map > div {
  margin-top: 5px;
}

.room-location-img-wrapper {
  width: 50px;
  height: 50px;
  border: 1px solid #dddddd;
  border-radius: 50%;
  display: flex;
  justify-content: center;
  align-items: center;
}

.room-location-img-wrapper > img {
  width: 50%;
  height: 50%;
}

.room-deal-information-container {
  margin-top: 50px;
  color: #222222;
  border: 1px solid #dddddd;
  box-shadow: rgba(188, 196, 219, 0.77) 5px 5px 13px 0px;
}
.room-deal-information-title {
  text-align: center;
  font-size: 18px;
  line-height: 60px;
  border-bottom: 1px solid #dddddd;
}

.room-deal-information-content-wrapper {
  min-height: 50px;
  display: flex;
}

.room-deal-informtaion-content-title {
  font-size: 15px;
  display: flex;
  align-items: center;
  justify-content: center;
  width: 150px;
  background-color: #f9f9f9;
}

.room-deal-information-content {
  width: 100%;

  font-size: 14px;
}

.room-deal-option-selector {
  display: flex;
  align-items: center;

  padding: 15px;
}

.room-deal-option-item {
  width: 100px;
  height: 40px;
  display: flex;
  align-items: center;
  justify-content: center;
  border: 1px solid #cccccc;
  border-radius: 5px;
  cursor: pointer;
}

.room-deal-option-item:last-child {
  margin-left: 10px;
}

.room-deal-option-notice {
  margin-left: auto;
  font-size: 14px;
  color: #888888;
}

.room-deal-option-item-deposit {
  margin-left: 10px;
}

.room-deal-information-wrapper {
  display: flex;

  flex-direction: column;
}

.room-deal-information-option {
  padding: 10px;
  display: flex;
  align-items: center;
}

.room-deal-information-option:last-child {
  border-bottom: 1px solid #dddddd;
}

.room-deal-information-item-type {
  font-size: 13px;

  color: #fff;
  background-color: #61b6e5;
  min-width: 50px;
  height: 26px;

  display: flex;
  justify-content: center;
  align-items: center;
  border-radius: 3px;
}
.room-deal-information-item-wrapper {
  display: flex;
  align-items: center;
  margin-left: 10px;
  height: 46px;
  width: 100%;
}

.room-deal-information-item-wrapper > input {
  border: 1px solid #dddddd;
  width: 140px;
  height: 100%;
  padding: 0 15px;
  font-size: 15px;
}
.room-deal-inforamtion-won {
  margin: 0 10px;
}
.room-deal-information-example {
  color: #888888;
}

.room-deal-information-option:not(:first-child) {
  margin-top: 10px;
}

.room-deal-inforamtion-divide {
  font-size: 22px;
  margin: 0 8px;
  color: #222222;
  font-weight: 100;
}
.room-deal-close-button-wrapper {
  margin-left: auto;
  cursor: pointer;
}

.room-deal-close-button {
  display: flex;
  align-items: center;
  justify-content: center;
  width: 22px;
  height: 22px;
  background-color: #666666;
  color: rgb(220, 220, 220);
}
.room-deal-cliked {
  background-color: rgb(235, 235, 235);
  color: rgb(170, 170, 170);
}

.building-information-wrapper {
  display: flex;
  width: 100%;
}

.bulding-information-scale {
  display: flex;
  flex: 1;
  border-bottom: 1px solid #dddddd;
  height: 150px;
}

.bulding-information-title {
  width: 150px;
  font-size: 15px;
  color: #222222;
  background-color: #f9f9f9f9;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
}

.bulding-information-calendar-selector {
  margin-left: 20px;
  display: flex;
  align-items: center;
  font-size: 14px;
}
.choose-date-picker {
  margin-right: 20px;
}
.bulding-information-calendar-wrapper {
  display: flex;
}

.bulding-information-calendar-title {
  width: 150px;
  height: 70px;
  background-color: #f9f9f9f9;
  font-size: 14px;
  color: #222222;
  display: flex;
  justify-content: center;
  align-items: center;
}

.bulding-information-option-wrapper {
  width: 430px;
  display: flex;
  flex-direction: column;
  font-size: 15px;
}
.bulding-information-option-wrapper > div {
  display: flex;
  align-items: center;
  padding: 0 20px;
  flex: 1;
}

.bulding-information-option1 {
  border-top: 1px solid #dddddd;
}
.bulding-inforamtion-floor-wrapper {
  display: flex;
  align-items: center;
  padding: 20px;
}
.bulding-information-floor-title {
  font-size: 15px;
}
.bulding-information-floor {
  display: flex;
  align-items: center;
}

.bulding-information-floor-select {
  margin-left: 10px;
  width: 168px;
  height: 46px;
}

select {
  width: 200px;
  padding: 0.8em 0.5em;
  border: 1px solid #999;
  font-family: inherit;
  background: url("../../assets/arrow.jpeg") no-repeat 95% 50%;
  background-size: 25px;
  border-radius: 0px;
  -webkit-appearance: none;
  -moz-appearance: none;
  appearance: none;
}

select::-ms-expand {
  display: none;
}
.bulding-information-input-wrapper {
  height: 46px;
  font-size: 15px;
}
.bulding-information-input-wrapper > input {
  width: 100px;
  height: 46px;
  margin: 0 7px;
  padding: 0 15px;
  font-size: 15px;
}

.room-option-type {
  text-align: center;
  min-width: 82px;
  height: 46px;
  font-size: 15px;
  line-height: 46px;
  /* border: 0.5px solid #dddddd; */
  border-radius: 5px;
  cursor: pointer;
}
.room-option-type:not(:first-child) {
  margin-left: 20px;
}

.room-option-type > input[type="checkbox"] {
  position: relative;
  user-select: none;
}

.room-option-type > input[type="checkbox"] {
  position: absolute;
  appearance: none;
  width: 0.9rem;
  height: 0.9rem;
  border-radius: 100%;
  margin-right: 0.1rem;
}
.room-option-type > input[type="checkbox"]:checked {
  position: absolute;
  appearance: none;
  width: 0.9rem;
  height: 0.9rem;
  border-radius: 100%;
  margin-right: 0.1rem;
}
.room-option-type > p {
  min-width: 82px;
  height: 100%;
  border-radius: 5px;
  padding: 0 15px;
  border: 1px solid #dddddd;
}

.room-option-type > input[type="checkbox"]:checked + p {
  color: white;
  background-color: #1a5ae8;
  border: 1px solid #1a5ae8;
}

.room-deal-option-wrapper {
  padding: 20px;
  display: flex;
}
.room-write-title-container {
  height: 70px;
}

.room-write-title-input-wrapper {
  height: 100%;
  padding: 12px 20px;
}
.room-write-title-input-wrapper > input {
  border: 1px solid #dddddd;
  width: 100%;
  height: 100%;
  padding: 0 15px;
  font-size: 15px;
  color: rgb(68, 68, 68);
}

.room-write-title-input-wrapper > textarea {
  border: 1px solid #dddddd;
  width: 100%;
  height: 100%;
  padding: 20px;
  color: rgb(68, 68, 68);
  font-size: 17px;
}
.room-file-upload-example {
  height: 100%;
}
.room-write-content-container {
  border-top: 1px solid #dddddd;
  min-height: 260px;
}

.room-picture-notice {
  margin: 20px;
  padding: 20px 40px;
  border: 1px solid #dddddd;
}
.file-preview-content-container {
  height: 100%;
}

.room-file-upload-wrapper {
  margin: 20px;
  border: 1px solid #dddddd;
  background-color: #f4f4f4;
  min-height: 350px;
  font-size: 15px;
  color: #888888;
  display: flex;
  align-items: center;
  justify-content: center;
  height: 100%;
}

.room-file-upload-example-container {
  display: flex;
  align-items: center;
  justify-content: center;
  height: 100%;
  /* height: 100%;
  width: 100%; */
}

.room-file-image-example-wrapper {
  text-align: center;
}

.room-file-notice-item {
  margin-top: 5px;
  text-align: center;
}
.room-file-notice-item-red {
  color: #ef4351;
}

.image-box {
  margin-top: 30px;
  padding-bottom: 20px;
  text-align: center;
}

.image-box input[type="file"] {
  position: absolute;
  width: 0;
  height: 0;
  padding: 0;
  overflow: hidden;
  border: 0;
}

.image-box label {
  display: inline-block;
  padding: 10px 20px;
  background-color: #232d4a;
  color: #fff;
  vertical-align: middle;
  font-size: 15px;
  cursor: pointer;
  border-radius: 5px;
}

.file-preview-wrapper {
  padding: 10px;
  position: relative;
}

.file-preview-wrapper > img {
  position: relative;
  width: 190px;
  height: 130px;
  z-index: 10;
}

.file-close-button {
  position: absolute;

  /* align-items: center; */
  line-height: 18px;
  z-index: 99;
  font-size: 18px;
  right: 5px;
  top: 10px;
  color: #fff;
  font-weight: bold;
  background-color: #666666;
  width: 20px;
  height: 20px;
  text-align: center;
  cursor: pointer;
}

.file-preview-container {
  height: 100%;
  display: flex;
  flex-wrap: wrap;
}

.file-preview-wrapper-upload {
  margin: 10px;
  padding-top: 20px;
  background-color: #888888;
  width: 190px;
  height: 130px;
}

.room-write-button-wrapper {
  margin-top: 20px;
  display: flex;
  justify-content: center;
  align-items: center;
  color: #222222;
}
.room-write-button-wrapper > div {
  width: 160px;
  height: 50px;
  border: 1px solid #dddddd;
  display: flex;
  justify-content: center;
  align-items: center;
  font-size: 15px;
  cursor: pointer;
}

.room-write-button {
  margin-left: 15px;
  color: #fff;
  background-color: #1564f9;
}

.room-write-button:hover {
  opacity: 0.8;
}
</style>
