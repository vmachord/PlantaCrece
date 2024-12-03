package com.pim.planta.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import com.pim.planta.models.*;

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
    @Insert
    void insert(UserPlantRelation user_plant_relation);

    @Update
    void update(Plant planta);
    @Update
    void update(DiaryEntry entrada);
    @Update
    void update(User usuario);
    @Update
    void update(Calendar calendario);
    @Update
    void update(UserPlantRelation user_plant_relation);


    @Delete
    void delete(Plant planta);
    @Delete
    void delete(DiaryEntry entrada);
    @Delete
    void delete(User usuario);
    @Delete
    void delete(Calendar calendario);
    @Delete
    void delete(UserPlantRelation user_plant_relation);


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
    @Query("SELECT * FROM plants WHERE name = :name")
    Plant getPlantaByName(String name);
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
    @Query("SELECT * FROM user_plant_relation WHERE userId = :userId")
    List<UserPlantRelation> getUserPlantRelations(int userId);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertUserPlantRelation(UserPlantRelation relation);
    @Query("UPDATE user_plant_relation SET growCount = growCount + 1 WHERE userId = :userId AND plantId = :plantId")
    void incrementGrowCount(int userId, int plantId);
    @Query("UPDATE user_plant_relation SET growCount = growCount - 1 WHERE userId = :userId AND plantId = :plantId")
    void decrementGrowCount(int userId, int plantId);
    @Query("SELECT growCount FROM user_plant_relation WHERE userId = :userId AND plantId = :plantId")
    int getGrowCount(int userId, int plantId);
}

