package com.example.projectprmteam2.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.projectprmteam2.R;
import com.example.projectprmteam2.adapter.ShowMoreProductAdapter;
import com.example.projectprmteam2.api.ApiService;
import com.example.projectprmteam2.model.Product;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ShowMoreListCheapestProduct extends AppCompatActivity {
    private RecyclerView recyclerView;
    private List<Product> listCheapestProductViewMore;
    private Context context;
    private ShowMoreProductAdapter showMoreCheapestProductAdapter;
    private Button back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getApplicationContext();
        setContentView(R.layout.activity_show_more_list_cheapest_product);
        recyclerView = findViewById(R.id.rcv_view_more);
        showMoreCheapestProductAdapter = new ShowMoreProductAdapter(context, listCheapestProductViewMore);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(showMoreCheapestProductAdapter);
        getCheapestProductListViewMore();
        back = findViewById(R.id.btn_backHome_view_more_cheapest);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    public void getCheapestProductListViewMore() {

        ApiService.apiservice.getProductList().enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                listCheapestProductViewMore = response.body();
                recyclerView.setAdapter(new ShowMoreProductAdapter(context, listCheapestProductViewMore));
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                Log.e("API Error", "Lỗi khi gọi API", t);
                Toast.makeText(context, "Lỗi call api", Toast.LENGTH_SHORT).show();
            }
        });
    }
}