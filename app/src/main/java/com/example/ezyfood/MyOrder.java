package com.example.ezyfood;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MyOrder extends AppCompatActivity {
    private RecyclerView recyclerView;
    private TextView totalTextView;
    private DummyDrinks dummyDrink;
    private DummyFoods dummyFood;
    private DummySnacks dummySnack;
    private int TotalHarga;
    private User user;
    private List<Drink> allDrinks = new ArrayList<>();
    public TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_order);

        totalTextView = findViewById(R.id.totalTextView);
        recyclerView = findViewById(R.id.recyclerViewBelanjaan);
        getData();



//        List<Food> listFood = dummyFood.getListFoods();
//        List<Food> allFoods = new ArrayList<>();
//
//        for(Food food: listFood) {
//            if(food.getQuantity() > 0) {
//                allFoods.add(food);
//                TotalHarga += TotalHarga + (food.getPrice() * food.getQuantity());
//            }
//        }
//
//        List<Snack> listSnack = dummySnack.getListSnacks();
//        List<Snack> allSnacks = new ArrayList<>();
//
//        for(Snack snack: listSnack) {
//            if(snack.getQuantity() > 0) {
//                allSnacks.add(snack);
//                TotalHarga += TotalHarga + (snack.getPrice() * snack.getQuantity());
//            }
//        }

        textView = findViewById(R.id.totalTextView);


        ItemAdapter adapter = new ItemAdapter(allDrinks, this, false);
        Log.i("MyOrder", "jml beli: " + allDrinks.size());
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        Button Pay = findViewById(R.id.BtnPay);
        Pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                aktivitas(CompleteOrder.class);
            }
        });

    }

    public void getData(){
        class GetDrink extends AsyncTask<Void,Void, List<Drink>> {

            @Override
            protected List<Drink> doInBackground(Void... voids) {
                List<Drink> Drinks = (List<Drink>) EzyDBClient
                        .getInstance(MyOrder.this)
                        .getDatabase().drinkDAO().getAll();
                return Drinks;
            }
            @Override
            protected void onPostExecute(List<Drink> Drinks) {
                super.onPostExecute((List<Drink>) Drinks);
                for(Drink drink: Drinks) {
                    if(drink.getQuantity() > 0) {
                        allDrinks.add(drink);
                        TotalHarga +=  (drink.getPrice() * drink.getQuantity());

                    }
                }
                textView.setText("Rp.  " + TotalHarga);
            }
        }

        GetDrink get = new GetDrink();
        get.execute();
    }



    private void aktivitas(Class<?> clazz) {
        Intent intent = new Intent(this, clazz);
//        intent.putExtra("dummyDrinks", dummyDrink);
//        intent.putExtra("dummyFoods", dummyFood);
//        intent.putExtra("dummySnacks", dummySnack);
        startActivity(intent);
    }


}