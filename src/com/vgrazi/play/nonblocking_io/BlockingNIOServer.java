package com.vgrazi.play.nonblocking_io;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.concurrent.Executors;

public class BlockingNIOServer {
    public static void main(String[] args) throws IOException {
        ServerSocketChannel ssc = ServerSocketChannel.open();
        ssc.bind(new InetSocketAddress(8080));
        Handler<SocketChannel> handler =
                new ExecutorServiceHandler<>(
                        new PrintingHandler<>(
                                new BlockingChannelHandler(
                                        new TransmogrifyChannelHandler()
                                )
                        ),
                        Executors.newFixedThreadPool(10),
                        (t, e) -> System.out.println("Exception in " + t + "error " + e)
                );

        while (true) {
            SocketChannel s = ssc.accept();
            handler.handle(s);
        }
    }

}
