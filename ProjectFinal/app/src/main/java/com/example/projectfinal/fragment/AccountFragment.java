package com.example.projectfinal.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.projectfinal.LoginActivity;
import com.example.projectfinal.MainActivity;
import com.example.projectfinal.R;

public class AccountFragment extends Fragment {
    private MainActivity mMainActivity;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_account, container, false);
        TextView txtUserName = view.findViewById(R.id.txtUserName);
        mMainActivity = (MainActivity) getActivity();
        txtUserName.setText(mMainActivity.getmUserName());

        Button btnLogout = view.findViewById(R.id.btnLogout);
        Button btnUserDetail = view.findViewById(R.id.btnUserDetail);

        btnLogout.setOnClickListener(listenerLogout);
        btnUserDetail.setOnClickListener(listenerUserDetail);
        return view;
    }

    private View.OnClickListener listenerLogout = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
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
