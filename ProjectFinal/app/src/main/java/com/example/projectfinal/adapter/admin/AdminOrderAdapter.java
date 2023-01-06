package com.example.projectfinal.adapter.admin;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projectfinal.R;
import com.example.projectfinal.activity.admin.AdminOrderDetailActivity;
import com.example.projectfinal.entity.Order;
import com.example.projectfinal.entity.User;

import java.text.DecimalFormat;
import java.util.List;

public class AdminOrderAdapter extends RecyclerView.Adapter<AdminOrderAdapter.OrderViewHolder> {

    private Context mCtx;
    private List<Order> mLst;
    private User mUser;
    public AdminOrderAdapter(Context context, List<Order> list, User user) {
        this.mCtx = context;
        this.mLst = list;
        this.mUser = user;
    }

    @NonNull
    @Override
    public OrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mCtx).inflate(R.layout.item_admin_order, parent, false);
        OrderViewHolder ovh = new OrderViewHolder(view);
        return ovh;
    }

    @Override
    public void onBindViewHolder(@NonNull OrderViewHolder holder, int position) {
        Order o = mLst.get(position);
        DecimalFormat formatter = new DecimalFormat("###,###,###");
        holder.txtOrderId.setText(String.valueOf(o.getId()));
        holder.txtOrderPrice.setText(formatter.format(o.getTotalPrice()) + " đ");
        holder.txtOrderDate.setText(o.getOrderDate());
        holder.txtOrderStatus.setText(o.getStatus());
        holder.layoutOrderList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickGoDetail(o);
            }
        });
    }

    public void onClickGoDetail(Order o){
        Intent intent = new Intent(mCtx, AdminOrderDetailActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("orderInfo", o);
        bundle.putSerializable("userInfo", mUser);
        intent.putExtras(bundle);
        mCtx.startActivity(intent);
    }

    @Override
    public int getItemCount() {
        if(mLst != null){
            return mLst.size();
        }
        return 0;
    }

    public static class OrderViewHolder extends RecyclerView.ViewHolder {
        private TextView txtOrderId;
        private TextView txtOrderPrice;
        private TextView txtOrderDate;
        private TextView txtOrderStatus;
        private LinearLayout layoutOrderList;
        public OrderViewHolder(@NonNull View itemView) {
            super(itemView);
            txtOrderId = itemView.findViewById(R.id.txtAdminOrderId);
            txtOrderPrice = itemView.findViewById(R.id.txtAdminOrderPrice);
            txtOrderDate = itemView.findViewById(R.id.txtAdminOrderDate);
            txtOrderStatus = itemView.findViewById(R.id.txtAdminOrderStatus);
            layoutOrderList = itemView.findViewById(R.id.layoutAdminOrder);
        }
    }

    /*
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Order o = mLst.get(position);
        View item = convertView;
        if(item == null){
            item = LayoutInflater.from(mCtx).inflate(R.layout.item_admin_order, null);
        }
        DecimalFormat formatter = new DecimalFormat("###,###,###");
        TextView txtOrderId = item.findViewById(R.id.txtAdminOrderId);
        TextView txtOrderPrice = item.findViewById(R.id.txtAdminOrderPrice);
        TextView txtOrderDate = item.findViewById(R.id.txtAdminOrderDate);
        TextView txtOrderStatus = item.findViewById(R.id.txtAdminOrderStatus);

        txtOrderId.setText(String.valueOf(o.getId()));
        txtOrderPrice.setText(formatter.format(o.getTotalPrice()) + " đ");
        txtOrderDate.setText(o.getOrderDate());
        txtOrderStatus.setText(o.getStatus());

        return item;
    }
    */

}
