package com.bodaboda.bodaboda;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class CustomerMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_main);

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
}
