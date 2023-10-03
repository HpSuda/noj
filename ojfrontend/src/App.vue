<template>
  <div>
    <NaviBar></NaviBar>
    <div id="app">
      <el-container>
        <el-main>
          <Transition name="fadeInUp" mode="out-in">
          <router-view></router-view>
          </Transition>
        </el-main>
        <el-footer class="app_footer">NJUSTOJ<br/>Powered by my homework</el-footer>
      </el-container>
    </div>
  </div>
</template>

<script>

import NaviBar from "../src/components/NaviBar";
export default {
  name: 'app',
  components: {
    NaviBar
  }
}
</script>

<style lang="less">
#app{
  background-color: rgba(220,220,220,0.5);
  width:100%;
  height:100%;
  position:fixed;
  background-size:100% 100%;
  overflow:auto!important /* 开启滚动显示溢出内容 */
}
.app_footer{
  text-align:center;
  font-weight: lighter;
  font-size: 15px;
}
.fadeInUp-enter-active {
  animation: fadeInUp .8s;
}
</style>
