package com.tolean.javatests;

import com.tolean.javatests.completablefuture.CompletableFutureLearning;

import java.util.concurrent.ExecutionException;

public class Application {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        new CompletableFutureLearning().run();
    }

}
