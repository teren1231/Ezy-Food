package com.example.ezyfood;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;
@Dao
public interface SnackDAO {
    @Query("SELECT * FROM Snack")
    List<Snack>getAll( );

    @Insert
    void insertAll(List<Snack> snack);

    @Update
    void update(Snack snack);

    @Update
    void removeItem(Snack snack);

    @Query("SELECT * FROM Snack WHERE SnackID=:ID")
    Snack getSnack(int ID);
}
