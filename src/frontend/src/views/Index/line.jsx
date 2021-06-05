import React, { useEffect } from "react";
import echarts from "echarts/lib/echarts";
import "echarts/lib/chart/line";
import "echarts/lib/component/tooltip";
import "echarts/lib/component/title";
import "echarts/lib/component/legend";
import PropTypes from "prop-types";

const Line = () => {
  useEffect(() => {
    let myChart = echarts.init(document.getElementById("line"));
    myChart.setOption({
      tooltip: {
        trigger: "axis"
      },
      legend: {
        data: ["在线设备", "离线设备", "总设备数"]
      },
      grid: {
        left: "3%",
        right: "4%",
        bottom: "3%",
        containLabel: true
      },
      toolbox: {
        feature: {
          saveAsImage: {}
        }
      },
      xAxis: {
        type: "category",
        boundaryGap: false,
        data: ["周一", "周二", "周三", "周四", "周五", "周六", "周日"]
      },
      yAxis: {
        type: "value"
      },
      series: [
        {
          name: "在线设备",
          type: "line",
          data: [20, 18, 4, 9, 22, 44, 36]
        },
        {
          name: "离线设备",
          type: "line",
          data: [20, 15, 6, 8, 33, 22, 13]
        },
        {
          name: "总设备数",
          type: "line",
          data: [40, 33, 10, 17, 55, 66, 49]
        }
      ]
    });
    window.addEventListener("resize", function() {
      myChart.resize();
    });
  }, []);

  return <div id="line" style={{ height: 300 }}></div>;
};


Line.propTypes = {
  data: PropTypes.object
};

function shouldRender(nextProps, prevProps) {
  if (nextProps.pieData === null) {
    return false;
  }
  if (nextProps.pieData !== prevProps.pieData) {
    return true;
  }
  return false;
}

export default React.memo(Line, shouldRender);
