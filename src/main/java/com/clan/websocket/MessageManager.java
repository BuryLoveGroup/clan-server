package com.clan.websocket;

import io.undertow.websockets.core.WebSocketCallback;
import io.undertow.websockets.core.WebSocketChannel;
import io.undertow.websockets.core.WebSockets;

/**
 * Created by robot on 2017/11/29.
 */
public class MessageManager {

    public void sendMessage(final String message, Long... uids){

        for (Long uid : uids){
            WebSocketChannel webSocketChannel = OnlineManager.get(uid);
            if (webSocketChannel==null){
                UnReadMessageManager.addUnReadMessage(uid, message);
            }else {
                WebSockets.sendText(message, webSocketChannel, new WebSocketCallback<Void>() {
                    public void complete(WebSocketChannel channel, Void context) {
                        //todo 消息持久化
                    }

                    public void onError(WebSocketChannel channel, Void context, Throwable throwable) {
                        //todo logger
                    }
                });
            }
        }
    }

    public void sendMessage(byte[] message, Long... uids){

    }
    public void sendFirendRquest(String message){

    }

}
