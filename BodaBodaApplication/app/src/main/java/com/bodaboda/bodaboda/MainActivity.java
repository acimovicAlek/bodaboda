package com.bodaboda.bodaboda;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
        final EditText username = (EditText) findViewById(R.id.main_enter_username_editText);
        final EditText password = (EditText) findViewById(R.id.main_enter_password_editText);
        Button loginButton = (Button)findViewById(R.id.main_login_button);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Check if the info in every field is okey before sending
                /*if(notOkey){
                    return;
                }*/

                //Send fields to server for check
                Call<ResponseBody> call = BodaBodaClient
                        .getInstance()
                        .getApi()
                        .loginRequest(
                                username.toString(),
                                password.toString()
                        );

                call.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        /*if(response.isSuccessful()){
                            Toast.makeText(MainActivity.this, "Server is up and got the message!", Toast.LENGTH_LONG).show();
                        }
                        else {
                            Toast.makeText(MainActivity.this, "Server is up but return error!", Toast.LENGTH_LONG).show();
                        }*/
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        //Toast.makeText(MainActivity.this, "Network failure", Toast.LENGTH_LONG).show();
                    }
                });

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
