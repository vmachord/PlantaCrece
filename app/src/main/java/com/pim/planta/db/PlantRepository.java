package com.pim.planta.db;

import android.content.Context;
import androidx.room.Room;

public class PlantRepository {
    //ESTE ARCHIVO SUS SI ES NECESARIO
    private DatabasePlantoo db;

    public PlantRepository(Context context) {
        db = Room.databaseBuilder(context.getApplicationContext(),
                DatabasePlantoo.class, "planta-database").build();
    }

    public DAO getPlantaDAO() {
        return db.DAO();
    }
}