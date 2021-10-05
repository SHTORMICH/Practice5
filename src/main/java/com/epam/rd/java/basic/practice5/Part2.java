package com.epam.rd.java.basic.practice5;

import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;

public class Part2 {

    public static void main(final String[] args) throws InterruptedException {
        System.setIn(new ByteArrayInputStream(
                "\n".getBytes(StandardCharsets.UTF_8)));
        Thread t = new Thread() {
            @Override
            public void run() {
                Spam.main(args);
            }
        };
        t.start();
        t.join();

    }

}
