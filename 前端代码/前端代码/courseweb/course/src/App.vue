<template>
  <div >

    <template v-if="!user.id">
      <Login></Login>
    </template>
    <template v-if="!!user.id">

      <Header></Header>
      <div style="display: flex">
        <Menu ></Menu>
        <router-view></router-view>
      </div>
    </template>



  </div>

</template>

<style>

</style>
<script>
import Menu from "@/components/Menu";
import Header from "@/components/Header";
import {computed} from "vue";
import store from "@/store";
import Login from "@/components/Login";
export default {
  components: {Login, Header, Menu},
  setup(){
    // 登录成功后 保存返回来的用户对象
    const user = computed(()=> store.state.user )
    return {
      user
    }

  }
}
</script>