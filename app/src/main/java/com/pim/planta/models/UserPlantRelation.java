package com.pim.planta.models;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;

@Entity(tableName = "user_plant_relation",
        primaryKeys = {"userId", "plantId"},
        foreignKeys = {
                @ForeignKey(entity = User.class,
                        parentColumns = "id",
                        childColumns = "userId",
                        onDelete = ForeignKey.CASCADE),
                @ForeignKey(entity = Plant.class,
                        parentColumns = "id",
                        childColumns = "plantId",
                        onDelete = ForeignKey.CASCADE)
        },
        indices = {@Index(value = {"userId", "plantId"}, unique = true)})
public class UserPlantRelation {
    public int userId;
    public int plantId;
    public int growCount; // Number of times the user has grown the plant

    public UserPlantRelation(int userId, int plantId) {
        this.userId = userId;
        this.plantId = plantId;
        this.growCount = 0; // Initialize growCount to 0
    }

    public int getUserId() {
        return userId;
    }
    public void setUserId(int userId) {
        this.userId = userId;
    }
    public int getPlantId() {
        return plantId;
    }
    public void setPlantId(int plantId) {
        this.plantId = plantId;
    }
    public int getGrowCount() {
        return growCount;
    }
    public void setGrowCount(int growCount) {
        this.growCount = growCount;
    }
}
