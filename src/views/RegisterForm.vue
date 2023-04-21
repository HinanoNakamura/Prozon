<template>
  <div>
    <form @submit.prevent="registerUser">
      <div class ="newfont">新規登録</div>
      <br><br>
      <label for="username">ユーザーID:</label>
      <input type="text" id="username" v-model="username">
      <br><br>
      <label for="password">Password:</label>
      <input type="password" id="password" v-model="password">
      <br>
      <label for="password-confirm">Confirm Password:</label>
      <input type="password" id="password-confirm" v-model="passwordConfirm" @input="checkPasswordMatch">
      <br>
      <span v-if="password !== passwordConfirm" style="color: red;">Passwords do not match</span>
      <br><br>
      <button :disabled="!isFormValid">登録</button>
    </form>
 
<div class="back-home">
    <button v-if="$route.path !== '/'" @click="$router.push('/')">ホーム画面に戻る</button>
    <router-view></router-view>
  </div>
</div>

  </template>



<script>
import {Service} from "@/store/index.js"
export default {
  name: 'UserRegistration',
  data() {
    return {
      username: '',
      password: '',
      passwordConfirm: '',
      isFormValid: false,
    };
  },
  methods: {
    registerUser() {
      const userData = {
        id: this.username,
        password: this.password,
        icon: '青りんご',
      };
      Service.post('/api/users', userData)
        .then((response) => {
          console.log(response);
        })
        .catch((error) => {
          console.log(error);
        });
    },
    checkPasswordMatch() {
      this.isFormValid = this.password === this.passwordConfirm;
    },
  }
};
</script>


  
  <style>
  form {
    text-align: center;
  }
  
  .back-home button{
  text-align: center;
  border-radius: 100vh;

  }

  .newfont {
    font-size: 30px;
  }

  
  </style>
  