package com.epam.rd.java.basic.practice5;

public class Part1 {

    public static void main(String[] args) {
        ChildThread childOne = new ChildThread();
        childOne.start();

        Thread childRunnable = new Thread(new ChildRunnable());
        childRunnable.start();
    }

}

 class ChildThread extends Thread {

     @Override
     public void run() {
         int counter = 0;
         while (counter != 4) {
             System.out.println("ChildThread");
             counter++;
             try {
                 sleep(500);
             } catch (InterruptedException e) {
                 e.printStackTrace();
             }
         }
     }
 }

 class ChildRunnable implements Runnable {

     @Override
     public void run() {
         int counter = 0;
         while (counter != 4) {
             System.out.println("ChildRunnable");
             counter++;
             try {
                 Thread.sleep(500);
             } catch (InterruptedException e) {
                 e.printStackTrace();
             }
         }
     }
 }