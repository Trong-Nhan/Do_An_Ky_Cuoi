package com.example.projectfinal.api;

import com.example.projectfinal.entity.Category;
import com.example.projectfinal.entity.News;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;

public interface NewsAPI {
    //Link API: http://localhost:8080/SolarBookAPI/solar_book/news/getnews

    Gson gson = new GsonBuilder().setLenient().create();
    NewsAPI newsAPI = new Retrofit.Builder()
            .baseUrl("http://10.0.2.2:8080/SolarBookAPI/solar_book/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build().create(NewsAPI.class);

    @GET("news/getnews")
    Call<List<News>> getNesws();

    @POST("news/addnews")
    Call<News> addNews(@Body News news);

    @PUT("news/updatenews")
    Call<News> updateNews(@Body News news);

    @POST("news/deletenews")
    Call<News> deleteNews(@Query("id") int id);

    @GET("news/detailnews")
    Call<News> detailNews(@Query("id") int id);
}
