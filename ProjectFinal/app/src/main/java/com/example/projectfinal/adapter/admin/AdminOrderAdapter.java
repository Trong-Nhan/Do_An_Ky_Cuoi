package com.example.projectfinal.adapter.admin;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.projectfinal.R;
import com.example.projectfinal.entity.Order;

import java.text.DecimalFormat;
import java.util.List;

public class AdminOrderAdapter extends ArrayAdapter<Order> {
    private Context mCtx;
    private List<Order> mLst;
    public AdminOrderAdapter(@NonNull Context context, @NonNull List<Order> objects) {
        super(context, R.layout.item_admin_order, objects);
        this.mCtx = context;
        this.mLst = objects;
    }

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


}
