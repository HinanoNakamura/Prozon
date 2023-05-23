<template>
  <div>
    <button type="button" @click="openModal">Click</button>

    <div v-if="isOpen" class="modal-overlay">
      <div class="modal-content">
        <div style="font-size: 14px; text-align: center; width: 50vw;">
          <h2>Edit Profile Image</h2>
          <hr>
          <h3>Select Image</h3>
          <input v-on:change="setImage" type="file" name="image" accept="image/*">
          <br><br>
          <div v-if="imgSrc !== ''" class="l_cropper_container">
            <vue-cropper ref="cropper" :key="imgSrc" :guides="true" :view-mode="2" :auto-crop-area="0.5"
              :min-container-width="200" :min-container-height="200" :background="true" :rotatable="false" :src="imgSrc"
              :img-style="{ 'width': '200px', 'height': '200px' }" :aspect-ratio="1" drag-mode="crop"
              :crop-shape="'round'" />

            <br>

            <button @click="cropImage" type="button" v-if="imgSrc !== ''">
              preview
            </button>
          </div>
          <br>
          <br>
          <br>
          <div v-if="cropImg !== ''">
            <img :src="cropImg" alt="Cropped Image" class="c_cropped_image">
            <br>
          </div>
        </div>
        <button @click="closeModal">completion</button>
      </div>
    </div>
  </div>
</template>
  
<script>
import VueCropper from 'vue-cropperjs';
import 'cropperjs/dist/cropper.css';
export default {
  name: 'ModalModal',
  components: {
    VueCropper
  },
  data() {
    return {
      isOpen: false,
      targetWidth: 1,
      targetHeight: 1,
      imgSrc: '',
      cropImg: '',
      filename: ''
    };
  },
  methods: {
    openModal() {
      this.isOpen = true;
    },
    closeModal() {
      const croppedImage = this.$refs.cropper.getCroppedCanvas().toDataURL();
      this.$emit('imageCropped', croppedImage);
      this.isOpen = false;
    },
    setImage(e) {
      const file = e.target.files[0];
      this.filename = file.name;
      if (!file.type.includes('image/')) {
        alert('Please select an image file');
        return;
      }
      if (typeof FileReader === 'function') {
        const reader = new FileReader();
        reader.onload = (event) => {
          this.imgSrc = event.target.result;
          this.cropImg = '';
        };
        reader.readAsDataURL(file);
      } else {
        alert('Sorry, FileReader API not supported');
      }
    },
    cropImage() {
      this.cropImg = this.$refs.cropper.getCroppedCanvas().toDataURL();
    }
  }
}
</script>
  
<style>
.modal-overlay {
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background-color: rgba(0, 0, 0, 0.5);
  display: flex;
  justify-content: center;
  align-items: center;
}

.modal-content {
  background-color: #fff;
  padding: 20px;
}

.c_cropped_image {
  width: 200px;
  height: 200px;
  border: 1px solid gray;
}

.l_cropper_container {
  width: 200px;
  height: 200px;
  border: 1px solid gray;
  display: inline-block;
}
</style>