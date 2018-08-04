package com.example.propertymanagment;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toolbar;

public class LoginActivity extends AppCompatActivity {

    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mToolbar =(Toolbar) findViewById(R.id.LoginToolbar);
        mToolbar.setTitle("Sign In");
    }
}
