package com.pim.planta;

import android.Manifest;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.app.Activity;
import android.app.usage.UsageStats;
import android.app.usage.UsageStatsManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.pim.planta.db.DAO;
import com.pim.planta.db.DatabaseExecutor;
import com.pim.planta.db.PlantRepository;
import com.pim.planta.models.Plant;
import com.pim.planta.models.UserLogged;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;


public class PerfilActivity extends AppCompatActivity{

    private ImageView profileImageView;
    private TextView userNameTextView;
    private BarChart barChart;
    private TextView textViewPlantoo;
    private TextView textViewText;
    private TextView textViewText2;
    private TextView textViewText3;

    private boolean isExpanded = false;

    private ImageView imageView12;

    private FrameLayout frame;
    private ImageView profileImage;
    private ImageView imageView13;
    private TextView userName;
    private int currentWeek;
    private Plant plant;
    private DAO dao;
    private static final int REQUEST_CODE_READ_EXTERNAL_STORAGE = 100;
    private ActivityResultLauncher<Intent> galleryLauncher;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        initializeNameAndProfile();
        //initializeGraph();
        setUpBottom();
        textViewPlantoo = findViewById(R.id.textView4);
        trackAppUsage2();
        textViewText = findViewById(R.id.textCreationDate);
        textViewText.setText("Bloomed on: " + UserLogged.getInstance().getCurrentUser().getCreationDate().toString());
        textViewText2 = findViewById(R.id.textScientificName);
        textViewText3 = findViewById(R.id.textNickname);
        SharedPreferences sharedPreferences = getSharedPreferences("plant_prefs", MODE_PRIVATE);
        PlantRepository plantaRepo = PlantRepository.getInstance(this);
        dao = plantaRepo.getPlantaDAO();
        String selectedPlantName = sharedPreferences.getString("selectedPlant", "");

        if (!selectedPlantName.isEmpty()) {
            DatabaseExecutor.executeAndWait(() -> {
                plant = dao.getPlantaByName(selectedPlantName);
            });
            if (plant != null) {
                // Display the scientific name and nickname
                textViewText2.setText("Scientific plant name: " + plant.getScientificName());
                textViewText3.setText("Nickname: " + plant.getNickname());
            } else {
                // Handle the case where the plant is not found
                textViewText2.setText("ERROR ERROR ERROR");
                textViewText3.setText("ERROR ERROR ERROR");
            }
        } else {
            // Handle the case where no plant is selected
            textViewText2.setText("ERROR ERROR ERROR");
            textViewText3.setText("ERROR ERROR ERROR");
        }
        imageView12 = findViewById(R.id.imageView12);
        frame = findViewById(R.id.frame);
        profileImage = findViewById(R.id.profile_image);
        imageView13 = findViewById(R.id.imageView13);
        userName = findViewById(R.id.user_name);
        setupClickListener();

        currentWeek = Calendar.getInstance().get(Calendar.WEEK_OF_YEAR);

        ImageButton buttonPreviousWeek = findViewById(R.id.buttonPreviousWeek);
        ImageButton buttonNextWeek = findViewById(R.id.buttonNextWeek);

        buttonPreviousWeek.setOnClickListener(v -> {
            currentWeek--; // Retroceder a la semana anterior
            if (currentWeek < 1) {
                currentWeek = 52; // Si estamos en la semana 1, volvemos a la semana 52
            }
            updateGraphAndData(currentWeek);
        });

        buttonNextWeek.setOnClickListener(v -> {
            currentWeek++; // Avanzar a la siguiente semana
            if (currentWeek > 52) {
                currentWeek = 1; // Si estamos en la semana 52, volvemos a la semana 1
            }
            updateGraphAndData(currentWeek);
        });

        updateGraphAndData(currentWeek);
        galleryLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        Intent data = result.getData();
                        if (data != null) {
                            Uri selectedImageUri = data.getData();
                            if (selectedImageUri != null) {
                                try {
                                    InputStream imageStream = getContentResolver().openInputStream(selectedImageUri);
                                    Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
                                    profileImageView.setImageBitmap(selectedImage);
                                } catch (FileNotFoundException e) {
                                    e.printStackTrace();
                                    Toast.makeText(this, "Error loading image", Toast.LENGTH_SHORT).show();
                                }
                            }
                        }
                    }
                }
        );
    }

    private void updateGraphAndData(int selectedWeek) {
        initializeGraph(selectedWeek);
        updateUsageSummary(selectedWeek);
    }


    @Override
    protected void onResume() {
        super.onResume();
        trackAppUsage2();
    }
    private void setupClickListener() {
        imageView12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isExpanded = !isExpanded; // Alternar el estado de expansión

                // Cambiar la visibilidad de los elementos
                if (isExpanded) {
                    // Mostrar los elementos
                    profileImage.setVisibility(View.VISIBLE);
                    imageView13.setVisibility(View.VISIBLE);
                    userName.setVisibility(View.VISIBLE);
                    textViewText.setVisibility(View.VISIBLE);
                    textViewText2.setVisibility(View.VISIBLE);
                    textViewText3.setVisibility(View.VISIBLE);
                } else {
                    // Ocultar los elementos
                    profileImage.setVisibility(View.GONE);
                    imageView13.setVisibility(View.GONE);
                    userName.setVisibility(View.GONE);
                    textViewText.setVisibility(View.GONE);
                    textViewText2.setVisibility(View.GONE);
                    textViewText3.setVisibility(View.GONE);
                }

                // Realizar la animación de expansión/colapso
                frame.post(new Runnable() {
                    @Override
                    public void run() {
                        float targetTranslationY = isExpanded ? 0 : -frame.getHeight() / 4;

                        frame.animate()
                                .translationY(targetTranslationY)
                                .setDuration(300)
                                .setInterpolator(new AccelerateDecelerateInterpolator())
                                .start();
                    }
                });
            }
        });
    }

    private void initializeGraph(int selectedWeek) {
        barChart = findViewById(R.id.bar_chart);
        SharedPreferences prefs = getSharedPreferences("AppUsageData", MODE_PRIVATE);

        float[][] appUsagePerDay = new float[7][5];

        String[] daysOfWeek = {"Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"};

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.WEEK_OF_YEAR, selectedWeek);
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);

        for (int i = 0; i < 7; i++) {
            String day = daysOfWeek[i];

            for (int j = 0; j < 5; j++) {
                String appName = (j == 0) ? "Instagram" : (j == 1) ? "TikTok" :
                        (j == 2) ? "YouTube" : (j == 3) ? "Twitter" : "Facebook";

                String appKey = "Week" + selectedWeek + "_" + day + "_" + appName;

                appUsagePerDay[i][j] = prefs.getLong(appKey, 0) / 3600000f;

                Log.d("AppUsage", "Cargando datos para: " + appKey + " -> " + appUsagePerDay[i][j] + " horas");
            }

            calendar.add(Calendar.DAY_OF_YEAR, 1);
        }

        ArrayList<BarEntry> barEntries = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            barEntries.add(new BarEntry(i, appUsagePerDay[i]));
        }

        BarDataSet barDataSet = new BarDataSet(barEntries, "Uso de Aplicaciones");
        barDataSet.setStackLabels(new String[]{"Instagram", "TikTok", "YouTube", "Twitter", "Facebook"});

        barDataSet.setColors(new int[]{
                Color.parseColor("#004D40"),
                Color.parseColor("#2E7D32"),
                Color.parseColor("#4CAF50"),
                Color.parseColor("#81C784"),
                Color.parseColor("#A5D6A7")
        });

        BarData data = new BarData(barDataSet);
        data.setBarWidth(0.5f);
        barChart.setData(data);
        Typeface aventaFont = ResourcesCompat.getFont(this, R.font.aventa);

        XAxis xAxis = barChart.getXAxis();
        xAxis.setValueFormatter(new IndexAxisValueFormatter(daysOfWeek));
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setTextSize(12f);
        xAxis.setTextColor(Color.BLACK);
        xAxis.setGranularity(1f);
        xAxis.setDrawGridLines(false);

        YAxis leftAxis = barChart.getAxisLeft();
        leftAxis.setAxisMinimum(0f);
        leftAxis.setTextSize(12f);
        leftAxis.setTextColor(Color.BLACK);

        barChart.getAxisRight().setEnabled(false);

        Legend legend = barChart.getLegend();
        legend.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);
        legend.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        legend.setDrawInside(false);
        legend.setTextSize(12f);
        legend.setTextColor(Color.BLACK);

        barChart.animateY(1000, Easing.EaseInOutCubic);
        barChart.setFitBars(true);
        barChart.invalidate();
    }


    private void initializeNameAndProfile(){
        profileImageView = findViewById(R.id.profile_image);
        userNameTextView = findViewById(R.id.user_name);

        profileImageView.setOnClickListener(view -> cambiarImagenDePerfil());
        if (UserLogged.getInstance().getCurrentUser() != null) {
            String username = UserLogged.getInstance().getCurrentUser().getUsername();
            userNameTextView.setText(username);
        } else {
            userNameTextView.setText("ERROR ERROR ERROR");
        }
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

    public void setUpBottom(){
        ImageButton imageButtonLupa = findViewById(R.id.imageButtonLupa);
        ImageButton imageButtonMaceta = findViewById(R.id.imageButtonMaceta);
        ImageButton imageButtonPlantadex = findViewById(R.id.imageButtonPlantadex);
        ImageButton imageButtonUsuario = findViewById(R.id.imageButtonUsuario);
        imageButtonUsuario.setEnabled(false); // Deshabilita el boton
        imageButtonUsuario.setImageAlpha(128); // Oscurece el boton

        imageButtonLupa.setOnClickListener(v -> {
            animateButton(v);
            Intent intent = new Intent(PerfilActivity.this, DiaryActivity.class);
            startActivity(intent);
        });
        imageButtonPlantadex.setOnClickListener(v -> {
            animateButton(v);
            Intent intent = new Intent(PerfilActivity.this, PlantListActivity.class);
            startActivity(intent);
        });
        imageButtonMaceta.setOnClickListener(view -> {
            animateButton(view);
            Intent intent = new Intent(PerfilActivity.this, JardinActivity.class);
            startActivity(intent);
        });
    }

    public long getTotalUsageToday() {
        UsageStatsManager usageStatsManager = (UsageStatsManager) getSystemService(Context.USAGE_STATS_SERVICE);

        // Obtén el inicio del día a las 00:00:00
        long startOfDay = getStartOfDay();
        long endOfDay = System.currentTimeMillis();

        // Consulta las estadísticas de uso para hoy
        List<UsageStats> usageStatsList = usageStatsManager.queryUsageStats(UsageStatsManager.INTERVAL_DAILY, startOfDay, endOfDay);

        long totalTimeToday = 0;
        for (UsageStats usageStats : usageStatsList) {
            totalTimeToday += usageStats.getTotalTimeInForeground();
        }

        return totalTimeToday;
    }

    private void updateUsageSummary(int selectedWeek) {
        SharedPreferences prefs = getSharedPreferences("AppUsageData", MODE_PRIVATE);

        String today = new SimpleDateFormat("EEE", Locale.getDefault()).format(new Date());

        long instagramUsage = prefs.getLong("Week" + selectedWeek + "_" + today + "_Instagram", 0);
        long tiktokUsage = prefs.getLong("Week" + selectedWeek + "_" + today + "_TikTok", 0);
        long youtubeUsage = prefs.getLong("Week" + selectedWeek + "_" + today + "_YouTube", 0);
        long twitterUsage = prefs.getLong("Week" + selectedWeek + "_" + today + "_Twitter", 0);
        long facebookUsage = prefs.getLong("Week" + selectedWeek + "_" + today + "_Facebook", 0);

        String usageSummary = String.format(
                "\n%-15s %s\n" +
                        "%-15s     %s\n" +
                        "%-15s  %s\n" +
                        "%-15s     %s\n" +
                        "%-15s %s",
                "Instagram:", formatTime(instagramUsage),
                "TikTok:     ", formatTime(tiktokUsage),
                "YouTube:", formatTime(youtubeUsage),
                "Twitter:", formatTime(twitterUsage),
                "Facebook:", formatTime(facebookUsage)
        );

        if (textViewPlantoo != null) {
            textViewPlantoo.setText(usageSummary);
        } else {
            Log.e("AppUsage", "TextView no está inicializado.");
        }
    }

    private void trackAppUsage2() {
        UsageStatsManager usageStatsManager = (UsageStatsManager) getSystemService(USAGE_STATS_SERVICE);
        if (usageStatsManager == null) {
            Log.e("AppUsage", "UsageStatsManager no está disponible.");
            return;
        }

        long totalTimeToday = getTotalUsageToday();

        long instagramUsageTimeToday = 0;
        long tiktokUsageTimeToday = 0;
        long youtubeUsageTimeToday = 0;
        long twitterUsageTimeToday = 0;
        long facebookUsageTimeToday = 0;

        long startOfDay = getStartOfDay();
        long endOfDay = System.currentTimeMillis();

        List<UsageStats> usageStatsList = usageStatsManager.queryUsageStats(
                UsageStatsManager.INTERVAL_DAILY, startOfDay, endOfDay);

        if (usageStatsList == null || usageStatsList.isEmpty()) {
            Log.d("AppUsage", "No hay estadísticas de uso disponibles.");
            return;
        }

        for (UsageStats usageStats : usageStatsList) {
            String packageName = usageStats.getPackageName();
            long totalTime = usageStats.getTotalTimeInForeground();

            Log.d("AppUsage", "Paquete encontrado: " + packageName + " - Uso: " + totalTime);

            switch (packageName) {
                case "com.instagram.android":
                    instagramUsageTimeToday = totalTime;
                    break;
                case "com.zhiliaoapp.musically": // TikTok
                    tiktokUsageTimeToday = totalTime;
                    break;
                case "com.google.android.youtube":
                    youtubeUsageTimeToday = totalTime;
                    break;
                case "com.twitter.android":
                    twitterUsageTimeToday = totalTime;
                    break;
                case "com.facebook.katana":
                    facebookUsageTimeToday = totalTime;
                    break;
            }
        }

        Calendar calendar = Calendar.getInstance();
        int currentWeek = calendar.get(Calendar.WEEK_OF_YEAR);

        SharedPreferences prefs = getSharedPreferences("AppUsageData", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();

        String today = new SimpleDateFormat("EEE", Locale.getDefault()).format(new Date());

        Log.d("AppUsage", "Hoy es: " + today);

        String weekKey = "Week" + currentWeek + "_" + today + "_";

        editor.putLong(weekKey + "Total", totalTimeToday);
        editor.putLong(weekKey + "Instagram", instagramUsageTimeToday);
        editor.putLong(weekKey + "TikTok", tiktokUsageTimeToday);
        editor.putLong(weekKey + "YouTube", youtubeUsageTimeToday);
        editor.putLong(weekKey + "Twitter", twitterUsageTimeToday);
        editor.putLong(weekKey + "Facebook", facebookUsageTimeToday);

        editor.apply();

        updateUsageSummary(currentWeek);
        initializeGraph(currentWeek);
    }

    /**
     * Obtiene el timestamp del inicio del día (medianoche).
     */
    public long getStartOfDay() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        return calendar.getTimeInMillis();
    }

    private String formatTime(long timeInMillis) {
        long minutes = (timeInMillis / 1000) / 60;
        long hours = minutes / 60;
        minutes = minutes % 60;

        return String.format("%d h %02d min", hours, minutes);
    }
    private void cambiarImagenDePerfil() {
        // Check if permission is needed (for API levels below 29)
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.Q) {
            if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                // Request permission
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, REQUEST_CODE_READ_EXTERNAL_STORAGE);
            } else {
                // Permission already granted, open gallery
                openGallery();
            }
        } else {
            // No permission needed for API level 29 and above, open gallery
            openGallery();
        }
    }

    private void openGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        galleryLauncher.launch(intent);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CODE_READ_EXTERNAL_STORAGE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission granted, open gallery
                openGallery();
            } else {
                // Permission denied
                Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT).show();
            }
        }
    }
}

