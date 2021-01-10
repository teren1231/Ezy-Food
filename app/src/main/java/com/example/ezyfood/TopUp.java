package com.example.ezyfood;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

public class TopUp extends AppCompatActivity {
    private EditText uang;
    private User user;
    private int Balance;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_up);

        uang = findViewById(R.id.editTextQuanityty);

        Button add = findViewById(R.id.btnTopUp);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!uang.getText().toString().isEmpty()) {
                    user.tambahUang(Integer.parseInt(uang.getText().toString()));
                    update(user);
                }
                else {
                    uang.setError("Kosong!");
                    uang.setFocusable(true);
                }

            }
        });


    }

    private void update(final User user){
        class UpdateUser extends AsyncTask<Void, Void, Void> {

            @Override
            protected Void doInBackground(Void... voids) {

                EzyDBClient.getInstance(TopUp.this).getDatabase()
                        .userDAO()
                        .update(user);
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                Toast.makeText(TopUp.this, "Item Added", Toast.LENGTH_SHORT).show();
            }
        }

        UpdateUser update = new UpdateUser();
        update.execute();
    }


}}