package com.example.inventorymanager.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.Adapter;

import com.example.inventorymanager.ItemListActivity;
import com.example.inventorymanager.LocationViewActivity;
import com.example.inventorymanager.Persistence.DatabaseHandler_Location;
import com.example.inventorymanager.R;
import com.example.inventorymanager.model.Location;
import com.example.inventorymanager.model.Profile;

import java.util.ArrayList;

public class LocationList_RecyclerViewAdapter extends  RecyclerView.Adapter<LocationList_RecyclerViewAdapter.ViewHolderLocation>{

    private Context context;
    private ArrayList<Location> locationArrayList;

    public LocationList_RecyclerViewAdapter(Context c, ArrayList<Location> locationArrayList){
        this.context = c;
        this.locationArrayList = locationArrayList;
    }

    @NonNull
    @Override
    public LocationList_RecyclerViewAdapter.ViewHolderLocation onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.homeview_row, parent, false);
        return new ViewHolderLocation(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LocationList_RecyclerViewAdapter.ViewHolderLocation holder, int position) {
        final Location location = locationArrayList.get(position);


        holder.name.setText(location.getLocationName());
        holder.address.setText(location.getAddress());
    }

    @Override
    public int getItemCount() {
        return locationArrayList.size();
    }

    public class ViewHolderLocation extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView name;
        public TextView address;
        public Button deleteButton;

        public ViewHolderLocation(@NonNull View itemView) {
            super(itemView);

            itemView.setOnClickListener(this);
            name = itemView.findViewById(R.id.name);
            address = itemView.findViewById(R.id.subdesc);
            deleteButton = itemView.findViewById(R.id.deleteButton);
            deleteButton.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            Location location = locationArrayList.get(position);
            if(v.getId() == R.id.deleteButton){
                deleteLocation(location.getKey());
                return;
            }

            Intent i = new Intent(context, ItemListActivity.class);

            context.startActivity(i);
        }
        private void deleteLocation(final int id) {
            DatabaseHandler_Location db = new DatabaseHandler_Location(context);
            db.deleteLocation(id);
            locationArrayList.remove(getAdapterPosition());
            notifyItemRemoved(getAdapterPosition());
        }
    }
}
