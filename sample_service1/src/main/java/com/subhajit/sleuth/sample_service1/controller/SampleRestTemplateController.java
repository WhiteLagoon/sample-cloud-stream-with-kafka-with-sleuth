/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.subhajit.sleuth.sample_service1.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
    public SampleRestTemplateController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping("/rest-call1")
    public String makeRestCall() {
        logger.info("Making new rest call to sample microservice 2");
        // some logic before make rest call
        String response = restTemplate.getForObject( "http://localhost:6050/rest-call2", String.class);
        // some logic after make rest call
        logger.info("Got Response from sample microservice 2");
        return response;
    }
}
