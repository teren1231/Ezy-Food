package com.example.ezyfood;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MenuSnacks extends AppCompatActivity {

    private Intent i = null;
    private DummySnacks dummySnacks = new DummySnacks();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_snacks);

        ImageView kentang = findViewById(R.id.kentang);
        kentang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getSnack(0);
            }
        });

        ImageView chiki = findViewById(R.id.chiki);
        chiki.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getSnack(1);
            }
        });

        ImageView lolipop = findViewById(R.id.lolipop);
        lolipop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getSnack(2);
            }
        });

        ImageView keripik = findViewById(R.id.keripik);
        keripik.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getSnack(3);
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

    public void getSnack(final int pos){
        class GetSnack extends AsyncTask<Void,Void, Snack> {

            @Override
            protected Snack doInBackground(Void... voids) {
                Snack Snacks = EzyDBClient
                        .getInstance(MenuSnacks.this)
                        .getDatabase().snackDAO().getSnack(pos);
                return Snacks;
            }
            @Override
            protected void onPostExecute(Snack Snacks) {
                super.onPostExecute(Snacks);

                i = new Intent(MenuSnacks.this, Order.class);

                i.putExtra("data", Snacks);
                startActivity(i);
            }
        }

        GetSnack get = new GetSnack();
        get.execute();
    }

    private void aktivitas(Class<?> clazz) {
        Intent intent = new Intent(this, clazz);
        startActivity(intent);
    }

}