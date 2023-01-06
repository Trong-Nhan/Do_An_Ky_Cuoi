package com.example.projectfinal.activity.admin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.projectfinal.R;
import com.example.projectfinal.api.BookAPI;
import com.example.projectfinal.api.OrderAPI;
import com.example.projectfinal.api.UserAPI;
import com.example.projectfinal.entity.Book;
import com.example.projectfinal.entity.Order;
import com.example.projectfinal.entity.User;

import java.io.File;
import java.text.DecimalFormat;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdminOrderDetailActivity extends AppCompatActivity {
    private Order mOrder;
    private Book mBook;
    private User mUser;
    private User mAdUser;
    private TextView txtOrderId;
    private ImageView imgBook;
    private TextView txtBookName;
    private TextView txtBookNumber;
    private TextView txtBookPrice;
    private TextView txtOrderTotalPrice;
    private TextView txtOrderStatus;
    private TextView txtUserName;
    private TextView txtUserEmail;
    private TextView txtUserPhone;
    private TextView txtUserAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_order_detail);

        Bundle bundle = getIntent().getExtras();
        if (bundle == null){
            return;
        }

        mOrder = (Order) bundle.get("orderInfo");
        mAdUser = (User) bundle.get("userInfo");


        DecimalFormat formatter = new DecimalFormat("###,###,###");
        txtOrderId = findViewById(R.id.txtOrderDetailId);
        imgBook = findViewById(R.id.imdOrderDetailBookImg);
        txtBookName = findViewById(R.id.txtOrderDetailBookName);
        txtBookNumber = findViewById(R.id.txtOrderDetailBookNumber);
        txtBookPrice = findViewById(R.id.txtOrderDetailBookPrice);
        txtOrderTotalPrice = findViewById(R.id.txtOrderDetailTotalPrice);
        txtOrderStatus = findViewById(R.id.txtOrderDetailStatus);
        txtUserName = findViewById(R.id.txtOrderDetailUserName);
        txtUserEmail = findViewById(R.id.txtOrderDetailUserEmail);
        txtUserPhone = findViewById(R.id.txtOrderDetailUserPhone);
        txtUserAddress = findViewById(R.id.txtOrderDetailUserAddress);
        Button btnAccept = findViewById(R.id.btnOrderAccept);
        Button btnReject = findViewById(R.id.btnOrderReject);

        if(mOrder.getStatus().equals("Đã nhận")){
            btnAccept.setVisibility(View.VISIBLE);
            btnReject.setVisibility(View.VISIBLE);
        }else{
            btnAccept.setVisibility(View.GONE);
            btnReject.setVisibility(View.GONE);
        }
        getBook();
        getUser();

        txtOrderId.setText("Mã đơn hàng: " + mOrder.getId());

        txtOrderTotalPrice.setText(formatter.format(mOrder.getTotalPrice()) + "đ");
        txtOrderStatus.setText("Trạng thái: " + mOrder.getStatus());
        txtUserAddress.setText("Địa chỉ nhận hàng: " + mOrder.getShippingAddress());

        btnAccept.setOnClickListener(listenerAccept);
        btnReject.setOnClickListener(listenerReject);

    }

    public void getBook(){
        BookAPI.bookAPI.detailBook(mOrder.getBookId()).enqueue(new Callback<Book>() {
            @Override
            public void onResponse(Call<Book> call, Response<Book> response) {
                if(response.isSuccessful()){
                    DecimalFormat formatter = new DecimalFormat("###,###,###");
                    mBook = response.body();
                    File imgFile = new File(mBook.getPicture());
                    Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
                    imgBook.setImageBitmap(myBitmap);
                    txtBookName.setText(mBook.getName());
                    txtBookNumber.setText("Số lượng: " + mOrder.getBookNumber());
                    if(mBook.getSalePrice() != 0){
                        txtBookPrice.setText(formatter.format(mBook.getSalePrice()) + "đ");
                    }else{
                        txtBookPrice.setText(formatter.format(mBook.getPrice()) + "đ");
                    }
                }
            }

            @Override
            public void onFailure(Call<Book> call, Throwable t) {
                Toast.makeText(AdminOrderDetailActivity.this, "Lỗi khi gọi API Book", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void getUser(){
        UserAPI.userApi.getUserById(mOrder.getUserId()).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if(response.isSuccessful()){
                    mUser = response.body();
                    txtUserName.setText("Tên người đặt hàng: "+ mUser.getName());
                    txtUserEmail.setText("Email: " + mUser.getEmail());
                    txtUserPhone.setText("Phone: " + mUser.getPhone());
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(AdminOrderDetailActivity.this, "Lỗi khi gọi API User", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private View.OnClickListener listenerAccept = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            mOrder.setStatus("Đã xác nhận");
            OrderAPI.orderApi.updateOrder(mOrder).enqueue(new Callback<Order>() {
                @Override
                public void onResponse(Call<Order> call, Response<Order> response) {
                    if(response.isSuccessful()){
                        Toast.makeText(AdminOrderDetailActivity.this, "Cập nhật trạng thái thành công", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(AdminOrderDetailActivity.this, AdminOrderActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("userInfo", mAdUser);
                        intent.putExtras(bundle);
                        startActivity(intent);
                    }
                }

                @Override
                public void onFailure(Call<Order> call, Throwable t) {
                    Toast.makeText(AdminOrderDetailActivity.this, "Lỗi khi gọi API", Toast.LENGTH_SHORT).show();
                }
            });
        }
    };

    private View.OnClickListener listenerReject = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            mOrder.setStatus("Đã từ chối");
            OrderAPI.orderApi.updateOrder(mOrder).enqueue(new Callback<Order>() {
                @Override
                public void onResponse(Call<Order> call, Response<Order> response) {
                    if(response.isSuccessful()){
                        Toast.makeText(AdminOrderDetailActivity.this, "Cập nhật trạng thái thành công", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(AdminOrderDetailActivity.this, AdminOrderActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("userInfo", mAdUser);
                        intent.putExtras(bundle);
                        startActivity(intent);
                    }
                }

                @Override
                public void onFailure(Call<Order> call, Throwable t) {
                    Toast.makeText(AdminOrderDetailActivity.this, "Lỗi khi gọi API", Toast.LENGTH_SHORT).show();
                }
            });
        }
    };

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(AdminOrderDetailActivity.this, AdminOrderActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("userInfo", mAdUser);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}