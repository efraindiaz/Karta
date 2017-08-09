package com.example.android.karta.Adapters;

import android.app.Dialog;
import android.content.Context;
import android.media.Image;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.android.karta.Models.Product;
import com.example.android.karta.R;

import java.util.List;

/**
 * Created by Root on 12/07/2017.
 */

public class AdapterProduct extends RecyclerView.Adapter<AdapterProduct.ProductsViewHolder>{

    List<Product> products;
    Context context;

    public AdapterProduct(List<Product> products){this.products = products;}

    @Override
    public ProductsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_recycler, parent, false);
        ProductsViewHolder holder = new ProductsViewHolder(v);
        context = parent.getContext();
        return holder;
    }

    @Override
    public void onBindViewHolder(ProductsViewHolder holder, int position) {

        final Product product = products.get(position);
        Glide.with(context).load(product.getImage()).into(holder.imgProduct);
        holder.txtName.setText(product.getName());
        holder.txtPrice.setText("$" + product.getPrice() + "MXN");

        /*Event for btn detail of product*/
        //display a custom dialog

        holder.btnDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final Dialog dialog = new Dialog(context);

                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);

                dialog.setContentView(R.layout.detail_product_dialog);

                ImageView img = (ImageView) dialog.findViewById(R.id.imgDetailProduct);

                TextView txtDetailPrice = (TextView) dialog.findViewById(R.id.txtDetailProductPrice);

                TextView txtDetailName = (TextView) dialog.findViewById(R.id.txtDetailProductName);

                TextView txtDetailDesc = (TextView) dialog.findViewById(R.id.txtDetailProductDesc);

                Glide.with(context).load(product.getImage()).into(img);

                txtDetailPrice.setText("$" + product.getPrice() + "MXN");

                txtDetailName.setText(product.getName());

                txtDetailDesc.setText(product.getDescription());

                dialog.show();

            }
        });
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public static class ProductsViewHolder extends RecyclerView.ViewHolder{

        TextView txtName, txtPrice;
        ImageView imgProduct;
        Button btnDetail;

        public ProductsViewHolder(View itemView) {
            super(itemView);

            imgProduct = (ImageView) itemView.findViewById(R.id.imageViewProduct);
            txtName = (TextView) itemView.findViewById(R.id.txtV_product_name);
            txtPrice = (TextView) itemView.findViewById(R.id.txtV_product_price);
            btnDetail = (Button) itemView.findViewById(R.id.btnDetailProduct);
        }
    }

    //public void setFilter(List<Product> filter)


}
