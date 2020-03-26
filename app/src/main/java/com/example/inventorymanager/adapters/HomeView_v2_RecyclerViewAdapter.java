package com.example.inventorymanager.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.inventorymanager.LocationViewActivity;
import com.example.inventorymanager.R;
import com.example.inventorymanager.model.Profile;

import java.util.ArrayList;

public class HomeView_v2_RecyclerViewAdapter extends RecyclerView.Adapter<HomeView_v2_RecyclerViewAdapter.ViewHolder> {

    private Context context;
    private ArrayList<Profile> profileList;

    public HomeView_v2_RecyclerViewAdapter(Context context, ArrayList<Profile> profileList) {
        this.context = context;
        this.profileList = profileList;
    }

    @NonNull
    @Override
    public HomeView_v2_RecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.homeview_row, viewGroup, false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull HomeView_v2_RecyclerViewAdapter.ViewHolder holder, int position) {
        // grab profile at index
        Profile profile = profileList.get(position);

        //set name and sub description in recycler view
        holder.name.setText(profile.getProfileName());
        holder.subDesc.setText(profile.getBusinessOrPersonal());
    }

    @Override
    public int getItemCount() {
        return profileList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements  View.OnClickListener{
        // fields in recyclerview cell
        public TextView name;
        public TextView subDesc;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            itemView.setOnClickListener(this); // makes cell clickable

            name = itemView.findViewById(R.id.name);
            subDesc = itemView.findViewById(R.id.subdesc);
        }

        @Override
        public void onClick(View v) {
            // get location where user clicked
            int position = getAdapterPosition();
            Profile profile = profileList.get(position);

            // send data to LocationViewActivity
            Intent intent = new Intent(context, LocationViewActivity.class);
            intent.putExtra("name", profile.getProfileName());
            intent.putExtra("description", profile.getBusinessOrPersonal());

            // move to LocationViewActivity
            context.startActivity(intent);
        }
    }
}
