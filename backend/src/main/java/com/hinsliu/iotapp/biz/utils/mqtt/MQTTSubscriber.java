package com.hinsliu.iotapp.biz.utils.mqtt;

import com.hinsliu.iotapp.biz.utils.redis.RedisUtil;
import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Timestamp;

/**
 * @Description: Subscriber.
 * @author: liuxuanming
 * @date: 2021/04/02 4:45 下午
 */
public class MQTTSubscriber implements MqttCallback {
    public static Logger logger = LoggerFactory.getLogger(MQTTSubscriber.class);

    final private String clientId = "mqtt_server_sub";

    private String serverUrl;

    private static MQTTSubscriber mqttSubscriber;

    private static MqttClient mqttClient;

    private static MqttConnectOptions connectOptions;

    private static MemoryPersistence memoryPersistence;

    public MQTTSubscriber() {
        this.config();
    }

    protected void finalize() throws Throwable {
        try {
            mqttClient.disconnect();
        }
        catch(MqttException e) {
            logger.error(e.getMessage(), e);
        }
    }

    synchronized public static MQTTSubscriber getInstance() {
        if(mqttSubscriber == null) {
            mqttSubscriber = new MQTTSubscriber();
        }
        return mqttSubscriber;
    }

    private void config() {
        this.serverUrl = MQTTConfig.SERVER;
        this.memoryPersistence = new MemoryPersistence();
        this.connectOptions = new MqttConnectOptions();

        try {
            this.mqttClient = new MqttClient(serverUrl, clientId, memoryPersistence);
            this.connectOptions.setCleanSession(true);
            this.mqttClient.connect(this.connectOptions);
            logger.info("MQTT connection created.");
        } catch (MqttException e) {
            logger.warn(e.getMessage(), e);
        }
    }

    public void subscribeMessage(String topic) {
        try {
            this.mqttClient.subscribe(topic);
        } catch (MqttException e) {
            logger.warn(e.getMessage(), e);
        }
    }

    public void disconnect() {
        try {
            this.mqttClient.disconnect();
        } catch (MqttException me) {
            logger.error("ERROR", me);
        }
    }

    @Override
    public void connectionLost(Throwable throwable) {
        logger.warn("Connection Lost");
    }

    @Override
    public void messageArrived(String topic, MqttMessage message) throws Exception {
        // Called when a message arrives from the server that matches any
        // subscription made by the client
        String time = new Timestamp(System.currentTimeMillis()).toString();
        System.out.println();
        System.out.println("***********************************************************************");
        System.out.println("Message Arrived at Time: " + time + "  Topic: " + topic + "  Message: "
                + new String(message.getPayload()));
        System.out.println("***********************************************************************");
        System.out.println();
    }

    @Override
    public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {
        // Leave it blank for subscriber
    }
}