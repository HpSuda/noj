<template>
  <div class="announcement">
    <!-- 标题和小标题，左对齐 -->
    <div class="title">
      <h2 style="color: orange;">{{ announcement.title }}</h2>
      <p class="subtitle" style="color: brown;">{{ announcement.subtitle }}</p>
    </div>

    <!-- 正文，居中对齐 -->
    <div class="content">
      <p>{{ announcement.content }}</p>
    </div>

    <!-- 作者和发表时间，左对齐 -->
    <div class="info">
      <p style="color: beige;">作者: {{ announcement.author }}</p>
      <p style="color: aquamarine;">发表时间: {{ announcement.publishDate }}</p>
    </div>


  </div>
</template>

<script>
export default {
  props: {
    announcement: Object, // 从父组件传递公告数据
  },
};
</script>

<style scoped>
.announcement {
  width: 100%;
  max-width: 800px;
  margin: 0 auto;
  padding: 20px;
  background-color: #333; /* 设置背景颜色为深色 */
  color: #fff; /* 设置文字颜色为白色 */
  border-radius: 5px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
  display: flex;
  flex-direction: column;
  /* justify-content: space-between; */
  align-items: flex-start;
  gap: 20px;
  margin-top: 50px;
}

.title {
  h2 {
    margin: 0;
    padding: 0;
    color: #fff; /* 标题文字颜色为白色 */
  }
  .subtitle {
    margin: 0;
    padding: 0;
  }
}

.info {
  p {
    margin: 0;
    padding: 0;
    color: #bbb; /* 作者和发表时间文字颜色为灰色 */
  }
}

.content {
  text-align: center;
  margin: 20px 0;
}
</style>
