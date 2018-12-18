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

import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import com.bodaboda.bodaboda.classes.Login;
import com.bodaboda.bodaboda.classes.Token;
import com.bodaboda.bodaboda.utils.BodaBodaClientApi;
import com.bodaboda.bodaboda.R;
import com.pubnub.api.PNConfiguration;
import com.pubnub.api.PubNub;

import java.io.IOException;

import static com.bodaboda.bodaboda.utils.Constants.PUBNUB_PUBLISH_KEY;
import static com.bodaboda.bodaboda.utils.Constants.PUBNUB_SUBSCRIBE_KEY;
import static com.bodaboda.bodaboda.utils.Constants.SERVER_URL;

public class MainActivity extends AppCompatActivity {

    public static PubNub pubnub;
    public static Retrofit retrofit;
    public static BodaBodaClientApi client;

    public static Token token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        hideSoftKeyboard();
        
        initRegisterButton();
        initLoginButton();
        initPubNub();
        initRetrofit();
        initToken();
    }

    private void initRegisterButton()
    {
        Button registerButton = (Button)findViewById(R.id.main_register_button);
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

        ///////TEST///////////
        Intent loginIntent = new Intent(MainActivity.this, DriverAccountSettingsActivity.class);
        MainActivity.this.startActivity(loginIntent);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Check if the info in every field is okey before sending
                if(username.getText().toString().length() <= 3){
                    error.setVisibility(View.VISIBLE);
                    error.setText("Username must be at least 3 characters!");
                    return;
                }

                if(password.getText().toString().length() <= 6){
                    error.setVisibility(View.VISIBLE);
                    error.setText("Password must be at least 6 characters!");
                    return;
                }

                error.setVisibility(View.GONE);

                Login login = new Login(
                        username.getText().toString(),
                        password.getText().toString()
                );

                //Send fields to server for check
                Call<Token> call = client.loginRequest(login);

                call.enqueue(new Callback<Token>() {
                    @Override
                    public void onResponse(Call<Token> call, Response<Token> response) {
                        if(response.isSuccessful())
                        {
                            token.setUserId(response.body().getUserId());
                            token.setUsername(response.body().getUsername());
                            token.setUserType(response.body().getUserType());
                            token.setToken(response.body().getToken());

                            //Activate popup for choose customer or taxi

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
                    public void onFailure(Call<Token> call, Throwable t) {
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

    private void initRetrofit(){

        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(SERVER_URL)
                .addConverterFactory(GsonConverterFactory.create());
        retrofit = builder.build();
        client = retrofit.create(BodaBodaClientApi.class);
    }

    private void initToken(){
        token = new Token();
    }

    private void hideSoftKeyboard(){
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }

}
