<template>

  <div style=" padding: 30px">
    <h3>系统公告</h3>

    <template v-if="!notice.title">
      <div style="width: 1000px">
        <h1 style="text-align: center">暂无公告</h1>
      </div>

    </template>

    <template v-if="!!notice.title">
      <a-card :title="notice.title" :bordered="false" style="background: #ececec;width: 1000px">
        <h1 style="text-align: center">{{notice.content}}</h1>
        <p style="text-align: right">创建人：{{notice.userid}}</p>
        <p style="text-align: right">创建时间：{{notice.creatdate}}</p>
      </a-card>
    </template>

  </div>

</template>

<script>
import { message } from 'ant-design-vue';
import axios from "axios"
import {onMounted, ref} from "vue";
export default {
  name: "Welcome",
  setup(){
    const notice = ref({});
    // 查询方法
    const load = ()=>{
      console.log("load ")
      axios.get("api/queryNotice",{}).then(res=>{
        console.log(res)
        if (res.data.code === "200"){
          notice.value = res.data.date[0]
          console.log(notice)
        }else {
          message.error(res.data.msg);
        }
      })
    }

    onMounted(()=>{

      load()
    })


    return {
      notice,
    }
  }
}
</script>

<style scoped>

</style>