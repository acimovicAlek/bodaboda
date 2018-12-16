package com.bodaboda.bodaboda.activities;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;

import com.bodaboda.bodaboda.classes.LocationClass;
import com.bodaboda.bodaboda.classes.RequestTrip;
import com.bodaboda.bodaboda.classes.RequestTripArguments;
import com.bodaboda.bodaboda.classes.User;
import com.bodaboda.bodaboda.utils.PlaceAutocompleteAdapter;

import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputFilter;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;

import com.bodaboda.bodaboda.R;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.places.AutocompletePrediction;
import com.google.android.gms.location.places.GeoDataClient;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.PlaceBufferResponse;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CustomerMainActivity extends AppCompatActivity implements OnMapReadyCallback {

    public static LatLngBounds LAT_LNG_BOUNDS;
    private FusedLocationProviderClient mFusedLocationClient;
    private Location customerLocation;
    private PlaceAutocompleteAdapter mPlaceAutocompleteAdapter;
    private GeoDataClient mGeoDataClient;
    private SupportMapFragment mMapFragment;
    private static final String TAG = "CustomerRequestTrip";
    private LatLng startingCoords;
    private LatLng destinationCoords;
    private GoogleMap mGoogleMap;
    CameraUpdate cameraUpdate;

    private AutoCompleteTextView startingLocationTextbox;
    private AutoCompleteTextView destinationTextbox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_main);
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        getUserLocation();
        hideSoftKeyboard();
        initMenuButton();
        initRequestButton();
        startingLocationTextbox = (AutoCompleteTextView)findViewById(R.id.customer_req_from_editText);
        destinationTextbox = (AutoCompleteTextView)findViewById(R.id.customer_req_to_editText);
        mGeoDataClient = Places.getGeoDataClient(this);
        mMapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.customer_map);
        mMapFragment.getMapAsync(this);
    }

    private void init()
    {
        int maxLength = 29;
        InputFilter[] fArray = new InputFilter[1];
        fArray[0] = new InputFilter.LengthFilter(maxLength);
        startingLocationTextbox.setFilters(fArray);
        destinationTextbox.setFilters(fArray);

        mPlaceAutocompleteAdapter = new PlaceAutocompleteAdapter(this, mGeoDataClient, LAT_LNG_BOUNDS, null);
        startingLocationTextbox.setOnItemClickListener(mAutomcompleteClicklistenerStart);
        destinationTextbox.setOnItemClickListener(mAutomcompleteClicklistenerDest);

        startingLocationTextbox.setAdapter(mPlaceAutocompleteAdapter);
        destinationTextbox.setAdapter(mPlaceAutocompleteAdapter);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        try {
            mGoogleMap = googleMap;
            mGoogleMap.setMyLocationEnabled(true);
            mGoogleMap.getUiSettings().setMyLocationButtonEnabled(false);
        } catch (SecurityException e) {
            e.printStackTrace();
        }

    }

    private void initMenuButton(){
        Button loginButton = (Button)findViewById(R.id.customer_menu_button);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent registerIntent = new Intent(CustomerMainActivity.this, CustomerAccountSettingsActivity.class);
                CustomerMainActivity.this.startActivity(registerIntent);
            }
        });
    }

    public void checkPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED ||
                ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED
                ) {//Can add more as per requirement
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION},
                    123);
        }
    }

    private void setBounds(Location location){
        LatLngBounds.Builder builder = new LatLngBounds.Builder();
        builder.include(new LatLng(location.getLatitude() ,location.getLongitude()));
        LAT_LNG_BOUNDS = builder.build();
    }

    private void getUserLocation(){
        checkPermission();
        mFusedLocationClient.getLastLocation()
                .addOnSuccessListener(this, new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        if(location != null)
                        {
                            setBounds(location);
                            customerLocation = location;
                            LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
                            cameraUpdate = CameraUpdateFactory.newLatLngZoom(latLng, 10);
                            mGoogleMap.animateCamera(cameraUpdate);
                            init();
                        }
                    }
                });
    }

    private AdapterView.OnItemClickListener mAutomcompleteClicklistenerStart = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            hideSoftKeyboard();

            final AutocompletePrediction item = mPlaceAutocompleteAdapter.getItem(i);
            final String placeID = item.getPlaceId();

            mGeoDataClient.getPlaceById(placeID).addOnCompleteListener(new OnCompleteListener<PlaceBufferResponse>() {
                @Override
                public void onComplete(@NonNull Task<PlaceBufferResponse> task) {
                    if (task.isSuccessful()) {
                        PlaceBufferResponse places = task.getResult();
                        Place start = places.get(0);
                        Log.i(TAG, "Place found: " + start.getName());

                        startingCoords = start.getLatLng();
                        places.release();
                    } else {
                        Log.e(TAG, "Place not found.");
                    }
                }
            });
        }
    };

    private void hideSoftKeyboard(){
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }

    private AdapterView.OnItemClickListener mAutomcompleteClicklistenerDest = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            hideSoftKeyboard();

            final AutocompletePrediction item = mPlaceAutocompleteAdapter.getItem(i);
            final String placeID = item.getPlaceId();

            mGeoDataClient.getPlaceById(placeID).addOnCompleteListener(new OnCompleteListener<PlaceBufferResponse>() {
                @Override
                public void onComplete(@NonNull Task<PlaceBufferResponse> task) {
                    if (task.isSuccessful()) {
                        PlaceBufferResponse places = task.getResult();
                        Place start = places.get(0);
                        Log.i(TAG, "Place found: " + start.getName());
                        destinationCoords = start.getLatLng();
                        cameraUpdate = CameraUpdateFactory.newLatLngZoom(start.getLatLng(), 10);
                        mGoogleMap.animateCamera(cameraUpdate);
                        places.release();
                    } else {
                        Log.e(TAG, "Place not found.");
                    }
                }
            });
        }
    };

    private void initRequestButton(){

        final AutoCompleteTextView startLocationTextView = (AutoCompleteTextView)findViewById(R.id.customer_req_from_editText);
        final AutoCompleteTextView destinationLocationTextView = (AutoCompleteTextView)findViewById(R.id.customer_req_to_editText);

        Button requestButton = (Button)findViewById(R.id.customer_request_button);
        requestButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Check if the info in every field is okey before sending
                if(startLocationTextView.getText().toString().length() <= 3){
                    return;
                }

                if(destinationLocationTextView.getText().toString().length() <= 3){
                    return;
                }

                String[] addressFrom = startLocationTextView.getText().toString().split(",");
                String[] addressTo = destinationLocationTextView.getText().toString().split(",");

                LocationClass startLocation = new LocationClass();
                startLocation.setLongitude(startingCoords.longitude);
                startLocation.setLatitude(startingCoords.latitude);
                startLocation.setUserId(MainActivity.token.getUserId());
                //startLocation.setLocationType(addressFrom[0]);

                LocationClass destinationLocation = new LocationClass();
                destinationLocation.setLongitude(destinationCoords.longitude);
                destinationLocation.setLatitude(destinationCoords.latitude);
                destinationLocation.setUserId(MainActivity.token.getUserId());
                //destinationLocation.setLocationType(addressTo[0]);

                RequestTrip requestTrip = new RequestTrip();
                requestTrip.setStatus("REQUESTED");
                requestTrip.setPaid(false);
                requestTrip.setStartingLocation(startLocation);
                requestTrip.setEndingLocation(destinationLocation);
                requestTrip.setCustomerId(MainActivity.token.getUserId());

                RequestTripArguments arguments = new RequestTripArguments();
                arguments._trip = requestTrip;
                arguments.id = MainActivity.token.getUserId();

                Call<User> call = MainActivity.client.getUserById("Bearer " + MainActivity.token.getToken());

                call.enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        if(response.isSuccessful())
                        {
                            Toast.makeText(CustomerMainActivity.this, "Fungerar", Toast.LENGTH_LONG).show();
                        }
                        else
                        {
                            Toast.makeText(CustomerMainActivity.this, ":(", Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable t) {

                    }
                });
                /*
                //Send request
                Call<User> call = MainActivity.client.sendRequestedTrip(MainActivity.token.getToken(), arguments);

                call.enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        if(response.isSuccessful())
                        {
                            Toast.makeText(CustomerMainActivity.this, "AMAZING", Toast.LENGTH_LONG).show();
                        }
                        else{
                            Toast.makeText(CustomerMainActivity.this, "Something went wrong!", Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable t) {
                        Toast.makeText(CustomerMainActivity.this, "Cannot establish a connection with the server", Toast.LENGTH_SHORT).show();
                    }
                });
                */


            }
        });
    }
}
