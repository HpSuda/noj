<template>
  <div>
  <el-card class="box-card">
    <div slot="header" class="clearfix">
      <!--   标题     -->
      <span class="card-title">排行榜</span>
    </div>
    <bar-chart v-if="echartsView" :topList="rankTop"></bar-chart>
    <div class="text item">
      <!--    题目栏    -->
      <el-table
          style="width: 100%"
          :data="rank"
          :header-cell-style="{background:'#f8f8f9',color:'#555'}"
      >
        <el-table-column
            type="index"
            label="#"
            width="80"
        >
        </el-table-column>
        <el-table-column
            prop="username"
            label="User"
            width="200"
            show-overflow-tooltip
        >
          <template v-slot="scope">
            <el-button class="entry" @click="goUser(scope.row.id)" type="text" >
              {{scope.row.username}}
            </el-button>
          </template>
        </el-table-column>
        <el-table-column
            prop="signature"
            label="Signature"
            width="400"
            show-overflow-tooltip
        >
        </el-table-column>
        <el-table-column
            prop="ac"
            label="AC"
            width="200"
            align="center"
        >
        </el-table-column>
        <el-table-column
            prop="tot"
            label="Total"
            width="200"
            align="center"
        >
        </el-table-column>
        <el-table-column
            prop="ac,tot"
            label="Rate"
            align="center"
        >
          <template v-slot="scope">
            {{scope.row.ac/scope.row.tot | rounding}}
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

import BarChart from "@/components/ECharts/BarChart";
export default {
  name: "Rank",
  components: {BarChart},
  data() {
    return {
      echartsView:false,
      rank: [],
      rankTop: [],
      queryInfo:{
        pageNum:1,
        pageSize:10,
        totalSize:0,
      },
    }
  },
  //取小数点后两位
  filters: {
    rounding(value) {
      return value.toFixed(2)
    }
  },
  mounted() {
    this.getRankList()
  },
  methods: {
    //获取问题列表
    async getRankList() {
      const {data:res} = await this.$http.get('api/user/rank',{
        params:{
          'pageNum': this.queryInfo.pageNum,
          'pageSize': this.queryInfo.pageSize
        }
      })
      console.log(res)
      if(!res) return this.$message.error('获取列表失败！')
      this.rank = res.data.records
      this.queryInfo.totalSize = res.total
      console.log(this.rank)
      if(this.queryInfo.pageNum === 1) this.initBarChart()
    },
    //初始化柱状图
    initBarChart(){
      this.rankTop = this.rank.slice(0,8)
      this.echartsView = true
    },
    //查看用户界面
    goUser(id){

    },
    //pagination函数
    handleSizeChange(val) {
      console.log(`每页 ${val} 条`)
    },
    handleCurrentChange(val) {
      console.log(`当前页: ${val}`)
      this.queryInfo.pageNum = val
      this.getRankList()
    }
  }
}
</script>

<style lang="less" scoped>
.box-card{
  margin-top: 1.5%;
  margin-left: 3%;
  margin-right: 3%;
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