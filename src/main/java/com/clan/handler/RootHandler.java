package com.clan.handler;

import com.clan.exception.AppException;
import io.undertow.server.DefaultResponseListener;
import io.undertow.server.HttpHandler;
import io.undertow.server.HttpServerExchange;
import io.undertow.util.Headers;

public class RootHandler implements HttpHandler {

    private HttpHandler handler;

    public RootHandler(HttpHandler handler) {
        this.handler = handler;
    }

    public void handleRequest(HttpServerExchange exchange) throws Exception {
        //设置默认的rsponse,即一个请求没有send数据时，调用此方法。此处生成404错误。
        exchange.addDefaultResponseListener(new DefaultResponseListener() {
            public boolean handleDefaultResponse(HttpServerExchange exchange) {
                if (!exchange.isResponseChannelAvailable()) {
                    return false;
                } else {
                    exchange.setStatusCode(404);
                    exchange.getResponseSender().send("Please Check Your Url!");
                    return true;
                }
            }
        });

        //设置统一的responseHeader，采用RestFul风格，应该设置为json
        exchange.getResponseHeaders().put(Headers.CONTENT_TYPE, "text/html; charset=utf-8");
        //全局异常处理
        try {
            handler.handleRequest(exchange);
        } catch (Exception e) {
            if (e instanceof AppException) {
                exchange.getResponseSender().send(e.getMessage());
            } else {
                exchange.setStatusCode(500).getResponseSender().send("SERVER ERROR!");
            }
        }
    }
}
