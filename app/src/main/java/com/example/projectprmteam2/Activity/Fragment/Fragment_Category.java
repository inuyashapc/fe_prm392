package com.example.projectprmteam2.Activity.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projectprmteam2.Activity.LoginActivity;
import com.example.projectprmteam2.R;
import com.example.projectprmteam2.adapter.BrandAdapter;
import com.example.projectprmteam2.adapter.ProductAdapter;
import com.example.projectprmteam2.api.ApiService;
import com.example.projectprmteam2.model.Brand;
import com.example.projectprmteam2.model.Product;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment_Category extends Fragment {

    private Spinner brandSpinner;
    private List<Brand> brandList;
    private List<Product> productList;
    private Context context;
    private ArrayAdapter<Brand> brandAdapter;
    private RecyclerView recyclerViewProductByBrand;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_category, container, false);
        context = getContext();
        brandSpinner = view.findViewById(R.id.brandSpinner);
        recyclerViewProductByBrand = view.findViewById(R.id.productRecyclerView);
        loadBrandList();
        loadProductsByBrand("653e1565dbe31530645397b6");
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerViewProductByBrand.setLayoutManager(layoutManager);

        return view;
    }

    private void loadBrandList() {
        ApiService.apiservice.getBrandList().enqueue(new Callback<List<Brand>>() {
            @Override
            public void onResponse(Call<List<Brand>> call, Response<List<Brand>> response) {
                brandList = response.body(); // Lấy danh sách brand từ phản hồi API
                setupBrandSpinner();
            }

            @Override
            public void onFailure(Call<List<Brand>> call, Throwable t) {
                Toast.makeText(getView().getContext(), "Lỗi: Không thể tải dữ liệu", Toast.LENGTH_SHORT).show();
            }

        });
    }

    private void setupBrandSpinner() {
        brandAdapter = new BrandAdapter(requireContext(), brandList);
        brandSpinner.setAdapter(brandAdapter);

        brandSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                // Xử lý sự kiện khi người dùng chọn một hãng bàn phím
                Brand selectedBrand = brandList.get(position);

                String selectedBrandId = selectedBrand.get_id();
                // Thực hiện việc tải sản phẩm dựa trên hãng bàn phím đã chọn
                loadProductsByBrand(selectedBrandId);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // Xử lý khi không có lựa chọn nào
            }
        });
    }


    private void loadProductsByBrand(String brandId) {
        // Thực hiện việc tải sản phẩm dựa trên hãng bàn phím
        // Gọi phương thức ApiService để tải danh sách sản phẩm dựa trên brandId
        ApiService.apiservice.getProductsByBrand(brandId).enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                productList = response.body();
                recyclerViewProductByBrand.setAdapter(new ProductAdapter(context, productList));
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                Toast.makeText(context, "Lỗi call api product by brand", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}
