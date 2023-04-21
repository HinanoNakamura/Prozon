<template>
    <div class="home">
    <HelloWorld msg="Welcome to Proteinworld"/>

    <div class="flexbox">
      <div class="flexitem">
          <img src="/assets/protein_14531-768x768.png"  alt="バナー01です"><router-link to="/about">おすすめプロテイン</router-link>
      </div>

      <div class="flexitem">
        <img src="/assets/protein_14531-768x768.png" alt="バナー02です"><router-link to="/about2">プロテインランキング</router-link>
      </div>

    </div>
    </div>
</template>

<script>
// @ is an alias to /src
import HelloWorld from '@/components/HelloWorld.vue'
export default {
  name: 'ProteinMain',
  components: {
    HelloWorld
  }
}
</script>

<style>
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
    background-color: rgb(115, 205, 205);
    color: #fff;
    padding: 5px 10px;
    border-radius: 5px;
    font-size: 12px;
    margin: auto;
    max-width: 80px;
    text-align: center;
    text-decoration: none;
  }
.imgs {
    max-width: 1000px;
    margin: auto;
  }

  .flexitem2 {
  position: absolute;
  top: 20px;
  right: 20px;
  flex: 0 0 24%;
}

  .flexitem2 img {
    display: block;
    width: 100%;
    height: 200px;
    object-fit: contain;
  }

  .flexitem2 a {
    display: block;
    background-color: rgb(115, 205, 205);
    color: #fff;
    padding: 5px 10px;
    border-radius: 5px;
    font-size: 12px;
    margin: auto;
    max-width: 80px;
    text-align: center;
    text-decoration: none;
  }


.home {
  text-align: center;
}

</style>
