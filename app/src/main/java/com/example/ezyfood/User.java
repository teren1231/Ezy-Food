package com.example.ezyfood;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;
@Entity
public class User implements Serializable {
    @PrimaryKey(autoGenerate = true)
    public int UserID;


    @ColumnInfo
    private int Balance ;


    public User(int Balance) {
        this.Balance = Balance;
    }


    public int getBalance() {
        return Balance;
    }

    public void setBalance(int balance) {
        Balance = balance;
    }

    public void tambahUang(int uang) {
        this.Balance += uang;
    }
}
