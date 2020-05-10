<template>
  <div class="content">
    <div class="md-layout">
      <div class="md-layout-item md-medium-size-100 md-xsmall-size-100 md-size-100">
        <!-- <edit-profile-form data-background-color="green"></edit-profile-form> -->
        <QnaForm data-background-color="green"></QnaForm>
      </div>
      <!-- <div class="md-layout-item md-medium-size-100 md-size-33">
        <user-card> </user-card>
      </div>-->
    </div>
  </div>
</template>

<script>
import { EditProfileForm, UserCard } from "@/pages";
import QnaForm from "../pages/UserProfile/QnaForm.vue";

export default {
  components: {
    QnaForm
    // EditProfileForm
    // UserCard
  }
};
</script>
