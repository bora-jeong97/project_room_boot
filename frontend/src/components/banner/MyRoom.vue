<template>
  <div class="my-room">
    <KeepRoom v-if="roomLists" :roomLists="roomLists" />
    <!-- <div class="my-room-not" v-else>찜한 목록이 없습니다</div> -->
  </div>
</template>

<script>
import KeepRoom from "./KeepRoom";
import { mapState } from "vuex";
import { request, requestParams } from "../../util/axios";
export default {
  components: {
    KeepRoom
  },
  data() {
    return {
      roomLists: ""
    };
  },
  computed: {
    ...mapState(["loginData"])
  },
  mounted() {
    this.getInformation();
  },
  methods: {
    getInformation() {
      requestParams("get", "wishlist/getWishList", {
        USERId: this.loginData.id
      }).then(res => {
        console.log(res);

        // this.roomLists = res;
      });
    }
  }
};
</script>

<style>
.my-room {
  min-height: 400px;
}
.my-room-not {
  height: 400px;
  width: 100%;
  display: flex;
  align-items: center;
  justify-content: center;
}
</style>