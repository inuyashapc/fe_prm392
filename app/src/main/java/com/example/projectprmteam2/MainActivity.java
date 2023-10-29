package com.example.projectprmteam2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.projectprmteam2.Activity.Home;
import com.example.projectprmteam2.Activity.LoginActivity;


public class MainActivity extends AppCompatActivity {
//    Sharepreference preferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Sharepreference preferences= new Sharepreference(this);
        if (preferences.getCheckLogin()) {
            Intent intent = new Intent(MainActivity.this, Home.class);
            startActivity(intent);
            finish();
        } else {
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        }
    }

}