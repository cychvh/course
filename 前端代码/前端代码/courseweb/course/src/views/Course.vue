<template>

<!--  <a-table :dataSource="teacher" :columns="columns" />-->
<!--        表格-->
  <div  style="margin: 15px; width: 1000px">


    <a-button type="primary" @click="add" >新增</a-button> &nbsp;&nbsp;
    <a-button type="primary" @click="arranging" >排课</a-button>


    <a-table :dataSource="course" :columns="columns" :pagination="false">
    <template #bodyCell="{ column, record }">

      <template  v-if="column.key === 'courseteacher'">
        {{ getTeacher(record.courseteacher) }}
      </template>

      <!--        操作-->
      <template v-else-if="column.key === 'action'">
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
      title="表单"
      :confirm-loading="confirmLoading"
      @ok="handleOk"
  >
    <!--      <p> 表单 </p>-->
    <a-form :model="formState" :label-col="labelCol" :wrapper-col="wrapperCol">
      <a-form-item label="课程编号" v-show="!!formState.id"  >
        <a-input v-model:value="formState.id1" />
      </a-form-item>

      <a-form-item label="课程名称">
        <a-input v-model:value="formState.coursename" />
      </a-form-item>


      <a-form-item label="授课教师"  >

        <a-select
            ref="select"
            v-model:value="formState.courseteacher"
        >
          <a-select-option v-for="t in teacher"  :key="t.id" :value="t.id">
            {{ t.name }}
          </a-select-option>

        </a-select>

      </a-form-item>
      <a-form-item label="学生数量"  >
        <a-input v-model:value="formState.coursenum"  />
      </a-form-item>

    </a-form>

  </a-modal>

  <a-modal
      v-model:visible="arrangingVisible"
      title="排课"
      :confirm-loading="arrangingConfirmLoading"
      @ok="arrangingHandleOk"
  >

    <a-form
        :model="arrangingformState"
        name="basic"
        :label-col="{ span: 8 }"
        :wrapper-col="{ span: 12 }"
        autocomplete="off"
    >
    <a-form-item label="课程名称" >
      <a-select
          ref="select"
          v-model:value="arrangingformState.id"
      >
        <a-select-option v-for="c in course"  :key="c.id" :value="c.id">
          {{ c.coursename }}
        </a-select-option>

      </a-select>
    </a-form-item>

    <a-form-item  label="上课日期" >
      <a-select
          mode="multiple"
          ref="select"
          v-model:value="arrangingformState.date1"
      >
        <a-select-option value="星期一"> 星期一</a-select-option>
        <a-select-option value="星期二"> 星期二</a-select-option>
        <a-select-option value="星期三"> 星期三</a-select-option>
        <a-select-option value="星期四"> 星期四</a-select-option>
        <a-select-option value="星期五"> 星期五</a-select-option>
        <a-select-option value="星期六"> 星期六</a-select-option>
        <a-select-option value="星期日"> 星期日</a-select-option>

      </a-select>
    </a-form-item>
    <a-form-item  label="上课时间" >
      <a-select
          mode="multiple"
          ref="select"
          v-model:value="arrangingformState.date2"
      >
        <a-select-option value="1-2"> 1-2</a-select-option>
        <a-select-option value="3-4"> 3-4</a-select-option>
        <a-select-option value="5-6"> 5-6</a-select-option>
        <a-select-option value="7-8"> 7-8</a-select-option>
        <a-select-option value="9-10"> 9-10</a-select-option>
        <a-select-option value="11-12"> 11-12</a-select-option>

      </a-select>
    </a-form-item>
    <a-form-item  label="上课教室" >
      <a-select
          mode="multiple"
          ref="select"
          v-model:value="arrangingformState.courseroom"
      >
        <a-select-option value="A教室"> A教室</a-select-option>
        <a-select-option value="B教室"> B教室</a-select-option>
        <a-select-option value="C教室"> C教室</a-select-option>
        <a-select-option value="D教室"> D教室</a-select-option>

      </a-select>
    </a-form-item>


    </a-form>


  </a-modal>



</template>

<script>
import { message } from 'ant-design-vue';
import axios from "axios"
import {onMounted, ref} from "vue";
export default {
  name: "Course",
  setup(){
    const course = ref();
    const columns = ref([
      {
        title: '课程编号',
        dataIndex: 'id',
        key:'id'
      },
      {
        title: '课程名称',
        dataIndex: 'coursename',
        key:"coursename"
      },
      {
        title: '授课教师',
        dataIndex: 'courseteacher',
        key: 'courseteacher'
      },
      {
        title: '学生数量',
        dataIndex: 'coursenum',
        key:"coursenum"
      },
      {
        title: '上课时间',
        dataIndex: 'coursedate',
        key:"coursedate"
      },
      {
        title: '上课教室',
        dataIndex: 'courseroom',
        key:"courseroom"
      },
      {
        title: '操作',
        key: 'action'
      }


    ]);

    // 查询方法
    const load = ()=>{
      console.log("load ")
      axios.get("api/queryCourse",{}).then(res=>{
        console.log(res)
        if (res.data.code === "200"){
          course.value = res.data.date
        }else {
          message.error(res.data.msg);
        }
      })
    }




    // 表单
    const teacher = ref([]);
    const formState = ref({});
    const visible = ref(false);  // 对话框的开关
    const confirmLoading = ref(false); // 确认按钮的加载


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

    const getTeacher = (courseteacher)=>{
      for (let i = 0; i < teacher.value.length; i++){
        const t = teacher.value[i];
        if (courseteacher === t.id){
          return t.name;
        }
      }
    }

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
      // 发送请求
      axios.post("api/saveCourse",formState.value).then(res=>{
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
      axios.delete("api/delCourse/" + id).then(res=>{
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


    // 排课
    const arrangingformState = ref({});
    const arrangingVisible = ref(false);  // 对话框的开关
    const arrangingConfirmLoading = ref(false); // 确认按钮的加载

    const arranging = () =>{
      arrangingVisible.value = true;
    }

    // 排课
    const arrangingHandleOk = () => {
      arrangingConfirmLoading.value = true;  // 开启确认按钮的加载

      axios.post("api/arranging",arrangingformState.value).then(res=>{
        arrangingVisible.value = false;
        arrangingConfirmLoading.value = false;
        console.log(res)
        if (res.data.code === "200"){
          load();
          message.success(res.data.msg);
          arrangingformState.value = {}
        }else {
          message.error(res.data.msg);
        }
      })
    }


    onMounted(()=>{
      queryTeacher()
      load()
    })

    return {
      columns,
      course,
      getTeacher,

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
      teacher,

      // 排课
      arranging,
      arrangingVisible,
      arrangingConfirmLoading,
      arrangingHandleOk,
      arrangingformState
    }
  }
}
</script>

<style scoped>

</style>