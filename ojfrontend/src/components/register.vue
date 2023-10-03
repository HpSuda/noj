<template>
  <div class="register_container">
    <el-button round @click="dialogFormVisible = true">注册</el-button>
    <!--   注册对话框    -->
    <el-dialog width="30%" center :modal="true"
               :visible.sync="dialogFormVisible" @close="resetForm('form')">
      <!--    dialogTitle区    -->
      <span slot="title">
        <div class="dialog-title">注册</div>
        <el-divider></el-divider></span>
      <!--   注册表单区域   -->
      <el-form  ref="registerForm" :rules="registerFormRules" :model="registerForm" label-width="0px" class="register_form">
        <!--     用户名     -->
        <el-form-item prop="username">
          <el-input v-model="registerForm.username" prefix-icon="el-icon-user" placeholder="Username"></el-input>
        </el-form-item>
        <!--     邮箱     -->
        <el-form-item prop="email">
          <el-input v-model="registerForm.email"
                    prefix-icon="el-icon-message" placeholder="Email Address"></el-input>
        </el-form-item>
        <el-form-item prop="password">
          <el-input v-model="registerForm.password" type="password"
                    prefix-icon="el-icon-lock" placeholder="Password"></el-input>
        </el-form-item>
        <el-form-item prop="passwordAgain">
          <el-input v-model="passwordAgain" type="password"
                    prefix-icon="el-icon-lock" placeholder="Password Again"></el-input>
        </el-form-item>
        <el-form-item prop="captcha">
          <el-row :gutter="20">
            <el-col :span="16">
              <el-input v-model="captcha"
                    prefix-icon="el-icon-key" placeholder="Captcha"></el-input>
            </el-col>
            <el-col :span="8">
              <span @click="randyzm()"
                    class="code"
              >{{code}}</span>
            </el-col>
          </el-row>
        </el-form-item>
      </el-form>
      <!--    按钮区域    -->
      <div slot="footer" class="dialog-footer">
        <el-button-group>
          <el-button class="primary-btn" size="medium" type="primary" @click="register">注册</el-button>
          <el-button size="medium" type="info" @click="test">测试</el-button>
        </el-button-group>
      </div>
    </el-dialog>
  </div>
</template>

<script>
export default {
  name: "register",
  data(){
    let validatePass2 = (rule, value, callback) => {
      if (this.passwordAgain === '') {
        callback(new Error('请再次输入密码'));
      } else if (this.passwordAgain !== this.registerForm.password) {
        callback(new Error('两次输入密码不一致!'));
      } else {
        callback();
      }
    };
    return{
      //验证数据
      code:'',
      passwordAgain:'',
      captcha:'',
      //对话框显示
      dialogFormVisible: false,
      //传送表单数据绑定对象
      registerForm:{
          username:'',
          password:'',
          email:'',
      },
      //表单的验证规则对象
      registerFormRules:{
        //验证用户名是否合法
        username:[
          { required: true, message: '请输入用户名', trigger: 'blur' },
        ],
        //验证密码是否合法
        password:[
          { required: true, message: '请输入密码', trigger: 'blur' },
          { min: 6, max: 20, message: '密码长度在 6 到 20 个字符', trigger: 'change' }
        ],
        passwordAgain:[
          { validator:validatePass2, trigger: 'change' }
        ],
      },
    }
  },
  methods:{
    //关闭对话框时清空数据：el自带resetFields函数
    resetForm (form) {
      this.code = ''
      this.passwordAgain = ''
      this.captcha = ''
      this.$refs.registerForm.resetFields();
    },
    //登陆函数
    register(){
      //验证码检验
      if(this.code === ''||this.captcha === ''){
        this.randyzm()
        this.$message.info('请输入验证码')
      }else if(this.code !== this.captcha){
        this.captcha = ''
        this.randyzm()
        this.$message.error('验证码错误')
        return
      }else this.$refs.registerForm.validate(async valid => {
        //预验证
        if(!valid) return;
        //向后台验证和请求数据
        const {data:result} = await this.$http.post('api/user/register',this.registerForm);
        console.log(result);
        //弹窗
        if(result.code !== '200') {
          return this.$message.error(result.code + '：' + result.msg);
        } else {
          this.$message.success('注册成功');
        }
        this.dialogFormVisible = false;
      });
    },
    test(){
      console.log(this)
      console.log(this.passwordAgain)
      console.log(this.captcha)
    },
    randyzm() {
      //生成随机验证码
      let code = ''
      let coden = ''
      var codeLength = 4 //验证码的长度
      var random = new Array(
          0,
          1,
          2,
          3,
          4,
          5,
          6,
          7,
          8,
          9,
          'A',
          'B',
          'C',
          'D',
          'E',
          'F',
          'G',
          'H',
          'I',
          'J',
          'K',
          'L',
          'M',
          'N',
          'O',
          'P',
          'Q',
          'R',
          'S',
          'T',
          'U',
          'V',
          'W',
          'X',
          'Y',
          'Z',
          'a',
          'b',
          'c',
          'd',
          'e',
          'f',
          'g',
          'h',
          'i',
          'j',
          'k',
          'l',
          'm',
          'n',
          'o',
          'p',
          'q',
          'r',
          's',
          't',
          'u',
          'v',
          'w',
          'x',
          'y',
          'z'
      ) //随机数
      for (var i = 0; i < codeLength; i++) {
        //循环操作
        var index = Math.floor(Math.random() * 62) //取得随机数的索引（0~61）
        code += random[index] //根据索引取得随机数加到code上
        coden += random[index] //根据索引取得随机数加到code上
      }
      this.code = coden //把code值赋给验证码
      console.log(this.code)
    },
  }
}
</script>

<style scoped>
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
.code{
  display: inline-block;
  text-align: center;
  width: 90px;
  font-size: 20px;
  height: 32px;
  line-height:32px;
  color: #2b3b4b;
  background-color: #3f78a1;
}
</style>