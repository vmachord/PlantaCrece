package com.pim.planta.models;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class EmotionCalendar {
    private Date[] fechas;
    private String[] anotaciones;
    private Map<Integer, Integer> emociones;

    public EmotionCalendar(Date[] fechas, String[] anotaciones) {
        this.fechas = fechas;
        this.anotaciones = anotaciones;
        this.emociones = new HashMap<>();
    }

    public void setEmocion(int day, int emotion) {
        emociones.put(day, emotion);
    }

    public int getEmocion(int day) {
        return emociones.getOrDefault(day, 0);
    }

    public Date[] getFechas() {
        return fechas;
    }

    public void setFechas(Date[] fechas) {
        this.fechas = fechas;
    }

    public String[] getAnotaciones() {
        return anotaciones;
    }

    public void setAnotaciones(String[] anotaciones) {
        this.anotaciones = anotaciones;
    }

    public void agregarAnotacion(Date fecha, String anotacion) {
        for (int i = 0; i < fechas.length; i++) {
            if (fechas[i].equals(fecha)) {
                anotaciones[i] = anotacion;
                break;
            }
        }
    }

    public String obtenerAnotacion(Date fecha) {
        for (int i = 0; i < fechas.length; i++) {
            if (fechas[i].equals(fecha)) {
                return anotaciones[i];
            }
        }
        return null;
    }
}
