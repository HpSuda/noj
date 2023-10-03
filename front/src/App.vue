<script setup>
import Top from './components/Top.vue'
import Navigation from './components/Navigation.vue'
// import Footer from './components/Footer.vue'




</script>

<template>
  <div class="gray-background"></div>
  <div style="width: 80%; text-align: center; margin: auto;margin-top: 50px;">
    <Top/><br/>
    <Navigation />
    <RouterView></RouterView>
  </div>
</template>

<style scoped>
*{
  margin: 0;
  padding: 0;
}

.gray-background {
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background-color: rgba(255, 255, 255, 0.826); /* 半透明的灰色背景 */
  z-index: -1; /* 确保位于内容之后 */
}
</style>
