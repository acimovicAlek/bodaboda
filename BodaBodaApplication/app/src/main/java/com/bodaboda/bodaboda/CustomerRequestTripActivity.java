package com.bodaboda.bodaboda;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.places.GeoDataClient;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.location.places.PlaceDetectionClient;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;

public class CustomerRequestTripActivity extends AppCompatActivity
        implements GoogleApiClient.OnConnectionFailedListener {

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    //widgets
    private AutoCompleteTextView startingLocationTextbox;
    private AutoCompleteTextView destinationTextbox;

    //variables
    private PlaceAutocompleteAdapter mPlaceAutocompleteAdapter;
    private GoogleApiClient mGoogleApiClient;
    private GeoDataClient mGeoDataClient;
    private static final LatLngBounds LAT_LNG_BOUNDS = new LatLngBounds(
            new LatLng(-40, -168), new LatLng(71, 136)
    );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_request_trip);

        startingLocationTextbox = (AutoCompleteTextView)findViewById(R.id.req_trip_start_searchbox);
        destinationTextbox = (AutoCompleteTextView)findViewById(R.id.req_trip_destination_searchbox);
        mGeoDataClient = Places.getGeoDataClient(this);
        init();
    }

    private void init()
    {
        /*mGoogleApiClient = new GoogleApiClient
                .Builder(this)
                .addApi(Places.GEO_DATA_API)
                .addApi(Places.PLACE_DETECTION_API)
                .enableAutoManage(this, this)
                .build();*/

        mPlaceAutocompleteAdapter = new PlaceAutocompleteAdapter(this, mGeoDataClient, LAT_LNG_BOUNDS, null);
        initGoBackButton();

        startingLocationTextbox.setAdapter(mPlaceAutocompleteAdapter);
    }

    private void initGoBackButton(){
        Button goBackButton = (Button)findViewById(R.id.req_trip_goback_button);
        goBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent registerIntent = new Intent(CustomerRequestTripActivity.this, CustomerMainActivity.class);
                CustomerRequestTripActivity.this.startActivity(registerIntent);
            }
        });
    }


}
