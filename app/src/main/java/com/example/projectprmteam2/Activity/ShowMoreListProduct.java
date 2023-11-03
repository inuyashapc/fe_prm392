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

public class ShowMoreListProduct extends AppCompatActivity {
    private RecyclerView recyclerView;
    private List<Product> listProductViewMore;
    private Context context;
    private ShowMoreProductAdapter showMoreProductAdapter;
    private Button back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getApplicationContext();
        setContentView(R.layout.activity_show_more_list_product);
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