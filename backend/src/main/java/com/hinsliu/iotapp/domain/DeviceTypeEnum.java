package com.hinsliu.iotapp.domain;

public enum DeviceTypeEnum {

    DEFAULT("未选择", 0),

    SMART_WEARING("智能穿戴", 1),

    SMART_HOUSING("智能家居", 2),

    SMART_MATERIAL("智能物流", 3),

    AEROBAT("智能飞行器", 4),

    SMART_INTERNET("智能互联网设备", 5);

    private final String name;

    private final Integer val;

    DeviceTypeEnum(String name, Integer val) {
        this.name = name;
        this.val = val;
    }

    public static DeviceTypeEnum getType(int val) {
        for (DeviceTypeEnum type : DeviceTypeEnum.values()) {
            if (type.getVal() == val) {
                return type;
            }
        }
        return DEFAULT;
    }

    public String getName() {
        return name;
    }

    public Integer getVal() {
        return val;
    }
}
