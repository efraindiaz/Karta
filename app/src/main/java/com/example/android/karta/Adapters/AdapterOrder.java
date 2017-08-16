package com.example.android.karta.Adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.karta.Activities.OrderDetailActivity;
import com.example.android.karta.Models.Order;
import com.example.android.karta.R;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by Root on 04/08/2017.
 */

public class AdapterOrder extends RecyclerView.Adapter<AdapterOrder.OrdersViewHolder> {

    List<Order> orders;
    Context context;

    public AdapterOrder(List<Order> orders) {
        this.orders = orders;
    }

    @Override
    public OrdersViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.order_recycler, parent, false);

        OrdersViewHolder holder = new OrdersViewHolder(v);

        context = parent.getContext();

        return holder;

    }

    @Override
    public void onBindViewHolder(OrdersViewHolder holder, int position) {

        final Order order = orders.get(position);

        holder.statusLayout.setBackgroundColor(Color.parseColor(order.getStatus_color()));
        holder.status.setText(order.getStatus_order());
        holder.commerce.setText(order.getName());
        holder.date.setText(order.getDate());
        holder.total.setText("$"+order.getTotal());

        holder.btnDetailOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, OrderDetailActivity.class);
                intent.putExtra("id_commerce", order.getId_commerce());
                intent.putExtra("id_order", order.getId_order_consumer());
                intent.putExtra("total", order.getTotal());
                intent.putExtra("date", order.getDate());
                intent.putExtra("status", order.getStatus_order());
                context.startActivity(intent);
            }
        });




    }

    @Override
    public int getItemCount() {
        return orders.size();
    }

    public class OrdersViewHolder extends RecyclerView.ViewHolder {

        TextView status, date, commerce, total;
        Button btnDetailOrder;
        LinearLayout statusLayout;


        public OrdersViewHolder(View itemView) {
            super(itemView);

            statusLayout = (LinearLayout) itemView.findViewById(R.id.statusLayout);
            status = (TextView) itemView.findViewById(R.id.orderStatus);
            commerce = (TextView) itemView.findViewById(R.id.orderCommerceName);
            date = (TextView) itemView.findViewById(R.id.orderDate);
            total = (TextView) itemView.findViewById(R.id.orderTotal);
            btnDetailOrder = (Button) itemView.findViewById(R.id.btnOrderDetail);


        }
    }

}
