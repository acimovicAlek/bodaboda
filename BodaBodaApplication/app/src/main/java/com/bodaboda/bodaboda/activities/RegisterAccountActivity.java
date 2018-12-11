package com.bodaboda.bodaboda.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.bodaboda.bodaboda.R;
import com.bodaboda.bodaboda.classes.Login;
import com.bodaboda.bodaboda.classes.Token;
import com.bodaboda.bodaboda.classes.User;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterAccountActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_account);
        hideSoftKeyboard();

        initRegisterButton();
        initCancelButton();
        initCheckboxIsDriver();
    }

    private void initRegisterButton(){
        Button registerButton = (Button)findViewById(R.id.reg_register_button);
        final CheckBox isDriverCheckbox = (CheckBox)findViewById(R.id.reg_driver_reg_checkBox);

        final EditText username = (EditText)findViewById(R.id.reg_username_editText);
        final EditText firstname = (EditText)findViewById(R.id.reg_firstname_editText);
        final EditText lastname = (EditText)findViewById(R.id.reg_lastname_editText);
        final EditText phoneNo = (EditText)findViewById(R.id.reg_phoneNo_editText);
        final EditText email = (EditText)findViewById(R.id.reg_email_editText);
        final EditText password = (EditText)findViewById(R.id.reg_password_editText);
        final EditText confirmPassword = (EditText)findViewById(R.id.reg_confirm_password_editText);
        final EditText mileagePrice = (EditText)findViewById(R.id.reg_mileage_price_editText);
        final EditText startingFee = (EditText)findViewById(R.id.reg_trip_starting_fee_editText);

        final TextView error = (TextView)findViewById(R.id.reg_error_textView);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Check if the info in every field is okey before sending
                if(username.getText().toString().length() <= 3){
                    error.setVisibility(View.VISIBLE);
                    error.setText("Username must have atleast 3 characters!");
                    return;
                }

                if(password.getText().toString().length() <= 6){
                    error.setVisibility(View.VISIBLE);
                    error.setText("Password must have atleast 6 characters!");
                    return;
                }

                if(!email.getText().toString().contains("@")){
                    error.setVisibility(View.VISIBLE);
                    error.setText("Incorrect email format!");
                    return;
                }

                if(password.getText().toString() == confirmPassword.getText().toString()){
                    error.setVisibility(View.VISIBLE);
                    error.setText("Password does not match!");
                    return;
                }

                error.setVisibility(View.GONE);

                //Make User
                String type;
                if(isDriverCheckbox.isChecked()){
                    type = "Taxi";
                }
                else {
                    type = "Customer";
                }

                User user = new User(
                        username.getText().toString(),
                        password.getText().toString(),
                        type,
                        firstname.getText().toString(),
                        lastname.getText().toString(),
                        email.getText().toString(),
                        phoneNo.getText().toString()
                );

                //Send request
                Call<User> call = MainActivity.client.registerAccount(user);

                call.enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        if(response.isSuccessful())
                        {
                            Login login = new Login(
                                    username.getText().toString(),
                                    password.getText().toString()
                            );

                            username.setText("");
                            password.setText("");
                            phoneNo.setText("");
                            firstname.setText("");
                            lastname.setText("");
                            email.setText("");
                            mileagePrice.setText("");
                            startingFee.setText("");
                            confirmPassword.setText("");

                            //Send Login after create
                            Call<Token> call2 = MainActivity.client.loginRequest(login);

                            call2.enqueue(new Callback<Token>() {
                                @Override
                                public void onResponse(Call<Token> call2, Response<Token> response) {
                                    if(response.isSuccessful())
                                    {
                                        MainActivity.token.setUserId(response.body().getUserId());
                                        MainActivity.token.setUsername(response.body().getUsername());
                                        MainActivity.token.setUserType(response.body().getUserType());
                                        MainActivity.token.setToken(response.body().getToken());

                                        error.setVisibility(View.GONE);
                                        username.setText("");
                                        password.setText("");

                                        if(isDriverCheckbox.isChecked()){
                                            Intent registerIntent = new Intent(RegisterAccountActivity.this, com.bodaboda.bodaboda.activities.DriverMainActivity.class);
                                            RegisterAccountActivity.this.startActivity(registerIntent);
                                        }
                                        else{
                                            Intent registerIntent = new Intent(RegisterAccountActivity.this, com.bodaboda.bodaboda.activities.CustomerMainActivity.class);
                                            RegisterAccountActivity.this.startActivity(registerIntent);
                                        }
                                    }
                                    else{
                                        error.setVisibility(View.VISIBLE);
                                        error.setText("Something went wrong!");
                                    }
                                }

                                @Override
                                public void onFailure(Call<Token> call2, Throwable t) {
                                    Toast.makeText(RegisterAccountActivity.this, "Cannot establish a connection with the server", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                        else{
                            password.setText("");
                            error.setVisibility(View.VISIBLE);
                            error.setText("Could not create account! Try another username!");
                        }
                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable t) {
                        Toast.makeText(RegisterAccountActivity.this, "Cannot establish a connection with the server", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    private void initCancelButton(){
        Button cancelButton = (Button)findViewById(R.id.reg_cancel_reg_button);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent registerIntent = new Intent(RegisterAccountActivity.this, com.bodaboda.bodaboda.activities.MainActivity.class);
                RegisterAccountActivity.this.startActivity(registerIntent);
            }
        });
    }

    private void initCheckboxIsDriver(){
        final CheckBox isDriverCheckbox = (CheckBox)findViewById(R.id.reg_driver_reg_checkBox);
        final TextView mileagePriceTW = (TextView)findViewById(R.id.reg_mileage_price_textView);
        final EditText mileagePriceET = (EditText)findViewById(R.id.reg_mileage_price_editText);
        final TextView startingFeeTW = (TextView)findViewById(R.id.reg_trip_starting_fee_textView);
        final EditText startingFeeET = (EditText)findViewById(R.id.reg_trip_starting_fee_editText);
        isDriverCheckbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isDriverCheckbox.isChecked()){
                    //Set Visible
                    mileagePriceET.setVisibility(View.VISIBLE);
                    mileagePriceTW.setVisibility(View.VISIBLE);
                    startingFeeET.setVisibility(View.VISIBLE);
                    startingFeeTW.setVisibility(View.VISIBLE);
                }
                else{
                    //Set Gone
                    mileagePriceET.setVisibility(View.GONE);
                    mileagePriceTW.setVisibility(View.GONE);
                    startingFeeET.setVisibility(View.GONE);
                    startingFeeTW.setVisibility(View.GONE);
                }
            }
        });
    }

    private void hideSoftKeyboard(){
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }
}
