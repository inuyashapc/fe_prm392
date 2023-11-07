package com.example.projectprmteam2.Activity.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projectprmteam2.R;
import com.example.projectprmteam2.model.Product;

import java.util.List;

public class Fragment_Cart extends Fragment {
    private RecyclerView recyclerView;
    private Context context;
    private List<Product> listProduct;
    private TextView tv_back, tv_add;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cart, container, false);
        context = getContext();
        initCart(view);
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    private void initCart(View view) {

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}
