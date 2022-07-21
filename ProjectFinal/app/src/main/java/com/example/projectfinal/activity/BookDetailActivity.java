package com.example.projectfinal.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.projectfinal.R;
import com.example.projectfinal.entity.Book;

public class BookDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_detail);

        Bundle bundle = getIntent().getExtras();
        if (bundle == null) {
            return;
        }

        Book book = (Book) bundle.get("object_book");
        TextView tvName = findViewById(R.id.book_name);
        tvName.setText(book.getName());
        ImageView tvPicture = findViewById(R.id.book_image);
        tvPicture.setImageResource(book.getPicture());
        TextView tvAuthor = findViewById(R.id.book_author);
        tvAuthor.setText(book.getAuthor());
        TextView tvPrice = findViewById(R.id.book_price);
        tvPrice.setText(book.getPrice());
        TextView tvSalePrice = findViewById(R.id.sale_price);
        tvSalePrice.setText(book.getSalePrice());
        TextView tvDescription = findViewById(R.id.book_description);
        tvDescription.setText(book.getDescription());

    }
}