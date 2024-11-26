package com.pim.planta.db;

import android.content.Context;
import androidx.room.Room;

public class PlantRepository {
    private DAO dao;
    private DatabasePlantoo db;

    public PlantRepository(Context context) {
        // Inicialización de la base de datos
        db = Room.databaseBuilder(context.getApplicationContext(),
                        DatabasePlantoo.class, "plant_database")
                .fallbackToDestructiveMigration()
                .build();
        this.dao = db.DAO();
    }

    public DAO getPlantaDAO() {
        return dao;
    }
}

