package com.pim.planta;

import android.app.NotificationManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Arrays;
import java.util.List;

public class NotificationActivity extends AppCompatActivity {

    protected static final String PREFS_NAME = "app_prefs";
    protected static final String TOTAL_USAGE_TIME_KEY = "total_usage_time";
    protected static final String FIFTEEN_MINUTES_NOTIFICATION_SENT_KEY = "fifteen_minutes_notification_sent";
    protected static final String TWO_HOURS_NOTIFICATION_SENT_KEY = "two_hours_notification_sent";
    protected static final int NOTIFICATION_ID = 1;
    protected static final String LAST_EXIT_TIME_KEY = "last_exit_time";
    protected static final List<String> SOCIAL_MEDIA_PACKAGES = Arrays.asList(
            "com.facebook.katana", // Facebook
            "com.instagram.android", // Instagram
            "com.twitter.android", // Twitter
            "com.zhiliaoapp.musically", // Tiktok
            "com.google.android.youtube" // Youtube
            // More to add here
    );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onResume() {
        super.onResume();
        resetTimersAndNotifications();
    }

    @Override
    protected void onPause() {
        super.onPause();
        // Store the current time as the last exit time
        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        prefs.edit().putLong(LAST_EXIT_TIME_KEY, System.currentTimeMillis()).apply();
    }

    protected void resetTimersAndNotifications() {
        // Reset timers and notification flags
        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        prefs.edit().putLong(TOTAL_USAGE_TIME_KEY, 0)
                .putBoolean(FIFTEEN_MINUTES_NOTIFICATION_SENT_KEY, false)
                .putBoolean(TWO_HOURS_NOTIFICATION_SENT_KEY, false)
                .apply();

        // Clear any existing notifications
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.cancel(NOTIFICATION_ID);
    }
}