package com.clan.handler;

import com.clan.exception.ParamException;
import io.undertow.server.HttpHandler;
import io.undertow.server.HttpServerExchange;

/**
 * Created by robot on 2017/11/14.
 */
public class UserHandler implements HttpHandler{


    public void handleRequest(HttpServerExchange exchange) throws Exception {
        throw new ParamException("参数错误！");
    }
}