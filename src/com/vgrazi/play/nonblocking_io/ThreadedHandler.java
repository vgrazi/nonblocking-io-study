package com.vgrazi.play.nonblocking_io;

public class ThreadedHandler<S> extends UncheckedIOHandler<S> {

    public ThreadedHandler(Handler<S> other) {
        super(other);
    }

    @Override
    public void handle(S s) {
        new Thread(()-> super.handle(s)).start();
    }
}
