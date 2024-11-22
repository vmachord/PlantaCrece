package com.pim.planta.models;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import androidx.databinding.adapters.Converters;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverter;
import androidx.room.TypeConverters;
import com.pim.planta.models.Convertidores;

@Entity(tableName = "calendar")
public class Calendar {

    @PrimaryKey(autoGenerate = true)
    private int id;
    @TypeConverters(Convertidores.class)
    private List<Date> fechas;
    @TypeConverters(Convertidores.class)
    private List<String> anotaciones;
    private int emociones;           // Almacena emociones asociadas a la clase, puedes usar otro tipo o estructura si lo necesitas

    // Constructor para inicializar el calendario con fechas, anotaciones y emociones
    public Calendar(List<Date> fechas, List<String> anotaciones, int emociones) {
        this.fechas = fechas;
        this.anotaciones = anotaciones;
        this.emociones = emociones;
    }

    // Getters y setters para cada propiedad
    public List<Date> getFechas() {
        return fechas;
    }

    public void setFechas(List<Date> fechas) {
        this.fechas = fechas;
    }

    // Getters y setters para 'anotaciones'
    public List<String> getAnotaciones() {
        return anotaciones;
    }

    public void setAnotaciones(List<String> anotaciones) {
        this.anotaciones = anotaciones;
    }

    public int getEmociones() {
        return emociones;
    }

    public void setEmociones(int emociones) {
        this.emociones = emociones;
    }

    public void agregarAnotacion(Date fecha, String anotacion) {
        for (int i = 0; i < fechas.size(); i++) {
            if (fechas.get(i).equals(fecha)) {
                anotaciones.set(i, anotacion); // Actualiza la anotación correspondiente
                return;
            }
        }
        // Si no se encuentra la fecha, agregarla como una nueva entrada
        fechas.add(fecha);
        anotaciones.add(anotacion);
    }

    public String obtenerAnotacion(Date fecha) {
        for (int i = 0; i < fechas.size(); i++) {
            if (fechas.get(i).equals(fecha)) {
                return anotaciones.get(i); // Devuelve la anotación correspondiente
            }
        }
        return null; // Si no se encuentra la fecha
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}

