<template>
  <div id="app">
    <div id="current" style="text-align: center;">
      <p>★Current Address★</p>
      <p>{{ homeaddress }}</p>
    </div>
    <form @submit.prevent="updateHomeAddress">
      <br>
      <p>★Change Address★</p>
      <label for="homenumber1">Postal Code : 〒</label>
      <input type="tel" maxlength="3" size="4" id="homenumber1" v-model="homenumber1">
      <span>- </span>
      <input type="tel" maxlength="4" size="5" id="homenumber2" v-model="homenumber2">
      <button id="search" type="button" @click="search" class="button-74">Search</button>
      <p id="error" style="color:#e0548e;"></p>
      <br>
      <label for="new-home-address">New Address:</label>
      <input type="text" id="new-home-address" v-model="newHomeAddress" value="">
      <br><br><br>
      <button :disabled="!isFormValid" class="button-74" type="submit">Update Address</button>
    </form>
    <div class="back-home">
      <br>
      <button class="button-74" @click="$router.push('/')">Back</button>
      <router-view></router-view>
    </div>
    <p id="error" style="color:#e0548e;"></p>
  </div>
</template>

<script>
import { Service } from "@/service/service"
import fetchJsonp from 'fetch-jsonp'
import store from "../store/";


export default {
  name: 'HomeAddress',
  data() {
    return {
      newHomeAddress: '',
      homenumber1: '',
      homenumber2: '',
      homeaddress: ''
    };
  },
  computed: {
    isFormValid() {
      return this.userhomeid !== '' && this.newHomeAddress !== '' && this.homenumber1 !== '' && this.homenumber2 !== '';
    }
  },
  methods: {
    updateHomeAddress() {
    Service.post('/api/updatehomeadd', {
      id: store.state.userId,
      homeaddress: this.newHomeAddress,
      homenumber: this.homenumber1 + '-' + this.homenumber2
    }).then(response => {
      console.log(response);
      alert('住所が変更されました');
      this.userhomeid = '';
      this.newHomeAddress = '';
      this.homenumber1 = '';
      this.homenumber2 = '';
      this.homeaddress = response.data.homeaddress;
      
      // 新しい住所を読み込む
      this.loadHomeAddress();
    }).catch(error => {
      console.log(error + "morimo-ri-");
    });
  },
  loadHomeAddress() {
    const da = store.state.userId;
    Service.get("/api/homeaddress/" + da).then(response => {
      this.homeaddress = response.data.homeaddress;
    }).catch(error => {
      alert("era-");
      console.log(error);
    });
  },
  // 以下省略
    search() {
      let api = 'https://zipcloud.ibsnet.co.jp/api/search?zipcode=';
      let error = document.getElementById('error');
      let address = document.getElementById('new-home-address');
      let param = this.homenumber1 + this.homenumber2;
      let url = api + param;
      fetchJsonp(url)
        .then((response) => {
          error.textContent = ''; //HTML側のエラーメッセージ初期化
          return response.json();
        })
        .then((data) => {
          if (data.status === 400) { //エラー時
            error.textContent = "Please enter the correct format";
          } else if (data.results === null) {
            error.textContent = 'Address not found from zip code.';
          } else {
            address.value = data.results[0].address1 + data.results[0].address2 + data.results[0].address3;

          }
        })
        .catch((ex) => { //例外処理
          console.log(ex);
        });
    }
  },
  created() {
    const da = store.state.userId
        // データを取得する処理
        Service.get("/api/homeaddress/" + da)
        .then((response) => {
          this.homeaddress = response.data.homeaddress;
        })
        .catch((error) => {
          alert("era-")
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
  