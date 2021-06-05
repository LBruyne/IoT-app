import React, {useCallback, useEffect, useState} from "react";
import PropTypes from "prop-types";
import {Col, Table, Row, Statistic, Icon, message} from "antd";
import axios from "@/api";
import {BASE_URL} from "@/api/config";

const DeviceData = props => {
    // 初始化地图
    var map;

    const renderMap = positionList => {
        map = new window.BMap.Map("detailMap");

        map.addControl(
            new window.BMap.ScaleControl({anchor: window.BMAP_ANCHOR_TOP_RIGHT})
        );
        map.addControl(
            new window.BMap.NavigationControl({
                anchor: window.BMAP_ANCHOR_TOP_RIGHT
            })
        );
        map.centerAndZoom("杭州", 10);

        drawDeviceRoute(positionList);
    };

    // 绘制行驶路线
    const drawDeviceRoute = positionList => {
        let startPoint = "";
        let endPoint = "";
        console.log(positionList);
        if (positionList.length > 0) {
            let first = positionList[0];
            let end = positionList[positionList.length - 1];

            // 起点
            startPoint = new window.BMap.Point(first.lng, first.lat);
            let startIcon = new window.BMap.Icon(
                "http://lbsyun.baidu.com/jsdemo/img/fox.gif",
                new window.BMap.Size(100, 120),
                {
                    imageSize: new window.BMap.Size(360, 420),
                    anchor: new window.BMap.Size(36, 42)
                }
            );
            let startMarker = new window.BMap.Marker(startPoint, {icon: startIcon});
            map.addOverlay(startMarker); // 添加覆盖物初始点图标

            // 终点
            endPoint = new window.BMap.Point(end.lng, end.lat);
            let endIcon = new window.BMap.Icon(
                "http://lbsyun.baidu.com/jsdemo/img/fox.gif",
                new window.BMap.Size(100, 120),
                {
                    imageSize: new window.BMap.Size(36, 42),
                    anchor: new window.BMap.Size(36, 42)
                }
            );
            let endMarker = new window.BMap.Marker(endPoint, {icon: endIcon});
            map.addOverlay(endMarker); // 添加覆盖物终点图标
        }

        // 连接路线图
        let trackPoint = [];
        for (let i = 0; i < positionList.length; i++) {
            let point = positionList[i];
            trackPoint.push(new window.BMap.Point(point.lng, point.lat));
        }

        let polyline = new window.BMap.Polyline(trackPoint, {
            strokeColor: "#1869AD",
            strokeWeight: 3,
            strokeOpacity: 1
        });
        map.addOverlay(polyline);
        map.centerAndZoom(endPoint, 11);
    };

    const columns = [
        {
            title: "ID",
            dataIndex: "id",
            key: "id"
        },
        {
            title: "Value",
            className: "column-money",
            dataIndex: "value",
            key: "value"
        },
        {
            title: "时间",
            dataIndex: "timestamp",
            key: "timestamp"
        }
    ];

    // 展示的数据
    var code = "";
    let [deviceMessage, setDeviceMessage] = useState(null);

    // 设置分页
    let [total, setTotal] = useState(0);
    let [page, setPage] = useState(1);
    let [pageSize, setPageSize] = useState(10);

    // 信息数据
    let [data, setData] = useState([]);

    // loading状态
    let [load, setLoad] = useState(false);

    // 初始化时候请求一次数据
    useEffect(() => {
        renderMap([]);

        const record = props.record;

        if (record.length !== 0) {
            pageChange(page, pageSize, record);
        }
        // eslint-disable-next-line
    }, [props.record]);

    // 点击下面的分页按钮触发的方法
    const pageChange = useCallback(
        (currentPage, currentSize, currentRecords) => {
            // eslint-disable-next-line
            setPage((page = currentPage === undefined ? page : currentPage));
            // eslint-disable-next-line
            setPageSize(
                // eslint-disable-next-line
                (pageSize = currentSize === undefined ? pageSize : currentSize)
            );

            if (currentRecords !== undefined) {
                // eslint-disable-next-line
                code = currentRecords.length === 0 ? null : currentRecords[0].code;
            }

            fetchData();
        },
        // eslint-disable-next-line
        []
    );

    // 获取数据
    const fetchData = async () => {
        // 开始加载
        setLoad(true);

        let postData = {
            page: page,
            pageSize: pageSize,
            code: code
        };

        let result = await axios
            .post(`${BASE_URL}/device/message`, postData)
            .then(res => {
                if (res.data.code === 0) {
                    return res.data.data;
                } else {
                    message.error("读取设备信息列表失败，" + res.data.message);
                    return null;
                }
            })
            .catch(err => {
                message.error("读取设备信息列表失败");
            });
        if (result !== null) {
            setTotal(result.count);
            setData([...result.results]);

            //加载完地图之后加载路线
            var positionList = [];
            for (let i in result.results) {
                positionList.push({
                    lat: result.results[i].lat,
                    lng: result.results[i].lng
                });
            }

            renderMap(positionList);
        }

        // 加载完成
        setLoad(false);
    };

    return (
        <div>
            <Col span={12}>
                <div className="base-style">
                    <div style={{height: "600px"}} id="detailMap"></div>
                </div>
            </Col>
            <Col span={12}>
                <div className="base-style">
                    <Table
                        style={{height: "600px"}}
                        bordered
                        rowKey="id"
                        columns={columns}
                        loading={load}
                        expandedRowRender={record => (
                            <p style={{margin: 0}}>{record.info}</p>
                        )}
                        dataSource={data}
                        scroll={{y: 400}}
                        pagination={{
                            showSizeChanger: true,
                            onChange: pageChange,
                            pageSizeOptions: ["2", "5", "10"],
                            defaultPageSize: pageSize,
                            showTotal: () => {
                                return `共${total}条数据`;
                            },
                            current: page,
                            total: total
                        }}
                        onRow={record => {
                            return {
                                onMouseEnter: event => {
                                    deviceMessage = {
                                        code: record.code,
                                        value: record.value,
                                        alert: record.alert,
                                        info: record.info,
                                        timestamp: record.timestamp,
                                        lat: Math.round(record.lat * 100) / 100,
                                        lng: Math.round(record.lng * 100) / 100
                                    };
                                    setDeviceMessage(deviceMessage);
                                }
                            };
                        }}
                        title={() => {
                            return (
                                <Row gutter={16}>
                                    <Col span={12}>
                                        <Statistic
                                            title="目前状态"
                                            value={deviceMessage !== null ? deviceMessage.value : 0}
                                            prefix={
                                                <Icon
                                                    type={
                                                        deviceMessage != null
                                                            ? deviceMessage.alert !== 0
                                                            ? "like"
                                                            : "dislike"
                                                            : "loading"
                                                    }
                                                />
                                            }
                                        />
                                    </Col>
                                    <Col span={12}>
                                        <Statistic
                                            title="目前位置"
                                            value={
                                                "(" +
                                                (deviceMessage !== null ? deviceMessage.lng : 0) +
                                                ", " +
                                                (deviceMessage !== null ? deviceMessage.lat : 0) +
                                                ")"
                                            }
                                        />
                                    </Col>
                                </Row>
                            );
                        }}
                    />
                </div>
            </Col>
        </div>
    );
};

DeviceData.propTypes = {
    record: PropTypes.array
};

function shouldRender(nextProps, prevProps) {
    if (nextProps.record.length === 0) {
        return false;
    }
    if (nextProps.record.join() !== prevProps.record.join()) {
        return true;
    }
    return false;
}

export default React.memo(DeviceData, shouldRender);
