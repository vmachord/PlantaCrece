package com.pim.planta.models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "users")
public class User {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String username;
    private String password;
    private String correo;

    public User(String username, String password, String correo) {
        this.username = username;
        this.password = password;
        this.correo = correo;
    }
}
