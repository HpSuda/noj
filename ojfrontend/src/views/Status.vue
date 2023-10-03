<template>
  <div>
    <el-card class="box-card">
      <div slot="header" class="clearfix">
        <!--   标题     -->
        <span class="card-title">提交记录</span>
        <!--    重置按钮    -->
        <el-button size="small" style="float:right" icon="el-icon-refresh-left"
                   @click="reset()">重置</el-button>
      </div>
      <div class="text item">
        <!--    提交记录栏    -->
        <el-table
            style="width: 100%"
            :data="status"
            :row-class-name="tableRowClassName"
            :header-cell-style="{background:'#f8f8f9',color:'#555'}"
        >
          <el-table-column
              prop="status"
              label="Status"
              width="180"
          ><template v-slot="scope">
              <el-tag
                  :type="(scope.row.status==='Runtime Error'?'info':
                  (scope.row.status==='Compiler Error'?'':
                  (scope.row.status==='Accepted'?'success':
                  (scope.row.status==='Wrong Answer'?'danger':'warning'))))"
                  effect="dark">
                {{ scope.row.status }}
              </el-tag>
            </template>
          </el-table-column>

          <el-table-column
              label="Problem"
              width="400"
              show-overflow-tooltip
          >
            <template v-slot="scope">
              <el-button class="entry" @click="goProblem(scope.row.problemid)" type="text" >
                {{scope.row.problem}}
              </el-button>
            </template>
          </el-table-column>

          <el-table-column
              prop="author"
              label="Author"
              width="150"
              align="center"
          >
          </el-table-column>

          <el-table-column
              prop="time"
              label="Time"
              width="150"
          >
          </el-table-column>
          <el-table-column
              prop="memory"
              label="Memory"
              width="150"
          >
          </el-table-column>
          <el-table-column
              prop="language"
              label="Language"
              width="150"
          >
          </el-table-column>
          <el-table-column
              prop="date"
              label="Date"
          >
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
  name: "Status",
  components: {Pagination},
  data() {
    return {
      queryInfo:{
        pageNum:1,
        pageSize:10,
        totalSize:0,
      },
      status: [{
        date: '2016-05-02',
        status:'Accepted',
        author: '王小虎',
        problem: '上海市普陀区金沙江路 1518 弄',
      }, {
        date: '2016-05-04',
        status:'Runtime Error',
        author: '王小虎',
        problem: '上海市普陀区金沙江路 1518 弄'
      }, {
        date: '2016-05-01',
        status:'Wrong Answer',
        author: '王小虎',
        problem: '上海市普陀区金沙江路 1518 弄',
      }, {
        date: '2016-05-03',
        status: 'Time Limit Exceed',
        author: '王小虎',
        problem: '上海市普陀区金沙江路 1518 弄'
      },{
        date: '2016-05-03',
        status: 'Compiler Error',
        author: '王小虎',
        problem: '上海市普陀区金沙江路 1518 弄'
      },
      ]
    }
  },
  created() {
    this.getStatusList()
  },
  methods: {
    //获取问题列表
    async getStatusList() {
      const {data:res} = await this.$http.get('api/status/page',{
        params:{
          'pageNum': this.queryInfo.pageNum,
          'pageSize': this.queryInfo.pageSize
        }
      })
      console.log(res)
      if(!res) return this.$message.error('获取列表失败！')
      this.status = res.records
      this.queryInfo.totalSize = res.total
    },
    //查看题目内容
    goProblem(id) {
      console.log(id)
      this.$router.push('/problems/' + id)
    },
    //渲染每一行颜色
    tableRowClassName({row, rowIndex}) {
      if (row.status === 'Accepted') {
        return 'success-row';
      } else if (row.status === 'Wrong Answer') {
        return 'danger-row';
      } else if (row.status === 'Time Limit Exceeded') {
        return 'warning-row';
      }else if (row.status === 'Runtime Error') {
        return 'strange-row';
      }else if (row.status === 'Compiler Error') {
        return 'wrong-row';
      }
      return '';
    },
    //pagination函数
    handleSizeChange(val) {
      console.log(`每页 ${val} 条`)
    },
    handleCurrentChange(val) {
      console.log(`当前页: ${val}`)
      this.queryInfo.pageNum = val
      this.getStatusList()
    },
  }
}
</script>

<style scoped>
.box-card{
  margin-top: 1.5%;
  margin-left: 3%;
  margin-right: 3%;
}
.card-title{
  font-size: 20px;
}
.el-table :deep(.warning-row) {
  background: oldlace;
}.el-table :deep(.success-row) {
  background: #f0f9eb;
}.el-table :deep(.danger-row) {
   background: #faeaea;
}.el-table :deep(.strange-row) {
   background: #e9e5f8;
}.el-table :deep(.wrong-row) {
   background: #eaf4fa;
 }
.pagination{
  margin-left: 5%;
  margin-right: 5%;
  margin-top: 1.5%;
}
</style>