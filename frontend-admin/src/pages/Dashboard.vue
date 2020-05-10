<template>
  <div class="content">
    <div class="md-layout">
      <div class="md-layout-item md-medium-size-100 md-xsmall-size-100 md-size-33">
        <chart-card
          :chart-data="dailySalesChart.data"
          :chart-options="dailySalesChart.options"
          :chart-type="'Line'"
          data-background-color="green"
        >
          <template slot="content">
            <h4 class="title">요일별 수익</h4>
            <p class="category">
              <span class="text-success">
                <!-- <i class="fas fa-long-arrow-alt-up"></i> 55% -->
              </span>
              요일별 수익
            </p>
          </template>

          <template slot="footer">
            <div class="stats">
              <md-icon>access_time</md-icon>today
            </div>
          </template>
        </chart-card>
      </div>
      <div class="md-layout-item md-medium-size-100 md-xsmall-size-100 md-size-33">
        <chart-card
          :chart-data="emailsSubscriptionChart.data"
          :chart-options="emailsSubscriptionChart.options"
          :chart-type="'Bar'"
          data-background-color="blue"
        >
          <template slot="content">
            <h4 class="title">일주일간 올라온 방의 수</h4>
            <p class="category">일주일간 게시된 방의 갯수</p>
          </template>

          <template slot="footer">
            <div class="stats">
              <md-icon>access_time</md-icon>today
            </div>
          </template>
        </chart-card>
      </div>
      <div class="md-layout-item md-medium-size-100 md-xsmall-size-100 md-size-33">
        <chart-card
          :chart-data="dataCompletedTasksChart.data"
          :chart-options="dataCompletedTasksChart.options"
          :chart-type="'Bar'"
          data-background-color="green"
        >
          <template slot="content">
            <h4 class="title">VIP 공인중개사</h4>
            <p class="category">최다 충전</p>
          </template>

          <template slot="footer">
            <div class="stats">
              <md-icon>access_time</md-icon>today
            </div>
          </template>
        </chart-card>
      </div>
      <div class="md-layout-item md-medium-size-50 md-xsmall-size-100 md-size-25">
        <stats-card data-background-color="green">
          <template slot="header">
            <md-icon>store</md-icon>
          </template>

          <template slot="content">
            <p class="category">전체 방의 개수</p>
            <h3 class="title">
              <small>{{roomCount}}개</small>
            </h3>
          </template>

          <template slot="footer">
            <div class="stats">
              <md-icon>access_time</md-icon>today
            </div>
          </template>
        </stats-card>
      </div>
      <div class="md-layout-item md-medium-size-50 md-xsmall-size-100 md-size-25">
        <stats-card data-background-color="orange">
          <template slot="header">
            <md-icon>face</md-icon>
          </template>

          <template slot="content">
            <p class="category">유저/공인중개사</p>
            <h3 class="title">
              <!-- 유저/브로커 -->
              <small>{{userCount}}/{{brokerCount}}</small>
            </h3>
          </template>

          <template slot="footer">
            <div class="stats">
              <md-icon>access_time</md-icon>today
            </div>
          </template>
        </stats-card>
      </div>
      <div class="md-layout-item md-medium-size-50 md-xsmall-size-100 md-size-25">
        <stats-card data-background-color="red">
          <template slot="header">
            <md-icon>attach_money</md-icon>
          </template>

          <template slot="content">
            <p class="category">일간 수익</p>
            <h3 class="title">
              <small>{{todayRevenue}}원</small>
            </h3>
          </template>

          <template slot="footer">
            <div class="stats">
              <md-icon>access_time</md-icon>today
            </div>
          </template>
        </stats-card>
      </div>
      <div class="md-layout-item md-medium-size-50 md-xsmall-size-100 md-size-25">
        <stats-card data-background-color="green">
          <template slot="header">
            <md-icon>attach_money</md-icon>
          </template>

          <template slot="content">
            <p class="category">누적 수익</p>
            <h3 class="title">
              <small>{{allRevenue}}원</small>
            </h3>
          </template>

          <template slot="footer">
            <div class="stats">
              <md-icon>update</md-icon>today
            </div>
          </template>
        </stats-card>
      </div>
      <div class="md-layout-item md-medium-size-100 md-xsmall-size-100 md-size-50">
        <md-card>
          <md-card-header data-background-color="green">
            <h4 class="title">일반 회원</h4>
            <p class="category"></p>
          </md-card-header>
          <md-card-content>
            <simple-table table-header-color="green">
              <div slot="button-delete"></div>
              <div slot="auth-table"></div>
            </simple-table>
          </md-card-content>
        </md-card>
      </div>
      <div class="md-layout-item md-medium-size-100 md-xsmall-size-100 md-size-50">
        <md-card>
          <md-card-header data-background-color="orange">
            <h4 class="title">공인중개사 회원</h4>
            <p class="category"></p>
          </md-card-header>
          <md-card-content>
            <ordered-table table-header-color="orange">
              <div slot="button-delete"></div>
              <div slot="auth-table"></div>
            </ordered-table>
          </md-card-content>
        </md-card>
      </div>
    </div>
  </div>
</template>

<script>
import {
  StatsCard,
  ChartCard,
  NavTabsCard,
  NavTabsTable,
  OrderedTable,
  SimpleTable
} from "@/components";
import { request } from "../util/axios";

export default {
  components: {
    StatsCard,
    ChartCard,
    SimpleTable,
    OrderedTable
  },

  data() {
    return {
      dailySalesChart: {
        data: {
          labels: ["월", "화", "수", "목", "금", "토", "일"],
          series: [[230, 750, 450, 300, 280, 240, 200]]
        },

        options: {
          lineSmooth: this.$Chartist.Interpolation.cardinal({
            tension: 0
          }),
          low: 0,
          high: 500, // creative tim: we recommend you to set the high sa the biggest value + something for a better look
          chartPadding: {
            top: 0,
            right: 0,
            bottom: 0,
            left: 0
          }
        }
      },
      dataCompletedTasksChart: {
        data: {
          labels: ["1", "2", "3"],
          series: [[23, 75, 45]]
        },

        options: {
          lineSmooth: this.$Chartist.Interpolation.cardinal({
            tension: 0
          }),
          low: 0,
          high: 500, // creative tim: we recommend you to set the high sa the biggest value + something for a better look
          chartPadding: {
            top: 0,
            right: 0,
            bottom: 0,
            left: 0
          }
        }
      },
      // dataCompletedTasksChart: {
      //   data: {
      //     labels: ["12am", "3pm", "6pm", "1am", "2am", "3am", "5am"],
      //     series: [[0, 0, 0, 0, 0, 0, 0, 0]]
      //   },

      //   options: {
      //     lineSmooth: this.$Chartist.Interpolation.cardinal({
      //       tension: 0
      //     }),
      //     low: 0,
      //     high: 100, // creative tim: we recommend you to set the high sa the biggest value + something for a better look
      //     chartPadding: {
      //       top: 0,
      //       right: 0,
      //       bottom: 0,
      //       left: 0
      //     }
      //   }
      // },
      emailsSubscriptionChart: {
        data: {
          labels: ["7일", "6일", "5일", "4일", "3일", "2일", "어제", "오늘"],
          series: [[0, 0, 0, 0, 0, 0, 0, 0]]
        },
        options: {
          axisX: {
            showGrid: true
          },
          low: 0,
          high: 30,
          chartPadding: {
            top: 0,
            right: 5,
            bottom: 0,
            left: 0
          }
        },
        responsiveOptions: [
          [
            "screen and (max-width: 640px)",
            {
              seriesBarDistance: 5,
              axisX: {
                labelInterpolationFnc: function(value) {
                  return value[0];
                }
              }
            }
          ]
        ]
      },
      roomCount: 0,
      userCount: 0,
      brokerCount: 0,
      todayRevenue: 0,
      allRevenue: 0
    };
  },
  created() {
    this.fetchRoomCount();
  },
  mounted() {
    this.fetchRoomCount();
  },
  methods: {
    fetchRoomCount() {
      // http://localhost:8081/api/admin/getAll
      request("get", "admin/getAll").then(res => {
        console.log(res[0]);
        //일주일간 올라온 방의수
        let arr = [
          [
            res[0].day_of_room_list[0].timestampdiff6,
            res[0].day_of_room_list[0].timestampdiff5,
            res[0].day_of_room_list[0].timestampdiff4,
            res[0].day_of_room_list[0].timestampdiff3,
            res[0].day_of_room_list[0].timestampdiff2,
            res[0].day_of_room_list[0].timestampdiff1,
            res[0].day_of_room_list[0].timestampdiff0
          ]
        ];
        this.emailsSubscriptionChart.data.series = arr;

        let arr1 = [
          [
            res[0].day_of_month[0].payment / 10000,
            res[0].day_of_month[1].payment / 10000,
            res[0].day_of_month[2].payment / 10000,
            res[0].day_of_month[3].payment / 10000,
            res[0].day_of_month[4].payment / 10000,
            res[0].day_of_month[5].payment / 10000,
            res[0].day_of_month[6].payment / 10000
          ]
        ];
        console.log(arr1);

        this.dailySalesChart.data.series = arr1;

        let arr2 = [
          [
            res[0].vip_list[0].payment / 10000,
            res[0].vip_list[1].payment / 10000,
            res[0].vip_list[2].payment / 10000
          ]
        ];
        console.log("aar2");
        console.log(arr2);
        this.dataCompletedTasksChart.data.series = arr2;

        let arr3 = [
          res[0].vip_list[0].id,
          res[0].vip_list[1].id,
          res[0].vip_list[2].id
        ];
        // this.dataCompletedTasksChart.data.labels = arr3;
        console.log(res[0].day_of_room_list);
        console.log(this.emailsSubscriptionChart.data.series);
        this.roomCount = res[0].room_count;
        this.userCount = res[0].user_count;
        this.brokerCount = res[0].broker_count;
        this.todayRevenue = res[0].today_revenue ? res[0].today_revenue : 0;
        this.allRevenue = res[0].all_revenue;
      });
      // request("get", "admin/getRoomCount").then(res => {
      //   // console.log(res);
      //   let arr = [
      //     [
      //       res.timestampdiff6,
      //       res.timestampdiff5,
      //       res.timestampdiff4,
      //       res.timestampdiff3,
      //       res.timestampdiff2,
      //       res.timestampdiff1,
      //       res.timestampdiff0
      //     ]
      //   ];
      //   this.emailsSubscriptionChart.data.series = arr;
      // });
    }
  }
};
</script>
