# Spring Cloud Sleuth For Microservice Tracing #

##Introduction##

Spring Cloud Sleuth provides a way to track requests between multiple microservices in a distributed system architecture. For tracing it provides following information :
	1. Trace-Id : Consistent Id between microservices two trace a request.
	2. Span-Id : The basic unit of work. For example, sending an RPC is a new span, as is sending a response to an RPC.
	3. Exportable : true if traces are send to Zipkin Server.


##Pre-requisites##
	1. Gradle
	2. Java 8
	
## How to setup sleuth for simple spring microservice

	1. Only need to add following dependencies in build.gradle file.
	    
		compile('org.springframework.cloud:spring-cloud-starter-sleuth')
		
	2. For sending traceid , spanid to zipkin server add following dependencies to build.gradle file :
		
		compile('org.springframework.cloud:spring-cloud-starter-zipkin')
		
		Goto src/main/resources/application.yml file in sample_service1 or sample_service2
		
## How to setup Kafka communication between two microservices using Spring Cloud Stream

	1. Need to add following dependencies in build.gradle .
	  
		compile "org.apache.kafka:kafka-clients:0.10.2.0"
		compile "org.springframework.kafka:spring-kafka:1.2.2.RELEASE"
		compile "org.springframework.cloud:spring-cloud-stream"
		compile ('org.springframework.cloud:spring-cloud-starter-stream-kafka') 
		
	2. For configure producer in kafka goto src/main/resources/application.yml in sample_service1 and 
	   check for spring.cloud section.
	
	3. For configure consumer in kafka goto src/main/resources/application.yml in sample_service1 and 
	   check for spring.cloud section.
	
	
	Note : If you want to add sleuth for kafka communication between microservices. Instead of adding 	       
		   compile "org.springframework.cloud:spring-cloud-stream" . Please add 		   
		   compile "org.springframework.cloud:spring-cloud-sleuth-stream"
		   
## How to run this sample program.
	
	1. First install kafka and zookeeper in your system.

	2. Build sample-service1 and sample_service2 using 
		-->	gradle clean build 
		--> gradle run
	3. If you want to run Zipkin server then first make spring.zipkin.enabled=true for both sample services and
	   then build and run this project.
	   Zipkin server comes with UI also. By default it will be accessable via http://localhost:9411
	   
	4. sample-service1 have sample two rest-controller :
		
		1. http://localhost:7000/rest-call1 
		
		2. http://localhost:7000/kafka_produce 
		
	5. Look into code for further understanding.
		
	   