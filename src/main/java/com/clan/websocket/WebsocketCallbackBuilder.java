package com.clan.websocket;

import com.clan.redis.Redis;
import io.undertow.websockets.WebSocketConnectionCallback;
import io.undertow.websockets.core.AbstractReceiveListener;
import io.undertow.websockets.core.BufferedTextMessage;
import io.undertow.websockets.core.WebSocketChannel;
import io.undertow.websockets.core.WebSockets;
import io.undertow.websockets.spi.WebSocketHttpExchange;
import org.apache.commons.lang3.StringUtils;
import redis.clients.jedis.Jedis;

import java.io.IOException;

/**
 * Created by robot on 2017/11/28.
 */
public class WebsocketCallbackBuilder {

    private static WebSocketConnectionCallback webSocketConnectionCallback = new WebSocketConnectionCallback() {

        public void onConnect(WebSocketHttpExchange exchange, WebSocketChannel channel) {

            String chatToken = exchange.getRequestHeader("chatToken");
            if (StringUtils.isBlank(chatToken)) {
                try {
                    channel.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                Jedis redisClient = Redis.getJedis();
                String uid = redisClient.get(chatToken);
                if (StringUtils.isBlank(uid)) {
                    WebSockets.sendText("end", channel, null);
                } else {
                    channel.setAttribute("user", uid);
                }
            }
            //listener for message
            channel.getReceiveSetter().set(new WebsocketReceiveListener());
            channel.resumeReceives();
        }
    };

    public static WebSocketConnectionCallback builder() {
        return webSocketConnectionCallback;
    }
}
