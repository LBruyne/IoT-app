import React, { useEffect, useState } from "react";
import { Layout, Row, Col, Icon, Divider, message } from "antd";
import screenfull from "screenfull";
import "@/style/view-style/index.scss";

import BarEcharts from "./bar.jsx";
import PieEcharts from "./pie.jsx";
import LineEcharts from "./line.jsx";
import CustomBreadcrumb from "@/components/CustomBreadcrumb";

import axios from "@/api";
import { BASE_URL } from "@/api/config";

const Index = () => {
  // eslint-disable-next-line
  let [data, setData] = useState(null);

  // 初始化时候请求一次数据
  useEffect(() => {
    fetchData();
  }, []);

  // 获取数据
  const fetchData = async () => {
    let result = await axios
      .get(`${BASE_URL}/device/statistic`)
      .then(res => {
        if (res.data.code === 0) {
          return res.data.data;
        } else {
          message.error("读取数据信息失败，" + res.data.message);
          return null;
        }
      })
      .catch(err => {
        message.error("读取数据信息失败");
      });
    if (result !== null) {
      console.log("在Index内", result);
      setData(result);
    }
  };

  const fullToggle = () => {
    if (screenfull.isEnabled) {
      screenfull.request(document.getElementById("bar"));
    }
  };

  return (
    <Layout className="index animated fadeIn">
      <CustomBreadcrumb arr={["统计数据"]} />
      <Row gutter={24} className="index-header">
        <Col span={6}>
          <div className="base-style other">
            <Icon type="slack" className="icon-style" />
            <div>
              <span>{data !== null ? data.typeData[0] : 0}</span>
              <div>其他</div>
            </div>
          </div>
        </Col>
        <Col span={6}>
          <div className="base-style smart-wearing">
            <Icon type="sketch" className="icon-style" />
            <div>
              <span>{data !== null ? data.typeData[1] : 0}</span>
              <div>智能穿戴设备</div>
            </div>
          </div>
        </Col>
        <Col span={6}>
          <div className="base-style smart-housing">
            <Icon type="android" className="icon-style" />
            <div>
              <span>{data !== null ? data.typeData[2] : 0}</span>
              <div>智能家居设备</div>
            </div>
          </div>
        </Col>
        <Col span={6}>
          <div className="base-style smart-material">
            <Icon type="yuque" className="icon-style" />
            <div>
              <span>{data !== null ? data.typeData[3] : 0}</span>
              <div>智能物流设备</div>
            </div>
          </div>
        </Col>
        <Col span={6}>
          <div className="base-style aerobat">
            <Icon type="twitter" className="icon-style" />
            <div>
              <span>{data !== null ? data.typeData[4] : 0}</span>
              <div>智能飞行器设备</div>
            </div>
          </div>
        </Col>
        <Col span={6}>
          <div className="base-style smart-internet">
            <Icon type="aliyun" className="icon-style" />
            <div>
              <span>{data !== null ? data.typeData[5] : 0}</span>
              <div>智能互联网设备</div>
            </div>
          </div>
        </Col>
        <Col span={6}>
          <div className="base-style smart-offline">
            <Icon type="reddit" className="icon-style" />
            <div>
              <span>{data !== null ? data.typeData[6] : 0}</span>
              <div>智能无线设备</div>
            </div>
          </div>
        </Col>
        <Col span={6}>
          <div className="base-style new-iot">
            <Icon type="ant-cloud" className="icon-style" />
            <div>
              <span>{data !== null ? data.typeData[7] : 0}</span>
              <div>新型物联网设备</div>
            </div>
          </div>
        </Col>
      </Row>
      <Row>
        <Col>
          <div className="base-style">
            <div className="bar-header">
              <div>MQTT数据流量柱状图（可全屏展示）</div>
              <Icon
                type="fullscreen"
                style={{ cursor: "pointer" }}
                onClick={fullToggle}
              />
            </div>
            <Divider />
            <BarEcharts data={data !== null ? data.barData : null} />
          </div>
        </Col>
      </Row>
      <Row gutter={8}>
        <Col span={12}>
          <div className="base-style">
            <div className="bar-header">
              <div>设备上线状况</div>
            </div>
            <Divider />
            <LineEcharts data={data !== null ? data.lineData : null} />
          </div>
        </Col>
        <Col span={12}>
          <div className="base-style">
            <div className="bar-header">
              <div>设备分类占比</div>
            </div>
            <Divider />
            <PieEcharts data={data !== null ? data.pieData : null} />
          </div>
        </Col>
      </Row>
    </Layout>
  );
};

export default Index;
