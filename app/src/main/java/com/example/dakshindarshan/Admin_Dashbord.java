package com.example.dakshindarshan;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;

public class Admin_Dashbord extends AppCompatActivity {
    CardView state,place,book,feedback,logout,complete,hotel,shoping;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_dashbord);

        state=findViewById(R.id.uploadstate);
        place=findViewById(R.id.uploadplace);
        book=findViewById(R.id.booked);
        feedback=findViewById(R.id.feedback);
        logout=findViewById(R.id.adminlogout);
        complete=findViewById(R.id.admincomplete);
hotel=findViewById(R.id.uploadresturent);
shoping=findViewById(R.id.uploadshoping);

hotel.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent intent = new Intent(Admin_Dashbord.this, Upload_Hotel.class);
        startActivity(intent);
    }
});


shoping.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent intent = new Intent(Admin_Dashbord.this, Shoping_Upload.class);
        startActivity(intent);
    }
});


        complete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
         /*       Intent intent = new Intent(Admin_Dashbord.this, Admin_Complete.class);
                startActivity(intent);*/
            }
        });


        state.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Admin_Dashbord.this, Upload_State.class);
                startActivity(intent);
            }
        });

        place.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Admin_Dashbord.this, Upload_Place.class);
                startActivity(intent);

            }
        });

        book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Admin_Dashbord.this, Complete_Booking.class);
                startActivity(intent);
            }
        });


        feedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Admin_Dashbord.this, Feed_Back.class);
                startActivity(intent);
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(Admin_Dashbord.this, Login_Page.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });


    }
}