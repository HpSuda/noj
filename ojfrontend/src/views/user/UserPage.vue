<template>
  <div class="user_page">
      <!--  头像  -->
      <div class="avatar-container">
        <img class="avatar" :src="user.ava"/>
      </div>
      <el-card class="box-card" shadow="hover">
        <div class="text item">
          <!--    名字      -->
          <div class="user_name">
            {{user.username}}
          </div>
          <hr id="split"/>
          <!--    解决——提交    -->
          <el-row :gutter="20" class="user_info">
            <el-col :span="11" class="title_small">
              <p>Solved</p>
              <p class="emphasis">{{user.solved}}</p>
            </el-col>
            <el-col :span="2" class="split-box" ><p class="split_vertical"></p></el-col>
            <el-col :span="11" class="title_small">
              <p>Submissions</p>
              <p class="emphasis">{{user.submissions}}</p>
            </el-col>
          </el-row>

          <!--    留言      -->
          <div class="intro">
            <p v-if="user.intro===''">这个人太懒，这里啥也没留下~</p>
            <p v-else>{{ user.intro }}</p>
          </div>
          <!--  图标  -->
          <div class="icons">
            <a :href="user.github" target="_blank">
              <span class="iconfont">&#xe8a1;</span>
            </a>
            <a :href="'mailto:'+ user.email" target="_blank">
              <span class="iconfont">&#xe75c;</span>
            </a>
            <a :href="user.blog" target="_blank">
              <span class="iconfont">&#xe632;</span>
            </a>
          </div>

        </div>
    </el-card>
  </div>
</template>

<script>
export default {
  name: "UserPage",
  data(){
    return{
      user:{
        username:'kevinXXX',
        solved:5,
        submissions:10,
        intro:'',
        github:'https://github.com/younoho',
        email:'',
        blog:'',
        ava:require('@/assets/c1116342d1.png'),//nice
      }
    }
  },
}
</script>

<style scoped>
.box-card{
  padding: 100px 100px 10px 100px;
  margin-left: 18%;
  margin-right: 18%;
  margin-top: 6%;
}
.avatar-container {
  position: absolute;
  left: 50%;
  transform: translate(-50%);
  z-index: 1;
  top: 30px;
}
.avatar {
  width: 140px;
  height: 140px;
  border-radius: 50%;
  box-shadow: 0 1px 1px 0;
}
.user_name{
  text-align: center;
  font-size: 18px;
  font-weight: bold;
  color: #495060;
}
#split {
  margin: 20px auto;
  width: 90%;
}
.split_vertical{
  width: 0.01px;
  border:0.01px solid #b0b0b4;
  align-self: center;
  height:80px;
  text-align:center;
  margin: 0 auto;
  padding-top: 20px;
}
.title_small{
  text-align: center;
}
.emphasis {
  font-size: 20px;
  font-weight: 600;
}
.intro{
  text-align: center;
  margin-top: 40px;
  padding-left: 30px;
  padding-right: 30px;
  font-size: 18px;
}
.icons {
  text-align: center;
  padding-left: 30px;
  padding-right: 30px;
  padding-top: 60px;
  font-size: 30px;
  margin: 0 auto;
}
/*iconfont图库引用*/
@font-face {
  font-family: 'iconfont';
  src: url('../../assets/iconfont/iconfont.ttf?t=1663862653138') format('truetype');
}
.iconfont {
  font-family: "iconfont" !important;
  font-size: 30px;
  font-style: normal;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  padding-right: 1%;
  padding-left: 1%;
  color: #2d8cf0;
}
</style>