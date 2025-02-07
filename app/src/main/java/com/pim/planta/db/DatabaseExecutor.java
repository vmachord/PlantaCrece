package com.pim.planta.db;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DatabaseExecutor {
    private static final ExecutorService executorService = Executors.newSingleThreadExecutor();

    public static void execute(Runnable command) {
        executorService.execute(command);
    }

    public static void executeAndWait(Runnable command) {
        CountDownLatch latch = new CountDownLatch(1);
        executorService.execute(() -> {
            command.run();
            latch.countDown();
        });
        try {
            latch.await();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException("Interrupted while waiting for database operation", e);
        }
    }
}