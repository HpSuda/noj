import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import './plugins/element.js'
import './styles/main.less'
//导入全局样式表
import './assets/css/global.css'
import axios from "axios";
axios.defaults.baseURl=''
//拦截请求，给请求头添加token
// axios.interceptors.request.use(config=>{
//   console.log(config) //
//   config.headers.Authorization = window.sessionStorage.getItem('token')
//   //挂载结束返回config值
//   return config
// })
import echarts from 'echarts'
Vue.prototype.$echarts = echarts

Vue.prototype.$http = axios

Vue.config.productionTip = false



new Vue({
  store,
  router,
  render: h => h(App)
}).$mount('#app')
