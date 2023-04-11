package com.photoapp.apigateway.webservice.APIGatewayService.authorization;

import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import reactor.core.publisher.Mono;
@Component
public class GatewayGlobalPreFilter implements GlobalFilter,Ordered {
	final Logger logger=LoggerFactory.getLogger(GatewayGlobalPreFilter.class);

	@Override
	public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
		logger.info("global First preFilter executed");
		String requestPath=exchange.getRequest().getPath().toString();
		
		logger.info("requestUrl Path  ->"+requestPath);
		HttpHeaders httpHeaders=exchange.getRequest().getHeaders();
		Set<String> headerNames=exchange.getRequest().getHeaders().keySet();
		headerNames.forEach(headerName->
		{
			logger.info(headerName+"->"+httpHeaders.getFirst(headerName));
		}
				);
		return chain.filter(exchange);
	}

	@Override
	public int getOrder() {
		// TODO Auto-generated method stub
		return 0;
	}

}
