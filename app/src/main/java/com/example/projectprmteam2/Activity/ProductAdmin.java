package com.example.projectprmteam2.Activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projectprmteam2.Activity.Fragment.Fragment_Category;
import com.example.projectprmteam2.Activity.Fragment.Fragment_Home;
import com.example.projectprmteam2.Activity.Fragment.Fragment_Profile;
import com.example.projectprmteam2.R;
import com.example.projectprmteam2.Sharepreference;
import com.example.projectprmteam2.adapter.ProductAdapter;
import com.example.projectprmteam2.adapter.ShowMoreProductAdapter;
import com.example.projectprmteam2.api.ApiService;
import com.example.projectprmteam2.model.Product;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductAdmin extends AppCompatActivity {
    private RecyclerView recyclerView;
    private List<Product> listProductViewMore;
    private Context context;
    private ShowMoreProductAdapter showMoreProductAdapter;
    private Button back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getApplicationContext();
        setContentView(R.layout.admin_product);
        recyclerView = findViewById(R.id.rcv_view_more);
        showMoreProductAdapter = new ShowMoreProductAdapter(context, listProductViewMore);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(showMoreProductAdapter);
        getProductListViewMore();
        back = findViewById(R.id.btn_backHome_view_more);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    public void getProductListViewMore() {

        ApiService.apiservice.getProductList().enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                listProductViewMore = response.body();
                recyclerView.setAdapter(new ShowMoreProductAdapter(context, listProductViewMore));
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                Log.e("API Error", "Lỗi khi gọi API", t);
                Toast.makeText(context, "Lỗi call api", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
