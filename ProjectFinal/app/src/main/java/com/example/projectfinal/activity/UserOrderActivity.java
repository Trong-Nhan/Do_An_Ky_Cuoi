package com.example.projectfinal.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import com.example.projectfinal.R;
import com.example.projectfinal.adapter.UserOrderAdapter;
import com.example.projectfinal.api.OrderAPI;
import com.example.projectfinal.entity.Order;
import com.example.projectfinal.entity.User;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserOrderActivity extends AppCompatActivity {
    private List<Order> mLstOrder = new ArrayList<>();
    private User mUser;
    private UserOrderAdapter mUserOrderAdapter;
    private ListView mLstViewOrder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_order);

        Bundle bundle = getIntent().getExtras();
        if (bundle == null) {
            return;
        }

        mUser = (User) bundle.get("userInfo");
        mLstViewOrder = findViewById(R.id.list_view_order);
        getList();
    }

    private void getList(){
        OrderAPI.orderApi.getOrdersByUserId(mUser.getId()).enqueue(new Callback<List<Order>>() {
            @Override
            public void onResponse(Call<List<Order>> call, Response<List<Order>> response) {
                if(response.isSuccessful()){
                    mLstOrder = response.body();
                    mUserOrderAdapter = new UserOrderAdapter(UserOrderActivity.this, mLstOrder);
                    mLstViewOrder.setAdapter(mUserOrderAdapter);
                }
            }

            @Override
            public void onFailure(Call<List<Order>> call, Throwable t) {
                Toast.makeText(UserOrderActivity.this, "Lỗi khi gọi API", Toast.LENGTH_SHORT).show();
            }
        });
    };
}