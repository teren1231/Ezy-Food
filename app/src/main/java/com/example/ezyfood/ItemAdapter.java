package com.example.ezyfood;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemHolder>{

    List<Drink> listDrinks;
    private Context context;
    private boolean type;

    public ItemAdapter(List<Drink> listDrinks, Context context , boolean type) {
        this.listDrinks = listDrinks;
        this.context = context;
        this.type = type;
    }

    @NonNull
    @Override
    public ItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.recycler_item, parent, false);
        return new ItemHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemHolder holder, final int position) {

        final Drink drink = listDrinks.get(position);
        holder.txtSubBarang.setText("Rp. " + drink.getPrice() + " x " + drink.getQuantity());
        holder.txtNamaBarang.setText(drink.getName());
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drink.setQuantity(0);
                remove(drink);
                listDrinks.remove(position);
                Log.i("ADAPTER", "Berhasil Hapus : " + position);
                notifyItemRemoved(position);
                notifyItemRangeChanged(position, listDrinks.size());


            }
        });

        if(type){
            holder.delete.setVisibility(View.INVISIBLE);
        }
    }

    public void remove(final Drink Drinks){
        class GetDrink extends AsyncTask<Void,Void,Void> {

            @Override
            protected Void doInBackground(Void... voids) {
                EzyDBClient.getInstance(context)
                        .getDatabase().drinkDAO().removeItem(Drinks);
                return null;
            }
            @Override
            protected void onPostExecute(Void avoid) {
                super.onPostExecute(avoid);

            }
        }

        GetDrink get = new GetDrink();
        get.execute();
    }

    @Override
    public int getItemCount() {
        return listDrinks.size();
    }

    static class ItemHolder extends RecyclerView.ViewHolder {
        private TextView txtNamaBarang;
        private TextView txtSubBarang;
        private Button delete;


        ItemHolder(View v) {
            super(v);
            txtNamaBarang = v.findViewById(R.id.namaBelanjaan);
            txtSubBarang = v.findViewById(R.id.jumlahBelanjaan);
            delete = v.findViewById(R.id.btnHapus);
        }
    }

}
