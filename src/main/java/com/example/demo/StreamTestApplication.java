package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class StreamTestApplication {

	public static void main(String[] args) {
		Runnable eventEmitter = new EventEmitter();
		Thread thread = new Thread(eventEmitter);
		thread.start();

		SpringApplication.run(StreamTestApplication.class, args);
	}

}
