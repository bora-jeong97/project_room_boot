<template>
  <div>
    <notifications group="notifyApp" position="right right" style="margin-right: 30vh;"></notifications>
    <md-table v-model="users" :table-header-color="tableHeaderColor" style="height: 360px;">
      <md-table-row
        class="md-t"
        @click="modalClick(item.id, item.del)"
        slot="md-table-row"
        slot-scope="{ item }"
      >
        <md-table-cell md-label="타입">{{item.type}}</md-table-cell>
        <!-- <md-table-cell md-label>{{ item.payment }}</md-table-cell> -->
        <md-table-cell md-label="제목 ">
          <span class="qna-list-depth" v-for=" n in item.depth " :key="n"></span>
          <img v-if="item.depth > 0" src="../../assets/arrow.png" style="width:20px; height:20px;" />
          <span v-if="item.del === 1">삭제된 게시글입니다</span>
          <span v-else>{{item.title}}</span>
        </md-table-cell>
        <md-table-cell v-if="item.broker_id" md-label="제목 ">{{ item.broker_id }}</md-table-cell>
        <md-table-cell v-if="item.user_id" md-label="작성자">{{ item.user_id }}</md-table-cell>
        <md-table-cell v-if="item.admin_id" md-label="작성자">관리자</md-table-cell>
        <md-table-cell v-if="item.wdate" md-label="작성한 시각">{{ (item.wdate).split(" ")[0] }}</md-table-cell>
      </md-table-row>
    </md-table>

    <b-modal v-model="modalShow" id="modal-1" title>
      <div slot="modal-header" class="modal-header-wrapper">
        <div class="modal-header-title">
          <b-form-input
            style="background-color:#fff;"
            v-model="title"
            :disabled="!modalUpdate"
            placeholder="제목을 입력하세요"
          ></b-form-input>
        </div>

        <div class="modal-header-body">
          <b-form-textarea
            style="background-color:#fff;"
            id="textarea"
            v-model="content"
            placeholder="내용을 입력하세요"
            rows="14"
            no-resize
            :disabled="!modalUpdate"
          ></b-form-textarea>
        </div>
      </div>
      <!-- <div slot="modal-title" class="modal-title-wrapper">{{title}}</div> -->
      <!-- <p class="my-4">Hello from modal!</p> -->
      <div slot="modal-footer" style="display:flex; aligin-item:center;">
        <div class="modal-footer-button-wrapper">
          <b-button
            v-if="!modalUpdate"
            variant="outline-success"
            @click="moveUpdate(detailData.id)"
          >수정하기</b-button>
          <b-button v-else variant="outline-success" @click="updateButton(detailData.id)">작성하기</b-button>
        </div>
        <div class="modal-footer-button-wrapper">
          <b-button variant="success" @click="moveReplyButton(detailData.id)">답글달기</b-button>
        </div>
        <div class="modal-footer-button-wrapper">
          <b-button variant="danger" @click="deleteButton(detailData.id)">삭제하기</b-button>
        </div>
      </div>
    </b-modal>

    <b-modal v-model="modalReplyShow" id="modal-1" title>
      <div slot="modal-header" class="modal-header-wrapper">
        <div class="modal-header-title">
          <b-form-input style="background-color:#fff;" v-model="replyTitle" placeholder="제목을 입력하세요"></b-form-input>
        </div>

        <div class="modal-header-body">
          <b-form-textarea
            style="background-color:#fff;"
            id="textarea"
            v-model="replyContent"
            placeholder="내용을 입력하세요"
            rows="14"
            no-resize
          ></b-form-textarea>
        </div>
      </div>

      <div slot="modal-footer" style="display:flex; aligin-item:center;">
        <div class="modal-footer-button-wrapper">
          <b-button variant="success" @click="writeReplyButton(detailData.id)">답글 작성하기</b-button>
        </div>
      </div>
    </b-modal>
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
import { error, success } from "../../util/notification";
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
      modalShow: false,
      selected: [],
      perPage: 3,
      currentPage: 1,
      search: "",
      s_category: "",
      s_keyword: "",
      userCount: "",
      detailData: "",
      title: "",
      content: "",
      replyTitle: "",
      replyContent: "",
      type: "",
      modalReplyShow: false,
      modalUpdate: false,
      modalReply: false,
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
      requestParams("get", "qna/getQnACount/", {
        s_category: "name",
        s_keyword: this.s_keyword
      }).then(res => {
        //유저의 수가 리턴된다
        this.userCount = res;
        // console.log(res);
        let params = new URLSearchParams();
        params.append("s_category", "user");
        requestParams("get", "qna/getQnAList/", {
          s_category: "name",
          s_keyword: this.s_keyword,
          start: (this.currentPage - 1) * 5
        }).then(res => {
          // console.log(res);
          this.users = res;
        });
      });
    },
    pageClick() {
      this.getUserList();
    },
    deleteUser(id, userId) {
      // console.log(id);
      let confirmId = confirm(`${userId} 해당 회원을 탈퇴처리 하시겠습니까?`);
      // console.log(confirmId);
      if (confirmId) {
        requestParams("get", "admin/userForcedDeletion", {
          id: id
        });
        this.getUserList();
      }
      // error("비밀번호를 입력해주세요", this);
    },
    modalClick(id, del) {
      // console.log(id);
      if (del > 0) return;

      requestParams("get", "qna/getQnA", {
        id: id
      }).then(res => {
        this.detailData = res;
        this.title = res.title;
        this.content = res.content;
        this.type = res.type;
        console.log(res);
        this.modalShow = !this.modalShow;
      });
    },
    moveUpdate(id) {
      // console.log(id);
      this.modalUpdate = true;
    },
    updateButton(id) {
      // console.log(id);

      requestParams("get", "qna/updateQnA", {
        title: this.title,
        content: this.content,
        type: this.type,
        id: id
      }).then(res => {
        console.log(res);
        success("수정이 완료되었습니다", this);
        this.modalUpdate = false;
        this.modalShow = false;
        this.getUserList();
      });
    },
    moveReplyButton(id) {
      console.log(id);
      this.modalShow = false;
      this.modalUpdate = false;
      this.modalReplyShow = true;
    },
    deleteButton(id) {
      let choose = confirm("해당 글을 삭제하시겠습니까?");
      if (choose) {
        requestParams("get", "qna/deleteQnA", {
          id: id
        }).then(res => {
          console.log(res);
          this.updateModal = false;
          this.modalUpdate = false;
          success("삭제가 완료되었습니다", this);
          this.modalShow = false;
          this.modalReplyShow = false;
          this.getUserList();
          // this.SET_QNA_MODAL(true);
        });
      }
    },
    writeReplyButton(id) {
      if (this.replyTitle.length < 1) {
        error("제목을 입력해 주세요", this);
        return;
      }
      if (this.replyContent.length < 1) {
        error("내용을 입력해주세요", this);
        return;
      }
      let params = new URLSearchParams();
      params.append("id", this.detailData.id);
      params.append("title", this.replyTitle);
      params.append("content", this.replyContent);
      params.append("type", this.detailData.type);
      params.append("adminId", "admin");
      // if (this.loginData.user_id) {
      //   params.append("userId", this.loginData.user_id);
      // } else if (this.loginData.broker_id) {
      //   params.append("brokerId", this.loginData.broker_id);
      // }
      request("post", "qna/replyQnAInsert", params).then(res => {
        console.log(res);
        success("답글 작성이 완료되었습니다", this);
        this.getUserList();
        this.modalReplyShow = false;
      });
      // console.log(id);
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

.qna-list-depth {
  margin-left: 15px;
}
.modal-footer-button-wrapper {
  margin-left: 10px;
}
.modal-header-title {
  width: 100%;
  text-align: center;
  margin-top: 20px;
}
.modal-header-wrapper {
  width: 100%;
  font-size: 20px;
}
.modal-header-body {
  margin-top: 40px;
  font-size: 24px;
}
.md-t:hover {
  cursor: pointer;
}
</style>
