<template>
  <div>
    <el-menu :default-active="'/'+this.$route.path.split('/')[1]"
             class="el-menu-demo"
             @select="handleSelect"
             mode="horizontal" router>
    <!--  左侧导航栏  -->
      <div class="menu-title">
        <span class="t1">NJ</span>
        <span class="t2">UST</span>
        <span class="t3">OJ</span>
      </div>
      <el-menu-item index="/main" class="el-icon-s-home"> 主页</el-menu-item>
      <el-menu-item index="/problems" class="el-icon-s-grid"> 题库</el-menu-item>
      <el-menu-item index="/status" class="el-icon-upload"> 提交记录</el-menu-item>
      <el-menu-item index="/rank" class="el-icon-s-data"> 排行榜</el-menu-item>
    <!--   右侧按钮   -->
      <template v-if="!logged">
        <div class="menu-btn"><register></register></div>
        <div class="menu-btn"><login></login></div>
      </template>
      <template v-else>
        <el-menu-item index="" style="float:right" >
          <el-dropdown size="medium" trigger="click" placement="bottom" @command="handleCommand">
            <el-button type="text" class="el-dropdown-link">kevinXXX
              <i class="el-icon-arrow-down el-icon&#45;&#45;right"></i>
            </el-button>
            <el-dropdown-menu slot="dropdown">
              <el-dropdown-item command="/userPage">主页</el-dropdown-item>
              <el-dropdown-item command="/status">提交记录</el-dropdown-item>
              <el-dropdown-item command="/setting">设置</el-dropdown-item>
              <el-dropdown-item divided command="logout">登出</el-dropdown-item>
            </el-dropdown-menu>
          </el-dropdown>
        </el-menu-item>
      </template>
   </el-menu>
  </div>
</template>

<script>
import login from "./login"
import Register from "@/components/register";
export default {
  name: "NaviBar",
  components: {
    Register,
    login,
  },
  data() {
    return {
      //当前页面地址拆去'/'后的数组取第二个加上'/'，即router路径名
       activeIndex: '/' + this.$route.path.split('/')[1],
    };
  },
  computed:{
    logged:{
      set(val){},
      get(){
        return this.$store.state.loginStatus
      },
    }
  },
  methods: {
    handleSelect(key, keyPath) {
      console.log(key, keyPath);
    },
    handleCommand(command) {
      if(command === 'logout') this.logout()
      else {
        this.$router.push(command);
      }
    },
    //登出函数
    logout(){
      console.log('logout');
      window.sessionStorage.clear();
      //应该对应改变导航栏中个人信息位置
      this.$store.state.loginStatus = false;
      this.$router.push('/main');
    }
  }
}
</script>

<style scoped>
.el-menu-demo{
  /*box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);*/
  box-shadow: 0 2px 4px rgba(0, 0, 0, .12), 0 0 6px rgba(0, 0, 0, .04)
}
.el-dropdown-link {
  font-size: 20px;
  cursor: pointer;
  color: #3b3b3b;
}
.el-icon-arrow-down {
  font-size: 12px;
}
.menu-btn{
  float: right;
  padding: 0.70% 0.6% 0.60% 0%;
}
.menu-title{
  /*font-weight:bold;*/
  font-size: 18px;
  margin-left: 1.5%;
  margin-right: 1%;
  float: left;
  line-height: 60px;
}
.t1{color: #409EFF}
.t2{color: #67C23A}
.t3{color: #E6A23C}
</style>