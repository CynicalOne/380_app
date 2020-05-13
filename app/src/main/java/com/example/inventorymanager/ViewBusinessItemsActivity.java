package com.example.inventorymanager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentUris;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.provider.MediaStore;
import android.widget.Toast;

import com.example.inventorymanager.Persistence.DatabaseHandler_Profiles;
import com.example.inventorymanager.Util.Constants;
import com.example.inventorymanager.model.Profile;


import java.io.File;
import java.text.NumberFormat;

public class ViewBusinessItemsActivity extends AppCompatActivity {

    //Items are actually profiles lol
    TextView nameOfBusinessItem;
    TextView descriptionOfBusinessItem;

    ImageView image;
    Button setImageButton;

    //Editables
    EditText serialEdit;
    EditText modelEdit;
    EditText quantityEdit;
    EditText priceEdit;
    TextView dateOfPurchase;

    Button saveButton;

    // Image request code
    private static int RESULT_LOAD_IMAGE = 1;

    // Getting item (actually its a profile) from recycler view
    Profile profile;

    DatabaseHandler_Profiles db = new DatabaseHandler_Profiles(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_business_items);

        //Get Profile from list
        Intent i = new Intent();
        profile = (Profile) getIntent().getSerializableExtra("businessItemSent");


        // Connections
        nameOfBusinessItem = findViewById(R.id.nameOfBusinessItem);
        descriptionOfBusinessItem = findViewById(R.id.descriptionOfBusinessItem);
        image = findViewById(R.id.businessItemPic);
        setImageButton = findViewById(R.id.setImage);

        serialEdit = findViewById(R.id.serialEditText);
        modelEdit = findViewById(R.id.modelEditText);
        quantityEdit = findViewById(R.id.quantityEditText);
        priceEdit = findViewById(R.id.priceEditText);
        dateOfPurchase = findViewById(R.id.dateOfPurchase);

        saveButton = findViewById(R.id.saveButton);

        // Set textview to match item
        nameOfBusinessItem.setText(profile.getProfileName());
        descriptionOfBusinessItem.setText(profile.getBusinessOrPersonal());

        //Set date
        dateOfPurchase.setText("Date Added: " + profile.getDateOfPurchase());

        //Set Edit Texts
//        if(profile.getSerialNo() != null || profile.getModel() != null || profile.getQuantity() != 0 || profile.getPrice() != 0) {
//            serialEdit.setText(profile.getSerialNo(), TextView.BufferType.EDITABLE);
//            modelEdit.setText(profile.getModel(), TextView.BufferType.EDITABLE);
//            quantityEdit.setText(profile.getQuantity(), TextView.BufferType.EDITABLE);
//        }

        //HardCodedImages since virtual sd always reformats
        if(profile.getProfileName().equals("David")) {
            image.setImageDrawable(getResources().getDrawable(R.drawable.david));
        }
        else if (profile.getProfileName().equals("Neal")){
            image.setImageDrawable(getResources().getDrawable(R.drawable.neal));
        }
        else if (profile.getProfileName().equals("Kyle")){
            image.setImageDrawable(getResources().getDrawable(R.drawable.kyle));
        }
        else if (profile.getProfileName().equals("Yasmeen")){
            image.setImageDrawable(getResources().getDrawable(R.drawable.yasmeen));
        }
        else if (profile.getProfileName().equals("Josh")){
            image.setImageDrawable(getResources().getDrawable(R.drawable.josh));
        }
        else if (profile.getProfileName().equals("Computers")){
            image.setImageDrawable(getResources().getDrawable(R.drawable.macbook));
        }


        if(profile.getSerialNo() != null || profile.getModel() != null || profile.getQuantity() != 0 || profile.getPrice() != 0) {
            serialEdit.setText(profile.getSerialNo(), TextView.BufferType.EDITABLE);
            modelEdit.setText(profile.getModel(), TextView.BufferType.EDITABLE);

            String quantity = String.valueOf(profile.getQuantity());
            quantityEdit.setText(quantity, TextView.BufferType.EDITABLE);

            NumberFormat formatter = NumberFormat.getCurrencyInstance();
            String priceString = formatter.format(profile.getPrice());
            priceEdit.setText( priceString, TextView.BufferType.NORMAL);
        }

        //Set image if it already exists in DB
//        if(profile.getImagePath() != null) {
//            image.setImageBitmap(BitmapFactory.decodeFile(profile.getImagePath()));
//        }

        setImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(i, RESULT_LOAD_IMAGE);
            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateBusinessItem(profile);
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

                String path = selectedImage.getPath();
                profile.setImagePath(path);
                db.updateProfile(profile);

//                ImageView imageView = (ImageView) findViewById(R.id.businessItemPic);
//                imageView.setImageBitmap(bm);
                Toast.makeText(this, path, Toast.LENGTH_LONG).show();
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void updateBusinessItem(Profile profile) {
//        if(quantityEdit.toString().isEmpty() || modelEdit.toString().isEmpty() || serialEdit.toString().isEmpty() || priceEdit.toString().isEmpty()) {
//            Toast.makeText(this, "Please fill out all fields", Toast.LENGTH_LONG).show();
//        }
        String serial = serialEdit.getText().toString();
        profile.setSerialNo(serial);

        String model = modelEdit.getText().toString();
        profile.setModel(model);

        int quantity = Integer.parseInt(quantityEdit.getText().toString());
        profile.setQuantity(quantity);

        double price = Double.parseDouble(priceEdit.getText().toString());
        profile.setPrice(price);

        db.updateProfile(profile);

        //refresh activity
        startActivity(getIntent());
        finish();
    }
}
