package com.vgrazi.play.nonblocking_io;

import java.nio.ByteBuffer;

public class Util {
    public static int transmogrify(int data) {
        // data ^ ' ' flips the 2 bit, so if it is lower case, makes it upper, and vice versa
        return Character.isLetter(data)? data ^ ' ':data;
    }

    public static void transmogrify(ByteBuffer buf) {
        System.out.println("Transmogrification done by " + Thread.currentThread());
        // change limit to end pos and pos to 0
        buf.flip();
        for(int i = 0; i < buf.limit(); i++) {
            buf.put(i, (byte) transmogrify(buf.get(i)));
        }
    }
}
