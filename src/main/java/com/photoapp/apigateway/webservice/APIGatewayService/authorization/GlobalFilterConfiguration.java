package com.photoapp.apigateway.webservice.APIGatewayService.authorization;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

import reactor.core.publisher.Mono;

@Configuration
public class GlobalFilterConfiguration {

	final Logger logger=LoggerFactory.getLogger(GlobalFilterConfiguration.class);
	@Bean
	@Order(2)
	public GlobalFilter secondPreFilter() {
		return (exchange, chain) -> {
			logger.info("second prefilter executed");
			return chain.filter(exchange).then(Mono.fromRunnable(() -> {
				logger.info("second post filter executed");
			}));

		};
	}
	
	@Bean
	@Order(1)
	public GlobalFilter thirdPreFilter() {
		return (exchange, chain) -> {
			logger.info("third prefilter executed");
			return chain.filter(exchange).then(Mono.fromRunnable(() -> {
				logger.info("third post filter executed");
			}));

		};
	}
}
