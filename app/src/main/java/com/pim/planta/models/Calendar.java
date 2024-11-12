package com.pim.planta.models;

import java.util.Date;

public class Calendar {
    private Date[] fechas;          // Almacena las fechas
    private String[] anotaciones;    // Almacena anotaciones para cada fecha
    private int emociones;           // Almacena emociones asociadas a la clase, puedes usar otro tipo o estructura si lo necesitas

    // Constructor para inicializar el calendario con fechas, anotaciones y emociones
    public Calendar(Date[] fechas, String[] anotaciones, int emociones) {
        this.fechas = fechas;
        this.anotaciones = anotaciones;
        this.emociones = emociones;
    }

    // Getters y setters para cada propiedad
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

    public int getEmociones() {
        return emociones;
    }

    public void setEmociones(int emociones) {
        this.emociones = emociones;
    }

    // Método para añadir una anotación a una fecha específica
    public void agregarAnotacion(Date fecha, String anotacion) {
        for (int i = 0; i < fechas.length; i++) {
            if (fechas[i].equals(fecha)) {
                anotaciones[i] = anotacion;
                break;
            }
        }
    }

    // Método para obtener anotación de una fecha específica
    public String obtenerAnotacion(Date fecha) {
        for (int i = 0; i < fechas.length; i++) {
            if (fechas[i].equals(fecha)) {
                return anotaciones[i];
            }
        }
        return null; // Si no se encuentra la fecha
    }
}

