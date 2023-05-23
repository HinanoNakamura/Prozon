import Vue from 'vue';
import Vuex from 'vuex';
import createPersistedState from 'vuex-persistedstate'

Vue.use(Vuex);

export default new Vuex.Store({
  state: {
    userId: '',
    loggin: false,
    cartItems: [],
    flavorCode:'',
    id:'',
    detailId:'',
  },
  plugins: [createPersistedState(
    {paths :[
    'userId','flavorCode','id']}
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