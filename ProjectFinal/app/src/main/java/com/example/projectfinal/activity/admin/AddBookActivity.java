package com.example.projectfinal.activity.admin;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.example.projectfinal.R;
import com.example.projectfinal.RealPathUtil;
import com.example.projectfinal.activity.MainActivity;
import com.example.projectfinal.api.BookAPI;
import com.example.projectfinal.api.CategoryAPI;
import com.example.projectfinal.api.PublisherAPI;
import com.example.projectfinal.entity.Book;
import com.example.projectfinal.entity.Category;
import com.example.projectfinal.entity.Publisher;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddBookActivity extends AppCompatActivity {

    private List<Category> mLstCategory = new ArrayList<>();
    private List<Publisher> mLstPublisher = new ArrayList<>();
    public static final String TAG = MainActivity.class.getName();
    private EditText bookName, author, price, salePrice, publisherYear, page, number, description;
    private ImageView imgView;
    private Button btnSelectImg, btnAdd, btnUpdate;
    private Spinner spinCategory, spinPublisher;
    private static final int MY_REQUEST_CODE = 10;
    private Uri mUri;
    private TextView imageName;

    //Hiện ảnh vừa chọn
    ActivityResultLauncher<Intent> mActivityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    Log.e(TAG, "onActivityResult");
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        Intent data = result.getData();
                        if (data == null) {
                            return;
                        }
                        Uri uri = data.getData();
                        mUri = uri;
                        try {
                            Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                            imgView.setImageBitmap(bitmap);
//                            imageName.setText(getNameImg());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
    );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_book);

        initUi();

        //Nhấn nút chọn ảnh trong gallery
        btnSelectImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickRepestPermisson();
            }
        });
        btnAdd.setOnClickListener(listenerAddBook);
    }

    private void initUi() {
        bookName = findViewById(R.id.edit_book_name);
        author = findViewById(R.id.edit_author_name);
        price = findViewById(R.id.edit_book_price);
        salePrice = findViewById(R.id.edit_book_sale);
        publisherYear = findViewById(R.id.edit_publisher_year);
        page = findViewById(R.id.edit_page);
        number = findViewById(R.id.edit_number);
        imgView = findViewById(R.id.img_from_gallery);
        btnSelectImg = findViewById(R.id.btn_select_img);
        btnAdd = findViewById(R.id.btn_add_book);
        btnUpdate = findViewById(R.id.btn_update_book);
        description = findViewById(R.id.edit_description);
        btnUpdate.setVisibility(View.GONE);
        spinCategory = findViewById(R.id.spin_category);
        spinPublisher = findViewById(R.id.spin_publisher);
        getListCategory();
        getListPublisher();
    }

    private void getListCategory() {
        CategoryAPI.categoryAPI.getCategory().enqueue(new Callback<List<Category>>() {
            @Override
            public void onResponse(Call<List<Category>> call, Response<List<Category>> response) {
                if (response.isSuccessful()) {
                    mLstCategory = response.body();
                    ArrayAdapter<Category> adapter = new ArrayAdapter<>(AddBookActivity.this, android.R.layout.simple_list_item_1, mLstCategory);
                    spinCategory.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<List<Category>> call, Throwable t) {
                Toast.makeText(AddBookActivity.this, "Lỗi khi gọi API", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getListPublisher() {
        PublisherAPI.publisherAPI.getPublisher().enqueue(new Callback<List<Publisher>>() {
            @Override
            public void onResponse(Call<List<Publisher>> call, Response<List<Publisher>> response) {
                if (response.isSuccessful()) {
                    mLstPublisher = response.body();
                    ArrayAdapter<Publisher> adapter = new ArrayAdapter<>(AddBookActivity.this, android.R.layout.simple_list_item_1, mLstPublisher);
                    spinPublisher.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<List<Publisher>> call, Throwable t) {
                Toast.makeText(AddBookActivity.this, "Lỗi khi gọi API", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(AddBookActivity.this, AdminBookActivity.class);
        startActivity(intent);
        super.onBackPressed();
    }

    //check phien ban android
    private void onClickRepestPermisson() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            openGallery();
            return;
        }
        if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
            openGallery();
        } else {
            String[] permission = {Manifest.permission.READ_EXTERNAL_STORAGE};
            requestPermissions(permission, MY_REQUEST_CODE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == MY_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                openGallery();
            }
        }
    }

    //Mo thu muc anh
    private void openGallery() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        mActivityResultLauncher.launch(Intent.createChooser(intent, "Select Image"));
    }

    private View.OnClickListener listenerAddBook = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            float bookSale = 0;
            String edtName = bookName.getText().toString();
//            int idCategory = spinCategory.getSelectedItemPosition();
            int idCategory = 1;
            float bookPrice = Float.parseFloat(price.getText().toString());
            if (salePrice.getText().toString() != null) {
                bookSale = Float.parseFloat(salePrice.getText().toString());
            }
            String bookAuthor = author.getText().toString();
//            int idPublisher = spinPublisher.getSelectedItemPosition();
            int idPublisher = 1;
            int bookYear = Integer.parseInt(publisherYear.getText().toString());
            String imgName = RealPathUtil.getRealPath(AddBookActivity.this, mUri);
            int bookNumber = Integer.parseInt(number.getText().toString());
            String bookDesc = description.getText().toString();
            int bookPage = Integer.parseInt(page.getText().toString());
            float rating = 2;
            boolean status = true;
            Book b = new Book(edtName, idCategory, bookPrice, bookSale, bookAuthor, idPublisher, bookYear, imgName, bookNumber, bookDesc, bookPage, rating, status);

            BookAPI.bookAPI.addBook(b).enqueue(new Callback<Book>() {
                @Override
                public void onResponse(Call<Book> call, Response<Book> response) {
                    if (response.isSuccessful()) {
                        Toast.makeText(AddBookActivity.this, "Thêm mới thành công", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(AddBookActivity.this, AdminBookActivity.class);
                        startActivity(intent);
                    }
                }

                @Override
                public void onFailure(Call<Book> call, Throwable throwable) {
                    Toast.makeText(AddBookActivity.this, "Lỗi khi gọi API", Toast.LENGTH_SHORT).show();
                }
            });
        }
    };
}