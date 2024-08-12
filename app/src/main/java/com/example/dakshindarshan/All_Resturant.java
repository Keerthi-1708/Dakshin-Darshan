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

public class All_Resturant extends AppCompatActivity {
    FirebaseFirestore database = FirebaseFirestore.getInstance();
    List<Resturantmodel> resturantmodelList;
    Resturantadapter resturantadapter;
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_resturant);
        database = FirebaseFirestore.getInstance();
        String type = getIntent().getStringExtra("state");
        recyclerView = findViewById(R.id.allrec);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        ;

        resturantmodelList = new ArrayList<>();
        resturantadapter = new Resturantadapter(getApplicationContext(), resturantmodelList);
        recyclerView.setAdapter(resturantadapter);

        // Retrieving the state value in ViewAll_Activity
        SharedPreferences preferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        String state = preferences.getString("state", ""); // Default value is empty string if not found




            //if (type != null && type.equalsIgnoreCase("Email")) {
            database.collection("Hotel").whereEqualTo("state", state)
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()) {
                                for (QueryDocumentSnapshot document : task.getResult()) {
                                    Resturantmodel feedmodel = document.toObject(Resturantmodel.class);
                                    resturantmodelList.add(feedmodel);
                                    // product_adapter.notifyDataSetChanged();
                                    resturantadapter.notifyDataSetChanged();
                                }
                            } else {
                                Toast.makeText(getApplication(), "Error", Toast.LENGTH_SHORT).show();
                            }
                        }

                    });

        if (type != null && type.equalsIgnoreCase("Telangana")) {
            //if (type != null && type.equalsIgnoreCase("Email")) {
            database.collection("Hotel").whereEqualTo("state", "Telangana")
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()) {
                                for (QueryDocumentSnapshot document : task.getResult()) {
                                    Resturantmodel feedmodel = document.toObject(Resturantmodel.class);
                                    resturantmodelList.add(feedmodel);
                                    // product_adapter.notifyDataSetChanged();
                                    resturantadapter.notifyDataSetChanged();
                                }
                            } else {
                                Toast.makeText(getApplication(), "Error", Toast.LENGTH_SHORT).show();
                            }
                        }

                    });


        }
        if (type != null && type.equalsIgnoreCase("Andhrapradesh")) {
            //if (type != null && type.equalsIgnoreCase("Email")) {
            database.collection("Hotel").whereEqualTo("state", "Andhrapradesh")
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()) {
                                for (QueryDocumentSnapshot document : task.getResult()) {
                                    Resturantmodel feedmodel = document.toObject(Resturantmodel.class);
                                    resturantmodelList.add(feedmodel);
                                    // product_adapter.notifyDataSetChanged();
                                    resturantadapter.notifyDataSetChanged();
                                }
                            } else {
                                Toast.makeText(getApplication(), "Error", Toast.LENGTH_SHORT).show();
                            }
                        }

                    });


        }
        if (type != null && type.equalsIgnoreCase("Karnataka")) {
            //if (type != null && type.equalsIgnoreCase("Email")) {
            database.collection("Hotel").whereEqualTo("state", "Karnataka")
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()) {
                                for (QueryDocumentSnapshot document : task.getResult()) {
                                    Resturantmodel feedmodel = document.toObject(Resturantmodel.class);
                                    resturantmodelList.add(feedmodel);
                                    // product_adapter.notifyDataSetChanged();
                                    resturantadapter.notifyDataSetChanged();
                                }
                            } else {
                                Toast.makeText(getApplication(), "Error", Toast.LENGTH_SHORT).show();
                            }
                        }

                    });


        }
        if (type != null && type.equalsIgnoreCase("Tamil nadu")) {
            //if (type != null && type.equalsIgnoreCase("Email")) {
            database.collection("Hotel").whereEqualTo("state", "Tamil nadu")
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()) {
                                for (QueryDocumentSnapshot document : task.getResult()) {
                                    Resturantmodel feedmodel = document.toObject(Resturantmodel.class);
                                    resturantmodelList.add(feedmodel);
                                    // product_adapter.notifyDataSetChanged();
                                    resturantadapter.notifyDataSetChanged();
                                }
                            } else {
                                Toast.makeText(getApplication(), "Error", Toast.LENGTH_SHORT).show();
                            }
                        }

                    });


        }
        if (type != null && type.equalsIgnoreCase("Goa")) {
            //if (type != null && type.equalsIgnoreCase("Email")) {
            database.collection("Hotel").whereEqualTo("state", "Goa")
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()) {
                                for (QueryDocumentSnapshot document : task.getResult()) {
                                    Resturantmodel feedmodel = document.toObject(Resturantmodel.class);
                                    resturantmodelList.add(feedmodel);
                                    // product_adapter.notifyDataSetChanged();
                                    resturantadapter.notifyDataSetChanged();
                                }
                            } else {
                                Toast.makeText(getApplication(), "Error", Toast.LENGTH_SHORT).show();
                            }
                        }

                    });


        }
        if (type != null && type.equalsIgnoreCase("Kerala")) {
            //if (type != null && type.equalsIgnoreCase("Email")) {
            database.collection("Hotel").whereEqualTo("state", "Kerala")
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()) {
                                for (QueryDocumentSnapshot document : task.getResult()) {
                                    Resturantmodel feedmodel = document.toObject(Resturantmodel.class);
                                    resturantmodelList.add(feedmodel);
                                    // product_adapter.notifyDataSetChanged();
                                    resturantadapter.notifyDataSetChanged();
                                }
                            } else {
                                Toast.makeText(getApplication(), "Error", Toast.LENGTH_SHORT).show();
                            }
                        }

                    });


        }



    }
}