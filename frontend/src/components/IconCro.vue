<template>
    <div>
      <!-- ファイル選択用のinputタグ -->
      <!-- <input type="file" @change="handleFileChange"> -->
      <!-- Croppaコンポーネント -->
      <croppa :src="imageSrc" v-model="croppedImage" :options="croppaOptions" ref="croppa">
        <br>
        <button  ref="croppa" @click="cropImage">うんち</button>
      </croppa>
      
      <!-- 画像を表示するimgタグ -->
      <img :src="imageSrc" ref="image">
      <!-- クロップボタン -->
  
  
  
    </div>
  </template>
  
  <script>
  import axios from 'axios';
  
  import 'vue-croppa/dist/vue-croppa.css'
  
  export default {
 
    data() {
      return {
        imageSrc: null, // 選択された画像のURLを格納する変数
        croppedImage: null, // クロップされた画像を格納する変数
        croppaOptions: {
          viewport: {
            width: 200,
            height: 200,
          },
          boundary: {
            width: 300,
            height: 300,
          },
        },
      };
    },
    methods: {
      handleFileChange(event) {
        // ファイルが選択されたときに呼び出されるメソッド
        const files = event.target.files;
        if (files && files[0]) {
          // 選択されたファイルを読み込む
          const reader = new FileReader();
          reader.onload = (e) => {
            // 読み込んだ画像をimgタグに設定する
            this.imageSrc = e.target.result;
          };
          reader.readAsDataURL(files[0]);
        }
      },
      cropImage() {
        if (this.$refs.croppa) {
          // クロップされた画像を取得する
          this.$refs.croppa.generateBlob((blob) => {
            // トリミングされた画像を保存するなどの処理を行う
            // 例えば、FormDataオブジェクトにクロップされた画像を追加し、サーバーに送信することができる
            const formData = new FormData();
          formData.append('image', blob, 'image.png');
          this.$emit('cropped-image', formData);
          });
        }
      }
    }
  }
  </script>
  
  <style>
  #app {
    font-family: Avenir, Helvetica, Arial, sans-serif;
    -webkit-font-smoothing: antialiased;
    -moz-osx-font-smoothing: grayscale;
    text-align: center;
    color: #2c3e50;
    margin-top: 60px;
  }
  </style>
  