<template>
    <div>
      <button @click="randomImage">画像を表示</button>
      <img :src="selectedImage" v-if="showImage" alt="ランダムな画像">
    </div>
  </template>
  
  <script>
  export default {
    data() {
      return {
        images: [
          'path/to/image1.jpg',
          'path/to/image2.jpg',
          'path/to/image3.jpg'
        ],
        selectedImage: '',
        showImage: false
      };
    },
    methods: {
      randomImage() {
        const randomIndex = Math.floor(Math.random() * this.images.length);
        this.selectedImage = this.images[randomIndex];
        this.showImage = true;
      }
    }
  };
  </script>
  