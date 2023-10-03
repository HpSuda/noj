<template>
  <div class="problem_box">
    <el-row class="row-col_box" :gutter="20">
      <!--左侧栏-->
      <el-col :span="18">
        <el-card class="problem-box-card">
          <div slot="header" class="clearfix">
            <!--   标题     -->
            <span class="box-card-title">{{ title }}</span>
            <!--    返回按钮    -->
            <el-button size="small" style="float:right" icon="el-icon-back"
                       @click="backPage()">返回</el-button>
          </div>
          <div id="problem-content" class="text item">
            <p class="title">题目描述</p>
            <p class="content" v-html=problem.description></p>

            <p class="title">输入格式</p>
            <p class="content" v-html=problem.inputDescription></p>

            <p class="title">输出格式</p>
            <p class="content" v-html=problem.outputDescription></p>
            <!--      测试数据栏      -->
            <el-row>
              <p class="title">样例</p>
              <el-col :span="12">
                <p class="title_small">
                  输入
                  <el-button size="mini" class="input_button"
                             @click="copyShaneUrl" icon="el-icon-copy-document"></el-button>
                </p>
                <div class="content_input" v-html=problem.inputData></div>
              </el-col>
              <el-col :span="12">
                <p class="title_small">输出</p>
                <div class="content_input" v-html=problem.outputData></div>
              </el-col>
            </el-row>

            <p class="title">数据范围</p>
            <p class="content" v-html=problem.dataDomain></p>

          </div>
        </el-card>
        <solve-box class="solveBox" :id='this.id'></solve-box>
      </el-col>
      <!--   右侧栏   -->
      <el-col :span="6">
        <el-card class="info-card">
          <div slot="header">
            提交数据
          </div>
          <div class="text item">
            <pie-chart v-if="echartsView" :AC="this.problem.ac" :Total="this.problem.total"></pie-chart>
          </div>
        </el-card>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import SolveBox from "@/components/solveBox";
import PieChart from "@/components/ECharts/PieChart";
export default {
  name: "Problem",
  components: {PieChart, SolveBox},
  data(){
    return{
      id:'',
      problem:'',
      echartsView:false,
    }
  },
  mounted () {
    this.id = this.$route.params.id
    this.init()
  },
  methods:{
    //页面初始化
    init(){
      this.getProblem()
    },
    //获取问题
    async getProblem(){
      const {data:res} = await this.$http.get('api/problem/'+this.id)
      if(res.code !== '200') return this.$message.error('获取题目失败')
      this.problem = res.data
      console.log(this.problem)
      console.log(this.problem.ac)
      this.echartsView = true
    },
    //返回函数
    backPage () {
      this.$router.go(-1)
    },
    //新式复制
    copyShaneUrl(shareLink){
      navigator.clipboard.writeText(this.problem.inputData).then(() => {
        this.$message.success('复制成功');
        });
      },
    updateScrollTop(){
      let x=document.getElementsByClassName("problem-box-card")[0]
      x.scrollTop=x.scrollHeight  //将滚轮置底
    },
    },
  computed: {
    title() {
      return this.problem.title
    },
      commendContent(content) {
        //this.commend.content是后端传回来的文本数据，就是要对这个数据进行处理
        let arr = content.split("");
        return arr.map((item) => {
          return item === "\n" ? "<br>" : item
        }).join("")
    },
  }
}

</script>

<style scoped>
.problem_box{
  margin-bottom: 5%;
  margin-right: 3%;
  margin-left: 3%;
}
.row-col_box{
  margin-top: 1.5%;
}
.box-card-title{
  font-size: 20px;
}
.title {
  font-size: 20px;
  font-weight: 400;
  margin: 25px 0 8px 0;
  color: #3091f2;
}
.title_small{
  font-size: 17px;
  font-weight: 400;
  margin: 25px 0 8px 0;
  color: #3091f2;
}
.content {
  margin-left: 25px;
  margin-right: 20px;
  font-size: 17px
}
.content_input{
  width: 90%;
  border-radius: 4px;
  box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);
  padding:2%
}
.input_button{
  float:right;
  margin-right: 6%;
  font-size: 10px;
  box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);
}
.solveBox{
  margin-top: 2%;
}
</style>