package com.example.inventorymanager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.provider.MediaStore;

import com.example.inventorymanager.model.Profile;

import java.io.File;

public class ViewBusinessItemsActivity extends AppCompatActivity {

    //Items are actually profiles lol

    TextView nameOfBusinessItem;
    TextView descriptionOfBusinessItem;

    ImageView image;
    Button setImageButton;

    // Image request code
    private static int RESULT_LOAD_IMAGE = 1;

    // Getting item (actually its a profile) from recycler view
   // Profile profile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_business_items);

        //Get Profile from list
        Intent i = new Intent();
        Profile profile = (Profile) getIntent().getSerializableExtra("businessItemSent");

        // Connections
        nameOfBusinessItem = findViewById(R.id.nameOfBusinessItem);
        descriptionOfBusinessItem = findViewById(R.id.descriptionOfBusinessItem);
        image = findViewById(R.id.businessItemPic);
        setImageButton = findViewById(R.id.setImage);

        // Set textview to match item
        nameOfBusinessItem.setText(profile.getProfileName());
        descriptionOfBusinessItem.setText(profile.getBusinessOrPersonal());

        setImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(i, RESULT_LOAD_IMAGE);
            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null != data) {
            Uri selectedImage = data.getData();
            try {
                Bitmap bm = MediaStore.Images.Media.getBitmap(this.getContentResolver(), selectedImage);

                ImageView imageView = (ImageView) findViewById(R.id.businessItemPic);
                imageView.setImageBitmap(bm);
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
}
