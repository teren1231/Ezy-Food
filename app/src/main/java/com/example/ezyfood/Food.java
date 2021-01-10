package com.example.ezyfood;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;
@Entity
public class Food implements Serializable  {
    @PrimaryKey(autoGenerate = true)
    public int FoodID;
    @ColumnInfo
    private String name;
    @ColumnInfo
    private int price;
    @ColumnInfo
    private int quantity;
    @ColumnInfo
    private String imgUrl;



    public Food(String name, int price, int quantity, String imgUrl) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.imgUrl = imgUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public void tambahQuantity(int jml) {
        this.quantity += jml;
    }
}
