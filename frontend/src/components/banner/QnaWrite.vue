<template>
  <div class="qna-write-container">
    <div class="qna-write-header">
      <div class="qna-write-header-title">
        <span class="qna-main-title">구해방</span>에 궁금하신 점을 문의해주세요!
      </div>
      <div class="qna-write-content-container">
        <div class="qna-write-type-select-wrapper">
          <div class="qna-write-title">문의유형</div>
          <div class="qna-write-content">
            <select v-model="type" class="qna-write-select-option">
              <option>일반문의</option>
              <option>허위매물 신고</option>
            </select>
          </div>
        </div>
        <div class="qna-write-title-wrapper">
          <div class="qna-write-title">제목</div>
          <div class="qna-write-content">
            <input class="qna-write-input" v-model="title" />
          </div>
        </div>
        <div class="qna-write-content-wrapper">
          <div class="qna-write-title">문의 내용</div>
          <div class="qna-write-content">
            <textarea class="qna-write-textarea" placeholder="내용을 입력하세요" v-model="content"></textarea>
          </div>
        </div>
      </div>
      <div class="qna-write-button-wrapper">
        <div class="qna-write-button" @click="writeQna">전송하기</div>
      </div>
    </div>
  </div>
</template>

<script>
import { requestParams } from "../../util/axios";
import { mapState } from "vuex";
export default {
  data() {
    return {
      type: "일반문의",
      title: "",
      content: ""
    };
  },
  computed: {
    ...mapState(["loginData"])
  },
  methods: {
    writeQna() {
      if (!this.title.length) {
        this.$toasted.show("제목을 입력해주세요", {
          type: "error",
          position: "top-right",
          duration: 2500
        });
        return;
      }
      if (!this.content.length) {
        this.$toasted.show("내용을 입력해주세요", {
          type: "error",
          position: "top-right",
          duration: 2500
        });
        return;
      }
      requestParams("get", "qna/writeQnA", {
        userId: this.loginData.user_id ? this.loginData.user_id : "",
        brokerId: this.loginData.broker_id ? this.loginData.broker_id : "",
        type: this.type,
        title: this.title,
        content: this.content,
        auth: this.loginData.auth
      }).then(res => {
        console.log(res);
        if (res === true) {
          this.$toasted.show("글 작성이 완료되었습니다", {
            type: "success",
            position: "top-right",
            duration: 2500
          });
          this.$router.push("/mypage/qna/list");
        } else {
          this.$toasted.show("글 작성에 실패하였습니다", {
            type: "error",
            position: "top-right",
            duration: 2500
          });
        }
      });
    }
  }
};
</script>

<style>
.qna-main-title {
  color: #1564f9;
}
.qna-write-container {
  padding: 0 40px;

  color: #222222;
}
.qna-write-header-title {
  text-align: center;
  font-size: 22px;
}

.qna-write-content-container {
  margin-top: 60px;
  padding: 0 0;
  border-top: 1px solid #dddddddd;
  border-bottom: 1px solid #dddddddd;
}

.qna-write-type-select-wrapper {
  display: flex;
}
.qna-write-title-wrapper {
  display: flex;
}
.qna-write-content-wrapper {
  display: flex;
}

.qna-write-title {
  flex: 2;
  font-size: 15px;
  display: flex;
  align-items: center;
  justify-content: center;
}
.qna-write-content {
  flex: 8;
}
.qna-write-type-select-wrapper {
  display: flex;
  align-items: center;
  height: 80px;
}

.qna-write-select-option {
  border: 1px solid #dddddd;
  width: 100%;
  height: 46px;
  padding-left: 15px;
}

.qna-write-title-wrapper {
  display: flex;
  align-items: center;
  height: 80px;
  padding: 16px 0;
  border-top: 1px solid #dddddddd;
}
.qna-write-input {
  width: 100%;
  height: 46px;
  font-size: 15px;
  padding: 0 15px;
  border: 1px solid #dddddd;
}

.qna-write-content-wrapper {
  border-top: 1px solid #dddddddd;
  padding: 16px 0;
}
.qna-write-textarea {
  border: 1px solid #dddddd;
  resize: none;
  width: 100%;
  height: 260px;
  font-size: 15px;
  padding: 12px 15px;
}
.qna-write-button-wrapper {
  display: flex;
  align-items: center;
  justify-content: center;
  padding: 40px 0;
}
.qna-write-button {
  height: 50px;
  width: 218px;
  display: flex;
  align-items: center;
  justify-content: center;
  color: #fff;
  background-color: #1564f9;
  font-size: 15px;
  cursor: pointer;
}
.qna-write-button:hover {
  opacity: 0.5;
}
</style>