package com.vgrazi.play.nonblocking_io;

import java.io.IOException;

public interface Handler<S> {
    void handle(S s) throws IOException;
}
