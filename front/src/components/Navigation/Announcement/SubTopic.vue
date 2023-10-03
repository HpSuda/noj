<template>
  <div>
    <form @submit.prevent="submitQuestion">
      <!-- 难度选择-->
      <div>
          <label for="levelSelect" style="color: rgb(0, 0, 0);">题目难度：</label>
          <select id="levelSelect" v-model="level">
          <option value= '0' >easy</option>
          <option value= '1' >medium</option>
          <option value= '2' >hard</option>
        </select>
      </div><br>
      <!-- 题目名字-->
      <div>
        <label for="nameInput" style="color: rgb(0, 0, 0);">题目名字:</label>&nbsp;
        <textarea id="nameInput" v-model="name" rows="1" style="width:10%; "></textarea>
      </div><br>
      <!-- 代码输入框 -->
      <div>
        <label for="questionInput" style="color: rgb(0, 0, 0);">提交题目与样例:</label><br>
        <textarea id="questionInput" v-model="question" rows="5" style="width:60%; height: 200px;"></textarea>
      </div><br>
      <div class="container">
          <div class="half-width-input">
              <label for="Input" style="color: rgb(0, 0, 0);">测试输入:</label><br>
              <textarea id="Input" v-model="input" rows="5" style="width:60%;"></textarea>
          </div>
          <div class="half-width-input">
              <label for="Output" style="color: rgb(0, 0, 0);">测试输出:</label><br>
              <textarea id="Output" v-model="output" rows="5" style="width:60%;"></textarea>
          </div>
      </div><br>
      <!-- 提交按钮 -->
      <button type="submit" class="sub" style="width: 100px">提交</button>
    </form>
  </div>
</template>
  <script>
  import { expandKeys } from 'element-plus/es/components/table-v2/src/common';
import { useCookies } from 'vue3-cookies';
  export default {
    setup() {
      const { cookies } = useCookies();
      return { cookies };
    },
    data() {
      return {
        name:'',
        level: '0',
        question: '', // 代码输入框的默认值
        input: '',
        output: '',
      };
    },
    methods: {
      submitQuestion() {
        const userid = this.cookies.get("userid");
        if (userid === '') {
          window.alert("请登录");
          return;
        }
        if (this.number === '') {
          window.alert('题目不能为空');
          return;
        }
        // const form_data = new FormData();
        // form_data.append("language", this.selectedLanguage);
        // form_data.append("question", this.question);
        // form_data.append("userid", userid);
        this.$api({
          method: "post",
          url: "/api/problem/createProblem",
          data: {
          //   'userId': this.submitInfo.userId,
          // 'code':this.submitInfo.code,
          // 'language':this.submitInfo.language,
          // 'problemId':this.submitInfo.problemId,
            'title': this.name,
            'level': this.level,
            'description': this.question,
            'inputData': this.input,
            'outputData': this.output,
        },
          headers: {"Content-Type": "application/json"},
        })
        .then(response => {
          console.log(response.data);
          if (response.data.status_code === 'Failed') {
            window.alert('提交失败');
          } else {
            window.alert('提交成功');
            this.$router.push('/question_bank/questions');
          }
        }).catch(error => {
          console.error(error);
        });
        this.name = '';
        this.question = '';
      },
      getCookie(name) {
        const cookieValue = document.cookie
          .split('; ')
          .find(row => row.startsWith(name + '='))
          ?.split('=')[1];
        return cookieValue || null;
      },
    },
  };
  </script>
    
  <style>
  .form-group {
    margin-bottom: 20px;
  }
  
  .sub {
    display: inline-block;
    text-align: center;
    white-space: nowrap;
    font-size: medium;
    height: 50px;
    line-height: 50px;
    background-color: #d3eefc;
    border-radius: 10px;
    transition: background-color 0.3s ease;
  }
  
  .sub:hover {
    background-color: #bae2f7;
  }
  </style>
    