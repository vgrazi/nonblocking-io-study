package com.vgrazi.play.nonblocking_io;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executors;

public class ExecutorSeviceBlockingServer {
    public static void main(String[] args) throws IOException {
        ServerSocket ss = new ServerSocket(8080);
        Handler handler = new ExecutorServiceHandler(
                new PrintingHandler<>(
                        new TransmogrifyHandler()),
                Executors.newCachedThreadPool()
        );

        while (true) {
            Socket s = ss.accept();
            handler.handle(s);
        }
    }

}
