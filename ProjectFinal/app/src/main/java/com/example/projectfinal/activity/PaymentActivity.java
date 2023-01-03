package com.example.projectfinal.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.projectfinal.R;
import com.example.projectfinal.activity.admin.AdminAddAccountActivity;
import com.example.projectfinal.adapter.BookPaymentAdapter;
import com.example.projectfinal.api.CityAPI;
import com.example.projectfinal.api.OrderAPI;
import com.example.projectfinal.api.OrderDetailAPI;
import com.example.projectfinal.entity.Book;
import com.example.projectfinal.entity.Cart;
import com.example.projectfinal.entity.City;
import com.example.projectfinal.entity.Order;
import com.example.projectfinal.entity.OrderDetail;
import com.example.projectfinal.entity.User;

import java.io.File;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PaymentActivity extends AppCompatActivity {
    private RecyclerView mRcvBook;
    private BookPaymentAdapter mAdapter;
    private User mUser;
    private Book mBook;
    private List<City> mLstCity;
    private float mBookTotalPrice;
    private float mTotalPrice;
    private int mBookNumber;
    private EditText userName, userPhone, userEmail, userAddress, paymentNote;
    private TextView bookTotalPrice;
    private TextView shippingPrice;
    private TextView totalPrice;
    private TextView bookName;
    private TextView bookPrice;
    private TextView bookNumber;
    private ImageView bookImg;
    private RadioButton rdoCOD, rdoBanking;
    private Spinner city;
    private int shipping;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        declareField();
        loadCities();

        Bundle bundle = getIntent().getExtras();
        mUser = (User) bundle.get("object_user");
        mBook = (Book) bundle.get("object_book");
        mBookTotalPrice = (float) getIntent().getExtras().get("TotalPrice");
        mBookNumber = (int) getIntent().getExtras().get("BookNumber");

        bookName.setText(mBook.getName());
        if(mBook.getSalePrice() != 0){
            bookPrice.setText(mBook.getSalePrice() + " đ");
        }else{
            bookPrice.setText(mBook.getPrice() + " đ");
        }
        bookNumber.setText("Số lượng: " + mBookNumber);
        File imgFile = new File(mBook.getPicture());
        Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
        bookImg.setImageBitmap(myBitmap);

        userName.setText(mUser.getName());
        userPhone.setText(mUser.getPhone());
        userEmail.setText(mUser.getEmail());
        bookTotalPrice.setText(String.valueOf(mBookTotalPrice));

        city.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                City c = (City) city.getSelectedItem();
                if(c.getName().equals("Hà Nội") || c.getName().equals("TP. Hồ Chí Minh")){
                    shipping = 15000;
                }else{
                    shipping = 30000;
                }
                mTotalPrice = shipping + mBookTotalPrice;
                shippingPrice.setText(String.valueOf(shipping));
                totalPrice.setText(String.valueOf(mTotalPrice));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        Button btnPayment = findViewById(R.id.btnPaymentAccept);
        btnPayment.setOnClickListener(listenerPayment);
    }

    public void declareField(){
        userName = findViewById(R.id.edtPaymentUserName);
        userPhone = findViewById(R.id.edtPaymentUserPhone);
        userEmail = findViewById(R.id.edtPaymentUserEmail);
        userAddress = findViewById(R.id.edtPaymentUserAddress);
        paymentNote = findViewById(R.id.edtPaymentNote);
        bookTotalPrice = findViewById(R.id.txtPaymentBookTotalPrice);
        shippingPrice = findViewById(R.id.txtPaymentShippingPrice);
        totalPrice = findViewById(R.id.txtPaymentTotalPrice);
        city = findViewById(R.id.spnPaymentCity);
        rdoCOD = findViewById(R.id.rdoPaymentCOD);
        rdoBanking = findViewById(R.id.rdoPaymentBanking);
        bookName = findViewById(R.id.book_name);
        bookPrice = findViewById(R.id.book_price);
        bookNumber = findViewById(R.id.book_number);
        bookImg = findViewById(R.id.book_image);
    }

    private void loadCities() {
        mLstCity = new ArrayList<>();
        CityAPI.cityApi.getListCities().enqueue(new Callback<List<City>>() {
            @Override
            public void onResponse(Call<List<City>> call, Response<List<City>> response) {
                if(response.isSuccessful()){
                    mLstCity = response.body();
                    ArrayAdapter<City> adapter = new ArrayAdapter<>(PaymentActivity.this, android.R.layout.simple_list_item_1, mLstCity);
                    city.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<List<City>> call, Throwable t) {
                Toast.makeText(PaymentActivity.this, "Lỗi khi gọi API", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private View.OnClickListener listenerPayment = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            try {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                Calendar calendar = Calendar.getInstance();
                String strDate = simpleDateFormat.format(calendar.getTime());
                Date date = simpleDateFormat.parse(strDate);
                City c = (City) city.getSelectedItem();
                int cityId = c.getId();
                float price = Float.parseFloat(totalPrice.getText().toString());
                String shippingAddress = userAddress.getText().toString();
                int shipPrice = Integer.parseInt(shippingPrice.getText().toString());
                int paymentId = 0;
                if(rdoCOD.isChecked()){
                    paymentId = 1;
                }else{
                    paymentId = 2;
                }
                String note = paymentNote.getText().toString();

            } catch (ParseException e) {
                e.printStackTrace();
            }

        }
    };
}