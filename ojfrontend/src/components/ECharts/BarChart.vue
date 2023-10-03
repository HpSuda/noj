<template>
  <div :id="uuid" class="barChart" :style="style" ref="chart"></div>
</template>

<script>
const idGen = () =>{
  return new Date().getTime()
}
export default {
  props:{
    options:{
      type:Object,
      default:null
    },
    height: {
      type:String,
      default:'400px'
    },
    width:{
      type:String,
      default:'100%'
    },
    topList:{
      type:Object,
      default:null
    },
  },
  data(){
    return{
      uuid: null,
      myChart: null,
    }
  },
  computed:{
    style(){
      return{
        height:this.height,
        width:this.width,
      }
    }
  },
  created(){
    this.uuid = idGen()
  },
  mounted() {
    this.initCharts();
  },
  methods: {
    initCharts() {
      console.log(this.topList)
      this.myChart = this.$echarts.init(document.getElementById(this.uuid));
      console.log(this.myChart)
      window.onresize = function() {
        this.myChart.resize();
      };
      this.myChart.setOption({
        legend: {
          // Try 'horizontal'
          orient: 'vertical',
          right: 10,
          top: 'center'
        },
        color:['#ff9300','#54d6b6','#f7de00','#2d8cf0'],
        dataset: {
          dimensions: ['username', 'ac', 'tot'],
          source: this.topList
        },
        xAxis: {type: 'category'},
        yAxis: {},
        series: [{type: 'bar',barWidth: 30}, {type: 'bar',barWidth: 30}]
      });
      console.log(this.myChart)
    },

  }
}
</script>

<style lang="less" scoped>
.barChart{
  margin-top: 0%;
  width: 100%;
}
</style>