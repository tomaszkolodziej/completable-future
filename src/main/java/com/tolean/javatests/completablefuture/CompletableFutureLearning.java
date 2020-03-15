package com.tolean.javatests.completablefuture;

import java.time.LocalDateTime;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CompletableFutureLearning {

    private ExecutorService pool = Executors.newFixedThreadPool(2);

    public void run() throws ExecutionException, InterruptedException {
        System.out.println("[START] Completable future learning");
        printCurrentThreadName();

        long start = System.currentTimeMillis();

        CompletableFuture<Void> cf1 = ask().thenAccept(val -> System.out.println("val: " + val));
        CompletableFuture<Void> cf2 = secondAsk().thenAccept(val -> System.out.println("val: " + val));

        CompletableFuture.allOf(cf1, cf2).thenAccept(aVoid -> printTime(start));

        pool.shutdown();
    }

    private CompletableFuture<String> ask() throws InterruptedException {
        return CompletableFuture.supplyAsync(() -> {
            printCurrentThreadName();
            int sum = 0;
            for (int idx = 0; idx < 999999; idx++) {
                sum += idx;
            }
            return "9";}, pool);
    }

    private CompletableFuture<Integer> secondAsk() {
        return CompletableFuture.supplyAsync(() -> {
            printCurrentThreadName();
            int sum = 0;
            for (int idx = 0; idx < 9999; idx++) {
                sum += idx;
            }
            return 100;
        }, pool);
    }

    private CompletableFuture<String> callWs(long id) {
        try {
            if (id == 1L) {
                return CompletableFuture.completedFuture("one");
            }

            CompletableFuture<String> async = new CompletableFuture<>();
            async.complete("other");
            return async;
        } catch (Exception e) {
            CompletableFuture<String> async = new CompletableFuture<>();
            async.completeExceptionally(e);
            return async;
        }
    }

    private void printCurrentThreadName() {
        System.out.println("THREAD " + Thread.currentThread().getName());
    }

    private void printTime(long start) {
        System.out.println("ELAPSED TIME IN MS IS " + (System.currentTimeMillis() - start));
    }

}
