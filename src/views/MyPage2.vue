<template>
    <div>
      <form>
        <div id="app">
            <p>🌟プロフィール画像の変更🌟</p>
            <button>プロフィール画像編集</button>
        </div>
        <br><br>
        <p>🌟メールアドレスの変更🌟</p>
        <label for="emailchange">メールアドレス変更:</label>
        <input type="emailchange" id="emailchange" v-model="emailchange">
        <br><br><br>

        <p>🌟パスワードの変更🌟</p>
        <label for="passchange">現在のパスワード:</label>
        <input type="passchange" id="passchange" v-model="passchange">
        <br><br>
        <label for="passchange">新しいパスワード:</label>
        <input type="passchange" id="passchange" v-model="passchange">
        <br><br>
        <label for="passchange">パスワード:</label>
        <input type="passchange" id="passchange" v-model="passchange">
      </form>
      
      <br>
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
        emailchange: '',
        passchange: ''
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
  