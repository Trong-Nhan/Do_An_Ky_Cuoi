package com.example.projectfinal.activity.admin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.projectfinal.R;
import com.example.projectfinal.api.NewsAPI;
import com.example.projectfinal.entity.News;
import com.example.projectfinal.entity.User;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpdateNewsActivity extends AppCompatActivity {
    private News mNews;
    private EditText edtName;
    private EditText edtDescription;
    private EditText edtDetail;
    private EditText edtPicture;
    private EditText edtCreatedDate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_news);

        //khai bao cac thanh phan tren form
        edtName = findViewById(R.id.edit_news_name);
        edtDescription = findViewById(R.id.edit_news_description);
        edtDetail = findViewById(R.id.edit_news_detail);
        edtPicture = findViewById(R.id.edit_news_picture);
        edtCreatedDate = findViewById(R.id.edit_news_createdDate);
        Button btnAdd = findViewById(R.id.btn_add_news);
        Button btnUpdate = findViewById(R.id.btn_update_news);

        //an hien cac truong can hien thi tren form
        btnAdd.setVisibility(View.GONE);
        btnUpdate.setVisibility(View.VISIBLE);

        ///lấy object được truyền từ AdminNewsActivity
        Bundle bundle = getIntent().getExtras();
        if (bundle == null) {
            return;
        }
        mNews = (News) bundle.get("updateNews");

        //load du lieu cua tin tuc len cac thanh phan tren form
        edtName.setText(mNews.getName());
        edtDescription.setText(mNews.getDescription());
        edtDetail.setText(mNews.getDetail());
        edtPicture.setText(mNews.getPicture());
        //chuyen kieu du lieu Date sang kieu String
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        edtCreatedDate.setText(formatter.format(mNews.getCreatedDate()));

        //an nut update
        btnUpdate.setOnClickListener(listenerUpdateNews);
    }


    //ham goi su kien update
    private View.OnClickListener listenerUpdateNews = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
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
            //goi constructor
            News n = new News(mNews.getId(), nName, nDescription, nDetail, nPicture, strDate);
            //bat dau update
            NewsAPI.newsAPI.updateNews(n).enqueue(new Callback<News>() {
                @Override
                public void onResponse(Call<News> call, Response<News> response) {
                    if (response.isSuccessful()) {
                        Toast.makeText(UpdateNewsActivity.this, "Cập nhật thành công", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(UpdateNewsActivity.this, AdminNewsActivity.class);
                        startActivity(intent);
                    }
                }

                @Override
                public void onFailure(Call<News> call, Throwable throwable) {
                    Toast.makeText(UpdateNewsActivity.this, "Lỗi khi gọi API", Toast.LENGTH_SHORT).show();
                }
            });
        }
    };
}