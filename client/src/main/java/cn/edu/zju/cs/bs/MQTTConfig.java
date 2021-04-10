package cn.edu.zju.cs.bs;

/**
 * @Description: config for MQTT.
 * @author: liuxuanming
 * @date: 2021/04/02 6:12 下午
 */
public class MQTTConfig {
    public final static String SERVER = "tcp://localhost:1883";

    public final static String USER_NAME = "lxm";

    public final static String USER_PASSWORD = "lxm";

    public final static Integer DEVICE_NUM = 5;

    public final static String TOPIC = "testapp";

    public final static String DEVICE_ID_PREFIX = "device";

    public final static Integer QoS = 2;
}
