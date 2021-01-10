package com.example.ezyfood;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MenuFoods extends AppCompatActivity {
    private Intent i = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_foods);

        final ImageView indomie = findViewById(R.id.indomie);
        indomie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFood(1);
            }
        });

        ImageView ayam = findViewById(R.id.ayam);
        ayam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFood(2);
            }
        });

        ImageView ikan = findViewById(R.id.ikan);
        ikan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFood(3);
            }
        });

        ImageView nasi = findViewById(R.id.nasi);
        nasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFood(4);
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

    public void getFood(final int pos){
        class GetFood extends AsyncTask<Void,Void, Food> {

            @Override
            protected Food doInBackground(Void... voids) {
                Food Foods = EzyDBClient
                        .getInstance(MenuFoods.this)
                        .getDatabase().foodDAO().getFood(pos);
                return Foods;
            }
            @Override
            protected void onPostExecute(Food Foods) {
                super.onPostExecute(Foods);

                i = new Intent(MenuFoods.this, Order.class);

                i.putExtra("data", Foods);
                startActivity(i);
            }
        }

        GetFood get = new GetFood();
        get.execute();
    }

    private void aktivitas(Class<?> clazz) {
        Intent intent = new Intent(this, clazz);
        startActivity(intent);
    }

}