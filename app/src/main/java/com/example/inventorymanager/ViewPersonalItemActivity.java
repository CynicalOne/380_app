package com.example.inventorymanager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.inventorymanager.Persistence.DatabaseHandler_Location;
import com.example.inventorymanager.model.Location;
import com.example.inventorymanager.model.Profile;

import java.text.NumberFormat;

public class ViewPersonalItemActivity extends AppCompatActivity {

    Location location;

    //Items are actually locations lol
    TextView nameOfLocationItem;
    TextView descriptionOfLocationItem;

    ImageView image;
    Button setImageButton;

    //Editables
    EditText serialEdit;
    EditText modelEdit;
    EditText quantityEdit;
    EditText priceEdit;
    TextView dateOfPurchase;

    Button savePersonalItemButton;

    // Image request code
    private static int RESULT_LOAD_IMAGE = 1;

    DatabaseHandler_Location db = new DatabaseHandler_Location(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_personal_item);

        //Get Personal item from list activity
        Intent i = new Intent();
        location = (Location) getIntent().getSerializableExtra("personalItemSent");

        //Init components
        nameOfLocationItem = findViewById(R.id.nameOfLocationItem);
        descriptionOfLocationItem = findViewById(R.id.addyView);

        serialEdit = findViewById(R.id.serialLocationEditText);
        modelEdit = findViewById(R.id.modelLocationEditText);
        quantityEdit = findViewById(R.id.quantityLocEditText);
        priceEdit = findViewById(R.id.priceLocEditText);
        dateOfPurchase = findViewById(R.id.dateOfPurchaseLoc);
        savePersonalItemButton = findViewById(R.id.saveButtonLoc);
        setImageButton = findViewById(R.id.setImageLocation);

        nameOfLocationItem.setText(location.getLocationName());
        descriptionOfLocationItem.setText(location.getAddress());

        dateOfPurchase.setText("Date Added: " + location.getDateOfPurchase());

        // Set edit texts to info if it exists
        if(location.getSerialNo() != null || location.getModel() != null || location.getQuantity() != 0 || location.getPrice() != 0) {
            serialEdit.setText(location.getSerialNo(), TextView.BufferType.EDITABLE);
            modelEdit.setText(location.getModel(), TextView.BufferType.EDITABLE);

            String quantity = String.valueOf(location.getQuantity());
            quantityEdit.setText(quantity, TextView.BufferType.EDITABLE);

            NumberFormat formatter = NumberFormat.getCurrencyInstance();
            String priceString = formatter.format(location.getPrice());
            priceEdit.setText(priceString, TextView.BufferType.EDITABLE);
        }

        //Update item
        savePersonalItemButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updatePersonalItem(location);
            }
        });

        //Pick image
        setImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(i, RESULT_LOAD_IMAGE);
            }
        });

    }

    private void updatePersonalItem(Location location) {
        String serial = serialEdit.getText().toString().trim();
        location.setSerialNo(serial);

        String model = modelEdit.getText().toString().trim();
        location.setModel(model);

        int quantity = Integer.parseInt(quantityEdit.getText().toString().trim());
        location.setQuantity(quantity);

        double price = Double.parseDouble(priceEdit.getText().toString().trim());
        location.setPrice(price);

        db.updateLocation(location);

        //refresh activity
        startActivity(getIntent());
        finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null != data) {
            Uri selectedImage = data.getData();
            try {
                Bitmap bm = MediaStore.Images.Media.getBitmap(this.getContentResolver(), selectedImage);

                String path = selectedImage.getPath();

                ImageView imageView = (ImageView) findViewById(R.id.locationItemPic);
                imageView.setImageBitmap(bm);
                //Toast.makeText(this, path, Toast.LENGTH_LONG).show();
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
