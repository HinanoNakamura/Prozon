<template>
  <div>

    <!-- <form> -->
      <div id="app" style="text-align: center;">
      <button :class="{'active': activeTab === 'password'}" class="button-1" @click="activeTab = 'password'">☆Change passwordConfirm</button>
      <button :class="{'active': activeTab === 'email'}" class="button-1" @click="activeTab = 'email'">☆change e-mail address</button>
      <button :class="{'active': activeTab === 'address'}" class="button-1" @click="activeTab = 'address'">☆change address</button>
      <button :class="{'active': activeTab === 'Acmana'}" class="button-1" @click="activeTab = 'Acmana'">☆Account Management</button>
    </div>
      <!-- <br><br>
      <button class="button-1" @click="logout">Log out</button> -->

    <div v-show="activeTab === 'password'"> <!-- パスワードの変更フォームを表示 -->
      <UserRegistration />
    </div>

    <div v-show="activeTab === 'email'"> <!-- パスワードの変更フォームを表示 -->
      <MailAdress />
    </div>

    <div v-show="activeTab === 'address'"> <!-- パスワードの変更フォームを表示 -->
      <HomeAdress/>
    </div>

    <div v-show="activeTab === 'Acmana'"> <!-- パスワードの変更フォームを表示 -->
      <AccountManager/>
    </div>
      
    <!-- <div class="back-home">
      <br>
      <button class="button-74" v-if="$route.path !== '/'" @click="$router.push('/')"> Home</button>
      <router-view></router-view>
    </div> -->
  </div>
</template>
  
<script>
import UserRegistration from '@/components/ChangePassword.vue'
import HomeAdress from '@/components/HomeAdress.vue'
import MailAdress from '@/components/MailAdress.vue'
import AccountManager from '@/components/AccountManagement.vue'

export default {
  name: 'SearchResults',
  components: {
   UserRegistration,
   HomeAdress,
   MailAdress,
   AccountManager
  },
  data() {
    return {
      activeTab: 'password', // 初期表示タブ
      password: '',
      address: '',
      username: '',
      email: '',
      Acmana:''
    }
  },
  methods: {
    submitForm() {
      // フォームの送信処理を実装する
    },
    changePassword() {
      // パスワード変更処理
    },
    changeEmail() {
      // メールアドレス変更処理
    },
    changeAddress() {
      // 住所変更処理
    }
  }
}
</script>


  
<style>
form {
  text-align: center;
}

.profile-image {
    width: 150px;
    height: 150px;
    border-radius: 50%;
    object-fit: cover;
  }
  
.back-home {
  text-align: center;
}

.newfont {
  font-size: 30px;
}

@import url('https://fonts.googleapis.com/css2?family=Caveat&display=swap');

.button-1 {
  background-color: transparent;
  border: none;
  font-family: 'Caveat', cursive;
  font-size: 20px;
}

.active {
  border: 2px solid rgb(243, 227, 216); /* 線のスタイル */
}

</style>
  