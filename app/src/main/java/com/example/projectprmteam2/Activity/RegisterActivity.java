package com.example.projectprmteam2.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.projectprmteam2.R;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        EditText edtUsername = findViewById(R.id.edtusename);
        EditText edtPassword = findViewById(R.id.edtpassword);
        EditText edtPassword_re = findViewById(R.id.edtpassword_re);
        Button btnRegister = findViewById(R.id.btnregister);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = edtUsername.getText().toString();
                String password = edtPassword.getText().toString();
                String repassword = edtPassword_re.getText().toString();
                if (!password.equals("repassword"))
                    Toast.makeText(RegisterActivity.this, "RePassword must same password", Toast.LENGTH_SHORT).show();
                else
                    createAccount();

            }
        });
    }


    private void createAccount() {
    }
}