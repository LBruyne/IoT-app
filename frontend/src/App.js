import React from "react";
import {
  HashRouter as Router,
  Route,
  Switch,
  Redirect
} from "react-router-dom";
import loadable from "./utils/loadable";
import "animate.css";
import "./style/base.scss";
import "./style/App.scss";

// 公共模块
const DefaultLayout = loadable(() =>
  import(/* webpackChunkName: 'default' */ "./containers")
);

// 页面
const Register = loadable(() =>
  import(/* webpackChunkName: 'register' */ "./views/Register")
);
const Login = loadable(() =>
  import(/* webpackChunkName: 'login' */ "./views/Login")
);
const View404 = loadable(() =>
  import(/* webpackChunkName: '404' */ "./views/Others/404")
);

const App = () => (
  <Router>
    <Switch>
      <Route path="/" exact render={() => <Redirect to="/index" />} />
      <Route path="/login" component={Login} />
      <Route path="/register" component={Register} />
      <Route path="/404" component={View404} />
      <Route component={DefaultLayout} />
    </Switch>
  </Router>
);

export default App;
