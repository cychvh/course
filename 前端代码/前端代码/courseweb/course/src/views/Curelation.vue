<template>


  <div  style="margin: 15px; width: 1000px">


    <a-button type="primary" @click="listening" >听课</a-button>


    <a-table :dataSource="curelation" :columns="columns" :pagination="false">
      <template #bodyCell="{ column, record }">

        <template v-if="column.key === 'userid'">
           {{ getUser(record.userid) }}
        </template>

        <!--        操作-->
        <template v-else-if="column.key === 'action'">
          <a-space>

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
      title="听课安排"
      :confirm-loading="confirmLoading"
      @ok="handleOk"
  >
    <!--      <p> 表单 </p>-->
    <a-form :model="formState" :label-col="labelCol" :wrapper-col="wrapperCol">
      <a-form-item label="课程名称" >
        <a-select
            ref="select"
            v-model:value="formState.courseid"
        >
          <a-select-option v-for="c in course"  :key="c.id" :value="c.id">
            {{ c.coursename }}
          </a-select-option>

        </a-select>
      </a-form-item>
      <a-form-item label="听课教师" >
        <a-select
            ref="select"
            v-model:value="formState.name"
        >
          <a-select-option v-for="t in teacher"  :key="t.id" :value="t.name">
            {{ t.name }}
          </a-select-option>

        </a-select>
      </a-form-item>

    </a-form>

  </a-modal>

</template>

<script>
import {onMounted, ref} from "vue";
import axios from "axios";
import {message} from "ant-design-vue";

export default {
  name: "Curelation",
  setup(){
    const curelation = ref();

    const columns = ref([
      {
        title: '编号',
        dataIndex: 'id',
        key:'id'
      },
      {
        title: '课程名称',
        dataIndex: 'type',
        key:"type"
      },
      {
        title: '听课人员',
        dataIndex: 'name',
        key:'name'
      },
      {
        title: '操作',
        key: 'action'
      }

    ]);

    // 查询方法
    const load = ()=>{
      console.log("load ")
      axios.get("api/queryCurelation",{}).then(res=>{
        console.log(res)
        if (res.data.code === "200"){
          curelation.value = res.data.date
        }else {
          message.error(res.data.msg);
        }
      })
    }

    let teacher = ref([]);
    // 查询方法
    const queryTeacher = ()=>{
      axios.get("api/queryTeacher",{}).then(res=>{
        console.log(res)
        if (res.data.code === "200"){
          teacher.value = res.data.date
        }else {
          message.error(res.data.msg);
        }
      })
    }

    const course = ref([]);
    // 查询所有课程
    const queryCourse = ()=>{
      axios.get("api/queryCourse",{}).then(res=>{
        console.log(res)
        if (res.data.code === "200"){
          course.value = res.data.date
        }else {
          message.error(res.data.msg);
        }
      })
    }


    // 听课人员
    const getUser = (userid)=>{
      console.log('getUser' , userid)
      for (let i=0; i<teacher.value.length; i++){
        const t = teacher.value[i];
        if (t.id === userid   ){
          return t.name;
        }
      }
    }


    const formState = ref({})
    const visible = ref(false);  // 对话框的开关
    const confirmLoading = ref(false); // 确认按钮的加载


    // 新增的点击事件
    const listening = () => {
      formState.value = {}; // 清空表单数据
      visible.value = true; // 打开对话框
    };


    // 对话框ok时会调用
    const handleOk = () => {

      visible.value = false;
      axios.post("api/listening",formState.value).then(res=>{
        if (res.data.code === "200"){
          message.success(res.data.msg);
          formState.value = {}
          load();
        }else {
          message.error(res.data.msg);
        }
      })
    }

    // 删除的点击事件
    const del = (id) => {
      // 发送请求
      axios.delete("api/delCurelation/" + id).then(res=>{
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
      queryTeacher()
      queryCourse()
    })


    return {
      curelation,
      columns,
      getUser,

      // 表单
      labelCol: { style: { width: '130px' } },
      wrapperCol: { span: 14 },
      formState,
      visible,
      confirmLoading,
      listening,
      teacher,
      course,
      handleOk,

      del
    }
  }
}
</script>

<style scoped>

</style>