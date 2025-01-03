package com.pim.planta;

import android.app.usage.UsageStats;
import android.app.usage.UsageStatsManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.RectF;
import android.graphics.Paint;
import android.graphics.Path;

import android.os.Bundle;
import android.text.Html;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.graphics.Canvas;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.buffer.BarBuffer;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.utils.Transformer;
import com.github.mikephil.charting.utils.ViewPortHandler;


import androidx.appcompat.app.AppCompatActivity;

import com.github.mikephil.charting.animation.ChartAnimator;
import com.github.mikephil.charting.charts.BarChart;
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
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;


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

    private long instagramUsageTime = 0;
    private long tiktokUsageTime = 0;
    private long youtubeUsageTime = 0;
    private long twitterUsageTime = 0;
    private long facebookUsageTime = 0;

    private View bottomSection;
    private ImageView imageView12;
    private boolean isAtTop = false;

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

    }

    private void initializeGraph() {
        barChart = findViewById(R.id.bar_chart);

        SharedPreferences prefs = getSharedPreferences("AppUsageData", MODE_PRIVATE);

        float[][] appUsagePerDay = new float[7][5]; // 7 días de la semana, 5 aplicaciones

        String[] daysOfWeek = {"Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"};

        Calendar calendar = Calendar.getInstance();
        int currentDayOfWeek = calendar.get(Calendar.DAY_OF_WEEK); // Domingo = 1, Lunes = 2, ..., Sábado = 7

        for (int i = 0; i < 7; i++) {
            String day = daysOfWeek[i];
            for (int j = 0; j < 5; j++) { // Recorrer las 5 aplicaciones
                String appKey = day + "_" + (j == 0 ? "Instagram" : j == 1 ? "TikTok" : j == 2 ? "YouTube" : j == 3 ? "Twitter" : "Facebook");
                appUsagePerDay[i][j] = prefs.getLong(appKey, 0) / 60f;
            }
        }

        ArrayList<BarEntry> barEntries = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            barEntries.add(new BarEntry(i, appUsagePerDay[i]));
        }

        BarDataSet barDataSet = new BarDataSet(barEntries, "");
        barDataSet.setStackLabels(new String[]{"Insta", "TikTok", "YouTube", "Twitter", "Face"});

        barDataSet.setColors(new int[]{
                Color.parseColor("#004D40"), // Instagram - Verde muy oscuro (más azulado)
                Color.parseColor("#2E7D32"), // TikTok - Verde oscuro
                Color.parseColor("#4CAF50"), // YouTube - Verde brillante
                Color.parseColor("#81C784"), // Twitter - Verde claro
                Color.parseColor("#A5D6A7")  // Facebook - Verde muy claro
        });

        BarData barData = new BarData(barDataSet);
        barData.setBarWidth(0.5f);
        barChart.setData(barData);

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
        legend.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM); // Posición en la parte inferior
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER); // Centrada horizontalmente
        legend.setOrientation(Legend.LegendOrientation.HORIZONTAL); // Orientación horizontal
        legend.setDrawInside(false); // Evitar que se superponga con el gráfico
        legend.setTextSize(12f);
        legend.setTextColor(Color.BLACK);

        barChart.animateY(1000, Easing.EaseInOutCubic);

        barChart.setFitBars(true);
        barChart.invalidate();
    }


/*
* private void initializeGraph() {
        barChart = findViewById(R.id.bar_chart);

        ArrayList<BarEntry> barEntries = new ArrayList<>();
        barEntries.add(new BarEntry(0, new float[]{2f, 3f, 1f, 4f, 5f}));
        barEntries.add(new BarEntry(1, new float[]{3f, 1f, 2f, 7f, 1f}));
        barEntries.add(new BarEntry(2, new float[]{4f, 2f, 3f, 6f, 1f}));
        barEntries.add(new BarEntry(3, new float[]{5f, 3f, 2f, 4f, 2f}));
        barEntries.add(new BarEntry(4, new float[]{3f, 4f, 1f, 5f, 2f}));
        barEntries.add(new BarEntry(5, new float[]{1f, 2f, 3f, 4f, 3f}));
        barEntries.add(new BarEntry(6, new float[]{2f, 3f, 2f, 5f, 4f}));

        BarDataSet barDataSet = new BarDataSet(barEntries, "App Usage");
        barDataSet.setStackLabels(new String[]{"WhatsApp", "YouTube", "Instagram", "Facebook", "TikTok"});
        barDataSet.setColors(new int[]{
                Color.parseColor("#1B5E20"),
                Color.parseColor("#388E3C"),
                Color.parseColor("#66BB6A"),
                Color.parseColor("#81C784"),
                Color.parseColor("#A5D6A7")
        });

        BarData barData = new BarData(barDataSet);
        barData.setBarWidth(0.5f);
        barChart.setData(barData);

        XAxis xAxis = barChart.getXAxis();
        xAxis.setValueFormatter(new IndexAxisValueFormatter(new String[]{"Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"}));
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(false);
        xAxis.setGranularity(1f);
        xAxis.setGranularityEnabled(true);

        YAxis yAxisLeft = barChart.getAxisLeft();
        yAxisLeft.setAxisMinimum(0f);
        barChart.getAxisRight().setEnabled(false);

        barChart.getDescription().setEnabled(false);
        barChart.setFitBars(true);
        barChart.getLegend().setEnabled(true);

        barChart.invalidate();  // Refresca el gráfico
    }
* */

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

    public void setUpBottom(){
        ImageButton imageButtonLupa = findViewById(R.id.imageButtonLupa);
        ImageButton imageButtonMaceta = findViewById(R.id.imageButtonMaceta);
        ImageButton imageButtonPlantadex = findViewById(R.id.imageButtonPlantadex);
        ImageButton imageButtonUsuario = findViewById(R.id.imageButtonUsuario);
        imageButtonUsuario.setEnabled(false); // Deshabilita el boton
        imageButtonUsuario.setImageAlpha(128); // Oscurece el boton

        imageButtonLupa.setOnClickListener(v -> {
            Intent intent = new Intent(PerfilActivity.this, DiaryActivity.class);
            startActivity(intent);
        });
        imageButtonPlantadex.setOnClickListener(v -> {
            Intent intent = new Intent(PerfilActivity.this, PlantListActivity.class);
            startActivity(intent);
        });
        imageButtonMaceta.setOnClickListener(view -> {
            Intent intent = new Intent(PerfilActivity.this, JardinActivity.class);
            startActivity(intent);
        });
    }

    public long getTotalUsageToday() {
        UsageStatsManager usageStatsManager = (UsageStatsManager) getSystemService(Context.USAGE_STATS_SERVICE);

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        long startOfDay = calendar.getTimeInMillis();
        long endOfDay = System.currentTimeMillis();

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

        long currentTime = System.currentTimeMillis();
        List<UsageStats> usageStatsList = usageStatsManager.queryUsageStats(
                UsageStatsManager.INTERVAL_DAILY, currentTime - 86400000, currentTime);

        if (usageStatsList == null || usageStatsList.isEmpty()) {
            Log.d("AppUsage", "No hay estadísticas de uso disponibles.");
            return;
        }

        // Inicializar el uso de las aplicaciones para hoy
        long instagramUsageTimeToday = 0;
        long tiktokUsageTimeToday = 0;
        long youtubeUsageTimeToday = 0;
        long twitterUsageTimeToday = 0;
        long facebookUsageTimeToday = 0;

        for (UsageStats usageStats : usageStatsList) {
            String packageName = usageStats.getPackageName();
            long totalTimeInForeground = usageStats.getTotalTimeInForeground();

            switch (packageName) {
                case "com.instagram.android":
                    instagramUsageTimeToday += totalTimeInForeground;
                    break;
                case "com.zhiliaoapp.musically":
                    tiktokUsageTimeToday += totalTimeInForeground;
                    break;
                case "com.google.android.youtube":
                    youtubeUsageTimeToday += totalTimeInForeground;
                    break;
                case "com.twitter.android":
                    twitterUsageTimeToday += totalTimeInForeground;
                    break;
                case "com.facebook.katana":
                    facebookUsageTimeToday += totalTimeInForeground;
                    break;
            }
        }

        SharedPreferences prefs = getSharedPreferences("AppUsageData", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();

        String today = new SimpleDateFormat("EEE", Locale.getDefault()).format(new Date());

        if (instagramUsageTimeToday > 0) {
            editor.putLong(today + "_Instagram", instagramUsageTimeToday);
        } else {
            editor.putLong(today + "_Instagram", 0);
        }

        if (tiktokUsageTimeToday > 0) {
            editor.putLong(today + "_TikTok", tiktokUsageTimeToday);
        } else {
            editor.putLong(today + "_TikTok", 0);
        }

        if (youtubeUsageTimeToday > 0) {
            editor.putLong(today + "_YouTube", youtubeUsageTimeToday);
        } else {
            editor.putLong(today + "_YouTube", 0);
        }

        if (twitterUsageTimeToday > 0) {
            editor.putLong(today + "_Twitter", twitterUsageTimeToday);
        } else {
            editor.putLong(today + "_Twitter", 0);
        }

        if (facebookUsageTimeToday > 0) {
            editor.putLong(today + "_Facebook", facebookUsageTimeToday);
        } else {
            editor.putLong(today + "_Facebook", 0);
        }

        editor.apply();

        updateUsageSummary();

        initializeGraph();
    }

    private void trackAppUsage2() {
        UsageStatsManager usageStatsManager = (UsageStatsManager) getSystemService(USAGE_STATS_SERVICE);
        if (usageStatsManager == null) {
            Log.e("AppUsage", "UsageStatsManager no está disponible.");
            return;
        }

        long totalTimeToday = getTotalUsageToday();  // Usamos la función para obtener el uso total del día de hoy

        // Inicializar el uso de las aplicaciones para hoy
        long instagramUsageTimeToday = 0;
        long tiktokUsageTimeToday = 0;
        long youtubeUsageTimeToday = 0;
        long twitterUsageTimeToday = 0;
        long facebookUsageTimeToday = 0;

        List<UsageStats> usageStatsList = usageStatsManager.queryUsageStats(
                UsageStatsManager.INTERVAL_DAILY, System.currentTimeMillis() - 86400000, System.currentTimeMillis());

        if (usageStatsList == null || usageStatsList.isEmpty()) {
            Log.d("AppUsage", "No hay estadísticas de uso disponibles.");
            return;
        }

        // Recorrer las estadísticas de uso
        for (UsageStats usageStats : usageStatsList) {
            String packageName = usageStats.getPackageName();

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

        SharedPreferences prefs = getSharedPreferences("AppUsageData", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();

        String today = new SimpleDateFormat("EEE", Locale.getDefault()).format(new Date());

        editor.putLong(today + "_Instagram", instagramUsageTimeToday);
        editor.putLong(today + "_TikTok", tiktokUsageTimeToday);
        editor.putLong(today + "_YouTube", youtubeUsageTimeToday);
        editor.putLong(today + "_Twitter", twitterUsageTimeToday);
        editor.putLong(today + "_Facebook", facebookUsageTimeToday);

        editor.apply();

        updateUsageSummary();
        initializeGraph();
    }

    private void updateUsageSummary() {
        SharedPreferences prefs = getSharedPreferences("AppUsageData", MODE_PRIVATE);

        String today = new SimpleDateFormat("EEE", Locale.getDefault()).format(new Date());

        long instagramUsage = prefs.getLong(today + "_Instagram", 0);
        long tiktokUsage = prefs.getLong(today + "_TikTok", 0);
        long youtubeUsage = prefs.getLong(today + "_YouTube", 0);
        long twitterUsage = prefs.getLong(today + "_Twitter", 0);
        long facebookUsage = prefs.getLong(today + "_Facebook", 0);

        // Mostrar el resumen en el TextView
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

        // Verifica si el TextView está inicializado y actualiza el texto
        if (textViewPlantoo != null) {
            textViewPlantoo.setText(usageSummary);
        } else {
            Log.e("AppUsage", "TextView no está inicializado.");
        }
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
    private long getStartOfDay(long currentTime) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(currentTime);
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
        long seconds = timeInMillis / 1000;
        long minutes = (seconds / 60) % 60;
        long hours = (seconds / 3600);

        return String.format("%d h %02d min", hours, minutes);
    }



}

