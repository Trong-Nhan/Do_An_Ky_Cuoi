package com.example.projectfinal.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projectfinal.R;
import com.example.projectfinal.entity.Book;


import java.util.List;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.BookViewHolder> {

    private Context mCtx;
    private List<Book> mLst;

    public BookAdapter(Context context, List<Book> list) {
        this.mCtx = context;
        this.mLst = list;
    }

    @NonNull
    @Override
    public BookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mCtx).inflate(R.layout.item_book, parent, false);
        BookViewHolder bvh = new BookViewHolder(view);
        return bvh;
    }

    @Override
    public void onBindViewHolder(@NonNull BookViewHolder holder, int position) {
        Book b = mLst.get(position);
        if (b == null) {
            return;
        }
        holder.bookImg.setImageResource(b.getImgResouce());
        holder.bookName.setText(b.getName());
        holder.bookAuthor.setText(b.getAuthor());
        holder.price.setText(b.getPrice());
        holder.salePrice.setText(b.getSalePrice());
        holder.ratingBar.setRating(b.getRating());
    }

    private int toString(int price) {
        return toString(price);
    }

    @Override
    public int getItemCount() {
        if (mLst != null) {
            if (mLst.size() > 4) {
                return 4;
            }
            return mLst.size();
        }
        return 0;
    }

    public static class BookViewHolder extends RecyclerView.ViewHolder {

        private TextView bookName;
        private ImageView bookImg;
        private TextView bookAuthor;
        private RatingBar ratingBar;
        private TextView price;
        private TextView salePrice;

        public BookViewHolder(@NonNull View itemView) {
            super(itemView);

            bookName = itemView.findViewById(R.id.book_name);
            bookImg = itemView.findViewById(R.id.book_image);
            bookAuthor = itemView.findViewById(R.id.book_author);
            ratingBar = itemView.findViewById(R.id.rating_bar);
            price = itemView.findViewById(R.id.book_price);
            salePrice = itemView.findViewById(R.id.sale_price);
        }
    }
}
