<template >
  <div class="navi">
  <RouterLink to="/homepage" class="link-style ">主页</RouterLink>&nbsp;
  <RouterLink to="/question_bank" class="link-style">题库</RouterLink>&nbsp;
  <RouterLink to="/announcement" class="link-style">POLYGON</RouterLink>&nbsp;
  <RouterLink to="/character" class="link-style">个人中心</RouterLink>
  </div>
</template>


<style scoped>
div{
  text-align: left;
}
.navi{
  height: 60px;
  border: 2px solid gray;
  border-radius: 5px; 
  line-height: 60px;
  margin-bottom: 20px;
  font-size: 20px;
}

a{
  display: inline-block;
  text-align: left;
  margin-left: 20px;
}

</style>