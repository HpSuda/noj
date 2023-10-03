<template>
  <el-card>
    <p class="title">代码框</p>
    <div class="container">
      <editor
          ref="aceEditor"
          v-model="content"
          @init="editorInit"
          :lang="lang"
          width="100%"
          height="300"
          :theme="theme"
          :options="{
    　　 enableBasicAutocompletion: true,
    　　 enableSnippets: true,
    　　 enableLiveAutocompletion: true,
  　　   tabSize:6,
    　　 fontSize:14,
    　　 showPrintMargin:false,   //去除编辑器里的竖线
      }"
      ></editor>
      <el-select v-model="theme" filterable placeholder="请选择">
        <el-option
            v-for="style in arr"
            :key="style"
            :label="style"
            :value="style"
        >
        </el-option>
      </el-select>
      <el-select v-model="lang" filterable placeholder="请选择">
        <el-option
            v-for="lang in langArr"
            :key="lang"
            :label="lang"
            :value="lang"
        >
        </el-option>
      </el-select>
      <el-button type="primary" style="float: right" @click="submitValue">提交</el-button>
      <!--    <el-button type="primary" size="small" @click="pre">上一个主题</el-button>-->
      <!--    <el-button type="primary" size="small" @click="next">下一个主题</el-button>-->
    </div>
  </el-card>
</template>

<script>
export default {
  name:'solveBox',
  props:{
    id:'',
  },
  data() {
    return {
      submitInfo:{
        code:'',
        token:'',
        language:'',
        problemId:''
      },
      content: "",
      theme:'',
      lang:'',
      num:0,
      langArr:[
        'c_cpp',
        'java',
        'python'
      ],
      arr:[ //将brace/theme文件夹下的所有主题名字拷贝出来
        'clouds',
        'ambiance',
        'chaos',
        'chrome',
        'clouds_midnight',
        'cobalt',
        'crimson_editor',
        'dawn',
        'dracula',
        'dreamweaver',
        'eclipse',
        'github',
        'gob',
        'gruvbox',
        'idle_fingers',
        'iplastic',
        'katzenmilch',
        'kr_theme',
        'kuroir',
        'merbivore',
        'merbivore_soft',
        'monokai',
        'mono_industrial',
        'pastel_on_dark',
        'solarized_dark',
        'solarized_light',
        'sqlserver',
        'terminal',
        'textmate',
        'tomorrow',
        'tomorrow_night',
        'tomorrow_night_blue',
        'tomorrow_night_bright',
        'tomorrow_night_eighties',
        'twilight',
        'vibrant_ink',
        'xcode',
      ]
    };
  },
  components: {
    editor: require("vue2-ace-editor")
  },
  methods: {
    editorInit() {//初始化
      require("brace/ext/language_tools") //language extension prerequsite...
      // require("brace/mode/java"); //language
      // require("brace/mode/python"); //language
      // require("brace/mode/javascript"); //language
      //require("brace/mode/java/c_cpp"); //language
      // require("brace/snippets/java"); //snippet
      // require("brace/snippets/python"); //snippet
      // require("brace/snippets/javascript"); //snippet
      // require("brace/snippets/c_cpp"); //snippet
      // require("brace/theme/tomorrow_night");
      for (let i = 0; i < this.arr.length; i++) {//方便看哪个主题好看，循坏加载了所有主题，通过点击按钮切换
        require("brace/theme/"+this.arr[i])
      }
      for (let j = 0; j < this.langArr.length; j++) {//点击按钮切换语言
        require("brace/mode/"+this.langArr[j]) //language
        require("brace/snippets/"+this.langArr[j]) //snippet
      }
    },
    getValue(){//获取编辑器中的值
      console.log('编辑器中的值：'+this.$refs.aceEditor.editor.getValue())
      console.log('编辑器中第一个换行符的位置：'+this.$refs.aceEditor.editor.getValue().indexOf('\n'))
    },
    async submitValue(){
      //收集提交信息
      this.submitInfo.problemId = this.$props.id
      this.submitInfo.code = this.$refs.aceEditor.editor.getValue()
      this.submitInfo.token = JSON.parse(window.sessionStorage.getItem('token'))
      this.submitInfo.language = this.lang
      console.log(this.submitInfo.token)

      const {data:result} = await this.$http({
        url:'api/status/submit',
        method:'post',
        headers:{
          token:this.submitInfo.token
        },
        data:{
          'code':this.submitInfo.code,
          'language':this.submitInfo.language,
          'problemId':this.submitInfo.problemId,
        }
      })
      console.log(result)
      //弹窗
      if(result.code === '401') {
        this.$store.state.loginDialogVisible = true
        return this.$message.error('请登陆后提交代码')
      } else if(result.code !== '200'){
        return this.$message.error('提交失败'+'：'+result.msg)
      }else{
        this.$message.success('提交成功')
      }
      //跳转status页面
      this.$router.push('/status')
    }
  },
  mounted() {
    this.editorInit();
    this.theme = this.arr[0]
    this.lang = this.langArr[0]
    console.log(this.$refs.aceEditor.editor.setValue(''))
  }
}
</script>

<style lang="less" scoped>
.title {
  font-size: 20px;
  font-weight: 400;
  margin: 25px 0 8px 0;
  color: #3091f2;
}
</style>