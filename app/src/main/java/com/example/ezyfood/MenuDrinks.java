package com.example.ezyfood;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.Arrays;
import java.util.List;

public class MenuDrinks extends AppCompatActivity {
    private Intent i = null;
    private Drink drink = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_drinks);

        final ImageView airMineral = findViewById(R.id.airmineral);
        airMineral.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getDrink(1);
            }
        });

        ImageView jusApel = findViewById(R.id.apel);
        jusApel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getDrink(2);
            }
        });

        ImageView jusMangga = findViewById(R.id.mangga);
        jusMangga.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getDrink(3);
            }
        });

        ImageView jusAlpukat = findViewById(R.id.alpukat);
        jusAlpukat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getDrink(4);
            }
        });

        Button Order = findViewById(R.id.btnMyOrder);
        Order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                aktivitas(MyOrder.class);
            }
        });
    }

    public void getDrink(final int pos){
        class GetDrink extends AsyncTask<Void,Void, Drink> {

            @Override
            protected Drink doInBackground(Void... voids) {
                Drink Drinks = EzyDBClient
                        .getInstance(MenuDrinks.this)
                        .getDatabase().drinkDAO().getDrink(pos);
                return Drinks;
            }
            @Override
            protected void onPostExecute(Drink Drinks) {
                super.onPostExecute(Drinks);

                Log.i("nama minuman", "onPostExecute: " + Drinks.getName());

                i = new Intent(MenuDrinks.this, Order.class);

                i.putExtra("data", Drinks);
                startActivity(i);
            }
        }

        GetDrink get = new GetDrink();
        get.execute();
    }

//    public void openOrder(int pos){
//        i = new Intent(MenuDrinks.this, Order.class);
//
//        getDrink(pos);
//        i.putExtra("data", drink);
//        startActivity(i);
//    }

    private void aktivitas(Class<?> clazz) {
        Intent intent = new Intent(this, clazz);
        //intent.putExtra("dummy", dummy);
        startActivity(intent);
    }

}