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
import com.example.projectfinal.api.BookAPI;
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
    RecyclerView rcvHomeNews,rcvBook;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        getListBook();
        rcvBook = view.findViewById(R.id.rcvNewBook);
        //set dữ liệu lên RecycleView New Book
        rcvBook.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        rcvBook.setAdapter(mBookAdapter);

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
        BookAPI.bookAPI.getBook().enqueue(new Callback<List<Book>>() {
            @Override
            public void onResponse(Call<List<Book>> call, Response<List<Book>> response) {
                if (response.isSuccessful()) {
                    mLstBook = response.body();
                    mBookAdapter = new BookAdapter(getActivity(), mLstBook);
                    rcvBook.setAdapter(mBookAdapter);
                }
            }

            @Override
            public void onFailure(Call<List<Book>> call, Throwable t) {
                Toast.makeText(getActivity(), "Lỗi khi gọi API", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
