package com.pim.planta;

import android.app.AppOpsManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.usage.UsageStats;
import android.app.usage.UsageStatsManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;
import androidx.work.WorkRequest;
import android.Manifest;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class JardinActivity extends AppCompatActivity {
    private TextView textViewPlantoo;
    public static int currentImageIndex = 1;
    private int previousImageIndex = -1;
    private static final String CHANNEL_ID = "default";
    private static final int NOTIFICATION_PERMISSION_CODE = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.plantoo);

        WorkRequest notificationWorkRequest =
                new PeriodicWorkRequest.Builder(NotificationWorker.class, 15, TimeUnit.MINUTES)
                        .build();

        WorkManager.getInstance(this).enqueue(notificationWorkRequest);

        setupBottom();
        textViewPlantoo = findViewById(R.id.textViewPlantoo);
        ImageButton imageButtonOjo = findViewById(R.id.imageButtonOjo);
        imageButtonOjo.setOnClickListener(v -> {
            Intent intent = new Intent(JardinActivity.this, InvernaderoActivity.class);
            startActivity(intent);
        });
        if (Build.VERSION.SDK_INT >= 33) {  // También puedes usar Build.VERSION_CODES.TIRAMISU
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS)
                    != PackageManager.PERMISSION_GRANTED) {
                // Solicitar el permiso si no ha sido otorgado
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.POST_NOTIFICATIONS},
                        NOTIFICATION_PERMISSION_CODE);
            }
        }
        if (!hasUsageStatsPermission()) {
            Toast.makeText(this, "Por favor habilita el acceso a estadísticas de uso.", Toast.LENGTH_LONG).show();
            //Redirige al usuario a la configuración de Android para habilitar el acceso a las estadísticas de uso
            startActivity(new Intent(Settings.ACTION_USAGE_ACCESS_SETTINGS));
        }
    }

    public void setupBottom(){
        ImageButton imageButtonLupa = findViewById(R.id.imageButtonLupa);
        ImageButton imageButtonMaceta = findViewById(R.id.imageButtonMaceta);
        imageButtonMaceta.setEnabled(false); // Deshabilita el boton
        imageButtonMaceta.setImageAlpha(128); // Oscurece el boton
        ImageButton imageButtonPlantadex = findViewById(R.id.imageButtonPlantadex);
        ImageButton imageButtonUsuario = findViewById(R.id.imageButtonUsuario);

        imageButtonLupa.setOnClickListener(v -> {
            Intent intent = new Intent(JardinActivity.this, DiarioActivity.class);
            //sendUsageNotification("¡Felicidades, tu jardin ha crecido!. Estado actual: " + 1);
            startActivity(intent);
        });
        imageButtonPlantadex.setOnClickListener(v -> {
            Intent intent = new Intent(JardinActivity.this, PlantListActivity.class);
            startActivity(intent);
        });
        imageButtonUsuario.setOnClickListener(v -> {
            Intent intent = new Intent(JardinActivity.this, PerfilActivity.class);
            startActivity(intent);
        });
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
        if(previousImageIndex == -1)
            previousImageIndex = imageIndex;
        SharedPreferences sharedPreferences = getSharedPreferences("plant_prefs", MODE_PRIVATE);
        String selectedPlant = sharedPreferences.getString("selectedPlant", null);
        //setImageBasedOnUsage(selectedPlant,imageIndex);
        setImageBasedOnUsage(imageIndex);
    }

    // Función para calcular el índice de la imagen según la media de tiempo de uso
    private int getImageBasedOnAverageTime(long averageUsageTime) {
        if (averageUsageTime < 60000) { // menos de 1 minuto
            return 5;
        } else if (averageUsageTime < 300000) { // menos de 5 minutos
            return 4;
        } else if (averageUsageTime < 900000) { // menos de 15 minutos
            return 3;
        } else if (averageUsageTime < 1800000) { // menos de 30 minutos
            return 2;
        } else {
            return 1; // más de 30 minutos
        }
    }
    private void createNotificationChannel() {
        // Verifica si el dispositivo está ejecutando Android Oreo (API 26) o superior
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            // Define un ID único para el canal
            String channelId = "usage_channel";
            CharSequence channelName = "App Usage Alerts"; // Nombre que se mostrará en la configuración
            String channelDescription = "Canal para alertas de seguimiento del uso de las aplicaciones"; // Descripción opcional

            // Crea el canal con las configuraciones necesarias
            NotificationChannel channel = new NotificationChannel(
                    channelId,          // ID del canal
                    channelName,        // Nombre visible del canal
                    NotificationManager.IMPORTANCE_DEFAULT // Nivel de importancia
            );

            // Configura la descripción del canal (opcional)
            channel.setDescription(channelDescription);
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }


    private void sendUsageNotification(String message) {
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        // Configuración para Android O y superior
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            createNotificationChannel();  // Asegúrate de que el canal esté creado antes de enviar la notificación
        }

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "usage_channel")
                .setSmallIcon(R.drawable.alerta_icon) // Asegúrate de que tienes un icono pequeño válido
                .setContentTitle("Alerta")
                .setContentText(message)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        // Mostrar la notificación
        notificationManager.notify(1, builder.build());
    }


    // Función para mostrar la imagen según el índice
    private void setImageBasedOnUsage(int imageIndex) {
        String imageName = "image_" + imageIndex;
        int resID = getResources().getIdentifier(imageName, "drawable", getPackageName());
        ImageView imageView = findViewById(R.id.imageView);
        imageView.setImageResource(resID);
        currentImageIndex = imageIndex;
    }
/*
    private void setImageBasedOnUsage(String plantName, int imageIndex) {
        // Reemplazar los espacios por guiones bajos y eliminar los acentos
        String sanitizedPlantName = plantName.replace(" ", "_").toLowerCase();
        sanitizedPlantName = removeAccents(sanitizedPlantName); // Eliminar caracteres acentuados

        // Crear el nombre del recurso de la imagen usando el nombre de la planta y el índice
        String imageName = "image_" + sanitizedPlantName + imageIndex;
        int resID = getResources().getIdentifier(imageName, "drawable", getPackageName());

        ImageView imageView = findViewById(R.id.imageView);

        // Verificar si la imagen existe
        if (resID != 0) {
            imageView.setImageResource(resID);
        } else {
            // Si no existe, usar solo el índice como nombre del recurso
            String fallbackImageName = "image_" + imageIndex;
            int fallbackResID = getResources().getIdentifier(fallbackImageName, "drawable", getPackageName());
            imageView.setImageResource(fallbackResID);
        }

        // Actualizar el índice de la imagen actual
        currentImageIndex = imageIndex;
    }*/

    private String removeAccents(String str) {
        return str.replaceAll("[áàäâ]", "a")
                .replaceAll("[éèëê]", "e")
                .replaceAll("[íìïî]", "i")
                .replaceAll("[óòöô]", "o")
                .replaceAll("[úùüû]", "u")
                .replaceAll("[ç]", "c");
    }

    private String formatTime(long milliseconds) {
        long seconds = (milliseconds / 1000) % 60;
        long minutes = (milliseconds / (1000 * 60)) % 60;
        long hours = (milliseconds / (1000 * 60 * 60)) % 24;
        return String.format("%02d:%02d:%02d", hours, minutes, seconds);
    }
}