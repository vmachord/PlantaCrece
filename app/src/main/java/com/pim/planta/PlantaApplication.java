package com.pim.planta;

import android.app.Application;

import androidx.work.Configuration;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;
import androidx.work.WorkRequest;

import java.util.concurrent.TimeUnit;

public class PlantaApplication extends Application implements Configuration.Provider {
    @Override
    public void onCreate() {
        super.onCreate();
        // Initialize WorkManager
        WorkManager.initialize(this, getWorkManagerConfiguration());

        // Schedule the NotificationWorker
        WorkRequest notificationWorkRequest =
                new PeriodicWorkRequest.Builder(NotificationWorker.class, 5, TimeUnit.MINUTES)
                        .build();

        WorkManager.getInstance(this).enqueue(notificationWorkRequest);
    }

    @Override
    public Configuration getWorkManagerConfiguration() {
        return new Configuration.Builder()
                .setMinimumLoggingLevel(android.util.Log.DEBUG) // You can change the logging level here
                .build();
    }
}