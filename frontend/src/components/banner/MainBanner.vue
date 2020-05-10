<template>
  <div class="banner-container">
    <div class="banner-type">
      <vue-typed-js
        :strings="[
          '<div style=color:#1a5ae8;>어떤 동네, 어떤방<span style=color:black;>에서</span></div><span style=color:black;>살고 싶으신가요?</span>',
        ]"
      >
        <h3>
          <div style="text-align:center" class="typing"></div>
        </h3>
      </vue-typed-js>
    </div>
    <div class="room-search-wrapper">
      <div class="room-search-icon">
        <img src="../../assets/search.png" />
      </div>
      <input @focus="SET_SEARCH_MODAL(true)" @blur="SET_SEARCH_MODAL(false)" @input="axiosSearch" v-model="keyword" />
      <div class="searchButton">방찾기</div>
      <div class="modal">
        <ModalSearch v-if="searchModal" />
      </div>
    </div>
  </div>
</template>

<script>
import { request, requestParams } from "../../util/axios";
import ModalSearch from "../../components/ModalSearch";
import { mapState, mapMutations } from "vuex";
("vuex");
export default {
  components: {
    ModalSearch
  },
  data() {
    return {
      keyword: "",
    };
  },
  computed: {
    ...mapState(["searchModal", "searchList"])
  },
  methods: {
    ...mapMutations(["SET_SEARCH_MODAL", "SET_SEARCH_LIST"] ),
    
    axiosSearch() {
      //console.log(this.keyword);

      let params = new URLSearchParams();
      params.append("keyword", this.keyword);

      request("post", "search/getRoomList", params)
        .then(res => {
          console.log(res);

          this.SET_SEARCH_LIST(res);

          console.log(this.searchList);
          //리스트를 data안에 집어넣기
          //this.roomLists = res;
        })
        .catch(error => console.log(error));

    }
  }
};
</script>

<style scoped>
.banner-container {
  padding-top: 80px;
  height: 100%;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  padding-bottom: 100px;
}
.banner-type {
  height: 125px;
  position: relative;
  z-index: 999;
}
.typing {
  font-size: 43px;
  font-weight: 100;
}
.room-search-wrapper {
  position: relative;
  z-index: 999;
  margin-top: 25px;
  display: flex;
  align-items: center;
  justify-content: space-between;
  width: 800px;
  height: 60px;
  background-color: #fff;
  padding: 0 5px;
  border: 1px solid rgba(19, 116, 248, 0.5);
}
.room-search-wrapper > input {
  z-index: 999;
  width: 90%;
  height: 100%;
  border: none;
  font-size: 25px;
  padding: 0 15px;
}
.room-search-icon {
  width: 45px;
  height: 30px;
  padding-left: 5px;

  border-right: 1px solid rgb(19, 116, 248, 0.5);
}
.room-search-icon > img {
  width: 30px;
}

.searchButton {
  color: white;
  background-color: rgb(19, 116, 248);
  font-size: 16px;
  width: 95px;
  height: 48px;
  line-height: 48px;
  text-align: center;
  cursor: pointer;
}
</style>
