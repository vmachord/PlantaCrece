package com.pim.planta.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import com.pim.planta.models.AppUsage;
import com.pim.planta.models.DiaryEntry;
import com.pim.planta.models.Plant;
import com.pim.planta.models.User;
import com.pim.planta.models.UserPlantRelation;

import java.util.Date;
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
    void insert(UserPlantRelation relation);
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(AppUsage appUsage);

    @Update
    void update(Plant planta);
    @Update
    void update(DiaryEntry entrada);
    @Update
    void update(User usuario);
    @Update
    void update(AppUsage appUsage);

    @Delete
    void delete(Plant planta);
    @Delete
    void delete(DiaryEntry entrada);
    @Delete
    void delete(User usuario);
    @Delete
    void delete(UserPlantRelation relation);

    @Query("SELECT * FROM users")
    List<User> getAllUsuarios();
    @Query("SELECT * FROM plants")
    List<Plant> getAllPlantas();
    @Query("SELECT * FROM plants WHERE id = :id")
    Plant getPlantaById(int id);
    @Query("SELECT * FROM plants WHERE name = :name")
    Plant getPlantaByName(String name);
    @Query("SELECT * FROM `diary-entries` WHERE user_id = :id")
    List<DiaryEntry> getEntradasByUserId(int id);
    @Query("SELECT * FROM `diary-entries` WHERE user_id = :userId AND date = :date")
    DiaryEntry getEntradaByUserIdAndDate(int userId, long date);
    @Query("SELECT * FROM users WHERE email = :email")
    User getUserByEmail(String email);
    @Query("SELECT * FROM user_plant_relation WHERE userId = :userId")
    List<UserPlantRelation> getUserPlantRelations(int userId);

    @Query("INSERT INTO user_plant_relation (userId, plantId) VALUES (:userId, :plantId)")
    void insertUserPlantRelation(int userId, int plantId);

    @Query("UPDATE user_plant_relation SET growCount = growCount + 1 WHERE userId = :userId AND plantId = :plantId")
    void incrementGrowCount(int userId, int plantId);

    @Query("SELECT growCount FROM user_plant_relation WHERE userId = :userId AND plantId = :plantId")
    int getGrowCount(int userId, int plantId);

    @Query("SELECT * FROM plants WHERE name = :plantName")
    LiveData<Plant> getLivePlantaByName(String plantName);

    @Query("SELECT * FROM app_usage WHERE date BETWEEN :startDate AND :endDate")
    List<AppUsage> getUsageBetweenDates(Date startDate, Date endDate);

    @Query("SELECT * FROM app_usage WHERE dayOfYear = :dayOfYear AND appName = :appName")
    AppUsage getUsageByDayOfYearAndApp(int dayOfYear, String appName);

    @Query("SELECT * FROM app_usage WHERE weekOfYear = :weekOfYear")
    List<AppUsage> getUsageByWeek(int weekOfYear);
}