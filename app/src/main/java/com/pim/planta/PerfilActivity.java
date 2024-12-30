package com.pim.planta;

import android.app.usage.UsageStats;
import android.app.usage.UsageStatsManager;
import android.content.Intent;
import android.content.SharedPreferences;
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

import com.github.mikephil.charting.buffer.BarBuffer;
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

    private long instagramUsageTime = 0;
    private long tiktokUsageTime = 0;
    private long youtubeUsageTime = 0;
    private long twitterUsageTime = 0;
    private long facebookUsageTime = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        initializeNameAndProfile();
        //initializeGraph();
        setUpBottom();
        textViewPlantoo = findViewById(R.id.textView4);
        trackAppUsage();
        textViewText = findViewById(R.id.textView8);
        textViewText.setText("Bloomed on 14th November 2024");
        textViewText2 = findViewById(R.id.textView9);
        textViewText2.setText("Scientific plant name: Tulipa\n Nicname: Nacho de Tulipán.");

    }

    private void initializeGraph() {
        barChart = findViewById(R.id.bar_chart);

        // Obtener los tiempos de uso por día desde SharedPreferences
        SharedPreferences prefs = getSharedPreferences("AppUsageData", MODE_PRIVATE);

        // Usamos los datos de uso de cada red social para cada día de la semana
        float[][] appUsagePerDay = new float[7][5];  // 7 días de la semana, 5 aplicaciones

        // Los días de la semana en orden clásico (fijo)
        String[] daysOfWeek = {"Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"};

        // Obtener el día actual
        Calendar calendar = Calendar.getInstance();
        int currentDayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);  // Domingo = 1, Lunes = 2, ..., Sábado = 7

        // Cargar los datos de uso para cada aplicación y cada día de la semana
        for (int i = 0; i < 7; i++) {
            String day = daysOfWeek[i];

            if (i == currentDayOfWeek - 2) {  // Restamos 2 para alinear el día de la semana con el índice
                appUsagePerDay[i][0] = prefs.getLong(day + "_Instagram", 0) / 60f;
                appUsagePerDay[i][1] = prefs.getLong(day + "_TikTok", 0) / 60f;
                appUsagePerDay[i][2] = prefs.getLong(day + "_YouTube", 0) / 60f;
                appUsagePerDay[i][3] = prefs.getLong(day + "_Twitter", 0) / 60f;
                appUsagePerDay[i][4] = prefs.getLong(day + "_Facebook", 0) / 60f;
            } else {
                appUsagePerDay[i][0] = 0;
                appUsagePerDay[i][1] = 0;
                appUsagePerDay[i][2] = 0;
                appUsagePerDay[i][3] = 0;
                appUsagePerDay[i][4] = 0;
            }
        }

        ArrayList<BarEntry> barEntries = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            barEntries.add(new BarEntry(i, new float[]{
                    appUsagePerDay[i][0],  // Instagram
                    appUsagePerDay[i][1],  // TikTok
                    appUsagePerDay[i][2],  // YouTube
                    appUsagePerDay[i][3],  // Twitter
                    appUsagePerDay[i][4]   // Facebook
            }));
        }

        // Crear el conjunto de datos para las barras apiladas
        BarDataSet barDataSet = new BarDataSet(barEntries, "");
        barDataSet.setStackLabels(new String[]{"Instagram", "TikTok", "YouTube", "Twitter", "Facebook"});
        barDataSet.setColors(new int[]{
                Color.parseColor("#1B5E20"),  // Instagram
                Color.parseColor("#388E3C"),  // TikTok
                Color.parseColor("#66BB6A"),  // YouTube
                Color.parseColor("#81C784"),  // Twitter
                Color.parseColor("#A5D6A7")   // Facebook
        });

        BarData barData = new BarData(barDataSet);
        barData.setBarWidth(0.5f);
        barChart.setData(barData);

        XAxis xAxis = barChart.getXAxis();
        xAxis.setValueFormatter(new IndexAxisValueFormatter(daysOfWeek));  // Usar los días fijos
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setGranularity(1f);

        barChart.getAxisLeft().setAxisMinimum(0f);
        barChart.getAxisRight().setEnabled(false);
        barChart.getDescription().setEnabled(false);
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

        // Acumular el tiempo de uso de cada aplicación
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

        // Guardar el tiempo de uso en SharedPreferences para cada red social
        SharedPreferences prefs = getSharedPreferences("AppUsageData", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();

        // Usamos el día de la semana como clave para guardar los datos (por ejemplo: "Mon", "Tue")
        String today = new SimpleDateFormat("EEE", Locale.getDefault()).format(new Date());
        editor.putLong(today + "_Instagram", instagramUsageTimeToday);
        editor.putLong(today + "_TikTok", tiktokUsageTimeToday);
        editor.putLong(today + "_YouTube", youtubeUsageTimeToday);
        editor.putLong(today + "_Twitter", twitterUsageTimeToday);
        editor.putLong(today + "_Facebook", facebookUsageTimeToday);

        editor.apply();

        // Actualizar el resumen de uso en la UI (opcional)
        updateUsageSummary();

        // Inicializar o actualizar el gráfico
        initializeGraph();
    }

    private void updateUsageSummary() {
        // Obtener el tiempo de uso desde SharedPreferences
        SharedPreferences prefs = getSharedPreferences("AppUsageData", MODE_PRIVATE);

        // Obtener los tiempos de uso para el día actual (por ejemplo, "Mon" para Lunes)
        String today = new SimpleDateFormat("EEE", Locale.getDefault()).format(new Date());

        long instagramUsage = prefs.getLong(today + "_Instagram", 0);
        long tiktokUsage = prefs.getLong(today + "_TikTok", 0);
        long youtubeUsage = prefs.getLong(today + "_YouTube", 0);
        long twitterUsage = prefs.getLong(today + "_Twitter", 0);
        long facebookUsage = prefs.getLong(today + "_Facebook", 0);

        // Mostrar el resumen en el TextView
        String usageSummary = String.format(
                "\n%-15s    %s\n" +
                        "%-15s        %s\n" +
                        "%-15s     %s\n" +
                        "%-15s        %s\n" +
                        "%-15s    %s",
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

    private String formatTime(long timeInMillis) {
        // Convertir de milisegundos a segundos
        long seconds = timeInMillis / 1000;
        long minutes = (seconds / 60) % 60; // Calcular minutos
        long hours = (seconds / 3600); // Calcular horas
        seconds = seconds % 60; // Los segundos restantes

        // Formatear el tiempo como hh:mm:ss
        return String.format("%02d:%02d:%02d", hours, minutes, seconds);
    }


}

