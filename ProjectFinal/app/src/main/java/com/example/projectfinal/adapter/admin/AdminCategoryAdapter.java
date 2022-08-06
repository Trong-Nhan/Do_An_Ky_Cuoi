package com.example.projectfinal.adapter.admin;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projectfinal.R;
import com.example.projectfinal.entity.Category;

import java.util.List;

public class AdminCategoryAdapter extends RecyclerView.Adapter<AdminCategoryAdapter.CategoryViewHolder> {
    private Context mCtx;
    private List<Category> mLst;

    public AdminCategoryAdapter(Context mCtx, List<Category> mLst) {
        this.mCtx = mCtx;
        this.mLst = mLst;
    }

    @NonNull
    @Override
    public AdminCategoryAdapter.CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mCtx).inflate(R.layout.item_admin_category, parent, false);
        AdminCategoryAdapter.CategoryViewHolder cvh = new AdminCategoryAdapter.CategoryViewHolder(view);
        return cvh;
    }

    @Override
    public void onBindViewHolder(@NonNull AdminCategoryAdapter.CategoryViewHolder holder, int position) {
        Category c = mLst.get(position);
        if (c == null) {
            return;
        }

        holder.txtCategoryName.setText(c.getName());
    }

    @Override
    public int getItemCount() {
        if (mLst != null) {
            return mLst.size();
        }
        return 0;
    }

    public static class CategoryViewHolder extends RecyclerView.ViewHolder {

        private TextView txtCategoryName;

        public CategoryViewHolder(@NonNull View itemView) {
            super(itemView);

            txtCategoryName = itemView.findViewById(R.id.admin_category_name);
        }
    }
}
