package com.example.projectprmteam2.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.projectprmteam2.R;
import com.example.projectprmteam2.Sharepreference;

public class HomeAdminActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Sharepreference sharepreference= new Sharepreference(this);
        setContentView(R.layout.activity_home_admin);
        Button btnlogout= findViewById(R.id.btn_logoutadmin);
        btnlogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sharepreference.logout();
                sharepreference.removeRole();
                Intent intent = new Intent(HomeAdminActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}