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
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView mNavigationView;
    private ViewPager mViewPager;
    private String mUserName;
    private String mUserId;
    private SharedPreferences sharedPreferences;
    private User mUser;
    private String mKey;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mNavigationView = findViewById(R.id.bottomNav);
        mViewPager = findViewById(R.id.viewPager);
        Bundle bundle = getIntent().getExtras();
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

        if (sharedPreferences.contains("uInfo")) {
            Gson gson = new Gson();
            String uJson = sharedPreferences.getString("uInfo", null);
            mUser = gson.fromJson(uJson, User.class);
        } else{
            if(bundle.containsKey("userInfo")){
                mUser = (User) bundle.get("userInfo");
            }else{
                mUser = (User) bundle.get("adInfo");
            }
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

    public User getmUser() {
        return mUser;
    }

}