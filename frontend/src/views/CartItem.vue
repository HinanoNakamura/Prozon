<template>
  <div>
    <div class="home">


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
</div>
  <div class="cart">
    <h1 class="cart-title">Cart 🛒</h1>
<hr>
    <ul class="cart-detail">
      <li v-for="item in items" :key="item.id">
        <div class="item-container">
          <div class="item-imagecart">
            <img :src="'./assets' + item.cartimg" alt="Image" style="width: 160px; height: 160px;" />
          </div>
          <div class="item-text">
            <h2>{{ item.cartname }}</h2>
            <p>{{ "Price: ¥" + item.cartprice.toLocaleString() }}</p>
            <p class="quantity-text">
              Quantity:
              <select v-model="item.cartquantity" @change="refreshquantity(item.cartquantity, item.cartname)">
                <option v-for="quantity in maxQuantity" :key="quantity" :value="quantity" :selected="quantity === item.cartquantity">{{ quantity }}</option>
              </select>
            </p>
            <p>{{ "Total price: " + (item.cartprice * item.cartquantity) + " yen" }}</p>
            <button @click="remove(item.id)">削除</button>
            <BuyCom :propName="total" @button-click="useCoupon"/> 
          </div>
        </div>
      </li>
    </ul>
  </div>
 
</div>

</template>

<script>
import store from "../store/";
import BuyCom from '@/components/Buy.vue'
  
  export default {
    name: 'CartVue',
    components: {
    BuyCom
  },
 
    data(){
      return{
        isOpen: false,
        maxQuantity: 100,
        totalPrice: 0,
        couponname: store.state.coupon.couponname,
      couponnumber: store.state.coupon.couponnumber,
      processing: false,
        total: 0
      }

    },

    computed:{
      items(){
        return store.state.cart
      }
    },
    mounted() {
  this.calculateTotalPrice();
},
  methods: {
    remove(id) {
      store.commit("delete", id);
      this.$router.go({ path: this.$router.currentRoute.path, force: true });
    },
    refreshquantity(value, name) {
      let sub = {
        value: value,
        name: name,
      };
      store.commit("refreshquantity", sub);
      this.calculateTotalPrice();
    },
    calculateTotalPrice() {
      this.totalPrice = this.items.reduce((total, item) => {
      return total + (item.cartprice * item.cartquantity);
    }, 0);
    this.total = this.totalPrice
  },createcoupon() {
      this.isOpen = true;
      
    },
    closeModal() {
      this.isOpen = false;
      
    },
    useCoupon() {
      let coupon = {
        couponname: this.couponname,
        couponnumber: this.couponnumber
      }
      store.commit('saveCoupon', coupon),
      this.processing = true
    }

    }
  };
  </script>

<style scoped>
ul {
  margin: 0;
  padding: 0;
}

li {
  list-style: none;
  padding-bottom: 10px;
  margin-top: 20px;
}

.cart {
  text-align: center;
}

.cart-title {
  margin-bottom: 20px;
}

.cart-detail {
  flex-wrap: wrap;
  justify-content: center;
}

.item-container {
  display: flex;
  align-items: center;
  padding: 0px 0px 0px 300px;
  margin-right: 20px;
  margin-top: 20px;
}

.item-imagecart {
  margin-right: 20px;
}

.item-text {
  text-align: left;
}

.item-buttons {
  margin-left: 50px;
}

.quantity-text {
  margin-bottom: 5px;
}
</style>