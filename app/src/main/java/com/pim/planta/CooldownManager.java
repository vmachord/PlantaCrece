package com.pim.planta;

import android.content.Context;
import android.content.SharedPreferences;

import java.time.Duration;

public class CooldownManager {

    private static final String PREFS_NAME = "cooldown_prefs";
    private static final String LAST_PAD_USED_KEY = "last_pad_used_timestamp";
    private static final String LAST_WATERING_USED_KEY = "last_watering_used_timestamp";
    private static final long PAD_COOLDOWN_DURATION_MINUTES = 5;
    private static final long WATERING_COOLDOWN_DURATION_HOURS = 20;

    private SharedPreferences sharedPreferences;

    public CooldownManager(Context context) {
        sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
    }

    // Pad Cooldown Methods
    public void recordPadUsage() {
        long currentTimestamp = System.currentTimeMillis();
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putLong(LAST_PAD_USED_KEY, currentTimestamp);
        editor.apply();
    }

    public boolean isPadCooldownActive() {
        long lastUsedTimestamp = sharedPreferences.getLong(LAST_PAD_USED_KEY, 0);
        if (lastUsedTimestamp == 0) {
            return false; // Never used before
        }

        long currentTimestamp = System.currentTimeMillis();
        long elapsedTime = currentTimestamp - lastUsedTimestamp;
        long cooldownDurationMillis = PAD_COOLDOWN_DURATION_MINUTES * 60 * 1000; // 5 minutes in milliseconds

        return elapsedTime < cooldownDurationMillis;
    }

    public String getRemainingPadCooldownTime() {
        long lastUsedTimestamp = sharedPreferences.getLong(LAST_PAD_USED_KEY, 0);
        if (lastUsedTimestamp == 0) {
            return null; // Never used before
        }

        long currentTimestamp = System.currentTimeMillis();
        long elapsedTime = currentTimestamp - lastUsedTimestamp;
        long cooldownDurationMillis = PAD_COOLDOWN_DURATION_MINUTES * 60 * 1000; // 5 minutes in milliseconds

        long remainingTime = cooldownDurationMillis - elapsedTime;
        return formatRemainingTime(Math.max(0, remainingTime)); // Ensure it's not negative
    }

    // Watering Cooldown Methods
    public void recordWateringUsage() {
        long currentTimestamp = System.currentTimeMillis();
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putLong(LAST_WATERING_USED_KEY, currentTimestamp);
        editor.apply();
    }

    public boolean isWateringCooldownActive() {
        long lastUsedTimestamp = sharedPreferences.getLong(LAST_WATERING_USED_KEY, 0);
        if (lastUsedTimestamp == 0) {
            return false; // Never used before
        }

        long currentTimestamp = System.currentTimeMillis();
        long elapsedTime = currentTimestamp - lastUsedTimestamp;
        long cooldownDurationMillis = WATERING_COOLDOWN_DURATION_HOURS * 60 * 60 * 1000; // 20 hours in milliseconds

        return elapsedTime < cooldownDurationMillis;
    }

    public String getRemainingWateringCooldownTime() {
        long lastUsedTimestamp = sharedPreferences.getLong(LAST_WATERING_USED_KEY, 0);
        if (lastUsedTimestamp == 0) {
            return null; // Never used before
        }

        long currentTimestamp = System.currentTimeMillis();
        long elapsedTime = currentTimestamp - lastUsedTimestamp;
        long cooldownDurationMillis = WATERING_COOLDOWN_DURATION_HOURS * 60 * 60 * 1000; // 20 hours in milliseconds

        long remainingTime = cooldownDurationMillis - elapsedTime;
        return formatRemainingTime(Math.max(0, remainingTime)); // Ensure it's not negative
    }

    // Helper Method
    public String formatRemainingTime(long remainingTimeMillis) {
        Duration duration = Duration.ofMillis(remainingTimeMillis);
        long hours = duration.toHours();
        long minutes = duration.minusHours(hours).toMinutes();
        long seconds = duration.minusHours(hours).minusMinutes(minutes).getSeconds();

        return String.format("%02d:%02d:%02d", hours, minutes, seconds);
    }

    public static long parseTime(String time) {
        String[] parts = time.split(":");
        long hours = Long.parseLong(parts[0]);
        long minutes = Long.parseLong(parts[1]);
        long seconds = Long.parseLong(parts[2]);
        return (hours * 60 * 60 * 1000) + (minutes * 60 * 1000) + (seconds * 1000);
    }
}