package com.example.ezyfood;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.Executors;

public class EzyDBClient {
    private Context context;
    private static EzyDBClient ezyDBClient;

    private EzyDB database;

    private EzyDBClient(Context context) {
        this.context = context;
        database = Room.databaseBuilder(context, EzyDB.class, "EzyFood")

        .build();

    }
    public static synchronized EzyDBClient getInstance(Context context) {
        if(ezyDBClient == null) {
            ezyDBClient = new EzyDBClient(context);
        }
        return ezyDBClient;
    }



    public EzyDB getDatabase() {return database;}


}
