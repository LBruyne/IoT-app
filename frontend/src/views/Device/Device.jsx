import React from "react";
import { Layout, Form } from "antd";
import { withRouter } from "react-router-dom";
import CustomBreadcrumb from "@/components/CustomBreadcrumb";

const Device = props => {
  return (
    <Layout>
      <div>
        <CustomBreadcrumb arr={["设备"]}></CustomBreadcrumb>
      </div>
      PASS
    </Layout>
  );
};

export default withRouter(Form.create()(Device));
