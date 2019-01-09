package com.bodaboda.bodaboda.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListView;

import com.bodaboda.bodaboda.R;
import com.bodaboda.bodaboda.classes.CustomerTripItem;
import com.bodaboda.bodaboda.classes.CustomerTripItemChild;
import com.bodaboda.bodaboda.utils.ExpandableListAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DriverMainActivity extends AppCompatActivity {
    private ExpandableListView listView;
    private ExpandableListAdapter listAdapter;
    private List<CustomerTripItem> listDataHeader;
    private HashMap<CustomerTripItem, List<CustomerTripItemChild>> listHash;
    private Button refreshButton;
    private View itemView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_main);

        listView = (ExpandableListView)findViewById(R.id.driver_expandableListView);
        init();
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

    private void init() {
        listDataHeader = new ArrayList<>();
        listHash = new HashMap<>();
        initRefreshButton();

        //Check when pressing Update button and call the server to select every trip that
        //has a status of "REQUESTED" as we want to select only the available trips.
        //Maybe we need to change the status directly on the server when a driver asks the customer.
        //The list of trips will have ids to customers and locations that we need to call
        //again for info. Then we just fill in every info into every item and child item in list.

        //If opening a childitem and click on the button. The call should change the status on the server
        //and ask the customer and wait for response (Send TaxiId and Price at least). Probably in a response code...
        //If cusotmer accepts, go to next intent(DriverCustomerAcceptedActivity) which begins the trip.

        //So this lines under here are examples of how to add info to the list.

        CustomerTripItem cti = new CustomerTripItem("Mugabe", "25km", "2500");
        CustomerTripItemChild ctic = new CustomerTripItemChild("Köping", "Västerås", "10km");

        listDataHeader.add(cti);
        listDataHeader.add(cti);
        listDataHeader.add(cti);

        List<CustomerTripItemChild> mugabe = new ArrayList<>();
        mugabe.add(ctic);

        List<CustomerTripItemChild> jonathan = new ArrayList<>();
        jonathan.add(ctic);

        List<CustomerTripItemChild> chingiz = new ArrayList<>();
        chingiz.add(ctic);

        listHash.put(listDataHeader.get(0), mugabe);
        listHash.put(listDataHeader.get(1), jonathan);
        listHash.put(listDataHeader.get(2), chingiz);

    };

}
