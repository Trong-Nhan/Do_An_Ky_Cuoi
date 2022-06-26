package com.example.projectfinal;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

public class AdapterTicket extends ArrayAdapter<Ticket> {
    private Context mCtx;
    private List<Ticket> mLstBook;

    public AdapterTicket(@NonNull Context context, @NonNull List<Ticket> objects) {
        super(context, R.layout.item_ticket, objects);

        this.mCtx = context;
        this.mLstBook = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View item = convertView;
        if (item == null) {
            // Lần đầu vẽ item thì thực hiện khởi tạo layout
            item = LayoutInflater.from(mCtx).inflate(R.layout.item_ticket, null);
        }

        Ticket b = mLstBook.get(position);

        // Các TextView trên layout item
        TextView txtId = item.findViewById(R.id.itemId);
        TextView txtTitle = item.findViewById(R.id.itemDeparture);
        TextView txtAuthor = item.findViewById(R.id.itemCityDeparture);
        TextView txtPrice = item.findViewById(R.id.itemCityArrive);

        txtId.setText(b.getId() + "");
        txtTitle.setText(b.getTimeDeparture());
        txtAuthor.setText(b.getCityDeparture());
        txtPrice.setText(b.getCityArrive());
//        NumberFormat fmt = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
//        txtPrice.setText(fmt.format(b.getPrice()));

        return item;
    }
}
