package com.example.dakshindarshan;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

public class Mybookingadapter extends RecyclerView.Adapter<Mybookingadapter.ViewHolder>{
    private Context context;
    private List<Placemodel> placemodelList;

    public Mybookingadapter(Context context, List<Placemodel> placemodelList) {
        this.context = context;
        this.placemodelList = placemodelList;
    }

    @NonNull
    @Override
    public Mybookingadapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new Mybookingadapter.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.mybooking, parent, false));

    }

    @Override
    public void onBindViewHolder(@NonNull Mybookingadapter.ViewHolder holder, int position) {
        holder.guide.setText(placemodelList.get(position).getGuidename());
        holder.avi.setText(placemodelList.get(position).getAviblity());
        holder.des.setText(placemodelList.get(position).getDescription());
        //holder.dir.setText(placemodelList.get(position).getDistrict());
    }

    @Override
    public int getItemCount() {
        return placemodelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements OnMapReadyCallback {
        TextView guide,avi,des,dir;
        private GoogleMap googleMap;
        private MapView mapView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            guide=itemView.findViewById(R.id.guidename);
            avi=itemView.findViewById(R.id.availablty);
            des=itemView.findViewById(R.id.descrip);
            dir=itemView.findViewById(R.id.district);
            mapView = itemView.findViewById(R.id.mapView);
            mapView.onCreate(null);
            mapView.getMapAsync(this);
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
