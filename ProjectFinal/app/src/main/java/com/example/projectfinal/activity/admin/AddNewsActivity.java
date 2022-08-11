package com.example.projectfinal.activity.admin;

import android.Manifest;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.projectfinal.R;
import com.example.projectfinal.RealPathUtil;
import com.example.projectfinal.activity.MainActivity;
import com.example.projectfinal.api.CategoryAPI;
import com.example.projectfinal.api.NewsAPI;
import com.example.projectfinal.entity.Category;
import com.example.projectfinal.entity.News;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddNewsActivity extends AppCompatActivity {
    private static final int MY_REQUEST_CODE = 10;
    ImageView imgView;
    private Uri mUri;
    public static final String TAG = MainActivity.class.getName();
    Button btnSelectImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_news);

        Button btnAdd = findViewById(R.id.btn_add_news);
        Button btnUpdate = findViewById(R.id.btn_update_news);
        btnAdd.setVisibility(View.VISIBLE);
        btnUpdate.setVisibility(View.GONE);
        btnAdd.setOnClickListener(listenerAddNews);
        btnSelectImg = findViewById(R.id.btn_select);
        //Nhấn nút chọn ảnh trong gallery
        btnSelectImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickRepestPermisson();
            }
        });

        imgView = findViewById(R.id.img_from_gallery);
    }

    private View.OnClickListener listenerAddNews = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            EditText edtName = findViewById(R.id.edit_news_name);
            EditText edtDescription = findViewById(R.id.edit_news_description);
            EditText edtDetail = findViewById(R.id.edit_news_detail);
            String edtPicture = RealPathUtil.getRealPath(AddNewsActivity.this, mUri);
            EditText edtCreatedDate = findViewById(R.id.edit_news_createdDate);

            SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
            try {
                String nName = edtName.getText().toString();
                String nDescription = edtDescription.getText().toString();
                String nDetail = edtDetail.getText().toString();
                String nPicture = edtPicture;
                //chuyen kieu du lieu String sang kieu Date
                Date strDate = fmt.parse(edtCreatedDate.getText().toString());
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

    //Check phiên bản Android
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

    //Mở thư mục ảnh
    private void openGallery() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        mActivityResultLauncher.launch(Intent.createChooser(intent, "Chọn Ảnh"));
    }

    //Lấy tên ảnh
    private String getNameImg() {
        String realPathUtil = RealPathUtil.getRealPath(this, mUri);
        File file = new File(realPathUtil);
        return file.getName();
    }

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
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
    );

}