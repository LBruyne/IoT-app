import React, {useEffect, useState, useCallback} from "react";
import {
    Layout,
    Form,
    Row,
    Table,
    Button,
    message,
    Collapse,
    Select,
    Input,
    Drawer
} from "antd";
import {withRouter} from "react-router-dom";
import axios from "@/api";
import {BASE_URL} from "@/api/config";
import CustomBreadcrumb from "@/components/CustomBreadcrumb";
import {UserOutlined, FormOutlined} from "@ant-design/icons";
import DeviceData from "./DeviceData";

const {Panel} = Collapse;
const {Option} = Select;
const {TextArea} = Input;

const Device = props => {
    // 抽屉
    let [isEdit, setIsEdit] = useState(true);
    let [editRecord, setEditRecord] = useState(null);
    let [drawerVisible, setDrawerVisible] = useState(false);

    const {getFieldDecorator} = props.form;

    // 搜索条件
    let filter = {
        code: null,
        name: null,
        type: null
    };

    // 设置分页
    let [total, setTotal] = useState(0);
    let [page, setPage] = useState(1);
    let [pageSize, setPageSize] = useState(10);

    // 表格数据
    let [tableData, setTableData] = useState([]);

    // loading状态
    let [load, setLoad] = useState(false);

    // 选择的设备
    let [showRecord, setShowRecord] = useState([]);

    const columns = [
        {title: "编号", dataIndex: "code", key: "code"},
        {title: "名称", dataIndex: "name", key: "name"},
        {title: "类型", dataIndex: "type", key: "type"},
        {title: "创建人", dataIndex: "creatorName", key: "creatorName"},
        {title: "创建时间", dataIndex: "createTime", key: "createTime"},
        {title: "最后上线", dataIndex: "updateTime", key: "updateTime"},
        {
            title: "操作",
            render: record => {
                return (
                    <Button
                        type="dashed"
                        onClick={() => {
                            setIsEdit(true);
                            setEditRecord(record);
                            setDrawerVisible(true);
                        }}
                    >
                        编辑
                    </Button>
                );
            }
        }
    ];

    // 初始化时候请求一次数据
    useEffect(() => {
        pageChange(page, pageSize);
        // eslint-disable-next-line
    }, []);

    // 点击下面的分页按钮触发的方法
    const pageChange = useCallback(
        (currentPage, currentSize) => {
            // eslint-disable-next-line
            setPage((page = currentPage === undefined ? page : currentPage));
            // eslint-disable-next-line
            setPageSize(
                // eslint-disable-next-line
                (pageSize = currentSize === undefined ? pageSize : currentSize)
            );
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
            code: filter.code,
            name: filter.name,
            creatorName: filter.creatorName,
            type: filter.type,
            startTime: filter.startTime,
            endTime: filter.endTime
        };
        let result = await axios
            .post(`${BASE_URL}/device/list`, postData)
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
            setTableData([...result.results]);
        }

        // 加载完成
        setLoad(false);
    };

    // 点击搜索按钮触发的方法
    const searchData = e => {
        e.preventDefault();
        props.form.validateFields((err, values) => {
            if (!err) {
                console.log("values", values);
                // 获取条件值
                filter.code = values && values.code !== undefined ? values.code : null;
                filter.name = values && values.name !== undefined ? values.name : null;
                filter.type = values && values.type !== undefined ? values.type : null;

                page = 1;
                setPage(page);
                fetchData();
            }
        });
    };

    const submitEdit = e => {
        e.preventDefault();
        props.form.validateFields((err, values) => {
            if (!err) {
                console.log(values);
                setDrawerVisible(false);
                // 获取修改结果
                let postData = {
                    id: editRecord.id,
                    name: values.editName,
                    description: values.editDescription,
                    creatorName: values.editCreatorName,
                    type: values.editType
                };
                axios
                    .post(`${BASE_URL}/device/edit`, postData)
                    .then(res => {
                        if (res.data.code === 0) {
                            message.info("更新设备成功");
                            props.history.go(-1);
                        } else {
                            message.error("更新设备失败，" + res.data.message);
                        }
                    })
                    .catch(err => {
                        message.error("更新设备失败");
                    });
            }
        });
    };

    const submitCreate = e => {
        e.preventDefault();
        props.form.validateFields((err, values) => {
            if (!err) {
                setDrawerVisible(false);
                // 获取创建结果
                let postData = {
                    code: values.editCode,
                    name: values.editName,
                    description: values.editDescription,
                    creatorName: values.editCreatorName,
                    type: values.editType
                };
                axios
                    .post(`${BASE_URL}/device/create`, postData)
                    .then(res => {
                        if (res.data.code === 0) {
                            message.info("创建设备成功");
                            props.history.go(-1);
                        } else {
                            message.error("创建设备失败，" + res.data.message);
                        }
                    })
                    .catch(err => {
                        message.error("创建设备失败");
                    });
            }
        });
    };

    const formReset = () => {
        props.form.resetFields();
    };

    return (
        <Layout>
            <CustomBreadcrumb arr={["设备"]}></CustomBreadcrumb>
            <Row className="base-style">
                <Collapse defaultActiveKey={["1"]} style={{marginBottom: "20px"}}>
                    <Panel header="搜索设备" key="1">
                        <Form
                            className="list"
                            autoComplete="off"
                            layout="inline"
                            name="basic"
                            onSubmit={searchData}
                        >
                            <Form.Item>
                                {getFieldDecorator("code", {
                                    rules: [{required: false}]
                                })(<Input prefix={<UserOutlined/>} placeholder="设备编号"/>)}
                            </Form.Item>
                            <Form.Item>
                                {getFieldDecorator("name", {
                                    rules: [{required: false}]
                                })(<Input prefix={<FormOutlined/>} placeholder="设备名称"/>)}
                            </Form.Item>
                            <Form.Item>
                                {getFieldDecorator("type", {
                                    rules: [{required: false}]
                                })(
                                    <Select
                                        showSearch
                                        style={{width: 200}}
                                        placeholder="设备类型"
                                        optionFilterProp="children"
                                        filterOption={(input, option) =>
                                            option.props.children
                                                .toLowerCase()
                                                .indexOf(input.toLowerCase()) >= 0
                                        }
                                    >
                                        <Option value="0">其他</Option>
                                        <Option value="1">智能穿戴设备</Option>
                                        <Option value="2">智能家居设备</Option>
                                        <Option value="3">智能物流设备</Option>
                                        <Option value="4">智能飞行器设备</Option>
                                        <Option value="5">智能互联网设备</Option>
                                        <Option value="6">智能无线设备</Option>
                                        <Option value="7">智能物联网设备</Option>
                                    </Select>
                                )}
                            </Form.Item>
                            <Form.Item>
                                <Button type="primary" htmlType="submit">
                                    查询
                                </Button>
                                <Button style={{marginLeft: 8}} onClick={formReset}>
                                    重置
                                </Button>
                            </Form.Item>
                        </Form>
                    </Panel>
                </Collapse>
            </Row>
            <Row className="base-style">
                <Row>
                    <Table
                        rowKey="id"
                        columns={columns}
                        loading={load}
                        expandedRowRender={record => (
                            <p style={{margin: 0}}>{record.description}</p>
                        )}
                        dataSource={tableData}
                        scroll={{y: 300}}
                        rowSelection={{
                            // row selection
                            onChange: (selectedRowKeys, selectedRows) => {
                                setShowRecord(selectedRows);
                            },
                            type: "radio",
                            getCheckboxProps: record => ({
                                name: record.name
                            })
                        }}
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
                    />
                </Row>
                <Row>
                    <Button
                        type="primary"
                        style={{marginTop: "10px"}}
                        onClick={() => {
                            setEditRecord(null);
                            setIsEdit(false);
                            setDrawerVisible(true);
                        }}
                    >
                        创建设备
                    </Button>
                </Row>
            </Row>
            <Row gutter={[16, 16]}>
                <DeviceData record={showRecord}/>
            </Row>
            <Drawer
                title={isEdit === true ? "编辑设备" : "新建设备"}
                width={500}
                onClose={() => {
                    setDrawerVisible(false);
                    setEditRecord(null);
                }}
                visible={drawerVisible}
                bodyStyle={{paddingBottom: 80}}
            >
                <Form layout="vertical" hideRequiredMark>
                    <Row gutter={16}>
                        <Form.Item label="设备编码（不可修改）">
                            {getFieldDecorator("editCode", {
                                rules: [{required: false}]
                            })(
                                <Input
                                    disabled={isEdit === true}
                                    placeholder={editRecord === null ? "" : editRecord.code}
                                />
                            )}
                        </Form.Item>
                    </Row>
                    <Row gutter={16}>
                        <Form.Item label="设备名称">
                            {getFieldDecorator("editName", {
                                rules: [{required: false}]
                            })(
                                <Input
                                    placeholder={editRecord === null ? "" : editRecord.name}
                                />
                            )}
                        </Form.Item>
                    </Row>
                    <Row gutter={16}>
                        <Form.Item label="创建人">
                            {getFieldDecorator("editCreatorName", {
                                rules: [{required: false}]
                            })(
                                <Input
                                    placeholder={
                                        editRecord === null ? "" : editRecord.creatorName
                                    }
                                />
                            )}
                        </Form.Item>
                    </Row>
                    <Row gutter={16}>
                        <Form.Item label="设备类型">
                            {getFieldDecorator("editType", {
                                rules: [{required: false}]
                            })(
                                <Select
                                    showSearch
                                    optionFilterProp="children"
                                    filterOption={(input, option) =>
                                        option.props.children
                                            .toLowerCase()
                                            .indexOf(input.toLowerCase()) >= 0
                                    }
                                    placeholder={editRecord === null ? 0 : editRecord.type}
                                >
                                    <Option value="0">其他</Option>
                                    <Option value="1">智能穿戴设备</Option>
                                    <Option value="2">智能家居设备</Option>
                                    <Option value="3">智能物流设备</Option>
                                    <Option value="4">智能飞行器设备</Option>
                                    <Option value="5">智能互联网设备</Option>
                                    <Option value="6">智能无线设备</Option>
                                    <Option value="7">智能物联网设备</Option>
                                </Select>
                            )}
                        </Form.Item>
                    </Row>
                    <Row gutter={16}>
                        <Form.Item label="设备描述">
                            {getFieldDecorator("editDescription", {
                                rules: [{required: false}]
                            })(
                                <TextArea
                                    rows={4}
                                    placeholder={
                                        editRecord === null ? "" : editRecord.description
                                    }
                                />
                            )}
                        </Form.Item>
                    </Row>
                </Form>
                <div
                    style={{
                        position: "absolute",
                        right: 0,
                        bottom: 0,
                        width: "100%",
                        borderTop: "1px solid #e9e9e9",
                        padding: "10px 16px",
                        background: "#fff",
                        textAlign: "right"
                    }}
                >
                    <Button
                        onClick={() => {
                            setDrawerVisible(false);
                            setEditRecord(null);
                        }}
                        style={{marginRight: 8}}
                    >
                        取消
                    </Button>
                    <Button
                        onClick={e => {
                            if (isEdit === true) submitEdit(e);
                            else submitCreate(e);
                        }}
                        type="primary"
                    >
                        提交
                    </Button>
                </div>
            </Drawer>
        </Layout>
    );
};

export default withRouter(Form.create()(Device));
