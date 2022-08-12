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
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
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
    private RadioButton raBtnHide,raBtnDisplay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_book);

        initUi();

        //Nh?n nút ch?n ?nh trong gallery
        btnSelectImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickRepestPermisson();
            }
        });
        btnAdd.setOnClickListener(listenerAddBook);
    }

    //khai báo các thành ph?n có trong View
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
        imageName = findViewById(R.id.image_name);
        raBtnHide = findViewById(R.id.ra_btn_hide);
        raBtnDisplay = findViewById(R.id.ra_btn_display);
        getListCategory();
        getListPublisher();
    }

    //L?y d? li?u Category vào Spinner
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
                Toast.makeText(AddBookActivity.this, "L?i khi g?i API", Toast.LENGTH_SHORT).show();
            }
        });
    }

    //L?y d? li?u Publisher vào Spinner
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
                Toast.makeText(AddBookActivity.this, "L?i khi g?i API", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(AddBookActivity.this, AdminBookActivity.class);
        startActivity(intent);
        super.onBackPressed();
    }

    //Check phiên b?n Android
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

    //M? th? m?c ?nh
    private void openGallery() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        mActivityResultLauncher.launch(Intent.createChooser(intent, "Ch?n ?nh"));
    }

    //L?y tên ?nh
    private String getNameImg() {
        String realPathUtil = RealPathUtil.getRealPath(this, mUri);
        File file = new File(realPathUtil);
        return file.getName();
    }

    //Hi?n ?nh v?a ch?n
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
                            imageName.setText(getNameImg());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
    );

    //Hành ??ng thêm ?nh
    private View.OnClickListener listenerAddBook = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            float bookSale, bookPrice;
            String edtName, bookAuthor, imgName, bookDesc;
            int bookYear, bookNumber, bookPage;
            boolean status;
            if (bookName.getText().toString().equals("")) {
                Toast.makeText(AddBookActivity.this, "Chưa nhập tên sách", Toast.LENGTH_SHORT).show();
                bookName.requestFocus();
                return;
            } else {
                edtName = bookName.getText().toString();
            }

            Category cat = (Category) spinCategory.getSelectedItem();

            if (price.getText().toString().equals("")) {
                Toast.makeText(AddBookActivity.this, "Chưa nhập giá sách", Toast.LENGTH_SHORT).show();
                price.requestFocus();
                return;
            } else {
                bookPrice = Float.parseFloat(price.getText().toString());
            }

            if (salePrice.getText().toString().equals("")) {
                bookSale = 0;
            } else {
                bookSale = Float.parseFloat(salePrice.getText().toString());
            }

            if (author.getText().toString().equals("")) {
                Toast.makeText(AddBookActivity.this, "Chưa nhâp tên tác giả", Toast.LENGTH_SHORT).show();
                author.requestFocus();
                return;
            } else {
                bookAuthor = author.getText().toString();
            }

            Publisher pub = (Publisher) spinPublisher.getSelectedItem();

            if (publisherYear.getText().toString().equals("")) {
                Toast.makeText(AddBookActivity.this, "Chưa nhập năm xuất bản", Toast.LENGTH_SHORT).show();
                publisherYear.requestFocus();
                return;
            } else {
                bookYear = Integer.parseInt(publisherYear.getText().toString());
            }

            if (imageName.getText().toString().equals("")) {
                Toast.makeText(AddBookActivity.this, "Chưa có ảnh sách", Toast.LENGTH_SHORT).show();
                return;
            } else {
                imgName = RealPathUtil.getRealPath(AddBookActivity.this, mUri);
            }

            if (number.getText().toString().equals("")) {
                Toast.makeText(AddBookActivity.this, "Chưa nhập số lượng", Toast.LENGTH_SHORT).show();
                number.requestFocus();
                return;
            } else {
                bookNumber = Integer.parseInt(number.getText().toString());
            }

            if (description.getText().toString().equals("")) {
                Toast.makeText(AddBookActivity.this, "Chưa nhập mô tả sách", Toast.LENGTH_SHORT).show();
                description.requestFocus();
                return;
            } else {
                bookDesc = description.getText().toString();
            }

            if (page.getText().toString().equals("")) {
                Toast.makeText(AddBookActivity.this, "Chưa nhập số trang sách", Toast.LENGTH_SHORT).show();
                page.requestFocus();
                return;
            } else {
                bookPage = Integer.parseInt(page.getText().toString());
            }

            float rating = 0;

            if (raBtnHide.isChecked()) {
                status = false;
            } else if (raBtnDisplay.isChecked()) {
                status = true;
            } else {
                Toast.makeText(AddBookActivity.this, "Chưa chọn trạng thái", Toast.LENGTH_SHORT).show();
                return;
            }

            Book b = new Book(edtName, cat.getId(), bookPrice, bookSale, bookAuthor, pub.getId(), bookYear, imgName, bookNumber, bookDesc, bookPage, rating, status);

            BookAPI.bookAPI.addBook(b).enqueue(new Callback<Book>() {
                @Override
                public void onResponse(Call<Book> call, Response<Book> response) {
                    if (response.isSuccessful()) {
                        Toast.makeText(AddBookActivity.this, "Thêm m?i thành công", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(AddBookActivity.this, AdminBookActivity.class);
                        startActivity(intent);
                    }
                }

                @Override
                public void onFailure(Call<Book> call, Throwable throwable) {
                    Toast.makeText(AddBookActivity.this, "L?i khi g?i API", Toast.LENGTH_SHORT).show();
                }
            });
        }
    };
}