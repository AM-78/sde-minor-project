//package com.editor.gateway;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.cloud.gateway.filter.GatewayFilter;
//import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.server.reactive.ServerHttpRequest;
//import org.springframework.stereotype.Component;
//import org.springframework.web.reactive.function.client.WebClient;
//import org.springframework.web.server.ServerWebExchange;
//
//@Component
//public class AuthorizationFilter extends AbstractGatewayFilterFactory<AuthorizationFilter.Config> {
//
//    @Autowired
//    private WebClient.Builder webClientBuilder;
//
//    public AuthorizationFilter() {
//        super(Config.class);
//    }
//
//    @Override
//    public GatewayFilter apply(Config config) {
//        return (exchange, chain) -> {
//        System.out.println("Starting apply method");
//            String authHeader = exchange.getRequest().getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
//            if (authHeader == null || !authHeader.startsWith("Bearer ")) {
//                exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
//                return exchange.getResponse().setComplete();
//            }
//
//            String token = authHeader.substring(7);
//            return webClientBuilder.build()
//                    .post()
//                    .uri("http://localhost:8080/auth/jwt/validate")
//                    .bodyValue(token)
//                    .exchangeToMono(response -> {
//                        System.out.println(response.statusCode().toString());
//                        if (response.statusCode() == HttpStatus.OK) {
//                            return response.bodyToMono(String.class).flatMap(userId -> {
//                                System.out.println("iske neeche user id aani chaiye");
//                                System.out.println(userId);
//                                ServerHttpRequest modifiedRequest = exchange.getRequest().mutate()
//                                        .header("x-user-id", userId)
//                                        .build();
//                                ServerWebExchange modifiedExchange = exchange.mutate()
//                                        .request(modifiedRequest)
//                                        .build();
//                                return chain.filter(modifiedExchange);
//                            });
//                        } else {
//                            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
//                            return exchange.getResponse().setComplete();
//                        }
//                    });
//        };
//    }
//
//    public static class Config {
//        // Configuration properties for the filter can be added here
//    }
//}


package com.editor.gateway;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class AuthorizationGlobalFilter implements GlobalFilter, Ordered {

    @Autowired
    private WebClient.Builder webClientBuilder;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String path = exchange.getRequest().getURI().getPath();
        if (path.startsWith("/reader") || path.startsWith("/writer") || path.startsWith("/document")) {
            String authHeader = exchange.getRequest().getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
            exchange.getRequest().getHeaders().forEach((k, v) -> System.out.println(k + ":" + v));
            System.out.println(authHeader);
            if (authHeader == null || !authHeader.startsWith("Bearer ")) {
                exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
                return exchange.getResponse().setComplete();
            }

            String token = authHeader.substring(7);
            return webClientBuilder.build()
                    .post()
                    .uri("http://localhost:8080/auth/jwt/validate")
                    .bodyValue(token)
                    .exchangeToMono(response -> {
                        if (response.statusCode() == HttpStatus.OK) {
                            return response.bodyToMono(String.class).flatMap(userId -> {
                            System.out.println("user id is "+userId);

                                ServerHttpRequest modifiedRequest = exchange.getRequest().mutate()
                                        .header("x-user-id", userId)
                                        .build();
                                ServerWebExchange modifiedExchange = exchange.mutate()
                                        .request(modifiedRequest)
                                        .build();
                                return chain.filter(modifiedExchange);
                            });
                        } else {
                            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
                            return exchange.getResponse().setComplete();
                        }
                    });
        } else {
            return chain.filter(exchange);
        }
    }

    @Override
    public int getOrder() {
        return -1; // Ensure this filter is applied early
    }
}