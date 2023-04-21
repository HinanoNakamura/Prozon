<template>
  <div class="今月のおすすめ">
    <h1>今月のおすすめプロテイン一覧</h1>

    <div class="flexbox">
      <div class="flexitem">
    <img src="/assets/81BC0hSJ-ZL.jpg" alt="Image">
    <p>>ザバス ココア味</p>
    <a href="">詳細を見る</a>
  </div>

  <div class="flexitem">
    <img src="/assets/000000000763_6lK7Cxx.jpg" alt="Image">
    <p>DNS いちご味</p>
    <a href="">詳細を見る</a>
  </div>
  </div>

  <div class="back-home">
    <button v-if="$route.path !== '/'" @click="$router.push('/')">ホーム画面に戻る</button>
    <router-view></router-view>
  </div>
  </div>
</template>

<style>
 body {
    max-width: 1800px;
    margin: auto;
  }

  .今月のおすすめ{
    text-align: center;
  }
  .flexbox {
    display: flex;
    justify-content: center;
    flex-flow: row wrap;
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
    background-color: rgb(123, 160, 181);
    color: #fff;
    padding: 5px 10px;
    border-radius: 5px;
    font-size: 12px;
    margin: auto;
    max-width: 80px;
    text-align: center;
    text-decoration: none;
  }
  </style>

