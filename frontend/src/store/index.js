import Vue from 'vue';
import Vuex from 'vuex';
import createPersistedState from 'vuex-persistedstate'

Vue.use(Vuex);

export default new Vuex.Store({
  state: {
    userId: '',
    loggin: false,
    cart: [],
    flavorCode:'',
    id:'',
    detailId:'',
    count:'',

  },
  plugins: [createPersistedState(
    {paths :[
    'userId','flavorCode','id','count','cart',' detailId']}
  )],
  actions: {
    setUserid(context, value) {
      context.commit('SETID', value)
    },
    setLoggin(context, value) {
      context.commit('SETLOG', value)
    },
    checkAuth({ commit }) {
      const userId = localStorage.getItem('userId');
      const loggin = localStorage.getItem('loggin');
      if (userId && loggin) {
        commit('SETID', userId);
        commit('SETLOG', true);
      }
    },
    logout({ commit }) {
      commit('SETID', '');
      commit('SETLOG', false);
      commit('deletecart');
      localStorage.removeItem('userId');
      localStorage.removeItem('loggin');
    },
    addToCart({ commit }, payload) {
      commit('addToCart', payload);
    },
    setDetailId(state,id){
      state.detailId =id
    },

  },
  mutations: {
    SETID(state, value) {
      // return 
      state.userId = value;
      localStorage.setItem('userId', value);
    },
    setDetailId(state, id) {
      state.detailId = id;
    },
    SETLOG(state, value) {
      // return 
      state.loggin = value;
    localStorage.setItem('loggin', value);
    },
    LOGOUT(state) {
      state.userId = '';
      state.cart = [];
      state.isLoggedIn = false;
      localStorage.removeItem('userId');
      localStorage.removeItem('loggin');
    },
    addToCart(state, payload) {
      state.cartItems.push(payload);
    },
    setFlavorCode(state, flavorCode) {
      state.flavorCode = flavorCode
    },
    setId(state,id){
      state.id =id
    },
    saveCart(state, cart) {

  
    
      const item = state.cart.find(item => item.cartname === cart.cartname);

      if (item) {
        const value = Number(cart.cartquantity)
        console.log("通信成功" + item.cartquantity)
       
         item.cartquantity = value + Number(item.cartquantity);
      }else{
        console.log("通信失敗")
        state.cart.unshift(cart)
        cart.id = ++state.count
      }
    },
    delete(state, cartId) {
      state.cart = state.cart.filter(cart => cart.id !== cartId)
    },
    refreshquantity(state,sub){
      console.log("おっぱっぴー" + sub.value + sub.name)
      const item = state.cart.findIndex(item => item.cartname === sub.name);
      if (item !== -1) {
         state.cart[item].cartquantity = sub.value
      }
    }
    
  },
  getters: {
    returnlog: (state) => {
      return state.loggin
    },
    getCartItems(state) {
      return state.cartItems; 
    },
  }
});