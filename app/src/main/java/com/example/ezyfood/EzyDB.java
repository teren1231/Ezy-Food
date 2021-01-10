package com.example.ezyfood;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Drink.class, Food.class, Snack.class, User.class}, version = 1)

public abstract class EzyDB extends RoomDatabase {

    public abstract DrinkDAO drinkDAO();
    public abstract FoodDAO foodDAO();
    public abstract SnackDAO snackDAO();
    public abstract UserDAO userDAO();
}
