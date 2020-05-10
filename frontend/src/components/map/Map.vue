<template>
  <div>
    <div id="kakao-map" style="width:100%;height:827px;"></div>
  </div>
</template>


<script>
import { request, requestParams } from "../../util/axios";
import { mapState, mapMutations } from "vuex";
let jsonDongPath = [];
let latlng = "";
// 지도의 현재 레벨을 얻어옵니다.
let level = "";
// 지도 영역정보를 얻어옵니다
let bounds = "";
// 영역정보의 남서쪽 정보를 얻어옵니다
let swLatlng = "";
// 영역정보의 북동쪽 정보를 얻어옵니다
let neLatlng = "";
let RoomOptionTemp = 0; //변화 감지용 임시 변수.
let mapVar =  function() {};
let centerSetting = function() {};



let roomTypeOption;
let roomPayOption;
let deposit;
let lease;
let monthRent;
let roomSpace;

export default {
  name: "kakao-map",
  mounted() {
    window.kakao && window.kakao.maps ? this.initMap() : this.addScript();
  },
  data() {
    return {};
  },
  computed: {
    ...mapState(["dongLatitude", "dongLongitude" , "roomTypeOption" , "roomPayOption" , "deposit" , "lease" , "monthRent" , "roomSpace"])

    /*
        roomTypeOption: [ ],
        roomPayOption: [ ],
        deposit: 0, //보증금
        lease: 0, //전세
        monthRent: 0, //월세
        roomSpace: 0, //몇평

        computed:{ check_snackbar(){return this.$store.getters.get_snackbar} }, watch:{ check_snackbar(val){ this.snackbar = val } },
    */
  },
  created: function() {
    console.log(this.dongLatitude + " / " + this.dongLongitude);  //검색 단어가 넘어온거 세팅.
  },
  methods: {
    ...mapMutations(["SET_SW_LAT" , "SET_SW_LNG" , "SET_NE_LAT", "SET_NE_LNG" ]),

    say() {
      alert("test");
    },
    initMap() {
      roomTypeOption = this.roomTypeOption;
      roomPayOption = this.roomPayOption;
      deposit = this.deposit;
      lease = this.lease;
      monthRent = this.monthRent;
      roomSpace = this.roomSpace;

      //alert("init");
      let container = document.getElementById("kakao-map");
      //let options = { center: new kakao.maps.LatLng(33.450701, 126.570667), level: 3 };
      let center = new kakao.maps.LatLng(37.566826, 126.9786567);
      let mapOption = {
        center: center, // 지도의 중심좌표
        level: 8, // 지도의 초기 레벨
        disableDoubleClickZoom: true
      };
      let map = new kakao.maps.Map(container, mapOption);

      let customOverlay = []; //new kakao.maps.CustomOverlay({}),
      let areas = [];
      map.setMinLevel(2); //확대 최소 레벨 2
      map.setMaxLevel(10); //확대 최대 레벨 10

      // 마커 클러스터러를 생성합니다
      let clusterer = new kakao.maps.MarkerClusterer({
        map: map, // 마커들을 클러스터로 관리하고 표시할 지도 객체
        averageCenter: true, // 클러스터에 포함된 마커들의 평균 위치를 클러스터 마커 위치로 설정
        minLevel: 2, // 클러스터 할 최소 지도 레벨
        gridSize: 35,
        disableClickZoom: true,
        minClusterSize: 1
      });
      //변수값 초기 세팅.
      // 지도 중심좌표를 얻어옵니다
      latlng = map.getCenter();
      // 지도의 현재 레벨을 얻어옵니다.
      level = map.getLevel();
      // 지도 영역정보를 얻어옵니다
      bounds = map.getBounds();
      // 영역정보의 남서쪽 정보를 얻어옵니다
      swLatlng = bounds.getSouthWest();
      // 영역정보의 북동쪽 정보를 얻어옵니다
      neLatlng = bounds.getNorthEast();

      this.SET_SW_LAT(swLatlng.getLat());
      this.SET_SW_LNG(swLatlng.getLng());
      this.SET_NE_LAT(neLatlng.getLat());
      this.SET_NE_LNG(neLatlng.getLng());

      //json 데이터 로드
      request("post", "search/getJson")
        .then(res => {
          if (res != null) {
            console.log(res);
            jsonDongPath = res;
          } else {
            alert("json is null!");
          }
        })
        .catch(error => console.log(error));

      //지도 확대 축소 감지
      kakao.maps.event.addListener(map, "zoom_changed", function() {
        mapVar();
      });

      //지도 이동 감지
      kakao.maps.event.addListener(map, "dragend", function() {
        mapVar();
      });

      if((this.dongLatitude != 0.0) && (this.dongLongitude != 0.0)) {
        //alert("데이터 있음!");
        map.setCenter(new kakao.maps.LatLng(this.dongLatitude, this.dongLongitude));
        map.setLevel(6, {
                          animate: {
                            duration: 500
                          }
                        });
      }else { 
        map.setLevel(7, {
                          animate: {
                            duration: 500
                          }
                        });
      }
      const a = this;
      function reSetting() {
        //값 재 설정
        latlng = map.getCenter();
        level = map.getLevel();
        bounds = map.getBounds();
        swLatlng = bounds.getSouthWest();
        neLatlng = bounds.getNorthEast();

        a.SET_SW_LAT(swLatlng.getLat());
        a.SET_SW_LNG(swLatlng.getLng());
        a.SET_NE_LAT(neLatlng.getLat());
        a.SET_NE_LNG(neLatlng.getLng());

      }

      function dongPrint(value) {
        //마우스 올릴경우 동에서 다각형 표시.
        //value에는 동 값이 넘어옴.
        //let temp = document.getElementById(value);
        console.log("동프린트로 넘어옴!");

        console.log(jsonDongPath[value]);
        console.log(jsonDongPath[value].type);

        let linePath = [];
        areas.forEach(function(area) {
          area.setMap(null);
        }); //오버레이 초기화.
        areas.length = 0; // 오버레이 배열 초기화

        if (jsonDongPath[value].type == "single") {
          for (let joPath in jsonDongPath[value].path) {
            //joPath : 0~ path의 최대 갯수
            linePath.push(
              new kakao.maps.LatLng(
                jsonDongPath[value].path[joPath][1],
                jsonDongPath[value].path[joPath][0]
              )
            );
          }

          let polygon = new kakao.maps.Polygon({
            map: map, // 다각형을 표시할 지도 객체
            path: linePath,
            strokeWeight: 2,
            strokeColor: "#004c80",
            strokeOpacity: 0.8,
            fillColor: "#fff",
            fillOpacity: 0.7
          });
          areas.push(polygon);
        } else if (jsonDongPath[value].type == "multi") {
          for (let joPath in jsonDongPath[value].path) {
            //joPath : 0~ path의 최대 갯수
            for (let joPath2 in jsonDongPath[value].path[joPath]) {
              linePath.push(
                new kakao.maps.LatLng(
                  jsonDongPath[value].path[joPath][joPath2][1],
                  jsonDongPath[value].path[joPath][joPath2][0]
                )
              );
            }
            let polygon = new kakao.maps.Polygon({
              map: map, // 다각형을 표시할 지도 객체
              path: linePath,
              strokeWeight: 2,
              strokeColor: "#004c80",
              strokeOpacity: 0.8,
              fillColor: "#fff",
              fillOpacity: 0.7
            });
            areas.push(polygon);
          }
        } else {
          console("다각형 에러. 이쪽으로 넘어오면 안됨.");
        }
        areas.forEach(function(area) {
          area.setMap(map);
        });
      }
      function dongPrintCancel(value) {
        //value에는 동 값이 넘어옴.
        //let temp = document.getElementById(value);
        areas.forEach(function(area) {
          area.setMap(null);
        }); //오버레이 초기화.
        areas.length = 0; // 오버레이 배열 초기화
      }

      centerSetting = function (Latitude, Longitude) {
        //alert("센터 세팅 호출");
        let moveLatLon = new kakao.maps.LatLng(Latitude, Longitude);
        map.setCenter(moveLatLon);
      }

      mapVar = function() { 
        
        reSetting(); //변수 재세팅
        let mapLevelTemp = level; //임시 변수 선언.
        let mapSwTemp = swLatlng;
        let mapNeTemp = neLatlng;
        let RoomOptionTempTemp = RoomOptionTemp;

        //console.log("jsonData Check");
        //console.log(jsonDongPath);

        setTimeout(function() {
          if (
            level == mapLevelTemp &&
            swLatlng == mapSwTemp &&
            neLatlng == mapNeTemp &&
            RoomOptionTemp == RoomOptionTempTemp
          ) {
            console.log("맵 변환 감지!!!");
            //0.75초동안 변화가 없는가?
            //alert("level : " + level + " / temp : " + mapLevelTemp);

            customOverlay.forEach(function(overlay) {
              overlay.setMap(null);
            }); //오버레이 초기화.
            customOverlay.length = 0; // 오버레이 배열 초기화
            
            /*
            areas.forEach(function(area) {
              area.setMap(null);
            });
            areas.length = 0;
            */

            if (level >= 9) {
              //시단위
              let params = new URLSearchParams();

              params.append("swLat", swLatlng.getLat());
              params.append("swLng", swLatlng.getLng());
              params.append("neLat", neLatlng.getLat());
              params.append("neLng", neLatlng.getLng());

              request("post", "search/getSi", params)
                .then(response => {
                  console.log(response);
                  for (let i = 0; i < response.length; i++) {
                    if (response[i].count == 0) {
                      continue;
                    }
                    //alert(response[i].name)
                    let position = new kakao.maps.LatLng(
                      response[i].latitude,
                      response[i].longitude
                    );
                    let content =
                      '<div class="search-item-wrapper">' +
                      '<div class="search-item">' +
                      //response[i].count +
                      "+" + 
                      "</div>" +
                      '<div class="search-address">' +
                      response[i].name +
                      "</div>" +
                      "</div>";
                    let customOverlayTemp = new kakao.maps.CustomOverlay({
                      position: position,
                      content: content
                    });

                    customOverlay.push(customOverlayTemp);
                  }
                  customOverlay.forEach(function(overlay) {
                    overlay.setMap(map);
                  });
                })
                .catch(error => console.log(error));
            } else if (level >= 7) {
              //구단위
              let params = new URLSearchParams();

              params.append("swLat", swLatlng.getLat());
              params.append("swLng", swLatlng.getLng());
              params.append("neLat", neLatlng.getLat());
              params.append("neLng", neLatlng.getLng());

              params.append("roomTypeOption", JSON.stringify(roomTypeOption));  //방 종류
              params.append("roomPayOption", JSON.stringify(roomPayOption));  // 전세 매매 등
              params.append("deposit", deposit);  // 보증금
              params.append("lease", lease);  // 전세
              params.append("monthRent", monthRent);  //월세
              params.append("roomSpace", roomSpace);  //몇평

              request("post", "search/getGu", params)
                .then(response => {
                  console.log(response);
                  for (let i = 0; i < response.length; i++) {
                    if (response[i].count == 0) {
                      continue;
                    }
                    //alert(response[i].name)
                    let position = new kakao.maps.LatLng(
                      response[i].latitude,
                      response[i].longitude
                    );
                    let content =
                      '<div class="search-item-wrapper">' +
                      '<div class="search-item">' +
                      response[i].count +
                      "</div>" +
                      '<div class="search-address">' +
                      response[i].name +
                      "</div>" +
                      "</div>";
                    let customOverlayTemp = new kakao.maps.CustomOverlay({
                      position: position,
                      content: content,
                      zIndex: 3
                    });

                    customOverlay.push(customOverlayTemp);
                  }
                  customOverlay.forEach(function(overlay) {
                    overlay.setMap(map);
                  });
                })
                .catch(error => console.log(error));
            } else if (level >= 4) {
              //동단위
              let params = new URLSearchParams();

              params.append("swLat", swLatlng.getLat());
              params.append("swLng", swLatlng.getLng());
              params.append("neLat", neLatlng.getLat());
              params.append("neLng", neLatlng.getLng());

              console.log(roomTypeOption);

              params.append("roomTypeOption", JSON.stringify(roomTypeOption));  //방 종류
              params.append("roomPayOption", JSON.stringify(roomPayOption));  // 전세 매매 등
              params.append("deposit", deposit);  // 보증금
              params.append("lease", lease);  // 전세
              params.append("monthRent", monthRent);  //월세
              params.append("roomSpace", roomSpace);  //몇평

              request("post", "search/getDong", params)
                .then(response => {
                  console.log(response);
                  for (let i = 0; i < response.length; i++) {
                    if (response[i].count == 0) {
                      continue;
                    }
                    //alert(response[i].name)
                    let position = new kakao.maps.LatLng(
                      response[i].latitude,
                      response[i].longitude
                    );
                    // onmouseover=dongPrint(${response[i].name}) onmouseout=dongPrintCancel(${response[i].name})
                    let content = `<div class="search-item-wrapper"
                    id=${response[i].name}>
                    <div class="search-item">
                      ${response[i].count}
                      </div>
                      <div class="search-address">
                      ${response[i].name}
                      </div>
                      </div>`;
                    let customOverlayTemp = new kakao.maps.CustomOverlay({
                      position: position,
                      content: content
                    });
                    // console.log(`"${response[i].name}"`);
                    //customOverlayTemp.setMap(map);
                    customOverlay.push(customOverlayTemp);


                    // customOverlay.push(customOverlayTemp);

                    // const searchItemWrapper = document.querySelectorAll(
                    //   ".search-item-wrapper"
                    // );
                    // searchItemWrapper.map(item =>
                    //   item.addEventListener("click", e => {
                    //     console.log(e);
                    //     console.log("aa");
                    //   })
                    // );
                  }
                  customOverlay.forEach(function(overlay) {
                    overlay.setMap(map);
                  });
                  for (let i = 0; i < response.length; i++) {
                    if (response[i].count == 0) {
                      continue;
                    }
                    const searchItemWrapper = document.getElementById(
                      response[i].name
                    );
                    searchItemWrapper.addEventListener("mouseover", function() {
                      console.log("온다요!!");
                      dongPrint(response[i].name);
                    });
                    searchItemWrapper.addEventListener("mouseout", function() {
                      console.log("간다다요!!");
                      dongPrintCancel(response[i].name);
                    });
                    console.log(searchItemWrapper);
                  }
                })
                .catch(error => console.log(error));
            } else if (level >= 2) {
              //클러스터
              /*
                        let position = new kakao.maps.LatLng(37.566826, 126.9786567);
                        let content = '<div class ="label"><span class="left"></span><span class="center">카카오!</span><span class="right"></span></div>';
                        
                        customOverlay = new kakao.maps.CustomOverlay({
                            position: position,
                            content: content
                        });
                        
                        customOverlay.setMap(map);
                        */
            } else {
              alert("왜 여길로 왔는가.");
              //예기치 못할때 여기로 들어옴.
            }
          }
        }, 750); //750ms
      }
    },
    addScript() {
      //alert("addScript");
      const script = document.createElement("script");
      script.onload = () => kakao.maps.load(this.initMap);
      script.src =
        "http://dapi.kakao.com/v2/maps/sdk.js?appkey=0fe1d5fd101ab6d2078168510cdb7237&libraries=services,clusterer,drawing&autoload=false";
      document.head.appendChild(script);
    }
  },
  watch: {
    dongLatitude: function() {
      centerSetting(this.dongLatitude, this.dongLongitude);
      mapVar();
    },
    roomTypeOption: function () {
      //alert("변경 감지!1");
      RoomOptionTemp += 1;
      roomTypeOption = this.roomTypeOption;
      mapVar();
      //this.initMap().mapChange();
    },
    roomPayOption: function () {
      //alert("변경 감지!2");
      RoomOptionTemp += 1;
      roomPayOption = this.roomPayOption;
      mapVar();
      //this.initMap().mapChange();
    },
    deposit: function () {
      //alert("변경 감지!3");
      RoomOptionTemp += 1;
      deposit = this.deposit;
      mapVar();
      ////this.initMap().mapChange();
    },
    lease: function () {
      //alert("변경 감지!4");
      RoomOptionTemp += 1;
      lease = this.lease;
      mapVar();
    },
    monthRent: function () {
      //alert("변경 감지!5");
      RoomOptionTemp += 1;
      monthRent = this.monthRent;
      mapVar();
    },
    roomSpace: function () {
      //alert("변경 감지!6");
      RoomOptionTemp += 1;
      roomSpace = this.roomSpace;
      mapVar();
    },
  },
};
</script>

<style>
.search-item-wrapper {
  margin-top: 40px;
  display: inline-block;
  position: relative;
  top: -20px;
  cursor: pointer;
  z-index: 9;
  border-radius: 34px;
}

.search-item {
  display: inline-block;
  min-width: 40px;
  height: 35px;
  color: rgb(255, 255, 255);
  font-size: 17px;
  line-height: 20px;
  text-align: center;
  white-space: nowrap;
  background-color: rgb(35, 128, 255);
  position: relative;
  left: 3px;
  padding: 6px 13px 8px;
  border-width: 1px;
  border-style: solid;
  border-color: rgb(25, 119, 247);
  border-image: initial;
  z-index: 1;
  border-radius: 40px;
}

.search-item:hover {
  background-color: #fff;
  color: #1374f8;
}

.search-address {
  display: inline-block;
  min-width: 44px;
  color: rgb(68, 68, 68);
  font-size: 13px;
  font-weight: 500;
  line-height: 20px;
  white-space: nowrap;
  border-top-right-radius: 32px;
  border-bottom-right-radius: 32px;
  box-shadow: rgba(0, 0, 0, 0.35) 0px 0px 2px 0px;
  background-color: rgb(255, 255, 255);
  pointer-events: none;
  position: relative;
  top: -1px;
  left: -7px;
  z-index: -99;
  padding: 6px 12px 6px 15px;
}
</style>