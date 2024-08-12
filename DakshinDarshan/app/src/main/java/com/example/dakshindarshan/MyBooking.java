package com.example.dakshindarshan;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;


public class MyBooking extends Fragment {

    FirebaseFirestore database = FirebaseFirestore.getInstance();
    RecyclerView productView;
    List<Placemodel> bookingmodelList;
    Mybookingadapter bookingadapter;
    FirebaseAuth auth;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_my_booking, container, false);


        auth=FirebaseAuth.getInstance();
        database=FirebaseFirestore.getInstance();
        productView=root.findViewById(R.id.bookingrec);
        productView.setLayoutManager(new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false));
        bookingmodelList = new ArrayList<>();
        bookingadapter = new Mybookingadapter(getContext(),bookingmodelList);
        productView.setAdapter(bookingadapter);


        database.collection("Booking").whereEqualTo("sendedBy",auth.getCurrentUser().getEmail())
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Placemodel bookingmodel = document.toObject(Placemodel.class);
                                bookingmodelList.add(bookingmodel);
                                bookingadapter.notifyDataSetChanged();
                            }
                        } else {
                            Toast.makeText(getContext(), "Error", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

return root;



    }
}