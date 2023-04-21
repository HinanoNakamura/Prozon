<template>
  <div class="app">

    <div class="img">
    <img alt="Protein" src="./assets/image.png" width="500">
</div>

      <div>
        <router-link v-if="false" to="/">Home</router-link>
        <router-view></router-view>
      </div>
      <div class="flexitem2">
        <img src="/assets/0b4e34ee55d77486d5692fcb253f8e6b.jpg" alt="バナー03です"><router-link to="/login">ログイン</router-link>
      </div>

  <div class="flavor-selector">
    <h3>FLAVOR</h3>
    <div v-for="(flavor, index) in flavors" :key="index" class="flavor-option">
      <input type="checkbox" :id="'flavor-' + index" v-model="selectedFlavors" :value="flavor">
      <label :for="'flavor-' + index">{{ flavor }}</label>
    </div>
    <!-- <p>Selected flavors: {{ selectedFlavors }}</p>
    <p>あいかちゃん: {{ selectedFlavors.map(flavor => flavors.indexOf(flavor) + 1) }}</p> -->
  </div>

  <div class="flavor-selector">
  <h3>PURPOSE</h3>
  <div v-for="(purpose, index) in purposes" :key="index" class="flavor-option">
    <input type="checkbox" :id="'purpose-' + index" v-model="selectedPurposes" :value="purpose">
    <label for="'purpose-' + index">{{ purpose }}</label>
  </div>
    <!-- <p>Selected purposes: {{ selectedPurposes }}</p>
    <p>ともちゃん: {{ selectedPurposes.map(purpose => purposes.indexOf(purpose) + 1) }}</p> -->

  </div>

  <div class="flavor-selector">
  <h3>COMPONENT</h3>
  <div v-for="(component, index) in components" :key="index" class="flavor-option">
    <input type="checkbox" :id="'component-' + index" v-model="selectedComponents" :value="component">
    <label for="'component-' + index">{{ component }}</label>
  </div>
  <!-- <p>Selected components: {{ selectedComponents }}</p>
  <p>なるお: {{ selectedComponents.map(component => components.indexOf(component) + 1) }}</p> -->
</div>

<div class="flavor-selector">
  <h3>PRICE</h3>
  <div v-for="(price, index) in prices" :key="index" class="flavor-option">
    <input type="checkbox" :id="'price-' + index" v-model="selectedPrices" :value="price">
    <label for="'price-' + index">{{ price }}</label>
  </div>
  <!-- <p>Selected prices: {{ selectedPrices }}</p>
  <p>ミーナ: {{ selectedPrices.map(price => prices.indexOf(price) + 1) }}</p> -->
</div>
<button @click="sendFlavors">search</button>

  </div>
</template>


<script>
import {Service} from "@/store/index.js"

export default {
  
  data() {
    return {
      flavors: ['いちご', 'マンゴー', 'メロン', 'ココア', '抹茶'],
      selectedFlavors: [],
      purposes: ['ダイエット','筋トレ'],
      selectedPurposes:[],
      components: ['ホエイ','ソイ','カゼイ'],
      selectedComponents:[],
      prices: ['~¥2,000','¥2,001~¥3,000','¥3,001~'],
      selectedPrices:[]
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
          console.log(response.data);
        })
        .catch(error => {
          console.log(error);
        });
    }
  }
}
</script>




<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  color: #2c3e50;
  margin-top: 60px;
}

.img {
  text-align: center;
}


  body {
    max-width: 1800px;
    margin: auto;
    
  }

.top-banner{
	display: flex;
	flex-wrap:wrap;

}
.top-banner li {
  list-style: none;
	width: calc(25%/1);/*←画像を横に4つ並べる場合*/
	padding:0 5px;/*←画像の左右に5pxの余白を入れる場合*/
	box-sizing:border-box;
}
.top-banner li img {
	max-width:100%; /*画像のはみだしを防ぐ*/
	height: auto; /*画像の縦横比を維持 */
	border:solid 1px #ccc; /*←画像を1pxのグレーの枠線で囲む指定の場合*/
}


.flavor-selector {
  margin-top: 20px; /* 上部の余白を設定 */
  display: flex;
  flex-direction: column;
  align-items: left;;
  font-size: 1.2rem;
}

.flavor-option label {
  margin-left: 10px; /* ラベルとラジオボタンの間隔を設定 */
}

</style>

