<template>
  <transition name="modal">
    <div class="modal-mask">
      <div class="modal-wrapper">
        <div class="modal-container">
          <div class="modal-image-close-button" @click="SET_IMAGE_LIST_MODAL(false)">x</div>
          <div class="modal-header">
            <div class="image-modal-layout">
              <div
                v-for="(image,index) in imageList"
                :key="index"
                class="image-modal-layout-header-image-wrapper"
                @click="imageClick(image)"
              >
                <img :src="image.file_name" />
              </div>
            </div>
          </div>

          <div class="modal-body" style="margin:0">
            <slot name="body">
              <div class="image-modal-layout-main-image-wrapper">
                <img :src="imageMain" />
              </div>
            </slot>
          </div>

          <!-- <div class="modal-footer">
            <slot name="footer">
              default footer
              <button class="modal-default-button" @click="$emit('close')">OK</button>
            </slot>
          </div>-->
        </div>
      </div>
    </div>
  </transition>
</template>

<script>
import { mapState, mapMutations } from "vuex";
export default {
  data() {
    return {
      imageMain: ""
    };
  },
  mounted() {
    // console.log(this.imageList[0].file_name);
    this.imageMain = this.imageList[0].file_name;
  },
  computed: {
    ...mapState(["imageList"])
  },
  methods: {
    ...mapMutations(["SET_IMAGE_LIST_MODAL"]),
    imageClick(image) {
      console.log(image);
      this.imageMain = image.file_name;
    }
  }
};
</script>

<style scoped>
* {
  box-sizing: border-box;
}
.modal-mask {
  position: fixed;
  z-index: 1100;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background-color: rgba(0, 0, 0, 0.8);
  display: table;
  transition: opacity 0.3s ease;
}

.modal-wrapper {
  display: table-cell;
  vertical-align: middle;
}

.modal-container {
  width: 800px;
  /* height: 80%; */
  margin: 0 auto;
  position: relative;
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

.modal-body {
  height: 100%;
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
.modal-header {
  overflow-x: scroll;
  overflow-y: hidden;
  position: relative;
}
.image-modal-layout {
  width: 1500px;
  height: 93px;
  border-bottom: 1px solid #dddddd;
  display: flex;
  flex-wrap: wrap;
}

.image-modal-layout-header-image-wrapper {
  width: 140px;
  height: 100%;
  display: flex;
  justify-content: center;
  border: 1px solid #dddddd;
}

.image-modal-layout-header-image-wrapper > img {
  width: 100%;
  height: 100%;
}

::-webkit-scrollbar {
  width: 10px; /* 세로축 스크롤바 길이 */
  height: 12px; /* 가로축 스크롤바 길이 */
}
::-webkit-scrollbar-track {
  background-color: lightblue;
}
::-webkit-scrollbar-track-piece {
  background-color: #fff;
}
::-webkit-scrollbar-thumb {
  border-radius: 8px;
  background-color: #1a5ae8;
}
::-webkit-scrollbar-button {
  background-color: darkblue;
  width: 20px;
  height: 10px;
}
::-webkit-scrollbar-button:start {
  background-color: #fff; /* Top, Left 방향의 이동버튼 */
}
::-webkit-scrollbar-button:end {
  background-color: #fff; /* Bottom, Right 방향의 이동버튼 */
  /* }
::-webkit-scrollbar-button:vertical:increment {
}
::-webkit-scrollbar-button:vertical:decrement {
}
::-webkit-scrollbar-corner {
  background-color: violet; /* 우측 하단의 코너 부분 */
}
/*
::-webkit-resizer {
  background-color: green;
} */

.image-modal-layout-main-image-wrapper {
  width: 100%;
  height: 100%;
  display: flex;
  justify-content: center;
}
.image-modal-layout-main-image-wrapper > img {
  height: 80%;
  width: 80%;
}

.modal-image-close-button {
  position: absolute;
  display: block;
  top: -25px;
  right: -80px;
  font-size: 80px;
  width: 40px;
  cursor: pointer;
  color: #666666;
}
</style>
