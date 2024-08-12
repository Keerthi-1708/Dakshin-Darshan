package com.example.dakshindarshan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.type.LatLng;

import java.util.HashMap;
import java.util.UUID;

public class Details_Page extends AppCompatActivity {
    TextView guide,avi,des,dir,lan,loc,pla,pri,ser;

    EditText sdate,edate,ppoint,dpoint,days;
    Button sumbit;
    FirebaseFirestore firebaseFirestore;
    FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        firebaseFirestore=FirebaseFirestore.getInstance();
        firebaseAuth=FirebaseAuth.getInstance();



        setContentView(R.layout.activity_details_page);
        guide=findViewById(R.id.guidename);
        avi=findViewById(R.id.availablty);
        des=findViewById(R.id.descrip);
        dir=findViewById(R.id.district);
        lan=findViewById(R.id.language);
        loc=findViewById(R.id.localarea);
        pla=findViewById(R.id.place);
        pri=findViewById(R.id.price);
        ser=findViewById(R.id.service);
        sumbit=findViewById(R.id.makepayment);

        sdate=findViewById(R.id.startdate);
        edate=findViewById(R.id.enddate);
        ppoint=findViewById(R.id.pickuppoint);
        dpoint=findViewById(R.id.drouppoint);
        days=findViewById(R.id.noofdays);




// Assume 'context' is your current activity or application context
        SharedPreferences sharedPreferences = getApplication().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);

// Retrieve data using the same keys used for storing
        String guidename = sharedPreferences.getString("guidename", "");
        String avilaty = sharedPreferences.getString("avilaty", "");
        String description = sharedPreferences.getString("description", "");
        String district = sharedPreferences.getString("district", "");
        String language = sharedPreferences.getString("language", "");
        String localarea = sharedPreferences.getString("localarea", "");
        String placename = sharedPreferences.getString("placename", "");
        String price = sharedPreferences.getString("price", "");
        String service = sharedPreferences.getString("service", "");

// Now, you can use these values as needed

        guide.setText(guidename);
        avi.setText(avilaty);
des.setText(description);
dir.setText(district);
lan.setText(language);
loc.setText(localarea);
pla.setText(placename);;
pri.setText(price);
ser.setText(service);


        sumbit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String startingdate = sdate.getText().toString();
                String enddate = edate.getText().toString();
                String pickuppoint = ppoint.getText().toString();
                String droppoint = dpoint.getText().toString();
                String noofdays = days.getText().toString();

                String uuid = UUID.randomUUID().toString();
                final HashMap<String, Object> objectMap = new HashMap<>();
                objectMap.put("guidename",guidename);
                objectMap.put("aviblity", avilaty);
                objectMap.put("description", description);
                objectMap.put("address", localarea);
                objectMap.put("sendedBy",firebaseAuth.getCurrentUser().getEmail());
                objectMap.put("sartdate",startingdate);
                objectMap.put("enddate",enddate);
                objectMap.put("pickuppoint",pickuppoint);
                objectMap.put("drouppoint",droppoint);
                objectMap.put("days",noofdays);

                objectMap.put("docId",uuid);
                firebaseFirestore.collection("Booked").document(uuid)
                        .set(objectMap)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {

                              /*  Intent intent = new Intent(Fill_Detail.this, Make_Payment.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                intent.putExtra("place",ti);
                                intent.putExtra("price",pri);
                                intent.putExtra("username",na);
                                intent.putExtra("sartdate",startingdate);
                                intent.putExtra("enddate",enddate);
                                intent.putExtra("pickuppoint",pickuppoint);
                                intent.putExtra("drouppoint",droppoint);
                                intent.putExtra("days",noofdays);

                                startActivity(intent);
                                finish();*/
                                Toast.makeText(Details_Page.this, "Booking successful!", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(Details_Page.this, Transportation.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(intent);
                            }
                        });



            }
        });




    }
}