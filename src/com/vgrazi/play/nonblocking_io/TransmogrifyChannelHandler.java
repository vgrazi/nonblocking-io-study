package com.vgrazi.play.nonblocking_io;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class TransmogrifyChannelHandler implements Handler<SocketChannel> {
    public void handle(SocketChannel sc) throws IOException {
        ByteBuffer buf = ByteBuffer.allocate(80);
        int read = sc.read(buf);
        if (read == -1) {
            sc.close();
        }
        else if (read > 0) {
            Util.transmogrify(buf);
            while(buf.hasRemaining()) {
                sc.write(buf);
            }
        }
    }

}
