package com.example.inventorymanager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

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
}
