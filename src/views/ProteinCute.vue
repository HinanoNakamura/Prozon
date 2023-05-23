<template>
    <div class="Protein recommendations for dogs">
      <div class="font">
        <div class="flexwan">
          <h1>★Employee recommended protein★</h1>
          <div class="spacer"></div>
          <div class="testes-wrapper">
            

            <p>宮澤おすすめプロテイン   gg   稲垣      中村</p>
              
            <div class="back-home">
              <button class="button-74" v-if="$route.path !== '/'" @click="$router.push('/')">Home</button>
              <router-view></router-view>
            </div>
          </div>
        </div>
      </div>
    </div>
  </template>
  
  <style>
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
  
  body {
    max-width: 1800px;
    margin: auto;
  }
  
  .image-list {
    display: flex;
    justify-content: flex-start; /* 左寄せに修正 */
    flex-wrap: wrap;
    gap: 100px;
    list-style: none;
    margin-left: 120px;
  }
  
  /* アイテム画像のスタイルを定義 */
  .item-image {
    width: 200px;
    height: 200px;
    display: block;
    margin: 0 auto;
  }
  
  .font {
    text-align: center;
    font-family: 'Caveat', cursive;
  }

  .spacer {
  height: 30px; /* 適切な間隔の高さを指定してください */
}

  </style>
  