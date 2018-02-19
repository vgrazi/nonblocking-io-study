package com.vgrazi.play.nonblocking_io;

import java.io.IOException;
import java.nio.channels.SocketChannel;

public class BlockingChannelHandler extends DecoratedHandler<SocketChannel> {

    public BlockingChannelHandler(Handler<SocketChannel> other) {
        super(other);
    }

    @Override
    public void handle(SocketChannel sc) throws IOException {
        while (sc.isConnected())
        {
            super.handle(sc);
        }
    }
}
