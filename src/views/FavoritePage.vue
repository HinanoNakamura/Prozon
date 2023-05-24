<template>
  <div>
    <form>
      <div>
        <h4 style="font-family: 'Caveat', cursive;">Favorite items</h4>
      </div>
    </form>
    <div class="testes-wrapper2">
      <ul v-for="item in items" :key="item.id">

        <div style="font-family: 'Caveat', cursive;" class="testes-item2">
          <span style="display:none">{{ item.id }}</span>
          <p style="padding-left: 5ch; text-align: left;">{{ item.name }}</p>
          <div style="display:flex; align-items:center; position: relative;">
            <div>
              <button class="heart" :class="{ liked: item.isLiked }" @click="toggleLike(item)"></button>
              <div style="position: relative; display: flex; flex-direction: column; align-items: center;">
  <img :src="'./assets' + item.img" style="display: block; margin: 0 auto; width: 200px; height: 200px;" />
  <p style="margin-top: 10px;">¥{{ item.price.toLocaleString() }}</p>
  <h2><router-link :to="{name:'detail',params:{ id: item.id}}" class="button-74">Detail</router-link></h2>
</div>
</div>
          </div>
        </div>
      </ul>

    </div>
    <br>
    <div class="back-home">
      <button class="button-74" v-if="$route.path !== '/'" @click="$router.push('/')">Home</button>
      <router-view></router-view>
    </div>
  </div>
</template>

<script>
import { Service } from "@/service/service"
import store from "../store/";

export default {
  data() {
    return {
      userId: store.state.userId,
      items: [],
    }
  },
  created() {
    Service.get("/getFavorites/" + this.userId)
      .then(response => {
        this.items = response.data.map(item => ({
          ...item,
          isLiked: false
        }));
        console.log(response);
      })
      .catch(error => {
        console.log(error);
        alert("いちご飴エラー")
      });
  },
  methods: {
    toggleLike(item) {
      if (!item.isLiked) {
          item.isLiked = !item.isLiked;
          Service.post("/deleteFavorites", {
            userid: this.userId,
            proteinid: item.id,
          },
          )
            .then(response => {
              console.log(response.data)
            })
            .catch(error => {
              console.log(error);
            });
      }
      else {
        item.isLiked = !item.isLiked;
        Service.post("/api/addToFavorites", {
          userid: this.userId,
          proteinid: item.id,
        })
          .then(response => {
            console.log(response);
          })
          .catch(error => {
            console.log(error);
            alert(this.userid + this.id)
          });
      }

    }
  }
}

</script>
<style scoped>
@import url('https://fonts.googleapis.com/css2?family=Caveat&display=swap');

form {
  text-align: center;
}

ul {
  list-style: none;
}

.back-home {
  text-align: center;
  font-family: 'Caveat', cursive;
}

.newfont {
  font-size: 30px;
}

.heart {
  width: 30px;
  height: 30px;
  position: absolute;
  top: -55px;
  left: 0px;
  background: none;
  border: none;
}


.heart::before,
.heart::after {
  content: "";
  /* 疑似要素に必須 */
  width: 50%;
  /* ハートの丸い部分の大きさにかかわる */
  height: 80%;
  /* ハートの高さにかかわる */
  background: #e0548e;
  /* ハートの色 */
  border-radius: 25px 25px 0 0;
  /* ハートの半円を生成 */
  position: absolute;
  /* 相対位置に指定 */
}

.heart::before {
  transform: rotate(-45deg);
  /* 左に回転 */
  left: 14%;
  /* 左からの位置を指定 */
}

.heart::after {
  transform: rotate(45deg);
  /* 右に回転 */
  right: 14%;
  /* 右からの位置を指定 */
}

.liked::before,
.liked::after {
  background: #c7c6c6;
}

.testes-wrapper2 {
  
  display: flex;
  flex-wrap: wrap;
  justify-content: flex-start;
  padding: 0 220px;
  text-align: center;
  display: flex; /* 追加 */
  
}

.testes-item2 {
  flex: 0 0 calc(25% - 10px);
  /* 一行に3つのアイテムを表示するため、25%に設定し余白を考慮 */
  margin-bottom: 10px;
  /* 下に20pxの余白を追加 */
}

.testes-wrapper2 img {
  float: left;
  margin-right: 100px                                                                                                                                                                                                                                                                                                                            0px;
}



/* @media screen and (max-width: 768px) {
  .testes-wrapper2 > * {
    width: calc(100% / 2 - 40px);
  }
}

@media screen and (max-width: 480px) {
  .testes-wrapper2 > * {
    width: 100%;
  }
} */
</style>