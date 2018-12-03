package com.bodaboda.bodaboda;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


import com.pubnub.api.PNConfiguration;
import com.pubnub.api.PubNub;

public class MainActivity extends AppCompatActivity {

    public static PubNub pubnub;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initRegisterButton();
        initLoginButton();
        initPubNub();
    }

    private void initRegisterButton()
    {
        Button registerButton = (Button)findViewById(R.id.main_reg_account_button);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent registerIntent = new Intent(MainActivity.this, RegisterAccountActivity.class);
                MainActivity.this.startActivity(registerIntent);
            }
        });
    }

    private void initLoginButton()
    {
        Button loginButton = (Button)findViewById(R.id.main_login_button);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Need to verify first and choose driver or customer
                Intent loginIntent = new Intent(MainActivity.this, CustomerMainActivity.class);
                MainActivity.this.startActivity(loginIntent);
            }
        });
    }

    private void initPubNub()
    {
        PNConfiguration pnConfiguration = new PNConfiguration();
        pnConfiguration.setSubscribeKey("sub-c-2e136416-f735-11e8-b35b-72ed2feff2dd");
        pnConfiguration.setPublishKey("pub-c-b1a495c8-4d4b-4a10-be69-41f8483c7681");
        pnConfiguration.setSecure(true);
        pubnub = new PubNub(pnConfiguration);
    }

}
