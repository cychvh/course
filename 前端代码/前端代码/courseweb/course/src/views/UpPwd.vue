<template>
  <div  style="margin: 15px; width: 1000px">

    <h1 style="text-align: center">修改密码</h1>

    <a-form
        :model="formState"
        name="basic"
        :label-col="{ span: 7 }"
        :wrapper-col="{ span: 12 }"
        autocomplete="off"
        @finish="onFinish"
        @finishFailed="onFinishFailed"
    >
      <a-form-item
          label="旧密码"
          name="oldPassword"
      >
        <a-input-password  v-model:value="formState.oldPassword" />
      </a-form-item>

      <a-form-item
          label="新密码"
          name="newPassword"
      >
        <a-input-password v-model:value="formState.newPassword" />
      </a-form-item>
      <a-form-item
          label="确认密码"
          name="newPassword2"
      >
        <a-input-password v-model:value="formState.newPassword2" />
      </a-form-item>

      <a-form-item :wrapper-col="{ offset: 11, span: 13 }">
        <a-button type="primary" html-type="submit">提交</a-button>
      </a-form-item>
    </a-form>


  </div>
</template>

<script>
import {ref} from "vue";
import {message} from "ant-design-vue";
import axios from "axios";

export default {
  name: "UpPwd",
  setup(){
    const formState = ref({});
    const onFinish = values => {
      console.log('Success:', values);
      // console.log("---",formState.value)

      if (formState.value.newPassword == formState.value.newPassword2){
        axios.post("api/upPwd",{},{
          params:{
            oldPwd: formState.value.oldPassword,
            newPwd: formState.value.newPassword
          }
        }).then(res=>{
          console.log(res)
          if (res.data.code === "200"){
            message.success(res.data.date);
            formState.value = {}
          }else {
            message.error(res.data.date);
          }
        })
      }else {
        message.error("新密码与确认密码不一致");
      }

    };
    const onFinishFailed = errorInfo => {
      console.log('Failed:', errorInfo);
    };
    return {
      formState,
      onFinish,
      onFinishFailed,
    };
  }
}
</script>

<style scoped>

</style>