<template>
  <div class="app-container home">
    <el-row :gutter="20">
      <el-col :sm="24" :lg="12" style="padding-left: 20px">
        <h2>欢迎使用医院预约挂号管理系统</h2>
      </el-col>

    </el-row>
    <el-divider />
    <panel-group @handleSetLineChartData="handleSetLineChartData" :userTotal="userTotal" :messageTotal="messageTotal" />

    <el-card>
      挂号数量（月）
      <el-row style="background:#fff;padding:16px 16px 0;margin-bottom:32px;">
        <bar-chart />
      </el-row>
    </el-card>

   <el-divider />

    <el-card>
      挂号数量统计（周）
      <el-row style="background:#fff;padding:16px 16px 0;margin-bottom:32px;">
        <bar-chart1 />
      </el-row>
    </el-card>


    <el-card>
      挂号数量统计（天）
      <el-row style="background:#fff;padding:16px 16px 0;margin-bottom:32px;">
        <bar-chart2 />
      </el-row>
    </el-card>

    <!-- <el-row :gutter="32">

      <el-col :xs="24" :sm="24" :lg="8">
        <div class="chart-wrapper">
          <bar-chart />
        </div>
      </el-col>
    </el-row> -->
  </div>
</template>

<script>
  import PanelGroup from './dashboard/PanelGroup'
  import LineChart from './dashboard/LineChart'
  import RaddarChart from './dashboard/RaddarChart'
  import PieChart from './dashboard/PieChart'
  import BarChart from './dashboard/BarChart'
  import BarChart1 from './dashboard/BarChart1'
  import BarChart2 from './dashboard/BarChart2'
  import { selectCount } from "@/api/system/user";
  import { listEvaluate } from "@/api/system/evaluate";

  const lineChartData = {
    newVisitis: {
      expectedData: [100, 120, 161, 134, 105, 160, 165],
      actualData: [120, 82, 91, 154, 162, 140, 145]
    },
    messages: {
      expectedData: [200, 192, 120, 144, 160, 130, 140],
      actualData: [180, 160, 151, 106, 145, 150, 130]
    }
  }
export default {
  name: "Index",
  components: {
    PanelGroup,
    LineChart,
    RaddarChart,
    PieChart,
    BarChart,
    BarChart1,
    BarChart2
  },
  data() {
    return {
      // 版本号
      version: "3.8.5",
      lineChartData: lineChartData.newVisitis,
      userTotal: 0,
      messageTotal: 0,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 100,
      },
    };
  },
  mounted() {
      this.getUserList()
      this.getEvaluateList()
    },
  methods: {
      getEvaluateList() {
        listEvaluate(this.queryParams).then(response => {
          this.messageTotal = response.total;
        });
      },
      /** 查询用户列表 */
      getUserList() {
        selectCount().then(response => {
            this.userTotal = response.data;
          }
        );
      },
    goTarget(href) {
      window.open(href, "_blank");
    },
    handleSetLineChartData(type) {
      this.lineChartData = lineChartData[type]
    }
  }
};
</script>

<style scoped lang="scss">
.home {
  blockquote {
    padding: 10px 20px;
    margin: 0 0 20px;
    font-size: 17.5px;
    border-left: 5px solid #eee;
  }
  hr {
    margin-top: 20px;
    margin-bottom: 20px;
    border: 0;
    border-top: 1px solid #eee;
  }
  .col-item {
    margin-bottom: 20px;
  }

  ul {
    padding: 0;
    margin: 0;
  }

  font-family: "open sans", "Helvetica Neue", Helvetica, Arial, sans-serif;
  font-size: 13px;
  color: #676a6c;
  overflow-x: hidden;

  ul {
    list-style-type: none;
  }

  h4 {
    margin-top: 0px;
  }

  h2 {
    margin-top: 10px;
    font-size: 26px;
    font-weight: 100;
  }

  p {
    margin-top: 10px;

    b {
      font-weight: 700;
    }
  }

  .update-log {
    ol {
      display: block;
      list-style-type: decimal;
      margin-block-start: 1em;
      margin-block-end: 1em;
      margin-inline-start: 0;
      margin-inline-end: 0;
      padding-inline-start: 40px;
    }
  }
}
</style>

<style lang="scss" scoped>
.dashboard-editor-container {
  padding: 32px;
  background-color: rgb(240, 242, 245);
  position: relative;

  .chart-wrapper {
    background: #fff;
    padding: 16px 16px 0;
    margin-bottom: 32px;
  }
}

@media (max-width:1024px) {
  .chart-wrapper {
    padding: 8px;
  }
}
</style>
