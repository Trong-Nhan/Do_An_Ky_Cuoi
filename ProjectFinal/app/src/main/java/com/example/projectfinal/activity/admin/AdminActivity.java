package com.example.projectfinal.activity.admin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.projectfinal.R;
import com.example.projectfinal.activity.LoginActivity;
import com.example.projectfinal.activity.MainActivity;
import com.example.projectfinal.api.UserAPI;
import com.example.projectfinal.entity.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdminActivity extends AppCompatActivity {

    private String mAdminName;
    private TextView mTxtAdminName;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        sharedPreferences = getSharedPreferences("UserInfo", MODE_PRIVATE);

        mTxtAdminName = findViewById(R.id.txtAdminName);

        //gán tên Admin lên trang Admin
        if (sharedPreferences.getString("UserEmail", null) != null) {
            String uEmail = sharedPreferences.getString("UserEmail", null);
            UserAPI.userApi.getUserByEmail(uEmail).enqueue(new Callback<User>() {
                @Override
                public void onResponse(Call<User> call, Response<User> response) {
                    if (response.isSuccessful()) {
                        User u = response.body();
                        mTxtAdminName.setText(u.getName());
                    }
                }

                @Override
                public void onFailure(Call<User> call, Throwable t) {
                    Toast.makeText(AdminActivity.this, "Lỗi khi gọi API", Toast.LENGTH_SHORT).show();
                }
            });
        } else if (!getIntent().getExtras().get("adminPageName").toString().isEmpty()) {
            mAdminName = getIntent().getExtras().get("adminPageName").toString();
            mTxtAdminName.setText(mAdminName);
        } else {
            mAdminName = getIntent().getExtras().get("adminName").toString();
            mTxtAdminName.setText(mAdminName);
        }

        Button btnLogout = findViewById(R.id.btnAdminLogout);
        Button btnToMain = findViewById(R.id.btnAdminToMainScreen);

        btnLogout.setOnClickListener(listenerLogout);
        btnToMain.setOnClickListener(listenerToMain);
    }

    //Hành động nút đăng xuất
    private View.OnClickListener listenerLogout = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.clear();
            editor.commit();
            Toast.makeText(AdminActivity.this, "Đăng xuất thành công", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(AdminActivity.this, LoginActivity.class);
            startActivity(intent);
        }
    };

    //Hành động nút về trang chính
    private View.OnClickListener listenerToMain = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            Intent intent = new Intent(AdminActivity.this, MainActivity.class);
            intent.putExtra("adminName", mTxtAdminName.getText().toString());
            startActivity(intent);
        }
    };
}