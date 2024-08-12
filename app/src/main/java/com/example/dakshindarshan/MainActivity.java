package com.example.dakshindarshan;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import com.example.dakshindarshan.databinding.ActivityMainBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class MainActivity extends AppCompatActivity {
    private FirebaseAuth firebaseAuth;
    TextView name;
    ImageView imageView;
    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        firebaseAuth = FirebaseAuth.getInstance();
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.appBarMain.toolbar);
        binding.appBarMain.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home,R.id.about,R.id.myBooking,R.id.contact,R.id.term,R.id.version,R.id.profile2)
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);


        View navHeaderView = navigationView.getHeaderView(0);
        name = navHeaderView.findViewById(R.id.name);
        imageView = navHeaderView.findViewById(R.id.imageView);

        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();


        SharedPreferences sharedPreferences = getSharedPreferences("Hello", MODE_PRIVATE);

        // Retrieve data from SharedPreferences
        String cou = sharedPreferences.getString("uid", "");



        //  DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Register").child(uid);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference usersRef = database.getReference("Usersregister");


        Query query = usersRef.orderByChild("email").equalTo(cou);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot userSnapshot : dataSnapshot.getChildren()) {
                    // Retrieve data
                    String nam = userSnapshot.child("name").getValue(String.class);
                    String imageUrl = userSnapshot.child("profileImg").getValue(String.class);

// Check if imageUrl is not null to avoid potential NullPointerException
                    if (imageUrl != null) {
                        ImageView imageView = findViewById(R.id.imageView);

                        Picasso.get().load(imageUrl).into(imageView);
                    }

                    // Do something with the data (e.g., display it in a TextView)
                    name.setText(nam);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Handle errors
                //  Log.e(TAG, "Error reading data from Firebase: " + databaseError.getMessage());
            }
        });








    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();

        if (itemId == R.id.action_settings) {
            firebaseAuth.signOut();
            startActivity(new Intent(MainActivity.this, Login_Page.class));
            finish();
            return true;
        }/* else if (itemId == R.id.menu_item2) {
                // Handle menu item 2 click
                return true;
            } else if (itemId == R.id.menu_item3) {
                // Handle menu item 3 click
                return true;
            }
*/
        return super.onOptionsItemSelected(item);

    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
}