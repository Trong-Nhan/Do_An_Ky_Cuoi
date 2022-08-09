package com.example.projectfinal.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projectfinal.R;
import com.example.projectfinal.adapter.BookAdapter;
import com.example.projectfinal.adapter.admin.HomeNewsAdapter;
import com.example.projectfinal.api.NewsAPI;
import com.example.projectfinal.entity.Book;
import com.example.projectfinal.entity.News;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {
    private BookAdapter mBookAdapter;
    private HomeNewsAdapter mHomeNewsAdapter;
    private List<Book> mLstBook = new ArrayList<>();
    private List<News> mLstNews = new ArrayList<>();
    RecyclerView rcvHomeNews;

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
//        mNewsAdapter = new NewsAdapter(getActivity(), mLstNews);
//        RecyclerView rcvHomeNews = view.findViewById(R.id.rcvHomeNews);
//        rcvHomeNews.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false));
//        rcvHomeNews.setAdapter(mNewsAdapter);

        getListNews();
        rcvHomeNews = view.findViewById(R.id.rcvHomeNews);
        //set dữ liệu lên recycler view
        rcvHomeNews.setLayoutManager(new LinearLayoutManager(getActivity()));
        rcvHomeNews.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));

        return view;
    }

    //Lay du lieu tin tuc qua API
    private void getListNews() {
        NewsAPI.newsAPI.getNesws().enqueue(new Callback<List<News>>() {
            @Override
            public void onResponse(Call<List<News>> call, Response<List<News>> response) {
                if (response.isSuccessful()) {
                    mLstNews = response.body();
                    mHomeNewsAdapter = new HomeNewsAdapter(getActivity(), mLstNews);
                    rcvHomeNews.setAdapter(mHomeNewsAdapter);
                }
            }

            @Override
            public void onFailure(Call<List<News>> call, Throwable t) {
                Toast.makeText(getActivity(), "Lỗi khi gọi API", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getListBook() {
        //Viet api
    }

}
