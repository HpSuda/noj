<template>
  <div>
      <table>
          <thead>
              <tr style="background-color: #ffffff;">
                <th style="width: 10%; color: rgb(0, 0, 0);text-align: center;">用户</th>
                <th style="width: auto; color: rgb(0, 0, 0);">题目</th>
                <th style="width: 25%;color: rgb(0, 0, 0);text-align: center;">时间</th>
                <th style="width: 25%;color: rgb(0, 0, 0);text-align: center;">结果</th>
              </tr>
          </thead>
          <tbody >
              <!-- 使用 v-for 渲染数据 -->
              <tr  v-for="msg in tableData" :key="msg.id" >
                  <td style="padding: 8px;">
                    {{ msg.author }}
                  </td>
                  <td>
                    {{ msg.problem }}
                  </td>
                  <td>
                    {{ msg.date }}
                  </td>
                  <td>
                    {{ msg.status }}
                  </td>
              </tr>
          </tbody>
      </table>
  </div>
</template>




<script>
export default {
  data() {
    return {
      tableData: [ ],
    };
  },
  created: function () {
    this.$api.get('/api/status')
      .then(response => {
        this.tableData = response.data;
        console.log(response.data);
      })
      .catch(error => {
        console.error(error);
      });
  },
  methods: {

  },
};

</script>
<style scoped>
table {
  border-collapse: collapse; /* 合并边框，使边框更紧凑 */
  width: 100%; /* 表格宽度100% */
  border: 3px solid gray; /* 表格的外边框样式 */
  border-radius: 50px;
}

th, td {
  border: 3px solid gray; /* 单元格的边框样式 */
  padding: 8px; /* 单元格内边距，可根据需要调整 */
  text-align: left; /* 文本左对齐，可根据需要调整 */
}
</style>
<!-- 
后端响应示例
{
    "status_code": "Success",
    "status_msg": "",
    "submissionInfos": [
        {
            "username": "Lird2002",
            "problemName": "A+B 2",
            "testcaseCount": 1,
            "passCount": 1,
            "id": 1706630651007058000
        },
        {
            "username": "Lird2002",
            "problemName": "A+B 2",
            "testcaseCount": 2,
            "passCount": 2,
            "id": 1706660088029184000
        }
    ]
}
-->