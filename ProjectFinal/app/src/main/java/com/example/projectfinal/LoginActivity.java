package com.example.projectfinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.projectfinal.api.UserAPI;
import com.example.projectfinal.entity.User;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    private EditText edtLoginEmail;
    private EditText edtLoginPassword;
    private List<User> mLst = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edtLoginEmail = findViewById(R.id.formLoginEmail);
        edtLoginPassword = findViewById(R.id.formLoginPassword);
        Button btnLogin = findViewById(R.id.formBtnLogin);

        //Hàm lấy dữ liệu danh sách User từ API
        getListUsers();

        //Xử lý sự kiện đăng nhập
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String loginEmail = edtLoginEmail.getText().toString().trim();
                String loginPassword = edtLoginPassword.getText().toString().trim();

                getListUsers();
                //trường hợp nếu List User rỗng

                if(mLst == null || mLst.isEmpty()){
                    return;
                }

                boolean hasUser = false;
                for (User user: mLst) {
                    if(user.getEmail().equals(loginEmail) || user.getPassword().equals(loginPassword)){
                        hasUser = true;
                        break;
                    }
                }
                if(hasUser){
                    UserAPI.userApi.getUserByEmail(loginEmail).enqueue(new Callback<User>() {
                        @Override
                        public void onResponse(Call<User> call, Response<User> response) {
                            User u = response.body();

                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            intent.putExtra("userName", u.getName());
                            startActivity(intent);


                            Toast.makeText(LoginActivity.this, "Xin chào: "+ u.getName(), Toast.LENGTH_SHORT).show();;
                        }

                        @Override
                        public void onFailure(Call<User> call, Throwable t) {
                            Toast.makeText(LoginActivity.this, "Lỗi khi gọi API", Toast.LENGTH_SHORT).show();
                        }
                    });
                }else{
                    Toast.makeText(LoginActivity.this, "Sai email và mật khẩu, xin thử lại", Toast.LENGTH_SHORT).show();
                }
               // login();

            }
        });
    }

    private void login(){
        String loginEmail = edtLoginEmail.getText().toString().trim();
        String loginPassword = edtLoginPassword.getText().toString().trim();

        //trường hợp nếu List User rỗng
        if(mLst == null || mLst.isEmpty()){
            return;
        }

        boolean hasUser = false;
        for (User user: mLst) {
            if(user.getEmail().equals(loginEmail) || user.getPassword().equals(loginPassword)){
                hasUser = true;
                break;
            }
        }

        if(hasUser){
            UserAPI.userApi.getUserByEmail(loginEmail).enqueue(new Callback<User>() {
                @Override
                public void onResponse(Call<User> call, Response<User> response) {
                    User u = response.body();
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    intent.putExtra("userName", u.getName());
                    startActivity(intent);
                    Toast.makeText(LoginActivity.this, "Xin chào: "+ u.getName(), Toast.LENGTH_SHORT).show();;
                }

                @Override
                public void onFailure(Call<User> call, Throwable t) {
                    Toast.makeText(LoginActivity.this, "Lỗi khi gọi API", Toast.LENGTH_SHORT).show();
                }
            });
        }else{
            Toast.makeText(this, "Sai email và mật khẩu, xin thử lại", Toast.LENGTH_SHORT).show();
        }
    }


    private void getListUsers() {

        UserAPI.userApi.getListUsers().enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                if(response.isSuccessful()){
                    mLst = response.body();
                }
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                Toast.makeText(LoginActivity.this, "Lỗi khi gọi API", Toast.LENGTH_SHORT).show();
            }
        });




/*
        UserAPI.userApi.getUserByEmail("tuanng16apu@gmail.com").enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if(response.isSuccessful()){
                    User u = response.body();
                    Gson gson = new Gson();
                    String strJson = gson.toJson(u);
                    Log.e("Obj", strJson);
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(LoginActivity.this, "Lỗi khi gọi API", Toast.LENGTH_SHORT).show();
            }
        });

*/
    }



}