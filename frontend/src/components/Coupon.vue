<template>
  <div>
    <button :disabled="processing" style="background-color: transparent; border: none; padding: 0;width: 50px; height: 50px;" 
      @click="createcoupon">
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
</template>

<script>
import { Service } from "@/service/service"
import store from "../store/";

export default {
  name: 'CouponCom',
  data() {
    return {
      favs: {},
      processing: false,
      couponname: '',
      couponnumber: '',
      isOpen: false,
    }
  },
  methods: {
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
      this.processing = true;
    },
    closeModal() {
      this.isOpen = false;
      console.log(store.state.coupon.couponnumber);
    }
  }
}
</script>

<style>
.modal-overlay {
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  display: flex;
  justify-content: center;
  align-items: center;
  background-color: rgba(0, 0, 0, 0.5);
  z-index: 9999;
  position: absolute;
  top: 100%;
  left: 100%;
  transform: translate(-50%, -50%);
}

</style>
