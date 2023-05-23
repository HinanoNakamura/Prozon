<template>
  <div id="app">
    <form @submit.prevent="updatePassword">
      <p>★Change Password★</p>
      <label class="password">Current Password:</label>
      <input type="password" id="passchange" v-model="passchange">
      <br><br><br>

      <label for="password">New Password:</label>
      <input type="password" id="password" v-model="password">
      <br><br>

      <label for="password-confirm">Confirm Password:</label>
      <input type="password" id="password-confirm" v-model="passwordConfirm" @input="checkPasswordMatch">
      <br>
      <span v-if="password !== passwordConfirm" style="color: red;">Passwords do not match</span>
      <br><br>

      <button :disabled="!isFormValid" class="button-74" type="submit">Update Password</button>
    </form>

    <div class="back-home">
      <br>
      <button class="button-74" @click="$router.push('/')">Back</button>
      <router-view></router-view>
    </div>

  </div>
</template>



<script>
import { Service } from "@/service/service"
import store from "@/store";

export default {
  name: 'UserRegistration',
  data() {
    return {
      passchange: '',
      password: '',
      passwordConfirm: '',
      isFormValid: false,
    };
  },
  methods: {
    updatePassword() {
      Service.post("/api/updatepassword", {

        userid: store.state.userId,
        password: this.passchange,
        newpassword: this.passwordConfirm,

      })
        .then((response) => {
          alert("パスワードが変更されました")
          console.log(response);
          this.useid = ''
          this.passchange = ''
          this.password = ''
          this.passwordConfirm = ''
          this.isFormValid = false
        })
        .catch((error) => {
          console.log(error);
        });
    },
    checkPasswordMatch() {
      this.isFormValid = this.password === this.passwordConfirm;
    },
  },
};
</script>


  
<style>
form {
  text-align: center;
}

.back-home button {
  text-align: center;
  border-radius: 100vh;

}

.newfont {
  font-size: 30px;
}
</style>
  