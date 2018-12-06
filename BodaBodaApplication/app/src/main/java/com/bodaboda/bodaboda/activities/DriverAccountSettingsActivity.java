package com.bodaboda.bodaboda.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.bodaboda.bodaboda.R;

public class DriverAccountSettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_account_settings);

        initGoBackButton();
    }

    private void initGoBackButton(){
        Button goBackButton = (Button)findViewById(R.id.dacc_go_back_button);
        goBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent registerIntent = new Intent(DriverAccountSettingsActivity.this, DriverMainActivity.class);
                DriverAccountSettingsActivity.this.startActivity(registerIntent);
            }
        });
    }
}
