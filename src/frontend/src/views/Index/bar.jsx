import React, { useEffect } from "react";
import echarts from "echarts/lib/echarts";
import "echarts/lib/chart/bar";
import "echarts/lib/component/tooltip";
import "echarts/lib/component/title";
import "echarts/lib/component/legend";

const Bar = () => {
  useEffect(() => {
    let myChart = echarts.init(document.getElementById("bar"));
    myChart.setOption({
      tooltip: {
        trigger: "axis",
        axisPointer: {
          // 坐标轴指示器，坐标轴触发有效
          type: "shadow" // 默认为直线，可选为：'line' | 'shadow'
        }
      },
      legend: {
        data: [
          "其他",
          "智能穿戴设备",
          "智能家居设备",
          "智能物流设备",
          "智能飞行器设备",
          "智能互联网设备",
          "智能无线设备",
          "新型物联网设备"
        ]
      },
      grid: {
        left: "3%",
        right: "4%",
        bottom: "3%",
        containLabel: true
      },
      xAxis: [
        {
          type: "category",
          data: ["周一", "周二", "周三", "周四", "周五", "周六", "周日"]
        }
      ],
      yAxis: [
        {
          type: "value"
        }
      ],
      series: [
        {
          name: "其他",
          type: "bar",
          data: [320, 332, 301, 334, 390, 330, 320]
        },
        {
          name: "智能穿戴设备",
          type: "bar",
          stack: "广告",
          data: [120, 132, 101, 134, 90, 230, 210]
        },
        {
          name: "智能家居设备",
          type: "bar",
          stack: "广告",
          data: [220, 182, 191, 234, 290, 330, 310]
        },
        {
          name: "智能物流设备",
          type: "bar",
          stack: "广告",
          data: [150, 232, 201, 154, 190, 330, 410]
        },
        {
          name: "智能飞行器设备",
          type: "bar",
          data: [862, 1018, 964, 1026, 1679, 1600, 1570],
          markLine: {
            lineStyle: {
              normal: {
                type: "dashed"
              }
            },
            data: [[{ type: "min" }, { type: "max" }]]
          }
        },
        {
          name: "智能互联网设备",
          type: "bar",
          barWidth: 5,
          stack: "搜索引擎",
          data: [620, 732, 701, 734, 1090, 1130, 1120]
        },
        {
          name: "智能无线设备",
          type: "bar",
          stack: "搜索引擎",
          data: [120, 132, 101, 134, 290, 230, 220]
        },
        {
          name: "新型物联网设备",
          type: "bar",
          stack: "搜索引擎",
          data: [60, 72, 71, 74, 190, 130, 110]
        },
      ]
    });
    window.addEventListener("resize", function() {
      myChart.resize();
    });
  }, []);
  return <div id="bar" style={{ height: 300, background: "#fff" }}></div>;
};

export default Bar;
