import loadable from "@/utils/loadable";

const Index = loadable(() =>
  import(/* webpackChunkName: 'index' */ "@/views/Index")
);
const Device = loadable(() =>
  import(/* webpackChunkName: 'Device' */ "@/views/Device")
);
const About = loadable(() =>
  import(/* webpackChunkName: 'about' */ "@/views/About")
);

const routes = [
  { path: "/index", exact: true, name: "Index", component: Index, auth: [1] },
  { path: "/device", exact: false, name: "设备", component: Device, auth: [1] },
  { path: "/about", exact: false, name: "关于", component: About, auth: [1] }
];

export default routes;
