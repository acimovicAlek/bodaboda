package com.bodaboda.bodaboda.activities;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;

import com.bodaboda.bodaboda.classes.LocationClass;
import com.bodaboda.bodaboda.utils.PlaceAutocompleteAdapter;
import com.google.android.gms.location.LocationRequest;

import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.Spanned;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.Button;

import com.bodaboda.bodaboda.R;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationResult;
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
        Button requestButton = (Button)findViewById(R.id.customer_request_button);
        requestButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                LocationClass startLocation = new LocationClass();
                LocationClass destinationLocation = new LocationClass();
            }
        });
    }
}
