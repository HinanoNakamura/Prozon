
<template>
  <div class="font">
    <form>
      <div class="newfont">New registration</div>
      <br><br>
      <label for="username">User ID : </label>
      <input type="text" id="username" v-model="username" autocomplete="username"><br>
      <span v-if="isError" style="color: red;">{{ errorMessage }}</span>


      <br><br>
      <label for="password">Password : </label>
      <input type="password" id="password" v-model="password" autocomplete="new-password">
      <br>
      <label for="password-confirm">Confirm Password : </label>
      <input type="password" id="password-confirm" v-model="passwordConfirm" autocomplete="new-password"
        @input="checkPasswordMatch">


      <br>
      <span v-if="password !== passwordConfirm" style="color: red;">Passwords do not match</span>
      <br>
      <label for="mail">Mailaddress : </label>
      <input type="email" id="mail" v-model="mail" placeholder="aika@ria.com">
      <br><br>
      <label for="homenumber">Postal Code : 〒</label>
      <input type="tel" maxlength="3" size="4" id="homenumber1" v-model="homenumber1">
      <span>- </span>
      <input type="tel" maxlength="4" size="5" id="homenumber2" v-model="homenumber2">
      <button id="search" class="button-74" type="button" @click="search">Search</button>
      <p id="error" style="color:#e0548e;"></p>
      <label for="homeaddress">Address : </label>
      <input type="text" size="40" id="homeaddress" v-model="homeaddress" value="">
      <br><br>
      <label for="profileimage">Profile Image : </label>
      <ModalModal @imageCropped="handleImageCropped"/>
      <!-- <input type="file" id="profileimage" v-on:change="handleProfileImageUpload"> -->
      <br> 
      <img v-if="croppedsrc!== ''" :src="croppedsrc" alt="Cropped Image" style="border-radius: 50%; height: 200px; width: 200px;">
      <!-- <img v-if="profileImageFile" :src="path" style="border-radius: 50%; height: 200px; width: 200px;" /> -->
      <br><br>
      <button :disabled="!isFormValid || isDataEmpty()"  type="button" @click="registerUser">registration</button>

    </form>
    
   

    <div class="back-home">
      <button v-if="$route.path !== '/'" class="button-74" @click="$router.push('/')">Home</button>
      <router-view></router-view>
    </div>


  </div>
</template>



<script>
import fetchJsonp from 'fetch-jsonp'
import { Service } from "@/service/service"
import store from "../store/";
import ModalModal from "@/components/Modal.vue";

export default {
  name: 'UserRegistration',
  components: {
    ModalModal,
  },
  data() {
    return {
      username: '',
      password: '',
      passwordConfirm: '',
      profileimage: '',
      isFormValid: false,
      profileImageFile: null,
      path: '',
      mail: '',
      homenumber1: '',
      homenumber2: '',
      homeaddress: '',
      croppedImage: '',
      croppedsrc:'',
      isError: false,
      errorMessage: ''
    };
  },
  methods: {
    handleProfileImageUpload(event) {
      this.profileImageFile = event.target.files[0];
      this.path = URL.createObjectURL(this.profileImageFile);
      if (this.profileImageFile) {
        console.log(this.profileImageFile);
      } else {
        console.log("天ぷら");
      }
    },
    registerUser() {
      const formData = new FormData();
      const postalcode = `${this.homenumber1}-${this.homenumber2}`;
      formData.append('id', this.username);
      formData.append('password', this.password);
      formData.append('icon', this.croppedImage);
      formData.append('mail', this.mail);
      formData.append('homenumber', postalcode);
      formData.append('homeaddress', this.homeaddress);
      Service.post('./api/users', formData, {
        headers: {
          'Content-Type': 'multipart/form-data'
        }
      })
        .then((response) => {
          console.log(response);
          store.commit('SETID', this.username);
          store.commit('SETLOG', true)
          alert(store.state.loggin)
          alert(store.state.userId)
          this.$router.push('/');
        })
        .catch((error) => {
          console.log("むり"+error);
          this.isError = true;
          this.errorMessage = 'ID is already registered';
        });
    },
    isDataEmpty() {
    return (
      this.username === '' ||
      this.password === '' ||
      this.passwordConfirm === '' ||
      this.mail === '' ||
      this.homenumber1 === '' ||
      this.homenumber2 === '' ||
      this.homeaddress === ''
    );
    },
    checkPasswordMatch() {
      this.isFormValid = this.password === this.passwordConfirm;
    },
    search() {
      let api = 'https://zipcloud.ibsnet.co.jp/api/search?zipcode=';
      let error = document.getElementById('error');
      let address = document.getElementById('homeaddress');
      let param = this.homenumber1 + this.homenumber2;
      let url = api + param;
      fetchJsonp(url)
        .then((response) => {
          error.textContent = ''; //HTML側のエラーメッセージ初期化
          return response.json();
        })
        .then((data) => {
          if (data.status === 400) { //エラー時
            error.textContent = 'dame';
          } else if (data.results === null) {
            error.textContent = 'Address not found from zip code.';
          } else {
            address.value = data.results[0].address1 + data.results[0].address2 + data.results[0].address3;

          }
        })
        .catch((ex) => { //例外処理
          console.log(ex);
        });
    },
    handleImageCropped(image) {
      this.croppedsrc = image;
  // 画像データをBase64からBlobに変換
  const byteCharacters = atob(image.split(',')[1]);
  const byteArrays = [];
  for (let i = 0; i < byteCharacters.length; i++) {
    byteArrays.push(byteCharacters.charCodeAt(i));
  }
  const blob = new Blob([new Uint8Array(byteArrays)], { type: 'image/jpeg' });

  // BlobオブジェクトをFileオブジェクトに変換
  const file = new File([blob], 'croppedImage.jpg', { type: 'image/jpeg' });

  this.croppedImage = file;

    }
  }
};
</script>


  
<style>
@import url('https://fonts.googleapis.com/css2?family=Caveat&display=swap');

form {
  text-align: center;
}

.back-home button {
  text-align: center;
  border-radius: 100vh;

}

.font {
  font-family: 'Caveat', cursive;
}

.newfont {
  font-size: 30px;

}
ul {
  list-style-type: none;
}
</style>
  