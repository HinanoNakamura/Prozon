<template>
  <div>
    <AppleApple />
  </div>
</template>

<script>
// @ is an alias to /src
import AppleApple from '@/components/Search.vue'
export default {
  name: 'SearchResults',
  components: {
    AppleApple
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
  background-color: rgb(243, 227, 216);
  color: #000000;
  padding: 5px 10px;
  border-radius: 5px;
  font-size: 12px;
  margin: auto;
  max-width: 80px;
  text-align: center;
  text-decoration: none;
}</style>