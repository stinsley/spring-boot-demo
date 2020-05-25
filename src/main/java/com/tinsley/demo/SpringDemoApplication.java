package com.tinsley.demo;

import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.metrics.export.prometheus.EnablePrometheusMetrics;

@SpringBootApplication
public class SpringDemoApplication {
	@Autowired
	private MeterRegistry meterRegistry;

	public static void main(String[] args) {

		SpringApplication.run(SpringDemoApplication.class, args);
	}

}
