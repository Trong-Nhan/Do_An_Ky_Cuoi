package com.example.projectfinal.activity.admin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.projectfinal.R;
import com.example.projectfinal.adapter.admin.AdminPublisherAdapter;
import com.example.projectfinal.api.PublisherAPI;
import com.example.projectfinal.entity.Publisher;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdminPublisherActivity extends AppCompatActivity {
    private List<Publisher> mLstPublisher = new ArrayList<>();
    private AdminPublisherAdapter mAdminPublisherAdapter;
    RecyclerView recyclerViewCategory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_publisher);

        getList();
        recyclerViewCategory = findViewById(R.id.rcvAdminPublisher);
        //set dữ liệu lên recycler view
        recyclerViewCategory.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewCategory.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        //chuyen sang form them moi
        TextView txtAdd = findViewById(R.id.add_publisher);
        txtAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminPublisherActivity.this, AddPublisherActivity.class);
                startActivity(intent);
            }
        });
    }

    //Lay du lieu Category qua API
    private void getList(){
        PublisherAPI.publisherAPI.getPublisher().enqueue(new Callback<List<Publisher>>() {
            @Override
            public void onResponse(Call<List<Publisher>> call, Response<List<Publisher>> response) {
                if (response.isSuccessful()) {
                    mLstPublisher = response.body();
                    mAdminPublisherAdapter = new AdminPublisherAdapter(AdminPublisherActivity.this, mLstPublisher);
                    recyclerViewCategory.setAdapter(mAdminPublisherAdapter);
                }
            }

            @Override
            public void onFailure(Call<List<Publisher>> call, Throwable t) {
                Toast.makeText(AdminPublisherActivity.this, "Lỗi khi gọi API", Toast.LENGTH_SHORT).show();
            }
        });
    }
}