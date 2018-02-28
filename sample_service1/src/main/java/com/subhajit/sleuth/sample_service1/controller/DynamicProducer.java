/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.subhajit.sleuth.sample_service1.controller;

import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.sleuth.instrument.messaging.TraceChannelInterceptor;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.binding.BindingService;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author sghosh
 */
@RestController
@EnableBinding
public class DynamicProducer {
    
    Logger logger = LoggerFactory.getLogger(DynamicProducer.class);

    @Autowired
    BindingService bindingService;

    @Autowired
    TraceChannelInterceptor channelInterceptor;

    private final Map<String, MessageChannel> topicProducer = new HashMap<>();
    
    @RequestMapping("/kafka_dynamic/{topic}")
    public String putMessage(@PathVariable("topic") String topic) {
        logger.info("Putting message to kafka topic  ---> " + topic);
        Message message = MessageBuilder.withPayload("{\'a\':\'test\'}").setHeader("messageSent", "true").build();
        if (createNewChannelOrTopicIfNotExists(topic)) {
            topicProducer.get(topic).send(message);
            logger.info("Message send successfully");
        }
        return "successfully pushed";
    }
    
     private boolean createNewChannelOrTopicIfNotExists(String topicName) {
        try {
            if (topicProducer.get(topicName) == null) {
                logger.info("Creating new topic/channel " + topicName);
                DirectChannel channel = new DirectChannel();
                channel.addInterceptor(channelInterceptor);
                channel.setComponentName(topicName);
                topicProducer.put(topicName, channel);
                bindingService.bindProducer(channel, topicName);
                logger.info("Topic/Channel created successfully....");
                return true;
            }
        } catch (Exception e) {
            logger.error("Error occured while creating topic/channel :: " + e);
            return false;
        }
        return true;
    }
    
}
