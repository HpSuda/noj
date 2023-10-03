import Vue from 'vue'
import VueRouter from 'vue-router'
import axios from "axios";
import Main from "../views/HomePage"
import NotFound from "../views/NotFound";
import Problems from "../views/Problems";
import Problem from "../views/Problem";
import Status from  "../views/Status";
import UserPage from "../views/user/UserPage";
import Setting from "../views/user/Setting";
import Rank from "../views/Rank";

Vue.use(VueRouter)

const routes = [
  {path:'/',redirect:'/main'},
  {path:'/main',component: Main},
  {path:'/problems',component: Problems},
  {path:'/problems/:id',component: Problem},
  {path:'/status',component: Status},
  {path:'/rank',component: Rank},
  {path:'/userPage',component: UserPage},
  {path:'/setting',component: Setting},
  {path:'*',component: NotFound},
]

const router = new VueRouter({
  routes
})

//挂载路由导航守卫
// router.beforeEach((to,from,next)=>{
//   //to:去哪 from:哪来 next:放行函数
//   if(to.path==='/main') return next()
//   //获取token
//   const tokenStr = JSON.parse(window.sessionStorage.getItem('token'))
//   if(!tokenStr){
//     this.$message.error('请先登录')
//     return next('/main')
//   }
//   //校验token合法性
//   axios({
//     url:'http://localhost:9090/ '
//   })
//   next();
// })

export default router
