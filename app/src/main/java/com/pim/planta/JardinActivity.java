package com.pim.planta;

import android.Manifest;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
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
import android.os.CountDownTimer;
import android.provider.Settings;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;
import androidx.work.WorkRequest;

import com.pim.planta.db.DAO;
import com.pim.planta.db.DatabaseExecutor;
import com.pim.planta.db.PlantRepository;
import com.pim.planta.models.Plant;
import com.pim.planta.models.UserLogged;
import com.pim.planta.models.UserPlantRelation;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class JardinActivity extends AppCompatActivity {

    public static int currentImageIndex = 1;
    private int previousImageIndex = -1;
    private static final String CHANNEL_ID = "default";
    private static final int NOTIFICATION_PERMISSION_CODE = 100;
    private PopupWindow tooltipWindow;

    private Plant plant;
    //Variables watering plant
    private boolean canWater = true;
    private long remainingTimeMillisWatering = 0;
    private static final long ONE_DAY_MILLIS = 24 * 60 * 60 * 1000;
    //Variables pad plant
    private boolean canPad = true;
    private long remainingTimeMillisPad = 0;
    private static final long FIVE_MIN_MILLIS = 5 * 60 * 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.plantoo);
        PlantRepository plantaRepo = PlantRepository.getInstance(this);
        DAO dao = plantaRepo.getPlantaDAO();

        WorkRequest notificationWorkRequest =
                new PeriodicWorkRequest.Builder(NotificationWorker.class, 15, TimeUnit.MINUTES)
                        .build();

        WorkManager.getInstance(this).enqueue(notificationWorkRequest);

        setUpBottom();

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

        //QUE HACE ESTO?!?!??
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.execute(() -> {
            if (dao.getUserPlantRelations(UserLogged.getInstance().getCurrentUser().getId()).isEmpty()) {
                for (Plant plant : plantaRepo.getPlantaDAO().getAllPlantas()) {
                    UserPlantRelation relation = new UserPlantRelation(UserLogged.getInstance().getCurrentUser().getId(), plant.getId()); // growCount is initialized to 0 by default
                    dao.insertUserPlantRelation(relation);
                }
            }
        });

        //Boton my cares
        Button btnMyCares = findViewById(R.id.btn_my_cares);
        btnMyCares.setOnClickListener(view -> showDescriptionPopup());

        //Boton de dentro del popup para hacer que se vaya
        Button btn_desc_close = findViewById(R.id.btn_desc_close);
        btn_desc_close.setOnClickListener(view -> {
            View popupView = findViewById(R.id.plant_desc_popup);
            popupView.setVisibility(View.INVISIBLE);
        });

        //Boton para navegar al Invernadero
        ImageButton imageButtonOjo = findViewById(R.id.imageButtonOjo);
        imageButtonOjo.setOnClickListener(v -> {
            Intent intent = new Intent(JardinActivity.this, InvernaderoActivity.class);
            startActivity(intent);
        });

        //Boton regar planta
        ImageButton imageWater = findViewById(R.id.icon_water);
        imageWater.setOnClickListener(v -> {
            if (!canWater) {
               // showTooltip(v, "Next water in : " + formatTime(remainingTimeMillisWatering));
            } else {
                wateringPlant(300);
            }
        });

        //Boton pad planta
        ImageButton imagePad = findViewById(R.id.icon_gesture);
        imagePad.setOnClickListener(v -> {
            if (!canPad) {
               // showTooltip(v, "Next pad in : " + formatTime(remainingTimeMillisPad));
            } else {
                padPlant();
            }
        });


        getPlantFromDB();

        //android:background="@drawable/background_plantoo"

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_O) {
            wateringPlant(300);
            return true;
        }
        if (keyCode == KeyEvent.KEYCODE_P) {
            wateringPlant(-300);
            return true;
        }

        if (keyCode == KeyEvent.KEYCODE_U) {
            String today = new SimpleDateFormat("EEE", Locale.getDefault()).format(new Date());

            SharedPreferences sharedPreferences = getSharedPreferences("AppUsageData", MODE_PRIVATE);
            long prevInstagramTime = sharedPreferences.getLong(today + "_Instagram",0);

            // Añade 1 minuto (60,000 ms)
            long newInstagramTime = prevInstagramTime + 60000;

            // Guarda el nuevo tiempo en SharedPreferences
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putLong(today + "_Instagram", newInstagramTime);
            editor.apply();

            // Muestra un log para confirmar que el tiempo ha cambiado
            Log.d("Tiempo", "Nuevo tiempo de "+  today + "_Instagram"+": " + formatTime(newInstagramTime));

            // Opción: muestra un Toast para que sea visible en pantalla
            Toast.makeText(this, "Se añadió 1 minuto al tiempo de Instagram", Toast.LENGTH_SHORT).show();

            return true; // Indica que se manejó la pulsación de la tecla
        }
        return super.onKeyDown(keyCode, event);
    }

    private void getPlantFromDB(){
        SharedPreferences sharedPreferences = getSharedPreferences("plant_prefs", MODE_PRIVATE);

        PlantRepository plantaRepo = PlantRepository.getInstance(this);
        DAO dao = plantaRepo.getPlantaDAO();

        DatabaseExecutor.execute(() -> {
            plant = dao.getPlantaByName(sharedPreferences.getString("selectedPlant", ""));
            calculatePlantIndex(plant);
            calculateXPprogress(plant);
        });

    }

    private void showDescriptionPopup() {
        // Inflar el diseño del pop-up
        View popupView = findViewById(R.id.plant_desc_popup);
        popupView.setVisibility(View.VISIBLE);

        TextView plantTitle = findViewById(R.id.plant_name_desc);
        plantTitle.setText(plant.getName());


        TextView plantDesc = findViewById(R.id.plant_desc);

        String desc = "\n\n" + plant.getDescription() + "\n\n" +
                "XP actual de la planta : " + plant.getXp() + "\n" +
                "XP máxima de la planta : " + plant.getXpMax() + "\n\n\n\n";
        plantDesc.setText(desc);
    }

    private void calculatePlantIndex(Plant plant){
        //Formula que calcula el nivel de la planta para saber el indice de la foto
        int level = (int) Math.floor(Math.sqrt((double) plant.getXp() / plant.getXpMax()) * 5);

        setImageBasedOnUsage(plant.getBasePath(),level);

        TextView plant_lvl_text = findViewById(R.id.plant_lvl);
        plant_lvl_text.setText(""+ level);
    }

    private void calculateXPprogress(Plant plant){
        int xpMax = plant.getXpMax();
        int xpNow = plant.getXp();

        //Formula que calcula el nivel de la planta para saber el indice de la foto
        int level = (int) Math.floor(Math.sqrt((double) xpNow / xpMax) * 5);

        if(xpMax == xpNow){
            ProgressBar progressBar = findViewById(R.id.progressBar);
            progressBar.setProgress(100,true);
        } else {

            double xpCurrentLevel = Math.pow((double) level / 5, 2) * xpMax;
            double xpNextLevel = Math.pow((double) (level + 1) / 5, 2) * xpMax;

            double progress = (xpNow - xpCurrentLevel) / (xpNextLevel - xpCurrentLevel);

            ProgressBar progressBar = findViewById(R.id.progressBar);
            progressBar.setProgress((int) (progress * 100),true);
        }

        TextView plant_name  = findViewById(R.id.plant_name);
        plant_name.setText(plant.getName() + " | L."  + level);
    }

    private void wateringPlant(int xp){
        if ((plant.getXp() + xp) > plant.getXpMax()){
            int xp_needed = plant.getXpMax() - plant.getXp();
            plant.addXp(xp_needed);
            calculateXPprogress(plant);
            calculatePlantIndex(plant);
            startWateringCooldown();
        } else{
            plant.addXp(xp);
            calculateXPprogress(plant);
            calculatePlantIndex(plant);
            startWateringCooldown();
        }
    }
    private void padPlant(){
        if ((plant.getXp() + 5) > plant.getXpMax()){
            int xp_needed = plant.getXpMax() - plant.getXp();
            plant.addXp(xp_needed);
            calculateXPprogress(plant);
            calculatePlantIndex(plant);
            startPadCooldown();
        } else{
            plant.addXp(5);
            calculateXPprogress(plant);
            calculatePlantIndex(plant);
            startPadCooldown();
        }
    }
    private void startWateringCooldown() {
        canWater = false;
        remainingTimeMillisWatering = ONE_DAY_MILLIS;

        new CountDownTimer(remainingTimeMillisWatering, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                remainingTimeMillisWatering = millisUntilFinished;
            }

            @Override
            public void onFinish() {
                canWater = true;
                remainingTimeMillisWatering = 0;
            }
        }.start();
    }
    private void startPadCooldown() {
        canPad = false;
        remainingTimeMillisPad = FIVE_MIN_MILLIS;

        new CountDownTimer(remainingTimeMillisPad, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                remainingTimeMillisPad = millisUntilFinished;
            }

            @Override
            public void onFinish() {
                canWater = true;
                remainingTimeMillisPad = 0;
            }
        }.start();
    }


    private void animateButton(View view) {
        ObjectAnimator animator = ObjectAnimator.ofPropertyValuesHolder(
                view,
                PropertyValuesHolder.ofFloat("scaleX", 0.9f, 1.0f),
                PropertyValuesHolder.ofFloat("scaleY", 0.9f, 1.0f)
        );
        animator.setDuration(150); // Duración de la animación
        animator.start();
    }

    //Se llama cada vez que la actividad vuelve a ser visible
    @Override
    protected void onResume() {
        super.onResume();
        if (hasUsageStatsPermission()) {
            penalizeIfUsageIncreased();
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

    private void saveAppUsageTimes(SharedPreferences sharedPreferences,
                                   long instagramUsageTime,
                                   long tiktokUsageTime,
                                   long youtubeUsageTime,
                                   long twitterUsageTime,
                                   long facebookUsageTime) {
        String todayKey = new SimpleDateFormat("EEE", Locale.getDefault()).format(new Date());
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putLong(todayKey + "_Instagram", instagramUsageTime);
        editor.putLong(todayKey + "_TikTok", tiktokUsageTime);
        editor.putLong(todayKey + "_YouTube", youtubeUsageTime);
        editor.putLong(todayKey + "_Twitter", twitterUsageTime);
        editor.putLong(todayKey + "_Facebook", facebookUsageTime);
        editor.apply();
    }

    private long getPreviousAppUsage(SharedPreferences sharedPreferences, String dayKey) {
        // Lista de claves de las aplicaciones a sumar
        String[] appKeys = {
                "_Instagram",
                "_TikTok",
                "_YouTube",
                "_Twitter",
                "_Facebook"
        };

        long totalUsage = 0;

        // Sumar los tiempos de todas las aplicaciones con el token del día
        for (String key : appKeys) {
            totalUsage += sharedPreferences.getLong(dayKey + key, 0);
        }

        return totalUsage; // Retorna el total de uso acumulado para el día
    }


    private void penalizeIfUsageIncreased() {
        SharedPreferences prefs = getSharedPreferences("AppUsageData", MODE_PRIVATE);
        String today = new SimpleDateFormat("EEE", Locale.getDefault()).format(new Date());

        long currentTime = prefs.getLong(today+"_Total",0);

        if (currentTime > 0) {
            long difference = currentTime - 0;
            Toast.makeText(this, "Se te ha quitado " + difference + " por tu consumo de redes.", Toast.LENGTH_LONG).show();
        }
    }

    private void trackAppUsage() {
        UsageStatsManager usageStatsManager = (UsageStatsManager) getSystemService(USAGE_STATS_SERVICE);
        long currentTime = System.currentTimeMillis();
        // Obtén el uso de las aplicaciones de los últimos 1 día (86400000 ms)
        List<UsageStats> usageStatsList = usageStatsManager.queryUsageStats(
                UsageStatsManager.INTERVAL_DAILY, currentTime - 86400000, currentTime);

        SharedPreferences sharedPreferencesUsage = getSharedPreferences("AppUsageData", MODE_PRIVATE);
        String today = new SimpleDateFormat("EEE", Locale.getDefault()).format(new Date());

        //TO DO
        long instagramUsageTime = 900000;
        long tiktokUsageTime = 0;
        long youtubeUsageTime = 0;
        long twitterUsageTime = 0;
        long facebookUsageTime = 0;

        long totalTimeInForeground = 0;

        if (usageStatsList != null && !usageStatsList.isEmpty()) {
            for (UsageStats usageStats : usageStatsList) { // Recorre la lista de uso de aplicaciones
                String packageName = usageStats.getPackageName();
                totalTimeInForeground = usageStats.getTotalTimeInForeground();

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

        if(previousImageIndex == -1)
            previousImageIndex = imageIndex;

        SharedPreferences sharedPreferences = getSharedPreferences("plant_prefs", MODE_PRIVATE);

        String selectedPlant = sharedPreferences.getString("selectedPlant", null);
        if (selectedPlant == null) {
            selectedPlant = "girasol";
        }
        setImageBasedOnUsage(selectedPlant,imageIndex);
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

    //Este metodo tiene como entries, el nombre de la planta y el indice de su crecimiento
    private void setImageBasedOnUsage(String basePath, int imageIndex) {
        // Crear el nombre del recurso de la imagen usando el basepath de la planta y el índice
        String imageName;
        if (imageIndex == 0){
            imageName = basePath;
        }else {
            imageName = basePath + imageIndex;

        }
        int resID = getResources().getIdentifier(imageName, "drawable", getPackageName());

        ImageView imageView = findViewById(R.id.plant_image);

        // Verificar si la imagen existe
        if (resID != 0) {
            imageView.setImageResource(resID);
        } else {
            int fallbackResID = getResources().getIdentifier("tulipan", "drawable", getPackageName());
            imageView.setImageResource(fallbackResID);
        }

        // Actualizar el índice de la imagen actual
        currentImageIndex = imageIndex;
    }

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
/*
    private void showTooltip(View anchorView, String tooltipText) {
        // Infla el diseño del bocadillo
        View tooltipView = LayoutInflater.from(anchorView.getContext())
                .inflate(R.layout.hover_text_plantoo, null);

        // Establece el texto
        TextView textView = tooltipView.findViewById(R.id.tooltipText);
        textView.setText(tooltipText);

        // Crea y configura el PopupWindow
        tooltipWindow = new PopupWindow(tooltipView,
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT,
                false);
        tooltipWindow.setOutsideTouchable(true);
        tooltipWindow.setBackgroundDrawable(null);

        // Muestra el PopupWindow
        tooltipWindow.showAsDropDown(anchorView, 0, -anchorView.getHeight() - 20);
    }*/
    public void setUpBottom(){
        ImageButton imageButtonLupa = findViewById(R.id.imageButtonLupa);
        ImageButton imageButtonMaceta = findViewById(R.id.imageButtonMaceta);
        imageButtonMaceta.setEnabled(false); // Deshabilita el boton
        imageButtonMaceta.setImageAlpha(128); // Oscurece el boton
        ImageButton imageButtonPlantadex = findViewById(R.id.imageButtonPlantadex);
        ImageButton imageButtonUsuario = findViewById(R.id.imageButtonUsuario);

        imageButtonLupa.setOnClickListener(v -> {
            animateButton(v);
            Intent intent = new Intent(JardinActivity.this, DiaryActivity.class);
            //sendUsageNotification("¡Felicidades, tu jardin ha crecido!. Estado actual: " + 1);
            startActivity(intent);
        });
        imageButtonPlantadex.setOnClickListener(v -> {
            animateButton(v);
            Intent intent = new Intent(JardinActivity.this, PlantListActivity.class);
            startActivity(intent);
        });
        imageButtonUsuario.setOnClickListener(v -> {
            animateButton(v);
            Intent intent = new Intent(JardinActivity.this, PerfilActivity.class);
            startActivity(intent);
        });
    }

}