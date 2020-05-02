package com.example.inventorymanager.adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.ColorSpace;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.inventorymanager.LocationViewActivity;
import com.example.inventorymanager.Persistence.DatabaseHandler_Profiles;
import com.example.inventorymanager.R;
import com.example.inventorymanager.ViewBusinessItemsActivity;
import com.example.inventorymanager.model.Profile;

import java.util.ArrayList;
import java.util.List;

public class HomeView_v2_RecyclerViewAdapter extends RecyclerView.Adapter<HomeView_v2_RecyclerViewAdapter.ViewHolder> {

    private Context context;
    private ArrayList<Profile> profileList;

    private AlertDialog.Builder builder;
    private AlertDialog dialog;
    private LayoutInflater inflater;

    public HomeView_v2_RecyclerViewAdapter(Context context, ArrayList<Profile> profileList) {
        this.context = context;
        this.profileList = profileList;
    }

    @NonNull
    @Override
    public HomeView_v2_RecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.homeview_row, viewGroup, false);
        return new ViewHolder(view, context);
    }


    @Override
    public void onBindViewHolder(@NonNull final HomeView_v2_RecyclerViewAdapter.ViewHolder holder, int position) {
        // grab profile at index
        final Profile profile = profileList.get(position);

        //set name and sub description in recycler view
        holder.name.setText(profile.getProfileName());
        holder.subDesc.setText(profile.getBusinessOrPersonal());

//        //multi select, this is commented out because multideleting at once may be hard to do in a database!
//        // if you want to remove long click feature, just remove onLongClick() and anywhere that says long!
//        holder.name.setOnLongClickListener(new View.OnLongClickListener() {
//            @Override
//            public boolean onLongClick(View v) {
//                onClick(v);
//                return false;
//            }
//
//            public void onClick(View v) {
//                profile.setSelected(!profile.isSelected());
//                holder.itemView.setBackgroundColor(profile.isSelected() ? Color.CYAN : Color.WHITE);
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return profileList.size();
    }

    public void updateProfileList(List<Profile> newlist) {
        profileList.clear();
        profileList.addAll(newlist);
        this.notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
        // fields in recyclerview cell
        public TextView name;
        public TextView subDesc;
        public Button deleteButton;

        public ViewHolder(@NonNull View itemView, Context ctx) {
            super(itemView);
            context = ctx;

            itemView.setOnClickListener(this); // makes cell clickable
            itemView.setOnLongClickListener(this);

            name = itemView.findViewById(R.id.name);
            subDesc = itemView.findViewById(R.id.subdesc);

            deleteButton = itemView.findViewById(R.id.deleteButton);
            deleteButton.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            // get location where user clicked
            int position = getAdapterPosition();
            Profile profile = profileList.get(position);

            if(v.getId() == R.id.deleteButton){
                deleteProfile(profile.getId());
                return;
            }

            // send data to LocationViewActivity
            Intent intent = new Intent(context, ViewBusinessItemsActivity.class);
            intent.putExtra("businessItemSent", profile);

            // move to LocationViewActivity
            context.startActivity(intent);
        }


        private void deleteProfile(final int id) {

            //TODO: need to show confirmation popup
            builder = new AlertDialog.Builder(context);

            inflater = LayoutInflater.from(context);
            //View view = inflater.inflate(R.layout.popup_addprofile_inhomeview, null);

            DatabaseHandler_Profiles db = new DatabaseHandler_Profiles(context);
            db.deleteProfile(id);
            profileList.remove(getAdapterPosition());
            notifyItemRemoved(getAdapterPosition());
        }



        @Override
        public boolean onLongClick(View v) {
           // deleteProfile();
            return false;
        }


    }


}
