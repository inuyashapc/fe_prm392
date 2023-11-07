package com.example.projectprmteam2.Activity.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.projectprmteam2.Activity.LoginActivity;
import com.example.projectprmteam2.R;
import com.example.projectprmteam2.Sharepreference;
import com.example.projectprmteam2.api.ApiService;
import com.example.projectprmteam2.model.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment_Profile extends Fragment_Home{

    private Button btnLogout;
    private TextView tvUsername, tvRole, tvFullname, tvPhone, tvAddress;
    private Sharepreference sharepreference;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        sharepreference = new Sharepreference(requireContext());
        ApiService apiservice = ApiService.apiservice;

        tvUsername = view.findViewById(R.id.tv_username);
        tvRole = view.findViewById(R.id.tv_role);
        tvFullname = view.findViewById(R.id.tv_fullname);
        tvPhone = view.findViewById(R.id.tv_phone);
        tvAddress = view.findViewById(R.id.tv_address);
        btnLogout = view.findViewById(R.id.logout);

        String userId = sharepreference.getCheckLogin();
        Call<User> call = apiservice.getUserApiDetail(userId);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful()) {
                    User user = response.body();
                    if (user != null) {
                        tvUsername.setText("Tên người dùng: " + user.getUsername());
                        tvRole.setText("Vai trò: " + user.getRole());
                        tvFullname.setText("Họ tên: " + user.getFullname());
                        tvPhone.setText("SĐT: " + user.getPhoneNumber());
                        tvAddress.setText("Địa chỉ: " + user.getAddress());
                    }
                } else {
                    // Xử lý khi có lỗi trong việc lấy dữ liệu từ API
                    Toast.makeText(requireContext(), "Lỗi khi lấy thông tin người dùng từ API", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                // Xử lý khi có lỗi trong quá trình kết nối đến API
                Toast.makeText(requireContext(), "Lỗi kết nối đến API", Toast.LENGTH_SHORT).show();
            }
        });

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sharepreference.logout();
                sharepreference.removeRole();
                Intent intent = new Intent(requireContext(), LoginActivity.class);
                startActivity(intent);
                requireActivity().finish();
            }
        });
    }
}
