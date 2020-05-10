<template>
  <div>
    <notifications
      group="notifyApp"
      position="top right"
      style="margin-top:20vh; margin-right: 30vh;"
    />
    <div class="qna-list-container">
      <div class="qna-write-header-title qna-write-margin">
        <span class="qna-main-title">구해방</span>에 문의해주신 질문에 대한 답변입니다
      </div>
      <el-pagination
        class="profile-payment-table-page qna-list-page"
        background
        layout="prev, pager, next"
        :page-size="4"
        :total="qnaCount"
        :current-page.sync="currentPage"
        @current-change="getList"
      ></el-pagination>
      <div class="qna-list-wrapper" style="border:none; font-weight:bold; ">
        <div class="qna-list-title">문의 유형</div>
        <div class="qna-list-content">제목</div>
        <div class="qna-list-writer">작성자</div>
        <div class="qna-list-date">날짜</div>
      </div>
      <div
        class="qna-list-wrapper"
        v-for="(test, index) in dataLists"
        :key="index"
        :id="test.id"
        @click="getDetail(test.id, test.del)"
      >
        <div class="qna-list-title">{{test.type ? test.type : "내용없음"}}</div>
        <div class="qna-list-content">
          <span class="qna-list-depth" v-for=" n in test.depth " :key="n">
            <!-- 댓글의 여백 -->
          </span>
          <img v-if="test.depth > 0" src="../../assets/arrow.png" width="20px" height="20px" />
          <span v-if="test.del === 1">삭제된 게시글입니다</span>
          <span v-else>{{test.title}}</span>
        </div>
        <div v-if="test.user_id" class="qna-list-writer">{{test.user_id}}</div>
        <div v-if="test.broker_id" class="qna-list-writer">{{test.broker_id}}</div>
        <div v-if="test.admin_id" class="qna-list-writer">관리자</div>
        <div class="qna-list-date">{{test.wdate.split(" ")[0]}}</div>
      </div>
    </div>
    <ModalQna v-if="qnaModal">
      <div slot="header">
        <div class="modal-qna-header-wrapper">
          <div class="modal-qna-close-button" @click="closeButton">
            <img src="../../assets/close.png" />
          </div>
          <!-- <div class="modal-qna-header-type">일반문의</div> -->

          <input
            class="modal-qna-header-title"
            :class="{'border-modal' : updateModal}"
            v-model="title"
            :disabled="!updateModal"
          />
          <div v-if="detailData.user_id" class="modal-qna-header-writer">작성자: {{detailData.user_id}}</div>
          <div
            v-if="detailData.broker_id"
            class="modal-qna-header-writer"
          >작성자: {{detailData.broker_id}}</div>
          <div v-if="detailData.admin_id" class="modal-qna-header-writer">작성자: 관리자</div>
        </div>
      </div>
      <div slot="body">
        <textarea
          class="modal-qna-body-wrapper"
          :class="{'border-modal' : updateModal}"
          :disabled="!updateModal"
          v-model="content"
        ></textarea>
      </div>
      <div slot="footer">
        <div class="modal-qna-footer-wrapper">
          <div
            v-if="!updateModal&&loginData.user_id ===detailData.user_id || !updateModal&&loginData.broker_id ===detailData.broker_id"
            class="modal-qna-button modal-qna-update"
            @click="updateModal = true"
          >수정하기</div>
          <div
            v-else-if="loginData.user_id ===detailData.user_id || loginData.broker_id === detailData.broker_id"
            class="modal-qna-button modal-qna-update"
            @click="updateButton(detailData.id)"
          >작성하기</div>
          <div class="modal-qna-button modal-qna-reply" @click="goToReply(detailData.id)">답글 달기</div>
          <div
            v-if="loginData.user_id ===detailData.user_id || loginData.broker_id ===detailData.broker_id"
            class="modal-qna-button modal-qna-delete"
            @click="deleteButton(detailData.id)"
          >삭제하기</div>
        </div>
      </div>
    </ModalQna>

    <ModalQna v-if="qnaReplyModal">
      <div slot="header">
        <div class="modal-qna-header-wrapper">
          <div class="modal-qna-close-button" @click="SET_QNA_REPLY_MODAL(false)">
            <img src="../../assets/close.png" />
          </div>
          <!-- <div class="modal-qna-header-type">일반문의</div> -->

          <input
            placeholder="제목을 작성해주세요"
            class="modal-qna-header-title"
            v-model="replyTitle"
            autofocus
          />
          <div class="modal-qna-header-writer">작성자: {{loginData.user_id}}</div>
        </div>
      </div>
      <div slot="body">
        <textarea class="modal-qna-body-wrapper" v-model="replyContent" placeholder="답글 내용을 작성해주세요"></textarea>
      </div>
      <div slot="footer">
        <div class="modal-qna-footer-wrapper">
          <div class="modal-qna-button modal-qna-update" @click="writeReplyButton()">답글 작성하기</div>
        </div>
      </div>
    </ModalQna>
  </div>
</template>

<script>
import axios from "axios";
import { requestParams, request } from "../../util/axios";
import { mapState, mapMutations } from "vuex";
import ModalQna from "../ModalQna";
import { success, error } from "../../util/notification";

export default {
  components: {
    ModalQna
  },
  data() {
    return {
      updateModal: false,
      // auth: login.auth,
      dataList: [],
      s_category: "sel",
      s_keyword: "",
      currentPage: 1,
      qnaCount: 0,
      dataLists: [],
      detailData: "",
      title: "",
      content: "",
      type: "",
      replyTitle: "",
      replyContent: ""
    };
  },
  computed: {
    ...mapState(["loginData", "qnaModal", "qnaReplyModal"])
  },
  mounted() {
    //무조건 실행 mounted
    //			alert('mounted');
    this.getList();
  },
  methods: {
    ...mapMutations(["SET_QNA_MODAL", "SET_QNA_REPLY_MODAL"]),

    getList() {
      if (this.loginData.user_id) {
        requestParams("get", "qna/getQnaUserList", {
          userId: this.loginData.user_id,
          start: (this.currentPage - 1) * 3
        }).then(res => {
          console.log("aaa");
          // console.log(res);
          // console.log(res[0].user_cnt);
          // data에 qna_list만 담기
          this.dataLists = [];
          res.map(data => {
            this.dataLists = this.dataLists.concat(data.qna_list);
            // console.log(data.qna_list);
          });
          this.qnaCount = res[0].user_cnt;
          // console.log(this.dataLists);
        });
      } else {
        requestParams("get", "qna/getQnaBrokerList", {
          brokerId: this.loginData.broker_id,
          start: (this.currentPage - 1) * 3
        }).then(res => {
          console.log(res);
          // console.log(res[0].broker_cnt);
          // data에 qna_list만 담기
          this.dataLists = [];
          this.qnaCount = res[0].broker_cnt;
          res.map(data => {
            this.dataLists = this.dataLists.concat(data.qna_list);
          });
          // console.log(this.dataLists);
        });
      }
    },
    closeButton() {
      this.SET_QNA_MODAL(false);
      this.updateModal = false;
    },
    getDetail(id, del) {
      if (del > 0) return;

      requestParams("get", "qna/getQnA", {
        id: id
      }).then(res => {
        this.detailData = res;
        this.title = res.title;
        this.content = res.content;
        this.type = res.type;
        console.log(res);
        this.SET_QNA_MODAL(true);
      });
    },
    updateButton(id) {
      console.log(id);

      requestParams("get", "qna/updateQnA", {
        title: this.title,
        content: this.content,
        type: this.type,
        id: id
      }).then(res => {
        console.log(res);
        this.SET_QNA_MODAL(false);
        success("수정이 완료되었습니다", this);
        this.getList();

        // this.SET_QNA_MODAL(true);
      });
      this.updateModal = false;
    },
    deleteButton(id) {
      let choose = confirm("해당 글을 삭제하시겠습니까?");
      if (choose) {
        requestParams("get", "qna/deleteQnA", {
          id: id
        }).then(res => {
          console.log(res);
          this.SET_QNA_MODAL(false);
          success("삭제가 완료되었습니다", this);
          this.getList();

          // this.SET_QNA_MODAL(true);
        });
      }
      this.updateModal = false;
    },
    closeReplyButton() {},
    //답글
    goToReply(id) {
      console.log(id);
      this.SET_QNA_MODAL(false);
      this.SET_QNA_REPLY_MODAL(true);
    },
    writeReplyButton() {
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
      if (this.loginData.user_id) {
        params.append("userId", this.loginData.user_id);
      } else if (this.loginData.broker_id) {
        params.append("brokerId", this.loginData.broker_id);
      }
      request("post", "qna/replyQnAInsert", params).then(res => {
        console.log(res);
        success("답글 작성이 완료되었습니다", this);
        this.SET_QNA_REPLY_MODAL(false);

        this.getList();
      });
    }
    // 글쓰기
  }
};
</script>

<style>
.qna-list-container {
  color: #222222;
}
.qna-write-margin {
  margin-bottom: 40px;
}

.qna-list-wrapper {
  display: flex;
  height: 60px;
  align-items: center;
}

.qna-list-wrapper:not(:first-child):hover {
  opacity: 0.5;
  cursor: pointer;
}

.qna-list-title {
  flex: 2;
  font-size: 15px;
  text-align: center;
}
.qna-list-content {
  flex: 5;
  font-size: 18px;
}
.qna-list-writer {
  flex: 1;
  font-size: 15px;
}

.qna-list-date {
  font-size: 15px;
  flex: 2;
  text-align: center;
}
.qna-list-depth {
  margin-left: 15px;
}

.qna-list-wrapper {
  border-top: 1px solid #dddddd;
}
.qna-list-page {
  margin-bottom: 30px;
}

.modal-qna-header-wrapper {
  display: flex;
  align-items: center;
  justify-content: center;
  flex-direction: column;
  position: relative;
}

.modal-qna-footer-wrapper {
  display: flex;
  justify-content: center;
  padding: 0 60px;
}

.modal-qna-header-type {
  font-size: 15px;
}
.modal-qna-header-title {
  /* margin-top: 10px; */
  height: 30px;
  width: 100%;
  font-size: 20px;
  font-weight: bold;
  border: none;
  padding: 0 10px;
  text-align: center;
  background-color: #fff;
}

.modal-qna-body-wrapper {
  margin-top: 20px;
  min-height: 360px;
  padding: 20px;
  border: none;
  border-top: 1px solid #dddddd;
  /* border-bottom: 1px solid #dddddd; */
  width: 100%;
  resize: none;
  font-size: 18px;
  font-family: inherit;
  word-spacing: 2px;
  line-height: 30px;
  background-color: #fff;
}
.modal-qna-close-button {
  position: absolute;
  top: -60px;
  right: -65px;
  width: 40px;
  height: 40px;
  cursor: pointer;
}

.modal-qna-close-button > img {
  width: 100%;
}
.modal-qna-header-writer {
  margin-top: 20px;
  margin-left: auto;
  font-size: 15px;
}

.modal-qna-button {
  /* border: 1px solid #ddd; */
  padding: 13px 10px;
  font-size: 14px;
  box-shadow: rgba(188, 196, 219, 0.77) 5px 5px 13px 0px;
  cursor: pointer;
  border-radius: 5px;
}
.modal-qna-button:hover {
  opacity: 0.5;
}
.modal-qna-update {
  background-color: #1374f8;
  color: #fff;
}

.modal-qna-delete {
  margin-left: 30px;
  background-color: #f83d3d;
  color: #fff;
}

.modal-qna-reply {
  margin-left: 30px;
  background-color: #354a87;
  color: #fff;
}

.border-modal {
  padding: 20px;
  border: 1px solid #dddddd;
}
</style>