<template>
  <div id="aboutview" style="align: center;">
    <div>
      <h1>Recommended gacha</h1>
      <button class="button-74" @click="search()">Push</button>
    </div>
    <ul v-for="result in results" :key="result.id">
      <h1>❤︎ITIOSHI❤︎</h1>
      <h2>{{ "名前：" + result.name }} </h2>
      <h2>{{ "価格:" + result.price }}</h2>
      <h2>{{result.detail }}</h2>

      <div class="flexitem">
        <img v-bind:src="'/assets' + result.img" alt="result.img">
      </div>
    </ul>
    <br><br><br>
    <div class="back-home">
      <button class="button-74" v-if="$route.path !== '/'" @click="$router.push('/')">Home</button>
      <router-view></router-view>
    </div>
  </div>
</template>

<script>
import { Service } from "@/service/service"

export default {
  name: 'AboutView',
  data() {
    return {
      testesId: null,
      // result:"",
      results: [],
      name: '',
      pass: '',
      isValidName: true,
      isValidPass: true
    }
  },
  methods: {
    search() {
      Service.get("/users").then(response => {
        if (!response.data) {
          this.result = "NO DATA"
        } else {
          this.results = response.data
          // this.$router.push('/users')
        }
      })
    },

    submitForm() {
      this.isValidName = this.name !== '';
      this.isValidPass = this.pass !== '';
      if (!this.isValidName || !this.isValidPass) {
        // エラーメッセージを表示して、送信を中止する
        return;
      }

      alert('本当にいいの？')
    }
  }
}

</script>

<style>
@import url('https://fonts.googleapis.com/css2?family=Caveat&display=swap');

#aboutview {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
  font-family: 'Caveat', cursive;
}

body {
  max-width: 1800px;
  margin: auto;
}

.プロテインランキング {
  text-align: center;
}

.flexbox {
  display: flex;
  justify-content: center;
  flex-flow: row wrap;
}
</style>