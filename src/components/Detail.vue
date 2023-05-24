<template>
    <div class="home" style="text-align: center;">

        <div class="flexbox">
            <div class="flexitem">
                <div class="image-container" style="position:relative;">
                    <br>
                    <ul v-for="item in items" :key="item.id">
                        <span style="display:none">{{ item.id }}</span>
                        <span style="font-size: 25px;">{{ item.name }}</span>
                        <br>
                        <img :src="'./assets' + item.img"
                            style="width: 300px; height: 300px; display: block; margin: 0 auto;" />
                        <br>
                        ¥{{ item.price.toLocaleString() }}
                        <br>
                        {{ item.detail }}
                    </ul>

                    <div>Quantity :
                        <select v-model="quantity">
                            <option v-for="num in maxQuantity" :value="num" :key="num">{{ num }}</option>
                        </select>
                    </div>
                    <div>Total: ¥{{ total }}</div>
                    <PopupWindow ref="popup"></PopupWindow>


                </div>
            </div>
            <!-- <ul>
                <li v-for="(value) in items">{{ value }}</li>
            </ul> -->
            <!-- <div v-for="item in items">
    {{ item.name}}</div> -->
        </div>
        <br>
        <div class="Details">
            <button class="button-53" @click="Favorites($event)">
                <span class="favorite-icon">♡</span>
                <span class="button-text">Favorites</span>
            </button>
            <button class="button-53" @click=" saveCart()">
                <span class="favorite-icon"><i class="fas fa-shopping-cart"></i></span>
                <span class="button-text">Cart</span>
            </button>

            <router-view></router-view>
        </div>
        <br>
        <h1>Also recommended</h1>
        <div class="flexbox">

            <div class="flexitem" v-for="fav in favs" :key="fav.id">
                <div class="image-container" style="width: 200px; height: 180px; margin-left: 50px;">
                    <ul>
                        <li>
                            {{ fav.name }}
                            <br>
                            ¥{{ fav.price.toLocaleString() }}

                            <img :src="'./assets' + fav.img" alt="Image" style="width: 160px; height: 160px;"
                                @click="MoreDetail(fav.id)" />


                            <!-- <img :src="'./assets' + fav.img" alt="Image" style="width: 160px; height: 160px;" />
              <h2><router-link :to="{name:'detail',params:{ id: testes.id}}" class="button-74">Detail</router-link></h2> -->
                        </li>
                    </ul>
                </div>
            </div>
        </div>
        <br><br><br>
        <div class="back-home">
            <button class="button-74" v-if="$route.path !== '/'" @click="goBack">back</button>
            <router-view></router-view>
        </div>
        <!-- </div> -->

    </div>
</template>
  
<script>
// import axios from 'axios'
// Vue.prototype.$http = axios

import { Service } from "@/service/service";
import store from "../store/";
import PopupWindow from './/PopupWindow';

export default {

    name: "DetailPage",
    components: {
        PopupWindow
    },
    props: {
        title: String,
        image: String,

    }, data() {
        return {
            username: '',
            items: {},
            detail: '',
            id: this.$route.params.id,
            item: '',
            maxQuantity: 100, // 最大数量
            quantity: 1, // 選択された数量
            favs: [],
            favId0: {},
            favId1: {},
            favId2: {},
            moreid: store.state.id,
            cartname: '',
            cartprice: '',
            cartimg: ''
        }
    },
    mounted() {
        Service.get('/detail/' + this.moreid).then(response => {
            this.items = response.data
            this.flavor = this.items[0].flavorcode
            // this.setFlavorCode(this.flavor)
            store.commit('setFlavorCode', response.data[0].flavorcode)
            // alert(store.state.flavorCode)
            this.cartname=this.items[0].name
      this.cartprice=this.items[0].price
      this.cartimg= this.items[0].img
            store.commit('setDetailId', this.moreid)

            Service.get('/fav/' + store.state.flavorCode + '/' + this.moreid).then(response => {
                this.favs = response.data;
                this.favId0 = response.data[0].id
                this.favId1 = response.data[1].id
                this.favId2 = response.data[2].id
                // alert(11111 + "/" + this.favs[1].id )
            });
        });
        console.log(store.state.flavorCode)
    },
    methods: {
        Favorites(event) {
            event.preventDefault();
            Service.post("/api/addToFavorites", {
                userid: store.state.userId,
                proteinid: store.state.id,
            })
                .then(response => {
                    this.$refs.popup.showPopup('Favorite Completed');
                    console.log(response);
                })
                .catch(error => {
                    console.log(error);
                    alert(this.userid + this.id + "ひ")
                });
        },
        goBack() {
            window.history.back();
        },
        // addToCart(items, quantity) {
        //     alert(items + 'あ' + quantity)
        //     store.dispatch('addToCart', { item: items, quantity: quantity });
        //     console.log('い' + store.state.cartItems)
        // },
        MoreDetail(itemId) {
            if (itemId == this.favId0) {
                store.commit('setDetailId', this.favId0)
                store.commit('setId', this.favId0)
            } if (itemId == this.favId1) {
                store.commit('setDetailId', this.favId1)
                store.commit('setId', this.favId1)
            } if (itemId == this.favId2) {
                store.commit('setDetailId', this.favId2)
                store.commit('setId', this.favId2)
            }
            this.$router.go({ path: this.$router.currentRoute.path, force: true })
        },
        saveCart() {
            let cart = {
                cartname: this.cartname,
                cartprice: this.cartprice,
                cartimg: this.cartimg,
                cartquantity: this.quantity
            }
            store.commit('saveCart', cart)
            alert("押したよ❤️")
        },
        computed: {
            total() {
                if (this.items && this.items.length > 0 && this.quantity) {
                    const selectedItem = this.items.find(item => item.id === this.$route.params.id);
                    if (selectedItem && selectedItem.price) {
                        const totalPrice = selectedItem.price * this.quantity;
                        return totalPrice.toLocaleString(); // 桁区切りの表示
                    }
                }
                return "0";
            },
        },
    }
    };
</script>

<style>
@import url('https://fonts.googleapis.com/css2?family=Caveat&display=swap');
@import "https://use.fontawesome.com/releases/v5.13.0/css/all.css";


body {
    max-width: 1800px;
    margin: auto;
}

.home {
    text-align: center;
}

.flexbox {
    display: flex;
    justify-content: center;
    flex-flow: row wrap;
    font-size: 20px;
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
    color: #fff;
    padding: 5px 10px;
    border-radius: 5px;
    font-size: 12px;
    margin: auto;
    max-width: 80px;
    text-align: center;
    text-decoration: none;
}

.Details {
    flex-direction: column;
    justify-content: center;
    align-items: center;
}

.button-53 {
    background-color: rgb(243, 227, 216);
    border: 0 solid #E5E7EB;
    box-sizing: border-box;
    color: #000000;
    display: inline-block;
    vertical-align: middle;
    margin-right: 40px;
    margin-left: 40px;
    font-size: 1rem;
    font-weight: 700;
    justify-content: center;
    line-height: 1rem;
    padding: .75rem 1.65rem;
    position: relative;
    text-align: center;
    text-decoration: none #000000 solid;
    text-decoration-thickness: auto;
    width: 100%;
    max-width: 150px;
    position: relative;
    cursor: pointer;
    transform: rotate(-2deg);
    user-select: none;
    -webkit-user-select: none;
    touch-action: manipulation;
}

.button-53:focus {
    outline: 0;
}

.button-53:after {
    content: '';
    position: absolute;
    border: 1px solid #000000;
    bottom: 4px;
    left: 4px;
    width: calc(100% - 1px);
    height: calc(100% - 1px);
}

.button-53:hover:after {
    bottom: 2px;
    left: 2px;
}

@media (min-width: 768px) {
    .button-53 {
        padding: .75rem 3rem;
        font-size: 1.25rem;
    }
}


.favorite-icon {
    font-size: 20px;
    font-family: ui-sans-serif, system-ui, -apple-system, system-ui, "Segoe UI", Roboto, "Helvetica Neue", Arial, "Noto Sans", sans-serif, "Apple Color Emoji", "Segoe UI Emoji", "Segoe UI Symbol", "Noto Color Emoji";
    color: rgb(222, 173, 173);
}

.button-text {
    font-size: 17px;
    font-family: 'Caveat', cursive;
}

.fa-shopping-cart {
    color: rgb(222, 173, 173);
    font-size: 18px;
}
</style>