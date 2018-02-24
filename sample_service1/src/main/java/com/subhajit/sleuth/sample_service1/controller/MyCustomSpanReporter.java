/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.subhajit.sleuth.sample_service1.controller;

import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.sleuth.Span;
import org.springframework.cloud.sleuth.SpanReporter;
import org.springframework.cloud.sleuth.zipkin.ZipkinSpanReporter;
import zipkin.Constants;

/**
 *
 * @author sghosh
 */
public class MyCustomSpanReporter implements ZipkinSpanReporter {
    Logger logger = LoggerFactory.getLogger(MyCustomSpanReporter.class);
    @Override
    public void report(zipkin.Span span) {
        
       logger.info(span.toString());
    }

    

}
