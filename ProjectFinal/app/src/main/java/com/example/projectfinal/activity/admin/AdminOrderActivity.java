package com.example.projectfinal.activity.admin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.projectfinal.R;
import com.example.projectfinal.adapter.admin.AdminOrderAdapter;
import com.example.projectfinal.api.OrderAPI;
import com.example.projectfinal.entity.Order;
import com.example.projectfinal.entity.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdminOrderActivity extends AppCompatActivity {
    private User mUser;
    private List<Order> mLstOrder;
    private AdminOrderAdapter mAdminOrderAdapter;
    private RecyclerView rcvOrder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_order);

        Bundle bundle = getIntent().getExtras();

        if(bundle == null){
            return;
        }

        mUser = (User) bundle.get("userInfo");
        rcvOrder = findViewById(R.id.rcvAdminOrder);

        OrderAPI.orderApi.getOrders().enqueue(new Callback<List<Order>>() {
            @Override
            public void onResponse(Call<List<Order>> call, Response<List<Order>> response) {
                if(response.isSuccessful()){
                    mLstOrder = response.body();
                    mAdminOrderAdapter = new AdminOrderAdapter(AdminOrderActivity.this, mLstOrder, mUser);
                    rcvOrder.setLayoutManager(new LinearLayoutManager(AdminOrderActivity.this));
                    rcvOrder.setAdapter(mAdminOrderAdapter);
                }
            }

            @Override
            public void onFailure(Call<List<Order>> call, Throwable t) {
                Toast.makeText(AdminOrderActivity.this, "Lỗi khi gọi API", Toast.LENGTH_SHORT).show();
            }
        });

    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(AdminOrderActivity.this, AdminActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("userInfo", mUser);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}