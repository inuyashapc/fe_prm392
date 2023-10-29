package com.example.projectprmteam2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.projectprmteam2.api.ApiService;
import com.example.projectprmteam2.model.LoginResponse;
import com.example.projectprmteam2.model.Logindata;
import com.example.projectprmteam2.model.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loginform);

        EditText edtusename = findViewById(R.id.editTextText3);
        EditText  edtpassword = findViewById(R.id.editTextNumberPassword);
        Button loginButton = findViewById(R.id.button2);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Logindata logindata = new Logindata(edtusename.getText().toString(),edtpassword.getText().toString());
                login(logindata);

            }
        });
    }


    private void login(Logindata login) {
        ApiService.apiservice.login(login).enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                LoginResponse loginResponse = response.body();

                if(!loginResponse.isSuccess())
                    Toast.makeText(MainActivity.this, "username or password not correct", Toast.LENGTH_SHORT).show();
                Toast.makeText(MainActivity.this, "Đang nhap thanh cong", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Loi co so du lieu", Toast.LENGTH_SHORT).show();
            }
        });
    }

}