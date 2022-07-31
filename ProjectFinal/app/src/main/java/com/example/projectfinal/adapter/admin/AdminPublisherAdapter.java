package com.example.projectfinal.adapter.admin;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projectfinal.R;
import com.example.projectfinal.entity.Publisher;

import java.util.List;

public class AdminPublisherAdapter extends RecyclerView.Adapter<AdminPublisherAdapter.PublisherViewHolder> {

    private Context mCtx;
    private List<Publisher> mLst;

    public AdminPublisherAdapter(Context mCtx, List<Publisher> mLst) {
        this.mCtx = mCtx;
        this.mLst = mLst;
    }

    @NonNull
    @Override
    public AdminPublisherAdapter.PublisherViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mCtx).inflate(R.layout.item_admin_publisher, parent, false);
        AdminPublisherAdapter.PublisherViewHolder cvh = new AdminPublisherAdapter.PublisherViewHolder(view);
        return cvh;
    }

    @Override
    public void onBindViewHolder(@NonNull AdminPublisherAdapter.PublisherViewHolder holder, int position) {
        Publisher c = mLst.get(position);
        if (c == null) {
            return;
        }

        holder.txtPublisherName.setText(c.getName());
    }

    @Override
    public int getItemCount() {
        if (mLst != null) {
            return mLst.size();
        }
        return 0;
    }

    public static class PublisherViewHolder extends RecyclerView.ViewHolder {

        private TextView txtPublisherName;

        public PublisherViewHolder(@NonNull View itemView) {
            super(itemView);

            txtPublisherName = itemView.findViewById(R.id.admin_publisher_name);
        }
    }
}
