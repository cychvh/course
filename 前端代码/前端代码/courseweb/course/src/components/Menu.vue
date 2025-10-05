<template>
  <div style="width: 256px">

    <a-menu
        style="width: 200px;min-height: calc(100vh - 50px)"
        v-model:openKeys="openKeys"
        v-model:selectedKeys="selectedKeys"
        mode="inline"
        :inline-collapsed="collapsed"
    >

      <template v-for="item in menuList" :key="item.key">
        <template v-if="!item.children">
          <a-menu-item :key="item.key">
            <router-link :to="'/'+item.key"> {{ item.title }}</router-link>
          </a-menu-item>
        </template>
        <template v-else>
          <sub-menu :key="item.key" :menu-info="item" />
        </template>
      </template>
    </a-menu>
  </div>
</template>
<script>
import {computed, defineComponent, onMounted, ref} from 'vue';
import { MenuFoldOutlined, MenuUnfoldOutlined, PieChartOutlined, MailOutlined } from '@ant-design/icons-vue';
import SubMenu from "@/components/SubMenu";
import store from "@/store";

export default {
  components: {
    'sub-menu': SubMenu,
    MenuFoldOutlined,
    MenuUnfoldOutlined,
    PieChartOutlined,
  },
  setup() {
    // 登录成功后 保存返回来的用户对象
    const menuList = ref([
      { key: '0000', title: '首页'},
      // {
      //   key: '1000',
      //   title: '基础信息管理',
      //   children: [{ key: '1001', title: '教师管理' },
      //     { key: '1002', title: '主任管理'}],
      // },
      // {
      //   key: '2000',
      //   title: '公告信息管理',
      //   children: [{ key: '2001', title: '公告管理' }],
      // },
      // {
      //   key: '3000',
      //   title: '课程管理',
      //   children: [{ key: '3001', title: '课程管理' },
      //     { key: '3002', title: '听课管理'}],
      // },
      // {
      //   key: '4000',
      //   title: '系统管理',
      //   children: [{ key: '4001', title: '角色管理' },
      //     { key: '4002', title: '修改密码'}],
      // },


    ]);
    const user = computed(()=> store.state.user )
    const getList = () =>{

      const powerStr = user.value.power;

      const powers = user.value.power.split(",");
      console.log(powers)
      for (let i=0; i<powers.length; i++){
        const p = powers[i];
        if (p === '1001'){
          menuList.value.push ({
            key: '1000',
            title: '基础信息管理',
            children: [{
              key: '1001',
              title: '教师管理',
            }],
          });
        }
        if (p === '1002'){
          if (powerStr.indexOf("1001") === -1){
            menuList.value.push ({
              key: '1000',
              title: '基础信息管理',
              children: [{
                key: '1002',
                title: '主任管理',
              }],
            });
          }else {
            menuList.value[menuList.value.length-1].children.push({ key: '1002', title: '主任管理',})
          }



        }
        if (p === '2001'){
          menuList.value.push({
            key: '2000',
            title: '公告信息管理',
            children: [{
              key: '2001',
              title: '公告管理',
            }],
          })
        }
        if (p === '3001'){
          menuList.value.push({
            key: '3000',
            title: '课程管理',
            children: [{
              key: '3001',
              title: '课程管理',
            }],
          })
        }
        if (p === '3002'){

          if (powerStr.indexOf("3001") == -1){
            menuList.value.push ({
              key: '3000',
              title: '课程管理',
              children: [{
                key: '3002',
                title: '听课管理',
              }],
            });
          }else {
            menuList.value[menuList.value.length-1].children.push({ key: '3002', title: '听课管理',})
          }

        }

        if (p === '4001'){
          menuList.value.push({
            key: '4000',
            title: '系统管理',
            children: [{
              key: '4001',
              title: '角色管理',
            }],
          })
        }
        if (p === '4002'){

          if (powerStr.indexOf("4001") === -1){
            menuList.value.push ({
              key: '4000',
              title: '系统管理',
              children: [{
                key: '4002',
                title: '修改密码',
              }],
            });
          }else {
            menuList.value[menuList.value.length-1].children.push({ key: '4002', title: '修改密码',})
          }


        }
      }
    }


    const collapsed = ref(false);
    const toggleCollapsed = () => {
      collapsed.value = !collapsed.value;
    };

    onMounted(()=>{
      getList()
    })
    return {
      menuList,
      collapsed,
      toggleCollapsed,
      selectedKeys: ref(['0000']),
      openKeys: ref(['0000']),
    };
  },
};
</script>