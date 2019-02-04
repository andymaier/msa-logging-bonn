package com.predic8.logging;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Metrics;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class LogJsonAutoConfiguration {

	final MeterRegistry registry;

	public LogJsonAutoConfiguration(MeterRegistry registry) {
		this.registry = registry;
	}

	@Bean
	public LogJsonHandlerInterceptor logJsonHandlerInterceptor() {
		return new LogJsonHandlerInterceptor( registry);
	}

	@Configuration
	static class WebConfig extends WebMvcConfigurerAdapter {

		private final LogJsonHandlerInterceptor logJsonHandlerInterceptor;

		WebConfig(LogJsonHandlerInterceptor logJsonHandlerInterceptor) {
			this.logJsonHandlerInterceptor = logJsonHandlerInterceptor;
		}

		@Override
		public void addInterceptors(InterceptorRegistry registry) {
			registry.addInterceptor(logJsonHandlerInterceptor);
		}
	}
}