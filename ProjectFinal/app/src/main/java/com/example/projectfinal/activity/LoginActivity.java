package com.example.projectfinal.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.projectfinal.R;
import com.example.projectfinal.activity.admin.AdminActivity;
import com.example.projectfinal.api.UserAPI;
import com.example.projectfinal.entity.User;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    private EditText edtLoginEmail;
    private EditText edtLoginPassword;
    private List<User> mLst = new ArrayList<>();
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edtLoginEmail = findViewById(R.id.formLoginEmail);
        edtLoginPassword = findViewById(R.id.formLoginPassword);
        Button btnLogin = findViewById(R.id.formBtnLogin);
        sharedPreferences = getSharedPreferences("UserInfo", MODE_PRIVATE);

        //Hàm lấy dữ liệu danh sách User từ API
        getListUsers();

        //nếu đã lưu dữ liệu trong SharedPreferences thì lập tức vào MainActivity
        String uEmail = sharedPreferences.getString("UserEmail", null);
        if (uEmail != null) {
            UserAPI.userApi.getUserByEmail(uEmail).enqueue(new Callback<User>() {
                @Override
                public void onResponse(Call<User> call, Response<User> response) {
                    if (response.isSuccessful()) {
                        User u = response.body();
                        if (u.getRole().equals("admin")) {
                            Intent intent = new Intent(LoginActivity.this, AdminActivity.class);
                            startActivity(intent);
                        } else {
                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            startActivity(intent);
                        }
                    }
                }

                @Override
                public void onFailure(Call<User> call, Throwable t) {
                    Toast.makeText(LoginActivity.this, "Lỗi khi gọi API", Toast.LENGTH_SHORT).show();
                }
            });

        }


        //Xử lý sự kiện đăng nhập
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CheckBox chbRemember = findViewById(R.id.chbRemember);
                //khi check vào nút ghi nhớ sẽ ghi nhớ dữ liệu user trên SharedPreferences
                if (chbRemember.isChecked()) {
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("UserEmail", edtLoginEmail.getText().toString());
                    editor.putString("UserPassword", edtLoginPassword.getText().toString());
                    editor.apply();
                    login();
                } else {
                    login();
                }

            }
        });

        //Chuyển sang màn hình đăng ký
        TextView txtRegistration = findViewById(R.id.formRegistator);
        txtRegistration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
    }

    private void login() {
        String loginEmail = edtLoginEmail.getText().toString().trim();
        String loginPassword = edtLoginPassword.getText().toString().trim();

        //trường hợp nếu List User rỗng
        if (mLst == null || mLst.isEmpty()) {
            return;
        }

        //khai báo biến check tài khoản mật khẩu của người dùng
        boolean hasUser = false;
        //khai báo biến check role của người dùng
        boolean isRole = false;
        for (User user : mLst) {
            //nếu trùng tài khoản và mật khẩu với user thì hasUser là true
            if (user.getEmail().equals(loginEmail) || user.getPassword().equals(loginPassword)) {
                hasUser = true;
                //nếu role là admin thì biến isRole là true
                if (user.getRole().equals("admin")) {
                    isRole = true;
                }
                break;
            }
        }
        if (hasUser) {
            if (isRole) { //role admin
                UserAPI.userApi.getUserByEmail(loginEmail).enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        User u = response.body();
                        Intent intent = new Intent(LoginActivity.this, AdminActivity.class);
                        intent.putExtra("adminName", u.getName());
                        startActivity(intent);
                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable t) {
                        Toast.makeText(LoginActivity.this, "Lỗi khi gọi API", Toast.LENGTH_SHORT).show();
                    }
                });
            } else { //role user
                UserAPI.userApi.getUserByEmail(loginEmail).enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        User u = response.body();

                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        intent.putExtra("userName", u.getName());
                        intent.putExtra("userId", u.getId());
                        startActivity(intent);


                        Toast.makeText(LoginActivity.this, "Xin chào: " + u.getName(), Toast.LENGTH_SHORT).show();
                        ;
                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable t) {
                        Toast.makeText(LoginActivity.this, "Lỗi khi gọi API", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        } else {
            Toast.makeText(LoginActivity.this, "Sai email và mật khẩu, xin thử lại", Toast.LENGTH_SHORT).show();
        }
    }


    private void getListUsers() {

        UserAPI.userApi.getListUsers().enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                if (response.isSuccessful()) {
                    mLst = response.body();
                }
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                Toast.makeText(LoginActivity.this, "Lỗi khi gọi API", Toast.LENGTH_SHORT).show();
            }
        });

    }


}