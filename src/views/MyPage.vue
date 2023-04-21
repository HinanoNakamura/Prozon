<template>
    <div>

      <form>
        <div id="app">
            <button>プロフィールの編集</button>
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
  