package com.example.inventorymanager.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.inventorymanager.R;
import com.example.inventorymanager.model.Item;
import com.example.inventorymanager.itemView;

import java.util.ArrayList;

public class ItemList_RecyclerViewAdapter extends RecyclerView.Adapter<ItemList_RecyclerViewAdapter.ViewHolderItem> {

    private Context context;
    private ArrayList<Item> itemArrayList;

    public ItemList_RecyclerViewAdapter(Context context, ArrayList<Item> itemArrayList) {
        this.context = context;
        this.itemArrayList = itemArrayList;
    }

    @NonNull
    @Override
    public ItemList_RecyclerViewAdapter.ViewHolderItem onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.homeview_row, parent, false);
        return new ViewHolderItem(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemList_RecyclerViewAdapter.ViewHolderItem holder, int position) {
        final Item item = itemArrayList.get(position);
        holder.name.setText(item.getItemName());
        holder.address.setText("Quanitity: " + item.getQuantity());
    }

    @Override
    public int getItemCount() {
        return itemArrayList.size();
    }

    public class ViewHolderItem extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView name;
        public TextView address;

        public ViewHolderItem(@NonNull View v) {
            super(v);


            name = v.findViewById(R.id.name);
            address = v.findViewById(R.id.subdesc);

            v.setOnClickListener(this);
        }


        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();

            Item item = itemArrayList.get(position);
            Intent intent = new Intent(context, com.example.inventorymanager.itemView.class);

            context.startActivity(intent);
        }
    }

}
