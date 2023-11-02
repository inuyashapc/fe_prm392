package com.example.projectprmteam2.Activity;


import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import com.example.projectprmteam2.R;
import com.example.projectprmteam2.api.ApiService;
import com.example.projectprmteam2.model.User;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        EditText edtUsername = findViewById(R.id.edtusename);
        EditText edtPassword = findViewById(R.id.edtpassword);
        EditText edtPassword_re = findViewById(R.id.edtpassword_re);
        Button btnRegister = findViewById(R.id.btnregister);
        ImageView btnbacklogin = findViewById(R.id.btn_back_to_login);
        btnbacklogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = edtUsername.getText().toString();
                String password = edtPassword.getText().toString();
                String repassword = edtPassword_re.getText().toString();
                if (username.isEmpty() || password.isEmpty() || repassword.isEmpty()) {
                    if (username.isEmpty())
                        edtUsername.setError("Username cannot empty");
                    if (password.isEmpty())
                        edtPassword.setError("Password cannot empty");
                    if (repassword.isEmpty())
                        edtPassword_re.setError("Repassword cannot empty");
                }
                else if(!isPasswordStrong(password)){
                    Toast.makeText(RegisterActivity.this, "Password length must > 8, contain lower and upper case", Toast.LENGTH_SHORT).show();
                }
                else if (!password.equals(repassword)) {
                    Toast.makeText(RegisterActivity.this, "RePassword must same password!", Toast.LENGTH_SHORT).show();
                }
                else {
                    checkExistAcount(username,password);
                }
//                    if (ContextCompat.checkSelfPermission(RegisterActivity.this, Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_GRANTED) {
//                        sendOtp();
//                    }
//                    else{
//                        ActivityCompat.requestPermissions(RegisterActivity.this, new String[]{Manifest.permission.SEND_SMS},100);
//                    }
                }

        });
    }

//    @SuppressLint("MissingSuperCall")
//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//        if(requestCode==100)
//        {
//            if(grantResults.length>0&&grantResults[0]==PackageManager.PERMISSION_GRANTED){
//                sendOtp();
//            }
//            else{
//                Toast.makeText(this,"Permission denied",Toast.LENGTH_SHORT).show();
//            }
//        }
//    }

//    private void sendOtp() {
//        SmsManager smsManager= SmsManager.getDefault();
//        ArrayList<String> part= smsManager.divideMessage("otp la 120");
//        smsManager.sendMultipartTextMessage("0961036929",null,part,null,null);
//        Toast.makeText(RegisterActivity.this, "Đã gửi tin nhắn", Toast.LENGTH_SHORT).show();
//    }

    private void checkExistAcount(String username,String password) {
        ApiService.apiservice.getExistUsernam(username).enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                boolean exists = response.body();
                if(exists)
                   Toast.makeText(RegisterActivity.this, "User has existed! Please try another username!", Toast.LENGTH_SHORT).show();
                else
                    createAccount(username,password);
            }
            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {
                Toast.makeText(RegisterActivity.this, "Error checking username existence.", Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void createAccount(String username, String password) {
        User user = new User(username,password);
        ApiService.apiservice.registerAccount(user).enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                boolean check= response.body();
                if (check)
                {
                    Toast.makeText(RegisterActivity.this, "Account Register Successfully", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(RegisterActivity.this,LoginActivity.class);
                    startActivity(intent);
                    finish();
                }
                else{
                    Toast.makeText(RegisterActivity.this, "Account Register Error!", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {
                Toast.makeText(RegisterActivity.this, "Register API Error!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public boolean isPasswordStrong(String password) {
        if (password.length() < 8) {
            return false;
        }
        boolean hasUppercase = false;
        boolean hasLowercase = false;
        for (char c : password.toCharArray()) {
            if (Character.isUpperCase(c)) {
                hasUppercase = true;
            } else if (Character.isLowerCase(c)) {
                hasLowercase = true;
            }
        }
        return hasUppercase && hasLowercase;
    }
}