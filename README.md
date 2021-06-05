# IoT-app

**Hinsliu 3180106071@zju.edu.cn**

## Introduction

这是一个IOT物联网设备管理APP。该应用用于浙江大学2020-2021学年春夏学期B/S体系软件设计课程大作业，由我一人独立开发，版权所有。

### 功能列表

实现的基本功能如下：

1.	搭建一个mqtt服务器，能够接收指定的物联网终端模拟器发送的数据。

2.	实现用户注册、登录功能，用户注册时需要填写必要的信息并验证，如用户名、密码要求在6字节以上，email的格式验证，并保证用户名和email在系统中唯一，用户登录后可以进行以下操作。

3.	提供设备配置界面，可以创建或修改设备信息，包含必要信息，如设备ID、设备名称等。

4.	提供设备上报数据的查询统计界面。

5.	提供地图界面展示设备信息，区分正常和告警信息，并可以展示历史轨迹/

6.	首页提供统计信息（设备总量、在线总量、接收的数据量等），以图表方式展示（柱状体、折线图等）。

7.	样式适配手机端，能够在手机浏览器/微信等应用内置的浏览器中友好显示。

8.  项目使用的数据库，建议使用mysql或mangodb，提交作业时同时附带建库建表脚本文件。


## Quick Start

### 技术栈

项目使用前后端分离的开发范式，均由我本人完成。

- 前端使用React + Antd组件库进行设计（拥抱Hook风格）

- 后端使用Spring + SpringMVC进行设计，用SpringBoot框架进行配置简化，JDK1.8

- 数据库使用本地的MySQL进行数据储存，使用本地的Redis进行用户Token储存服务

- IoT服务中涉及的通信功能采用MQTT协议完成，具体使用的MQTT实现和代理为Mosquitto，基于此在本机上建立MQTT服务器

- 开发机(devbox)为MacBook Pro，使用的操作系统为macOS Catalina 10.15.7

### 项目目录结构

- 本目录下存放README文件，请先阅读本文件；以及本项目的功能快速浏览（glance-over）。

- src文件夹下存放的为本项目源码，其中：

  - frontend文件夹中存放前端工程文件，开发IDE为WebStorm

  - backend文件夹中存放后端工程文件，开发IDE为IntelliJ IDEA

  - client文件夹中存放一个SpringBoot框架下的IoT设备模拟器，用来模拟设备对数据的发布。该部分代码由bs.cs.zju.edu.cn的老师提供，我对其进行了改动和封装。

- build文件夹中存放的是MySQL的建表文件，其中create用来创建数据库和表，source用来添加测试数据
  
- documents文件夹下存放了本项目的设计文档，使用手册（包含功能测试）


### 如何启动应用

#### 基础准备

1. 在本地安装Java JDK 1.8，并完成相关配置

2. 在本地安装node.js，并完成相关配置

3. 在本地安装MySQL，并完成相关配置

4. 在本地安装Redis，并完成相关配置 

#### 搭建MQTT服务器

见[Mac下Mosquitto安装与配置指南](./README-Mosquitto.md)

#### 运行后端工程项目

完成对MySQL服务的配置、对Redis的配置、对Mosquitto连接的配置

运行后端工程文件下的src/IoTApplicationRunner.java文件，启动后端进程

#### 运行Client测试项目

完成对Mosquitto连接的配置以及设备数量等配置

然后使用Maven运行

```maven

mvn clean package

```

得到JAR文件，然后用JAR的方式运行该文件
#### 运行前端工程项目

在前端工程下运行

```shell

npm install

npm run start

```

启动React服务。在浏览器中搜索localhost:3000，就可以使用本应用






