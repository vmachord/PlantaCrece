package com.pim.planta.db;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DatabaseExecutor {
    private static final ExecutorService executorService = Executors.newSingleThreadExecutor();

    public static void execute(Runnable command) {
        executorService.execute(command);
    }
}
