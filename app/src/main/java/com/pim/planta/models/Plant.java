package com.pim.planta.models;

public class Plant {
    private String name;
    private int imageResourceId;
    private String xp; //opcional
    private String xpMax; //opcional
    private String description; //opcional

    public Plant(String name, int imageResourceId, String xp, String xpMax, String description) {
        this.name = name;
        this.imageResourceId = imageResourceId;
        this.xp = xp;
        this.xpMax = xpMax;
        this.description = description;
    }

    // Getters para los atributos
    public String getName() {
        return name;
    }

    public int getImageResourceId() {
        return imageResourceId;
    }

    public String getXp() {
        return xp;
    }

    public String getXpMax() {return xpMax;}

    public String getDescription() {
        return description;
    }
}
