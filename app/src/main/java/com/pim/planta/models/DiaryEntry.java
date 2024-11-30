package com.pim.planta.models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity(tableName = "diary-entries")
public class DiaryEntry {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String annotation;
    private int emotion;
    private int user_id;

    private long date;

    public DiaryEntry(String annotation, int emotion, int user_id, long date){
        this.annotation = annotation;
        this.emotion = emotion;
        this.user_id = user_id;
        this.date = date;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public int getUser_id(){
        return user_id;
    }

    public void setUser_id(int user_id){
        this.user_id = user_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAnnotation(){
        return annotation;
    }

    public void setAnotation(String annotation){
        this.annotation = annotation;
    }

    public int getEmotion(){
        return emotion;
    }

    public void setEmotion(int emocion){
        this.emotion = emocion;
    }

    public String emotionToString() {
        switch (this.emotion) {
            case 1:
                return "Feliz";
            case 2:
                return "Triste";
            case 3:
                return "Ansioso";
            case 4:
                return "Contento";
            case 5:
                return "Estresado";
            default:
                return "Sin emoción";
        }
    }
}
