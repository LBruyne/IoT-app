import React from "react";
import { Layout, Divider } from "antd";
import CustomBreadcrumb from "@/components/CustomBreadcrumb";

const AboutView = () => (
  <Layout>
    <div>
      <CustomBreadcrumb arr={["关于"]}></CustomBreadcrumb>
    </div>
    <div className="base-style">
      <h3>关于作者</h3>
      <Divider />
      <p>浙江大学 软件工程专业 1801 刘轩铭</p>
    </div>
  </Layout>
);

export default AboutView;
