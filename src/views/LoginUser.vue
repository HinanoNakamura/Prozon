<template>
    <div>

      <form>
        <div class ="newfont">ログイン</div>
        <br><br>
        <label for="username">ユーザーID:</label>
        <input type="text" id="username" v-model="username">
        <br><br>
        <label for="email">メールアドレス:</label>
        <input type="email" id="email" v-model="email">
        <div id="app">
            <button>ログイン</button>
        </div>

        <br>
        <div class="flex">
          <router-link to="/register">登録がまだの方はこちら 新規登録</router-link>
      </div>
      </form>
  
      <div class="back-home">
    <button v-if="$route.path !== '/'" @click="$router.push('/')">ホーム画面に戻る</button>
    <router-view></router-view>
  </div>
    </div>
  </template>
  
  <script>
  export default {
    data() {
      return {
        username: '',
        email: ''
      }
    },
    methods: {
      submitForm() {
        // フォームの送信処理を実装する
      }
    }
  }
  </script>
  
  <style>
  form {
    text-align: center;
  }
  
  .back-home {
    text-align: center;
  }

  .newfont {
    font-size: 30px;
  }
  </style>
  