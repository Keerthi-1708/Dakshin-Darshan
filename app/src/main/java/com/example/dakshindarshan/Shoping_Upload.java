package com.example.dakshindarshan;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.GeoPoint;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Shoping_Upload extends AppCompatActivity implements OnMapReadyCallback, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, com.google.android.gms.location.LocationListener, AdapterView.OnItemSelectedListener{
    Spinner spinner;
    String[] page = {"Choose Option","Maharashtra","Telangana","Andhrapradesh","Karnataka","Tamil nadu","Goa","Kerala"};
    TextView textView;




    private SearchView searchView;
    private static final double DEFAULT_LATITUDE = 37.7749; // Default latitude (e.g., San Francisco)
    private static final double DEFAULT_LONGITUDE = -122.4194;
    AutoCompleteTextView searchAutoCompleteTextView;

    private TextView addressTextView;
    TextView ambulacemap;


    Activity mContext = this;

    ImageView uploadPicIV;
    private ProgressBar uploadProgressBar;
    final int IMAGE_REQUEST = 71;
    Uri imageLocationPath;
    StorageReference objectStorageReference;
    FirebaseFirestore objectFirebaseFirestore;
    private GoogleMap mMap;
    GoogleApiClient mGoogleApiClient;
    Location mLastLocation;
    com.google.android.gms.location.LocationRequest mLocationRequest;
    private int REQUEST_CODE = 11;
    SupportMapFragment mapFragment;
    EditText mFullName,dirtick,description,local,service,language,avilaty,price,guide;
    Button mSubmitBtn;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    String userID;
    public static final String TAG = "TAG";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shoping_upload);
        dirtick=findViewById(R.id.dirtick);
        description=findViewById(R.id.description);
        local=findViewById(R.id.localarea);
        service=findViewById(R.id.service);
        language=findViewById(R.id.language);
        avilaty=findViewById(R.id.avilaty);
        price=findViewById(R.id.price);
        guide=findViewById(R.id.guidename);


        searchView = findViewById(R.id.searchView);
        searchAutoCompleteTextView = searchView.findViewById(R.id.searchAutoCompleteTextView);
        searchAutoCompleteTextView.requestFocus();

        searchAutoCompleteTextView.setHint("Search for a location");
        addressTextView = findViewById(R.id.addressTextView);
        ambulacemap=findViewById(R.id.ambulanceloc);



        spinner=findViewById(R.id.spineerr);
        textView=findViewById(R.id.textt);
        spinner.setOnItemSelectedListener(this);
        ArrayAdapter adapterr = new ArrayAdapter(this, android.R.layout.simple_spinner_item,page);
        adapterr.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapterr);







        mFullName = findViewById(R.id.donorname);
        mSubmitBtn=findViewById(R.id.submit);

        uploadPicIV = findViewById(R.id.imageID);




        uploadProgressBar = findViewById(R.id.progress_bar);

        objectStorageReference = FirebaseStorage.getInstance().getReference("imageFolder"); // Create folder to Firebase Storage
        objectFirebaseFirestore = FirebaseFirestore.getInstance();

        fAuth=FirebaseAuth.getInstance();
        fStore= FirebaseFirestore.getInstance();

        mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.google_map);
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            mapFragment.getMapAsync(this);
        } else {
            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_CODE);
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, new ArrayList<String>());
        searchAutoCompleteTextView.setAdapter(adapter);

        searchAutoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Handle item selection from auto-suggestions
                String selectedLocation = (String) parent.getItemAtPosition(position);
                searchLocation(selectedLocation);

            }
        });



        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                searchLocation(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                updateAutoSuggestions(newText);

                return false;
            }
        });






    }

    private void updateAutoSuggestions(String newText) {
        // Implement logic to fetch and update auto-suggestions based on the query
        // You may use a web service or a local database to get suggestions
        // For simplicity, let's use a dummy list as an example
        List<String> suggestions = getDummySuggestions(newText);

        ArrayAdapter<String> adapter = (ArrayAdapter<String>) searchAutoCompleteTextView.getAdapter();
        adapter.clear();
        adapter.addAll(suggestions);
        adapter.notifyDataSetChanged();
    }

    private List<String> getDummySuggestions(String newText) {
        List<String> suggestions = new ArrayList<>();
        suggestions.add(newText + " suggestion 1");
        suggestions.add(newText + " suggestion 2");
        suggestions.add(newText + " suggestion 3");
        return suggestions;
    }

    private void searchLocation(String query) {


        Geocoder geocoder = new Geocoder(this, Locale.getDefault());

        try {
            List<Address> addressList = geocoder.getFromLocationName(query, 1);
            if (!addressList.isEmpty()) {
                Address address = addressList.get(0);
                LatLng searchedLocation = new LatLng(address.getLatitude(), address.getLongitude());
                mMap.clear();
                mMap.addMarker(new MarkerOptions().position(searchedLocation).title(query));

                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(searchedLocation, 15));

                // Display the selected address
                addressTextView.setText(address.getAddressLine(0));
                updateTextViewWithLatLng(searchedLocation);
            } else {
                Toast.makeText(this, "Location not found for: " + query, Toast.LENGTH_SHORT).show();
            }
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, "Error searching for location: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }


    }

    private void updateTextViewWithLatLng(LatLng defaultLocation) {
        double latitude = defaultLocation.latitude;
        double longitude = defaultLocation.longitude;

        String latitudeText = String.format("%.7f° N", Math.abs(latitude));
        String longitudeText = String.format("%.7f° E", Math.abs(longitude));



        String formattedLocation = "[" + latitudeText + ", " + longitudeText + "]";

// Assuming you have a TextView named textViewLocation
        addressTextView.setText(formattedLocation);
        addressTextView.setText(latitudeText + "\n" + longitudeText);

    }


    public void selectImage(View view) {
        try {
            Intent objectIntent = new Intent();
            objectIntent.setType("image/*");

            objectIntent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(objectIntent, IMAGE_REQUEST);

        } catch (Exception e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        try {
            if (requestCode == IMAGE_REQUEST
                    && resultCode == RESULT_OK
                    && data != null
                    && data.getData() != null) {
                imageLocationPath = data.getData();
                Bitmap objectBitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), imageLocationPath);
                uploadPicIV.setImageBitmap(objectBitmap);

            }

        } catch (Exception e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        buildGoogleApiClient();
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        mMap.setMyLocationEnabled(true);
    }

    protected synchronized void buildGoogleApiClient(){
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
        mGoogleApiClient.connect();
    }

    @Override
    public void onLocationChanged(@NonNull Location location) {
        mLastLocation = location;
        LatLng latLng = new LatLng(location.getLatitude(),location.getLongitude());

        //MarkerOptions markerOptions1 = new MarkerOptions().position(latLng).title("You are here");
        //mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        //mMap.animateCamera(CameraUpdateFactory.zoomTo(15));
        //mMap.addMarker(markerOptions1).showInfoWindow();
        String busname = mFullName.getText().toString();

        MarkerOptions markerOptions = new MarkerOptions().position(latLng).title("busname").snippet(busname);
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng,15));
        mMap.addMarker(markerOptions).showInfoWindow();





        mSubmitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    if (/*!uploadPicET.getText().toString().isEmpty() && */imageLocationPath != null) {
                        //String nameOfimage = "." + getExtension(imageLocationPath);
                        String uuid = UUID.randomUUID().toString();
                        String nameOfimage = uuid.toString() + "." + getExtension(imageLocationPath);
                        uploadProgressBar.setVisibility(View.VISIBLE);
                        uploadProgressBar.setIndeterminate(true);
                        final StorageReference imageRef = objectStorageReference.child(nameOfimage);

                        UploadTask objectUploadTask = imageRef.putFile(imageLocationPath);

                        objectUploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                            @Override
                            public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                                if (!task.isSuccessful()) {

                                    throw task.getException();

                                }
                                return imageRef.getDownloadUrl();
                            }
                        }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                            @RequiresApi(api = Build.VERSION_CODES.O)
                            @Override
                            public void onComplete(@NonNull Task<Uri> task) {
                                if (task.isSuccessful()) {

                                    String saveCurrentDate, saveCurrenttime;
                                    Calendar calForDate = Calendar.getInstance();


                                    SimpleDateFormat currentDate = new SimpleDateFormat("MM, dd, yyyy");
                                    saveCurrentDate = currentDate.format(calForDate.getTime());
                                    SimpleDateFormat currenttime = new SimpleDateFormat("HH:mm:ss a");
                                    saveCurrenttime = currenttime.format(calForDate.getTime());
                                    String typename = textView.getText().toString();

                                    String fullname = mFullName.getText().toString().trim();

                                    String address = addressTextView.getText().toString();

                                    String dirtick_ = dirtick.getText().toString();
                                    String description_ = description.getText().toString();
                                    String local_ = local.getText().toString();
                                    String service_ = service.getText().toString();
                                    String language_ = language.getText().toString();
                                    String avilaty_ = avilaty.getText().toString();
                                    String price_ = price.getText().toString();
                                    String guide_ = guide.getText().toString();
// Define a pattern to match numeric values and direction indicators
                                    Pattern pattern = Pattern.compile("[-+]?\\d*\\.?\\d+|[NSWE]");

// Convert addressTextView's text to GeoPoint
                                    Matcher matcherAddress = pattern.matcher(address);

                                    double latitudeAddress = 0.0;
                                    double longitudeAddress = 0.0;
                                    boolean isLatitudeNegativeAddress = false;
                                    boolean isLongitudeNegativeAddress = false;

                                    while (matcherAddress.find()) {
                                        String match = matcherAddress.group();
                                        if (match.equals("N") || match.equals("S")) {
                                            isLatitudeNegativeAddress = match.equals("S");
                                        } else if (match.equals("E") || match.equals("W")) {
                                            isLongitudeNegativeAddress = match.equals("W");
                                        } else {
                                            double value = Double.parseDouble(match);
                                            if (latitudeAddress == 0.0) {
                                                latitudeAddress = value;
                                            } else {
                                                longitudeAddress = value;
                                            }
                                        }
                                    }

// Adjust latitude and longitude based on direction
                                    if (isLatitudeNegativeAddress) {
                                        latitudeAddress = -latitudeAddress;
                                    }

                                    if (isLongitudeNegativeAddress) {
                                        longitudeAddress = -longitudeAddress;
                                    }

// Create GeoPoint object for address
                                    GeoPoint geoPointFromAddress = new GeoPoint(latitudeAddress, longitudeAddress);

                                    Matcher matcherAmbula = pattern.matcher(address);

                                    double latitudeAmbula = 0.0;
                                    double longitudeAmbula = 0.0;
                                    boolean isLatitudeNegativeAmbula = false;
                                    boolean isLongitudeNegativeAmbula = false;

                                    while (matcherAmbula.find()) {
                                        String match = matcherAmbula.group();
                                        if (match.equals("N") || match.equals("S")) {
                                            isLatitudeNegativeAmbula = match.equals("S");
                                        } else if (match.equals("E") || match.equals("W")) {
                                            isLongitudeNegativeAmbula = match.equals("W");
                                        } else {
                                            double value = Double.parseDouble(match);
                                            if (latitudeAmbula == 0.0) {
                                                latitudeAmbula = value;
                                            } else {
                                                longitudeAmbula = value;
                                            }
                                        }
                                    }

// Adjust latitude and longitude based on direction
                                    if (isLatitudeNegativeAmbula) {
                                        latitudeAmbula = -latitudeAmbula;
                                    }

                                    if (isLongitudeNegativeAmbula) {
                                        longitudeAmbula = -longitudeAmbula;
                                    }


                                    if(TextUtils.isEmpty(fullname))
                                    {
                                        mFullName.setError("Name is Required.");
                                        return;
                                    }

                                 /*   if(TextUtils.isEmpty(fooditem))
                                    {
                                        mFoodItem.setError("Required.");
                                        return;
                                    }

                                    if(phone.length() < 10)
                                    {
                                        mPhone.setError("Phone Number Must be >=10 Characters");
                                        return;
                                    }
*/
                                    // userID = fAuth.getCurrentUser().getUid();
                                    //DocumentReference documentReference = fStore.collection("donate").document(userID);
                                    CollectionReference collectionReference = fStore.collection("user data");

                                    GeoPoint geoPoint = new GeoPoint(location.getLatitude(),location.getLongitude());
                                    String uuid = UUID.randomUUID().toString();



                                    Map<String, Object> objectMap = new HashMap<>();
                                    String docId = objectFirebaseFirestore.collection("data").document().getId();
                                    objectMap.put("uri", task.getResult().toString());
                                    objectMap.put("docId", uuid);
                                    objectMap.put("address", geoPointFromAddress);
                                    objectMap.put("date", saveCurrentDate);
                                    objectMap.put("time", saveCurrenttime);
                                    objectMap.put("shopname",fullname);
                                    objectMap.put("closing",description_);
                                    objectMap.put("opening",dirtick_);
                                    objectMap.put("mens",local_);
                                    objectMap.put("service",service_);
                                    objectMap.put("language",language_);
                                    objectMap.put("contact",avilaty_);
                                    objectMap.put("owner",price_);
                                    objectMap.put("about",guide_);
                                    objectMap.put("state",typename);
                                    objectMap.put("location",geoPoint);
                                    objectMap.put("userid",userID);

                                    objectFirebaseFirestore.collection("Shoping").document(uuid)
                                            .set(objectMap)
                                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                @Override
                                                public void onSuccess(Void unused) {

                                                    uploadProgressBar.setVisibility(View.GONE);
                                                    uploadProgressBar.setIndeterminate(false);
                                                    uploadProgressBar.setProgress(0);
                                                    //    Toast.makeText(Upload_Bus.this, ""+geoPointFromAddress, Toast.LENGTH_SHORT).show();
                                                    AlertDialog.Builder alert = new AlertDialog.Builder(mContext);
                                                    alert.setMessage("Shop Upload Sucessful");
                                                    alert.setCancelable(false);
                                                    alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                                        @Override
                                                        public void onClick(DialogInterface dialog, int which) {
                                                            dialog.dismiss();

                                                            /*    Intent intent = new Intent(Exam.this, Institute_Dashboard.class);
                                                                startActivity(intent);
                                                                finish();*/
                                                        }
                                                    });
                                                    alert.show();
                                                    //  Toast.makeText(Student_Registration.this, "Upload Sucessfully", Toast.LENGTH_SHORT).show();
                                                }
                                            });


                                } else if (!task.isSuccessful()) {
                                    Toast.makeText(Shoping_Upload.this, task.getException().toString(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        });


                    } else {
                        //    Toast.makeText(this, "Please provide name for image", Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    // Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }




            }
        });
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        mLocationRequest = new com.google.android.gms.location.LocationRequest();
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, this);

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                mapFragment.getMapAsync(this);
            } else {
                Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private String getExtension(Uri uri) {
        try {
            ContentResolver objectContentResolver = getContentResolver();
            MimeTypeMap objectMimeTypeMap = MimeTypeMap.getSingleton();

            return objectMimeTypeMap.getExtensionFromMimeType(objectContentResolver.getType(uri));

        } catch (Exception e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
        return null;


    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text = spinner.getSelectedItem().toString();
        textView.setText(text);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
