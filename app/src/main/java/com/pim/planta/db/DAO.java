package com.pim.planta.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.pim.planta.models.EntradaDiario;
import com.pim.planta.models.Plant;
import com.pim.planta.models.User;
import com.pim.planta.models.Calendar;

import java.util.List;

@Dao
public interface DAO {

    @Insert
    void insert(Plant planta);
    void insert(EntradaDiario entrada);
    void insert(User usuario);
    void insert(Calendar calendario);

    @Update
    void update(Plant planta);
    void update(EntradaDiario entrada);
    void update(User usuario);
    void update(Calendar calendario);

    @Delete
    void delete(Plant planta);
    void delete(EntradaDiario entrada);
    void delete(User usuario);
    void delete(Calendar calendario);

    @Query("SELECT * FROM plants")
    List<Plant> getAllPlantas();
    List<EntradaDiario> getAllEntradas();
    List<User> getAllUsuarios();
    List<Calendar> getAllCalendario();

    @Query("SELECT * FROM plants WHERE id = :id")
    Plant getPlantaById(int id);
    EntradaDiario getEntradaById(int id);
    User getUsuarioById(int id);
    Calendar getCalendarioById(int id);
}
