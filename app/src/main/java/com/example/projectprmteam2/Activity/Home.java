package com.example.projectprmteam2.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.projectprmteam2.Activity.Fragment.Fragment_Home;
import com.example.projectprmteam2.Activity.Fragment.Fragment_Profile;
import com.example.projectprmteam2.R;
import com.example.projectprmteam2.Sharepreference;
import com.example.projectprmteam2.adapter.ProductAdapter;
import com.example.projectprmteam2.api.ApiService;
import com.example.projectprmteam2.model.Product;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Home extends AppCompatActivity {
    Sharepreference sharepreference;
    private BottomNavigationView bottomNavigationView;
    private Fragment fragment;
    private FragmentManager manager;
    private FragmentTransaction transaction;

    private ProductAdapter productAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setSelectedItemId(R.id.home);
        sharepreference = new Sharepreference(this);
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
        transaction = manager.beginTransaction();
        getSupportFragmentManager().beginTransaction().replace(R.id.framelayout, fragment).commit();
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.home) {
                    fragment = new Fragment_Home();
                } else if (item.getItemId() == R.id.person) {
                    fragment = new Fragment_Profile();
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
                if (fragment != null) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.framelayout, fragment).commit();
                }
                //drawerLayout.closeDrawers();
                return true;
            }
        });
    }


}