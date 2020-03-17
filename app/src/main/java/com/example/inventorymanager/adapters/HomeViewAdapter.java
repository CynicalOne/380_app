package com.example.inventorymanager.adapters;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.inventorymanager.MainActivity;
import com.example.inventorymanager.Profile;
import com.example.inventorymanager.R;

import java.util.ArrayList;

public class HomeViewAdapter extends RecyclerView.Adapter<HomeViewAdapter.ViewHolder> {

    private Context context;
    private ArrayList<Profile> profileArrayList;

    public HomeViewAdapter(Context context, ArrayList<Profile> profileArrayList) {
        this.context = context;
        this.profileArrayList = profileArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.homeview_row, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // grab profile at index
        Profile profile = profileArrayList.get(position);

        //set name and sub description in recycler view
        holder.name.setText(profile.getProfileName());
        holder.subDesc.setText(profile.getBusinessOrPersonal());

    }


    @Override
    public int getItemCount() {
        return profileArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

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
            int position = getAdapterPosition();
            Profile profile = profileArrayList.get(position);

//            Intent intent = new Intent(context, MainActivity.class);
//            intent.putExtra("name", profile.getProfileName());
//
//            context.startActivity(intent);
            /*
            TODO: Intent sends data and switches to LocationView
             */

            //debug
            Log.d("clicked", "onClick: " + profile.getProfileName());
        }
    }

}
