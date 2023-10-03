<template>
  <div class="pic">
    <img src="../assets/fishead.png" alt="fishead" width="100" height="60"/>
  </div>
  <div v-if=flag class="top">
    <select id="languageSelect" v-model="selectedMode" class="select">
      <option value="quit" @click="quit">退出登录</option>
      <option value="change" @click="change">修改信息</option>
      <option value="main" hidden>{{ userName }}</option>
    </select>
  </div>
  <div v-else class="top">
    <RouterLink to="/login" class="link-style">
      <a> 登录 </a>
    </RouterLink>&nbsp;
    <RouterLink to="/register" class="link-style">
      <a> 注册 </a>
    </RouterLink>

  </div>
</template>

<script>
export default {
  data() {
    return {
      selectedMode:'main',
      userName: '',
      token: '',
      flag: 0,
    };
  },
  methods: {

  },
  watch: {
    selectedMode: function (val) {
      if (val === 'quit') {
        this.flag = 0;
        localStorage.removeItem('token');
        this.$router.push('/');
      }
      if(val === 'change') {
        this.flag = 1;
        // localStorage.removeItem('token');
        this.$router.push('/change');
      }
    },
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
        })
        .catch(error => {
          console.error(error);
        });
    }
  }
};
</script>

<style scoped>
.pic {
  float:left;
}
.top {
  text-align: right;
}

a {
  text-align: center;
  width: 80px;
  height: 80px;
  line-height: 50px;
  font-size: 20px;
  background-color: #d3eefc; 
  color: #000000;
  padding: 5px 20px; 
  border-radius: 5px;
}

a:hover {
  background: #bae2f7;
}

.select{
  text-align: center;
  width: 200px;
  height: 50px;
  line-height: 50px;
  font-size: 20px;
  appearance: none;
  -moz-appearance: none;
  -webkit-appearance: none; 
  background-color: #d3eefc; 
  color: #000000;
  border-radius: 5px;
}
.select::-ms-expand{
  display: none;
}
</style>
  