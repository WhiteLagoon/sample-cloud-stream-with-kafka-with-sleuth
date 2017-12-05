/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.subhajit.sleuth.sample_service2.channel;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;


/**
 *
 * @author sghosh
 */
public interface InputChannel {
   public static final String INPUT = "inputchannel";

    @Input(value = "inputchannel")
    public SubscribableChannel input();
}
