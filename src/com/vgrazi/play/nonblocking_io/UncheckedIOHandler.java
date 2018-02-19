package com.vgrazi.play.nonblocking_io;

import java.io.IOException;
import java.io.UncheckedIOException;

public class UncheckedIOHandler<S> extends DecoratedHandler<S> {

    public UncheckedIOHandler(Handler<S> other) {
        super(other);
    }

    @Override
    public void handle(S s) {
        try {
            super.handle(s);
        }
        catch (IOException e)
        {
            throw new UncheckedIOException(e);
        }
    }
}
