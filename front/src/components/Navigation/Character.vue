<template>
    <div v-if=flag>
        <div shadow-box rounded style="box-shadow: 0 2px 4px rgba(0, 0, 0, 0.4);">
          <br>
          <img src="../../assets/man.png" alt="man" width="100" height="100"/>
          <div class="user_name">
              <h1>{{this.userName}}</h1>
          </div>
          <hr id="split"/>
          <!--    解决——提交    -->
          <el-row :gutter="20" class="user_info">
              <el-col :span="11" class="title_small">
                  <p>Solved</p>
                  <p class="emphasis">
                      <h2>{{this.ac}}</h2>
                  </p>
              </el-col>
              <el-col :span="2" class="split-box">
                  <p class="split_vertical"></p>
              </el-col>
              <el-col :span="11" class="title_small">
                  <p>Submissions</p>
                  <p class="emphasis">
                      <h2>{{this.total}}</h2>
                  </p>
              </el-col>
          </el-row>
          <div class="intro">
                <h2>{{ this.email }}</h2>
          </div>
          <br>
      </div>
    </div>
    <div v-else>
      <h1>
          您当前未登录！
      </h1>
    </div>
</template>

<script>
export default {
  data() {
    return {
      email:'',
      userName: '',
      ac: 0,
      total: 0,
      flag:0,
    };
  },
  methods: {

  },
  mounted: function () {
    this.token = localStorage.getItem('token');
    console.log(this.token);
    if (this.token) {
      this.flag = 1;
      this.$api.get('/api/user/curUser', {
      params:{
        token: this.token,
      }})
        .then(response => {
          this.userName = response.data.data.username;
          this.ac = response.data.data.ac;
          this.total = response.data.data.tot;
          this.email = response.data.data.email;
        })
        .catch(error => {
          console.error(error);
        });
    }
  }
};
</script>

<style>
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
  font-weight: 500;
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

</style>