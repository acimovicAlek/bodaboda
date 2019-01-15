package com.bodaboda.bodaboda.activities;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.AsyncTask;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListView;

import com.bodaboda.bodaboda.R;
import com.bodaboda.bodaboda.classes.CustomerTripItem;
import com.bodaboda.bodaboda.classes.CustomerTripItemChild;
import com.bodaboda.bodaboda.classes.Location;
import com.bodaboda.bodaboda.classes.Trip;
import com.bodaboda.bodaboda.classes.User;
import com.bodaboda.bodaboda.utils.ExpandableListAdapter;
import com.google.android.gms.maps.model.LatLng;
import com.google.gson.JsonDeserializationContext;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Locale;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DriverMainActivity extends AppCompatActivity {
    private ExpandableListView listView;
    private ExpandableListAdapter listAdapter;
    private List<CustomerTripItem> listDataHeader;
    private HashMap<CustomerTripItem, List<CustomerTripItemChild>> listHash;
    private Button refreshButton;
    private View itemView;
    private ArrayList<LatLng> destinationCoords;
    private ArrayList<LatLng> originCoords;
    private ArrayList<String> destinationNames;
    private ArrayList<String> originNames;
    CyclicBarrier barrier;
    List<Trip> _tripList = new ArrayList<>();
    private ArrayList<String> customerName;
    Geocoder geocoder;
    int i;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_main);
        barrier = new CyclicBarrier(4);
        init();
        listView = (ExpandableListView)findViewById(R.id.driver_expandableListView);
        listAdapter = new ExpandableListAdapter(this, listDataHeader, listHash);
        listView.setAdapter(listAdapter);
        listView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView expandableListView, View view, int i, long l) {
                expandableListView.expandGroup(i);
                view.findViewById(R.id.driver_expand_item_button).setVisibility(view.INVISIBLE);
                itemView = view;
                return true;
            }
        });

        listView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView expandableListView, View view, int i, int i1, long l) {
                expandableListView.collapseGroup(i);
                itemView.findViewById(R.id.driver_expand_item_button).setVisibility(View.VISIBLE);
                return true;
            }
        });
    }


    private void initRefreshButton() {
        Button goBackButton = (Button) findViewById(R.id.driver_refresh_button);
        goBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*REFRESH THE LIST OF TRIP REQUESTS*/
            }
        });
    }

    private void initDriverAcceptButton() {
        Button goBackButton = (Button) findViewById(R.id.driver_accept_button);
        goBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*SEND ACCEPTANCE TO THE CUSTOMER*/
            }
        });
    }

    private void initListItems(){
        Call<List<Trip>> call = MainActivity.client.getRequestedTrips(MainActivity.token.getToken());
        listDataHeader = new ArrayList<>();
        listHash = new HashMap<>();

        call.enqueue(new Callback<List<Trip>>() {
            @Override
            public void onResponse(Call<List<Trip>> call, Response<List<Trip>> response) {
                if(response.isSuccessful())
                {
                    _tripList = response.body();

                    for(i=0; i < _tripList.size(); i++){
                        Call<User> customerCall = MainActivity.client.getUserById(MainActivity.token.getToken(), _tripList.get(i).getCustomerId());

                        customerCall.enqueue(new Callback<User>() {
                            @Override
                            public void onResponse(Call<User> call, Response<User> response) {
                                if(response.isSuccessful()) {
                                    customerName.add(response.body().getFirstName());

                                    Call<Location> destinationCall = MainActivity.client.getLocation(MainActivity.token.getToken(), _tripList.get(0).getEndingLocationId());
                                    destinationCall.enqueue(new Callback<Location>() {
                                        @Override
                                        public void onResponse(Call<Location> call, Response<Location> response) {
                                            if(response.isSuccessful()){

                                                destinationCoords.add(new LatLng(response.body().getLatitude(), response.body().getLongitude()));
                                                try{
                                                    List<Address> destinationAddresses = geocoder.getFromLocation(response.body().getLatitude(), response.body().getLongitude(), 1);
                                                    if(null!=destinationAddresses && destinationAddresses.size()>0){
                                                        destinationNames.add(destinationAddresses.get(0).getAddressLine(0));
                                                    }
                                                }catch(IOException e){
                                                    System.out.println("FAILED DESTINATION");
                                                }
                                                Call<Location> originCall = MainActivity.client.getLocation(MainActivity.token.getToken(), _tripList.get(0).getStartingLocationId());
                                                originCall.enqueue(new Callback<Location>() {
                                                    @Override
                                                    public void onResponse(Call<Location> call, Response<Location> response) {
                                                        if(response.isSuccessful()){
                                                            originCoords.add(new LatLng(response.body().getLatitude(), response.body().getLongitude()));
                                                            try{
                                                                List<Address> originAddresses = geocoder.getFromLocation(response.body().getLatitude(), response.body().getLongitude(), 1);
                                                                if(null != originAddresses && originAddresses.size() >= 0){
                                                                    originNames.add(originAddresses.get(0).getAddressLine(0));
                                                                }
                                                            }catch(IOException e){
                                                                System.out.println("FAILED ORIGIN");
                                                            }
                                                            for(int i = 0; i < originNames.size() && i < destinationNames.size(); i++){
                                                                CustomerTripItem cti = new CustomerTripItem(customerName.get(i), "distanceToOrigin", String.valueOf(_tripList.get(i).getPrice()));
                                                                CustomerTripItemChild ctic = new CustomerTripItemChild(originNames.get(i), destinationNames.get(i), "tripLength");
                                                                System.out.println("ORIGINNAME: " + originNames.get(i));
                                                                listDataHeader.add(cti);
                                                                List<CustomerTripItemChild> itemList = new ArrayList<>();
                                                                itemList.add(ctic);
                                                                listHash.put(listDataHeader.get(i), itemList);
                                                            }
                                                        }
                                                        else{
                                                        }
                                                    }

                                                    @Override
                                                    public void onFailure(Call<Location> call, Throwable t) {

                                                    }
                                                });
                                            }
                                            else{
                                            }
                                        }
                                        @Override
                                        public void onFailure(Call<Location> call, Throwable t) {

                                        }
                                    });
                                }
                                else {

                                }
                            }
                            @Override
                            public void onFailure(Call<User> call, Throwable t) {

                            }
                        });







                    }
                }
            }

            @Override
            public void onFailure(Call<List<Trip>> call, Throwable t) {

            }
        });
    }

    /*private void getLocationNames(){

        System.out.println("TRY GET NAME");

        System.out.println("ORIGIN SIZE: " + originCoords.size());
        for(int i = 0; i < destinationAddresses.size() || i < originAddresses.size(); i++) {
            try {
                List<Address> originAddresses = geocoder.getFromLocation(originCoords.get(i).latitude, originCoords.get(i).longitude, 1);
                List<Address> destinationAddresses = geocoder.getFromLocation(destinationCoords.get(i).latitude, destinationCoords.get(i).longitude, 1);
                if(null!=originAddresses&&originAddresses.size()>0){
                    originNames.add(originAddresses.get(i).getAddressLine(i));
                    destinationNames.add(destinationAddresses.get(i).getAddressLine(i));
                }
            } catch (IOException e) {
                System.out.println("FAILED GET NAME");
                e.printStackTrace();
            }
            System.out.println("ORIGIN: " + originNames.get(i) + "DESTINATION: " + destinationNames.get(i));
        }
    }*/

    private void fillList(){
        System.out.println(originNames.size());
        for(int i = 0; i < originNames.size() && i < destinationNames.size(); i++){
            CustomerTripItem cti = new CustomerTripItem(customerName.get(i), "distanceToOrigin", String.valueOf(_tripList.get(i).getPrice()));
            CustomerTripItemChild ctic = new CustomerTripItemChild(originNames.get(i), destinationNames.get(i), "tripLength");
            System.out.println("ORIGINNAME: " + originNames.get(i));
            listDataHeader.add(cti);
            List<CustomerTripItemChild> itemList = new ArrayList<>();
            itemList.add(ctic);
            listHash.put(listDataHeader.get(i), itemList);
        }
    }

    private void init() {
        destinationCoords = new ArrayList<>();
        originCoords = new ArrayList<>();
        destinationNames = new ArrayList<>();
        originNames = new ArrayList<>();
        customerName = new ArrayList<>();
        geocoder = new Geocoder(getApplicationContext(), Locale.getDefault());
        initListItems();
        initRefreshButton();

        //Check when pressing Update button and call the server to select every trip that
        //has a status of "REQUESTED" as we want to select only the available trips.
        //Maybe we need to change the status directly on the server when a driver asks the customer.
        //The list of trips will have ids to customers and locations that we need to call
        //again for info. Then we just fill in every info into every item and child item in list.

        //If opening a childitem and click on the button. The call should change the status on the server
        //and ask the customer and wait for response (Send TaxiId and Price at least). Probably in a response code...
        //If cusotmer accepts, go to next intent(DriverCustomerAcceptedActivity) which begins the trip.

        //So this lines under here are examples of how to add info to the list

    }

}
