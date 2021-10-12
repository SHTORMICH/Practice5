package com.epam.rd.java.basic.practice5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Spam {

    private Thread[] threads;
    private String[] messages;
    private int[] delays;

    public Spam(final String[] messages, final int[] delays) {
        this.messages = messages;
        this.delays = delays;
    }

    public static void main(final String[] args) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Worker worker = new Worker();
        Thread thread = new Thread(worker);
        thread.start();
        while (true) {
            try {
                if (reader.readLine().isEmpty()) {
                    thread.interrupt();
                    break;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }

    public void start() {

    }

    public void stop() {

    }

    private static class Worker extends Thread {


        @Override
        public void run() {
            Spam spam = new Spam(new String[] {"a", "bb", "ccc", "dddd"}, new int[] {500, 1000, 1500, 2000});
            int i = 0;
            while (!Thread.currentThread().isInterrupted() && i < spam.delays.length) {
                try {
                    Thread.sleep(spam.delays[i]);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(spam.messages[i]);
                i++;
            }
        }
    }
}
// new String[] {"a", "bb", "ccc", "dddd"}
//new int[] {500, 1000, 1500, 2000}