package com.example.inventorymanager;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class BusinessOrPersonalMainActivity extends AppCompatActivity {
    TextView presentTitle;
    Button businessButton;
    Button personalButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_business_or_personal_main);

        presentTitle = findViewById(R.id.presentTitle);
        businessButton = findViewById(R.id.BusinessButton);
        personalButton = findViewById(R.id.PersonalButton);

        //Set text
        businessButton.setText("Business");
        personalButton.setText("Personal");
    }
}
