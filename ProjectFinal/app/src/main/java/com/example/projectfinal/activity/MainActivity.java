package com.example.projectfinal.activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;


import com.example.projectfinal.R;
import com.example.projectfinal.adapter.ViewPagerAdapter;
import com.example.projectfinal.api.UserAPI;
import com.example.projectfinal.entity.User;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView mNavigationView;
    private ViewPager mViewPager;
    private String mUserName;
    private String mUserId;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mNavigationView = findViewById(R.id.bottomNav);
        mViewPager = findViewById(R.id.viewPager);

        setUpViewPager();
        mNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.menuHome:
                        mViewPager.setCurrentItem(0);
                        break;
                    case R.id.menuCategory:
                        mViewPager.setCurrentItem(1);
                        break;
                    case R.id.menuNews:
                        mViewPager.setCurrentItem(2);
                        break;
                    case R.id.menuAccount:
                        mViewPager.setCurrentItem(3);
                        break;
                }
                return true;
            }
        });


        sharedPreferences = getSharedPreferences("UserInfo", MODE_PRIVATE);
        //lấy tên và id người dùng

        if (sharedPreferences.getString("UserEmail", null) != null) {
            String uEmail = sharedPreferences.getString("UserEmail", null);
            UserAPI.userApi.getUserByEmail(uEmail).enqueue(new Callback<User>() {
                @Override
                public void onResponse(Call<User> call, Response<User> response) {
                    if (response.isSuccessful()) {
                        User u = response.body();
                        mUserName = u.getName();
                        mUserId = String.valueOf(u.getId());
                    }
                }

                @Override
                public void onFailure(Call<User> call, Throwable t) {
                    Toast.makeText(MainActivity.this, "Lỗi khi gọi API", Toast.LENGTH_SHORT).show();
                }
            });
        } else if (!getIntent().getExtras().get("adminName").toString().isEmpty()) {
            mUserName = getIntent().getExtras().get("adminName").toString();
        } else {
            mUserName = getIntent().getExtras().get("userName").toString();
            mUserId = getIntent().getExtras().get("userId").toString();
        }


    }

    //hàm thiết lập ViewPager
    private void setUpViewPager() {
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        mViewPager.setAdapter(viewPagerAdapter);

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        mNavigationView.getMenu().findItem(R.id.menuHome).setChecked(true);
                        break;
                    case 1:
                        mNavigationView.getMenu().findItem(R.id.menuCategory).setChecked(true);
                        break;
                    case 2:
                        mNavigationView.getMenu().findItem(R.id.menuNews).setChecked(true);
                        break;
                    case 3:
                        mNavigationView.getMenu().findItem(R.id.menuAccount).setChecked(true);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.cart_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public String getmUserName() {
        return mUserName;
    }

    public String getmUserId() {
        return mUserId;
    }
}