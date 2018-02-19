package com.vgrazi.play.nonblocking_io;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class AcceptHandler implements Handler<SelectionKey> {
    private Map<SocketChannel, Queue<ByteBuffer>> pendingData;

    public AcceptHandler(Map<SocketChannel, Queue<ByteBuffer>> pendingData) {

        this.pendingData = pendingData;
    }

    @Override
    public void handle(SelectionKey selectionKey) throws IOException {
        ServerSocketChannel ssc = (ServerSocketChannel) selectionKey.channel();
        SocketChannel sc = ssc.accept();// never null
        System.out.println("Connected to " + sc);
        pendingData.put(sc, new ConcurrentLinkedQueue<>());
        sc.configureBlocking(false);
        sc.register(selectionKey.selector(), SelectionKey.OP_READ);
    }
}
