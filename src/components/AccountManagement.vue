<template>
  <div id="app" style="text-align: center;">
      <!-- 画像のアップロードフォーム -->
      <!-- <div class="image-container">
        <img :src="items.icon" alt="Image" style="width: 200px; height: 200px; display: block; margin: 0 auto; border-radius: 50%;">
        <input type="file" id="profileimage" v-on:change="handleProfileImageUpload">
        <br><br>
      </div> -->
    <!-- 成功時に表示するメッセージの div 要素 -->

    <label for="profileimage">Profile Image : </label>
    <img :src="items.icon" alt="Image" style="width: 200px; height: 200px; display: block; margin: 0 auto; border-radius: 50%;">
    <br><br><br>
    <label for="profileimage">Change Profile Image : </label>
      <ModalModal @imageCropped="handleImageCropped"/>
      <!-- <input type="file" id="profileimage" v-on:change="handleProfileImageUpload"> -->
      <br> 
      <img v-if="croppedsrc!== ''" :src="croppedsrc" alt="Cropped Image" style="border-radius: 50%; height: 200px; width: 200px;">
      <!-- <img v-if="profileImageFile" :src="path" style="border-radius: 50%; height: 200px; width: 200px;" /> -->
      <br><br>
      <button class="button-74" @click="changeicon">Changeicon</button>

    <br><br><br>
    <button class="button-1" @click="logout">Logout</button>
  
      <br><br>
      <button class="button-1" @click="deleteaccount">Delete account</button>

    <div class="back-home">
      <br><br>
      <button class="button-74" @click="$router.push('/')">Back</button>
      <router-view></router-view>
    </div>
  </div>
</template>

<script>
import { Service } from "@/service/service";
import store from "../store/";
import ModalModal from "@/components/Modal.vue";

export default {
  name: "AccountManager",
  computed: {
  getImageURL() {
    return this.$store.state.imageURL;
  }
},
data() {
  return {
    items: {
      icon: ''
    },
    profileImageFile: null,
    croppedImage: '',
    croppedsrc:'',
    profileimage: '',
  };
},
components: {
    ModalModal,
  },
  methods: {
    changeicon() {
  const formData = new FormData();
  formData.append("id", this.items.id);
  formData.append('icon', this.croppedImage);
  Service.post("/api/changeicon", formData, {
    headers: {
      "Content-Type": "multipart/form-data",
    }
  })
},

handleProfileImageUpload(event) {
      this.profileImageFile = event.target.files[0];
      this.path = URL.createObjectURL(this.profileImageFile);
      if (this.profileImageFile) {
        console.log(this.profileImageFile);
      } else {
        console.log("天ぷら");
      }
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

    },

    logout() {
      // ログアウト処理
      store.commit("LOGOUT");
      store.commit("SETLOG", false);
      this.$router.push("/");
    },
    deleteaccount() {
      const id = store.state.userId;
      Service.get("/deleteuser/" + id).then((response) => {
        console.log(response.data);

        store.commit("LOGOUT");
        store.commit("SETLOG", false);
        this.$router.push("/");
      });
    },
  },
  created() {
    const da = store.state.userId;
    Service.get("/getuser/" + da).then((response) => {
      console.log(response.data);
      this.items = response.data[0];
    });
  },
};
</script>

<style>
.success-message {
  color: rgb(255, 36, 36);
  font-weight: bold;
  text-align: center;
}
</style>
