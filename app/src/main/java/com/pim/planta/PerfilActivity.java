package com.pim.planta;

import android.Manifest;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.app.Activity;
import android.app.usage.UsageEvents;
import android.app.usage.UsageStatsManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;


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
            private static final String APP_USAGE_PREFS_NAME = "AppUsageTrackingPrefs";
            private static final String KEY_LAST_ACCESS_TIME = "lastAccessTime";


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
                Log.d("AppUsageGraph", "Initializing graph for week: " + selectedWeek);

                barChart = findViewById(R.id.bar_chart); // Make sure R.id.bar_chart is correct
                if (barChart == null) {
                    Log.e("AppUsageGraph", "BarChart view not found!");
                    // Consider hiding the chart or showing a message if it's null
                    return;
                }

                // Data structures for the graph
                float[][] appUsagePerDay = new float[7][5]; // 7 days, 5 apps
                String[] daysOfWeekLabels = {"Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"}; // For X-axis labels
                String[] appNamesForStack = {"Instagram", "TikTok", "YouTube", "X", "Facebook"}; // For stack labels & data retrieval order

                Calendar calendar = Calendar.getInstance();
                calendar.set(Calendar.WEEK_OF_YEAR, selectedWeek);
                calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY); // Start week on Monday

                // Ensure 'dao' is initialized. It could be a class field,
                // or you can get the instance here.
                // Example: dao = PlantRepository.getInstance(getApplicationContext()).getPlantaDAO();
                // For this example, I'll assume 'dao' is an accessible class field.
                if (dao == null) {
                    Log.e("AppUsageGraph", "DAO is null. Cannot fetch data.");
                    // Handle this case, maybe show an error to the user or on the chart
                    return;
                }

                DatabaseExecutor.execute(() -> {
                    for (int i = 0; i < 7; i++) { // For each day of the selected week (0=Mon, 1=Tue, ...)
                        int currentDayOfYear = calendar.get(Calendar.DAY_OF_YEAR);
                        // int currentYear = calendar.get(Calendar.YEAR); // Crucial if your data spans multiple years
                        // and dayOfYear alone isn't unique.
                        // Your AppUsage entity and DAO query would need to support year.

                        Log.d("AppUsageGraph", "Fetching data for day " + i + " (dayOfYear: " + currentDayOfYear +
                                ", Date: " + calendar.getTime().toString() + ")");

                        for (int j = 0; j < appNamesForStack.length; j++) { // For each app
                            String appName = appNamesForStack[j];

                            // Fetch the total usage for this specific app on this specific day.
                            // If your AppUsage table might have entries for the same dayOfYear but different years,
                            // your getUsageByDayOfYearAndApp query needs to be year-specific.
                            // For now, assuming dayOfYear is sufficient for your current data scope.
                            AppUsage usage = dao.getUsageByDayOfYearAndApp(currentDayOfYear, appName);

                            if (usage != null && usage.usageTime > 0) {
                                // usage.usageTime should represent the total milliseconds for that app on that day
                                appUsagePerDay[i][j] = usage.usageTime / 3600000f; // Convert milliseconds to hours
                                Log.d("AppUsageGraph", "Data for " + appName + " on day " + daysOfWeekLabels[i] +
                                        ": " + appUsagePerDay[i][j] + " hours (Raw: " + usage.usageTime + "ms)");
                            } else {
                                appUsagePerDay[i][j] = 0f;
                                if (usage == null) {
                                    Log.d("AppUsageGraph", "No usage entry for " + appName + " on day " + daysOfWeekLabels[i]);
                                } else {
                                    Log.d("AppUsageGraph", "Zero usage time for " + appName + " on day " + daysOfWeekLabels[i]);
                                }
                            }
                        }
                        calendar.add(Calendar.DAY_OF_YEAR, 1); // Move to the next day
                    }

                    // Update UI (BarChart) on the main thread
                    runOnUiThread(() -> {
                        // It's good practice to check if the activity is still alive before UI updates
                        if (isFinishing() || isDestroyed()) {
                            return;
                        }
                        if (barChart == null) { // Should have been caught earlier, but good for robustness
                            Log.e("AppUsageGraph", "BarChart became null before UI update.");
                            return;
                        }

                        ArrayList<BarEntry> barEntries = new ArrayList<>();
                        for (int i = 0; i < 7; i++) { // For each day
                            // The values for the stack: {app1_usage, app2_usage, ..., app5_usage} for day i
                            barEntries.add(new BarEntry(i, appUsagePerDay[i]));
                        }

                        BarDataSet barDataSet = new BarDataSet(barEntries, ""); // Label for the dataset
                        barDataSet.setStackLabels(appNamesForStack); // Labels for each part of the stack

                        // Define colors for the stacks (ensure you have enough colors for appNamesForStack)
                        barDataSet.setColors(new int[]{
                                Color.MAGENTA, // Instagram
                                Color.GRAY, // TikTok
                                Color.RED, // YouTube
                                Color.BLACK, // X (Twitter)
                                Color.BLUE  // Facebook
                        });
                        barDataSet.setValueTextColor(Color.BLACK);
                        barDataSet.setValueTextSize(0);

                        BarData barData = new BarData(barDataSet);
                        barData.setBarWidth(0.8f); // Adjust bar width as needed

                        barChart.setData(barData);
                        barChart.setFitBars(true); // Makes the bars fit into the chart area

                        // --- XAxis Configuration ---
                        XAxis xAxis = barChart.getXAxis();
                        xAxis.setValueFormatter(new IndexAxisValueFormatter(daysOfWeekLabels));
                        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
                        xAxis.setGranularity(1f);
                        xAxis.setGranularityEnabled(true);
                        xAxis.setDrawGridLines(false);
                        xAxis.setTextSize(12f);
                        xAxis.setTextColor(Color.BLACK);
                        // Typeface aventaFont = ResourcesCompat.getFont(this, R.font.aventa); // Assuming R.font.aventa exists
                        // if (aventaFont != null) {
                        //     xAxis.setTypeface(aventaFont);
                        // }

                        // --- YAxis (Left) Configuration ---
                        YAxis leftAxis = barChart.getAxisLeft();
                        leftAxis.setAxisMinimum(0f); // Start Y-axis from 0
                        leftAxis.setTextSize(12f);
                        leftAxis.setTextColor(Color.BLACK);
                        // if (aventaFont != null) {
                        //     leftAxis.setTypeface(aventaFont);
                        // }

                        // --- YAxis (Right) Configuration ---
                        barChart.getAxisRight().setEnabled(false); // Disable right Y-axis

                        // --- Legend Configuration ---
                        Legend legend = barChart.getLegend();
                        legend.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
                        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);
                        legend.setOrientation(Legend.LegendOrientation.HORIZONTAL);
                        legend.setDrawInside(false);
                        legend.setTextSize(12f);
                        legend.setXEntrySpace(12f);
                        legend.setTextColor(Color.BLACK);
                        // if (aventaFont != null) {
                        //     legend.setTypeface(aventaFont);
                        // }

                        // --- Chart Description & Animation ---
                        barChart.getDescription().setEnabled(false); // No description text
                        barChart.animateY(1000, Easing.EaseInOutCubic); // Animation
                        barChart.invalidate(); // Refresh the chart
                        Log.d("AppUsageGraph", "Graph updated and invalidated.");
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
                                        "%-2s %s\n" +
                                        "%-9s %s\n",
                                "Week", selectedWeek,
                                "Instagram:", formatTime(finalInstagramUsage),
                                "TikTok:", formatTime(finalTiktokUsage),
                                "YouTube:", formatTime(finalYoutubeUsage),
                                "X:", formatTime(finalTwitterUsage),
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
                // getLastAccessTime() should load the time your app was last active
                // (e.g., saved during a previous onStop or when tracking was last successfully run).
                long lastSuccessfullyTrackedTime = getLastAccessTime(); // Renamed for clarity

                // Save current time as the new "last access time" immediately.
                // This marks the end point of the current processing interval if processing happens.
                // And it's the starting point for the *next* interval if processing is skipped now.
                saveLastAccessTime(currentAccessTime);

                // If it's the first time (lastSuccessfullyTrackedTime is 0) or
                // if current time is somehow before the last tracked time (e.g., clock change),
                // there's no valid interval to process *from*.
                if (lastSuccessfullyTrackedTime == 0 || currentAccessTime < lastSuccessfullyTrackedTime) {
                    Log.d("AppUsage", "Skipping usage processing: lastSuccessfullyTrackedTime=" + lastSuccessfullyTrackedTime +
                            ", currentAccessTime=" + currentAccessTime + ". Will start tracking from currentAccessTime onwards.");
                    return;
                }

                Log.d("AppUsage", "Preparing to track usage for interval: " +
                        new Date(lastSuccessfullyTrackedTime) + " to " + new Date(currentAccessTime));

                // Final variables for lambda
                final long finalLastTrackedTime = lastSuccessfullyTrackedTime;
                final long finalCurrentAccessTime = currentAccessTime;

                DatabaseExecutor.execute(() -> {
                    processAppUsageDataWithUsageEvents(usageStatsManager, finalLastTrackedTime, finalCurrentAccessTime);
                });
            }

            // Ensure DAO is passed or accessible
            private void processAppUsageDataWithUsageEvents(UsageStatsManager usageStatsManager, long intervalStartTime, long intervalEndTime) {
                String[] targetAppNames = {"Instagram", "TikTok", "YouTube", "Twitter", "Facebook"};
                String[] targetPackageNames = {"com.instagram.android", "com.zhiliaoapp.musically", "com.google.android.youtube", "com.twitter.android", "com.facebook.katana"};

                Log.i("AppUsage_EVENTS", "--------------------------------------------------------------------");
                Log.i("AppUsage_EVENTS", "START processAppUsageDataWithUsageEvents Interval: " +
                        new Date(intervalStartTime) + " [" + intervalStartTime + "] TO " +
                        new Date(intervalEndTime) + " [" + intervalEndTime + "]");

                // This map will store the calculated foreground time (delta) for each app within this specific interval
                // Key: PackageName, Value: Milliseconds in foreground during this interval
                HashMap<String, Long> appForegroundTimeDeltasThisInterval = new HashMap<>();
                for (String pkg : targetPackageNames) {
                    appForegroundTimeDeltasThisInterval.put(pkg, 0L);
                }

                // This map tracks the last time an app moved to the foreground *within the current processing of events*
                // Key: PackageName, Value: Timestamp of last MOVE_TO_FOREGROUND event
                HashMap<String, Long> lastForegroundEventTimestamp = new HashMap<>();

                UsageEvents usageEvents = usageStatsManager.queryEvents(intervalStartTime, intervalEndTime);
                if (usageEvents == null) {
                    Log.w("AppUsage_EVENTS", "UsageEvents query returned null for interval.");
                    Log.i("AppUsage_EVENTS", "END processAppUsageDataWithUsageEvents (null events)");
                    Log.i("AppUsage_EVENTS", "--------------------------------------------------------------------");
                    return;
                }

                UsageEvents.Event event = new UsageEvents.Event();
                while (usageEvents.hasNextEvent()) {
                    usageEvents.getNextEvent(event);
                    String currentPackageName = event.getPackageName();
                    long eventTimestamp = event.getTimeStamp();

                    // Check if this event's package is one of the apps we are tracking
                    boolean isTrackedApp = false;
                    for (String pkgToTrack : targetPackageNames) {
                        if (pkgToTrack.equals(currentPackageName)) {
                            isTrackedApp = true;
                            break;
                        }
                    }

                    if (!isTrackedApp) {
                        continue; // Skip events from non-tracked apps
                    }

                    if (event.getEventType() == UsageEvents.Event.MOVE_TO_FOREGROUND) {
                        // Store the timestamp when this app moved to the foreground
                        lastForegroundEventTimestamp.put(currentPackageName, eventTimestamp);
                        Log.d("AppUsage_EVENTS", "Event: " + currentPackageName + " moved to FOREGROUND at " + new Date(eventTimestamp) + " [" + eventTimestamp + "]");
                    } else if (event.getEventType() == UsageEvents.Event.MOVE_TO_BACKGROUND) {
                        // App moved to background, calculate duration if we have a preceding foreground event
                        Long foregroundStartTime = lastForegroundEventTimestamp.get(currentPackageName);
                        if (foregroundStartTime != null && eventTimestamp > foregroundStartTime) {
                            long duration = eventTimestamp - foregroundStartTime;
                            long currentTotalDelta = appForegroundTimeDeltasThisInterval.getOrDefault(currentPackageName, 0L);
                            appForegroundTimeDeltasThisInterval.put(currentPackageName, currentTotalDelta + duration);

                            Log.d("AppUsage_EVENTS", "Event: " + currentPackageName + " moved to BACKGROUND at " + new Date(eventTimestamp) + " [" + eventTimestamp + "]" +
                                    ". Matched with FG event at " + new Date(foregroundStartTime) + " [" + foregroundStartTime + "]" +
                                    ". Duration in foreground: " + (duration / 1000) + "s. " +
                                    "New total delta for this app in this run: " + (appForegroundTimeDeltasThisInterval.get(currentPackageName) / 1000) + "s");

                            // Important: Remove the foreground timestamp once it's "used" by a background event
                            // This prevents incorrect calculations if an app goes FG -> BG -> FG -> BG quickly.
                            lastForegroundEventTimestamp.remove(currentPackageName);
                        } else {
                            Log.d("AppUsage_EVENTS", "Event: " + currentPackageName + " moved to BACKGROUND at " + new Date(eventTimestamp) +
                                    ", but no matching foreground event found or timestamp issue (FG: " + foregroundStartTime + ", BG: " + eventTimestamp + ")");
                        }
                    }
                }
                // Handle apps that were still in the foreground when intervalEndTime was reached
                // (i.e., they have a MOVE_TO_FOREGROUND event but no subsequent MOVE_TO_BACKGROUND within the interval)
                for (Map.Entry<String, Long> entry : lastForegroundEventTimestamp.entrySet()) {
                    String packageNameStillInFG = entry.getKey();
                    Long foregroundStartTimeStillInFG = entry.getValue(); // This is the timestamp it moved to FG

                    // Ensure this foreground event actually started before the interval ended
                    if (foregroundStartTimeStillInFG != null && intervalEndTime > foregroundStartTimeStillInFG) {
                        long duration = intervalEndTime - foregroundStartTimeStillInFG; // Duration is from FG start to interval END
                        long currentTotalDelta = appForegroundTimeDeltasThisInterval.getOrDefault(packageNameStillInFG, 0L);
                        appForegroundTimeDeltasThisInterval.put(packageNameStillInFG, currentTotalDelta + duration);

                        Log.d("AppUsage_EVENTS", "App " + packageNameStillInFG + " was still in foreground at interval end (" + new Date(intervalEndTime) + "). " +
                                "Moved to FG at " + new Date(foregroundStartTimeStillInFG) + ". Adding duration: " + (duration / 1000) + "s. " +
                                "New total delta for this app in this run: " + (appForegroundTimeDeltasThisInterval.get(packageNameStillInFG) / 1000) + "s");
                    }
                }

                // Now, update the database with these calculated deltas (totalNewUsageForAppThisInterval)
                for (int i = 0; i < targetPackageNames.length; i++) {
                    String packageName = targetPackageNames[i];
                    String appName = targetAppNames[i];
                    long totalNewUsageForAppThisInterval = appForegroundTimeDeltasThisInterval.getOrDefault(packageName, 0L);

                    if (totalNewUsageForAppThisInterval <= 0) {
                        Log.d("AppUsage_EVENTS", "No new usage delta calculated for " + appName + " (Package: " + packageName + ") in this interval. Skipping DB update for this app.");
                        continue;
                    }

                    // --- ATTRIBUTION TO DAY ---
                    // This is a critical part. The totalNewUsageForAppThisInterval might span midnight.
                    // A truly robust solution would iterate from intervalStartTime to intervalEndTime,
                    // calculate how much of totalNewUsageForAppThisInterval falls on each day,
                    // and update the DB for each of those days accordingly.

                    // SIMPLIFIED APPROACH: Attribute all usage to the day of intervalEndTime.
                    // This is an approximation and will be inaccurate if usage spans midnight significantly.
                    // For a more accurate daily breakdown, you'd need to process events day by day or
                    // intelligently split the 'totalNewUsageForAppThisInterval'.

                    Calendar cal = Calendar.getInstance();
                    cal.setTimeInMillis(intervalEndTime); // Attribute all usage to the day the interval ended
                    int dayOfYear = cal.get(Calendar.DAY_OF_YEAR);
                    int weekOfYear = cal.get(Calendar.WEEK_OF_YEAR);
                    // int year = cal.get(Calendar.YEAR); // Important if tracking year

                    Log.d("AppUsage_EVENTS", "Attempting to update DB for " + appName + " on dayOfYear " + dayOfYear +
                            " with total new usage delta for this interval: " + (totalNewUsageForAppThisInterval / 1000) + "s.");

                    AppUsage existingDailyUsage = dao.getUsageByDayOfYearAndApp(dayOfYear, appName /*, year */);

                    if (existingDailyUsage != null) {
                        Log.d("AppUsage_EVENTS", "DB: Updating " + appName + " for dayOfYear " + dayOfYear +
                                ". Current stored usageTime: " + (existingDailyUsage.usageTime / 1000) + "s. Adding delta: " + (totalNewUsageForAppThisInterval / 1000) + "s.");
                        existingDailyUsage.usageTime += totalNewUsageForAppThisInterval;
                        dao.update(existingDailyUsage);
                        Log.d("AppUsage_EVENTS", "DB: Updated. New total usageTime for " + appName + " on dayOfYear " + dayOfYear + ": " + (existingDailyUsage.usageTime / 1000) + "s.");
                    } else {
                        // No existing record for this app on this day (as per the simplified attribution).
                        // Create a new one.
                        Log.d("AppUsage_EVENTS", "DB: No existing usage for " + appName + " on dayOfYear " + dayOfYear +
                                ". Inserting new record with usageTime (delta): " + (totalNewUsageForAppThisInterval / 1000) + "s.");

                        // We need the Date object for the AppUsage constructor.
                        // cal was set to intervalEndTime.
                        AppUsage newDailyUsage = new AppUsage(cal.getTime(), appName, totalNewUsageForAppThisInterval, weekOfYear /* weekOfYear from cal */);
                        dao.insert(newDailyUsage);
                        Log.d("AppUsage_EVENTS", "DB: Inserted new daily usage for " + appName + " on dayOfYear " + dayOfYear + ".");
                    }
                }

                Log.i("AppUsage_EVENTS", "END processAppUsageDataWithUsageEvents");
                Log.i("AppUsage_EVENTS", "--------------------------------------------------------------------");
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

            private long getLastAccessTime() {
                SharedPreferences prefs = getSharedPreferences(APP_USAGE_PREFS_NAME, MODE_PRIVATE);
                return prefs.getLong(KEY_LAST_ACCESS_TIME, 0);
            }

            private void saveLastAccessTime(long time) {
                SharedPreferences prefs = getSharedPreferences(APP_USAGE_PREFS_NAME, MODE_PRIVATE);
                SharedPreferences.Editor editor = prefs.edit();
                editor.putLong(KEY_LAST_ACCESS_TIME, time);
                editor.apply(); // Use apply() for asynchronous save
                Log.d("AppUsage", "Saved lastAccessTime: " + new Date(time));
            }
        }