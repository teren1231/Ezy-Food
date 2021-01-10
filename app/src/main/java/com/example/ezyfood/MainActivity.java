package com.example.ezyfood;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    private DummyDrinks dummy = new DummyDrinks();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getData();

        setContentView(R.layout.activity_main);

        Button Drinks = findViewById(R.id.BtnDrinks);
        Drinks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                aktivitas(MenuDrinks.class);
            }
        });

        Button MyOrder = findViewById(R.id.btnMyOrder);
        MyOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                aktivitas(MyOrder.class);
            }
        });

        Button Foods = findViewById(R.id.BtnFoods);
        Foods.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                aktivitas(MenuFoods.class);
            }
        });

        Button Snacks = findViewById(R.id.BtnSnacks);
        Snacks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                aktivitas(MenuSnacks.class);
            }
        });

    }

    private void aktivitas(Class<?> clazz) {
        Intent intent = new Intent(this, clazz);
//        intent.putExtra("dummy", dummy);
        startActivity(intent);
    }

    public void getData(){
        class GetData extends AsyncTask<Void,Void, List<Drink>> {

            @Override
            protected List<Drink> doInBackground(Void... voids) {
                List<Drink> Drinks = (List<Drink>) EzyDBClient
                        .getInstance(MainActivity.this)
                        .getDatabase().drinkDAO().getAll();
                return Drinks;
            }
            @Override
            protected void onPostExecute(List<Drink> Drinks) {
                super.onPostExecute(Drinks);
                if (Drinks.isEmpty()){
                    initDummy();
                    Toast.makeText(MainActivity.this, "Empty List" + Drinks, Toast.LENGTH_SHORT).show();
                }else{
                    for(Drink drink: Drinks) {
                        Log.i("main aktivitas", "onPostExecute: " + drink.getName());
                        Toast.makeText(MainActivity.this, drink.getName(), Toast.LENGTH_SHORT).show();
                    }
                    Toast.makeText(MainActivity.this, "masuk else" + Drinks.size(), Toast.LENGTH_SHORT).show();
                }
            }
        }

        GetData get = new GetData();

        get.execute();
    }

    public void initDummy(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                EzyDBClient
                        .getInstance(MainActivity.this).getDatabase()
                        .drinkDAO().insertAll(DummyDrinks());
                EzyDBClient
                        .getInstance(MainActivity.this).getDatabase()
                        .foodDAO().insertAll(DummyFoods());
                EzyDBClient
                        .getInstance(MainActivity.this).getDatabase()
                        .snackDAO().insertAll(DummySnacks());
                EzyDBClient
                        .getInstance(MainActivity.this).getDatabase()
                        .userDAO().insertAll(DummyUser());
            }
        }).start();
    }

    public ArrayList<Drink> DummyDrinks(){
        ArrayList<Drink> listDrinks = new ArrayList<Drink>();
        DummyDrinks dummy = new DummyDrinks();
        Drink drink1 = new Drink("airmineral", 2000 , 0, "drinks");
        Drink drink2 = new Drink("apel", 5000 , 0, "apel");
        Drink drink3 = new Drink("mangga", 5000 , 0, "mangga");
        Drink drink4 = new Drink("alpukat", 6000 , 0, "alpukat");
        listDrinks.addAll(Arrays.asList(drink1,drink2,drink3,drink4));

        return listDrinks;
    }

    public ArrayList<Food> DummyFoods(){
        ArrayList<Food> listFoods = new ArrayList<Food>();
        DummyFoods dummy = new DummyFoods();
        Food food1 = new Food("indomie", 5000 , 0, "indomie");
        Food food2 = new Food("Ayam Goreng", 12000 , 0, "apel");
        Food food3 = new Food("Ikan Bakar", 20000 , 0, "mangga");
        Food food4 = new Food("Nasi Goreng", 15000 , 0, "alpukat");

        listFoods.addAll(Arrays.asList(food1,food2,food3,food4));

        return listFoods;
    }

    public ArrayList<Snack> DummySnacks(){
        ArrayList<Snack> listSnacks = new ArrayList<Snack>();
        DummySnacks dummy = new DummySnacks();

        Snack snack1 = new Snack("kentang", 1000 , 0, "drinks");
        Snack snack2 = new Snack("chiki", 2500 , 0, "apel");
        Snack snack3 = new Snack("lolipop", 1000 , 0, "mangga");
        Snack snack4 = new Snack("keripik", 1500 , 0, "alpukat");

        listSnacks.addAll(Arrays.asList(snack1,snack2,snack3,snack4));

        return listSnacks;
    }

    public User DummyUser(){
        User user = new User(0);

        return user;
    }


}