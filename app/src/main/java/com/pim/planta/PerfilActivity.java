package com.pim.planta;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.app.usage.UsageStats;
import android.app.usage.UsageStatsManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
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
import com.pim.planta.models.UserLogged;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;


public class PerfilActivity extends AppCompatActivity{

    private ImageView profileImageView;
    private TextView userNameTextView;
    private BarChart barChart;
    private List<BarDataSet> dataSets;
    public static int currentImageIndex = 1;
    private int previousImageIndex = -1;
    private TextView textViewPlantoo;
    private TextView textViewText;
    private TextView textViewText2;

    private boolean isExpanded = false;

    private long instagramUsageTime = 0;
    private long tiktokUsageTime = 0;
    private long youtubeUsageTime = 0;
    private long twitterUsageTime = 0;
    private long facebookUsageTime = 0;

    private View bottomSection;
    private ImageView imageView12;
    private boolean isAtTop = false;

    private FrameLayout frame;
    private ImageView profileImage;
    private ImageView imageView13;
    private TextView userName;
    private TextView textView8;
    private TextView textView9;
    private int currentWeek;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        initializeNameAndProfile();
        //initializeGraph();
        setUpBottom();
        textViewPlantoo = findViewById(R.id.textView4);
        trackAppUsage2();
        textViewText = findViewById(R.id.textView8);
        textViewText.setText("Bloomed on 14th November 2024");
        textViewText2 = findViewById(R.id.textView9);
        textViewText2.setText("Scientific plant name: Tulipa\n Nicname: Nacho de Tulipán.");
        imageView12 = findViewById(R.id.imageView12);
        frame = findViewById(R.id.frame);
        profileImage = findViewById(R.id.profile_image);
        imageView13 = findViewById(R.id.imageView13);
        userName = findViewById(R.id.user_name);
        textView8 = findViewById(R.id.textView8);
        textView9 = findViewById(R.id.textView9);
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
                    textView8.setVisibility(View.VISIBLE);
                    textView9.setVisibility(View.VISIBLE);
                } else {
                    // Ocultar los elementos
                    profileImage.setVisibility(View.GONE);
                    imageView13.setVisibility(View.GONE);
                    userName.setVisibility(View.GONE);
                    textView8.setVisibility(View.GONE);
                    textView9.setVisibility(View.GONE);
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
        xAxis.setTypeface(aventaFont);
        xAxis.setTextColor(Color.BLACK);
        xAxis.setGranularity(1f);
        xAxis.setDrawGridLines(false);

        YAxis leftAxis = barChart.getAxisLeft();
        leftAxis.setTypeface(aventaFont);
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
        legend.setTypeface(aventaFont);
        legend.setTextColor(Color.BLACK);

        barChart.animateY(1000, Easing.EaseInOutCubic);
        barChart.setFitBars(true);
        barChart.invalidate();
    }


    private void initializeNameAndProfile(){
        profileImageView = findViewById(R.id.profile_image);
        userNameTextView = findViewById(R.id.user_name);

        profileImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cambiarImagenDePerfil();
            }
        });
        userNameTextView.setText(UserLogged.getInstance().getCurrentUser().getUsername());
    }

    private  BarDataSet addColorOnGraph(ArrayList<BarEntry> barEntries){
        BarDataSet barDataSet = new BarDataSet(barEntries, "Progreso Diario");
        barDataSet.setColors(new int[]{Color.RED, Color.BLUE, Color.YELLOW, Color.GREEN, Color.MAGENTA}); // Colores de la barra
        return barDataSet;
    }

    private void cambiarImagenDePerfil() {
        Toast.makeText(this, "Cambiar imagen de perfil", Toast.LENGTH_SHORT).show();

        // Aquí puedes abrir una actividad para seleccionar una imagen o usar un selector de imágenes
        // Por ejemplo, puedes abrir la galería con una Intent:
        // Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        // startActivityForResult(intent, 100);

        profileImageView.setImageResource(R.drawable.default_profile);
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

    private void trackAppUsage() {
        UsageStatsManager usageStatsManager = (UsageStatsManager) getSystemService(USAGE_STATS_SERVICE);
        if (usageStatsManager == null) {
            Log.e("AppUsage", "UsageStatsManager no está disponible.");
            return;
        }

        // Obtén el tiempo total de uso de hoy usando la función
        long totalTimeToday = getTotalUsageToday();
        Log.d("AppUsage", "Total Time Today: " + totalTimeToday);

        // Inicializar el uso de las aplicaciones para hoy
        long instagramUsageTimeToday = 0;
        long tiktokUsageTimeToday = 0;
        long youtubeUsageTimeToday = 0;
        long twitterUsageTimeToday = 0;
        long facebookUsageTimeToday = 0;

        // Obtén el inicio y final del día de hoy
        long startOfDay = getStartOfDay();
        long endOfDay = System.currentTimeMillis();

        // Consulta las estadísticas de uso para el día de hoy
        List<UsageStats> usageStatsList = usageStatsManager.queryUsageStats(
                UsageStatsManager.INTERVAL_DAILY, startOfDay, endOfDay);

        if (usageStatsList == null || usageStatsList.isEmpty()) {
            Log.d("AppUsage", "No hay estadísticas de uso disponibles.");
            return;
        }

        // Recorrer las estadísticas de uso
        for (UsageStats usageStats : usageStatsList) {
            String packageName = usageStats.getPackageName();
            Log.d("AppUsage", "Paquete encontrado: " + packageName);  // Log para depurar

            switch (packageName) {
                case "com.instagram.android":
                    instagramUsageTimeToday = usageStats.getTotalTimeInForeground();
                    break;
                case "com.zhiliaoapp.musically":
                    tiktokUsageTimeToday = usageStats.getTotalTimeInForeground();
                    break;
                case "com.google.android.youtube":
                    youtubeUsageTimeToday = usageStats.getTotalTimeInForeground();
                    break;
                case "com.twitter.android":
                    twitterUsageTimeToday = usageStats.getTotalTimeInForeground();
                    break;
                case "com.facebook.katana":
                    facebookUsageTimeToday = usageStats.getTotalTimeInForeground();
                    break;
            }
        }
        Calendar calendar = Calendar.getInstance();
        int currentWeek = calendar.get(Calendar.WEEK_OF_YEAR);

        // Guarda los datos en SharedPreferences
        SharedPreferences prefs = getSharedPreferences("AppUsageData", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();

        // Obtén el día actual y formatea la clave de SharedPreferences
        String today = new SimpleDateFormat("EEE", Locale.getDefault()).format(new Date());
        Log.d("AppUsage", "Hoy es: " + today);  // Log para depurar

        /*editor.putLong(today + "_Total", totalTimeToday);
        editor.putLong(today + "_Instagram", instagramUsageTimeToday);
        editor.putLong(today + "_TikTok", tiktokUsageTimeToday);
        editor.putLong(today + "_YouTube", youtubeUsageTimeToday);
        editor.putLong(today + "_Twitter", twitterUsageTimeToday);
        editor.putLong(today + "_Facebook", facebookUsageTimeToday);
*/
        String weekKey = "Week" + currentWeek + "_" + today + "_";

        editor.putLong(weekKey + "Total", totalTimeToday);
        editor.putLong(weekKey + "Instagram", instagramUsageTimeToday);
        editor.putLong(weekKey + "TikTok", tiktokUsageTimeToday);
        editor.putLong(weekKey + "YouTube", youtubeUsageTimeToday);
        editor.putLong(weekKey + "Twitter", twitterUsageTimeToday);
        editor.putLong(weekKey + "Facebook", facebookUsageTimeToday);

        editor.apply();

        // Actualiza la UI con los datos guardados
        //updateUsageSummary();
        //initializeGraph();
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
    private void resetTodayUsage(SharedPreferences.Editor editor) {
        Calendar calendar = Calendar.getInstance();
        int currentWeek = calendar.get(Calendar.WEEK_OF_YEAR);
        String today = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());

        String weekKey = "Week" + currentWeek + "_" + today + "_";

        editor.putLong(weekKey + "Instagram", 0);
        editor.putLong(weekKey + "TikTok", 0);
        editor.putLong(weekKey + "YouTube", 0);
        editor.putLong(weekKey + "Twitter", 0);
        editor.putLong(weekKey + "Facebook", 0);
    }
    private long getStartOfDayUTC() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        return calendar.getTimeInMillis();
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



    private String getAppNameByIndex(int index) {
        switch (index) {
            case 0:
                return "Instagram";
            case 1:
                return "TikTok";
            case 2:
                return "YouTube";
            case 3:
                return "Twitter";
            case 4:
                return "Facebook";
            default:
                return "Unknown";
        }
    }


    private List<String> getTrackedApps() {
        return Arrays.asList("com.instagram.android", "com.tiktok.android", "com.facebook.katana");
    }

    private String getDayLabel(int dayIndex) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, -dayIndex);
        SimpleDateFormat dateFormat = new SimpleDateFormat("EEE", Locale.getDefault());
        return dateFormat.format(calendar.getTime());
    }

    private String getAppName(String packageName) {
        PackageManager pm = getPackageManager();
        try {
            ApplicationInfo info = pm.getApplicationInfo(packageName, 0);
            return pm.getApplicationLabel(info).toString();
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return packageName;
        }
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

    /**
     * Muestra los datos semanales en la UI.
     */
    private void updateUsageSummaryWeekly() {
        SharedPreferences prefs = getSharedPreferences("AppUsageData", MODE_PRIVATE);

        String[] packages = {
                "com.instagram.android",
                "com.zhiliaoapp.musically",
                "com.google.android.youtube",
                "com.twitter.android",
                "com.facebook.katana"
        };

        for (String packageName : packages) {
            for (int i = 0; i < 7; i++) {
                long usageTime = prefs.getLong(packageName + "_Day" + i, 0);
                Log.d("WeeklyUsage", "App: " + packageName + " | Day " + i + ": " + usageTime + " ms");
            }
        }
    }


    private String formatTime(long timeInMillis) {
        long minutes = (timeInMillis / 1000) / 60;
        long hours = minutes / 60;
        minutes = minutes % 60;

        return String.format("%d h %02d min", hours, minutes);
    }



}

