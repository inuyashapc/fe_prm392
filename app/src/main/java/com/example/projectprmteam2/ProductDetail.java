package com.example.projectprmteam2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.projectprmteam2.model.Product;
import com.squareup.picasso.Picasso;

public class ProductDetail extends AppCompatActivity {
    private ImageView imageView;
    private TextView tv_name, tv_category, tv_brand, tv_price, tv_description;
    private Button back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);
        Intent myIntent = getIntent();
        imageView = findViewById(R.id.img_product_detail);
        tv_name = findViewById(R.id.tv_product_detail_name);
        tv_category = findViewById(R.id.tv_product_detail_category);
        tv_brand = findViewById(R.id.tv_product_detail_brand);
        tv_price = findViewById(R.id.tv_product_detail_price);
        tv_description = findViewById(R.id.tv_product_detail_description);
        back = findViewById(R.id.btn_backHome);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        Product product = ((Product) myIntent.getSerializableExtra("product"));
        Picasso.get().load(product.getThumbnail()).into(imageView);
        tv_name.setText(product.getName());
        tv_category.setText(product.getCategory().getName());
        tv_brand.setText(product.getBrand().getName());
        tv_price.setText((int)product.getPrice()+ " vnd");
        tv_description.setText(product.getDescription());
    }
}