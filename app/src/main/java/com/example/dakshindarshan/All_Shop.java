package com.example.dakshindarshan;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class All_Shop extends AppCompatActivity {
    FirebaseFirestore database = FirebaseFirestore.getInstance();
    List<Shopmodel> shopmodelList;
    Shopadapter shopadapter;
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_shop);
        database = FirebaseFirestore.getInstance();
        String type = getIntent().getStringExtra("state");
        recyclerView = findViewById(R.id.allrec);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        ;

        shopmodelList = new ArrayList<>();
        shopadapter = new Shopadapter(getApplicationContext(), shopmodelList);
        recyclerView.setAdapter(shopadapter);

        // Retrieving the state value in ViewAll_Activity
        SharedPreferences preferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        String state = preferences.getString("state", ""); // Default value is empty string if not found




        //if (type != null && type.equalsIgnoreCase("Email")) {
        database.collection("Shoping").whereEqualTo("state", state)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Shopmodel feedmodel = document.toObject(Shopmodel.class);
                                shopmodelList.add(feedmodel);
                                // product_adapter.notifyDataSetChanged();
                                shopadapter.notifyDataSetChanged();
                            }
                        } else {
                            Toast.makeText(getApplication(), "Error", Toast.LENGTH_SHORT).show();
                        }
                    }

                });

        if (type != null && type.equalsIgnoreCase("Telangana")) {
            //if (type != null && type.equalsIgnoreCase("Email")) {
            database.collection("Shoping").whereEqualTo("state", "Telangana")
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()) {
                                for (QueryDocumentSnapshot document : task.getResult()) {
                                    Shopmodel feedmodel = document.toObject(Shopmodel.class);
                                    shopmodelList.add(feedmodel);
                                    // product_adapter.notifyDataSetChanged();
                                    shopadapter.notifyDataSetChanged();
                                }
                            } else {
                                Toast.makeText(getApplication(), "Error", Toast.LENGTH_SHORT).show();
                            }
                        }

                    });


        }
        if (type != null && type.equalsIgnoreCase("Andhrapradesh")) {
            //if (type != null && type.equalsIgnoreCase("Email")) {
            database.collection("Shoping").whereEqualTo("state", "Andhrapradesh")
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()) {
                                for (QueryDocumentSnapshot document : task.getResult()) {
                                    Shopmodel feedmodel = document.toObject(Shopmodel.class);
                                    shopmodelList.add(feedmodel);
                                    // product_adapter.notifyDataSetChanged();
                                    shopadapter.notifyDataSetChanged();
                                }
                            } else {
                                Toast.makeText(getApplication(), "Error", Toast.LENGTH_SHORT).show();
                            }
                        }

                    });


        }
        if (type != null && type.equalsIgnoreCase("Karnataka")) {
            //if (type != null && type.equalsIgnoreCase("Email")) {
            database.collection("Shoping").whereEqualTo("state", "Karnataka")
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()) {
                                for (QueryDocumentSnapshot document : task.getResult()) {
                                    Shopmodel feedmodel = document.toObject(Shopmodel.class);
                                    shopmodelList.add(feedmodel);
                                    // product_adapter.notifyDataSetChanged();
                                    shopadapter.notifyDataSetChanged();
                                }
                            } else {
                                Toast.makeText(getApplication(), "Error", Toast.LENGTH_SHORT).show();
                            }
                        }

                    });


        }
        if (type != null && type.equalsIgnoreCase("Tamil nadu")) {
            //if (type != null && type.equalsIgnoreCase("Email")) {
            database.collection("Shoping").whereEqualTo("state", "Tamil nadu")
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()) {
                                for (QueryDocumentSnapshot document : task.getResult()) {
                                    Shopmodel feedmodel = document.toObject(Shopmodel.class);
                                    shopmodelList.add(feedmodel);
                                    // product_adapter.notifyDataSetChanged();
                                    shopadapter.notifyDataSetChanged();
                                }
                            } else {
                                Toast.makeText(getApplication(), "Error", Toast.LENGTH_SHORT).show();
                            }
                        }

                    });


        }
        if (type != null && type.equalsIgnoreCase("Goa")) {
            //if (type != null && type.equalsIgnoreCase("Email")) {
            database.collection("Shoping").whereEqualTo("state", "Goa")
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()) {
                                for (QueryDocumentSnapshot document : task.getResult()) {
                                    Shopmodel feedmodel = document.toObject(Shopmodel.class);
                                    shopmodelList.add(feedmodel);
                                    // product_adapter.notifyDataSetChanged();
                                    shopadapter.notifyDataSetChanged();
                                }
                            } else {
                                Toast.makeText(getApplication(), "Error", Toast.LENGTH_SHORT).show();
                            }
                        }

                    });


        }
        if (type != null && type.equalsIgnoreCase("Kerala")) {
            //if (type != null && type.equalsIgnoreCase("Email")) {
            database.collection("Shoping").whereEqualTo("state", "Kerala")
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()) {
                                for (QueryDocumentSnapshot document : task.getResult()) {
                                    Shopmodel feedmodel = document.toObject(Shopmodel.class);
                                    shopmodelList.add(feedmodel);
                                    // product_adapter.notifyDataSetChanged();
                                    shopadapter.notifyDataSetChanged();
                                }
                            } else {
                                Toast.makeText(getApplication(), "Error", Toast.LENGTH_SHORT).show();
                            }
                        }

                    });


        }



    }
}