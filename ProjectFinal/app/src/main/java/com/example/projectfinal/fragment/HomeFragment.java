package com.example.projectfinal.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projectfinal.R;
import com.example.projectfinal.adapter.BookAdapter;
import com.example.projectfinal.adapter.NewsAdapter;
import com.example.projectfinal.entity.Book;
import com.example.projectfinal.entity.News;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {
    private BookAdapter mBookAdapter;
    private NewsAdapter mNewsAdapter;
    private List<Book> mLstBook = new ArrayList<>();
    private List<News> mLstNews = new ArrayList<>();
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        getListBook();
        mBookAdapter = new BookAdapter(getActivity(), mLstBook);
        //set dữ liệu lên RecycleView New Book
        RecyclerView rcvNewBook = view.findViewById(R.id.rcvNewBook);
        rcvNewBook.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        rcvNewBook.setAdapter(mBookAdapter);
        

        //set dữ liệu lên RecycleView Popular Book
        RecyclerView rcvPopularBook = view.findViewById(R.id.rcvPopularBook);
        rcvPopularBook.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        rcvPopularBook.setAdapter(mBookAdapter);

        //set dữ liệu lên RecycleView Discount Book
        RecyclerView rcvDiscountBook = view.findViewById(R.id.rcvDiscountBook);
        rcvDiscountBook.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false));
        rcvDiscountBook.setAdapter(mBookAdapter);

        //set dữ liệu lên RecycleView Homepage News
//        try {
//            getListNews();
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
        mNewsAdapter = new NewsAdapter(getActivity(), mLstNews);
        RecyclerView rcvHomeNews = view.findViewById(R.id.rcvHomeNews);
        rcvHomeNews.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false));
        rcvHomeNews.setAdapter(mNewsAdapter);

        return view;
    }

    private void getListBook(){
        mLstBook.add(new Book(1,"Harry Potter và Bảo Bối Tử Thần", 1,"100000","80000","Duy Duc",1,2000,R.drawable.book1,197,"Harry Potter và Bảo Bối Tử Thần",200,3.8F, true));
        mLstBook.add(new Book(2,"Cuộc sống của bạn đã tốt đẹp chưa", 1,"100000","80000","Duy Duc",1,2000,R.drawable.book2,197,"Harry Potter và Bảo Bối Tử Thần",200,1.5F,true));
        mLstBook.add(new Book(3,"11 bí quyết giao tiếp để thành công", 1,"100000","80000","Duy Duc",1,2000,R.drawable.book3,197,"Harry Potter và Bảo Bối Tử Thần",200,2.3F,true));
        mLstBook.add(new Book(4,"Tiếng gọi nơi hoang dã", 1,"100000","80000","Duy Duc",1,2000,R.drawable.book4,197,"Harry Potter và Bảo Bối Tử Thần",200, (float) 4.3,true));
        mLstBook.add(new Book(2,"Cuộc sống của bạn đã tốt đẹp chưa", 1,"100000","80000","Duy Duc",1,2000,R.drawable.book2,197,"Harry Potter và Bảo Bối Tử Thần",200,1.5F,true));
        mLstBook.add(new Book(3,"11 bí quyết giao tiếp để thành công", 1,"100000","80000","Duy Duc",1,2000,R.drawable.book3,197,"Harry Potter và Bảo Bối Tử Thần",200,2.3F,true));

    }


//    private void getListNews() throws ParseException {
//        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
//
//        mLstNews.add(new News(1,"Tin Tức", "Mô tả", "Chi tiết", "news1.png", formatter.parse("2006/12/12")));
//        mLstNews.add(new News(2,"Tin Tức", "Mô tả", "Chi tiết", "news1.png", formatter.parse("2006/12/12")));
//        mLstNews.add(new News(3,"Tin Tức", "Mô tả", "Chi tiết", "news1.png", formatter.parse("2006/12/12")));
//        mLstNews.add(new News(4,"Tin Tức", "Mô tả", "Chi tiết", "news1.png", formatter.parse("2006/12/12")));
//        mLstNews.add(new News(5,"Tin Tức", "Mô tả", "Chi tiết", "news1.png", formatter.parse("2006/12/12")));
//
//    }
}
