package com.example.ezyfood;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface DrinkDAO {
    @Query("SELECT * FROM Drink")
    List<Drink>getAll( );

    @Insert
    void insertAll(List<Drink> drink);

    @Update
    void update(Drink drink);

    @Update
    void removeItem(Drink drink);

    @Query("SELECT * FROM Drink WHERE DrinkID=:ID")
    Drink getDrink(int ID);
}
