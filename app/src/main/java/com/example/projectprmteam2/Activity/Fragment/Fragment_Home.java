package com.example.projectprmteam2.Activity.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projectprmteam2.R;
import com.example.projectprmteam2.adapter.ProductAdapter;
import com.example.projectprmteam2.api.ApiService;
import com.example.projectprmteam2.model.Product;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment_Home extends Fragment {
    private List<Product> listProduct;
    private RecyclerView recyclerView;
    private Context context;
    private ProductAdapter productAdapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        context = getContext();
        recyclerView = view.findViewById(R.id.rcvSP);
        productAdapter = new ProductAdapter(context,listProduct);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(productAdapter);
        getProductList2();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
//    public void getProductList() {
//
//        ApiService.apiservice.getProductList().enqueue(new Callback<Product>() {
//            @Override
//            public void onResponse(Call<Product> call, Response<Product> response) {
//                listProduct = (List<Product>) response.body();
//                recyclerView.setAdapter(new ProductAdapter(context, listProduct));
//            }
//
//            @Override
//            public void onFailure(Call<Product> call, Throwable t) {
//                Log.e("API Error", "Lỗi khi gọi API", t);
//                Toast.makeText(context, "Lỗi call api1234", Toast.LENGTH_SHORT).show();
//            }
//        });
//    }
    public void getProductList2() {

        ApiService.apiservice.getProductList().enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                listProduct = response.body();
                recyclerView.setAdapter(new ProductAdapter(context, listProduct));
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                Log.e("API Error", "Lỗi khi gọi API", t);
                Toast.makeText(context, "Lỗi call api", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
