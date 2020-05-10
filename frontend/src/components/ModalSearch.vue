<template>
  <transition name="modal">
    <div class="modal-mask">
      <div class="modal-wrapper">
        <div class="modal-container">
          <div class="modal-header">
            <slot name="header">
              <div class="header-content">
                <div class="header-content-popular">인기검색</div>
                <div class="header-content-popular-search">
                  <ul>
                    <li
                      style="list-style:none; margin-top:10px;"
                      v-for="(res,index) in searchList"
                      :key="index"
                    >
                      <router-link
                        style="border: none; color:#222;"
                        class="room-selector1"
                        :to="`/search`"
                      >
                        <div
                          class="router-item router-item-search"
                          v-on:click="moveSearch(res.d_name , res.latitude , res.longitude)"
                        >{{res.s_name}} {{res.g_name}} {{res.d_name}}</div>
                      </router-link>
                    </li>
                  </ul>
                </div>
              </div>
            </slot>
          </div>

          <div class="modal-body">
            <slot name="body">
              <!-- <div class="content-wrapper">
                <div class="content-recent-title">최근검색기록</div>
                <div class="content-recent-wrapper">
                  <div class="content-recent-search">
                    <img src="../assets/clock.png" />잠실동
                  </div>
                  <div class="content-recent-search">신림역</div>
                </div>
              </div>-->
            </slot>
          </div>
        </div>
      </div>
    </div>
  </transition>
</template>


<script>
import { mapState, mapMutations } from "vuex";
export default {
  computed: {
    ...mapState(["searchList"])
  },
  methods: {
    ...mapMutations(["SET_DONG_LATITUDE", "SET_DONG_LONGITUDE"]),

    moveSearch(d_name, latitude, longitude) {
      //alert(d_name + " " + latitude + " " + longitude);
      this.SET_DONG_LATITUDE(latitude);
      this.SET_DONG_LONGITUDE(longitude);
    }
  }
};
</script>

<style scoped>
.router-item-search {
  cursor: pointer;
  margin-top: 3px;
}
.router-item-search:hover {
  font-weight: bold;
}
* {
  box-sizing: border-box;
}
.modal-mask {
  position: absolute;
  z-index: 950;
  top: 40px;
  left: 0;
  width: 100%;
  height: 100%;
  /* background-color: rgba(255, 255, 255, 0.7); */
  display: table;
  transition: opacity 0.3s ease;
}

.modal-wrapper {
  display: table-cell;
  vertical-align: middle;
}

.modal-container {
  position: absolute;

  width: 800px;
  height: 360px;
  margin: 0px auto;
  padding: 30px 40px;
  background-color: #fff;
  border-radius: 2px;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.33);
  transition: all 0.3s ease;
  font-family: Helvetica, Arial, sans-serif;
}

.modal-header h3 {
  margin-top: 0;
  color: #42b983;
}
.header-content {
  color: black;
}

.header-content-popular {
  font-size: 16px;
  font-weight: bold;
}
.header-content-popular-search {
  margin-top: 10px;
  font-size: 15px;
}

.modal-body {
  margin: 20px auto;
}
.content-wrapper {
  border-top: 1px solid rgb(235, 235, 235);
  padding-top: 20px;
  font-weight: bold;
  font-size: 16px;
  color: black;
}

.content-recent-wrapper {
  margin-top: 20px;
}
.content-recent-search {
  margin-top: 10px;
  font-weight: 100;
  font-size: 15px;
  display: flex;
  align-items: center;
}
.content-recent-search > img {
  width: 18px;
  margin-right: 10px;
}

.modal-default-button {
  float: right;
}

/*
 * The following styles are auto-applied to elements with
 * transition="modal" when their visibility is toggled
 * by Vue.js.
 *
 * You can easily play with the modal transition by editing
 * these styles.
 */

.modal-enter {
  opacity: 0;
}

.modal-leave-active {
  opacity: 0;
}

.modal-enter .modal-container,
.modal-leave-active .modal-container {
  -webkit-transform: scale(1.1);
  transform: scale(1.1);
}
</style>
