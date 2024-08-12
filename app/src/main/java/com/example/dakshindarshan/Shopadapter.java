package com.example.dakshindarshan;

import android.content.Context;
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

public class Shopadapter  extends RecyclerView.Adapter<Shopadapter.ViewHolder>{

    private Context context;
    private List<Shopmodel> shopmodelList;

    public Shopadapter(Context context, List<Shopmodel> shopmodelList) {
        this.context = context;
        this.shopmodelList = shopmodelList;
    }

    @NonNull
    @Override
    public Shopadapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new Shopadapter.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.shop, parent, false));

    }

    @Override
    public void onBindViewHolder(@NonNull Shopadapter.ViewHolder holder, int position) {
        Glide.with(context).load(shopmodelList.get(position).getUri()).into(holder.imageView);
        holder.resturant.setText(shopmodelList.get(position).getShopname());
        holder.close.setText(shopmodelList.get(position).getClosing());
        holder.opening.setText(shopmodelList.get(position).getOpening());
        holder.veg.setText(shopmodelList.get(position).getMens());
        holder.service.setText(shopmodelList.get(position).getService());
        holder.language.setText(shopmodelList.get(position).getLanguage());
        holder.contact.setText(shopmodelList.get(position).getContact());
        holder.owner.setText(shopmodelList.get(position).getOwner());
        holder.detail.setText(shopmodelList.get(position).getAbout());
    }

    @Override
    public int getItemCount() {
        return shopmodelList.size();
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

        public void bindData(Shopmodel location) {
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
            Shopmodel location = shopmodelList.get(getAdapterPosition());
            bindData(location);

        }

    }
}
