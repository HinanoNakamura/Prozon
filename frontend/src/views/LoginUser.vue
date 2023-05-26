<template>
  <div>
    <form>

      <div class="newfont">LOGGIN</div>
      <br>
      <br><br>
      <div class="hina">
        <label for="username">UserId:</label>
        <input type="text" id="username" v-model="username">
        <div v-if="!isValidName" class="error-message">Name is required.</div>
        <br><br>
        <label for="password">Password:</label>
        <input type="password" id="password" v-model="password">
        <div v-if="!isValidPass" class="error-message">Please enter your password.</div>
        <div id="app">
          <button class="button-74" @click="login($event)">Loggin</button>
        </div>
        <p>{{ loginMsg }}</p>
        <br>
        <div class="flex">
          <a>Don't have an account?  </a>
          <router-link to="/register">register</router-link>
        </div>
      </div>
    </form>
    <div class="back-home">
      <button class="button-74" v-if="$route.path !== '/'" @click="$router.push('/')">Home</button>
      <router-view></router-view>
    </div>
  </div>
</template>

<script>
import { Service } from "@/service/service"
import store from "../store/";
export default {
  data() {
    return {
      username: '',
      password: '',
      isValidName: true,
      isValidPass: true,
      loginMsg: '',
    }
  },
  methods: {
    login(event) {
      event.preventDefault();
      this.isValidName = this.username !== '';
      this.isValidPass = this.password !== '';
      if (!this.isValidName || !this.isValidPass) {
        // エラーメッセージを表示して、送信を中止する
        return;
      } else {
        Service.post('/login', {
          id: this.username,
          password: this.password
        })
          .then(response => {
            this.loginMsg = 'Login successful';
            console.log(response);
            store.commit('SETID', this.username);
            store.commit('SETLOG', true)
            localStorage.setItem('userId', this.username);
            localStorage.setItem('loggin', true);
            this.$router.push('/'); // ログイン成功後にホームページに遷移する
            console.log(response);
          })
          .catch(error => {
            this.loginMsg = 'wrong😩';
            console.log(error);
          });
      }
    }
  }
}


</script>

<style>
@import url('https://fonts.googleapis.com/css2?family=Caveat&display=swap');

form {
  text-align: center;
}

.back-home {
  text-align: center;
}

.hina {
  font-family: 'Caveat', cursive;
  font-size: 20px;
}

.newfont {
  font-size: 40px;
  font-family: 'Caveat', cursive;
}

.okini {
  color: red;
}

.error-message {
  color: rgb(243, 227, 216);
}</style>