<template>
  <div class="home1">
    <HelloWorld msg="Welcome to Proteinworld" />

    <div class="flexbox">
      <div class="flexitem">
        <img src="/assets/1682565705747.jpg" alt="バナー01です"><router-link to="/about">Recommended gacha</router-link>
      </div>

      <div class="flexitem">
        <img src="/assets/1682565705747.jpg" alt="バナー02です"><router-link to="/about2">Protein<br>ranking</router-link>
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
  background-color: rgb(243, 227, 216);
  color: #000000;
  padding: 5px 10px;
  border-radius: 5px;
  font-size: 15px;
  margin: auto;
  max-width: 100px;
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
  background-color: rgb(243, 227, 216);
  color: #000000;
  padding: 5px 10px;
  border-radius: 5px;
  font-size: 12px;
  margin: auto;
  max-width: 80px;
  text-align: center;
  text-decoration: none;
}

.flexitem3 {
  position: absolute;
  top: 20px;
  left: 20px;
  flex: 0 0 24%;
}

.flexitem3 img {
  display: block;
  width: 100%;
  height: 200px;
  object-fit: contain;
}

.flexitem3 a {
  display: block;
  background-color: rgb(243, 227, 216);
  color: #000000;
  padding: 5px 10px;
  border-radius: 5px;
  font-size: 12px;
  margin: auto;
  max-width: 80px;
  text-align: center;
  text-decoration: none;
}

@import url('https://fonts.googleapis.com/css2?family=Caveat&display=swap');

.home1 {
  text-align: center;
  font-family: 'Caveat', cursive;
}</style>
