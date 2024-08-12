package com.example.dakshindarshan;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AllPlaceadapter extends RecyclerView.Adapter<AllPlaceadapter.ViewHolder>{
    private Context context;
    private List<Placemodel> placemodelList;
    FirebaseAuth firebaseAuth;

    public AllPlaceadapter(Context context, List<Placemodel> placemodelList) {
        this.context = context;
        this.placemodelList = placemodelList;
        firebaseAuth=FirebaseAuth.getInstance();
    }

    @NonNull
    @Override
    public AllPlaceadapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new AllPlaceadapter.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.place, parent, false));

    }

    @Override
    public void onBindViewHolder(@NonNull AllPlaceadapter.ViewHolder holder, int position) {
        Glide.with(context).load(placemodelList.get(position).getUri()).into(holder.imageView);
        holder.guide.setText(placemodelList.get(position).getGuidename());
        holder.avi.setText(placemodelList.get(position).getAviblity());
        holder.des.setText(placemodelList.get(position).getDescription());
        holder.dir.setText(placemodelList.get(position).getDistrict());
        holder.lan.setText(placemodelList.get(position).getLanguage());
        holder.loc.setText(placemodelList.get(position).getLocalarea());
        holder.pla.setText(placemodelList.get(position).getPlacename());
        holder.pri.setText(placemodelList.get(position).getPrice());
        holder.ser.setText(placemodelList.get(position).getService());




holder.button.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        saveDataToSharedPreferences(placemodelList.get(position));

        insertDataIntoFirestore(placemodelList.get(position));
        Intent intent = new Intent(context, Details_Page.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }
});





    }

    private void saveDataToSharedPreferences(Placemodel placemodel) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        // Store data using unique keys
        // Store all data using unique keys
        editor.putString("guidename", placemodel.getGuidename());
        editor.putString("avilaty", placemodel.getAviblity());
        editor.putString("description", placemodel.getDescription());
        editor.putString("district", placemodel.getDistrict());
        editor.putString("language", placemodel.getLanguage());
        editor.putString("localarea", placemodel.getLocalarea());
        editor.putString("placename", placemodel.getPlacename());
        editor.putString("price", placemodel.getPrice());
        editor.putString("service", placemodel.getService());
        // Add more lines for other data fields

        editor.apply();
    }


    private void insertDataIntoFirestore(Placemodel selectedPlace) {

        // Assuming you have a Firestore instance initialized
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        // Create a new document with a unique ID
        DocumentReference newPlaceRef = db.collection("Booking").document();

        // Create a Map to store your data
        Map<String, Object> placeData = new HashMap<>();
        placeData.put("guidename", selectedPlace.getGuidename());
        placeData.put("aviblity", selectedPlace.getAviblity());
        placeData.put("description", selectedPlace.getDescription());
        placeData.put("address", selectedPlace.getAddress());
        placeData.put("sendedBy",firebaseAuth.getCurrentUser().getEmail());
        // Add other fields as needed

        // Set the data to Firestore
        newPlaceRef.set(placeData)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        // Data successfully added to Firestore
                      /*  Toast.makeText(context, "Booking successful!", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(context, MainActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        context.startActivity(intent);*/
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        // Handle failures
                      //  Log.e(TAG, "Error adding document", e);
                        Toast.makeText(context, "Booking failed. Please try again.", Toast.LENGTH_SHORT).show();
                    }
                });

    }

    @Override
    public int getItemCount() {
        return placemodelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements OnMapReadyCallback {

        TextView guide,avi,des,dir,lan,loc,pla,pri,ser;
        ImageView imageView;
        Button button;
        private GoogleMap googleMap;
        private MapView mapView;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            guide=itemView.findViewById(R.id.guidename);
            avi=itemView.findViewById(R.id.availablty);
            des=itemView.findViewById(R.id.descrip);
            dir=itemView.findViewById(R.id.district);
            lan=itemView.findViewById(R.id.language);
            loc=itemView.findViewById(R.id.localarea);
            pla=itemView.findViewById(R.id.place);
            pri=itemView.findViewById(R.id.price);
            ser=itemView.findViewById(R.id.service);
            imageView=itemView.findViewById(R.id.mageview);
            mapView = itemView.findViewById(R.id.mapView);
            mapView.onCreate(null);
            mapView.getMapAsync(this);
            button=itemView.findViewById(R.id.bookingbtn);



        }
        public void bindData(Placemodel location) {
            // Use the location data to set up the map marker or other map interactions
            if (googleMap != null) {
                LatLng latLng = new LatLng(location.getAddress().getLatitude(), location.getAddress().getLongitude());
                googleMap.addMarker(new MarkerOptions().position(latLng).title("Location Marker"));
                googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15));
            }
        }
        @Override
        public void onMapReady(@NonNull GoogleMap googleMap) {
            this.googleMap = googleMap;
            Placemodel location = placemodelList.get(getAdapterPosition());
            bindData(location);

        }



    }


}
