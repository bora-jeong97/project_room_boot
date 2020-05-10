<template>
  <md-card>
    <md-card-header :data-background-color="dataBackgroundColor">
      <h4 class="title">Q & A</h4>
      <p class="category">문의게시판</p>
    </md-card-header>

    <md-card-content>
      <QnaTable></QnaTable>
    </md-card-content>
  </md-card>
</template>
<script>
import QnaTable from "../../components/Tables/QnaTable";
export default {
  name: "edit-profile-form",
  props: {
    dataBackgroundColor: {
      type: String,
      default: ""
    }
  },
  components: {
    QnaTable
  },
  data() {
    return {
      username: null,
      disabled: null,
      emailadress: null,
      lastname: null,
      firstname: null,
      address: null,
      city: null,
      country: null,
      code: null,
      aboutme:
        "Lamborghini Mercy, Your chick she so thirsty, I'm in that two seat Lambo."
    };
  }
};
</script>
<style></style>
