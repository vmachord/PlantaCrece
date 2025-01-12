package com.pim.planta.models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "plants")
public class Plant {
    @PrimaryKey(autoGenerate = true)
    private int id;

    private String name;
    private String basePath;
    private int imageResourceId;
    private int xp; //opcional
    private int xpMax; //opcional
    private String description; //opcional

    public Plant(String name, String basePath, int imageResourceId, int xp, int xpMax, String description) {
        this.name = name;
        this.basePath = basePath;
        this.imageResourceId = imageResourceId;
        this.xp = xp;
        this.xpMax = xpMax;
        this.description = description;
    }

    // Getters para los atributos
    public String getName() {
        return name;
    }

    public String getBasePath() { return basePath; }

    public int getImageResourceId() {
        return imageResourceId;
    }

    public int getXp() {
        return xp;
    }

    public int getXpMax() {return xpMax;}

    public String getDescription() {
        return description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public void addXp(int xp){ this.xp = this.xp + xp;}
}
