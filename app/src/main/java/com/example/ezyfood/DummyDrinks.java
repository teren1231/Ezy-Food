package com.example.ezyfood;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;

public class DummyDrinks implements Serializable {
    private static ArrayList<Drink> listDrinks = new ArrayList<Drink>();

    public DummyDrinks() {
        Drink drink1 = new Drink("airmineral", 2000 , 0, "drinks");
        Drink drink2 = new Drink("apel", 5000 , 0, "apel");
        Drink drink3 = new Drink("mangga", 5000 , 0, "mangga");
        Drink drink4 = new Drink("alpukat", 6000 , 0, "alpukat");

        listDrinks.addAll(Arrays.asList(drink1,drink2,drink3,drink4));
    }

    public static ArrayList<Drink> getListDrinks() {
        return listDrinks;
    }
}
