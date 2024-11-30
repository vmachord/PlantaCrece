package com.pim.planta.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import com.pim.planta.models.DiaryEntry;
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
    void insert(DiaryEntry entrada);
    @Insert
    void insert(User usuario);
    @Insert
    void insert(Calendar calendario);

    @Update
    void update(Plant planta);
    @Update
    void update(DiaryEntry entrada);
    @Update
    void update(User usuario);
    @Update
    void update(Calendar calendario);

    @Delete
    void delete(Plant planta);
    @Delete
    void delete(DiaryEntry entrada);
    @Delete
    void delete(User usuario);
    @Delete
    void delete(Calendar calendario);

    @Query("SELECT * FROM plants")
    List<Plant> getAllPlantas();
    @Query("SELECT * FROM `diary-entries`")
    List<DiaryEntry> getAllEntries();
    @Query("SELECT * FROM users")
    List<User> getAllUsuarios();

    @Query("SELECT * FROM calendar")
    List<Calendar> getAllCalendar();

    @Query("SELECT * FROM plants WHERE id = :id")
    Plant getPlantaById(int id);
    @Query("SELECT * FROM `diary-entries` WHERE id = :id")
    DiaryEntry getEntradaById(int id);
    @Query("SELECT * FROM `diary-entries` WHERE user_id = :id")
    List<DiaryEntry> getEntradasByUserId(int id);
    @Query("SELECT * FROM users WHERE id = :id")
    User getUsuarioById(int id);
    @Query("SELECT * FROM calendar WHERE id = :id")
    Calendar getCalendarById(int id);
    @Query("SELECT * FROM users WHERE email = :email")
    User getUserByEmail(String email);
}
