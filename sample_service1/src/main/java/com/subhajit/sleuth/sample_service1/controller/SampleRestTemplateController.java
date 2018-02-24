/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.subhajit.sleuth.sample_service1.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.sleuth.SpanReporter;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author sghosh
 */
@RestController
public class SampleRestTemplateController {
    Logger logger = LoggerFactory.getLogger(SampleRestTemplateController.class);

    private final RestTemplate restTemplate;
    
    
    @Autowired
    SpanReporter reporter;
    
    @Autowired
    public SampleRestTemplateController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping("/rest-call1")
    public String makeRestCall() {
//        logger.info("Making new rest call to sample microservice 2");
//        logger.info("Making new rest call to sample microservice 21");
//        logger.info("Making new rest call to sample microservice 22");
//        logger.info("Making new rest call to sample microservice 23");
//        logger.info("Making new rest call to sample microservice 24");
//        logger.info("Making new rest call to sample microservice 25");
//        logger.info("Making new rest call to sample microservice 26");
//        logger.info("Making new rest call to sample microservice 27");
//        logger.info("Making new rest call to sample microservice 28");
//        logger.info("Making new rest call to sample microservice 29");
//        logger.info("Making new rest call to sample microservice 30");
//        logger.info("Making new rest call to sample microservice 31");
////        System.out.println(reporter.getClass().getName());
//        
//        
//        
//        // some logic before make rest call
        String response = restTemplate.getForObject( "http://localhost:6070/rest-call2", String.class);
//        // some logic after make rest call
        //logger.info("Got Response from sample microservice 2");
        return "";
    }
}
