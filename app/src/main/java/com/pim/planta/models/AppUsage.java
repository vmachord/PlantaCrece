package com.pim.planta.models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Calendar;
import java.util.Date;

@Entity(tableName = "app_usage")
public class AppUsage {
    @PrimaryKey(autoGenerate = true)
    public int id;
    public Date date;
    public int dayOfYear;
    public String appName;
    public long usageTime;
    public int weekOfYear;

    public AppUsage(Date date, String appName, long usageTime, int weekOfYear) {
        this.date = date;
        this.appName = appName;
        this.usageTime = usageTime;
        this.weekOfYear = weekOfYear;
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        this.dayOfYear = calendar.get(Calendar.DAY_OF_YEAR); // Calculate and store the day of the year
    }
}