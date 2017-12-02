package com.clan.utils;

import io.undertow.server.HttpServerExchange;

import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

/**
 * description:HttpServerExchangeUtil类的公共方法
 * author: magic
 * date: 2017/12/2 下午3:28
 */
public class HttpServerExchangeUtil {


    //从HttpServerExchange类获取到参数值
    public static Map getParameter(HttpServerExchange exchange){
        Map<String, Deque<String>> map = exchange.getQueryParameters();
        Map<String, Object> parameters = new HashMap();
        for (String key:map.keySet()){
            Deque<String> deque = map.get(key);
            parameters.put(key, deque.getFirst());
        }

        return parameters;
    }
}
