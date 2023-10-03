<template>
    <div>
        <table>
            <thead>
                <tr style="background-color: #ffffff;">
                    <th style="width: 3%; color: rgb(0, 0, 0);text-align: center;">#</th>
                    <th style="width: auto; color: rgb(0, 0, 0);">题目</th>
                    <th style="width: 10%; color: rgb(0,0,0);">通过/提交</th>
                    <th style="width: 10%; color: rgb(0, 0, 0);">难度</th>
                    <th style="width: 2%;text-align: right;"></th>
                </tr>
            </thead>
            <tbody >
                <!-- 使用 v-for 渲染数据 -->
                <tr  v-for="item in tableData" :key="item.id" >
                    <td style="padding: 8px;">
                        <!-- 每个单元格包含链接 -->
                        <RouterLink to="/question_bank/question_description" class="link-style" 
                            @click="storeItemID(item.id)">{{ item.id }}</RouterLink>
                    </td>
                    <td>
                        <RouterLink to="/question_bank/question_description" class="link-style" 
                            @click="storeItemID(item.id)">
                            <a>{{ item.title }}</a>
                        </RouterLink>
                    </td>
                    <td>
                        <a>{{ item.ac }}/{{ item.total }}</a>
                    </td>
                    <td>
                        <a>{{ item.level }}</a>
                    </td>
                    <td>
                        <RouterLink to="/question_bank/submit" class="link-style submit"
                            @click="storeItemID(item.id)">submit</RouterLink>
                    </td>
                </tr>
            </tbody>
        </table>
    </div>
</template>
  
<script>
import { useCookies } from "vue3-cookies";
const { cookies } = useCookies();


export default {
    data() {
        return {
            tableData: [ ],
        };
    },
    created:function() {
            this.$api.get('/api/problem')
                .then(response => {
                    this.tableData = response.data;
                })
                .catch(error => {
                    console.error(error);
                });
        },
    methods: {
        storeItemID(itemID) {
            this.setCookie('itemID', itemID, 7);
        },
        setCookie(name, value, days) {
            const date = new Date();
            date.setTime(date.getTime() + days * 60 * 1000);
            const expires = 'expires=' + date.toUTCString();
            document.cookie = name + '=' + value + ';' + expires + ';path=/';
        },
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

.submit{
    color:rgb(0, 0, 0);
}
.submit:hover{
    color:#0077b6;
}
</style>