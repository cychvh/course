<template>

  <div class="body">
    <div class="container-fluid my-container" id="large-header">

      <div class="box">
        <!-- logo -->
        <div class="logo">

        </div>
        <div class="form">
          <a-form
              :model="loginUser"
              name="basic"
              :label-col="{ style: { width: '120px' } }" :wrapper-col="{ span: 14 }"
              autocomplete="off"
              @finish="onFinish"
              @finishFailed="onFinishFailed"
          >
            <a-form-item
                label="登录名"
                name="userId"
                :rules="[{ required: true, message: '请输入登录名！' }]"
            >
              <a-input v-model:value="loginUser.userId" />
            </a-form-item>

            <a-form-item
                label="密码"
                name="password"
                :rules="[{ required: true, message: '请输入密码！' }]"
            >
              <a-input-password v-model:value="loginUser.password"  />
            </a-form-item>

            <a-form-item :wrapper-col="{ offset: 10, span: 15 }">
              <a-button type="primary" html-type="submit">登录</a-button>
            </a-form-item>
          </a-form>
        </div>

      </div>
    </div>
  </div>

</template>

<script>
import { defineComponent, ref } from 'vue';
import axios from "axios";
import store from "@/store";
import {message} from "ant-design-vue";
export default {
  name: "Login",
  setup() {
    const loginUser = ref({});
    const onFinish = values => {
      console.log('Success:', values);
      axios.post("api/login",{},{
        params:{
          userId: loginUser.value.userId,
          password: loginUser.value.password
        }

      }).then(res=>{
        console.log(res)
        if (res.data.code === "200"){
          // user.value = res.data.date
          // 把user对象保存到state
          store.commit("setUser",res.data.date)
          message.success(res.data.msg);
        }else {
          message.error(res.data.msg);
        }
      })


    };
    const onFinishFailed = errorInfo => {
      console.log('Failed:', errorInfo);
    };
    return {
      loginUser,
      onFinish,
      onFinishFailed,
    };
  },
}
</script>

<style scoped>
.body {
  width: 100%;
  height: 100vh;
}

.my-container {
  height: 100vh;
  background-image: url("../../public/img/bg.jpeg");
  background-repeat: no-repeat;
  background-size: 100% 100%;
}

.box {
  position: absolute;
  left: 420px;
  width: 460px;
  height: 380px;
  background-color: #ffffffaa;
  margin: 180px auto;
  border-radius: 5px;
  z-index: 999;
}

.logo {
  width: 100%;
  height: 80px;
  padding: 10px 5px 5px;
  background-image: url("../../public/img/logo.png");
  background-size: 100% 100%;
}
.form {
  width: 100%;
  height: calc(440px - 80px);
  padding: 55px 56px 10px 10px;
  margin-left: 23px;
}

</style>