<template>

<!--  <a-table :dataSource="teacher" :columns="columns" />-->
<!--        表格-->

  <div  style="margin: 15px; width: 1000px">


    <a-form
        layout="inline"
        :model="state"
    >
      <a-form-item>
        <a-input v-model:value="state.name" placeholder="名称">
        </a-input>
      </a-form-item>
      <a-form-item>
        <a-button type="primary" @click="load" >查询</a-button>
      </a-form-item>
      <a-form-item>
        <a-button type="primary" @click="add" >新增</a-button>
      </a-form-item>
    </a-form>



    <a-table :dataSource="teacher" :columns="columns" :pagination="pagination"  @change="handleTableChange">>
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
      <a-form-item label="编号"  >
        <a-input v-model:value="formState.id1" :disabled="!!formState.id"/>
      </a-form-item>

      <a-form-item label="姓名">
        <a-input v-model:value="formState.name" />
      </a-form-item>


      <a-form-item label="性别"  >
        <a-input v-model:value="formState.sex"  />
      </a-form-item>
      <a-form-item label="联系方式"  >
        <a-input v-model:value="formState.mobile"  />
      </a-form-item>

    </a-form>

  </a-modal>

</template>

<script>
import { message } from 'ant-design-vue';
import axios from "axios"
import {onMounted, ref} from "vue";
export default {
  name: "Teacher",
  setup(){
    const state = ref({})
    const teacher = ref();
    const columns = ref([
      {
        title: '编号',
        dataIndex: 'id',
        key:'id'
      },
      {
        title: '姓名',
        dataIndex: 'name',
        key:"name"
      },
      {
        title: '性别',
        dataIndex: 'sex',
        key: 'sex'
      },
      {
        title: '联系方式',
        dataIndex: 'mobile',
        key:"mobile"
      },
      {
        title: '操作',
        key: 'action'
      }

    ]);

    // 分页对象
    const pagination = ref({
      total:'',
      current:1,
      pageSize:2
    })

    // 表单
    const formState = ref({})
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

    // 查询方法
    const load = ()=>{
      axios.get("api/findTeacher",{
        params:{
          pageNum:pagination.value.current,
          pageSize: pagination.value.pageSize,
          name:state.value.name
        }
      }).then(res=>{
        if (res.data.code === "200"){
          teacher.value = res.data.date.list
          pagination.value.total = res.data.date.total
        }else {
          message.error(res.data.msg);
        }
      })
    }

    // 页码发生改变时会调用这个方法
    const handleTableChange = (pagination2)=>{
      pagination.value = pagination2
      console.log(pagination.value)
      load()
    }
    // 对话框ok时会调用
    const handleOk = () => {
      confirmLoading.value = true;  // 开启确认按钮的加载
      formState.value.password = '123456';
      formState.value.type = "1";
      formState.value.roleid = "0001";
      formState.value.id = formState.value.id1
      // 发送请求
      axios.post("api/saveUser",formState.value).then(res=>{
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
      axios.delete("api/delUser/" + id).then(res=>{
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
      teacher,

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
      pagination,
      load,
      state,
      handleTableChange
    }
  }
}
</script>

<style scoped>

</style>