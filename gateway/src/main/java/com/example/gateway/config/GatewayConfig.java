package com.example.gateway.config;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

@Configuration
@RequiredArgsConstructor
public class GatewayConfig {


        private final AuthenticationFilter authenticationFilter;

        @Bean
        public RouteLocator routes(RouteLocatorBuilder builder) {
            return builder.routes()

                    .route("auth-service", r -> r.path("/api/auth/**")
                            .filters(gatewayFilterSpec -> gatewayFilterSpec.filter(authenticationFilter))
                            .uri("lb://auth-service"))
                    .route("customer-service", r -> r.path("/api/v1/customers")
                            .filters(gatewayFilterSpec -> gatewayFilterSpec.filter(authenticationFilter))
                            .uri("lb://customer-service"))
                    .route("account-service", r -> r.path("/api/v1/account")
                            .filters(gatewayFilterSpec -> gatewayFilterSpec.filter(authenticationFilter))
                            .uri("lb://account-service"))
                    .route("transfer-service", r -> r.path("/api/v1/transfers/**")
                            .filters(gatewayFilterSpec -> gatewayFilterSpec.filter(authenticationFilter))
                            .uri("lb://transfer-service"))
                    .build();
        }
    @Bean
    public CorsWebFilter corsWebFilter() {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOriginPattern("*");
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);

        return new CorsWebFilter(source);
    }
}