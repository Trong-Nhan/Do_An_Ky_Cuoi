package com.example.projectfinal.api;

import com.example.projectfinal.entity.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface UserAPI {
    //Link API: http://localhost:8080/SolarBookAPI/solar_book/user/getuser

    Gson gson = new GsonBuilder().setLenient().create();
    UserAPI userApi = new Retrofit.Builder()
            .baseUrl("http://10.0.2.2:8080/SolarBookAPI/solar_book/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build().create(UserAPI.class);

    @GET("user/getuser")
    Call<List<User>> getListUsers();

    @GET("user/getuser/find")
    Call<User> getUserByEmail(@Query("email") String email);
}
