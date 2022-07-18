package com.example.projectfinal.fragment;

import static android.content.Context.MODE_PRIVATE;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.projectfinal.LoginActivity;
import com.example.projectfinal.MainActivity;
import com.example.projectfinal.R;

public class AccountFragment extends Fragment {
    private MainActivity mMainActivity;
    private SharedPreferences sharedPreferences;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_account, container, false);
        sharedPreferences = getContext().getSharedPreferences("UserInfo", MODE_PRIVATE);

        TextView txtUserName = view.findViewById(R.id.txtUserName);
        TextView txtUserId = view.findViewById(R.id.txtUserId);
        mMainActivity = (MainActivity) getActivity();
        txtUserName.setText(mMainActivity.getmUserName());
        txtUserId.setText(String.valueOf(mMainActivity.getmUserId()));

        Button btnLogout = view.findViewById(R.id.btnLogout);
        Button btnUserDetail = view.findViewById(R.id.btnUserDetail);

        btnLogout.setOnClickListener(listenerLogout);
        btnUserDetail.setOnClickListener(listenerUserDetail);
        return view;
    }

    private View.OnClickListener listenerLogout = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            //clear dữ liệu trong SharedPreferences
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.clear();
            editor.commit();
            Toast.makeText(mMainActivity, "Đăng xuất thành công", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(getActivity(), LoginActivity.class);
            startActivity(intent);
        }
    };

    private View.OnClickListener listenerUserDetail = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

        }
    };
}
