<template>
  <div>
    <el-card class="box-card">
      <div slot="header" class="clearfix">
        <!--   标题     -->
        <span class="card-title">{{ title }}</span>
        <!--    返回按钮    -->
        <el-button size="small" style="float:right" icon="el-icon-back"
                   v-if="!listVisible" @click="goBack()">返回</el-button>
      </div>
      <div class="text item">
        <!--    公告栏    -->
        <template v-if="listVisible">
          <el-table
              :show-header="false"
              style="width: 100%"
              :data="announcements"
              >
            <el-table-column
                width="800"
                show-overflow-tooltip
            >
              <template v-slot="scope">
                <el-button class="entry" @click="goAnnouncement(scope.row)" type="text" >
                  {{scope.row.title}}
                </el-button>
              </template>
            </el-table-column>
            <el-table-column
                prop="date"
                width="250"
                align="center"
            >
            </el-table-column>
            <el-table-column
                prop="author"
                align="center"
            >
            </el-table-column>
          </el-table>
        </template>
        <!--    公告内容    -->
        <template v-else>
          <div v-html="announcement.content" key="content" class="content-container"></div>
        </template>
      </div>
    </el-card>
  </div>
</template>

<script>
export default {
  name: "announcements",
  data() {
    return {
      listVisible:true,
      announcements:[],
      announcement:'',
      tableData: [{
        title: '入站须知',
        author: '龙力建',
        date: '2022-09-18 15:34:22',
        content:'<p>本站仅供学习交流</p><br/><p>不可用于商业用途</p>'
      },
        {
        title: '管理须知',
        author: '林老德',
        date: '2022-09-18 15:36:12',
        content:'<p>请勿修改本站设置</p>'
      }],
    }
  },
  computed: {
    title() {
      if (this.listVisible) {
        return '公告栏'
      } else {
        return this.announcement.title
      }
    }
  },
  mounted(){
    this.init()
  },
  methods:{
    //页面初始化
    init(){
      this.getAnnouncementList()
    },
    //获取广告清单
    getAnnouncementList(){
      this.announcements = this.tableData
      console.log(this.announcements)
    },
    //查看广告内容
    goAnnouncement(announcement){
      this.announcement = announcement
      console.log(this.announcement)
      this.listVisible = false
    },
    //返回
    goBack(){
      this.announcement = ''
      this.listVisible = true
    },
  }
}
</script>

<style scoped>
.text {
  font-size: 14px;
}
.item {
  margin-bottom: 18px;
}
.box-card{
  margin-left: 5%;
  margin-right: 5%;
  margin-top: 1.5%;
}
.card-title{
  font-size: 20px;
}
.entry{
  color: #495060;
}
.content-container {
  padding: 20px 20px 20px 20px;
  font-size: 16px;
  line-height: 23px;
}
.content-container >>>p{
  margin-top: 0;
  margin-bottom: 0;
}

</style>