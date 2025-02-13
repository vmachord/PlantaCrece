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
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
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
import com.pim.planta.models.AppUsage;
import com.pim.planta.models.Plant;
import com.pim.planta.models.UserLogged;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


        public class PerfilActivity extends NotificationActivity {

            private ImageView profileImageView;
            private TextView userNameTextView;
            private BarChart barChart;
            private TextView textViewPlantoo;
            private TextView textViewText;
            private TextView textViewText2;
            private TextView textViewText3;
            private int currentWeek;
            private Plant plant;
            private DAO dao;
            private static final int REQUEST_CODE_READ_EXTERNAL_STORAGE = 100;
            private ActivityResultLauncher<Intent> galleryLauncher;
            private static final String PROFILE_PREFS = "profile_prefs";
            private static final String PROFILE_IMAGE_PATH_KEY = "profile_image_path";


            @Override
            protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_profile);
                initializeNameAndProfile();
                //initializeGraph();
                setUpBottom();
                textViewPlantoo = findViewById(R.id.textView4);
                textViewPlantoo.setTextAlignment(TextView.TEXT_ALIGNMENT_CENTER);
                trackAppUsage2();
                textViewText = findViewById(R.id.textCreationDate);
                textViewText.setText("Bloomed on: " + UserLogged.getInstance().getCurrentUser().getFormattedCreationDate());
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
                        textViewText3.setText("Plant nickname: " + plant.getNickname());
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
                                            // Save the image to internal storage and get the file path
                                            String imagePath = saveImageToInternalStorage(selectedImage);

                                            if (imagePath != null) {
                                                // Save the file path to SharedPreferences
                                                SharedPreferences prefs = getSharedPreferences(PROFILE_PREFS, MODE_PRIVATE);
                                                prefs.edit().putString(PROFILE_IMAGE_PATH_KEY, imagePath).apply();
                                            }
                                        } catch (FileNotFoundException e) {
                                            e.printStackTrace();
                                            Toast.makeText(this, "Error loading image", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                }
                            }
                        }
                );
                // Load the image from internal storage
                Bitmap savedImage = loadImageFromInternalStorage();
                if (savedImage != null) {
                    profileImageView.setImageBitmap(savedImage);
                } else {
                    // Set a default image if no image is saved
                    profileImageView.setImageResource(R.drawable.image_tulipan);
                }
            }

            private void updateGraphAndData(int selectedWeek) {
                initializeGraph(selectedWeek);
                updateUsageSummary(selectedWeek);
            }

            @Override
            public void onResume() {
                super.onResume();
                trackAppUsage2();
            }

            private void initializeGraph(int selectedWeek) {
                Log.d("AppUsage", "initializeGraph() called for week: " + selectedWeek);

                barChart = findViewById(R.id.bar_chart);
                if (barChart == null) {
                    Log.e("AppUsage", "BarChart is null!");
                    return;
                }

                // Data structures for the graph
                float[][] appUsagePerDay = new float[7][5];
                String[] daysOfWeek = {"Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"};
                String[] appNames = {"Instagram", "TikTok", "YouTube", "Twitter", "Facebook"};

                Calendar calendar = Calendar.getInstance();
                calendar.set(Calendar.WEEK_OF_YEAR, selectedWeek);
                calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);

                dao = PlantRepository.getInstance(this).getPlantaDAO();

                // Run database operations in a background thread
                DatabaseExecutor.execute(() -> {
                    // Loop through each day of the week
                    for (int i = 0; i < 7; i++) {
                        Date currentDate = calendar.getTime();
                        int currentDayOfYear = calendar.get(Calendar.DAY_OF_YEAR);
                        // Loop through each app
                        for (int j = 0; j < 5; j++) {
                            String appName = appNames[j];
                            AppUsage usage = dao.getUsageByDayOfYearAndApp(currentDayOfYear, appName);
                            if (usage != null) {
                                appUsagePerDay[i][j] = usage.usageTime / 3600000f; // Convert milliseconds to hours
                            } else {
                                appUsagePerDay[i][j] = 0f; // No usage for this app on this day
                            }
                            Log.d("AppUsage", "Loading data for: " + currentDate + " - " + appName + " -> " + appUsagePerDay[i][j] + " hours");
                        }
                        calendar.add(Calendar.DAY_OF_YEAR, 1);
                    }

                    // Prepare data for the bar chart (must be done on the main thread)
                    runOnUiThread(() -> {
                        ArrayList<BarEntry> barEntries = new ArrayList<>();
                        for (int i = 0; i < 7; i++) {
                            float[] dailyUsage = new float[5];
                            for (int j = 0; j < 5; j++) {
                                dailyUsage[j] = appUsagePerDay[i][j];
                            }
                            barEntries.add(new BarEntry(i, dailyUsage));
                        }

                        // Configure the bar chart
                        BarDataSet barDataSet = new BarDataSet(barEntries, "App Usage");
                        barDataSet.setStackLabels(appNames);
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

                        // Customize the chart's appearance
                        Typeface aventaFont = ResourcesCompat.getFont(this, R.font.aventa);

                        XAxis xAxis = barChart.getXAxis();
                        xAxis.setValueFormatter(new IndexAxisValueFormatter(daysOfWeek));
                        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
                        xAxis.setTextSize(12f);
                        xAxis.setTextColor(Color.BLACK);
                        xAxis.setGranularity(1f);
                        xAxis.setDrawGridLines(false);
                        if (aventaFont != null) {
                            xAxis.setTypeface(aventaFont);
                        }

                        YAxis leftAxis = barChart.getAxisLeft();
                        leftAxis.setAxisMinimum(0f);
                        leftAxis.setTextSize(12f);
                        leftAxis.setTextColor(Color.BLACK);
                        if (aventaFont != null) {
                            leftAxis.setTypeface(aventaFont);
                        }

                        barChart.getAxisRight().setEnabled(false);

                        Legend legend = barChart.getLegend();
                        legend.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
                        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);
                        legend.setOrientation(Legend.LegendOrientation.HORIZONTAL);
                        legend.setDrawInside(false);
                        legend.setTextSize(12f);
                        legend.setTextColor(Color.BLACK);
                        if (aventaFont != null) {
                            legend.setTypeface(aventaFont);
                        }

                        // Animate and refresh the chart
                        barChart.animateY(1000, Easing.EaseInOutCubic);
                        barChart.setFitBars(true);
                        barChart.invalidate();
                    });
                });
            }

            private void initializeNameAndProfile() {
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

            public void setUpBottom() {
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

            private void updateUsageSummary(int selectedWeek) {

                Calendar calendar = Calendar.getInstance();
                calendar.set(Calendar.WEEK_OF_YEAR, selectedWeek);
                calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);

                // Run database operations in a background thread
                DatabaseExecutor.execute(() -> {
                    long instagramUsage = 0;
                    long tiktokUsage = 0;
                    long youtubeUsage = 0;
                    long twitterUsage = 0;
                    long facebookUsage = 0;

                    List<AppUsage> usageList = dao.getUsageByWeek(selectedWeek);
                    for (AppUsage usage : usageList) {
                        switch (usage.appName) {
                            case "Instagram":
                                instagramUsage += usage.usageTime;
                                break;
                            case "TikTok":
                                tiktokUsage += usage.usageTime;
                                break;
                            case "YouTube":
                                youtubeUsage += usage.usageTime;
                                break;
                            case "Twitter":
                                twitterUsage += usage.usageTime;
                                break;
                            case "Facebook":
                                facebookUsage += usage.usageTime;
                                break;
                        }
                    }

                    // Update the UI on the main thread
                    long finalInstagramUsage = instagramUsage;
                    long finalTiktokUsage = tiktokUsage;
                    long finalYoutubeUsage = youtubeUsage;
                    long finalTwitterUsage = twitterUsage;
                    long finalFacebookUsage = facebookUsage;
                    runOnUiThread(() -> {
                        String usageSummary = String.format(
                                "\n%-4s %s\n" +
                                        "%-10s %s\n" +
                                        "%-7s %s\n" +
                                        "%-8s %s\n" +
                                        "%-8s %s\n" +
                                        "%-9s %s\n",
                                "Week", selectedWeek,
                                "Instagram:", formatTime(finalInstagramUsage),
                                "TikTok:", formatTime(finalTiktokUsage),
                                "YouTube:", formatTime(finalYoutubeUsage),
                                "Twitter:", formatTime(finalTwitterUsage),
                                "Facebook:", formatTime(finalFacebookUsage)
                        );

                        if (textViewPlantoo != null) {
                            textViewPlantoo.setText(usageSummary);
                        } else {
                            Log.e("AppUsage", "TextView no está inicializado.");
                        }
                    });
                });
            }

            public void trackAppUsage2() {
                UsageStatsManager usageStatsManager = (UsageStatsManager) getSystemService(Context.USAGE_STATS_SERVICE);
                if (usageStatsManager == null) {
                    Log.e("AppUsage", "UsageStatsManager is not available.");
                    return;
                }

                long currentAccessTime = System.currentTimeMillis();
                long lastAccessTime = getLastAccessTime();

                // Save the current access time for the next run
                saveLastAccessTime();

                // If it's the first time or the difference is negative, we don't have to do anything
                if (lastAccessTime == 0 || currentAccessTime < lastAccessTime) {
                    return;
                }

                // Run database operations in a background thread using DatabaseExecutor
                DatabaseExecutor.execute(() -> {
                    dao = PlantRepository.getInstance(this).getPlantaDAO();
                    processAppUsageData(usageStatsManager, lastAccessTime, currentAccessTime);
                });
            }

            private void processAppUsageData(UsageStatsManager usageStatsManager, long lastAccessTime, long currentAccessTime) {
                String[] appNames = {"Instagram", "TikTok", "YouTube", "Twitter", "Facebook"};
                String[] packageNames = {"com.instagram.android", "com.zhiliaoapp.musically", "com.google.android.youtube", "com.twitter.android", "com.facebook.katana"};

                Calendar calendar = Calendar.getInstance();
                int currentWeek = calendar.get(Calendar.WEEK_OF_YEAR);
                Date today = calendar.getTime();
                int currentDayOfYear = calendar.get(Calendar.DAY_OF_YEAR);

                for (int i = 0; i < appNames.length; i++) {
                    String appName = appNames[i];
                    String packageName = packageNames[i];

                    // Get the usage stats for the app between the last access time and the current access time
                    List<UsageStats> usageStatsList = usageStatsManager.queryUsageStats(
                            UsageStatsManager.INTERVAL_DAILY, lastAccessTime, currentAccessTime);

                    long totalUsageTimeInForeground = 0;
                    if (usageStatsList != null && !usageStatsList.isEmpty()) {
                        for (UsageStats usageStats : usageStatsList) {
                            if (usageStats.getPackageName().equals(packageName)) {
                                totalUsageTimeInForeground = usageStats.getTotalTimeInForeground();
                                break;
                            }
                        }
                    }

                    // Check if the session spans across midnight
                    Calendar lastAccessCalendar = Calendar.getInstance();
                    lastAccessCalendar.setTimeInMillis(lastAccessTime);
                    Calendar currentAccessCalendar = Calendar.getInstance();
                    currentAccessCalendar.setTimeInMillis(currentAccessTime);

                    if (lastAccessCalendar.get(Calendar.DAY_OF_YEAR) != currentAccessCalendar.get(Calendar.DAY_OF_YEAR)) {
                        // Session spans across midnight
                        // Calculate the time until midnight on the last access day
                        Calendar midnightCalendar = (Calendar) lastAccessCalendar.clone();
                        midnightCalendar.set(Calendar.HOUR_OF_DAY, 23);
                        midnightCalendar.set(Calendar.MINUTE, 59);
                        midnightCalendar.set(Calendar.SECOND, 59);
                        midnightCalendar.set(Calendar.MILLISECOND, 999);
                        long timeUntilMidnight = midnightCalendar.getTimeInMillis() - lastAccessTime;
                        int lastAccessDayOfYear = lastAccessCalendar.get(Calendar.DAY_OF_YEAR);

                        // Calculate the time from midnight on the current access day
                        long timeFromMidnight = currentAccessTime - midnightCalendar.getTimeInMillis();
                        int currentAccessDayOfYear = currentAccessCalendar.get(Calendar.DAY_OF_YEAR);

                        // Update the last access day
                        Date lastAccessDate = lastAccessCalendar.getTime();
                        AppUsage existingUsageLastDay = dao.getUsageByDayOfYearAndApp(lastAccessDayOfYear, appName);
                        long newUsageLastDay = Math.min(totalUsageTimeInForeground, timeUntilMidnight);
                        if (existingUsageLastDay != null) {
                            existingUsageLastDay.usageTime = newUsageLastDay;
                            dao.update(existingUsageLastDay);
                        } else {
                            AppUsage newUsageLastDayObject = new AppUsage(lastAccessDate, appName, newUsageLastDay, lastAccessCalendar.get(Calendar.WEEK_OF_YEAR));
                            dao.insert(newUsageLastDayObject);
                        }

                        // Update the current access day
                        Date currentAccessDate = currentAccessCalendar.getTime();
                        AppUsage existingUsageCurrentDay = dao.getUsageByDayOfYearAndApp(currentAccessDayOfYear, appName);
                        long previousUsageCurrentDay = (existingUsageCurrentDay != null) ? existingUsageCurrentDay.usageTime : 0;
                        long newUsageCurrentDay = Math.min(totalUsageTimeInForeground, timeFromMidnight);
                        if (existingUsageCurrentDay != null) {
                            existingUsageCurrentDay.usageTime = newUsageCurrentDay;
                            dao.update(existingUsageCurrentDay);
                        } else {
                            AppUsage newUsageCurrentDayObject = new AppUsage(currentAccessDate, appName, newUsageCurrentDay, currentAccessCalendar.get(Calendar.WEEK_OF_YEAR));
                           dao.insert(newUsageCurrentDayObject);
                        }
                    } else {
                        // Session is within the same day
                        // Check if a record exists for this app and date
                        AppUsage existingUsage = dao.getUsageByDayOfYearAndApp(currentDayOfYear, appName);

                        if (existingUsage != null) {
                            // Update the existing record with the new usage time
                            existingUsage.usageTime = totalUsageTimeInForeground;
                            dao.update(existingUsage);
                        } else {
                            // Insert a new record
                            AppUsage newUsageObject = new AppUsage(today, appName, totalUsageTimeInForeground, currentWeek);
                            dao.insert(newUsageObject);
                        }
                    }
                }
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

            // New methods for image persistence
            private String saveImageToInternalStorage(Bitmap bitmap) {
                // Create a file in the internal storage
                File directory = getFilesDir();
                File file = new File(directory, "profile_image.png");

                try (FileOutputStream fos = new FileOutputStream(file)) {
                    // Compress the bitmap and write it to the file
                    bitmap.compress(Bitmap.CompressFormat.PNG, 100, fos);
                    return file.getAbsolutePath();
                } catch (IOException e) {
                    e.printStackTrace();
                    Toast.makeText(this, "Error saving image", Toast.LENGTH_SHORT).show();
                    return null;
                }
            }

            private Bitmap loadImageFromInternalStorage() {
                SharedPreferences prefs = getSharedPreferences(PROFILE_PREFS, MODE_PRIVATE);
                String imagePath = prefs.getString(PROFILE_IMAGE_PATH_KEY, null);

                if (imagePath != null) {
                    File file = new File(imagePath);
                    if (file.exists()) {
                        return BitmapFactory.decodeFile(file.getAbsolutePath());
                    }
                }
                return null;
            }

            private void saveLastAccessTime() {
                SharedPreferences prefs = getSharedPreferences("AppUsageData", MODE_PRIVATE);
                SharedPreferences.Editor editor = prefs.edit();
                editor.putLong("lastAccessTime", System.currentTimeMillis());
                editor.apply();
            }

            private long getLastAccessTime() {
                SharedPreferences prefs = getSharedPreferences("AppUsageData", MODE_PRIVATE);
                return prefs.getLong("lastAccessTime", 0);
            }
        }