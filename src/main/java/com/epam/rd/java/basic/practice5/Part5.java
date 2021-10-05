package com.epam.rd.java.basic.practice5;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class Part5 {

    public static void main(final String[] args) throws InterruptedException, IOException, ExecutionException {

        ExecutorService service = Executors.newFixedThreadPool(9);
        File file = new File("part5.txt");
        RandomAccessFile r = new RandomAccessFile(file, "rw");
        List<Worker> tasks = new ArrayList<>();
        Worker result = new Worker();
        for (int i = 0; i < 10; i++) {
            tasks.add(result);
        }
        List<Future<String>> text = service.invokeAll(tasks);
        for (Future<String> line : text) {
            String str = line.get() + "\n";
            r.write(str.getBytes("UTF-8"));
        }
        service.shutdown();
    }

}

class Worker implements Callable<String> {

    private static int writeNub = 0;

    @Override
    public synchronized String call() {

        StringBuilder line = new StringBuilder();
        for (int i = 0; i < 20; i++) {
            line.append(writeNub);
        }
        writeNub++;

        return line.toString();
    }
}
