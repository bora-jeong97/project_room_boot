<template>
  <div>
    <notifications group="notifyApp" position="right right" style="margin-right: 30vh;"></notifications>
    <md-table v-model="users" :table-header-color="tableHeaderColor" style="height: 360px;">
      <md-table-row slot="md-table-row" slot-scope="{ item, index }">
        <md-table-cell md-label="index">{{index + 1}}</md-table-cell>
        <md-table-cell md-label="profile">
          <img style="width:40px; height:40px;" :src="item.profile_image" />
        </md-table-cell>
        <md-table-cell md-label="Email">{{ item.user_id }}</md-table-cell>
        <md-table-cell md-label="Name">{{ item.name }}</md-table-cell>
        <md-table-cell md-label="PhoneNumber">{{ item.phone_number }}</md-table-cell>
        <slot name="auth-table">
          <md-table-cell v-if="item.auth == 6" md-label="auth">탈퇴한 회원</md-table-cell>
          <md-table-cell v-if="item.auth == 3" md-label="auth">일반 회원</md-table-cell>
        </slot>
        <slot name="button-delete">
          <md-table-cell class="user-delete-button" md-label="탈퇴">
            <b-button
              v-if="item.auth == 6"
              variant="success"
              @click="resurrectionUser(item.id, item.user_id)"
            >복구</b-button>
            <b-button v-else variant="danger" @click="deleteUser(item.id, item.user_id)">탈퇴</b-button>
          </md-table-cell>
        </slot>
      </md-table-row>
    </md-table>

    <!-- per page 보여주는 갯수 -->
    <div class="user-search-container">
      <b-pagination v-model="currentPage" :per-page="5" :total-rows="userCount" @input="pageClick"></b-pagination>
      <div class="user-search-input-wrapper">
        <b-form-input
          v-model="s_keyword"
          placeholder="Enter your name"
          style="width:200px;"
          @input="getUserList"
        ></b-form-input>
        <b-button variant="success" @click="getUserList">검색</b-button>
      </div>
    </div>
  </div>
</template>

<script>
import { request, requestParams } from "../../util/axios";
import { error } from "../../util/notification";
export default {
  name: "simple-table",
  props: {
    tableHeaderColor: {
      type: String,
      default: ""
    }
  },
  data() {
    return {
      selected: [],
      perPage: 3,
      currentPage: 1,
      search: "",
      s_category: "",
      s_keyword: "",
      userCount: "",
      users: [
        {
          name: "Dakota Rice",
          salary: "$36,738",
          country: "Niger",
          city: "Oud-Turnhout"
        },
        {
          name: "Minerva Hooper",
          salary: "$23,738",
          country: "Curaçao",
          city: "Sinaai-Waas"
        },
        {
          name: "Sage Rodriguez",
          salary: "$56,142",
          country: "Netherlands",
          city: "Overland Park"
        },
        {
          name: "Philip Chaney",
          salary: "$38,735",
          country: "Korea, South",
          city: "Gloucester"
        },
        {
          name: "Doris Greene",
          salary: "$63,542",
          country: "Malawi",
          city: "Feldkirchen in Kārnten"
        },
        {
          name: "Mason Porter",
          salary: "$78,615",
          country: "Chile",
          city: "Gloucester"
        }
      ]
    };
  },
  mounted() {
    this.getUserList();
  },
  computed: {
    // rows() {
    //   return this.items.length;
    // },
  },
  methods: {
    getUserList() {
      requestParams("get", "admin/getUserCount/", {
        s_category: "name",
        s_keyword: this.s_keyword
      }).then(res => {
        //유저의 수가 리턴된다
        this.userCount = res;
        console.log(res);
        let params = new URLSearchParams();
        params.append("s_category", "user");
        requestParams("get", "admin/getUserList/", {
          s_category: "name",
          s_keyword: this.s_keyword,
          start: (this.currentPage - 1) * 5
        }).then(res => {
          console.log(res);
          this.users = res;
        });
      });
    },
    pageClick() {
      this.getUserList();
    },
    deleteUser(id, userId) {
      console.log(id);
      let confirmId = confirm(`${userId} 해당 회원을 탈퇴처리 하시겠습니까?`);
      console.log(confirmId);
      if (confirmId) {
        requestParams("get", "admin/userForcedDeletion", {
          id: id
        });
        this.getUserList();
      }
      // error("비밀번호를 입력해주세요", this);
    },
    resurrectionUser(id, userId) {
      console.log(id);
      let confirmId = confirm(`${userId} 해당 회원을 복구처리 하시겠습니까?`);
      console.log(confirmId);
      if (confirmId) {
        requestParams("get", "admin/userResurrection", {
          id: id
        });
        this.getUserList();
      }
      // error("비밀번호를 입력해주세요", this);
    }
  }
};
</script>
<style>
.page-item.active .page-link {
  background-color: #4da851 !important;
  border-color: #4da851 !important;
  color: #fff !important;
}
.page-link {
  color: #4da851 !important;
}
.user-search-container {
  margin-top: 20px;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
}

.user-search-input-wrapper {
  display: flex;
}
.user-search-input-wrapper > * {
  margin-left: 10px;
}
.user-delete-button {
}
</style>
