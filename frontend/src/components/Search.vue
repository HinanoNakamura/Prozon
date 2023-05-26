<template>
    <div id="app">

      <div class="flavor-selector">
        
        <h3>FLAVOR</h3>
        <div v-for="(flavor, index) in flavors" :key="'flavor-' + index" class="flavor-option">
          <input type="checkbox" :id="'flavor-' + index" v-model="selectedFlavors" :value="flavor">
          <label :for="'flavor-' + index">{{ flavor }}</label>
        </div>

        <h3>PURPOSE</h3>
        <div v-for="(purpose, index) in purposes" :key="'purpose-' + index" class="flavor-option">
          <input type="checkbox" :id="'purpose-' + index" v-model="selectedPurposes" :value="purpose">
          <label for="'purpose-' + index">{{ purpose }}</label>
        </div>

        <h3>COMPONENT</h3>
        <div v-for="(component, index) in components" :key="'component-' + index" class="flavor-option">
          <input type="checkbox" :id="'component-' + index" v-model="selectedComponents" :value="component">
          <label for="'component-' + index">{{ component }}</label>
        </div>



        <h3>PRICE</h3>
        <div v-for="(price, index) in prices" :key="'price-' + index" class="flavor-option">
          <input type="checkbox" :id="'price-' + index" v-model="selectedPrices" :value="price">
          <label for="'price-' + index">{{ price }}</label>
        </div>


        <button class="button-74" @click="sendFlavors">search</button>

        <div>
        <button class="button-74" v-if="$route.path !== '/'" @click="$router.push('/')">Home</button>
        <router-view></router-view>
        </div>

      </div>

    <h4>Search Results</h4>
    <h5 v-show="testesList.length === 0 && showResults">No item</h5>
    <div class="testes-wrapper">
      <div v-for="testes in testesList" :key="testes.name" class="testes-item">
        <h2>{{ testes.name }}</h2>
        <img :src="'./assets' + testes.img" style="width: 200px; height: 200px; display: block; margin: 0 auto;" />

        <h2>¥{{ testes.price.toLocaleString() }}</h2>
        <!-- <router-link :to="{ name: 'TestesDetails', params: { name: testes.name } }"> -->
          <h2><router-link :to="{name:'detail',params:{ id: testes.id}}"><button @click="saveId(testes.id)"  class="button-74">Detail</button></router-link></h2>
       
        <!-- </router-link> -->

      </div>
    </div>

  </div>
<!-- 
    <div class="back-home">
          <button class="button-74" v-if="$route.path !== '/'" @click="$router.push('/')">Home</button>
          <router-view></router-view>
        </div> -->

</template>


<script>
import { Service } from "@/service/service"
import store from "../store/"

export default {
  name: 'AppleApple',
  data() {
    return {
      flavors: ['Cocoa', 'Strawberry', 'Mango','Melon' , 'Matcha','Plane'],
      selectedFlavors: [],
      purposes: ['Diet', 'Muscle','dog'],
      selectedPurposes: [],
      components: ['Whey', 'Soy', 'Casein'],
      selectedComponents: [],
      prices: ['~¥2,000', '¥2,001~¥3,000', '¥3,001~'],
      selectedPrices: [],
      testesList: [],
      showResults: false,
      items: {},
      detail: '',
      id: this.$route.params.id,
      item: ''
    }
  },

  methods: {
    sendFlavors() {
      const data = {
        flavors: this.selectedFlavors.map(flavor => this.flavors.indexOf(flavor) + 1),
        purposes: this.selectedPurposes.map(purpose => this.purposes.indexOf(purpose) + 1),
        components: this.selectedComponents.map(component => this.components.indexOf(component) + 1),
        prices: this.selectedPrices.map(price => this.prices.indexOf(price) + 1)
      };
      Service.post('/api/flavors', data)
        .then(response => {
          this.showResults = true;
          this.testesList = response.data.map(testes => {
            return {
              id:testes.id,
              name: testes.name,
              price: testes.price,
              img: testes.img,
            };
          });
          const dataStr = JSON.stringify(this.testesList);
          sessionStorage.setItem('results', dataStr);
        })
        .catch(error => {
          console.log(error);
          if (error.name !== 'NavigationDuplicated') {
            throw error;
          }
        });
    },
    saveId(id){
      store.commit('setId', id)
    }
  },
  created() {
    const results = sessionStorage.getItem('results');
    if (results) {
      this.testesList = JSON.parse(results);
      this.showResults = true;
    }
  }
}

</script>


<style>
@import url('https://fonts.googleapis.com/css2?family=Caveat&display=swap');



.flavor-selector {
  position: fixed;
  left: 0; 
  top: 56%; 
  width: 200px; 
  height: 44vh; 
  overflow-y: auto;
}


h4 {
  text-align: center;
  font-family: 'Caveat', cursive;
  font-size: 50px;
}

h5 {
  font-family: 'Caveat', cursive;
  text-align: center;
  font-size: 40px;
}

h3 {
  font-family: 'Caveat', cursive;
}

h2 {
  font-size: 17px;
}

.testes-wrapper {
  display: flex;
  flex-wrap: wrap;
  padding: 0 120px; 
  text-align: center;
}

.testes-item {
  flex: 0 0 calc(25% - 10px); /* 一行に3つのアイテムを表示するため、25%に設定し余白を考慮 */
  margin-bottom: 10px; /* 下に20pxの余白を追加 */
}


</style>