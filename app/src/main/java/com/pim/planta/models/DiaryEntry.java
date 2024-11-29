package com.pim.planta.models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "diary-entries")
public class DiaryEntry {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String anotacion;
    private int emocion;

    public DiaryEntry(String anotacion, int emocion){
        this.anotacion = anotacion;
        this.emocion = emocion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAnotacion(){
        return anotacion;
    }

    public void setAnotacion(String anotacion){
        this.anotacion = anotacion;
    }

    public int getEmocion(){
        return emocion;
    }

    public void setEmocion(int emocion){
        this.emocion = emocion;
    }
}
