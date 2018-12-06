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

import com.bodaboda.bodaboda.R;
import com.bodaboda.bodaboda.classes.User;

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

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Check if the info in every field is okey before sending
                if(username.getTextSize() <= 3){
                    //Show red text
                    return;
                }

                if(password.getTextSize() <= 6){
                    //Show red text
                    return;
                }

                if(!email.getText().toString().contains("@")){
                    //Show red text
                    return;
                }

                if(password.getText().toString() == confirmPassword.getText().toString()){
                    //Show red text
                    return;
                }

                //Make User
                User user = new User();
                user.setUsername(username.getText().toString());
                user.setFirstname(firstname.getText().toString());
                user.setLastname(lastname.getText().toString());
                user.setPhoneNumber(phoneNo.getText().toString());
                user.setEmail(email.getText().toString());

                if(isDriverCheckbox.isChecked()){

                    user.setDriver(true);


                    Intent registerIntent = new Intent(RegisterAccountActivity.this, com.bodaboda.bodaboda.activities.DriverMainActivity.class);
                    RegisterAccountActivity.this.startActivity(registerIntent);
                }
                else {

                    user.setDriver(false);

                    Intent registerIntent = new Intent(RegisterAccountActivity.this, com.bodaboda.bodaboda.activities.CustomerMainActivity.class);
                    RegisterAccountActivity.this.startActivity(registerIntent);
                }
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
