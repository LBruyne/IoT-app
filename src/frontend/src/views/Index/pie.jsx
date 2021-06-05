import React, {useEffect} from "react";
import echarts from "echarts/lib/echarts";
import "echarts/lib/chart/pie";
import "echarts/lib/component/tooltip";
import "echarts/lib/component/title";
import "echarts/lib/component/legend";
import PropTypes from "prop-types";

const Pie = props => {
    useEffect(() => {
        console.log("在Pie内", props.data)
        var data = (props.data !== undefined && props.data != null) ? props.data : null;
        var resData = [];
        if (data !== null) {
            for (var i in data.names) {
                resData.push({
                    value: data.numbers[i],
                    name: data.names[i]
                })
            }
        }
        let myChart = echarts.init(document.getElementById("pie"));
        myChart.setOption({
            tooltip: {
                trigger: "item",
                formatter: "{a} <br/>{b} : {c} ({d}%)"
            },
            legend: {
                orient: "vertical",
                left: "left",
                data: data != null ? data.names : []
            },
            series: [
                {
                    name: "访问来源",
                    type: "pie",
                    radius: "55%",
                    center: ["50%", "60%"],
                    data: resData,
                    itemStyle: {
                        emphasis: {
                            shadowBlur: 10,
                            shadowOffsetX: 0,
                            shadowColor: "rgba(0, 0, 0, 0.5)"
                        }
                    }
                }
            ]
        });
        window.addEventListener("resize", function () {
            myChart.resize();
        });
    }, [props.data]);

    return <div id="pie" style={{height: 300}}></div>;
};

Pie.propTypes = {
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

export default React.memo(Pie, shouldRender);