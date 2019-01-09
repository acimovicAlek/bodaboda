package com.bodaboda.bodaboda.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.bodaboda.bodaboda.R;

import java.util.concurrent.TimeUnit;

public class DriverCustomerAcceptedActivity extends AppCompatActivity {

    private TextView customerAcceptedTW;
    private TextView startTW;
    private TextView destinationTW;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_customer_accepted);

        customerAcceptedTW = findViewById(R.id.driver_customer_accteped_textView);
        startTW = findViewById(R.id.driver_trip_start_textView);
        destinationTW = findViewById(R.id.driver_trip_destination_textView);
        customerAcceptedTW.setText("CUSTOMERNAME accepted!");
        startTW.setText("ORIGINATION");
        startTW.setText("DESTINATION");
        /*Change these TextViews to the relevant trip information received from the customer*/

        try{
            TimeUnit.SECONDS.sleep(5);
        }
        catch(InterruptedException ex)
        {
            Thread.currentThread().interrupt();
        }

        Intent loginIntent = new Intent(DriverCustomerAcceptedActivity.this, DriverFindCustomerActivity.class);
        DriverCustomerAcceptedActivity.this.startActivity(loginIntent);
        /*Keep alive for 5 seconds and then go on to the DriverFindCustomerActivity.
        Add the correct trip info to the TextViews.*/

    }


}
