package com.icesi.gym_api_gateway.security;


import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;

@Configuration
public class GatewayConfig {

    @Bean
    public GlobalFilter customGlobalFilter() {
        return (exchange, chain) -> {
            ServerHttpRequest request = exchange.getRequest();
            String authHeader =
                    request.getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
            return chain.filter(exchange.mutate()
                    .request(request.mutate()
                            .header(HttpHeaders.AUTHORIZATION, authHeader)
                            .build())
                    .build());
        };
    }

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("miembros-service", r -> r.path("/api/miembros/**")
                        .uri("http://localhost:8082"))
                .build();
    }





}
