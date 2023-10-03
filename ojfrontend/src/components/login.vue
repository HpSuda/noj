<template>
  <div class="login_container">
      <el-button round @click="dialogReveal">登录</el-button>
      <!--   登录对话框    -->
      <el-dialog width="30%" center
                 class="login-dialog"
                 :modal="true"
                 :visible.sync="dialogVisible"
                 @close="resetForm('form')">
      <!--    dialogTitle区    -->
      <span slot="title">
        <div class="dialog-title">登录</div>
        <el-divider></el-divider>
      </span>
      <!--   登陆表单区域   -->
      <el-form
          ref="loginForm"
          :rules="loginFormRules"
          :model="loginForm"
          label-width="0px"
          class="login_form">
        <!--     用户名     -->
        <el-form-item prop="username">
          <el-input v-model="loginForm.username"
                    prefix-icon="el-icon-user"
                    placeholder="Username">
          </el-input>
        </el-form-item>
         <!--     密码     -->
        <el-form-item prop="password">
          <el-input v-model="loginForm.password"
                    type="password"
                    prefix-icon="el-icon-lock"
                    placeholder="Password">
          </el-input>
        </el-form-item>
      </el-form>
        <!--    按钮区域    -->
        <div slot="footer" class="dialog-footer">
          <el-button-group>
            <el-button class="primary-btn" size="medium" type="primary" @click="login">登录</el-button>
            <el-button class="primary-btn" size="medium" type="primary" >注册</el-button>
            <el-button class="primary-btn" size="medium" type="primary" >找回密码</el-button>
<!--            <el-button size="medium" type="info" @click="test">测试</el-button>-->
          </el-button-group>
        </div>
      </el-dialog>
  </div>
</template>

<script>
export default {
  name: "login",
  data(){
    return{
      //登录对话框显示
      //loginDialogVisible: false,
      //登陆表单数据绑定对象
      loginForm:{
        username:'',
        password:''
      },
      //表单的验证规则对象
      loginFormRules:{
        //验证用户名是否合法
        username:[
          { required: true, message: '请输入用户名', trigger: 'blur' },
        ],
        //验证密码是否合法
        password:[
          { required: true, message: '请输入密码', trigger: 'blur' },
          { min: 6, max: 20, message: '密码长度在 6 到 20 个字符', trigger: 'change' }
        ],
      }
    }
  },
  computed:{
    dialogVisible:{
      set(val){},
      get(){
        return this.$store.state.loginDialogVisible
      },
    }
  },
  methods:{
     //关闭对话框时清空数据：el自带resetFields函数
    resetForm (form) {
      this.$store.state.loginDialogVisible = false
      this.$refs.loginForm.resetFields()
    },
    //登陆函数
    login(){
      this.$refs.loginForm.validate(async valid => {
        //预验证
        if(!valid) return;
        //向后台验证和请求数据
        const {data:result} = await this.$http.post('api/user/login',this.loginForm)
        console.log(result)
        //弹窗
        if(result.code !== '200') {
          return this.$message.error(result.code + '：' + result.msg)
        } else {
          this.$message.success('登陆成功')
        }
        //将token保存入sessionStorage
        window.sessionStorage.setItem('token',JSON.stringify(result.data.token))
        //修改导航栏按钮部分
        this.$store.state.loginStatus = true
        this.$store.state.loginDialogVisible = false
        //导航跳转主页
        // this.$router.push('/main');
      });
    },
    //前端测试跳转，删除
    // test(){
    //   this.$message.success('Welcome back,dude!')
    //   this.$store.state.loginStatus = true;
    //   this.$store.state.loginDialogVisible = false
    // },
    dialogReveal(){
      this.$store.state.loginDialogVisible = true
    }
  }
}
</script>

<style lang="less" scoped>
.primary-btn{
  background-color: #2d8cf0;
}
.dialog-title{
  font-size: 20px;
  padding: 20px;
}
.dialog-footer {
  display:flex;
  justify-content:flex-end;
}
</style>