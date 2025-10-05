import { createStore } from 'vuex'

const store = createStore({
  state: {
    user: SessionStorage.get("USER") || {}
  },
  getters: {
  },
  mutations: {

    setUser(state,user){
      state.user = user
      SessionStorage.set("USER",user)
    }


  },
  actions: {
  },
  modules: {
  }
})
export default store