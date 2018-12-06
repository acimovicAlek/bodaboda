package com.bodaboda.bodaboda.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import com.bodaboda.bodaboda.classes.User;
import com.bodaboda.bodaboda.utils.BodaBodaClientApi;
import com.bodaboda.bodaboda.R;
import com.pubnub.api.PNConfiguration;
import com.pubnub.api.PubNub;

import static com.bodaboda.bodaboda.utils.Constants.PUBNUB_PUBLISH_KEY;
import static com.bodaboda.bodaboda.utils.Constants.PUBNUB_SUBSCRIBE_KEY;

public class MainActivity extends AppCompatActivity {

    public static PubNub pubnub;
    public static Retrofit retrofit;
    public static BodaBodaClientApi client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        hideSoftKeyboard();

        initRegisterButton();
        initLoginButton();
        initPubNub();
        initRetrofit("http://localhost:5000");
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
        final TextView error = (TextView)findViewById(R.id.main_error_textView);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Check if the info in every field is okey before sending
                if(username.getText().toString().length() <= 3){
                    error.setVisibility(View.VISIBLE);
                    error.setText("Username must be atleast 3 characters!");
                    return;
                }

                if(password.getText().toString().length() <= 6){
                    error.setVisibility(View.VISIBLE);
                    error.setText("Password must be atleast 6 characters!");
                    return;
                }

                error.setVisibility(View.GONE);

                //Send fields to server for check
                Call<ResponseBody> call = client.loginRequest(
                        username.getText().toString(),
                        password.getText().toString()
                );

                call.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if(response.code() == 200)
                        {
                            username.setText("");
                            password.setText("");

                            Intent loginIntent = new Intent(MainActivity.this, CustomerMainActivity.class);
                            MainActivity.this.startActivity(loginIntent);
                        }
                        else{
                            password.setText("");
                            error.setVisibility(View.VISIBLE);
                            error.setText("Wrong Username or Password!");
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Toast.makeText(MainActivity.this, "Cannot establish a connection with the server", Toast.LENGTH_LONG).show();
                    }
                });
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

    private void initRetrofit(String url){
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create());
        retrofit = builder.build();
        client = retrofit.create(BodaBodaClientApi.class);
    }

    private void hideSoftKeyboard(){
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }

}