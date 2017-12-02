package com.clan.handler;

import com.clan.exception.AppException;
import com.clan.exception.ParamException;
import com.clan.utils.HttpServerExchangeUtil;
import io.undertow.server.HttpHandler;
import io.undertow.server.HttpServerExchange;
import io.undertow.util.Headers;

import java.util.Map;

/**
 * Created by robot on 2017/11/14.
 */
public class UserHandler implements HttpHandler{


    public void handleRequest(HttpServerExchange exchange) throws Exception {
        //设置统一的responseHeader，采用RestFul风格，应该设置为json
        exchange.getResponseHeaders().put(Headers.CONTENT_TYPE, "text/html; charset=utf-8");
        //全局异常处理
        try {
            Map<String, Object> parameters = HttpServerExchangeUtil.getParameter(exchange);

        } catch (Exception e) {
            if (e instanceof AppException) {
                exchange.getResponseSender().send(e.getMessage());
            } else {
                exchange.setStatusCode(500).getResponseSender().send("SERVER ERROR!");
            }
        }
    }
}