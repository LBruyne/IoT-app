package com.hinsliu.iotapp.biz.utils.mqtt;

import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.mqtt.core.DefaultMqttPahoClientFactory;
import org.springframework.integration.mqtt.core.MqttPahoClientFactory;

/**
 * @Description: config for Mqtt.
 * @author: liuxuanming
 * @date: 2021/04/01 2:38 下午
 */
@Configuration
public class MQTTConfig {

    public final static String SERVER = "tcp://localhost:1883";

    public final static Integer QoS = 2;

    public final static Boolean HAS_SSL = false;

    public final static String USER_NAME = "lxm";

    public final static String USER_PASSWORD = "lxm";

    public final static String TOPIC = "/iotapp/app";

    public final static String IN_CLIENT_ID = "iot-backend-subscriber";

    public final static String OUT_CLIENT_ID = "iot-backend-pubisher";

    /**
     * 构造一个默认的mqtt客户端操作bean
     */
    @Bean
    public MqttPahoClientFactory factory() {
        DefaultMqttPahoClientFactory defaultMqttPahoClientFactory = new DefaultMqttPahoClientFactory();
        MqttConnectOptions options = new MqttConnectOptions();
        options.setUserName(USER_NAME);
        options.setPassword(USER_PASSWORD.toCharArray());
        options.setServerURIs(new String[]{SERVER});
        defaultMqttPahoClientFactory.setConnectionOptions(options);
        return defaultMqttPahoClientFactory;
    }
}
