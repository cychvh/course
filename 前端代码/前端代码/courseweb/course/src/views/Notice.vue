<template>

<!--  <a-table :dataSource="teacher" :columns="columns" />-->
<!--        表格-->
  <div  style="margin: 15px; width: 1000px">
    <a-button type="primary" @click="add" >新增</a-button>

  <a-table :dataSource="notice" :columns="columns" :pagination="false">
    <template #bodyCell="{ column, record }">

      <!--        操作-->
      <template v-if="column.key === 'action'">
        <a-space>

          <a-button type="primary" @click="edit(record)">编辑</a-button>
          <a-popconfirm
              title="是否确认删除?"
              ok-text="是"
              cancel-text="否"
              @confirm="del(record.id)"
          >
            <a-button type="primary" danger>删除</a-button>
          </a-popconfirm>
        </a-space>
      </template>

    </template>
  </a-table>
  </div>


  <a-modal
      v-model:visible="visible"
      title="Title"
      :confirm-loading="confirmLoading"
      @ok="handleOk"
  >
    <!--      <p> 表单 </p>-->
    <a-form :model="formState" :label-col="labelCol" :wrapper-col="wrapperCol">
      <a-form-item label="编号" v-show="!!formState.id"  >
        <a-input v-model:value="formState.id1" :disabled="!!formState.id" />
      </a-form-item>

      <a-form-item label="标题">
        <a-input v-model:value="formState.title" />
      </a-form-item>


      <a-form-item label="内容">
        <a-textarea v-model:value="formState.content" :rows="4" />
      </a-form-item>


    </a-form>

  </a-modal>

</template>

<script>
import { message } from 'ant-design-vue';
import axios from "axios"
import {onMounted, ref,computed} from "vue";
import store from "@/store";
export default {
  name: "Course",
  setup(){

    const user = computed(()=> store.state.user )
    const notice = ref();
    const columns = ref([
      {
        title: '编号',
        dataIndex: 'id',
        key:'id'
      },
      {
        title: '标题',
        dataIndex: 'title',
        key:"title"
      },
      {
        title: '创建人',
        dataIndex: 'userid',
        key: 'userid'
      },
      {
        title: '内容',
        dataIndex: 'content',
        key: 'content'
      },
      {
        title: '操作',
        key: 'action'
      }
    ]);

    // 查询方法
    const load = ()=>{
      console.log("load ")
      axios.get("api/queryNotice",{}).then(res=>{
        console.log(res)
        if (res.data.code === "200"){
          notice.value = res.data.date
        }else {
          message.error(res.data.msg);
        }
      })
    }




    // 表单
    const formState = ref({});
    const visible = ref(false);  // 对话框的开关
    const confirmLoading = ref(false); // 确认按钮的加载


    // 编辑的点击事件
    const edit = (record) => {
      // JSON.stringify(record) 转为为字符串
      // JSON.parse(“str“) 转为json对象

      formState.value = JSON.parse(JSON.stringify(record));
      formState.value.id1 = record.id
      visible.value = true;
    };

    // 新增的点击事件
    const add = () => {

      formState.value = {}; // 清空表单数据
      visible.value = true; // 打开对话框
    };


    // 对话框ok时会调用
    const handleOk = () => {
      confirmLoading.value = true;  // 开启确认按钮的加载
      formState.value.id = formState.value.id1
      formState.value.userid = user.value.id
      // 发送请求
      axios.post("api/saveNotice",formState.value).then(res=>{
        console.log(res)
        confirmLoading.value = false; // 关闭确认按钮的加载
        visible.value = false;  // 关闭对话框
        if (res.data.code === "200"){
          load()
          message.success(res.data.msg);
        }else {
          message.error(res.data.msg);
        }
      })
    }

    // 删除的点击事件
    const del = (id) => {
      // 发送请求
      axios.delete("api/delNotice/" + id).then(res=>{
        console.log(res)
        if (res.data.code === "200"){
          // 重新加载数据
          load()
          message.success(res.data.msg);
        }else {
          message.error(res.data.msg);
        }
      })
    };

    onMounted(()=>{

      load()
    })

    return {
      columns,
      notice,


      // 表单
      labelCol: { style: { width: '130px' } },
      wrapperCol: { span: 14 },
      formState,
      visible,
      confirmLoading,
      edit,
      add,
      handleOk,
      del,
    }
  }
}
</script>

<style scoped>

</style>