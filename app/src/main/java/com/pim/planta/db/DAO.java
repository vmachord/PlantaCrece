package com.pim.planta.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.pim.planta.models.Plant;

import java.util.List;

@Dao
public interface DAO {

    @Insert
    void insert(Plant planta);

    @Update
    void update(Plant planta);

    @Delete
    void delete(Plant planta);

    @Query("SELECT * FROM plants")
    List<Plant> getAllPlantas();

    @Query("SELECT * FROM plants WHERE id = :id")
    Plant getPlantaById(int id);
}
