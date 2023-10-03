import  Vue from 'vue'
import Vuex from 'vuex'

//在vue实例上注册$store属性,下边export暴露,main.js中的new vue里赋值
Vue.use(Vuex)
//创建全局状态变量
const ifLogged = !!sessionStorage.getItem("token")

const store = new Vuex.Store({
    state:{
        loginStatus:ifLogged,
        loginDialogVisible:false,
    }
})

export default store