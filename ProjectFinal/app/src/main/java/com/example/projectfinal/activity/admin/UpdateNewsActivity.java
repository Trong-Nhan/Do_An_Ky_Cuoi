package com.example.projectfinal.activity.admin;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.projectfinal.R;
import com.example.projectfinal.api.NewsAPI;
import com.example.projectfinal.entity.News;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        edtCreatedDate.setText(formatter.format(mNews.getCreatedDate()));

        //an nut update
        btnUpdate.setOnClickListener(listenerUpdateNews);
    }


    //ham goi su kien update
    private View.OnClickListener listenerUpdateNews = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            try {
                String nName = edtName.getText().toString();
                String nDescription = edtDescription.getText().toString();
                String nDetail = edtDetail.getText().toString();
                String nPicture = edtPicture.getText().toString();
                String nCreatedDate = edtCreatedDate.getText().toString();
                //chuyen kieu du lieu String sang kieu Date
                Date strDate = formatter.parse(nCreatedDate);
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
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
    };

    public void openDatePicker(View view) {
        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        DatePickerDialog.OnDateSetListener listener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                EditText edtCreatedDate = findViewById(R.id.edit_news_createdDate);
                SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
                Calendar c = Calendar.getInstance();
                c.set(year, month, day);
                edtCreatedDate.setText(fmt.format(c.getTime()));
            }
        };

        DatePickerDialog dialog = new DatePickerDialog(this, listener, c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH));
        dialog.show();
    }
}