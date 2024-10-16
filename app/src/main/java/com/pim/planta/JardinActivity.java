package com.pim.planta;

import android.app.AppOpsManager;
import android.app.usage.UsageStats;
import android.app.usage.UsageStatsManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class JardinActivity extends AppCompatActivity {
    private int variableContador;
    private static final String TARGET_PACKAGE_NAME = "com.google.android.googlequicksearchbox"; // Paquete de Google
    private TextView textViewPlantoo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.plantoo);

        textViewPlantoo = findViewById(R.id.textViewPlantoo);

        if (!hasUsageStatsPermission()) {
            Toast.makeText(this, "Por favor habilita el acceso a estadísticas de uso.", Toast.LENGTH_LONG).show();
            //Redirige al usuario a la configuración de Android para habilitar el acceso a las estadísticas de uso
            startActivity(new Intent(Settings.ACTION_USAGE_ACCESS_SETTINGS));
        } else {
            trackAppUsage();
        }
    }

    //Se llama cada vez que la actividad vuelve a ser visible
    @Override
    protected void onResume() {
        super.onResume();
        if (hasUsageStatsPermission()) {
            trackAppUsage();
        }
    }
    //Verifica si la aplicación tiene permiso para acceder a las estadísticas de uso del dispositivo
    private boolean hasUsageStatsPermission() {
        AppOpsManager appOps = (AppOpsManager) getSystemService(Context.APP_OPS_SERVICE);
        int mode = appOps.checkOpNoThrow(AppOpsManager.OPSTR_GET_USAGE_STATS,
                android.os.Process.myUid(), getPackageName());
        return mode == AppOpsManager.MODE_ALLOWED;
    }

    private void trackAppUsage() {
        UsageStatsManager usageStatsManager = (UsageStatsManager) getSystemService(USAGE_STATS_SERVICE);
        long currentTime = System.currentTimeMillis();

        // Obtén el uso de las aplicaciones de los últimos 1 día (86400000 ms)
        List<UsageStats> usageStatsList = usageStatsManager.queryUsageStats(
                UsageStatsManager.INTERVAL_DAILY, currentTime - 86400000, currentTime);

        long googleUsageTime = 0;
        long instagramUsageTime = 0;
        long tiktokUsageTime = 0;
        long youtubeUsageTime = 0;
        long twitterUsageTime = 0;
        long facebookUsageTime = 0;

        // Nombres de los paquetes de las aplicaciones
        final String GOOGLE_PACKAGE_NAME = "com.google.android.googlequicksearchbox";
        final String INSTAGRAM_PACKAGE_NAME = "com.instagram.android";
        final String TIKTOK_PACKAGE_NAME = "com.zhiliaoapp.musically";
        final String YOUTUBE_PACKAGE_NAME = "com.google.android.youtube";
        final String TWITTER_PACKAGE_NAME = "com.twitter.android";
        final String FACEBOOK_PACKAGE_NAME = "com.facebook.katana";

        if (usageStatsList != null && !usageStatsList.isEmpty()) {
            for (UsageStats usageStats : usageStatsList) {
                String packageName = usageStats.getPackageName();
                long totalTimeInForeground = usageStats.getTotalTimeInForeground();

                // Verifica si la aplicación es Google y acumula su tiempo de uso
                if (GOOGLE_PACKAGE_NAME.equals(packageName)) {
                    googleUsageTime += totalTimeInForeground;
                }
                // Verifica si la aplicación es Instagram
                if (INSTAGRAM_PACKAGE_NAME.equals(packageName)) {
                    instagramUsageTime += totalTimeInForeground;
                }
                // Verifica si la aplicación es TikTok
                if (TIKTOK_PACKAGE_NAME.equals(packageName)) {
                    tiktokUsageTime += totalTimeInForeground;
                }
                // Verifica si la aplicación es YouTube
                if (YOUTUBE_PACKAGE_NAME.equals(packageName)) {
                    youtubeUsageTime += totalTimeInForeground;
                }
                // Verifica si la aplicación es Twitter
                if (TWITTER_PACKAGE_NAME.equals(packageName)) {
                    twitterUsageTime += totalTimeInForeground;
                }
                // Verifica si la aplicación es Facebook
                if (FACEBOOK_PACKAGE_NAME.equals(packageName)) {
                    facebookUsageTime += totalTimeInForeground;
                }

                // Para depuración: imprime el uso de cada aplicación
                Log.d("AppUsage", "Uso de " + packageName + ": " + totalTimeInForeground + " milisegundos");
            }
        } else {
            Log.d("AppUsage", "No hay estadísticas de uso disponibles.");
        }

        // Actualiza el TextView con los tiempos de uso de cada aplicación
        String usageSummary = "Tiempo de uso:\n" +
                "Google: " + formatTime(googleUsageTime) + "\n" +
                "Instagram: " + formatTime(instagramUsageTime) + "\n" +
                "TikTok: " + formatTime(tiktokUsageTime) + "\n" +
                "YouTube: " + formatTime(youtubeUsageTime) + "\n" +
                "Twitter: " + formatTime(twitterUsageTime) + "\n" +
                "Facebook: " + formatTime(facebookUsageTime);

        textViewPlantoo.setText(usageSummary);
    }


    private String formatTime(long milliseconds) {
        long seconds = (milliseconds / 1000) % 60;
        long minutes = (milliseconds / (1000 * 60)) % 60;
        long hours = (milliseconds / (1000 * 60 * 60)) % 24;
        return String.format("%02d:%02d:%02d", hours, minutes, seconds);
    }
}