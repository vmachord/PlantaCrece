package com.pim.planta.models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "entradas_diarias")
public class EntradaDiario {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String anotacion;
    private int emocion;

    public EntradaDiario(String anotacion, int emocion){
        this.anotacion = anotacion;
        this.emocion = emocion;
    }
}
