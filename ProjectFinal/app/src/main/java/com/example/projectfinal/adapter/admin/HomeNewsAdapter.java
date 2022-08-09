package com.example.projectfinal.adapter.admin;

import android.content.Context;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projectfinal.R;
import com.example.projectfinal.entity.News;

import java.util.List;

public class HomeNewsAdapter extends RecyclerView.Adapter<HomeNewsAdapter.NewsViewHolder> {

    private Context mCtx;
    private List<News> mLst;

    public HomeNewsAdapter(Context mCtx, List<News> mLst) {
        this.mCtx = mCtx;
        this.mLst = mLst;
    }

    @NonNull
    @Override
    public HomeNewsAdapter.NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mCtx).inflate(R.layout.item_admin_news, parent, false);
        HomeNewsAdapter.NewsViewHolder nvh = new HomeNewsAdapter.NewsViewHolder(view);
        return nvh;
    }

    @Override
    public void onBindViewHolder(@NonNull HomeNewsAdapter.NewsViewHolder holder, int position) {
        News n = mLst.get(position);
        if (n == null) {
            return;
        }
        holder.imgNews.setImageResource(getImageId(mCtx, n.getPicture()));
        holder.txtNewsTitle.setText(n.getDescription());
    }

    private int getImageId(Context context, String imageName) {
        return context.getResources().getIdentifier("drawable/"
                + imageName, null, context.getPackageName());
    }

    public int getItemCount() {
        if (mLst != null) {
            if (mLst.size() > 5) {
                return 5;
            }
            return mLst.size();
        }
        return 0;
    }

    public static class NewsViewHolder extends RecyclerView.ViewHolder{

        private ImageView imgNews;
        private TextView txtNewsTitle;

        public NewsViewHolder(@NonNull View itemView) {
            super(itemView);

            imgNews = itemView.findViewById(R.id.itemNewsImg);
            txtNewsTitle = itemView.findViewById(R.id.itemNewsTitle);
        }

    }
}
