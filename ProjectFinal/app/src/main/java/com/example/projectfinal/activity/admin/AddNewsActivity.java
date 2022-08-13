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
    private ImageView imgView;
    private TextView imageName;
    private EditText edtName;
    private EditText edtDescription;
    private EditText edtDetail;
    private EditText edtCreatedDate;
    private Uri mUri;
    public static final String TAG = MainActivity.class.getName();
    private Button btnSelectImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_news);

        edtName = findViewById(R.id.edit_news_name);
        edtDescription = findViewById(R.id.edit_news_description);
        edtDetail = findViewById(R.id.edit_news_detail);
        imageName = findViewById(R.id.image_name);
        edtCreatedDate = findViewById(R.id.edit_news_createdDate);
        imgView = findViewById(R.id.img_from_gallery);

        Button btnAdd = findViewById(R.id.btn_add_news);
        Button btnUpdate = findViewById(R.id.btn_update_news);
        Button btnReset = findViewById(R.id.btn_cancel_news);

        //an hien cac truong can hien thi
        btnAdd.setVisibility(View.VISIBLE);
        btnUpdate.setVisibility(View.GONE);

        //an nut reset
        btnReset.setOnClickListener(listenerResetNews);

        //an nut them moi
        btnAdd.setOnClickListener(listenerAddNews);

        btnSelectImg = findViewById(R.id.btn_select);
        //Nhấn nút chọn ảnh trong gallery
        btnSelectImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickRepestPermisson();
            }
        });

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
                            imageName.setText(getNameImg());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
    );

    //ham goi su kien reset cac truong thong tin
    private View.OnClickListener listenerResetNews = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            edtName.setText("");
            edtDescription.setText("");
            edtDetail.setText("");
            edtCreatedDate.setText("");
            imageName.setText("");
            imgView.setImageBitmap(null);
        }
    };

    //ham goi su kien them moi
    private View.OnClickListener listenerAddNews = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
            try {
                //kiem tra ca truong thong tin da duoc nhap du hay chua
                if (
                        "".equals(imageName.getText().toString()) ||
                        "".equals(edtName.getText().toString()) ||
                        "".equals(edtDescription.getText().toString()) ||
                        "".equals(edtDetail.getText().toString()) ||
                        "".equals(edtCreatedDate.getText().toString())
                ) {
                    Toast.makeText(AddNewsActivity.this, "Chưa nhập đủ các trường", Toast.LENGTH_SHORT).show();
                    return;
                }
                String nName = edtName.getText().toString();
                String nDescription = edtDescription.getText().toString();
                String nDetail = edtDetail.getText().toString();
                String nPicture = RealPathUtil.getRealPath(AddNewsActivity.this, mUri);

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


    @Override
    public void onBackPressed() {
        Intent intent = new Intent(AddNewsActivity.this, AdminNewsActivity.class);
        startActivity(intent);
        super.onBackPressed();
    }

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