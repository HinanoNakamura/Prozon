<template>
  <div class="app">
    <div class="slideshow">
      <img v-for="(image, index) in images" v-show="index === currentImageIndex" :key="index" :src="image.src"
        :alt="image.alt" @click="goToURL(image.url)">
    </div>

    <div class="buttonchan">
      <button class="button-74" @click="prevImage">←</button>
      <button class="button-74" @click="nextImage">→</button>
    </div>
    <div>
      <router-link v-if="false" to="/">Home</router-link>
      <router-view></router-view>
    </div>



    <div class="unko">
      <img src="./assets/unnko.jpg" alt="画像" style="width: 150px; height: 150px;">
    </div>

    <div class="flavor-selector2">
      <router-link to="/search-results" class="search-link">
        <img src="./assets/image.png" alt="検索アイコン" class="search-icon">
      </router-link>
      
      <router-link v-if="!loggin" to="/login"><img src="./assets/cart.png" alt="カートアイコン" class="cart-icon" style="width: 47px; height: 47px;"></router-link>
 <router-link v-else to="/cart" class="cart-link">
        <img src="./assets/cart.png" alt="カートアイコン" class="cart-icon" style="width: 47px; height: 47px;">
      </router-link>

      <router-link v-if="!loggin" to="/login">Login</router-link>
      <router-link v-else to="/page">MyPage</router-link>
      <router-link v-if="loggin" to="/favorites">Favorite</router-link>
      <router-link v-else to="login">Favorite</router-link>
<br>
      <div class="coupon-container">
        <div style="font-size: 8px; text-align: center; width: 10vw;">
          <h2>My Coupon</h2>
          <hr>
          <img src="./assets/image1.png" alt="" @click="createcoupon()" style="width: 45px; height: 45px;">
          <img src="./assets/image2.png" alt="" @click="createcoupon()" style="width: 45px; height: 45px;">
          <img src="./assets/image3.png" alt="" @click="createcoupon()" style="width: 45px; height: 45px;">
          <h2>{{ ponname + " " + ponnumber + "%OFF" }}</h2>
        </div>
      </div>
    </div>

  </div>
</template>

<script>
import store from "./store/";

export default {
  data() {
    return {
      ponname: store.state.coupon.couponname,
      ponnumber: store.state.coupon.couponnumber,
      // 画像リストを追加します。
      images: [
        {
          src: '/assets/d.jpg',
          alt: '画像1',
          url: '/Prodoc'
        },
        {
          src: '/assets/w.jpg',
          alt: '画像2',
          url: '/Prowoman'
        },
        {
          src: '/assets/man.jpg',
          alt: '画像3',
          url: '/ProMan'
        },
        {
          src: '/assets/2.png',
          alt: '画像4',
          url: '/Procute'
        }
      ],
      currentImageIndex: 0,
      // スライドショーが自動的に移動するためのタイマーを追加します。
      timer: null,
      // スライドショーの遷移時間（ミリ秒）を追加します。
      transitionDuration: 5000,
    }
  },
  computed: {
    loggin() {
      return store.state.loggin
    }
  },

  watch: {
    loggin(value) {
      localStorage.setItem('loggin', value) // logginの値が変更された時にlocalStorageに保存するメソッドを呼び出す
    },
  },
  mounted() {
    // 初回のログイン状態のチェック
    if (localStorage.getItem('loggin') === 'true') {
      store.commit('SETID', localStorage.getItem('userId'));
      store.commit('SETLOG', true);
    }

    // 自動的に画像をスライドするタイマーを開始します。
    this.startSlideshow();
  },
  beforeDestroy() {
    this.stopSlideshow();
  },

  methods: {
    goToURL(url) {
      window.location.href = url;
    },
    startSlideshow() {
      if (this.timer) {
        clearInterval(this.timer);
      }
      this.timer = setInterval(() => {
        this.currentImageIndex = (this.currentImageIndex + 1) % this.images.length;
      }, this.transitionDuration);
    },
    stopSlideshow() {
      clearInterval(this.timer);
    },
    prevImage() {
      this.currentImageIndex = (this.currentImageIndex - 1 + this.images.length) % this.images.length;
      this.stopSlideshow();
      setTimeout(() => {
        this.startSlideshow();
      }, this.transitionDuration);
    },
    nextImage() {
      this.currentImageIndex = (this.currentImageIndex + 1) % this.images.length;
      this.stopSlideshow();
      setTimeout(() => {
        this.startSlideshow();
      }, this.transitionDuration);
    },
    changeTransitionDuration(duration) {
      this.transitionDuration = duration;
      this.stopSlideshow();
      this.timer = setInterval(() => {
        this.currentImageIndex = (this.currentImageIndex + 1) % this.images.length;
      }, this.transitionDuration);
    }
  }
}
</script>


<style>
@import url('https://fonts.googleapis.com/css2?family=Caveat&display=swap');

#app {
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  margin-top: 60px;
  font-family: 'Caveat', cursive;
}

.buttonchan {
  text-align: center;
}

.slideshow {
  text-align: center;
}

.img {
  text-align: center;
}


body {
  max-width: 1800px;
  margin: auto;

}

.top-banner {
  display: flex;
  flex-wrap: wrap;

}

.top-banner li {
  list-style: none;
  width: calc(25%/1);
  /*←画像を横に4つ並べる場合*/
  padding: 0 5px;
  /*←画像の左右に5pxの余白を入れる場合*/
  box-sizing: border-box;
}

.top-banner li img {
  max-width: 100%;
  /*画像のはみだしを防ぐ*/
  height: auto;
  /*画像の縦横比を維持 */
  border: solid 1px #ccc;
  /*←画像を1pxのグレーの枠線で囲む指定の場合*/
}

.flavor-option label {
  margin-left: 10px;
  /* ラベルとラジオボタンの間隔を設定 */
}

.unko {
  margin-top: 20px;
  /* 上部の余白を設定 */
  display: flex;
  flex-direction: column;
  align-items: left;
  font-size: 1.3rem;
  position: fixed;
  left: 0;
  top: 26%;
  transform: translateY(-180%);
}


.flavor-selector2 {
  margin-top: 20px;
  /* 上部の余白を設定 */
  display: flex;
  flex-direction: column;
  align-items: left;
  font-size: 1.3rem;
  position: fixed;
  left: 20;
  top: 60%;
  transform: translateY(-130%);
  font-family: 'Caveat', cursive;
}

@import url('https://fonts.googleapis.com/css2?family=Caveat&display=swap');

.home {
  font-family: 'Caveat', cursive;
}

.unko img {
  width: 200px;
}

.button-74 {
  font-family: 'Caveat', cursive;
  background-color: rgb(243, 227, 216);
  border: 2px solid #422800;
  border-radius: 30px;
  box-shadow: #422800 4px 4px 0 0;
  color: #422800;
  cursor: pointer;
  display: inline-block;
  font-weight: 50;
  font-size: 13px;
  padding: 0 18px;
  line-height: 20px;
  text-align: center;
  text-decoration: none;
  user-select: none;
  -webkit-user-select: none;
  touch-action: manipulation;
}

.button-74:hover {
  background-color: #fff;
}

.button-74:active {
  box-shadow: #422800 2px 2px 0 0;
  transform: translate(2px, 2px);
}
.coupon-container {
  display: flex;
  justify-content: center;
  align-items: center;
  width: 100%;
  height: 100%;
  border: 1px solid rgb(106, 94, 75);
  border-radius: 3px;
}
</style>

