package com.vgrazi.play.nonblocking_io;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class TransmogrifyHandler implements Handler<Socket> {
    public void handle(Socket s) throws IOException {
        try (s; InputStream in = s.getInputStream(); OutputStream out = s.getOutputStream();) {
            int data;
            while ((data = in.read()) != -1) {
                if(data=='%') throw new IOException("oopsie");
                out.write(Util.transmogrify(data));
            }
        }
    }

}
