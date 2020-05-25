package com.tinsley.demo;

import io.micrometer.core.instrument.MeterRegistry;
import io.prometheus.client.CollectorRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication

public class SpringDemoApplication {
	@Autowired
	public MeterRegistry meterRegistry;

	public static void main(String[] args) {

		SpringApplication.run(SpringDemoApplication.class, args);
	}

}
