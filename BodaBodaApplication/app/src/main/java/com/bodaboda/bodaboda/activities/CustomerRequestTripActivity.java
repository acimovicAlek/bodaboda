package com.bodaboda.bodaboda.activities;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.Button;

import com.bodaboda.bodaboda.utils.Constants;
import com.bodaboda.bodaboda.utils.PlaceAutocompleteAdapter;
import com.bodaboda.bodaboda.R;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.places.AutocompletePrediction;
import com.google.android.gms.location.places.GeoDataClient;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.PlaceBufferResponse;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.pubnub.api.callbacks.PNCallback;
import com.pubnub.api.models.consumer.PNPublishResult;
import com.pubnub.api.models.consumer.PNStatus;

import java.util.LinkedHashMap;

public class CustomerRequestTripActivity extends AppCompatActivity
        implements GoogleApiClient.OnConnectionFailedListener {

    //widgets
    private AutoCompleteTextView startingLocationTextbox;
    private AutoCompleteTextView destinationTextbox;

    //variables

    private PlaceAutocompleteAdapter mPlaceAutocompleteAdapter;
    private GoogleApiClient mGoogleApiClient;
    private GeoDataClient mGeoDataClient;
    private static final String TAG = "CustomerRequestTrip";
    private LatLng startingCoords;
    private LatLng destinationCoords;
    /*private LatLngBounds LAT_LNG_BOUNDS = new LatLngBounds(
            new LatLng(-40, -168), new LatLng(71, 136)
    );*/

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    private LinkedHashMap<String, String> getNewLocationMessage(double startLat, double startLng, double destLat, double destLng) {
        LinkedHashMap<String, String> map = new LinkedHashMap<String, String>();
        map.put("startLat", String.valueOf(startLat));
        map.put("startLng", String.valueOf(startLng));
        map.put("destLat", String.valueOf(destLat));
        map.put("destLng", String.valueOf(destLng));
        //place.getLatLng();
        return map;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_request_trip);
        hideSoftKeyboard();
        //LAT_LNG_BOUNDS = CustomerMainActivity.LAT_LNG_BOUNDS;

        startingLocationTextbox = (AutoCompleteTextView)findViewById(R.id.req_trip_start_searchbox);
        destinationTextbox = (AutoCompleteTextView)findViewById(R.id.req_trip_destination_searchbox);
        mGeoDataClient = Places.getGeoDataClient(this);
        init();
        checkPermission();
    }

    private void init()
    {
        mPlaceAutocompleteAdapter = new PlaceAutocompleteAdapter(this, mGeoDataClient, CustomerMainActivity.LAT_LNG_BOUNDS, null);
        startingLocationTextbox.setOnItemClickListener(mAutomcompleteClicklistenerStart);
        destinationTextbox.setOnItemClickListener(mAutomcompleteClicklistenerDest);

        startingLocationTextbox.setAdapter(mPlaceAutocompleteAdapter);
        destinationTextbox.setAdapter(mPlaceAutocompleteAdapter);


        initGoBackButton();
        initRequestButton();
    }

    private void initGoBackButton(){
        Button goBackButton = (Button)findViewById(R.id.req_trip_goback_button);
        goBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent registerIntent = new Intent(CustomerRequestTripActivity.this, com.bodaboda.bodaboda.activities.CustomerMainActivity.class);
                CustomerRequestTripActivity.this.startActivity(registerIntent);
            }
        });
    }

    private void initRequestButton() {
        Button requestButton = (Button)findViewById(R.id.req_trip_search_button);
        requestButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LinkedHashMap<String, String> startingLocationMessage = getNewLocationMessage(startingCoords.latitude, startingCoords.longitude, destinationCoords.latitude, destinationCoords.longitude);
                com.bodaboda.bodaboda.activities.MainActivity.pubnub.publish()
                        .message(startingLocationMessage)
                        .channel(Constants.PUBNUB_CHANNEL_NAME)
                        .async(new PNCallback<PNPublishResult>() {
                            @Override
                            public void onResponse(PNPublishResult result, PNStatus status) {
                                // handle publish result, status always present, result if successful
                                // status.isError() to see if error happened
                                if (!status.isError()) {
                                    System.out.println("pub timetoken: " + result.getTimetoken());
                                }
                                System.out.println("pub status code: " + status.getStatusCode());
                            }
                        });
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

    private void hideSoftKeyboard(){
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
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
                        places.release();
                    } else {
                        Log.e(TAG, "Place not found.");
                    }
                }
            });
        }
    };
}
