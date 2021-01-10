package com.example.ezyfood;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;

public class DummySnacks implements Serializable {
    private static ArrayList<Snack> listSnacks = new ArrayList<Snack>();

    public DummySnacks() {
        Snack snack1 = new Snack("kentang", 1000 , 0, "drinks");
        Snack snack2 = new Snack("chiki", 2500 , 0, "apel");
        Snack snack3 = new Snack("lolipop", 1000 , 0, "mangga");
        Snack snack4 = new Snack("keripik", 1500 , 0, "alpukat");

        listSnacks.addAll(Arrays.asList(snack1,snack2,snack3,snack4));
    }

    public static ArrayList<Snack> getListSnacks() {
        return listSnacks;
    }
}
