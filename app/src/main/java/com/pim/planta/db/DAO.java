package com.pim.planta.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import com.pim.planta.models.EntradaDiario;
import com.pim.planta.models.Plant;
import com.pim.planta.models.User;
import com.pim.planta.models.Calendar;

import java.util.List;

@Dao
public interface DAO {
    @Transaction
    default void commit(Runnable operations) {
        operations.run();
    }

    @Insert
    void insert(Plant planta);
    @Insert
    void insert(EntradaDiario entrada);
    @Insert
    void insert(User usuario);
    @Insert
    void insert(Calendar calendario);

    @Update
    void update(Plant planta);
    @Update
    void update(EntradaDiario entrada);
    @Update
    void update(User usuario);
    @Update
    void update(Calendar calendario);

    @Delete
    void delete(Plant planta);
    @Delete
    void delete(EntradaDiario entrada);
    @Delete
    void delete(User usuario);
    @Delete
    void delete(Calendar calendario);

    @Query("SELECT * FROM plants")
    List<Plant> getAllPlantas();
    @Query("SELECT * FROM entradas_diarios")
    List<EntradaDiario> getAllEntradas();
    @Query("SELECT * FROM users")
    List<User> getAllUsuarios();

    @Query("SELECT * FROM calendar")
    List<Calendar> getAllCalendario();

    @Query("SELECT * FROM plants WHERE id = :id")
    Plant getPlantaById(int id);
    @Query("SELECT * FROM entradas_diarios WHERE id = :id")
    EntradaDiario getEntradaById(int id);
    @Query("SELECT * FROM users WHERE id = :id")
    User getUsuarioById(int id);
    @Query("SELECT * FROM calendar WHERE id = :id")
    Calendar getCalendarioById(int id);
    @Query("SELECT * FROM users WHERE email = :email")
    User getUserByEmail(String email);
}
