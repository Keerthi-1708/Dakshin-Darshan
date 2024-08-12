package com.example.dakshindarshan;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

public class Resturantadapter extends RecyclerView.Adapter<Resturantadapter.ViewHolder>{

    private Context context;
    private List<Resturantmodel> resturantmodelList;

    public Resturantadapter(Context context, List<Resturantmodel> resturantmodelList) {
        this.context = context;
        this.resturantmodelList = resturantmodelList;
    }

    @NonNull
    @Override
    public Resturantadapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new Resturantadapter.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.resturant, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull Resturantadapter.ViewHolder holder, int position) {
        Glide.with(context).load(resturantmodelList.get(position).getUri()).into(holder.imageView);
        holder.resturant.setText(resturantmodelList.get(position).getHotelname());
        holder.close.setText(resturantmodelList.get(position).getClosetiming());
        holder.opening.setText(resturantmodelList.get(position).getOpeningtime());
        holder.veg.setText(resturantmodelList.get(position).getVegnonveg());
        holder.service.setText(resturantmodelList.get(position).getService());
        holder.language.setText(resturantmodelList.get(position).getLanguage());
        holder.contact.setText(resturantmodelList.get(position).getContact());
        holder.owner.setText(resturantmodelList.get(position).getOwnername());
        holder.detail.setText(resturantmodelList.get(position).getDescription());


        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });






    }

    @Override
    public int getItemCount() {
        return resturantmodelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements OnMapReadyCallback {
        TextView resturant,close,opening,veg,service,language,contact,owner,detail;

        ImageView imageView;
        Button button;
        private GoogleMap googleMap;
        private MapView mapView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            resturant=itemView.findViewById(R.id.r_resturant);
            close=itemView.findViewById(R.id.r_closing);
            opening=itemView.findViewById(R.id.r_opening);
            veg=itemView.findViewById(R.id.r_vegnonveg);
            service=itemView.findViewById(R.id.r_service);
            language=itemView.findViewById(R.id.r_language);
            contact=itemView.findViewById(R.id.r_contact);
            owner=itemView.findViewById(R.id.r_ownername);
            detail=itemView.findViewById(R.id.r_description);
            imageView=itemView.findViewById(R.id.mageview);
            mapView = itemView.findViewById(R.id.mapView);
            mapView.onCreate(null);
            mapView.getMapAsync(this);
            button=itemView.findViewById(R.id.bookingbtn);
        }

        public void bindData(Resturantmodel location) {
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
            Resturantmodel location = resturantmodelList.get(getAdapterPosition());
            bindData(location);

        }

    }
}
