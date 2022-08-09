package com.example.projectfinal.activity.admin;

import androidx.appcompat.app.AppCompatActivity;

import com.example.projectfinal.R;
import com.example.projectfinal.api.PublisherAPI;
import com.example.projectfinal.entity.Category;
import com.example.projectfinal.entity.Publisher;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpdatePublisherActivity extends AppCompatActivity {

    private Publisher publisher = new Publisher();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_publisher);

        Button btnUpdate = findViewById(R.id.btn_update_publisher);
        btnUpdate.setOnClickListener(listenerUpdatePublisher);
        //Ẩn nút
        Button btnAdd = findViewById(R.id.btn_add_publisher);
        btnAdd.setVisibility(View.GONE);
        //lấy object được truyền từ AdminPublisherActivity
        Bundle bundle = getIntent().getExtras();
        publisher = (Publisher) bundle.get("idPublisher");
        //load du lieu
        EditText editText = findViewById(R.id.edit_publisher_name);
        editText.setText(publisher.getName());
    }

    private View.OnClickListener listenerUpdatePublisher = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            EditText edtName = findViewById(R.id.edit_publisher_name);
            String cName = edtName.getText().toString();

            Publisher p = new Publisher(publisher.getId(), cName);
            PublisherAPI.publisherAPI.updatePublisher(p).enqueue(new Callback<Publisher>() {
                @Override
                public void onResponse(Call<Publisher> call, Response<Publisher> response) {
                    if (response.isSuccessful()) {
                        Toast.makeText(UpdatePublisherActivity.this, "Cập nhật thành công", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(UpdatePublisherActivity.this, AdminPublisherActivity.class);
                        startActivity(intent);
                    }
                }

                @Override
                public void onFailure(Call<Publisher> call, Throwable throwable) {
                    Toast.makeText(UpdatePublisherActivity.this, "Lỗi khi gọi API", Toast.LENGTH_SHORT).show();
                }
            });
        }
    };

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(UpdatePublisherActivity.this, AdminPublisherActivity.class);
        startActivity(intent);
        super.onBackPressed();
    }
}