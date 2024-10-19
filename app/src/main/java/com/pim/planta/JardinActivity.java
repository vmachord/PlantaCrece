package com.pim.planta;

import android.app.AppOpsManager;
import android.app.usage.UsageStats;
import android.app.usage.UsageStatsManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class JardinActivity extends AppCompatActivity {
    private int variableContador;
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

        long instagramUsageTime = 0;
        long tiktokUsageTime = 0;
        long youtubeUsageTime = 0;
        long twitterUsageTime = 0;
        long facebookUsageTime = 0;

        if (usageStatsList != null && !usageStatsList.isEmpty()) {
            for (UsageStats usageStats : usageStatsList) { // Recorre la lista de uso de aplicaciones
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
                // Para depuración: imprime el uso de cada aplicación
                Log.d("AppUsage", "Uso de " + packageName + ": " + totalTimeInForeground + " milisegundos");
            }
        } else {
            Log.d("AppUsage", "No hay estadísticas de uso disponibles.");
        }

        // Calcular la media del tiempo de uso (excluyendo Google)
        int appCount = 5; // Cinco aplicaciones: Instagram, TikTok, YouTube, Twitter y Facebook
        long totalUsageTime = instagramUsageTime + tiktokUsageTime + youtubeUsageTime + twitterUsageTime + facebookUsageTime;
        long averageUsageTime = totalUsageTime / appCount;

        // Mostrar la imagen adecuada según la media
        int imageIndex = getImageBasedOnAverageTime(averageUsageTime);

        // Actualiza el TextView con los tiempos de uso de cada aplicación
        String usageSummary = "Tiempo de uso:\n" +
                "Instagram: " + formatTime(instagramUsageTime) + "\n" +
                "TikTok: " + formatTime(tiktokUsageTime) + "\n" +
                "YouTube: " + formatTime(youtubeUsageTime) + "\n" +
                "Twitter: " + formatTime(twitterUsageTime) + "\n" +
                "Facebook: " + formatTime(facebookUsageTime) + "\n" +
                "Media de uso: " + formatTime(averageUsageTime);

        textViewPlantoo.setText(usageSummary);
        setImageBasedOnUsage(imageIndex);
    }

    // Función para calcular el índice de la imagen según la media de tiempo de uso
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

    // Función para mostrar la imagen según el índice
    private void setImageBasedOnUsage(int imageIndex) {
        String imageName = "image_" + imageIndex;
        int resID = getResources().getIdentifier(imageName, "drawable", getPackageName());
        ImageView imageView = findViewById(R.id.imageView);
        imageView.setImageResource(resID);
    }


    private String formatTime(long milliseconds) {
        long seconds = (milliseconds / 1000) % 60;
        long minutes = (milliseconds / (1000 * 60)) % 60;
        long hours = (milliseconds / (1000 * 60 * 60)) % 24;
        return String.format("%02d:%02d:%02d", hours, minutes, seconds);
    }
}