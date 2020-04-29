package com.example.inventorymanager.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.inventorymanager.R;
import com.example.inventorymanager.model.Item;

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

    public class ViewHolderItem extends RecyclerView.ViewHolder {
        public TextView name;
        public TextView address;

        public ViewHolderItem(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.name);
            address = itemView.findViewById(R.id.subdesc);
        }
        /*
        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();

            Intent i = new Intent(context, itemView.class);

            context.startActivity(i);
        }

         */


    }

}
