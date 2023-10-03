<template>
  <div style="text-align: left;">
    <RouterLink to="/Announcement/submit_topic" class="link-style">
      <a style="width: 100px;">创建题目</a></RouterLink>&nbsp;

    <div style="padding-top: 20px;">
      
      <RouterView></RouterView>
    </div>
  </div>
</template>
  
<style scoped>
a{
  display: inline-block;
  white-space: nowrap;
  text-align: center;
  height: 50px;
  line-height: 50px;
  background-color: #d3eefc;
  border-radius: 10px;
  transition: background-color 0.3s ease;
}

a:hover{
  background-color: #bae2f7;
}
</style>