package com.example.projectfinal;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.projectfinal.databinding.ActivityBookingBinding;
import com.example.projectfinal.databinding.ActivityListTicketBinding;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class BookingActivity extends AppCompatActivity {
    private ActivityBookingBinding mBD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBD = ActivityBookingBinding.inflate(getLayoutInflater());
        setContentView(mBD.getRoot());

        mBD.header.btnService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Chuyển màn hình Activity
                Intent intent = new Intent(BookingActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        mBD.header.btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Chuyển màn hình List Ticket
                Intent intent = new Intent(BookingActivity.this, ListTicketActivity.class);
                startActivity(intent);
            }
        });

        EditText txtToDate = findViewById(R.id.txtToDate);
        txtToDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDialog((EditText) view);
            }
        });

        Button btnBooking = findViewById(R.id.formBtnBooking);
        btnBooking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Spinner spGaDi = findViewById(R.id.spFromStation);
                EditText txtFullname = findViewById(R.id.txtFullname);
                Toast.makeText(BookingActivity.this, "Tên khách hàng: " + txtFullname.getText().toString()
                        + " Ga đi: " + spGaDi.getSelectedItem().toString(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void openDialog(EditText v) {
        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();

        DatePickerDialog.OnDateSetListener listener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                // EditText txtFromDate = findViewById(R.id.txtFromDate);
                SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
                Calendar c = Calendar.getInstance();
                c.set(year, month, day); // Cài đặt ngày tháng được chọn
                v.setText(fmt.format(c.getTime()));
            }
        };

        DatePickerDialog pickerDialog = new DatePickerDialog(this, listener,
                c.get(Calendar.YEAR),
                c.get(Calendar.MONTH),
                c.get(Calendar.DAY_OF_MONTH));
        pickerDialog.show(); // Hiển thị dialog
    }

    public void openDatePicker(View view) {
        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();

        DatePickerDialog.OnDateSetListener listener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                EditText txtFromDate = findViewById(R.id.txtFromDate);
                SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
                Calendar c = Calendar.getInstance();
                c.set(year, month, day); // Cài đặt ngày tháng được chọn
                txtFromDate.setText(fmt.format(c.getTime()));
            }
        };

        DatePickerDialog pickerDialog = new DatePickerDialog(this, listener,
                c.get(Calendar.YEAR),
                c.get(Calendar.MONTH),
                c.get(Calendar.DAY_OF_MONTH));
        pickerDialog.show(); // Hiển thị dialog
    }
}