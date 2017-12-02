package com.clan;

import com.clan.annotation.InitAnnotation;
import com.clan.handler.RootHandler;
import com.clan.handler.UserHandler;
import com.clan.redis.Redis;
import com.clan.websocket.WebsocketCallbackBuilder;
import com.sun.org.apache.regexp.internal.RE;
import io.undertow.Handlers;
import io.undertow.Undertow;
import io.undertow.UndertowOptions;
import io.undertow.connector.ByteBufferPool;
import io.undertow.server.DefaultByteBufferPool;
import io.undertow.server.HttpHandler;
import io.undertow.server.HttpServerExchange;
import io.undertow.server.handlers.PathHandler;
import io.undertow.server.protocol.http.HttpOpenListener;
import io.undertow.util.Headers;
import io.undertow.websockets.WebSocketConnectionCallback;
import io.undertow.websockets.core.AbstractReceiveListener;
import io.undertow.websockets.core.BufferedTextMessage;
import io.undertow.websockets.core.WebSocketChannel;
import io.undertow.websockets.core.WebSockets;
import io.undertow.websockets.spi.WebSocketHttpExchange;
import org.apache.commons.lang3.StringUtils;
import org.xnio.*;
import org.xnio.channels.AcceptingChannel;
import redis.clients.jedis.Jedis;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Inet4Address;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.Channel;

import static io.undertow.Handlers.websocket;

/**
 * Created by robot on 2017/11/5.
 */
public class Run {

    public static void main(final String[] args) throws IOException {

        //注解处理器
//        InitAnnotation.init();

        PathHandler pathHandler = new PathHandler();
        pathHandler.addPrefixPath("/user", new UserHandler());
        pathHandler.addPrefixPath("/chat", websocket(WebsocketCallbackBuilder.builder()));

        Undertow server = Undertow.builder()
                .addHttpListener(8000, "0.0.0.0")
                .setHandler(new RootHandler(pathHandler)).build();
        server.start();
    }
}
