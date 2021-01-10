package com.example.ezyfood;


import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;


@Dao
public interface UserDAO {
    @Query("SELECT * FROM User")
    User getAll();

    @Insert
    void insertAll(User user);

    @Update
    void update(User user);


}
