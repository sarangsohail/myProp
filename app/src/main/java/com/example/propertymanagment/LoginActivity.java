package com.example.propertymanagment;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    private Toolbar mToolbar;

    TextInputEditText mLoginEmail;
    TextInputEditText mLoginPassword;

    private FirebaseAuth mAuth;

    private Button mButton;

    private ProgressBar spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mLoginEmail = (TextInputEditText) findViewById(R.id.login_email);
        mLoginPassword = (TextInputEditText) findViewById(R.id.login_password);
        mButton = (Button) findViewById(R.id.login_button);
        spinner = (ProgressBar)findViewById(R.id.progressBarLogin);
        spinner.setVisibility(View.INVISIBLE);

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = mLoginEmail.getText().toString();
                String password = mLoginPassword.getText().toString();

                if (!TextUtils.isEmpty(email) || !TextUtils.isEmpty(password)){

                    spinner.setVisibility(View.VISIBLE);
                    loginUser(email, password);

                }
            }
        });

    }

    private void loginUser(String email, String password) {

        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful()){

                     spinner.setVisibility(View.INVISIBLE);
                    Intent mainIntent = new Intent(LoginActivity.this, MainActivity.class);
                    //stops the activity from going back to previous activity
                    mainIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(mainIntent);
                    finish();


                }else{
                    spinner.setVisibility(View.INVISIBLE);
                    Toast.makeText(LoginActivity.this, "Can't sign in, please re-check the form and try again", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
