package com.example.projectprmteam2.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projectprmteam2.ProductDetail;
import com.example.projectprmteam2.R;
import com.example.projectprmteam2.api.ApiService;
import com.example.projectprmteam2.model.Product;
import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductHomeScrollAdapter extends RecyclerView.Adapter<ProductHomeScrollAdapter.ProductHomeScrollViewHolder> {
    private Context context;
    private List<Product> listProduct;

    public ProductHomeScrollAdapter(Context context, List<Product> listProduct) {
        this.context = context;
        this.listProduct = listProduct;
    }

    @NonNull
    @Override
    public ProductHomeScrollViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_product_scroll, parent, false);
        return new ProductHomeScrollViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductHomeScrollViewHolder holder, int position) {
        Product product = listProduct.get(position);
        if (product == null)
            return;
        if (product.getThumbnail() != null) {
            Picasso.get().load(product.getThumbnail()).into(holder.imageView);
        }
//        holder.tv_product_name.setText(product.getName());
        String productName = product.getName();
        if (productName.length() > 16) {
            // Nếu chuỗi dài hơn 16 kí tự, chỉ lấy 16 kí tự đầu tiên và thêm dấu "..."
            productName = productName.substring(0, 16) + "...";
        }
        holder.tv_product_name.setText(productName);

        holder.tv_product_price.setText("Giá: " + (int) product.getPrice() + " vnd");
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getProductDetail(product.get_id());
            }
        });
    }

    @Override
    public int getItemCount() {
        if (listProduct != null)
            return listProduct.size();
        return 0;
    }


    public class ProductHomeScrollViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ImageView imageView;
        private TextView tv_product_name, tv_product_price;

        public ProductHomeScrollViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.img_product_scroll);
            tv_product_name = itemView.findViewById(R.id.tv_product_name_scroll);
            tv_product_price = itemView.findViewById(R.id.tv_product_price_scroll);
        }

        @Override
        public void onClick(View v) {

        }
    }

    public void getProductDetail(String id) {
        ApiService.apiservice.getProductDetail(id).enqueue(new Callback<Product>() {
            @Override
            public void onResponse(Call<Product> call, Response<Product> response) {
                Product product = response.body();
                Intent intent = new Intent(context, ProductDetail.class);
                intent.putExtra("product", product);
                context.startActivity(intent);
            }

            @Override
            public void onFailure(Call<Product> call, Throwable t) {
                System.out.println("data" + t);
                Toast.makeText(context, "Lỗi call api 1234", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
