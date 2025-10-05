import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import antd from "ant-design-vue"
import 'ant-design-vue/dist/antd.css'
import axios from "axios";
import {isEmpty} from "@/util/Tool";
createApp(App).use(store).use(router).use(antd).mount('#app')
// axios 拦截器
// 请求拦截器
axios.interceptors.request.use(
    (config)=>{

        console.log("请求参数：",config)
        const token = store.state.user.token;
        console.log(token)
        if (!isEmpty(token)){
            // 有token 加到请求头
            config.headers.token = token;
        }
        return config;
    },
    (error)=>{
        return Promise.reject(error)
    }

)

axios.interceptors.response.use(
    (response)=>{
        console.log("返回结果：",response)
        return response;
    },
    error => {
        console.log("返回错误：",error)
        return Promise.reject(error)
    }
)