package com.example.ezyfood;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

public class Order extends AppCompatActivity {

    private ImageView imageView;
    private ImageButton myOrder;
    private EditText jml;
    private Drink drink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);


        drink = (Drink) getIntent().getExtras().get("data");
        Log.i("Order-Dummy", drink.toString());

        imageView = findViewById(R.id.orderGambar);
        imageView.setImageResource(getResources().getIdentifier(drink.getImgUrl(), "drawable", getPackageName()));

        jml = findViewById(R.id.editTextQuanityty);

        Button add = findViewById(R.id.btnAdd);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!jml.getText().toString().isEmpty()) {
                    drink.tambahQuantity(Integer.parseInt(jml.getText().toString()));
                    update(drink);
                }
                else {
                    jml.setError("Kosong!");
                    jml.setFocusable(true);
                }

            }
        });

        myOrder = findViewById(R.id.myOrder);
        myOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Order.this, MyOrder.class);
               // i.putExtra("dummy", dummy);
                startActivity(i);

                finish();
            }
        });

        Button Drinks = findViewById(R.id.BtnDrinks);
        Drinks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                aktivitas(MainActivity.class);
            }
        });

    }

    private void aktivitas(Class<?> clazz) {
        Intent intent = new Intent(this, clazz);
//        intent.putExtra("dummyDrinks", dummy);
//        intent.putExtra("dummyFoods", dummyFood);
//        intent.putExtra("dummySnacks", dummySnack);
        startActivity(intent);
    }


    private < E > void update(final E item){
        class UpdateUser extends AsyncTask<Void, Void, Void> {

            @Override
            protected Void doInBackground(Void... voids) {
                EzyDB db =
                EzyDBClient.getInstance(Order.this).getDatabase();

                switch(item.getClass().getSimpleName()){
                    case "Drink":
                        db.drinkDAO().update((Drink) item);
                        break;
                    case "Food":
                        db.foodDAO().update((Food) item);
                        break;
                    case "Snack":
                        db.snackDAO().update((Snack) item);
                        break;
                }

                return null;
            }



            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                Toast.makeText(Order.this, "Item Added", Toast.LENGTH_SHORT).show();
            }
        }

        UpdateUser update = new UpdateUser();
        update.execute();
    }

}