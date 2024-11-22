package com.pim.planta.models;

import androidx.room.TypeConverter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class Convertidores {

    @TypeConverter
    public static String fromListToString(List<String> list) {
        return list == null ? null : String.join(",", list);
    }


    @TypeConverter
    public static List<String> fromStringToList(String value) {
        return value == null ? null : Arrays.asList(value.split(","));
    }

    // Convierte List<Date> a String (como una lista de timestamps separados por comas)
    @TypeConverter
    public static String fromDateList(List<Date> dates) {
        if (dates == null) {
            return null;
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (Date date : dates) {
            stringBuilder.append(date.getTime()).append(",");
        }
        return stringBuilder.toString();
    }

    // Convierte String (lista de timestamps) de vuelta a List<Date>
    @TypeConverter
    public static List<Date> toDateList(String data) {
        if (data == null || data.isEmpty()) {
            return null;
        }
        String[] timestamps = data.split(",");
        List<Date> dates = new ArrayList<>();
        for (String timestamp : timestamps) {
            dates.add(new Date(Long.parseLong(timestamp)));
        }
        return dates;
    }
}
