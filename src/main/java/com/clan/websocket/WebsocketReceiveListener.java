package com.clan.websocket;

import io.undertow.websockets.core.AbstractReceiveListener;
import io.undertow.websockets.core.BufferedTextMessage;
import io.undertow.websockets.core.WebSocketChannel;
import io.undertow.websockets.core.WebSockets;

/**
 * Created by robot on 2017/11/28.
 */
public class WebsocketReceiveListener extends AbstractReceiveListener{
    protected void onFullTextMessage(WebSocketChannel channel, BufferedTextMessage message) {
                    WebSockets.sendText(String.valueOf(channel.getAttribute("user")), channel, null);
                }
}
