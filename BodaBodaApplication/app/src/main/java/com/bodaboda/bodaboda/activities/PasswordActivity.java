package com.bodaboda.bodaboda.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.bodaboda.bodaboda.R;


public class PasswordActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password);

        initGoBackButton();
        initResetPassword();

    }

    private void initResetPassword()

    {
        final TextView information = (TextView)findViewById(R.id.InformationTextView);
        final EditText passwordEmail = (EditText) findViewById(R.id.et_Password_Email);
        Button resetPassword = (Button) findViewById(R.id.btn_Password_Reset);

        resetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String useremail = passwordEmail.getText().toString().trim();
                if (useremail.equals("")) {
                    Toast.makeText(PasswordActivity.this, "Please enter your registered Email ID", Toast.LENGTH_LONG).show();
                } else {
                    //Add code for email reset


                }

            }
        });

    }


    private void initGoBackButton() {
        Button goBackButton = (Button) findViewById(R.id.forgotpassword_back_button);
        goBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent registerIntent = new Intent(PasswordActivity.this, MainActivity.class);
                PasswordActivity.this.startActivity(registerIntent);
            }
        });
    }
}

