package com.bodaboda.bodaboda;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class DriverBrowseTripsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_browse_trips);

        initRefreshButton();
        initGoBackButton();
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
