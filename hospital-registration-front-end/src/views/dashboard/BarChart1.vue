<template>
  <div :class="className" :style="{height:height,width:width}" />
</template>

<script>
import * as echarts from 'echarts';
require('echarts/theme/macarons') // echarts theme
import resize from './mixins/resize'
import { getStatisticsWeekVO } from "@/api/system/registration";
const animationDuration = 6000

export default {
  mixins: [resize],
  props: {
    className: {
      type: String,
      default: 'chart'
    },
    width: {
      type: String,
      default: '100%'
    },
    height: {
      type: String,
      default: '300px'
    }
  },
  data() {
    return {
      chart: null,
      xAxisData: [1],
      seriesData: []
    }
  },
  mounted() {
    // this.$nextTick(() => {
    //   this.initChart()
    // })
    this.getList()
  },
  beforeDestroy() {
    if (!this.chart) {
      return
    }
    this.chart.dispose()
    this.chart = null
  },
  methods: {

        getList() {
          getStatisticsWeekVO(this.queryParams).then(response => {
            response.rows.forEach(item => {
              this.seriesData[0] = item.countNum
              this.$nextTick(() => {
                this.initChart()
              })
            })
          });
        },
    initChart() {
      this.chart = echarts.init(this.$el, 'macarons')

      this.chart.setOption({
        tooltip: {
          trigger: 'axis',
          axisPointer: { // 坐标轴指示器，坐标轴触发有效
            type: 'shadow' // 默认为直线，可选为：'line' | 'shadow'
          }
        },
        grid: {
          top: 10,
          left: '2%',
          right: '2%',
          bottom: '3%',
          containLabel: true
        },
        xAxis: [{
          type: 'category',
          data: this.xAxisData,
          axisTick: {
            alignWithLabel: true
          }
        }],
        yAxis: [{
          type: 'value',
          axisTick: {
            show: false
          }
        }],
        series: [{
          name: '挂号数量',
          type: 'bar',
          stack: 'vistors',
          barWidth: '60%',
          data: this.seriesData,
          animationDuration
        }]
      })
    }
  }
}
</script>
