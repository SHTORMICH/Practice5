package com.epam.rd.java.basic.practice5;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class Part4 {
    private static final int N = 10;
    private static final int M = 20;

    public static void main(final String[] args) {
        int[][] matrix = getRandomMatrix(N, M);
        writeToFile("part4.txt", matrix);
        long startTime = System.currentTimeMillis();
        int max = findMax(matrix);
        long endTime = System.currentTimeMillis() - startTime;

        System.out.println(max + "\n" + endTime);

        long startTimeMul = System.currentTimeMillis();
        int maxMul = multiThreadMax(matrix);
        long endTimeMul = System.currentTimeMillis() - startTimeMul;

        System.out.println(maxMul + "\n" + endTimeMul);

    }

    public static int multiThreadMax(int[][] matrix) {
        int max = 0;
        ExecutorService executorService = Executors.newFixedThreadPool(N);
        List<Callable<Integer>> maxInLine = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            maxInLine.add(getLocalMax(matrix[i]));
        }

        List<Future<Integer>> futures = null;
        try {
            futures = executorService.invokeAll(maxInLine);
            for (Future<Integer> future : futures) {
                int result = 0;
                result = Integer.parseInt(future.get().toString());
                if (max < result) {
                    max = result;
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        executorService.shutdown();
        return max;
    }

    public static Callable<Integer> getLocalMax(int[] matrix) {
        return () -> {
            int localMax = 0;
            for (int j = 0; j < M; j++) {
                if (localMax < matrix[j]) {
                    localMax = matrix[j];
                }
            }
            return localMax;
        };
    }

    public static int findMax(int[][] matrix) {
        int maxNum = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (maxNum < matrix[i][j]) {
                    maxNum = matrix[i][j];
                }
            }
        }
        return maxNum;
    }

    public static int[][] getRandomMatrix(int N, int M) {
        int[][] randomNubs = new int[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                randomNubs[i][j] = (int) (Math.random() * 1000 + 1);
            }
        }
        return randomNubs;
    }

    public static void writeToFile(String path, int[][] randomNubs) {
        File numbers = new File(path);
        try (PrintWriter file = new PrintWriter(numbers)){
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    file.printf("%s ", randomNubs[i][j]);
                }
                file.println();
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static String reader(String path) {
        File file = new File(path);
        StringBuilder builder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String value = reader.readLine();
            while (value != null) {
                builder.append(value).append(System.lineSeparator());
                value = reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return builder.toString();
    }
}
