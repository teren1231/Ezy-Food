package com.example.ezyfood;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class CompleteOrder extends AppCompatActivity {
    private RecyclerView recyclerView;
    private TextView totalTextView;
    private DummyDrinks dummy;
    private int TotalHarga;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complete_order);

        dummy = (DummyDrinks) getIntent().getExtras().get("dummy");
        totalTextView = findViewById(R.id.totalTextView);
        recyclerView = findViewById(R.id.recyclerViewBelanjaan);

        List<Drink> listDrink = dummy.getListDrinks();
        List<Drink> allDrinks = new ArrayList<>();

        for(Drink drink: listDrink) {
            if(drink.getQuantity() > 0) {
                allDrinks.add(drink);
                TotalHarga = drink.getPrice() * drink.getQuantity();
            }
        }

        TextView textView = findViewById(R.id.totalTextView);
        textView.setText("Rp.  " + TotalHarga);

        ItemAdapter adapter = new ItemAdapter(allDrinks, this, true);
        Log.i("CompleteOrder", "jml beli: " + allDrinks.size());
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        Button MainMenu = findViewById(R.id.BtnMainMenu);
        MainMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                aktivitas(MainActivity.class);
            }
        });

    }

    private void aktivitas(Class<?> clazz) {
        Intent intent = new Intent(this, clazz);
        intent.putExtra("dummy", dummy);
        startActivity(intent);
    }
}