package com.bodaboda.bodaboda;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.pubnub.api.PNConfiguration;
import com.pubnub.api.PubNub;

import static com.bodaboda.bodaboda.Constants.PUBNUB_PUBLISH_KEY;
import static com.bodaboda.bodaboda.Constants.PUBNUB_SUBSCRIBE_KEY;

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
        pnConfiguration.setSubscribeKey(PUBNUB_SUBSCRIBE_KEY);
        pnConfiguration.setPublishKey(PUBNUB_PUBLISH_KEY);
        pnConfiguration.setSecure(true);
        pubnub = new PubNub(pnConfiguration);
    }

}
