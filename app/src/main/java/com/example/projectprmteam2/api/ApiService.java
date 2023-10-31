package com.example.projectprmteam2.api;

import com.example.projectprmteam2.model.Brand;
import com.example.projectprmteam2.model.Category;
import com.example.projectprmteam2.model.LoginResponse;
import com.example.projectprmteam2.model.Logindata;
import com.example.projectprmteam2.model.Product;
import com.example.projectprmteam2.model.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {
    Gson gson = new GsonBuilder().create();
    //khi su dung phair sua lai dia chi ip theo ip may:ipv4(WindowR->cmd->ipconfig)
    ApiService apiservice = new Retrofit.Builder().baseUrl("http://192.168.1.252:9999/").
            addConverterFactory(GsonConverterFactory.create(gson)).
            build().create(ApiService.class);

    @GET("users/{id}")
    Call<User> getUserApiDetail(@Path("id") String id);
    @POST("users/login")
    Call<LoginResponse> login(@Body Logindata logindata);

    @GET("brands")
    Call<Brand> getBrandList();

    @GET("category")
    Call<Category> getCategoryList();

    @GET("products")
    Call<List<Product>> getProductList();
}
