package com.pim.planta;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.usage.UsageStats;
import android.app.usage.UsageStatsManager;
import android.content.Context;
import android.os.Build;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import java.util.List;

//tengo que cambiar para que solo mande la notificacion cuando tenga un cambio
public class NotificationWorker extends Worker {

    private static final String CHANNEL_ID = "notification_channel";
    private static final int NOTIFICATION_ID = 1;
    private int previousImageIndex = 10;

    public NotificationWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @NonNull
    @Override
    public Result doWork() {
        sendNotification();
        return Result.success();
    }

    private void sendNotification() {
        // Crear el canal de notificación para Android 8.0 o superior
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

        // Crear la notificación
        NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext(), CHANNEL_ID)
                .setSmallIcon(R.drawable.alerta_icon) // Icono de la notificación
                .setContentTitle("Alerta")
                .setContentText(trackAppUsage())
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        NotificationManager manager = (NotificationManager) getApplicationContext()
                .getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(NOTIFICATION_ID, builder.build());
    }

    private String trackAppUsage() {
        Context context = getApplicationContext();
        UsageStatsManager usageStatsManager = (UsageStatsManager) context.getSystemService(Context.USAGE_STATS_SERVICE);
        long currentTime = System.currentTimeMillis();

        // Obtiene estadísticas de uso de los últimos 1 día (86400000 ms)
        List<UsageStats> usageStatsList = usageStatsManager.queryUsageStats(
                UsageStatsManager.INTERVAL_DAILY, currentTime - 86400000, currentTime);

        long instagramUsageTime = 0;
        long tiktokUsageTime = 0;
        long youtubeUsageTime = 0;
        long twitterUsageTime = 0;
        long facebookUsageTime = 0;

        if (usageStatsList != null && !usageStatsList.isEmpty()) {
            for (UsageStats usageStats : usageStatsList) {
                String packageName = usageStats.getPackageName();
                long totalTimeInForeground = usageStats.getTotalTimeInForeground();

                switch (packageName) {
                    case "com.instagram.android":
                        instagramUsageTime += totalTimeInForeground;
                        break;
                    case "com.zhiliaoapp.musically":
                        tiktokUsageTime += totalTimeInForeground;
                        break;
                    case "com.google.android.youtube":
                        youtubeUsageTime += totalTimeInForeground;
                        break;
                    case "com.twitter.android":
                        twitterUsageTime += totalTimeInForeground;
                        break;
                    case "com.facebook.katana":
                        facebookUsageTime += totalTimeInForeground;
                        break;
                }
                Log.d("AppUsage", "Uso de " + packageName + ": " + totalTimeInForeground + " ms");
            }
        } else {
            Log.d("AppUsage", "No hay estadísticas de uso disponibles.");
        }

        int appCount = 5;
        long totalUsageTime = instagramUsageTime + tiktokUsageTime + youtubeUsageTime + twitterUsageTime + facebookUsageTime;
        long averageUsageTime = totalUsageTime / appCount;

        // Calcula el índice de imagen basado en el tiempo medio de uso
        int imageIndex = getImageBasedOnAverageTime(averageUsageTime);
        Log.d("AppUsage", "imageIndex calculado: " + imageIndex);

        if (imageIndex != previousImageIndex) {
            if (imageIndex > previousImageIndex) {
                previousImageIndex = imageIndex;
                return "¡Felicidades, tu jardín ha crecido! Estado actual: " + previousImageIndex;
            } else {
                previousImageIndex = imageIndex;
                return "¡El uso de las redes está marchitando tu jardín! Estado actual: " + previousImageIndex;
            }
        } else {
            return "No entra al if, imageIndex es igual a previousImageIndex (" + previousImageIndex + ")";
        }
    }

    private int getImageBasedOnAverageTime(long averageUsageTime) {
        if (averageUsageTime < 60000) { // menos de 1 minuto
            return 10;
        } else if (averageUsageTime < 300000) { // menos de 5 minutos
            return 9;
        } else if (averageUsageTime < 900000) { // menos de 15 minutos
            return 8;
        } else if (averageUsageTime < 1800000) { // menos de 30 minutos
            return 7;
        } else if (averageUsageTime < 3600000) { // menos de 1 hora
            return 6;
        } else if (averageUsageTime < 7200000) { // menos de 2 horas
            return 5;
        } else if (averageUsageTime < 10800000) { // menos de 3 horas
            return 4;
        } else if (averageUsageTime < 14400000) { // menos de 4 horas
            return 3;
        } else if (averageUsageTime < 18000000) { // menos de 5 horas
            return 2;
        } else {
            return 1; // más de 5 horas
        }
    }
}
