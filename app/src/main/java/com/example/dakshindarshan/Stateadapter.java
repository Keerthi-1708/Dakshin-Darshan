package com.example.dakshindarshan;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class Stateadapter extends RecyclerView.Adapter<Stateadapter.ViewHolder>{

    private Context context;
    private List<Placemodel> placemodelList;

    public Stateadapter(Context context, List<Placemodel> placemodelList) {
        this.context = context;
        this.placemodelList = placemodelList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.state,parent,false));

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Glide.with(context).load(placemodelList.get(position).getUri()).into(holder.imageView);
        holder.name.setText(placemodelList.get(position).getName());
        holder.state.setText(placemodelList.get(position).getState());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Storing state value using SharedPreferences
                SharedPreferences preferences = context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("state", placemodelList.get(position).getState());
                editor.apply();

// Starting the activity
                Intent intent = new Intent(context, User_Dashboard.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);

               /* Intent intent = new Intent(context, ViewAll_Activity.class);
             intent.putExtra("state",placemodelList.get(position).getState());
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);*/
            }
        });


    }

    @Override
    public int getItemCount() {
        return placemodelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView name,state;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView =itemView.findViewById(R.id.simg);
            name=itemView.findViewById(R.id.sname);
            state=itemView.findViewById(R.id.sstate);
        }
    }
}
