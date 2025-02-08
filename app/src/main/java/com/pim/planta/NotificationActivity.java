package com.pim.planta;

import android.app.NotificationManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class NotificationActivity extends AppCompatActivity {

    private static final String PREFS_NAME = "app_prefs";
    private static final String TOTAL_USAGE_TIME_KEY = "total_usage_time";
    private static final String FIFTEEN_MINUTES_NOTIFICATION_SENT_KEY = "fifteen_minutes_notification_sent";
    private static final String TWO_HOURS_NOTIFICATION_SENT_KEY = "two_hours_notification_sent";
    private static final int NOTIFICATION_ID = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onStart() {
        super.onStart();
        resetTimersAndNotifications();
    }

    private void resetTimersAndNotifications() {
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