import React, { useState } from "react";
import { Layout, Input, Form, Button, Divider, message } from "antd";
import { withRouter } from "react-router-dom";
import axios from "@/api";
import { BASE_URL } from "@/api/config";
import "@/style/view-style/login.scss";
import { UserOutlined, LockOutlined, HomeOutlined } from "@ant-design/icons";

const Register = props => {
  const [loading, setLoading] = useState(false);

  const { getFieldDecorator } = props.form;

  const handleSubmit = e => {
    e.preventDefault();
    props.form.validateFields((err, values) => {
      if (!err) {
        let { username, password, email } = values;
        let postData = {
          userName: username,
          userPassword: password,
          email: email
        };
        // 发起注册请求
        axios
          .post(`${BASE_URL}/register`, postData)
          .then(res => {
            console.log(res.data);
            if (res.data.code === 0) {
              message.success("注册成功！请返回登陆");
              setLoading(true);
              setTimeout(() => {
                message.success("正在返回...");
                props.history.push("/login");
              }, 1000);
            } else {
              message.error("注册失败, " + res.data.message);
            }
          })
          .catch(err => {
            message.error("注册失败，账号存在或格式错误");
          });
      }
    });
  };

  return (
    <Layout className="login animated fadeIn">
      <div className="model">
        <div className="login-form">
          <h3>物联网设备管理系统</h3>
          <Divider />
          <Form onSubmit={handleSubmit}>
            <Form.Item>
              {getFieldDecorator("username", {
                rules: [{ required: true, message: "请输入用户名!" }]
              })(<Input prefix={<UserOutlined />} placeholder="用户名" />)}
            </Form.Item>
            <Form.Item>
              {getFieldDecorator("password", {
                rules: [{ required: true, message: "请输入密码" }]
              })(
                <Input
                  prefix={<LockOutlined />}
                  type="password"
                  placeholder="密码"
                />
              )}
            </Form.Item>
            <Form.Item>
              {getFieldDecorator("email", {
                rules: [{ required: true, message: "请输入邮箱" }]
              })(<Input prefix={<HomeOutlined />} placeholder="邮箱" />)}
            </Form.Item>
            <Form.Item>
              <Button
                type="primary"
                htmlType="submit"
                className="login-form-button"
                loading={loading}
              >
                注册
              </Button>
              <Button
                type="dashed"
                className="login-form-button"
                onClick={event => {
                  props.history.push("/login");
                }}
              >
                登陆
              </Button>
            </Form.Item>
          </Form>
        </div>
      </div>
    </Layout>
  );
};

export default withRouter(Form.create()(Register));
