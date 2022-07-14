package com.example.projectfinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.projectfinal.entity.User;

import java.util.ArrayList;
import java.util.List;

public class LoginActivity extends AppCompatActivity {

    private List<User> mLst = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        createUser();

        Button btnLogin = findViewById(R.id.formBtnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText txtUserEmail = findViewById(R.id.formLoginEmail);
                EditText txtUserPassword = findViewById(R.id.formLoginPassword);

                String userEmail = txtUserEmail.getText().toString();
                String userPassword = txtUserPassword.getText().toString();
                for (int i = 0; i < mLst.size(); i++) {
                    User u = mLst.get(i);
                    if(u.getEmail().equals(userEmail) && u.getPassword().equals(userPassword)){
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        intent.putExtra("userName", u.getName());
                        startActivity(intent);
                    }
                }
            }
        });
    }

    private void createUser(){
        mLst.add(new User("admin@gmail.com", "Admin", "admin"));
        mLst.add(new User("tson.nguyen3112@gmail.com", "Nguyễn Tuấn Sơn", "123456"));
    }
}