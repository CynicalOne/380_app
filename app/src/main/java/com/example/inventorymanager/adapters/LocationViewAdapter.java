package com.example.inventorymanager.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.inventorymanager.R;

public class LocationViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    Context context;
    String[] items;
    public LocationViewAdapter(Context context, String[] items){
        this.context = context;
        this.items=items;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View row = inflater.inflate(R.layout.homeview_row,parent,false);
        Item item = new Item(row);
        return item;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((Item)holder).textView.setText(items[position]);


    }

    @Override
    public int getItemCount() {
        return items.length;
    }
    public class Item extends RecyclerView.ViewHolder{
        TextView textView;

        public Item(@NonNull View itemView) {
            super(itemView);
            textView = (TextView)itemView.findViewById(R.id.name);
        }
    }

}
