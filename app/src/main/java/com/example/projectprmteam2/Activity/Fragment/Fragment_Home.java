package com.example.projectprmteam2.Activity.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projectprmteam2.Activity.ShowMoreListCheapestProduct;
import com.example.projectprmteam2.Activity.ShowMoreListProduct;
import com.example.projectprmteam2.R;
import com.example.projectprmteam2.adapter.ProductAdapter;
import com.example.projectprmteam2.adapter.ProductHomeScrollAdapter;
import com.example.projectprmteam2.adapter.ShowMoreProductAdapter;
import com.example.projectprmteam2.api.ApiService;
import com.example.projectprmteam2.model.Product;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment_Home extends Fragment {
    private ProductAdapter productAdapter;
    private ProductHomeScrollAdapter productCheapAdapter;
    private RecyclerView recyclerView;
    private RecyclerView recyclerViewCheap;
    private Context context;
    private List<Product> listProduct;
    private List<Product> listProductCheap;
    private RecyclerView recyclerView_view_more;
    private List<Product> listProduct_view_more;
    private TextView tv_view_more_1;
    private TextView tv_view_more_cheapest;
    private Handler handler;
    private Runnable runnable;
    private boolean isLastItemVisible = false;
    private SearchView searchView;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        context = getContext();
        initCheapProduct(view);
        initProduct(view);
        handleSearchView(view);
        return view;
    }

    private void initProduct(View view) {
        recyclerView = view.findViewById(R.id.rcvSP);
//        recyclerView.setNestedScrollingEnabled(false);
        productAdapter = new ProductAdapter(context,listProduct);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(productAdapter);
        getProductList();
        tv_view_more_1 = view.findViewById(R.id.home_view_more_1);
        tv_view_more_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context.getApplicationContext(), ShowMoreListProduct.class);
                startActivity(intent);
            }
        });
    }

    private void initCheapProduct(View view) {
        recyclerViewCheap = view.findViewById(R.id.rcv_cheap);
        productCheapAdapter = new ProductHomeScrollAdapter(context, listProductCheap);
        LinearLayoutManager layoutManager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
        recyclerViewCheap.setLayoutManager(layoutManager);
        handleScrollView();
        recyclerViewCheap.setAdapter(productCheapAdapter);
        getProductListCheap();
        tv_view_more_cheapest = view.findViewById(R.id.home_view_more_cheapest);
        tv_view_more_cheapest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context.getApplicationContext(), ShowMoreListCheapestProduct.class);
                startActivity(intent);
            }
        });
    }

    private void handleScrollView() {
        recyclerViewCheap.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                isLastItemVisible = !recyclerViewCheap.canScrollHorizontally(1); // Kiểm tra xem đã cuộn đến cuối danh sách chưa
            }
        });

        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {
                if (isLastItemVisible) {
                    recyclerViewCheap.smoothScrollToPosition(0); // Cuộn về đầu danh sách
                } else {
                    if (recyclerViewCheap != null && recyclerViewCheap.getLayoutManager() != null) {
                        int firstVisibleItemPosition = ((LinearLayoutManager) recyclerViewCheap.getLayoutManager()).findFirstVisibleItemPosition();
                        recyclerViewCheap.smoothScrollToPosition(firstVisibleItemPosition + 2); // Cuộn tới hàng kế tiếp (2 item)
                    }
                }
                handler.postDelayed(this, 2000); // Thời gian cuộn mỗi lần (ms)
            }
        };

        handler.postDelayed(runnable, 2000);
    }
    private void handleSearchView(View view) {
        searchView=view.findViewById(R.id.home_search);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }
            @Override
            public boolean onQueryTextChange(String s) {
                searchUser(s);
                return true;
            }
        });
    }
    private void searchUser(String text_search) {
        List<Product> searchResult = new ArrayList<>();
        for (Product product: listProduct) {
            String product_name = product.getName().toLowerCase().trim();
            if(product_name.contains(text_search.toLowerCase().trim())||
                    product.getBrand().getName().toLowerCase().trim().contains(text_search.toLowerCase().trim())){
                searchResult.add(product);
            }
        }
        if (searchResult.isEmpty()){
            Toast.makeText(context,"Không tìm thấy user!",Toast.LENGTH_SHORT).show();
        }
        recyclerView.setAdapter(new ProductAdapter(context, searchResult));
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
    public void getProductList() {

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

    public void getProductListCheap() {
        ApiService.apiservice.getFiveCheapest().enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                listProductCheap = response.body();
                recyclerViewCheap.setAdapter(new ProductHomeScrollAdapter(context, listProductCheap));
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                Log.e("API Error", "Lỗi khi gọi API", t);
                Toast.makeText(context, "Lỗi call api", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
