<template>
<div>
  <el-card class="box-card">
    <div slot="header" class="clearfix">
      <!--   标题     -->
      <span class="card-title">题库</span>
      <!--    重置按钮    -->
      <el-button size="small" style="float:right" icon="el-icon-refresh-left"
                 @click="reset()">重置</el-button>
    </div>
    <div class="text item">
      <!--    题目栏    -->
        <el-table
            style="width: 100%"
            :data="problems"
            :header-cell-style="{background:'#f8f8f9',color:'#555'}"
        >
          <el-table-column
            prop="index"
            label="#"
            width="50"
          >
          </el-table-column>
          <el-table-column
              prop="title"
              label="Title"
              width="800"
              show-overflow-tooltip
          >
            <template v-slot="scope">
              <el-button class="entry" @click="goProblem(scope.row.id)" type="text" >
                {{scope.row.title}}
              </el-button>
            </template>
          </el-table-column>
          <el-table-column
              prop="level"
              label="Level"
              width="250"
              align="center"
          >
            <template v-slot="scope">
              <el-tag
              :type="(scope.row.level==='Easy'?'':(scope.row.level==='Middle'?'success':'warning'))"
              effect="dark">
              {{ scope.row.level }}
              </el-tag>
            </template>
          </el-table-column>
          <el-table-column
              prop="ac,total"
              label="AC/Total"
              align="center"
          >
            <template v-slot="scope">
              {{scope.row.ac}}/{{scope.row.total}}
            </template>
          </el-table-column>
        </el-table>
    </div>
  </el-card>
  <el-pagination
      class="pagination"
      background
      style="float:right"
      :pager-count="5"
      @size-change="handleSizeChange"
      @current-change="handleCurrentChange"
      :current-page.sync=queryInfo.pageNum
      :page-size=queryInfo.pageSize
      layout="total, prev, pager, next, jumper"
      :total=queryInfo.totalSize
  ></el-pagination>
</div>
</template>

<script>
import Pagination from "@/components/Pagination";
export default {
  components: {
    Pagination
  },
  name: "Problems",
  data() {
    return {
      listVisible: true,
      problems: [],
      queryInfo:{
        pageNum:1,
        pageSize:10,
        totalSize:0,
      },
    }
  },
  created() {
    this.getProblemList()
  },
  methods: {
    //获取问题列表
    async getProblemList() {
      const {data:res} = await this.$http.get('api/problem/page',{
        params:{
          'pageNum': this.queryInfo.pageNum,
          'pageSize': this.queryInfo.pageSize
        }
      })
      console.log(res)
      if(!res) return this.$message.error('获取列表失败！')
      this.problems = res.records
      console.log(this.problems)
      this.queryInfo.totalSize = res.total
    },
    //查看题目内容
    goProblem(id) {
      console.log(id)
      this.$router.push('/problems/' + id)
    },
    //重置
    reset() {

    },
    //pagination函数
    handleSizeChange(val) {
      console.log(`每页 ${val} 条`)
    },
    handleCurrentChange(val) {
      console.log(`当前页: ${val}`)
      this.queryInfo.pageNum = val
      this.getProblemList()
    }
  },
}
</script>

<style scoped>
.box-card{
  margin-left: 5%;
  margin-right: 5%;
  margin-top: 1.5%;
}
.card-title{
  font-size: 20px;
}
.pagination{
  margin-left: 5%;
  margin-right: 5%;
  margin-top: 1.5%;
}
</style>