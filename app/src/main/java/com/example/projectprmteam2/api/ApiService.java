package com.example.projectprmteam2.api;

import com.example.projectprmteam2.model.Brand;
import com.example.projectprmteam2.model.Category;
import com.example.projectprmteam2.model.Product;
import com.example.projectprmteam2.model.Search;
import com.example.projectprmteam2.model.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.List;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiService {
    Gson gson = new GsonBuilder().create();
    //khi su dung phai sua lai dia chi ip theo ip may:ipv4(WindowR->cmd->ipconfig)
    ApiService apiservice = new Retrofit.Builder().baseUrl("https://prm-project.onrender.com/").
            addConverterFactory(GsonConverterFactory.create(gson)).
            build().create(ApiService.class);

    @GET("users/{id}")
    Call<User> getUserApiDetail(@Path("id") String id);

    @GET("users/checkexistusername/{name}")
    Call<Boolean> getExistUsernam(@Path("name") String name);
    @POST("users/login")
    Call<User> login(@Body User user);
    @POST("users")
    Call<Boolean> registerAccount(@Body User user);


    @GET("brands")
    Call<List<Brand>> getBrandList();

    @GET("category")
    Call<Category> getCategoryList();

    @GET("products")
    Call<List<Product>> getProductList();

    @GET("products/{id}")
    Call<Product> getProductDetail(@Path("id") String id);

    @GET("products/fiveCheapest")
    Call<List<Product>> getFiveCheapest();
    @GET("products/brand/{brand}")
    Call<List<Product>> getProductsByBrand(@Path("brand") String brand);

    @GET("products/cheapest")
    Call<List<Product>> getCheapestList();
    @GET("products/search")
    Call<List<Product>> getAllProductSearch(@Body Search search);
}
