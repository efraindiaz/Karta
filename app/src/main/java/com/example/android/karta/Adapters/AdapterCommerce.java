package com.example.android.karta.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.android.karta.Activities.CommerceActivity;
import com.example.android.karta.Models.Commerce;
import com.example.android.karta.R;

import java.util.List;

/**
 * Created by Root on 12/07/2017.
 */

public class AdapterCommerce extends RecyclerView.Adapter<AdapterCommerce.CommercesViewHolder> {

    List<Commerce> commerces;

    Context context;

    public AdapterCommerce(List<Commerce> commerces) {
        this.commerces = commerces;
    }

    @Override
    public CommercesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.commerce_recycler, parent, false);
        CommercesViewHolder holder = new CommercesViewHolder(v);
        context = parent.getContext();
        return holder;
    }

    @Override
    public void onBindViewHolder(CommercesViewHolder holder, int position) {

        final Commerce commerce = commerces.get(position);

        Glide.with(context).load(commerce.getUrl_img()).into(holder.imgCommerce);
        holder.txtName.setText(commerce.getName());

        holder.btnDetailCommerce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, CommerceActivity.class);
                intent.putExtra("id_commerce", commerce.getId_commerce());
                intent.putExtra("name", commerce.getName());
                intent.putExtra("img", commerce.getUrl_img());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return commerces.size();
    }


    public static class CommercesViewHolder extends RecyclerView.ViewHolder{

        TextView txtName;
        ImageView imgCommerce;
        Button btnDetailCommerce;

        public CommercesViewHolder(View itemView){
            super(itemView);

            txtName = (TextView) itemView.findViewById(R.id.txtCommerceName);
            imgCommerce = (ImageView) itemView.findViewById(R.id.imageViewCommerce);
            btnDetailCommerce = (Button) itemView.findViewById(R.id.buttonSeeDetailCommerce);
        }

    }



}
