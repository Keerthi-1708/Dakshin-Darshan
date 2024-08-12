package com.example.dakshindarshan;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class User_Dashboard extends AppCompatActivity {

    CardView place,resturant,shoping;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_dashboard);

        place=findViewById(R.id.u_uploadplace);
        resturant=findViewById(R.id.u_uploadresturent);
        shoping=findViewById(R.id.u_uploadshoping);

        place.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(User_Dashboard.this, ViewAll_Activity.class);
                startActivity(intent);
            }
        });


        resturant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(User_Dashboard.this, All_Resturant.class);
                startActivity(intent);
            }
        });

        shoping.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(User_Dashboard.this, All_Shop.class);
                startActivity(intent);
            }
        });

    }
}