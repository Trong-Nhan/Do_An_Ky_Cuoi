package com.example.projectfinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.projectfinal.databinding.ActivityServiceBinding;

public class ServiceActivity extends AppCompatActivity {
    private ActivityServiceBinding mBD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBD = ActivityServiceBinding.inflate(getLayoutInflater());
        setContentView(mBD.getRoot());

        mBD.header.btnService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Chuyển màn hình Activity
                Intent intent = new Intent(ServiceActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        mBD.header.btnBooking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Chuyển màn hình Activity
                Intent intent = new Intent(ServiceActivity.this, BookingActivity.class);
                startActivity(intent);
            }
        });

        mBD.header.btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Chuyển màn hình List Ticket
                Intent intent = new Intent(ServiceActivity.this, ListTicketActivity.class);
                startActivity(intent);
            }
        });
    }
}