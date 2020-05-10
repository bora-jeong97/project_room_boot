export const kakaoMap = (mapId) => {
    if (window.kakao && window.kakao.maps) {
        const container = document.querySelector(mapId);
        const options = {
            center: new kakao.maps.LatLng(33.450701, 126.570667),
            level: 3,
        };

        const map = new kakao.maps.Map(container, options);
        map.setMapTypeId(kakao.maps.MapTypeId.HYBRID);
    } else {
        const script = document.createElement('script');
        /* global kakao */
        script.onload = () => {
            kakao.maps.load(initMap(mapId));
        };
        script.src =
            // '//dapi.kakao.com/v2/maps/sdk.js?appkey=0fe1d5fd101ab6d2078168510cdb7237&libraries=services';
            'http://dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=0fe1d5fd101ab6d2078168510cdb7237';
        document.head.appendChild(script);
    }
};

function initMap(mapId) {
    // var container = document.getElementById("map");
    const container = document.querySelector(mapId);
    const options = {
        center: new kakao.maps.LatLng(33.450701, 126.570667),
        level: 3,
    };

    const map = new kakao.maps.Map(container, options);
    map.setMapTypeId(kakao.maps.MapTypeId.HYBRID);
}
// https://devtalk.kakao.com/t/vue-kakao-map-api/96660/10
// <template>
//   <div id="app">
//     <div id="map"></div>
//   </div>
// </template>

/*
<script>
export default {
  mounted() {
    if (window.kakao && window.kakao.maps) {
      this.initMap();
    } else {
      const script = document.createElement("script");
  
      script.onload = () => kakao.maps.load(this.initMap);
      script.src =
        "http://dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=YOUR_APPKEY";
      document.head.appendChild(script);
    }
  },
  methods: {
    initMap() {
      var container = document.getElementById("map");
      var options = {
        center: new kakao.maps.LatLng(33.450701, 126.570667),
        level: 3
      };

      var map = new kakao.maps.Map(container, options);
      map.setMapTypeId(kakao.maps.MapTypeId.HYBRID);
    }
  }
};
</script>
*/