<template>
  <div class="home1">
    <HelloWorld msg="Welcome to Proteinworld" />

    <div class="flexbox">
      <div class="flexitem">
        <img src="/assets/1682565705747.jpg" alt="バナー01です"><router-link to="/about">Recommended gacha</router-link>
      </div>

      <div class="flexitem">
        <img src="/assets/1682565705747.jpg" alt="バナー02です"><router-link to="/about2">Protein<br>ranking</router-link>
      </div>
      <button :disabled="processing" style="background-color: transparent; border: none; padding: 0;width: 50px; height: 50px;" @click="createcoupon">
  <img src="/assets/coupon.png" alt="">
</button>


<!-- クーポン用モーダルウィンドウ -->
<div v-if="isOpen" class="modal-overlay">
  <div class="modal-content" v-for="fav in favs" :key="fav.id">
    <div style="font-size: 14px; text-align: center; width: 50vw;">
      <h2>Today's Coupon</h2>
      <hr>
      <img src="/assets/image1.png" alt="" @click="createcoupon()" style="width: 150px; height: 150px;">
      <img src="/assets/image2.png" alt="" @click="createcoupon()" style="width: 150px; height: 150px;">
      <img src="/assets/image3.png" alt="" @click="createcoupon()" style="width: 150px; height: 150px;">


      <h1>{{ fav.name + " " + fav.flavorcode + "0" + "%OFF" }}</h1>
      <!-- <input v-on:change="setImage" type="file" name="image" accept="image/*">
      <br><br> -->
    </div>
    <button @click="closeModal">Close</button>
  </div>
</div>
    </div>
  </div>
</template>

<script>
// @ is an alias to /src
import HelloWorld from '@/components/HelloWorld.vue'
import { Service } from "@/service/service"
import store from "../store/";
export default {
  name: 'ProteinMain',
  components: {
    HelloWorld
  },
  data() {
    return{
    favs: {},
      processing: false,
      couponname: '',
      couponnumber: '',
      isOpen: false,
  }
  },
  methods : {
    createcoupon() {
      Service.get('/users').then(response => {
        this.favs = response.data;
        this.couponname = response.data[0].name;
        this.couponnumber = response.data[0].flavorcode + "0"
        console.log(this.couponname)

        let coupon = {
          couponname: this.couponname,
          couponnumber: this.couponnumber
        }
        store.commit('saveCoupon', coupon)

      });

      this.isOpen = true;
      this.processing = true

    },
    closeModal() {
      this.isOpen = false;
      console.log(store.state.coupon.couponnumber)
    }
  }
}
</script>

<style>
.flexbox {
  display: flex;
  justify-content: center;
  flex-flow: row wrap;
}

.flexitem {
  flex: 0 0 24%;
}

.flexitem img {
  display: block;
  width: 100%;
  height: 200px;
  object-fit: contain;
}

.flexitem a {
  display: block;
  background-color: rgb(243, 227, 216);
  color: #000000;
  padding: 5px 10px;
  border-radius: 5px;
  font-size: 15px;
  margin: auto;
  max-width: 100px;
  text-align: center;
  text-decoration: none;
}

.imgs {
  max-width: 1000px;
  margin: auto;
}

.flexitem2 {
  position: absolute;
  top: 20px;
  right: 20px;
  flex: 0 0 24%;
}

.flexitem2 img {
  display: block;
  width: 100%;
  height: 200px;
  object-fit: contain;
}

.flexitem2 a {
  display: block;
  background-color: rgb(243, 227, 216);
  color: #000000;
  padding: 5px 10px;
  border-radius: 5px;
  font-size: 12px;
  margin: auto;
  max-width: 80px;
  text-align: center;
  text-decoration: none;
}

.flexitem3 {
  position: absolute;
  top: 20px;
  left: 20px;
  flex: 0 0 24%;
}

.flexitem3 img {
  display: block;
  width: 100%;
  height: 200px;
  object-fit: contain;
}

.flexitem3 a {
  display: block;
  background-color: rgb(243, 227, 216);
  color: #000000;
  padding: 5px 10px;
  border-radius: 5px;
  font-size: 12px;
  margin: auto;
  max-width: 80px;
  text-align: center;
  text-decoration: none;
}

@import url('https://fonts.googleapis.com/css2?family=Caveat&display=swap');

.home1 {
  text-align: center;
  font-family: 'Caveat', cursive;
}</style>
