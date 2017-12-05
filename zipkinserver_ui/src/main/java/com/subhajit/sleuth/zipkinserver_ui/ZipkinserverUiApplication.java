package com.subhajit.sleuth.zipkinserver_ui;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import zipkin.server.EnableZipkinServer;

@SpringBootApplication
@EnableZipkinServer
public class ZipkinserverUiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ZipkinserverUiApplication.class, args);
	}
}
