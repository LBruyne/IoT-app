import axios from "axios";

// 这里取决于登录的时候将 token 存储在哪里
const token = localStorage.getItem("token");

const instance = axios.create({
  timeout: 5000
});

// 设置post请求头
instance.defaults.headers.post["Content-Type"] =
  "application/x-www-form-urlencoded";

// 添加请求拦截器
instance.interceptors.request.use(
  config => {
    // 将 token 添加到请求头
    token && (config.headers.Authorization = token);
    return config;
  },
  error => {
    return Promise.reject(error);
  }
);

export default instance;
