package com.example.propertymanagment;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {

    private TextInputEditText mDisplayName;
    private TextInputEditText mEmail;
    private TextInputEditText mPassword;
    private Button mCreateButton;

    private FirebaseAuth mAuth;

    //progress dialog
    private ProgressBar spinner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Toolbar mToolbar = (Toolbar) findViewById(R.id.registration_toolbar);
        mToolbar.setTitle("Create Account");

         //getting firebase instance
        mAuth = FirebaseAuth.getInstance();

        mDisplayName = (TextInputEditText) findViewById(R.id.reg_display_name);
        mEmail = (TextInputEditText) findViewById(R.id.reg_email_create);
        mPassword = (TextInputEditText) findViewById(R.id.reg_password);
        mCreateButton = (Button) findViewById(R.id.reg_button_create);
        spinner = (ProgressBar)findViewById(R.id.progressBarReg);
        spinner.setVisibility(View.INVISIBLE);


        mCreateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String display_name = mDisplayName.getText().toString().trim();
                String email = mEmail.getText().toString().trim();
                String password = mPassword.getText().toString().trim();

                if (!TextUtils.isEmpty(display_name) && !TextUtils.isEmpty(email) && !TextUtils.isEmpty(password)){

                    spinner.setVisibility(View.VISIBLE);
                    registerUser(display_name, email, password);

                }
            }
            //register user code
            private void registerUser(String display_name, String email, String password) {

               mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                   @Override
                   public void onComplete(@NonNull Task<AuthResult> task) {
                       if (task.isSuccessful()){

                           spinner.setVisibility(View.INVISIBLE);

                           Intent mainIntent = new Intent(RegisterActivity.this, StartActivity.class);
                           mainIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                           startActivity(mainIntent);
                           Toast.makeText(RegisterActivity.this, "Account Created", Toast.LENGTH_SHORT).show();
                           finish();

                       }else{
                           spinner.setVisibility(View.INVISIBLE);
                           Toast.makeText(RegisterActivity.this, "There is a problem with input, please try again.", Toast.LENGTH_SHORT).show();
                       }
                   }
               });
            }
        });
    }


    //todo - add all the checking for the textfields - such as the internet checking  and password complexity
}
