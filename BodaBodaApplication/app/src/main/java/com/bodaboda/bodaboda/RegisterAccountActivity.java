package com.bodaboda.bodaboda;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

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
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isDriverCheckbox.isChecked()){
                    Intent registerIntent = new Intent(RegisterAccountActivity.this, DriverMainActivity.class);
                    RegisterAccountActivity.this.startActivity(registerIntent);
                }
                else {
                    Intent registerIntent = new Intent(RegisterAccountActivity.this, CustomerMainActivity.class);
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
                Intent registerIntent = new Intent(RegisterAccountActivity.this, MainActivity.class);
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
