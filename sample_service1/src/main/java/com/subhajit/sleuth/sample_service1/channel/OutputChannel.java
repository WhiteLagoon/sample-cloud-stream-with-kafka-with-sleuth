/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.subhajit.sleuth.sample_service1.channel;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

/**
 *
 * @author sghosh
 */
public interface OutputChannel {
    public static final String OUTPUT = "outputchannel";

    @Output(value = OUTPUT)
    public MessageChannel output();
    
    @Output(value = "anotherchannel")
    public MessageChannel anotheroutput();
}
