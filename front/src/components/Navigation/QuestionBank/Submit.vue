<template>
  <div>
    <form @submit.prevent="submitCode">
      <!-- 输入框 -->
      <div class="form-group">
        <label for="numberInput" style="color: rgb(0, 0, 0);">题目：</label>
        <input type="number" id="numberInput" v-model.number="number" placeholder="" />
      </div>

      <!-- 下拉列表 -->
      <div class="form-group">
        <label for="languageSelect" style="color: rgb(0, 0, 0);">编程语言：</label>
        <select id="languageSelect" v-model="selectedLanguage">
          <option value="python3">Python</option>
          <option value="Java">Java</option>
          <option value="c++">C++</option>
          <option value="c">C</option>
        </select>
      </div>
      <!-- 代码输入框 -->
      <div>
        <label for="codeInput" style="color: rgb(0, 0, 0);">提交代码:</label><br><br>
        <textarea id="codeInput" v-model="code" rows="5" style="width:60%; height: 200px;"></textarea>
      </div>

      <!-- 提交按钮 -->
      <button type="submit" class="sub" style="width: 100px">提交</button>
    </form>
  </div>
</template>
  
<script>
import { useCookies } from 'vue3-cookies';
export default {
  setup() {
    const { cookies } = useCookies();
    return { cookies };
  },
  data() {
    return {
      number: 0, // 输入框的默认值
      selectedLanguage: 'c++', // 下拉列表的默认值
      code: '', // 代码输入框的默认值
      submitInfo:{
        code:'',
        userId:'',
        language:'',
        problemId:''
      }
    };
  },
  created() {
    // 在这里添加获取数据的逻辑
    const itemIDCookie = this.getCookie('itemID');
    if (itemIDCookie) {
      this.number = parseInt(itemIDCookie); // 将字符串转换为整数
    }
    else this.number = 0;
  },
  methods: {
    submitCode() {
      // 在这里添加提交代码的逻辑
      const userid = this.cookies.get("userid");
      if (userid === '') {
        window.alert("请登录");
        return;
      }
      if (this.number === '') {
        window.alert('题目不能为空');
        return;
      }
      console.log(this.number);
      console.log(this.code);
      // const form_data = new FormData();
      //   form_data.append("language", this.selectedLanguage);
      //   form_data.append("problemid", this.number.toString());
      //   form_data.append("userid", userid);
      //   form_data.append("code", this.code);
      this.submitInfo.code = this.code;
      this.submitInfo.problemId = this.number.toString();
      this.submitInfo.userId = userid;
      this.submitInfo.language = this.selectedLanguage;
      console.log(this.submitInfo);
      this.$api({
        url: "/api/status/submit",
        method:'post',
        data: {
          'userId': this.submitInfo.userId,
          'code':this.submitInfo.code,
          'language':this.submitInfo.language,
          'problemId':this.submitInfo.problemId,
        },
        // data: form_data,
        Headers:'application/json'
      })
      .then(response => {
        console.log(response);
        if (response.data.data === null) {
          window.alert('提交失败');
        } else {
          window.alert('提交成功');
          this.$router.push('/question_bank/submissions');
        }
      }).catch(error => {
        console.error(error);
      });
      this.code = '';

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
  