package com.example.projectfinal.activity.admin;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.projectfinal.R;
import com.example.projectfinal.activity.LoginActivity;
import com.example.projectfinal.activity.MainActivity;
import com.example.projectfinal.entity.User;
import com.google.gson.Gson;

public class AdminActivity extends AppCompatActivity {

    private String mAdminName;
    private TextView mTxtAdminName;
    private SharedPreferences sharedPreferences;
    private Bundle mBundle;
    private User mUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        sharedPreferences = getSharedPreferences("UserInfo", MODE_PRIVATE);

        mTxtAdminName = findViewById(R.id.txtAdminName);

        mBundle = getIntent().getExtras();
        //gán tên Admin lên trang Admin
        if (sharedPreferences.contains("uInfo")) {
            Gson gson = new Gson();
            String uJson = sharedPreferences.getString("uInfo", null);
            mUser = gson.fromJson(uJson, User.class);
            mTxtAdminName.setText(mUser.getName());
        }else{
            mUser = (User) mBundle.get("userInfo");
            mTxtAdminName.setText(mUser.getName());
        }



        Button btnAccount = findViewById(R.id.btnAdminAccount);
        Button btnLogout = findViewById(R.id.btnAdminLogout);
        Button btnToMain = findViewById(R.id.btnAdminToMainScreen);
        Button btnAdminCategory = findViewById(R.id.btnAdminCategory);
        Button btnAdminPublisher = findViewById(R.id.btnAdminPublisher);
        Button btnAdminBook = findViewById(R.id.btnAdminBook);
        Button btnAdminNews = findViewById(R.id.btnAdminNews);
        Button btnAdminOrder = findViewById(R.id.btnAdminOrder);

        btnLogout.setOnClickListener(listenerLogout);
        btnToMain.setOnClickListener(listenerToMain);
        btnAccount.setOnClickListener(listenerToAccount);
        btnAdminCategory.setOnClickListener(listenerToCategory);
        btnAdminPublisher.setOnClickListener(listenerToPublisher);
        btnAdminBook.setOnClickListener(listenerToBook);
        btnAdminNews.setOnClickListener(listenerToNews);
        btnAdminOrder.setOnClickListener(listenerToOrder);
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
            Bundle bundle = new Bundle();
            bundle.putSerializable("adInfo", mUser);
            intent.putExtras(bundle);
            startActivity(intent);
        }
    };

    //Hành động vào trang quản lý tài khoản
    private View.OnClickListener listenerToAccount = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(AdminActivity.this, AdminAccountActivity.class);
//            Bundle bundle = new Bundle();
//            bundle.putSerializable("adInfo", mUser);
//            intent.putExtras(bundle);
            startActivity(intent);
        }
    };

    //Hành động vào trang quản lý sachs
    private View.OnClickListener listenerToBook = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(AdminActivity.this, AdminBookActivity.class);
//            Bundle bundle = new Bundle();
//            bundle.putSerializable("adInfo", mUser);
//            intent.putExtras(bundle);
            startActivity(intent);
        }
    };

    //Hành động vào trang quản lý NXB
    private View.OnClickListener listenerToPublisher = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(AdminActivity.this, AdminPublisherActivity.class);
//            Bundle bundle = new Bundle();
//            bundle.putSerializable("adInfo", mUser);
//            intent.putExtras(bundle);
            startActivity(intent);
        }
    };

    //Hành động vào trang quản lý danh muc
    private View.OnClickListener listenerToCategory = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(AdminActivity.this, AdminCategoryActivity.class);
//            Bundle bundle = new Bundle();
//            bundle.putSerializable("adInfo", mUser);
//            intent.putExtras(bundle);
            startActivity(intent);
        }
    };

    //Hành động vào trang quản lý tin tuc
    private View.OnClickListener listenerToNews = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(AdminActivity.this, AdminNewsActivity.class);
//            Bundle bundle = new Bundle();
//            bundle.putSerializable("adInfo", mUser);
//            intent.putExtras(bundle);
            startActivity(intent);
        }
    };

    //Hành động vào trang quản lý đơn hàng
    private View.OnClickListener listenerToOrder = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(AdminActivity.this, AdminOrderActivity.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable("userInfo", mUser);
            intent.putExtras(bundle);
            startActivity(intent);
        }
    };

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}