<template>
  <div style="height: 50px;line-height: 50px;border-bottom: 1px solid #ccc; display: flex "  >
    <div style="width: 300px; padding-left: 30px; font-weight: bold; color: dodgerblue" >课程信息管理</div>
    <div style="flex: 1" ></div>
    <div style="width: 150px" >
      <a-dropdown>
        <a class="ant-dropdown-link" @click.prevent>
          {{user.id }}
          <DownOutlined  />
        </a>
        <template #overlay>
          <a-menu>
            <a-menu-item>
              <a @click="loginOut" >退出登录</a>
            </a-menu-item>
          </a-menu>
        </template>
      </a-dropdown>
    </div>
  </div>


</template>

<script>
import {computed, ref} from "vue"
import axios from "axios";
import {message} from "ant-design-vue";
import store from "@/store";
import { DownOutlined } from '@ant-design/icons-vue';
export default {
  name: "Header",
  components: {
    DownOutlined,
  },

  setup(){

    // 登录成功后 保存返回来的用户对象
    const user = computed(()=> store.state.user )



    // 退出登录
    const loginOut = ()=>{
      console.log("loginout")
      // 发送请求
      axios.get("api/loginOut/"+user.value.token).then(res=>{
        if (res.data.code === "200"){
          store.commit("setUser", {})
          message.success(res.data.msg);
        }else {
          message.error(res.data.msg);
        }
      })
    }

    return {
      user,
      loginOut
    }
  }
}
</script>

<style scoped>

</style>