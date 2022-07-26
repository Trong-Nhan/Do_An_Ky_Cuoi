package com.example.projectfinal.activity.admin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.projectfinal.R;
import com.example.projectfinal.adapter.AdminAccountAdapter;
import com.example.projectfinal.api.UserAPI;
import com.example.projectfinal.entity.User;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdminAccountActivity extends AppCompatActivity {
    private RecyclerView mRcvAccount;
    private AdminAccountAdapter mAdapter;
    private List<User> mLst;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_account);

        mRcvAccount = findViewById(R.id.rcvAdminAccount);
        mLst = new ArrayList<>();

        //set dữ liệu lên recycler view
        mRcvAccount.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        getListUsers();
    }

    private void getListUsers() {
        UserAPI.userApi.getListUsers().enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                if (response.isSuccessful()){
                    mLst = response.body();
                    mAdapter = new AdminAccountAdapter(AdminAccountActivity.this, mLst);
                    mRcvAccount.setAdapter(mAdapter);
                }
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                Toast.makeText(AdminAccountActivity.this, "Lỗi khi gọi API", Toast.LENGTH_SHORT).show();
            }
        });
    }
}