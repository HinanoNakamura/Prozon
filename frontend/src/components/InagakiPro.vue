<template>
    <div>
      <div style="display: flex; align-items: center; height: 100vh; flex-direction: column;">
        <div style="display: flex; align-items: center;">
          <img src="/assets/inagaki.png" alt="画像" style="width: 150px; height: 150px;">
          <h1 style="margin-left: 10px;">Inagaki<br>recommended Protein riaria</h1>
        </div>
        <div>
          <div v-for="fav in favs" :key="fav.id">
            <br>
            <div style="width: 200px; height: 180px; margin-left: 50px;">
              {{ fav.name }}
              <br>
              ¥{{ fav.price.toLocaleString() }}
              <img :src="'./assets' + fav.img" alt="Image" style="width: 160px; height: 160px;" @click="MoreDetail" />
  
              <h2>
                <router-link :to="{name:'detail',params:{ id: 105}}">
                  <button @click="saveId" class="button-74">Detail</button>
                </router-link>
              </h2>
            </div>
          </div>
        </div>
      </div>
    </div>
  </template>
  
  
  <script>
  import { Service } from "@/service/service";
  import store from "../store/"
  
  export default {
    data() {
      return {
        favs: [],
      };
    },
    mounted() {
      Service.get('/detail/' + 105).then(response => {
        this.favs = response.data;
      });
    },
    methods: {
      MoreDetail() {
        // 固定の値 1 を使用してデータの詳細を表示するための処理を実装する
      },
      saveId(){
      store.commit('setId', 105)
    }
    },
  };
  </script>
  