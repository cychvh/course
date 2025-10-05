const { defineConfig } = require('@vue/cli-service')
module.exports = defineConfig({
  transpileDependencies: true,
  lintOnSave:false,
  devServer:{
    proxy:{
      "/api":{
        target:"http://localhost:8089/",// 代理的目标地址
        changeOrigin:true, // 配置同源
        pathRewrite:{ // 路径重写
          '/api':''
        }
      }
    }
  }
})
