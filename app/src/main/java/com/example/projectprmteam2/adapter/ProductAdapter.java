package com.example.projectprmteam2.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projectprmteam2.R;
import com.example.projectprmteam2.model.Product;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {
    private Context context;
    private List<Product> listProduct;

    public ProductAdapter(Context context, List<Product> listProduct) {
        this.context = context;
        this.listProduct = listProduct;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_product, parent, false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        Product product = listProduct.get(position);
        if (product == null)
            return;
//        Picasso.get().load(product.getThumbnail()).into(holder.imageView);
//        holder.imageView.setImageResource(product.getThumbnail());
        holder.tv_brand_product.setText("Sản phẩm: "+product.getName());
        holder.tv_product_category.setText("Loại bàn phím: "+product.getCategory().getName());
        holder.tv_brand_product.setText("Nhãn hiệu: "+product.getBrand().getName());
        holder.tv_product_price.setText("Giá: "+product.getPrice());
    }

    @Override
    public int getItemCount() {
        if (listProduct != null)
            return listProduct.size();
        return 0;
    }

    public class ProductViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ImageView imageView;
        private TextView tv_product_name, tv_product_category, tv_brand_product, tv_product_price;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.img_product);
            tv_product_name = itemView.findViewById(R.id.tv_product_name);
            tv_product_category = itemView.findViewById(R.id.tv_product_category);
            tv_brand_product = itemView.findViewById(R.id.tv_brand_product);
            tv_product_price = itemView.findViewById(R.id.tv_product_price);
        }

        @Override
        public void onClick(View v) {

        }
    }
}
