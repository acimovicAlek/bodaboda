package com.bodaboda.bodaboda.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.bodaboda.bodaboda.utils.Constants;
import com.bodaboda.bodaboda.R;
import com.pubnub.api.PubNub;
import com.pubnub.api.callbacks.SubscribeCallback;
import com.pubnub.api.models.consumer.PNStatus;
import com.pubnub.api.models.consumer.pubsub.PNMessageResult;
import com.pubnub.api.models.consumer.pubsub.PNPresenceEventResult;

import java.util.Arrays;

public class DriverBrowseTripsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_browse_trips);
        MainActivity.pubnub.subscribe()
                .channels(Arrays.asList(Constants.PUBNUB_CHANNEL_NAME)) // subscribe to channels
                .execute();

        initRefreshButton();
        initGoBackButton();
        initList();
    }

    private void initList(){
        /*Link to the list id here*/
        MainActivity.pubnub.addListener(new SubscribeCallback() {
            @Override
            public void status(PubNub pubnub, PNStatus status) {

            }

            @Override
            public void message(PubNub pubnub, PNMessageResult message) {
                String tripRequest = message.getMessage().toString();
                System.out.println("TRIP REQUEST: " + tripRequest);
            }

            @Override
            public void presence(PubNub pubnub, PNPresenceEventResult presence) {

            }
        });

    }

    private void initRefreshButton(){
        Button refreshButton = (Button)findViewById(R.id.browse_refresh_button);
        refreshButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Call server to get list of pending trips...
            }
        });
    }

    private void initGoBackButton(){
        Button goBackButton = (Button)findViewById(R.id.browse_goback_button);
        goBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent registerIntent = new Intent(DriverBrowseTripsActivity.this, DriverMainActivity.class);
                DriverBrowseTripsActivity.this.startActivity(registerIntent);
            }
        });
    }
}
