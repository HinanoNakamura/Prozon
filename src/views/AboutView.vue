<template>


  <div id="app" style="align: center;">
    <div>
        <h1>💩君もプロテインマスターにならないか？💩</h1>
      <button
        @click="search()">押しちゃう？？</button>
    </div>
    <ul v-for="result in results" :key="result.id">
      <h1>💗おすすめプロテイン💗</h1>
      <h2>{{ "名前：" + result.name }} </h2>
      <h2>{{"価格:" + result.price }}</h2>
      <div class="flexitem">
        <img v-bind:src="'/assets'+result.img" alt="result.img">
    </div>
  </ul>
  <div class="back-home">
    <button v-if="$route.path !== '/'" @click="$router.push('/')">ホーム画面に戻る</button>
    <router-view></router-view>
  </div>
</div>
 
</template>

<script>
import {Service} from "@/store/index.js"

export default {
  name: 'AboutView',
  data(){
    return{
      testesId:null,
      // result:"",
      results:[],
      name: '',
      pass: '',
      isValidName: true,
      isValidPass: true
    }
  },
  methods:{
    search(){
      Service.get("/users").then(response => {
        if(!response.data){
          this.result="NO DATA"
        }else{
          this.results=response.data
          // this.$router.push('/users')
        }
      })
    },
    
    submitForm() {
    this.isValidName = this.name !== '';
    this.isValidPass = this.pass !=='';
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
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}

  body {
    max-width: 1800px;
    margin: auto;
  }

  .プロテインランキング{
    text-align: center;
  }

  .flexbox {
    display: flex;
    justify-content: center;
    flex-flow: row wrap;
  }
  .flexitem {
    flex: 0 0 24%;
    margin: 10px;
  }
  .flexitem img {
    display: block;
    width: 100%;
    height: 400px;
    object-fit: contain;
  }
</style>