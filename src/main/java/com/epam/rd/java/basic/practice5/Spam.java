package com.epam.rd.java.basic.practice5;

import java.util.Scanner;

public class Spam {

    private Thread[] threads;
    private String[] messages;
    private int[] times;
    private static boolean running = true;

    public Spam(final String[] messages, final int[] delays) {
        this.messages = messages;
        this.times = delays;
    }

    public static void main(final String[] args) {
        Scanner input = new Scanner(System.in);

        while (running) {
            Worker worker = new Worker();
            worker.run();
        }
    }

    public void start() {

    }

    public void stop() {
        running = false;
    }

    private static class Worker extends Thread {
        String[] messages = new String[] {"a", "bb", "ccc", "dddd"};
        int[] delays = new int[] {500, 1000, 1500, 2000};
        Spam spam = new Spam(messages, delays);
        @Override
        public void run() {
            for (int i = 0; i < messages.length; i++) {
                System.out.println(messages[i]);
                try {
                    sleep(delays[i]);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
// new String[] {"a", "bb", "ccc", "dddd"}
//new int[] {500, 1000, 1500, 2000}