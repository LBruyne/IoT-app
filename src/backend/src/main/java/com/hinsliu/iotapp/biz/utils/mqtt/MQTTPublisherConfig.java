package com.hinsliu.iotapp.biz.utils.mqtt;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.mqtt.core.MqttPahoClientFactory;
import org.springframework.integration.mqtt.outbound.MqttPahoMessageHandler;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;

/**
 * @Description: publisher config.
 * @author: liuxuanming
 * @date: 2021/04/15 7:55 下午
 */
@Configuration
public class MQTTPublisherConfig {

    /**
     * mqtt消息出站通道，用于发送出站消息
     */
    @Bean
    public MessageChannel mqttOutputChannel() {
        return new DirectChannel();
    }

    /**
     * mqtt消息出站通道默认配置，用于向外发出mqtt消息，当前案例中发到了同一个mqtt服务
     */
    @Bean
    public MessageHandler mqttOutbound(MqttPahoClientFactory factory) {
        MqttPahoMessageHandler messageHandler = new MqttPahoMessageHandler(MQTTConfig.OUT_CLIENT_ID, factory);
        messageHandler.setAsync(true);
        messageHandler.setDefaultQos(2);
        messageHandler.setDefaultTopic(MQTTConfig.TOPIC);
        return messageHandler;
    }
}
