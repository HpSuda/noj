<template>
  <div :id="uuid" class="pieChart" :style="style" ref="chart"></div>
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
      default:'350px'
    },
    width:{
      type:String,
      default:'100%'
    },
    AC:{
      type:Number,
      default:null
    },
    Total:{
      type:Number,
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
      this.myChart = this.$echarts.init(document.getElementById(this.uuid));
      console.log(this.AC)
      console.log(this.ac)
      console.log(this.myChart)
      this.myChart.setOption({
        title: {
          text: "AC/Total",
              left: "center",
        },
        tooltip: {
          trigger: "item",
        },
        legend: {
          orient: "vertical",
              left: "left",
        },
        series: [
          {
            name: "",
            type: "pie",
            radius: "50%",
            data: [
              {
                value: (this.Total-this.AC),
                name: "WA",
                itemStyle: {
                  color: "#ed3f14",
                },
              },
              {
                value: (this.AC),
                name: "AC",
                itemStyle: {
                  color: "#19be6b",
                },
              },
            ],
            emphasis: {
              itemStyle: {
                normal: {
                  label: {
                    show: true,//饼图上是否出现标注文字 标注各模块代表什么  默认是true
                    position: 'inner',//控制饼图上标注文字相对于饼图的位置  默认位置在饼图外
                  },
                  labelLine: {
                    show: false//官网demo里外部标注上的小细线的显示隐藏    默认显示
                  }
                },
                shadowBlur: 10,
                shadowOffsetX: 0,
                shadowColor: "rgba(0, 0, 0, 0.5)",
              },
            },
          },
        ],
      });
      console.log(this.myChart)
    },

  }
}
</script>

<style lang="less" scoped>
.pieChart{
  margin-top: 15%;
}
</style>