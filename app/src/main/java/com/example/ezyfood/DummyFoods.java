package com.example.ezyfood;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;

public class DummyFoods implements Serializable {
    private static ArrayList<Food> listFoods = new ArrayList<Food>();

    public DummyFoods() {
        Food food1 = new Food("indomie", 5000 , 0, "indomie");
        Food food2 = new Food("Ayam Goreng", 12000 , 0, "apel");
        Food food3 = new Food("Ikan Bakar", 20000 , 0, "mangga");
        Food food4 = new Food("Nasi Goreng", 15000 , 0, "alpukat");

        listFoods.addAll(Arrays.asList(food1,food2,food3,food4));
    }

    public static ArrayList<Food> getListFoods() {
        return listFoods;
    }
}