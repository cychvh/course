<template>
  <a-sub-menu :key="menuInfo.key">
    <template #title>{{ menuInfo.title }}</template>
    <template v-for="item in menuInfo.children" :key="item.key">
      <template v-if="!item.children">
        <a-menu-item :key="item.key">

          <router-link :to="'/'+item.key"> {{ item.title }}</router-link>
        </a-menu-item>
      </template>
      <template v-else>
        <sub-menu :menu-info="item" :key="item.key" />
      </template>
    </template>
  </a-sub-menu>
</template>

<script>
import {MailOutlined, PieChartOutlined} from "@ant-design/icons-vue";

export default {
  name: "SubMenu",
  props: {
    menuInfo: {
      type: Object,
      default: () => ({}),
    },
  },
  components: {
    PieChartOutlined,
    MailOutlined,
  },
}
</script>

<style scoped>

</style>