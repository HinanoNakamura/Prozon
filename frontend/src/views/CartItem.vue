<template>
  <div class="home">

    <h3>❤️今持ってるクーポン</h3>
    {{ couponname + ":" + couponnumber + "%" + "引き" }}
    <button @click="createcoupon()" v-bind:disabled="processing">クーポン</button>

    <!-- クーポン用モーダルウィンドウ -->
    <div v-if="isOpen" class="modal-overlay">
      <div class="modal-content">
        <div style="font-size: 14px; text-align: center; width: 50vw;">
          <h2>今日のクーポン</h2>
          <hr>
          <img src="../assets/image1.png" alt="" style="width: 150px; height: 150px;">
          <img src="../assets/image2.png" alt="" style="width: 150px; height: 150px;">
          <img src="../assets/image3.png" alt="" style="width: 150px; height: 150px;">
          <h1>{{ couponname + " が " + couponnumber + " % " + " 引き " }}</h1>
        </div>
        <button @click="closeModal">閉じる</button>
        <button @click="useCoupon">クーポンを使用する</button>
      </div>
    </div>

    <h1>{{ "購入金額 :" + totalPrice + "yen" }}</h1>
    <button>購入</button>
    <br>
    <hr>
    <br>
    <ul>
      <li v-for="item in items" :key="item.id">
        {{ "名前 :" + item.cartname }}
        {{ "値段 :" + item.cartprice + "yen" }}
        <h3>{{ "個数 :" + item.cartquantity }}</h3>
        <h2>{{ "合計金額:" + item.cartprice * item.cartquantity + "yen" }}</h2>
        <input type="number" :value="item.cartquantity" @input="refreshquantity($event.target.value, item.cartname)">
        <img :src="'./assets' + item.cartimg" alt="Image" style="width: 160px; height: 160px;" />

        <button @click="remove(item.id)">削除</button>
        <button @click="refreshquantity(item.cartquantity, item.cartname)">個数変更</button>

      </li>
    </ul>
  </div>
</template>
  
<script>
import store from "../store/";
export default {
  name: 'CartVue',

  data() {
    return {
      isOpen: false,
      totalPrice: 0,
      couponname: store.state.coupon.couponname,
      couponnumber: store.state.coupon.couponnumber,
      processing: false,
    }

  },

  computed: {
    items() {
      return store.state.cart
    }
  },
  mounted() {
    this.calculateTotalPrice();
  },
  methods: {
    remove(id) {
      store.commit('delete', id)
      this.$router.go({ path: this.$router.currentRoute.path, force: true })
    },
    refreshquantity(value, name) {
      let sub = {
        value: value,
        name: name
      }
      // const value =document.getElementById('name').value
      // alert(sub.value + sub.name)
      store.commit('refreshquantity', sub)
      this.calculateTotalPrice();
    },
    calculateTotalPrice() {
      this.totalPrice = this.items.reduce((total, item) => {
        return total + (item.cartprice * item.cartquantity);
      }, 0);
    },
    createcoupon() {
      this.isOpen = true;
      this.processing = true
    },
    closeModal() {
      this.isOpen = false;
    },
    useCoupon() {
      let coupon = {
        couponname: this.couponname,
        couponnumber: this.couponnumber
      }
      store.commit('saveCoupon', coupon)
      console.log("母は")
    }

  }
};
</script>

<style>
ul {
  margin: 0;
  padding: 0;
}

li {
  list-style: none;
  border-bottom: 1px splid #ccc;
  padding-bottom: 10px;
  margin-bottom: 10px;

}
</style>