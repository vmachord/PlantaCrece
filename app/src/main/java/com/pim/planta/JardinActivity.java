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
    private static final int USAGE_PERMISSION_REQUEST_CODE = 1001;
    private static final String PACKAGE_NAME = "com.pim.planta"; // Cambia esto a tu paquete
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
            startActivity(new Intent(Settings.ACTION_USAGE_ACCESS_SETTINGS));
        } else {
            trackAppUsage();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (hasUsageStatsPermission()) {
            trackAppUsage();
        }
    }

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

        long googleUsageTime = 0; // Variable para almacenar el tiempo de uso de Google

        if (usageStatsList != null && !usageStatsList.isEmpty()) {
            for (UsageStats usageStats : usageStatsList) {
                String packageName = usageStats.getPackageName();
                long totalTimeInForeground = usageStats.getTotalTimeInForeground();

                // Verifica si la aplicación es Google y acumula su tiempo de uso
                if (TARGET_PACKAGE_NAME.equals(packageName)) {
                    googleUsageTime += totalTimeInForeground;
                }

                // Para depuración: imprime el uso de cada aplicación
                Log.d("AppUsage", "Uso de " + packageName + ": " + totalTimeInForeground + " milisegundos");
            }
        } else {
            Log.d("AppUsage", "No hay estadísticas de uso disponibles.");
        }

        // Actualiza el TextView con el tiempo de uso de Google
        textViewPlantoo.setText("Tiempo de uso de Google: " + formatTime(googleUsageTime));
    }

    private String formatTime(long milliseconds) {
        long seconds = (milliseconds / 1000) % 60;
        long minutes = (milliseconds / (1000 * 60)) % 60;
        long hours = (milliseconds / (1000 * 60 * 60)) % 24;
        return String.format("%02d:%02d:%02d", hours, minutes, seconds);
    }
}