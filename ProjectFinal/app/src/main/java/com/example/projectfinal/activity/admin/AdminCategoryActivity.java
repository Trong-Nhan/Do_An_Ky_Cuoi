package com.example.projectfinal.activity.admin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.projectfinal.R;
import com.example.projectfinal.activity.LoginActivity;
import com.example.projectfinal.activity.RegisterActivity;
import com.example.projectfinal.adapter.admin.AdminCategoryAdapter;
import com.example.projectfinal.api.CategoryAPI;
import com.example.projectfinal.api.UserAPI;
import com.example.projectfinal.entity.Category;
import com.example.projectfinal.entity.User;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdminCategoryActivity extends AppCompatActivity {
    private List<Category> mLstCategory = new ArrayList<>();
    private AdminCategoryAdapter mAdminCategoryAdapter;
    RecyclerView recyclerViewCategory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_category);

        getList();
        recyclerViewCategory = findViewById(R.id.rcvAdminCategory);
        //set dữ liệu lên recycler view
        recyclerViewCategory.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewCategory.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        //chuyen sang form them moi
        TextView txtAdd = findViewById(R.id.add_category);
        txtAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminCategoryActivity.this, AddCategoryActivity.class);
                startActivity(intent);
            }
        });
    }

    //Lay du lieu Category qua API
    private void getList() {
        CategoryAPI.categoryAPI.getCategory().enqueue(new Callback<List<Category>>() {
            @Override
            public void onResponse(Call<List<Category>> call, Response<List<Category>> response) {
                if (response.isSuccessful()) {
                    mLstCategory = response.body();
                    mAdminCategoryAdapter = new AdminCategoryAdapter(AdminCategoryActivity.this, mLstCategory);
                    recyclerViewCategory.setAdapter(mAdminCategoryAdapter);
                }
            }

            @Override
            public void onFailure(Call<List<Category>> call, Throwable t) {
                Toast.makeText(AdminCategoryActivity.this, "Lỗi khi gọi API", Toast.LENGTH_SHORT).show();
            }
        });
    }
}