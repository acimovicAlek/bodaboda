package com.bodaboda.bodaboda;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class CustomerRequestTripActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_request_trip);

        initGoBackButton();
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
