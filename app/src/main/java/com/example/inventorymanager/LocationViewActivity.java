package com.example.inventorymanager;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class LocationViewActivity extends AppCompatActivity {

    TextView name;
    TextView description;
    ImageView profilePic;
    Button goToLocationButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_view);

        //connect to xml
        name = findViewById(R.id.name);
        description = findViewById(R.id.description);
        profilePic = findViewById(R.id.profilePicture);
        goToLocationButton = findViewById(R.id.goToLocationButton);

        Bundle bundle = getIntent().getExtras();
        if(bundle != null){
            String nameRecieved = bundle.getString("name");
            String descriptionRecieved = bundle.getString("description");

            name.setText(nameRecieved);
            description.setText(descriptionRecieved);
        }


    }
}
