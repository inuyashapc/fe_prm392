package com.example.projectprmteam2.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.projectprmteam2.R;
import com.example.projectprmteam2.Sharepreference;

public class Home extends AppCompatActivity {
    Sharepreference sharepreference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        sharepreference=new Sharepreference(this);
//        Button btnlogout= findViewById(R.id.button);
//
//        btnlogout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                sharepreference.logout();
//                Intent intent = new Intent(Home.this,LoginActivity.class);
//                startActivity(intent);
//                finish();
//            }
//        });
    }
}