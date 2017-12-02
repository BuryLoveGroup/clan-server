package com.clan.websocket;

import io.undertow.websockets.core.WebSocketChannel;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by robot on 2017/11/28.
 */
public class OnlineManager {

    private static ConcurrentHashMap<Long, WebSocketChannel> onlines = new ConcurrentHashMap();

    public static void add(Long uid, WebSocketChannel webSocketChannel){
        onlines.put(uid, webSocketChannel);
    }

    public static WebSocketChannel get(Long uid){
        return onlines.get(uid);
    }

}
