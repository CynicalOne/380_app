package com.example.inventorymanager.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.Adapter;

import com.example.inventorymanager.LocationViewActivity;
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

    public class ViewHolderLocation extends RecyclerView.ViewHolder {

        public TextView name;
        public TextView address;

        public ViewHolderLocation(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.name);
            address = itemView.findViewById(R.id.subdesc);
        }
    }
}
