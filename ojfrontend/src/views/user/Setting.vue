<template>
<div class="setting_page">
  <el-row class="row-col_box" :gutter="20">
    <!--左侧栏-->
    <el-col :span="16">
      <el-card class="info-card">
        <el-form class="info-form" label-position="top" label-width="80px":model="formLabelAlign">
          <el-form-item label="昵称" class="form-label">
            <el-input size="small" v-model="formLabelAlign.name"></el-input>
          </el-form-item>
          <el-form-item label="个性签名" class="form-label">
            <el-input type="textarea"
                      :rows="3"
                      resize="none"
                      placeholder="可以写下描述自己，或自己喜欢的话"
                      size="small"
                      v-model="formLabelAlign.signature"></el-input>
          </el-form-item>
          <el-form-item label="组织" class="form-label">
            <el-input placeholder="你的学校、公司或组织"
                      size="small"
                      v-model="formLabelAlign.organization"></el-input>
          </el-form-item>
          <el-form-item label="QQ" class="form-label">
            <el-input placeholder="仅数字，如：1234567"
                      size="small"
                      v-model="formLabelAlign.qq"></el-input>
          </el-form-item>
          <el-form-item label="Github" class="form-label">
            <el-input size="small" v-model="formLabelAlign.github"></el-input>
          </el-form-item >
          <el-form-item label="电子邮箱" class="form-label">
            <el-input size="small" v-model="formLabelAlign.email"></el-input>
          </el-form-item>
          <el-form-item label="博客" class="form-label">
            <el-input size="small" v-model="formLabelAlign.blog"></el-input>
          </el-form-item>
          <el-form-item class="form-label">
            <el-button class="primary-btn" type="primary" @click="onSubmit">提交</el-button>
          </el-form-item>
        </el-form>
      </el-card>
    </el-col>
    <el-col :span="8">
      <el-card class="avatar-card">
        <div class="section-title">头像设置</div>
        <!--  头像  -->
        <div class="avatar-container">
          <img class="avatar" :src="formLabelAlign.avatarUrl"/>
        </div>

        <el-upload
            class="upload-demo"
            drag
            accept="image/jpeg,image/gif,image/png"
            action=""
            :on-preview="picturePreview"
            :on-change="onUploadChange"
            :auto-upload="false"
            :show-file-list="false"
            :multiple="false">
          <i class="el-icon-upload"></i>
          <div class="el-upload__text">将文件拖到此处，或<em>点击上传</em></div>
          <div class="el-upload__tip" slot="tip">只能上传jpg/png文件，且不超过500kb</div>
        </el-upload>
      </el-card>
    </el-col>
  </el-row>
</div>
</template>

<script>
export default {
  name: "Setting",
  data(){
    return{
      formLabelAlign: {
        avatarUrl:require('@/assets/c1116342d1.png'),
        name: '',
        signature: '',
        organization:'',
        qq:'',
        github: '',
        email:'',
        blog:''
      }
    }
  },
  methods:{
    //限制上传文件格式
    onUploadChange(file) {
      const isIMAGE = (file.raw.type === 'image/jpeg' || file.raw.type === 'image/png' || file.raw.type === 'image/gif');
      const isLt1M = file.size / 1024 / 1024 < 1;

      if (!isIMAGE) {
        this.$message.error('上传文件只能是图片格式!');
        return false;
      }
      if (!isLt1M) {
        this.$message.error('上传文件大小不能超过 1MB!');
        return false;
      }
    }
  }
}
</script>

<style scoped>
.section-title {
  font-size: 21px;
  font-weight: 500;
  padding-top: 14px;
  padding-left: 5px;
  padding-bottom: 20px;
  line-height: 30px;
}
.row-col_box{
  margin-top: 1.5%;
}
.info-card{
  margin-left: 35%;
  margin-right: 0;
  margin-top: 0;
}
.avatar-card{
  margin-left: 0;
  margin-right: 35%;
  margin-top: 0;
}
.info-form{
  margin-top: 10px;
  margin-left: 5%;
  margin-right: 8%;
}
.form-label{
  margin-bottom: 12px;
}
.form-label :deep(.el-form-item__label){
  font-weight: bold;
  font-size: 17px;
  padding-bottom: 0;
}
.primary-btn{
  background-color: #2d8cf0;
  margin-top: 5px;
}
.upload-demo{
  width:100%;
  text-align: center;
}
:deep(.el-upload-dragger){
  width: 100%;
}
.avatar-container{
  text-align: center;
}
.avatar {
  width: 140px;
  height: 140px;
  margin: 1px 1px 1px 1px;
  border-radius: 50%;
  border: 1px solid #494949;
  /*box-shadow: 1px 1px 1px 1px;*/
}
</style>