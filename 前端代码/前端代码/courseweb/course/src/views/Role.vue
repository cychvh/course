<template>
  <div  style="margin: 15px; width: 1000px">

    <a-table :dataSource="roles" :columns="columns" :pagination="false">
      <template #bodyCell="{ column, record }">

        <!--        操作-->
        <template v-if="column.key === 'action'">
          <a-space>

            <a-button type="primary" @click="edit(record)">编辑</a-button>
          </a-space>
        </template>

      </template>
    </a-table>
  </div>


  <a-modal
      v-model:visible="visible"
      title="表单"
      :confirm-loading="confirmLoading"
      @ok="handleOk"
  >
    <!--      <p> 表单 </p>-->
    <a-form :model="formState" :label-col="labelCol" :wrapper-col="wrapperCol">
      <a-form-item label="角色ID"   >
        <a-input v-model:value="formState.roleid" />
      </a-form-item>

      <a-form-item label="角色名称">
        <a-input v-model:value="formState.rolename" />
      </a-form-item>
      <a-form-item label="角色权限">
        <a-checkbox-group v-model:value="formState.power" :options="power" />
      </a-form-item>



    </a-form>

  </a-modal>

</template>

<script>
import {onMounted, ref} from "vue";
import axios from "axios";
import {message} from "ant-design-vue";


export default {
  name: "Role",
  setup(){
    const roles = ref([]);
    const columns = ref([
      {
        title: '角色ID',
        dataIndex: 'roleid',
        key:'roleid'
      },
      {
        title: '角色名称',
        dataIndex: 'rolename',
        key:'rolename'
      },
      {
        title: '操作',
        key: 'action'
      }


    ]);

    const power = ref([
      { label: '教师管理', value: '1001' },
      { label: '主任管理', value: '1002' },
      { label: '公告管理', value: '2001' },
      { label: '课程管理', value: '3001' },
      { label: '听课管理', value: '3002' },
      { label: '角色管理', value: '4001' },
      { label: '修改密码', value: '4002' },
    ]);
    // 查询方法
    const load = ()=>{
      axios.get("api/queryRole",{}).then(res=>{
        console.log(res)
        if (res.data.code === "200"){
          roles.value = res.data.date
        }else {
          message.error(res.data.msg);
        }
      })
    }

    const formState = ref({});
    const visible = ref(false);  // 对话框的开关
    const confirmLoading = ref(false); // 确认按钮的加载
    // 编辑的点击事件
    const edit = (record) => {
      formState.value = JSON.parse(JSON.stringify(record));
      const powers = formState.value.power;
      formState.value.power = powers.split(",");
      visible.value = true;
    };

    // 对话框ok时会调用
    const handleOk = () => {
      confirmLoading.value = true;  // 开启确认按钮的加载
      formState.value.power =  formState.value.power.toString();
      axios.put("api/editRole",formState.value).then(res=>{
        confirmLoading.value = false;
        visible.value = false;
        console.log(res)
        if (res.data.code === "200"){
          load()
          message.success(res.data.msg);
        }else {
          message.error(res.data.msg);
        }
      })


    }

    onMounted(()=>{
      load()
    })
    return {
      roles,
      columns,

      labelCol: { style: { width: '130px' } },
      wrapperCol: { span: 14 },
      formState,
      visible,
      confirmLoading,
      edit,
      power,

      handleOk
    }
  }
}
</script>

<style scoped>

</style>