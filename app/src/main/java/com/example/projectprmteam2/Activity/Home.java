package com.example.projectprmteam2.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.projectprmteam2.Fragment.Fragment_Home;
import com.example.projectprmteam2.R;
import com.example.projectprmteam2.Sharepreference;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class Home extends AppCompatActivity {
    Sharepreference sharepreference;
    private BottomNavigationView bottomNavigationView;
    private Fragment fragment;
    private FragmentManager manager;
    private FragmentTransaction transaction;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setSelectedItemId(R.id.home);
        sharepreference=new Sharepreference(this);
        Init();


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

    private void Init() {
        fragment = new Fragment_Home();
        manager = getSupportFragmentManager();
        transaction =manager.beginTransaction();
        bottomNavigationView.setBackground(null);
        getSupportFragmentManager().beginTransaction().replace(R.id.framelayout,fragment).commit();
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.home) {
                    fragment = new Fragment_Home();
                }

//                switch (item.getItemId()){
//                    case  R.id.home:
//                        fragment = new Fragment_Home();
//                        break;
//                    case  R.id.donhang:
//                        fragment=new FragMent_Bill();
//                        break;
//                    case  R.id.person:
//                        fragment = new SettingFragment();
//                        break;
//                    case  R.id.category:
//                        startActivity(new Intent(HomeActivity.this, ThongKeDanhMucActivity.class));break;
//                }
                if(fragment!=null){
                    getSupportFragmentManager().beginTransaction().replace(R.id.framelayout,fragment).commit();
                }
                //drawerLayout.closeDrawers();
                return true;
            }
        });
    }
}