package com.example.propertymanagment;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private ViewPager mViewPager;

    private SectionsPagerAdapter mSectionsPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();

        getSupportActionBar().setTitle("Chats");

        //tab viewpager
        mViewPager = (ViewPager) findViewById(R.id.mainPager);

    }

    @Override
    public void onStart() {
        super.onStart();
        // if the user isn't signed in, send them to the 'login activity'
        FirebaseUser currentUser = mAuth.getCurrentUser();

        if (currentUser == null){

            sendToStartActivity();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.chat_menu, menu);


        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.main_signout_button){
            FirebaseAuth.getInstance().signOut();
            sendToStartActivity();
        }

        return super.onOptionsItemSelected(item);
    }

    private void sendToStartActivity(){

        Intent startIntent = new Intent(
                MainActivity.this, StartActivity.class);
        startActivity(startIntent);
        finish();
    }
}
