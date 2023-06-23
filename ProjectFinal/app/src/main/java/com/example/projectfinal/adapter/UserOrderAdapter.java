package com.example.projectfinal.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.projectfinal.R;
import com.example.projectfinal.api.BookAPI;
import com.example.projectfinal.entity.Book;
import com.example.projectfinal.entity.Order;

import java.io.File;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserOrderAdapter extends ArrayAdapter<Order> {
    private Context mCtx;
    private List<Order> mLstOrder;

    public UserOrderAdapter(@NonNull Context context, @NonNull List<Order> objects) {
        super(context, R.layout.item_user_order, objects);
        this.mCtx = context;
        this.mLstOrder = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Order o = mLstOrder.get(position);
        NumberFormat formatter = new DecimalFormat("#,###");

        View item = convertView;
        if (item == null) {
            item = LayoutInflater.from(this.mCtx).inflate(R.layout.item_user_order, null);
        }
        TextView txtOrderId = item.findViewById(R.id.item_order_id);
        ImageView imgBookImg = item.findViewById(R.id.item_order_bookimg);
        TextView txtBookName = item.findViewById(R.id.item_order_bookname);
        TextView txtBookNumber = item.findViewById(R.id.item_order_booknumber);
        TextView txtBookPrice = item.findViewById(R.id.item_order_bookprice);
        TextView txtOrderTotalPrice = item.findViewById(R.id.item_order_totalprice);
        TextView txtOrderStatus = item.findViewById(R.id.item_order_status);
        
        txtOrderId.setText("Mã đơn hàng: " + o.getId());
        txtOrderTotalPrice.setText("Tổng tiền: " + formatter.format(o.getTotalPrice()) + "đ");
        txtOrderStatus.setText("Trạng thái: " + o.getStatus());
        txtBookNumber.setText("Số lượng: " + o.getBookNumber());
        BookAPI.bookAPI.detailBook(o.getBookId()).enqueue(new Callback<Book>() {
            @Override
            public void onResponse(Call<Book> call, Response<Book> response) {
                if(response.isSuccessful()){
                    Book b = response.body();

                    File imgFile = new File(b.getPicture());
                    Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
                    imgBookImg.setImageBitmap(myBitmap);
                    txtBookName.setText(b.getName());
                    if(b.getSalePrice() != 0){
                        txtBookPrice.setText("Giá tiền: " + formatter.format(b.getSalePrice()) + "đ");
                    }else{
                        txtBookPrice.setText("Giá tiền: " + formatter.format(b.getPrice()) + "đ");
                    }
                }
            }

            @Override
            public void onFailure(Call<Book> call, Throwable t) {
                Toast.makeText(mCtx, "Lỗi khi gọi API", Toast.LENGTH_SHORT).show();
            }
        });
        
        return item;
    }
}
