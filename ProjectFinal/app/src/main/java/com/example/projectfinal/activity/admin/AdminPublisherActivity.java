package com.example.projectfinal.activity.admin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.projectfinal.R;
import com.example.projectfinal.adapter.admin.AdminPublisherAdapter;
import com.example.projectfinal.api.PublisherAPI;
import com.example.projectfinal.entity.Publisher;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdminPublisherActivity extends AppCompatActivity {
    private List<Publisher> mLstPublisher = new ArrayList<>();
    private AdminPublisherAdapter mAdminPublisherAdapter;
    ListView listviewPublisher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_publisher);

        getList();
        listviewPublisher = findViewById(R.id.listviewPublisher);
        // Cài đặt context menu cho ListView
        registerForContextMenu(listviewPublisher);
        //chuyen sang form them moi
        TextView txtAdd = findViewById(R.id.add_publisher);
        txtAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminPublisherActivity.this, AddPublisherActivity.class);
                startActivity(intent);
            }
        });
    }

    //Lay du lieu Category qua API
    private void getList() {
        PublisherAPI.publisherAPI.getPublisher().enqueue(new Callback<List<Publisher>>() {
            @Override
            public void onResponse(Call<List<Publisher>> call, Response<List<Publisher>> response) {
                if (response.isSuccessful()) {
                    mLstPublisher = response.body();
                    mAdminPublisherAdapter = new AdminPublisherAdapter(AdminPublisherActivity.this, mLstPublisher);
                    listviewPublisher.setAdapter(mAdminPublisherAdapter);
                }
            }

            @Override
            public void onFailure(Call<List<Publisher>> call, Throwable t) {
                Toast.makeText(AdminPublisherActivity.this, "Lỗi khi gọi API", Toast.LENGTH_SHORT).show();
            }
        });
    }

    //Tạo view sub menu
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        getMenuInflater().inflate(R.menu.sub_menu, menu);
        menu.setHeaderTitle("Lựa chọn: ");
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    //
    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        int idPublisher = mLstPublisher.get(info.position).getId();

        switch (id) {
            case R.id.menuUpdate:
                Intent intent = new Intent(AdminPublisherActivity.this, UpdatePublisherActivity.class);
                intent.putExtra("idPublisher", idPublisher);
                startActivity(intent);
                break;
            case R.id.menuDelete:
                PublisherAPI.publisherAPI.deletePublisher(idPublisher).enqueue(new Callback<Publisher>() {
                    @Override
                    public void onResponse(Call<Publisher> call, Response<Publisher> response) {
                        if (response.isSuccessful()) {
                            Toast.makeText(AdminPublisherActivity.this, "Xóa thành công!", Toast.LENGTH_SHORT).show();
                            onResume();
                        }
                    }

                    @Override
                    public void onFailure(Call<Publisher> call, Throwable t) {
                        Toast.makeText(AdminPublisherActivity.this, "Lỗi khi gọi API", Toast.LENGTH_SHORT).show();
                    }
                });
        }
        return super.onContextItemSelected(item);
    }

    @Override
    protected void onResume() {
        getList();
        super.onResume();
    }
}