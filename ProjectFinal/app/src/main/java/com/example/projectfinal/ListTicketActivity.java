package com.example.projectfinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.projectfinal.databinding.ActivityListTicketBinding;
import com.example.projectfinal.databinding.ActivityServiceBinding;

import java.util.ArrayList;
import java.util.List;

public class ListTicketActivity extends AppCompatActivity {
    private ActivityListTicketBinding mBD;
    private List<Ticket> mLstBooks = new ArrayList<>(); // Doi tuong chua mang ticket

    // Hàm sinh dữ liệu mẫu
    private void fakeBooks() {
        mLstBooks.add(new Ticket("DSVN1234", "Bui Trong Nhan", "0908070605", "Ha Noi", "Kon Tum", "29/02/2017   12:55:00", "01/03/2017 14:09:10", "Khu hoi", 10, 1));
        mLstBooks.add(new Ticket("DSVN2222", "Bui Phuong Nam", "0908070605", "Ha Nam", "Vung tau", "29/02/2017   12:55:00", "01/03/2017 14:09:10", "Khu hoi", 10, 1));
        mLstBooks.add(new Ticket("DSVN9090", "Bui Khac Than", "0908070605", "Hoa Binh", "Hai Phong", "29/02/2017   12:55:00", "01/03/2017 14:09:10", "Khu hoi", 10, 1));
        mLstBooks.add(new Ticket("DSVN0987", "Pham Thanh Nam", "0908070605", "Nghe An", "Ha Nam", "29/02/2017   12:55:00", "01/03/2017 14:09:10", "Khu hoi", 10, 1));
        mLstBooks.add(new Ticket("DSVN3786", "Nguyen Tai Tue", "0908070605", "Thai Binh", "Thai Nguyen", "29/02/2017   12:55:00", "01/03/2017 14:09:10", "Khu hoi", 10, 1));
        mLstBooks.add(new Ticket("DSVN3333", "Nguyen Tai Long", "0908070605", "Thai Binh", "Binh Phuoc", "29/02/2017   12:55:00", "01/03/2017 14:09:10", "Khu hoi", 10, 1));
        mLstBooks.add(new Ticket("DSVN4444", "Nguyen Tai Phuong", "0908070605", "Thai Binh", "Long An", "29/02/2017   12:55:00", "01/03/2017 14:09:10", "Khu hoi", 10, 1));
        mLstBooks.add(new Ticket("DSVN3533", "Nguyen Tai Nam", "0908070605", "Thai Binh", "Binh Thuan", "29/02/2017   12:55:00", "01/03/2017 14:09:10", "Khu hoi", 10, 1));
        mLstBooks.add(new Ticket("DSVN3133", "Nguyen Tai Phong", "0908070605", "Thai Binh", "Can Tho", "29/02/2017   12:55:00", "01/03/2017 14:09:10", "Khu hoi", 10, 1));
        mLstBooks.add(new Ticket("DSVN3664", "Nguyen Tai Binh", "0908070605", "Thai Binh", "Hau Giang", "29/02/2017   12:55:00", "01/03/2017 14:09:10", "Khu hoi", 10, 1));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBD = ActivityListTicketBinding.inflate(getLayoutInflater());
        setContentView(mBD.getRoot());

        fakeBooks();

        // Lấy ListView trên layout
        ListView lstBooks = findViewById(R.id.myListView);

        // Tạo Adapter
        AdapterTicket adapterBook = new AdapterTicket(this, mLstBooks);

        lstBooks.setAdapter(adapterBook);
        lstBooks.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int pos, long l) {
                Toast.makeText(ListTicketActivity.this, "Ban chon ve: " + mLstBooks.get(pos).getId(), Toast.LENGTH_SHORT).show();
            }
        });
        mBD.header.btnService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Chuyển màn hình Activity
                Intent intent = new Intent(ListTicketActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        mBD.header.btnBooking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Chuyển màn hình Activity
                Intent intent = new Intent(ListTicketActivity.this, BookingActivity.class);
                startActivity(intent);
            }
        });

    }
}