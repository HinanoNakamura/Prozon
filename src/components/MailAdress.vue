<template>
  <div id="app">
    <form @submit.prevent="updatemailAddress">
      <div id="current" style="text-align: center;">
      <p>★Current Address★</p>
      <p>{{ Mailsaddress }}</p>
    </div>
   
      <p>★Change mailaddress★</p> 
      <label class="mailpassword">Change mailaddress:</label>
      <input type="mailpassword" id="mailpasschange" v-model="mailpasschange">
      <br><br><br>

      <button :disabled="!isFormValid" class="button-74" type="submit">Change mailaddress</button>
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
import store from "../store/";

export default {
  name: 'MailAdress',
  data() {
    return {
      mailpasschange: '',
      Mailsaddress:''
    };
  },
  computed: {
    isFormValid() {
      return this.mailuseid !== '' && this.mailpasschange !== '';
    }
  },
  methods: {
    updatemailAddress() {

      Service.post('/api/updatemailadd', 
     
     {id: store.state.userId,
     mail: this.mailpasschange})

        .then(response => {
          console.log(response);
          alert('メールアドレスが変更されました');
          this.mailpasschange = '';

        this.loadmailAddress();
    }).catch(error => {
      console.log(error + "morimo-ri-");
    });
  },
  loadmailAddress() {
    const da = store.state.userId
        // データを取得する処理
        Service.get("/api/mailaddress/" + da)
        .then((response) => {
          this.Mailsaddress = response.data.mail;
        })
        .catch((error) => {
          console.log(error);
        });
      }
  },
  created() {
    const da = store.state.userId
        // データを取得する処理
        Service.get("/api/mailaddress/" + da)
        .then((response) => {
          this.Mailsaddress = response.data.mail;
        })
        .catch((error) => {
          console.log(error);
        });
      }
  
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
  