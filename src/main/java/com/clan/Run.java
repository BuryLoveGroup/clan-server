package com.clan;

import com.clan.annotation.InitAnnotation;
import com.clan.handler.RootHandler;
import com.clan.handler.UserHandler;
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
import org.xnio.channels.AcceptingChannel;

import java.io.IOException;
import java.net.Inet4Address;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;

/**
 * Created by robot on 2017/11/5.
 */
public class Run {

    public static void main(final String[] args) throws IOException {

        //注解处理器
        InitAnnotation.init();

        Xnio xnio = Xnio.getInstance();

        XnioWorker worker = xnio.createWorker(OptionMap.builder()
                .set(Options.WORKER_IO_THREADS, 4)
                .set(Options.WORKER_TASK_CORE_THREADS, 40)
                .set(Options.TCP_NODELAY, true)
                .getMap());

        OptionMap socketOptions = OptionMap.builder()
                .set(Options.WORKER_IO_THREADS, 4)
                .set(Options.TCP_NODELAY, true)
                .set(Options.REUSE_ADDRESSES, true)
                .getMap();

        Pool<ByteBuffer> buffers = new ByteBufferSlicePool(BufferAllocator.DIRECT_BYTE_BUFFER_ALLOCATOR, 4096, 8192);

        PathHandler pathHandler = new PathHandler();
        pathHandler.addPrefixPath("/test", new UserHandler());

        HttpOpenListener openListener = new HttpOpenListener(buffers, OptionMap.builder().set(UndertowOptions.BUFFER_PIPELINED_DATA, true).getMap());
        openListener.setRootHandler(new RootHandler(pathHandler));
        ChannelListener<AcceptingChannel<StreamConnection>> acceptListener = ChannelListeners.openListenerAdapter(openListener);
        AcceptingChannel<? extends StreamConnection> server = worker.createStreamConnectionServer(new InetSocketAddress(Inet4Address.getByName("localhost"), 8000), acceptListener, socketOptions);
        server.resumeAccepts();
    }
}
