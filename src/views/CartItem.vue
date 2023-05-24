<template>
    <div class="home">
      <h1>カートだよ💩</h1>
       <h1>{{ "購入金額 :" +totalPrice + "yen"}}</h1>
 <ul>
  <li v-for="item in items" :key="item.id">
{{ "名前 :" +item.cartname}}
{{ "値段 :" + item.cartprice + "yen" }}
<h3>{{"個数 :" + item.cartquantity }}</h3>
<h2>{{ "合計金額:" + item.cartprice * item.cartquantity + "yen"}}</h2>
<input type="number" :value="item.cartquantity" @input="refreshquantity($event.target.value, item.cartname)">
<img :src="'./assets' + item.cartimg" alt="Image" style="width: 160px; height: 160px;" />

<button @click="remove(item.id)">削除</button>
<button @click="refreshquantity(item.cartquantity,item.cartname)">個数変更</button>
  </li>
 </ul>
    </div>
  </template>
  
  <script>
  import store from "../store/";
  export default {
    name: 'CartVue',
 
    data(){
      return{
        totalPrice: 0
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
    methods:{
      remove(id){
        store.commit('delete', id)
        this.$router.go({ path: this.$router.currentRoute.path, force: true })
      
      },
      refreshquantity(value,name){
        let sub ={
          value:value,
          name:name
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

    }
  };
  </script>

  <style>
ul{
  margin:0;
  padding:0;
}
li{
  list-style: none;
  border-bottom: 1px splid #ccc;
  padding-bottom:10px;
  margin-bottom:10px;

}
</style>