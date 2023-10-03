<template>
    <div v-if=flag>
        <div shadow-box rounded style="box-shadow: 0 2px 4px rgba(0, 0, 0, 0.4);">
          <br>
          <form @submit.prevent="update">
          <img src="../../assets/man.png" alt="man" width="100" height="100"/>
          <div class="form-group">
            <label for="username">用户名:</label>&nbsp;
            <input type="text" id = "username" v-model="userName" />
            </div>
            <div class="form-group">
                <label for="oldPassword">旧密码:</label>&nbsp;&nbsp;
                <input type="password" id = "oldPassword" v-model="oldPassword" />
            </div>
            <div class="form-group">
                <label for="newPassword">新密码:</label>&nbsp;&nbsp;
                <input type="password" id = "newPassword" v-model="newPassword"/>
            </div>
            <div class="form-group">
                <label>新邮箱:</label>&nbsp;&nbsp;
                <input type="text" id = "email" v-model="email" />
            </div>
            <div v-if="errorMessage" class="error-message">{{ errorMessage }}</div>
            <button type="submit" style="background-color:#d3eefc; color: #000000; padding: 10px 20px; border-radius: 5px;">更新</button>
          </form>
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
      oldPassword:'',
      newPassword:'',
      errorMessage: '',
    };
  },
  methods: {
    update() {
        // if (this.username.trim() === '') this.errorMessage = '用户名不能为空';
        // else if (this.oldPassword.trim() === '') this.errorMessage = '旧密码不能为空';
        // else if (this.newPassword.trim() === '') this.errorMessage = '新密码不能为空';
        // else if (this.email.trim() === '') this.errorMessage = '新密码不能为空';
        {
            this.errorMessage = '';
            this.token = localStorage.getItem('token');
            console.log(this.token);
            if (this.token) {
            this.$api.get('/api/user/update', {
          params: {
            token: this.token,
            Username: this.userName,
            oldPassword: this.oldPassword,
            newPassword: this.newPassword,
            email: this.email,
          }
        })
          .then(response => {
            // localStorage.removeItem('token');
            // localStorage.setItem('token', response.data.data.token);
            console.log(response.data);
            location.reload();
            location.href = '/homepage';
          })
          .catch(error => {
            console.error(error);
          });
        }
        }
    }
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