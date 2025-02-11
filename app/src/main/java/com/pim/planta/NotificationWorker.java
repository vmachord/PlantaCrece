package com.pim.planta;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import com.pim.planta.db.DAO;
import com.pim.planta.db.DatabaseExecutor;
import com.pim.planta.db.PlantRepository;
import com.pim.planta.models.Plant;

public class NotificationWorker extends Worker {

    private static final String CHANNEL_ID = "notification_channel";
    private static final int NOTIFICATION_ID = 1;
    private static final String PREFS_NAME = "app_prefs";
    private static final String LAST_EXIT_TIME_KEY = "last_exit_time";
    private static final String LAST_RESUME_TIME_KEY = "last_resume_time";
    private static final String LAST_XP_TO_DEDUCT_KEY = "last_xp_to_deduct";
    private static final long TWO_HOURS_IN_MILLIS = 2 * 60 * 60 * 1000; // 2 hours
    private Context context;

    public NotificationWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
        this.context = context;
    }

    @NonNull
    @Override
    public Result doWork() {
        Log.d("NotificationWorker", "doWork() called");
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        long lastExitTime = prefs.getLong(LAST_EXIT_TIME_KEY, 0);
        long currentTime = System.currentTimeMillis();
        long timeSinceLastExit = currentTime - lastExitTime;
        Log.d("NotificationWorker", "Time since last exit: " + timeSinceLastExit + " ms");

        if (timeSinceLastExit >= TWO_HOURS_IN_MILLIS) {
            // Calculate xpToDeduct
            long lastResumeTime = prefs.getLong(LAST_RESUME_TIME_KEY, 0);
            long usageToAdd = calculateUsageSinceLastResume(lastResumeTime, currentTime);
            long xpToDeduct = usageToAdd / 30000;
            long lastXpToDeduct = prefs.getLong(LAST_XP_TO_DEDUCT_KEY, 0);
            // Check if xpToDeduct is greater than lastXpToDeduct
            if (xpToDeduct > lastXpToDeduct) {
                // Check for selected plant
                SharedPreferences sharedPreferences = context.getSharedPreferences("plant_prefs", Context.MODE_PRIVATE);
                String selectedPlantName = sharedPreferences.getString("selectedPlant", null);
                if (selectedPlantName != null) {
                    PlantRepository plantaRepo = PlantRepository.getInstance(context);
                    DAO dao = plantaRepo.getPlantaDAO();
                    DatabaseExecutor.execute(() -> {
                        Plant plant = dao.getPlantaByName(selectedPlantName);
                        if (plant != null) {
                            // Post to the main thread to send the notification
                            new Handler(Looper.getMainLooper()).post(() -> {
                                sendUsageNotification(xpToDeduct, plant.getNickname());
                            });
                        }
                    });
                }
            }
            // Update the last exit time to now, so we start counting again
            prefs.edit().putLong(LAST_EXIT_TIME_KEY, currentTime).apply();
            // Update the last xp to deduct
            prefs.edit().putLong(LAST_XP_TO_DEDUCT_KEY, xpToDeduct).apply();
        }

        return Result.success();
    }

    public long calculateUsageSinceLastResume(long lastResumeTime, long currentTime) {
        long timeSinceLastResume = currentTime - lastResumeTime;
        return timeSinceLastResume;
    }

    public void sendUsageNotification(long xpToDeduct, String plantNickname) {
        createNotificationChannel();
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, CHANNEL_ID)
                .setSmallIcon(R.drawable.alerta_icon)
                .setContentTitle("Alerta")
                .setContentText("Your " + plantNickname + " has lost " + xpToDeduct + " experience due to bad usage of your phone!")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        NotificationManager manager = (NotificationManager) context
                .getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(NOTIFICATION_ID, builder.build());
    }

    public void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(
                    CHANNEL_ID,
                    "Notificaciones de la App",
                    NotificationManager.IMPORTANCE_DEFAULT
            );
            NotificationManager manager = (NotificationManager) context
                    .getSystemService(Context.NOTIFICATION_SERVICE);
            manager.createNotificationChannel(channel);
        }
    }
}