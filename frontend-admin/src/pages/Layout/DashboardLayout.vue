<template>
  <div class="wrapper" :class="{ 'nav-open': $sidebar.showSidebar }">
    <notifications></notifications>
    <Login v-if="loginModal" />
    <side-bar
      :sidebar-item-color="sidebarBackground"
      :sidebar-background-image="sidebarBackgroundImage"
    >
      <mobile-menu slot="content"></mobile-menu>
      <sidebar-link to="/dashboard">
        <md-icon>dashboard</md-icon>
        <p>Dashboard</p>
      </sidebar-link>

      <sidebar-link to="/table">
        <md-icon>content_paste</md-icon>
        <p>유저/공인중개사 관리</p>
      </sidebar-link>
      <sidebar-link to="/user">
        <md-icon>person</md-icon>
        <p>결제 내역 조회</p>
      </sidebar-link>
      <sidebar-link to="/qna">
        <md-icon>person</md-icon>
        <p>문의 게시판</p>
      </sidebar-link>
      <!-- <sidebar-link to="/typography">
        <md-icon>library_books</md-icon>
        <p>Typography</p>
      </sidebar-link>
      <sidebar-link to="/icons">
        <md-icon>bubble_chart</md-icon>
        <p>Icons</p>
      </sidebar-link>
      <sidebar-link to="/maps">
        <md-icon>location_on</md-icon>
        <p>Maps</p>
      </sidebar-link>-->
      <!-- <sidebar-link to="/notifications">
        <md-icon>notifications</md-icon>
        <p>알림</p>
      </sidebar-link>-->
      <!-- <sidebar-link to="/upgrade" class="active-pro">
        <md-icon>unarchive</md-icon>
        <p>Upgrade to PRO</p>
      </sidebar-link>-->
    </side-bar>

    <div class="main-panel">
      <top-navbar></top-navbar>

      <fixed-plugin :color.sync="sidebarBackground" :image.sync="sidebarBackgroundImage"></fixed-plugin>

      <dashboard-content></dashboard-content>

      <content-footer v-if="!$route.meta.hideFooter"></content-footer>
    </div>
  </div>
</template>

<script>
import TopNavbar from "./TopNavbar.vue";
import ContentFooter from "./ContentFooter.vue";
import DashboardContent from "./Content.vue";
import MobileMenu from "@/pages/Layout/MobileMenu.vue";
import FixedPlugin from "./Extra/FixedPlugin.vue";
import Login from "../Login";
import { mapState, mapMutations } from "vuex";

export default {
  components: {
    TopNavbar,
    DashboardContent,
    ContentFooter,
    MobileMenu,
    FixedPlugin,
    Login
  },
  data() {
    return {
      sidebarBackground: "green",
      sidebarBackgroundImage: require("@/assets/img/sidebar-2.jpg")
    };
  },
  computed: {
    ...mapState(["loginModal"])
  }
};
</script>
