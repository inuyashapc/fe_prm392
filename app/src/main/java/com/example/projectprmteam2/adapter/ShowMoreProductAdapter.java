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

public class ShowMoreProductAdapter extends RecyclerView.Adapter<ShowMoreProductAdapter.ProductShowMoreViewHolder> {
    private Context context;
    private List<Product> listProductViewMore;

    public ShowMoreProductAdapter(Context context, List<Product> listProductViewMore) {
        this.context = context;
        this.listProductViewMore = listProductViewMore;
    }

    @NonNull
    @Override
    public ProductShowMoreViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_list_product_show_more, parent, false);
        return new ProductShowMoreViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductShowMoreViewHolder holder, int position) {
        Product product = listProductViewMore.get(position);
        if (product == null)
            return;
        if (product.getThumbnail() != null) {
            Picasso.get().load(product.getThumbnail()).into(holder.imageView);
        }
        holder.tv_product_name.setText(product.getName());
        holder.tv_product_category.setText("Switch: " + product.getCategory().getName());
        holder.tv_brand_product.setText("Hãng: " + product.getBrand().getName());
        holder.tv_product_price.setText("Giá: " + (int) product.getPrice() + " vnd");
    }

    @Override
    public int getItemCount() {
        if (listProductViewMore != null)
            return listProductViewMore.size();
        return 0;
    }

    public class ProductShowMoreViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ImageView imageView;
        private TextView tv_product_name, tv_product_category, tv_brand_product, tv_product_price;


        public ProductShowMoreViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.img_product_view_more);
            tv_product_name = itemView.findViewById(R.id.tv_product_name_view_more);
            tv_product_category = itemView.findViewById(R.id.tv_product_category_view_more);
            tv_brand_product = itemView.findViewById(R.id.tv_brand_product_view_more);
            tv_product_price = itemView.findViewById(R.id.tv_product_price_view_more);
        }

        @Override
        public void onClick(View v) {

        }
    }
}
