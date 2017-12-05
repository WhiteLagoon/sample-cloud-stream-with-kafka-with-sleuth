/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.subhajit.sleuth.sample_service2.consumer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.subhajit.sleuth.sample_service2.channel.InputChannel;
import com.subhajit.sleuth.sample_service2.model.KafkaModel;
import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.support.GenericMessage;

/**
 *
 * @author sghosh
 */
// Bind a kafka topic to a Input Message Channel
@EnableBinding(InputChannel.class)
public class KafkaConsumer {
    Logger logger = LoggerFactory.getLogger(KafkaConsumer.class);
    
    // Annotation that makes a Message Listener for getting message from kafka topic
    @StreamListener(InputChannel.INPUT)
    public void recieveMessage(GenericMessage message) throws IOException {
        ObjectMapper jsonMapper = new ObjectMapper(); 
        
        logger.info("Got some message from kafka topic :");
        KafkaModel kafkaModel = jsonMapper.readValue((String) message.getPayload(), KafkaModel.class);
        logger.info("Recieved Message from Kafka topic ::: topic : " );
        logger.info("Id ::" + kafkaModel.getId() + "   Name :: " + kafkaModel.getName());
    }
    
    
}
