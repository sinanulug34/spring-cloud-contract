package com.example.smokeapiproducermaven;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;

@SpringBootApplication
// we will be sending out a message
@EnableBinding(Source.class)
public class SmokeApiProducerMavenApplication {

	public static void main(String[] args) {
		SpringApplication.run(SmokeApiProducerMavenApplication.class, args);
	}

}
