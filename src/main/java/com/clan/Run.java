package com.clan;

import com.clan.annotation.InitAnnotation;
import com.clan.handler.RootHandler;
import com.clan.handler.UserHandler;
<<<<<<< HEAD
import com.clan.redis.Redis;
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
=======
import io.undertow.UndertowOptions;
import io.undertow.server.handlers.PathHandler;
import io.undertow.server.protocol.http.HttpOpenListener;
import org.xnio.BufferAllocator;
import org.xnio.ByteBufferSlicePool;
import org.xnio.ChannelListener;
import org.xnio.ChannelListeners;
import org.xnio.OptionMap;
import org.xnio.Options;
import org.xnio.Pool;
import org.xnio.StreamConnection;
import org.xnio.Xnio;
import org.xnio.XnioWorker;
>>>>>>> master
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
        pathHandler.addPrefixPath("/test", new UserHandler());
        pathHandler.addPrefixPath("/chat", websocket(new WebSocketConnectionCallback() {
            int i = 0;
            public void onConnect(final WebSocketHttpExchange exchange, WebSocketChannel channel) {
//                String chatToken = exchange.getRequestHeader("chatToken");
//                if (StringUtils.isBlank(chatToken)) {
//                    try {
//                        channel.close();
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                } else {
//                    Jedis redisClient = Redis.getJedis();
//                    String uid = redisClient.get(chatToken);
//                    if (StringUtils.isBlank(uid)) {
//                        WebSockets.sendText("end", channel, null);
//                    }else {
//                        channel.setAttribute("user",uid);
//                    }
//                }

                channel.getReceiveSetter().set(new AbstractReceiveListener() {
                    protected void onFullTextMessage(WebSocketChannel channel, BufferedTextMessage message) {
                        //检验客户端
                        WebSockets.sendText(String.valueOf(channel.getAttribute("user")), channel, null);
                    }
                });
                channel.resumeReceives();
            }
        }));

<<<<<<< HEAD
        Undertow server = Undertow.builder()
                .addHttpListener(8000, "0.0.0.0")
                .setHandler(new RootHandler(pathHandler)).build();
        server.start();
=======
        PathHandler pathHandler = new PathHandler();
        pathHandler.addPrefixPath("/test", new UserHandler());

        HttpOpenListener openListener = new HttpOpenListener(buffers, OptionMap.builder().set(UndertowOptions.BUFFER_PIPELINED_DATA, true).getMap());
        openListener.setRootHandler(new RootHandler(pathHandler));
        ChannelListener<AcceptingChannel<StreamConnection>> acceptListener = ChannelListeners.openListenerAdapter(openListener);
        AcceptingChannel<? extends StreamConnection> server = worker.createStreamConnectionServer(new InetSocketAddress(Inet4Address.getByName("localhost"), 8000), acceptListener, socketOptions);
        server.resumeAccepts();
>>>>>>> master
    }
}
