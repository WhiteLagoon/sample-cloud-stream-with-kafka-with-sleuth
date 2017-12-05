/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.subhajit.sleuth.sample_service2.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.MessagingException;

/**
 *
 * @author sghosh
 */
public class ConsumerHandler implements MessageHandler {

    Logger logger = LoggerFactory.getLogger(ConsumerHandler.class);

    @Override
    public void handleMessage(Message<?> message) throws MessagingException {
        try {
            ObjectMapper jsonMapper = new ObjectMapper();
            logger.info("Message recieved : " + message.getHeaders());
            KafkaModel kafkaModel;
            kafkaModel = jsonMapper.readValue((String) message.getPayload(), KafkaModel.class);
            logger.info("Id ::" + kafkaModel.getId() + "   Name :: " + kafkaModel.getName());
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }

}
