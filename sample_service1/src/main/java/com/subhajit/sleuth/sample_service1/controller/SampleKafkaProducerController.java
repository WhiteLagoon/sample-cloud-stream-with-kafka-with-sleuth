/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.subhajit.sleuth.sample_service1.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.subhajit.sleuth.sample_service1.channel.OutputChannel;
import com.subhajit.sleuth.sample_service1.model.KafkaModel;
import java.util.Arrays;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.binding.BinderAwareChannelResolver;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author sghosh
 */
@RestController
@EnableBinding
public class SampleKafkaProducerController {
    Logger logger = LoggerFactory.getLogger(SampleKafkaProducerController.class);
    
//    private final OutputChannel outputChannel;
    
    @Value("${allowedchannels}")
    String allowedchannels;
    
//    @Autowired
//    public SampleKafkaProducerController(OutputChannel channel) {
//        this.outputChannel=channel;
//    }
    @Autowired
    private BinderAwareChannelResolver resolver;
    
    // This api takes one path variable called channel -- example purpose channel name should be "outputchannel,anotherchannel"
    // allowed channel names are taking from application.yml file
    @GetMapping("/kafka_produce/{channel}")
    public String putMessageToKafka(@PathVariable("channel") String topic) throws JsonProcessingException {
        logger.info("kafka_produce api get called ....");
        
        if (!Arrays.asList(allowedchannels.split(",")).contains(topic)) {
            return "channel does not exists";
        }
        logger.info("Generating model to send to kafka ..");
        
        KafkaModel kafkaModel = new KafkaModel();
        kafkaModel.setId(String.valueOf(Math.round(Math.random()*99999)));
        kafkaModel.setName("Test Information");
        
        ObjectMapper jsonMapper = new ObjectMapper();
        
        logger.info("Putting message to kafka topic name : sampletopic");
        
        MessageBuilder messageBuilder =  MessageBuilder.withPayload(jsonMapper.writeValueAsString(kafkaModel))
                .setHeader("messageSent", "true");
        
        if ("outputchannel".contains(topic)) {
            resolver.resolveDestination("outputchannel").send(messageBuilder.build());
        } else {
//            outputChannel.anotheroutput().send(messageBuilder.build());
        }
        
        logger.info("Message successfully pushed to kafka");
        return "Message successfully pushed to kafka";
    } 
}
