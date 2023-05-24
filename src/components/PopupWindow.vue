<template>
    <div v-if="show" class="popup">
      {{ message }}
    </div>
  </template>
  
  <script>
  export default {
    data() {
      return {
        show: false,
        message: ''
      }
    },
    methods: {
      showPopup(message) {
        this.show = true;
        this.message = message;
        setTimeout(() => {
          this.show = false;
        }, 1000);
      }
    }
  }
  </script>
  
  <style>
@import url('https://fonts.googleapis.com/css2?family=Caveat&display=swap');

  .popup {
  position: absolute;
  top: 38%;
  left: 5%;
  transform: translate(-50%, -50%);
  padding: 10px;
  text-shadow: 2px 2px 0 rgba(255, 151, 189, 0.741),-2px -2px 0 rgba(1243, 227, 216), 6px 6px 0 rgba(1243, 227, 216);
  color: rgb(139, 95, 126);
  width: 300px;
  height: 50px;
  display: flex;
  justify-content: center;
  align-items: center;
  text-align: center;
  transform: rotate(-2deg);
  font-family: 'Caveat', cursive;
  font-size:90px;
}

.popup:focus {
    outline: 0;
}
  </style>
  