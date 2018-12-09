package com.bodaboda.bodaboda.activities;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.bodaboda.bodaboda.R;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.tasks.OnSuccessListener;

public class CustomerMainActivity extends AppCompatActivity {

    public static LatLngBounds LAT_LNG_BOUNDS;
    private FusedLocationProviderClient mFusedLocationClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_main);
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        getUserLocation();

        initRequestTripButton();
        initAccountSettingsButton();
        initLogOutButton();
    }

    private void initRequestTripButton(){
        Button requestTripButton = (Button)findViewById(R.id.cmain_request_trip_button);
        requestTripButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent registerIntent = new Intent(CustomerMainActivity.this, CustomerRequestTripActivity.class);
                CustomerMainActivity.this.startActivity(registerIntent);
            }
        });
    }

    private void initAccountSettingsButton(){
        Button accountSettingsButton = (Button)findViewById(R.id.cmain_account_settings_button);
        accountSettingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent registerIntent = new Intent(CustomerMainActivity.this, CustomerAccountSettingsActivity.class);
                CustomerMainActivity.this.startActivity(registerIntent);
            }
        });
    }

    private void initLogOutButton(){
        Button logOutButton = (Button)findViewById(R.id.cmain_logout_button);
        logOutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent registerIntent = new Intent(CustomerMainActivity.this, MainActivity.class);
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
                        }
                    }
                });
    }
}
