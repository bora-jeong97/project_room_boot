<template>
  <md-card>
    <md-card-header :data-background-color="dataBackgroundColor">
      <h4 class="title">결제 내역 조회</h4>
      <p class="category">결제 내역 조회</p>
    </md-card-header>

    <md-card-content>
      <PayTable></PayTable>
    </md-card-content>
  </md-card>
</template>
<script>
import PayTable from "../../components/Tables/PayTable";
export default {
  name: "edit-profile-form",
  props: {
    dataBackgroundColor: {
      type: String,
      default: ""
    }
  },
  components: {
    PayTable
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
