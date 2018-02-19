package com.vgrazi.play.nonblocking_io;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class MultiThreadedBlockingServer {
    public static void main(String[] args) throws IOException {
        UncheckedIOHandler handler = new ThreadedHandler(
                new PrintingHandler<>(
                        new TransmogrifyHandler())
        );

        ServerSocket ss = new ServerSocket(8080);
        while (true) {
            Socket s = ss.accept();
            handler.handle(s);
        }
    }

}
