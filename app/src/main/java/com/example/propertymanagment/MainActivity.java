package com.example.propertymanagment;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toolbar;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();

    }

    @Override
    public void onStart() {
        super.onStart();
        // if the user isn't signed in, send them to the 'login activity'
        FirebaseUser currentUser = mAuth.getCurrentUser();

        if (currentUser == null){
            Intent startIntent = new Intent(
                    MainActivity.this, StartActivity.class);
            startActivity(startIntent);
            finish();
        }
    }
}
