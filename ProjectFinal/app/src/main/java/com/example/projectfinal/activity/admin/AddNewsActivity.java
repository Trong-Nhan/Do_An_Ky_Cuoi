package com.example.projectfinal.activity.admin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.projectfinal.R;
import com.example.projectfinal.api.CategoryAPI;
import com.example.projectfinal.api.NewsAPI;
import com.example.projectfinal.entity.Category;
import com.example.projectfinal.entity.News;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddNewsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_news);

        Button btnAdd = findViewById(R.id.btn_add_news);
        Button btnUpdate = findViewById(R.id.btn_update_news);
        btnAdd.setVisibility(View.VISIBLE);
        btnUpdate.setVisibility(View.GONE);
        btnAdd.setOnClickListener(listenerAddNews);
    }

    private View.OnClickListener listenerAddNews = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            EditText edtName = findViewById(R.id.edit_news_name);
            EditText edtDescription = findViewById(R.id.edit_news_description);
            EditText edtDetail = findViewById(R.id.edit_news_detail);
            EditText edtPicture = findViewById(R.id.edit_news_picture);
            EditText edtCreatedDate = findViewById(R.id.edit_news_createdDate);

            String nName = edtName.getText().toString();
            String nDescription = edtDescription.getText().toString();
            String nDetail = edtDetail.getText().toString();
            String nPicture = edtPicture.getText().toString();
            String nCreatedDate = edtCreatedDate.getText().toString();
            //chuyen kieu du lieu String sang kieu Date
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            Date strDate = null;
            try {
                strDate = formatter.parse(nCreatedDate);
            } catch (ParseException e) {
                e.printStackTrace();
            }

            News n = new News(nName, nDescription, nDetail, nPicture, strDate);
            NewsAPI.newsAPI.addNews(n).enqueue(new Callback<News>() {
                @Override
                public void onResponse(Call<News> call, Response<News> response) {
                    if (response.isSuccessful()) {
                        Toast.makeText(AddNewsActivity.this, "Thêm mới thành công", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(AddNewsActivity.this, AdminNewsActivity.class);
                        startActivity(intent);
                    }
                }

                @Override
                public void onFailure(Call<News> call, Throwable throwable) {
                    Toast.makeText(AddNewsActivity.this, "Lỗi khi gọi API", Toast.LENGTH_SHORT).show();
                }
            });
        }
    };
}