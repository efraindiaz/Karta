package com.example.android.karta.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.android.karta.Models.ProductOrder;
import com.example.android.karta.R;

import java.util.List;

/**
 * Created by Root on 14/08/2017.
 */

public class AdapterDetailOrder extends RecyclerView.Adapter<AdapterDetailOrder.DetailOrderViewHolder>{

    List<ProductOrder> productsOrder;
    Context context;

    public AdapterDetailOrder(List<ProductOrder> productsOrder) {
        this.productsOrder = productsOrder;
    }

    @Override
    public DetailOrderViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.detail_order_recycler, parent, false);

        DetailOrderViewHolder holder = new DetailOrderViewHolder(v);

        context = parent.getContext();

        return holder;
    }

    @Override
    public void onBindViewHolder(DetailOrderViewHolder holder, int position) {

        final ProductOrder productOrder = productsOrder.get(position);

        Glide.with(context).load(productOrder.getImage()).into(holder.imgItem);
        holder.nameItem.setText(productOrder.getName());
        holder.priceItem.setText("$"+productOrder.getPrice()+" MXN");

    }

    @Override
    public int getItemCount() {
        return productsOrder.size();
    }

    public static class DetailOrderViewHolder extends RecyclerView.ViewHolder{

        ImageView imgItem;
        TextView nameItem;
        TextView priceItem;

        public DetailOrderViewHolder(View itemView) {
            super(itemView);

            imgItem = (ImageView) itemView.findViewById(R.id.orderImgItem);
            nameItem = (TextView) itemView.findViewById(R.id.orderNameItem);
            priceItem = (TextView) itemView.findViewById(R.id.orderPriceItem);

        }
    }

}
