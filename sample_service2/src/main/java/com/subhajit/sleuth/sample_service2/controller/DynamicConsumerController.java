/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.subhajit.sleuth.sample_service2.controller;

import com.subhajit.sleuth.sample_service2.model.ConsumerHandler;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.sleuth.instrument.messaging.TraceChannelInterceptor;
import org.springframework.cloud.stream.binding.BindingService;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.stereotype.Component;

/**
 *
 * @author sghosh
 */
@Component
public class DynamicConsumerController {
    @Autowired
    BindingService bindingService;
    @Autowired
    TraceChannelInterceptor channelInterceptor;
    
    @PostConstruct
    public void onInit() {
        DirectChannel newchannel = new DirectChannel();
        ConsumerHandler c=new ConsumerHandler();
        newchannel.subscribe(c);
        newchannel.addInterceptor(channelInterceptor);
        bindingService.bindConsumer(newchannel, "testchannel");
    }
        
}
