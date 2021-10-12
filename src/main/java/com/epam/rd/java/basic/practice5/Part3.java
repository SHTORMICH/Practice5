package com.epam.rd.java.basic.practice5;

public class Part3 {
    private int counter;
    private int counter2;

    public Part3(int counter, int counter2) {
        this.counter = counter;
        this.counter2 = counter2;
    }

    public int getCounter() {
        return counter;
    }

    public int getCounter2() {
        return counter2;
    }

    public int incrementCounter() {
        return counter++;
    }

    public int incrementCounter2() {
        return counter2++;
    }

    public static void main(final String[] args) {
        Part3 n = new Part3(0, 0);
        try {
            n.compare();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        n = new Part3(0, 0);
        n.compareSync();

    }

    public void compare() throws InterruptedException {
        Thread threadNoSync = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                System.out.println(getCounter() + " == " + getCounter2() + " : " + (getCounter() + getCounter2()));
                counter++;
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                counter2++;
            }
        });
        threadNoSync.start();
        threadNoSync.join();
    }

    public void compareSync() {
        Thread threadSync = new Thread(new Runnable() {
            @Override
            public synchronized void run() {
                    for (int i = 0; i < 10; i++) {
                        System.out.println(getCounter() + " == " + getCounter2() + " : " + (getCounter() + getCounter2()));
                        incrementCounter();
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        incrementCounter2();
                    }

            }
        });
        threadSync.start();
    }

}
