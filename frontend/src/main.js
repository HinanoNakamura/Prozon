import Vue from 'vue';

import App from './App.vue';
import router from './router';
import store from './store';
import VueRouter from 'vue-router'
import Cropper from 'cropperjs';
import Croppa from 'vue-croppa'
import 'vue-croppa/dist/vue-croppa.css'

Vue.use(Croppa)
Vue.prototype.$cropper = Cropper;

Vue.use(VueRouter)

Vue.config.productionTip = false;

new Vue({
  router,
  store, // Vuexストアを使用することを設定
  render: (h) => h(App),
}).$mount('#app');