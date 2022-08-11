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
import com.example.projectfinal.adapter.admin.AdminBookAdapter;
import com.example.projectfinal.api.BookAPI;
import com.example.projectfinal.entity.Book;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdminBookActivity extends AppCompatActivity {
    private List<Book> mLstBook;
    private AdminBookAdapter mAdminBookAdapter;
    ListView listviewBook;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_book);

        mLstBook = new ArrayList<>();
        listviewBook = findViewById(R.id.listviewBook);
        getList();

        // Cài đặt context menu cho ListView
        registerForContextMenu(listviewBook);
        //chuyen sang form them moi
        TextView txtAdd = findViewById(R.id.add_book);
        txtAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminBookActivity.this, AddBookActivity.class);
                startActivity(intent);
            }
        });
    }

    //Lay du lieu Book qua API
    private void getList() {
        BookAPI.bookAPI.getBook().enqueue(new Callback<List<Book>>() {
            @Override
            public void onResponse(Call<List<Book>> call, Response<List<Book>> response) {
                mLstBook = response.body();
                mAdminBookAdapter = new AdminBookAdapter(AdminBookActivity.this, mLstBook);
                listviewBook.setAdapter(mAdminBookAdapter);

            }

            @Override
            public void onFailure(Call<List<Book>> call, Throwable t) {
                Toast.makeText(AdminBookActivity.this, "Lỗi khi gọi API", Toast.LENGTH_SHORT).show();
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
        int idBook = mLstBook.get(info.position).getId();

        switch (id) {
            case R.id.menuUpdate:
                Intent intent = new Intent(AdminBookActivity.this, UpdateCategoryActivity.class);
                intent.putExtra("idBook", idBook);
                startActivity(intent);
                break;
            case R.id.menuDelete:
                BookAPI.bookAPI.deleteBook(idBook).enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        if (response.isSuccessful()) {
                            Toast.makeText(AdminBookActivity.this, "Xóa thành công!", Toast.LENGTH_SHORT).show();
                            //getList();
                        }
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        Toast.makeText(AdminBookActivity.this, "Lỗi khi gọi API", Toast.LENGTH_SHORT).show();
                    }
                });
        }
        return super.onContextItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(AdminBookActivity.this, AdminActivity.class);
        startActivity(intent);
        super.onBackPressed();
    }

}