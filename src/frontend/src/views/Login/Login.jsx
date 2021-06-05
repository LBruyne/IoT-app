import React, {useState, useEffect} from "react";
import {
    Layout,
    Input,
    Form,
    Button,
    Divider,
    message,
    notification
} from "antd";
import {withRouter} from "react-router-dom";
import axios from "@/api";
import {BASE_URL} from "@/api/config";
import "@/style/view-style/login.scss";
import {LockOutlined, UserOutlined} from "@ant-design/icons";

const Login = props => {
    const [loading, setLoading] = useState(false);

    const {getFieldDecorator} = props.form;

    const handleSubmit = e => {
        e.preventDefault();
        props.form.validateFields((err, values) => {
            if (!err) {
                let {username, password} = values;
                let postData = {
                    userName: username,
                    userPassword: password
                };
                // 发起登陆请求
                axios
                    .post(`${BASE_URL}/login`, postData)
                    .then(res => {
                        console.log(res.data);
                        if (res.data.code === 0) {
                            // 权限校验 模拟接口返回用户权限标识
                            let user = res.data.data;
                            if (user) {
                                switch (user.userName) {
                                    case "admin":
                                        user.auth = 0;
                                        break;
                                    default:
                                        user.auth = 1;
                                }
                            }
                            localStorage.setItem("user", JSON.stringify(user));
                            localStorage.setItem("token", res.data.data.token);
                            message.success("登录成功!");
                            if (localStorage.getItem("user")) {
                                setLoading(true);
                                setTimeout(() => {
                                    message.success("登录成功!");
                                    props.history.push("/");
                                }, 1000);
                            }
                        } else {
                            message.error("登陆失败, " + res.data.message);
                        }
                    })
                    .catch(err => {
                        message.error("登陆失败，账号不存在或密码错误");
                    });
            }
        });
    };

    useEffect(() => {
        notification.open({
            message: "欢迎使用物联网设备管理平台",
            duration: null,
            description: "管理员账号admin(admin)，其他游客请注册后使用平台"
        });
        if(localStorage.getItem("token")) {
            props.history.push("/index");
        }
        return () => {
            notification.destroy();
        };
        // eslint-disable-next-line
    }, []);

    return (
        <Layout className="login animated fadeIn">
            <div className="model">
                <div className="login-form">
                    <h3>物联网设备管理系统</h3>
                    <Divider/>
                    <Form onSubmit={handleSubmit}>
                        <Form.Item>
                            {getFieldDecorator("username", {
                                rules: [{required: true, message: "请输入用户名!"}]
                            })(<Input prefix={<UserOutlined/>} placeholder="用户名"/>)}
                        </Form.Item>
                        <Form.Item>
                            {getFieldDecorator("password", {
                                rules: [{required: true, message: "请输入密码"}]
                            })(
                                <Input
                                    prefix={<LockOutlined/>}
                                    type="password"
                                    placeholder="密码"
                                />
                            )}
                        </Form.Item>
                        <Form.Item>
                            <Button
                                type="primary"
                                htmlType="submit"
                                className="login-form-button"
                                loading={loading}
                            >
                                登录
                            </Button>
                            <Button
                                type="dashed"
                                className="login-form-button"
                                onClick={event => {
                                    props.history.push("/register");
                                }}
                            >
                                注册
                            </Button>
                        </Form.Item>
                    </Form>
                </div>
            </div>
        </Layout>
    );
};

export default withRouter(Form.create()(Login));
