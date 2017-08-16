package com.example.android.karta.Adapters;

import android.content.Context;
import android.media.Image;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.android.karta.Activities.CommerceActivity;
import com.example.android.karta.Activities.ShoppingCartActivity;
import com.example.android.karta.Models.Product;
import com.example.android.karta.R;

import java.util.List;

/**
 * Created by Root on 09/08/2017.
 */

public class AdapterCart extends RecyclerView.Adapter<AdapterCart.cartProductsViewHolder>{

    List<Product> cartProducts;
    Context context;

    public AdapterCart(List<Product> cartProducts) {
        this.cartProducts = cartProducts;
    }

    @Override
    public cartProductsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_recycler, parent,false);

        cartProductsViewHolder holder = new cartProductsViewHolder(v);

        context = parent.getContext();

        return holder;
    }

    @Override
    public void onBindViewHolder(final cartProductsViewHolder holder, final int position) {

        final Product item = cartProducts.get(position);
        Glide.with(context).load(item.getImage()).into(holder.imgItem);
        holder.txtNameItem.setText(item.getName());
        holder.txtPriceItem.setText("$"+item.getPrice()+" MXN");

        holder.txtQuantity.setEnabled(false);
        holder.txtQuantity.setText(""+item.getQuantity());

        holder.btnRemoveItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                cartProducts.remove(position);
                notifyItemRemoved(position);
                notifyItemRangeChanged(position, cartProducts.size());
                ShoppingCartActivity.txtTotal.setText("$"+calculateTotal()+" MXN");
            }
        });


        /*****Controlls to dec and Inc quantity*****/

        //Dec quantity
        holder.btnDec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int quantity = Integer.parseInt(holder.txtQuantity.getText().toString());
                int newDecQuantity = decQuantity(quantity); //return the new quantity
                holder.txtQuantity.setText(""+newDecQuantity); //set the new quantity to txt
                cartProducts.get(position).setQuantity(newDecQuantity); //update the quantity from arraylist

                ShoppingCartActivity.txtTotal.setText("$"+calculateTotal()+" MXN");



            }
        });

        //Inc quantity
        holder.btnInc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int quantity = Integer.parseInt(holder.txtQuantity.getText().toString());
                int newIncQuantity = incQuantity(quantity);
                holder.txtQuantity.setText(""+newIncQuantity);
                cartProducts.get(position).setQuantity(newIncQuantity);
                ShoppingCartActivity.txtTotal.setText("$"+calculateTotal()+" MXN");


            }
        });

    }

    @Override
    public int getItemCount() {
        return cartProducts.size();
    }


    public class cartProductsViewHolder extends RecyclerView.ViewHolder{

        ImageButton btnRemoveItem;
        ImageView imgItem;
        TextView txtNameItem;
        TextView txtPriceItem;
        EditText txtQuantity;
        TextView txtTotal;

        Button btnInc;
        Button btnDec;

        public cartProductsViewHolder(View itemView) {
            super(itemView);

            imgItem = (ImageView) itemView.findViewById(R.id.cartImgItem);
            btnRemoveItem = (ImageButton) itemView.findViewById(R.id.removeItem);
            txtNameItem = (TextView) itemView.findViewById(R.id.cartNameItem);
            txtPriceItem = (TextView) itemView.findViewById(R.id.cartPriceItem);

            txtQuantity = (EditText) itemView.findViewById(R.id.txtQuatity);


            btnDec = (Button) itemView.findViewById(R.id.btnDecItem);
            btnInc = (Button) itemView.findViewById(R.id.btnIncItem);



        }
    }


    //Decrement quantity

    public int decQuantity(int quantity){

        if(quantity != 1){

            quantity = quantity - 1;
            return quantity;
        }

        return 1;
    }

    //Increment quantity
    public int incQuantity(int quantity){

        quantity = quantity + 1;

        return quantity;
    }


    public Double calculateTotal(){

        Double total = 0.0;

        for(int i = 0; i < cartProducts.size(); i++){

            total = total + (cartProducts.get(i).getQuantity() * cartProducts.get(i).getPrice());

        }

        return total;
    }




}
