package com.example.projectfinal.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projectfinal.R;
import com.example.projectfinal.entity.News;

import java.util.List;


public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder> {

    private Context mCtx;
    private List<News> mLst;

    public NewsAdapter(Context mCtx, List<News> mLst) {
        this.mCtx = mCtx;
        this.mLst = mLst;
    }

    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mCtx).inflate(R.layout.item_news_grid_layout, parent, false);
        NewsViewHolder nvh = new NewsViewHolder(view);
        return nvh;
    }

    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder holder, int position) {
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

    @Override
    public int getItemCount() {
        if (mLst != null) {
            if (mLst.size() > 10) {
                return 10;
            }
            return mLst.size();
        }
        return 0;
    }

    public static class NewsViewHolder extends RecyclerView.ViewHolder {

        private ImageView imgNews;
        private TextView txtNewsTitle;

        public NewsViewHolder(@NonNull View itemView) {
            super(itemView);

            imgNews = itemView.findViewById(R.id.itemNewsImg);
            txtNewsTitle = itemView.findViewById(R.id.itemNewsTitle);
        }
    }
}
