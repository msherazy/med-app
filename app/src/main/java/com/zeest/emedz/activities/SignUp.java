package com.zeest.emedz.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.zeest.emedz.R;

public class SignUp extends AppCompatActivity {

    EditText inputName;
    EditText inputPhone;
    EditText inputPassword;
    EditText inputConfirmPassword;

    Button buttonSignUp;

    TextView textSignIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        //Hide Action bar
        getSupportActionBar().hide();


        inputName = findViewById(R.id.inputName);
        inputPhone = findViewById(R.id.inputPhone);
        inputPassword = findViewById(R.id.inputPassword);
        inputConfirmPassword = findViewById(R.id.inputConfirmPassword);
        textSignIn = findViewById(R.id.textSignIn);
        buttonSignUp = findViewById(R.id.buttonSignUp);

        buttonSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(inputName.getText())) {
                    Toast.makeText(getApplicationContext(), "Name cannot be empty", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(inputPhone.getText())) {
                    Toast.makeText(getApplicationContext(), "Phone Number cannot be empty", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(inputPassword.getText())) {
                    Toast.makeText(getApplicationContext(), "Password cannot be empty", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(inputConfirmPassword.getText())) {
                    Toast.makeText(getApplicationContext(), "Confirm Password cannot be empty", Toast.LENGTH_SHORT).show();
                } else if (!TextUtils.equals(inputPassword.getText(), inputConfirmPassword.getText())) {
                    Toast.makeText(getApplicationContext(), "Password and Confirm password are not same", Toast.LENGTH_SHORT).show();
                } else {
                    //verification of email and phone.
                }
            }
        });
        textSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent activity = new Intent(SignUp.this, SignIn.class);
                startActivity(activity);
            }
        });
    }
}
