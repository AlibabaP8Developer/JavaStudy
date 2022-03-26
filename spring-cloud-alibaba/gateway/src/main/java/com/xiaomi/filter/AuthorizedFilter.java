package com.xiaomi.filter;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

//@Order(-1) // 值越小，越优先执行
//@Component
public class AuthorizedFilter implements GlobalFilter, Ordered {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        // 1 获取请求参数
        ServerHttpRequest request = exchange.getRequest();
        MultiValueMap<String, String> params = request.getQueryParams();
        // 2 获取参数中的authorization参数
        String authorization = params.getFirst("authorization");

        // 3 判断参数值是否等于admin
        if ("admin".equals(authorization)) {
            // 4 是，放行
            Mono<Void> filter = chain.filter(exchange);
            return filter;
        } else {
            // 5 否，拦截
            // (1) 设置状态码
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            // (2) 拦截请求
            return exchange.getResponse().setComplete();
        }
    }

    @Override
    public int getOrder() {
        return -1;
    }
}
