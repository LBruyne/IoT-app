package com.hinsliu.iotapp.domain;

public enum DeviceTypeEnum {

    DEFAULT("其他", 0),

    SMART_WEARING("智能穿戴设备", 1),

    SMART_HOUSING("智能家居设备", 2),

    SMART_MATERIAL("智能物流设备", 3),

    AEROBAT("智能飞行器设备", 4),

    SMART_INTERNET("智能互联网设备", 5),

    SMART_OFFLINE("智能无线设备", 6),

    NEW_IOT_DEVICE("新型物联网设备", 7)

    ;

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
