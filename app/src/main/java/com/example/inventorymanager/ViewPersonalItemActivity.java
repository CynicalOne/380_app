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
        priceEdit = findViewById(R.id.priceEditText);
        dateOfPurchase = findViewById(R.id.dateOfPurchaseLoc);
        savePersonalItemButton = findViewById(R.id.saveButtonLocation);

        nameOfLocationItem.setText(location.getLocationName());
        descriptionOfLocationItem.setText(location.getAddress());

        dateOfPurchase.setText("Date Added: " + location.getDateOfPurchase());

        //Update item
        savePersonalItemButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updatePersonalItem(location);
            }
        });
    }

    private void updatePersonalItem(Location location) {
        String serial = serialEdit.getText().toString();
        location.setSerialNo(serial);

        String model = modelEdit.getText().toString();
        location.setModel(model);

        int quantity = Integer.parseInt(quantityEdit.getText().toString());
        location.setQuantity(quantity);

        double price = Double.parseDouble(priceEdit.getText().toString());
        location.setPrice(price);

        db.updateLocation(location);

        //refresh activity
        startActivity(getIntent());
        finish();
    }
}
