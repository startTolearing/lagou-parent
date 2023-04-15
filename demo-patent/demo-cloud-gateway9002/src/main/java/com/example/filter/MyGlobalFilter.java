package com.example.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.*;

@Slf4j
@Component
public class MyGlobalFilter implements GlobalFilter, Ordered {

  private static List<String> blackList = new ArrayList<>();
  // 我们可以去数据库中查询黑名单
  static {
    blackList.add("0:0:0:0:0:0:0:1");
  }
  /**
   * 过滤器的具体业务逻辑
   * @param exchange
   * @param chain
   * @return
   */
  @Override
  public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
    // exchage: 代表当前请求的上下文信息
    // 获得request和response
    ServerHttpRequest request = exchange.getRequest();
    ServerHttpResponse response = exchange.getResponse();
    // 获取url
    String hostIp = request.getRemoteAddress().getHostString();
    // 拿着hostIp去黑名单中判断，如果在黑名单中，就拒绝访问
    if (blackList.contains(hostIp)) {
      log.info("你在黑名单中，拒绝访问");
      response.setStatusCode(HttpStatus.UNAUTHORIZED);
      String data = "Request is denied";
      return response.writeWith(Mono.just(response.bufferFactory().wrap(data.getBytes())));
    }
    return chain.filter(exchange);
  }

  /**
   * 过滤器的执行顺序，数值越小，优先级越高
   * @return
   */
  @Override
  public int getOrder() {
    return 0;
  }
}
