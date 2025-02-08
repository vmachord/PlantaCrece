package com.pim.planta;

import android.app.AppOpsManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.usage.UsageEvents;
import android.app.usage.UsageStatsManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NotificationWorker extends Worker {

    private static final String CHANNEL_ID = "notification_channel";
    private static final int NOTIFICATION_ID = 1;
    private static final String PREFS_NAME = "app_prefs";
    private static final String TOTAL_USAGE_TIME_KEY = "total_usage_time";
    private static final String FIFTEEN_MINUTES_NOTIFICATION_SENT_KEY = "fifteen_minutes_notification_sent";
    private static final String TWO_HOURS_NOTIFICATION_SENT_KEY = "two_hours_notification_sent";
    private static final long FIFTEEN_MINUTES_IN_MILLIS = 15 * 60 * 1000; // 15 minutes
    private static final long TWO_HOURS_IN_MILLIS = 2 * 60 * 60 * 1000; // 2 hours
    private static final List<String> SOCIAL_MEDIA_PACKAGES = Arrays.asList(
            "com.facebook.katana", // Facebook
            "com.instagram.android", // Instagram
            "com.twitter.android", // Twitter
            "com.zhiliaoapp.musically", // Tiktok
            "com.google.android.youtube" // Youtube
            // More to add here
    );
    public NotificationWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @NonNull
    @Override
    public Result doWork() {
        if (!hasUsageStatsPermission()) {
            Log.e("NotificationWorker", "No tiene permisos");
            return Result.failure();
        }

        SharedPreferences prefs = getApplicationContext().getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        boolean fifteenMinutesNotificationSent = prefs.getBoolean(FIFTEEN_MINUTES_NOTIFICATION_SENT_KEY, false);
        boolean twoHoursNotificationSent = prefs.getBoolean(TWO_HOURS_NOTIFICATION_SENT_KEY, false);

        if (twoHoursNotificationSent) {
            // If the 2-hour notification has been sent, don't do anything else
            return Result.success();
        }

        long totalUsageTime = trackAppUsage();

        if (totalUsageTime >= TWO_HOURS_IN_MILLIS && !twoHoursNotificationSent) {
            sendUsageNotification("¡La planta no aguantara mucho mas si sigues asi!");
            prefs.edit().putBoolean(TWO_HOURS_NOTIFICATION_SENT_KEY, true).apply();
        } else if (totalUsageTime >= FIFTEEN_MINUTES_IN_MILLIS && !fifteenMinutesNotificationSent) {
            sendUsageNotification("¡El uso de las redes está marchitando tu jardín!");
            prefs.edit().putBoolean(FIFTEEN_MINUTES_NOTIFICATION_SENT_KEY, true).apply();
        }

        return Result.success();
    }

    private void sendUsageNotification(String message) {
        createNotificationChannel();
        NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext(), CHANNEL_ID)
                .setSmallIcon(R.drawable.alerta_icon) // Icono de la notificación
                .setContentTitle("Alerta")
                .setContentText(message)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        NotificationManager manager = (NotificationManager) getApplicationContext()
                .getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(NOTIFICATION_ID, builder.build());
    }

    private long trackAppUsage() {
        Context context = getApplicationContext();
        UsageStatsManager usageStatsManager = (UsageStatsManager) context.getSystemService(Context.USAGE_STATS_SERVICE);
        long currentTime = System.currentTimeMillis();
        long beginTime = currentTime - 900000; // 15 minutes

        Map<String, Long> appUsageMap = new HashMap<>();
        UsageEvents usageEvents = usageStatsManager.queryEvents(beginTime, currentTime);
        if (usageEvents == null) {
            Log.e("NotificationWorker", "Error al obtener los eventos de uso");
            return 0;
        }
        while (usageEvents.hasNextEvent()) {
            UsageEvents.Event event = new UsageEvents.Event();
            usageEvents.getNextEvent(event);
            if (event.getEventType() == UsageEvents.Event.ACTIVITY_RESUMED) {
                String packageName = event.getPackageName();
                if (SOCIAL_MEDIA_PACKAGES.contains(packageName)) {
                    long usageTime = appUsageMap.getOrDefault(packageName, 0L);
                    appUsageMap.put(packageName, usageTime + event.getTimeStamp());
                }
            }
        }

        long totalUsageTime = 0;
        for (long usageTime : appUsageMap.values()) {
            totalUsageTime += usageTime;
        }

        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        long previousTotalUsageTime = prefs.getLong(TOTAL_USAGE_TIME_KEY, 0);
        long newTotalUsageTime = previousTotalUsageTime + totalUsageTime;

        prefs.edit().putLong(TOTAL_USAGE_TIME_KEY, newTotalUsageTime).apply();

        return newTotalUsageTime;
    }

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(
                    CHANNEL_ID,
                    "Notificaciones de la App",
                    NotificationManager.IMPORTANCE_DEFAULT
            );
            NotificationManager manager = (NotificationManager) getApplicationContext()
                    .getSystemService(Context.NOTIFICATION_SERVICE);
            manager.createNotificationChannel(channel);
        }
    }

    private boolean hasUsageStatsPermission() {
        AppOpsManager appOps = (AppOpsManager) getApplicationContext().getSystemService(Context.APP_OPS_SERVICE);
        int mode = appOps.checkOpNoThrow(AppOpsManager.OPSTR_GET_USAGE_STATS, android.os.Process.myUid(), getApplicationContext().getPackageName());
        return mode == AppOpsManager.MODE_ALLOWED;
    }
}