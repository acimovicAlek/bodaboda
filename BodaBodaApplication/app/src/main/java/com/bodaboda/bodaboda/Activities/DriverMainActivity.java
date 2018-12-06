package com.bodaboda.bodaboda.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.bodaboda.bodaboda.R;

public class DriverMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_main);

        initBrowseTripsButton();
        initAccountSettingsButton();
        initStatisticsButton();
        initLogoutButton();
    }

    private void initBrowseTripsButton(){
        Button browseTripButton = (Button)findViewById(R.id.dmain_browse_trips_button);
        browseTripButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent registerIntent = new Intent(DriverMainActivity.this, DriverBrowseTripsActivity.class);
                DriverMainActivity.this.startActivity(registerIntent);
            }
        });
    }

    private void initAccountSettingsButton(){
        Button accountSettingsButton = (Button)findViewById(R.id.dmain_account_settings_button);
        accountSettingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent registerIntent = new Intent(DriverMainActivity.this, DriverAccountSettingsActivity.class);
                DriverMainActivity.this.startActivity(registerIntent);
            }
        });
    }

    private void initStatisticsButton(){
        Button statisticsButton = (Button)findViewById(R.id.dmain_statistics_button);
        statisticsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent registerIntent = new Intent(DriverMainActivity.this, DriverStatisticsActivity.class);
                DriverMainActivity.this.startActivity(registerIntent);
            }
        });
    }

    private void initLogoutButton(){
        Button logoutButton = (Button)findViewById(R.id.dmain_logout_button);
        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent registerIntent = new Intent(DriverMainActivity.this, MainActivity.class);
                DriverMainActivity.this.startActivity(registerIntent);
            }
        });
    }
}
