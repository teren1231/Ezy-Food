package com.example.ezyfood;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;
@Dao
public interface FoodDAO {
    @Query("SELECT * FROM Food")
    List<Food>getAll( );

    @Insert
    void insertAll(List<Food> food);

    @Update
    void update(Food food);

    @Update
    void removeItem(Food food);

    @Query("SELECT * FROM Food WHERE FoodID=:ID")
    Food getFood(int ID);
}
