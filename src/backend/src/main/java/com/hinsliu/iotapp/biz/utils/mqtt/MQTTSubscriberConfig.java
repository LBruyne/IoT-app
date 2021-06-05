package com.hinsliu.iotapp.biz.utils.mqtt;

import com.hinsliu.iotapp.biz.DeviceMessageManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.core.MessageProducer;
import org.springframework.integration.mqtt.core.MqttPahoClientFactory;
import org.springframework.integration.mqtt.inbound.MqttPahoMessageDrivenChannelAdapter;
import org.springframework.integration.mqtt.support.DefaultPahoMessageConverter;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;

/**
 * @Description: subscriber config.
 * @author: liuxuanming
 * @date: 2021/04/15 11:35 上午
 */
@Configuration
public class MQTTSubscriberConfig {

    private final DeviceMessageManager mqttMessageReceiver;

    public MQTTSubscriberConfig(DeviceMessageManager mqttMessageReceiver) {
        this.mqttMessageReceiver = mqttMessageReceiver;
    }

    /**
     * mqtt消息入站通道，订阅消息后消息进入的通道
     * 可创建多个入站通道，对应多个不同的消息生产者
     */
    @Bean
    public MessageChannel mqttInputChannel() {
        return new DirectChannel();
    }

    /**
     * 对于当前应用来讲，接收的mqtt消息的生产者。
     * 将生产者绑定到mqtt入站通道，即通过入站通道进入的消息为生产者生产的消息。
     * 可创建多个消息生产者，对应多个不同的消息入站通道，同时生产者监听不同的topic
     */
    @Bean
    public MessageProducer channelInBound(MessageChannel mqttInputChannel, MqttPahoClientFactory factory) {
        MqttPahoMessageDrivenChannelAdapter adapter =
                new MqttPahoMessageDrivenChannelAdapter(MQTTConfig.IN_CLIENT_ID, factory, MQTTConfig.TOPIC);
        adapter.setCompletionTimeout(5000);
        adapter.setConverter(new DefaultPahoMessageConverter());
        adapter.setQos(MQTTConfig.QoS);
        adapter.setOutputChannel(mqttInputChannel);
        return adapter;
    }

    /**
     * mqtt入站消息处理工具，对于指定消息入站通道接收到生产者生产的消息后处理消息的工具。
     */
    @Bean
    @ServiceActivator(inputChannel = "mqttInputChannel")
    public MessageHandler mqttMessageHandler() {
        return this.mqttMessageReceiver;
    }
}