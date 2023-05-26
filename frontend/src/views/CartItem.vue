<template>
  <div class="cart">
    <h1 class="cart-title">Cart 🛒</h1>
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
            <BuyCom :propName="total" /> 
          </div>
        </div>
      </li>
    </ul>
    <p v-if="items.length === 0" style= "font-family: 'Caveat', cursive; font-size:30px;">No item</p>
    <div class="back-home">
            <button class="button-74" v-if="$route.path !== '/'" @click="goBack">back</button>
            <router-view></router-view>
        </div>
  </div>
</template>

<script>
import store from "../store/";
import BuyCom from '@/components/Buy.vue'

export default {
  name: "CartVue",
    components: {
    BuyCom
  },
  data() {
    return {
      maxQuantity: 100, // 最大数量
      totalPrice: 0,
      total: 0
    };
  },
  computed: {
    items() {
      return store.state.cart;
    },
  },
  mounted() {
  this.calculateTotalPrice();
},
  methods: {
    remove(id) {
      store.commit("delete", id);
      this.$router.go({ path: this.$router.currentRoute.path, force: true });
    },
    goBack() {
            window.history.back();
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
    },
  },
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