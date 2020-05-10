<template>
  <div class="profile-container">
    <div class="image-upload-wrapper">
      <div class="payment-title">
        글 쓰기 가능한 횟수:
        <span class="remain-write-count-title">{{ loginData.write_count }}회</span>
      </div>
      <notifications
        group="notifyApp"
        position="top right"
        style="margin-top:20vh; margin-right: 30vh;"
      />
      <div class="payment-button-container">
        <div class="payment-button-wrapper">
          <img src="../../assets/living.png" />
          <div class="payment-button-title">글쓰기 패키지</div>
          <div class="payment-button-count">10회</div>
          <div class="payment-button-cost">100,000원</div>
          <div class="payment-button" @click="requestPay(100000)">
            <div class="payment">충전하기</div>
          </div>
        </div>
        <div class="payment-button-wrapper">
          <img src="../../assets/living.png" />
          <div class="payment-button-title">글쓰기 패키지</div>
          <div class="payment-button-count">
            22회
            <span class="count-sub">(2회 보너스)</span>
          </div>
          <div class="payment-button-cost">200,000원</div>
          <div class="payment-button" @click="requestPay(200000)">
            <div class="payment">충전하기</div>
          </div>
        </div>
        <div class="payment-button-wrapper">
          <img src="../../assets/living.png" />
          <div class="payment-button-title">글쓰기 패키지</div>
          <div class="payment-button-count">
            35회
            <span class="count-sub">(5회 보너스)</span>
          </div>
          <div class="payment-button-cost">300,000원</div>
          <div class="payment-button" @click="requestPay(300000)">
            <div class="payment">충전하기</div>
          </div>
        </div>
      </div>
    </div>

    <div v-if="tableData[0]" class="profile-information-wrapper">
      <div class="profile-payment-table-title">결제 내역 조회</div>
      <div class="profile-payment-table-container">
        <el-table :data="tableData" style="width: 100%; height:100%;">
          <el-table-column
            type="payment_type"
            label="결제 타입"
            width="180"
            header-align="center"
            align="center"
          ></el-table-column>
          <el-table-column
            prop="payment"
            label="결제 금액"
            width="180"
            header-align="center"
            align="center"
          ></el-table-column>
          <el-table-column prop="payment_at" label="날짜" header-align="center" align="center"></el-table-column>
        </el-table>
      </div>
      <el-pagination
        class="profile-payment-table-page"
        background
        layout="prev, pager, next"
        :page-size="3"
        :total="payCount"
        :current-page.sync="currentPage"
        @current-change="getPaymentList"
      ></el-pagination>
    </div>
  </div>
</template>

<script>
//axios를 모듈화해서 가져온다
import { request, requestFile, requestParams } from "../../util/axios";
import { mapState, mapMutations } from "vuex";
import { error, success } from "../../util/notification";
export default {
  data() {
    return {
      name: "",
      userId: "",
      file: "",
      phoneNumber: "",
      currentPage: 1,
      payCount: 10,
      tableData: [
        {
          date: "2016-05-03",
          name: "Tom",
          address: "No. 189, Grove St, Los Angeles"
        },
        {
          date: "2016-05-02",
          name: "Tom",
          address: "No. 189, Grove St, Los Angeles"
        },
        {
          date: "2016-05-04",
          name: "Tom",
          address: "No. 189, Grove St, Los Angeles"
        },
        {
          date: "2016-05-01",
          name: "Tom",
          address: "No. 189, Grove St, Los Angeles"
        },
        {
          date: "2016-05-01",
          name: "Tom",
          address: "No. 189, Grove St, Los Angeles"
        }
      ]
    };
  },
  mounted() {
    this.updateBrokerInformation();
    this.name = this.loginData.name;

    if (this.user_id) {
      this.userId = this.loginData.user_id;
    } else {
      this.userId = this.loginData.broker_id;
    }

    this.phoneNumber = this.loginData.phone_number;

    this.getPaymentList();
  },
  computed: {
    ...mapState(["loginData", "profileImage"])
  },
  methods: {
    ...mapMutations(["SET_LOGIN", "SET_PROFILE_IMAGE"]),
    getPaymentList() {
      requestParams("get", "payment/getPaymentBrokerCount/", {
        id: this.loginData.id
      }).then(res => {
        //결제 내역의 수가 리턴된다
        this.payCount = res;
        requestParams("get", "payment/getPaymentBrokerList/", {
          id: this.loginData.id,
          start: (this.currentPage - 1) * 3
        }).then(res => {
          console.log(res);
          this.tableData = res;
        });
      });
    },
    // 아임포트 모듈 사용하기
    requestPay(amount) {
      IMP.request_pay(
        {
          // param
          pg: "inicis",
          pay_method: "card",
          merchant_uid: "merchant_" + new Date().getTime(),
          name: "구해방 글쓰기 패키지",
          amount: amount,
          buyer_email: this.loginData.broker_id,
          buyer_name: this.loginData.name,
          buyer_tel: this.loginData.phone_number,
          buyer_addr: this.loginData.address,
          buyer_postcode: "01181"
        },
        rsp => {
          // callback
          if (rsp.success) {
            console.log("s");
            requestParams("get", "payment/payments", {
              payment: amount,
              brokerId: this.loginData.id,
              id: this.loginData.id
            }).then(res => {
              success("결제에 성공하였습니다", this);
              this.getPaymentList();
              this.updateBrokerInformation();
            });
          } else {
            msg = "결제에 실패하였습니다.";
            // msg += '에러내용 : ' + rsp.error_msg;
            error(msg, this);

            //실패!
            // ...,
            // // 결제 실패 시 로직,
            // ...
          }
        }
      );
    },
    // session및 vuex 갱신하기
    updateBrokerInformation() {
      let params = new URLSearchParams();
      params.append("id", this.loginData.id);
      request("POST", "broker/getInformation", params)
        .then(data => {
          if (data !== "") {
            sessionStorage.setItem("login", JSON.stringify(data));
            this.SET_LOGIN(data);
          }
        })
        .catch(error => {
          console.log(error);
        });
    }
  }
};
</script>

<style>
.profile-container {
  margin: 0 auto;
  width: 850px;
}

.image-profile {
  margin: 0 auto 12px auto;
  width: 76px;
  height: 76px;
  border-radius: 50%;
  background-color: beige;
  border: 1px solid #dddddd;
}

.image-profile > img {
  width: 100%;
  height: 100%;
  border-radius: 50%;
}

.profile-information-wrapper {
  margin-top: 20px;
  padding: 50px 0;
  border-top: 1px solid #dddddd;
  border-bottom: 1px solid #dddddd;
}
.profile-wrapper:not(:last-child) {
  margin-bottom: 30px;
}

.profile-title {
  margin-left: 200px;
  margin-bottom: 8px;
  color: #222222;
  text-align: left;
  font-size: 14px;
}
.profile-input {
  padding-left: 15px;
  margin-left: 200px;
  width: 450px;
  height: 44px;
}
.profile-input-phone:first-child {
  margin-left: 200px;
  width: 450px;
}

.profile-input-phone {
  width: 140px;
  height: 44px;
  padding: 10px;
  text-align: center;
}
.profile-phone-divider {
  display: inline-block;
  color: #666666;
  font-size: 14px;
  margin: 0 5px;
}
.profile-button-container {
  margin-top: 50px;
  text-align: center;
  color: #222222;
}

.profile-button-wrapper {
  display: flex;
  justify-content: center;
}

.profile-cancel-button {
  border: none;
  border: 1px solid #dddddd;
  width: 218px;
  height: 50px;
  font-size: 15px;
  cursor: pointer;
}
.profile-apply-button {
  margin-left: 20px;
  border: none;
  border: 1px solid #dddddd;
  width: 218px;
  height: 50px;
  color: #fff;
  background-color: #1564f9;
  font-size: 15px;
  cursor: pointer;
}

.secession-button {
  border: none;
  margin-top: 50px;
  font-size: 14px;
  color: #666666;
  text-decoration: underline;
  padding-bottom: 80px;
}

.filebox {
  margin-top: 60px;
  padding-bottom: 20px;
  text-align: center;
}

.filebox input[type="file"] {
  position: absolute;
  width: 0;
  height: 0;
  padding: 0;
  overflow: hidden;
  border: 0;
}

.filebox label {
  display: inline-block;
  padding: 10px 20px;
  color: #1564f9;
  vertical-align: middle;
  font-size: 15px;
  cursor: pointer;
  border-radius: 5px;
}
.remain-write-count-title {
  margin-left: 15px;
  font-size: 27px;
}
.payment-title {
  margin-top: 60px;
  color: #222222;
  display: flex;
  align-items: center;
  justify-content: center;
}
.payment-button-container {
  margin-top: 50px;
  color: #222222;
  text-align: center;
  display: flex;
  align-items: center;
  justify-content: center;
  padding-bottom: 50px;
}

.payment-button-container > div {
  width: 270px;
  border: 2px solid #ffff;
  padding: 30px 20px;
  box-shadow: rgba(188, 196, 219, 0.77) 5px 5px 13px 0px;
}
.payment-button-container > div:hover {
  border: 2px solid rgb(148, 179, 255);
  box-shadow: rgba(188, 196, 219, 0.77) 5px 5px 13px 0px;
}

.payment-button-container > div:not(:first-child) {
  margin-left: 30px;
}

.payment-button-wrapper > img {
  width: 60px;
}

.payment-button-title {
  margin-top: 10px;
  font-size: 22px;
  font-weight: bold;
}

.payment-button-count {
  font-size: 17px;
  margin-top: 10px;
  padding-bottom: 10px;
  border-bottom: 1px solid #dddddd;
}
.payment-button-cost {
  margin-top: 10px;
  color: #1564f9;
  font-weight: bold;
  font-size: 30px;
}

.payment-button {
  margin-top: 15px;
  display: flex;
  align-items: center;
  justify-content: center;
}

.payment {
  width: 120px;
  height: 40px;
  background-color: #1564f9;
  line-height: 40px;
  font-size: 16px;
  color: #fff;
  font-weight: bold;
  cursor: pointer;
}

.count-sub {
  font-size: 13px;
}

.payment:hover {
  opacity: 0.5;
}

.pagination {
  color: #222222;
}
.page-item {
  color: #222222;
}

.profile-payment-table-title {
  color: #222222;
  text-align: center;
  font-size: 27px;
}

.profile-payment-table-container {
  margin-top: 30px;
  box-shadow: rgba(188, 196, 219, 0.77) 5px 5px 13px 0px;

  height: 200px;
}

.profile-payment-table-page {
  margin-top: 30px;
}

.el-pagination.is-background .el-pager li:not(.disabled).active {
  background-color: #1564f9;
}
</style>
