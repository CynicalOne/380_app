package com.example.inventorymanager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
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

        //Go To Homeview or Business Items
        businessButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), HomeviewV2Activity.class);
                startActivity(i);
            }
        });

        // Go to location or Personal Items
        personalButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), LocationListActivity.class);
                startActivity(i);
            }
        });
    }
}
