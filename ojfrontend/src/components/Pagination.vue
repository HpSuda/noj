<template>
<div class="pagination">
  <el-pagination
      background
      style="float:right"
      :pager-count="5"
      @size-change="handleSizeChange"
      @current-change="handleCurrentChange"
      :current-page.sync=currentPage
      :page-size=pageSize
      layout="total, prev, pager, next, jumper"
      :total=totalSize>
  </el-pagination>
</div>
</template>

<script>
export default {
  props: {
    currentPage:'',
    pageSize:'',
    totalSize:'',
  },
  name: "Pagination",
  data(){
    return{
    }
  },
  methods: {
    handleSizeChange(val) {
      console.log(`每页 ${val} 条`);
    },
    handleCurrentChange(val) {
      console.log(`当前页: ${val}`);
    }
  },
}
</script>

<style scoped>
.pagination{
  margin-left: 5%;
  margin-right: 5%;
  margin-top: 1.5%;
}
</style>