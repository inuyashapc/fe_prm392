package com.example.projectprmteam2.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.projectprmteam2.MainActivity;
import com.example.projectprmteam2.R;
import com.example.projectprmteam2.Sharepreference;
import com.example.projectprmteam2.api.ApiService;
import com.example.projectprmteam2.model.LoginResponse;
import com.example.projectprmteam2.model.Logindata;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    public Sharepreference preferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loginactivity);
        preferences= new Sharepreference(this);
    EditText edtusename = findViewById(R.id.edtusername);
    EditText  edtpassword = findViewById(R.id.edtpassword);
    Button loginButton = findViewById(R.id.btnlogin);
    Button registerButton = findViewById(R.id.btnregister_login);
    registerButton.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
            startActivity(intent);
            finish();
        }
    });

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

                if(loginResponse.isSuccess()){
                    preferences.login(loginResponse.isSuccess());
                    Toast.makeText(LoginActivity.this, "Đang nhap thanh cong", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }else
                    Toast.makeText(LoginActivity.this, "username or password not correct", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Toast.makeText(LoginActivity.this, "Loi co so du lieu", Toast.LENGTH_SHORT).show();
            }
        });
    }
}