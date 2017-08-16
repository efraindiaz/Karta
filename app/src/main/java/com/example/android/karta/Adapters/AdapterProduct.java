package com.example.android.karta.Adapters;

import android.app.Dialog;
import android.content.Context;
import android.media.Image;
import android.support.v4.content.res.TypedArrayUtils;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.android.karta.Activities.CommerceActivity;
import com.example.android.karta.Models.Commerce;
import com.example.android.karta.Models.ProdTest;
import com.example.android.karta.Models.Product;
import com.example.android.karta.Models.Response.CartResponse;
import com.example.android.karta.R;

import java.util.List;

import static android.R.attr.tag;
import static android.content.ContentValues.TAG;

/**
 * Created by Root on 12/07/2017.
 */

public class AdapterProduct extends RecyclerView.Adapter<AdapterProduct.ProductsViewHolder>{

    List<Product> products;
    Context context;
    private static final String TAG = "ITEM";
    private static final String TAGA = "TEST";
    int totalProducts;



    public AdapterProduct(List<Product> products){this.products = products;}

    @Override
    public ProductsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_recycler, parent, false);
        ProductsViewHolder holder = new ProductsViewHolder(v);
        context = parent.getContext();
        return holder;
    }

    @Override
    public void onBindViewHolder(final ProductsViewHolder holder, final int position) {

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

                //Cast elements in dialog layout

                Button closeDialog = (Button) dialog.findViewById(R.id.closeDialog);

                ImageView img = (ImageView) dialog.findViewById(R.id.imgDetailProduct);

                TextView txtDetailPrice = (TextView) dialog.findViewById(R.id.txtDetailProductPrice);

                TextView txtDetailName = (TextView) dialog.findViewById(R.id.txtDetailProductName);

                TextView txtDetailDesc = (TextView) dialog.findViewById(R.id.txtDetailProductDesc);

                final EditText inputQuantity = (EditText) dialog.findViewById(R.id.quatity);

                //Buttons to dec and inc quantity
                Button btnDecQ = (Button) dialog.findViewById(R.id.decQ);
                Button btnIncQ = (Button) dialog.findViewById(R.id.incQ);
                Button btnAddToCart = (Button) dialog.findViewById(R.id.btnAddToCart);

                //end cast element

                Glide.with(context).load(product.getImage()).into(img);

                txtDetailPrice.setText("$" + product.getPrice() + " MXN");

                txtDetailName.setText(product.getName());

                txtDetailDesc.setText(product.getDescription());

                inputQuantity.setEnabled(false);

                //First get the valuo of EditText quantity

                //Set dec button to quantity

                btnDecQ.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int quantity = Integer.parseInt(inputQuantity.getText().toString());
                        inputQuantity.setText(""+decQuantity(quantity));
                    }
                });

                //set inc button to quantity

                btnIncQ.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int quantity = Integer.parseInt(inputQuantity.getText().toString());
                        int dec = quantity + 1;
                        inputQuantity.setText(""+incQuantity(quantity));
                    }
                });


                //btn to add product to arraylist CART
                btnAddToCart.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int a = CommerceActivity.cart.size();
                        Log.d(TAGA, String.valueOf(a));
                        int quant = Integer.parseInt(inputQuantity.getText().toString());
                        //Create a new Product object

                        /*Verify if the item exist in the cart*/

                        /****************************/
                        /*
                        /* VERIFY THIS VALIDATION AND IMPROVE THE PERFORMANCE
                         */
                        if(!CommerceActivity.cart.isEmpty()) {
                           for (int i = 0; i < totalProducts + 1; i++) {
                               Log.d(TAG, CommerceActivity.cart.get(i).getId_product() +","+ product.getId_product());

                                if (CommerceActivity.cart.get(i).getId_product() == product.getId_product()) {
                                    Toast.makeText(context, "Ya existe", Toast.LENGTH_SHORT).show();
                                    Toast.makeText(context, "cart: "+CommerceActivity.cart.get(i).getId_product() + ", product: "+product.getId_product(), Toast.LENGTH_SHORT).show();
                                    int prevQ = CommerceActivity.cart.get(i).getQuantity();
                                    CommerceActivity.cart.get(i).setQuantity(prevQ+quant);
                                    break;
                                }else{
                                    Toast.makeText(context, "cart: "+CommerceActivity.cart.get(i).getId_product() + ", product: "+product.getId_product(), Toast.LENGTH_SHORT).show();
                                    Product item = new Product(product.getId_product(), product.getName(), product.getPrice(),product.getImage(), quant);
                                    CommerceActivity.cart.add(item);
                                    break;
                                }

                            }
                        }
                        else {
                            Toast.makeText(context, "primer producto", Toast.LENGTH_SHORT).show();
                            Product item = new Product(product.getId_product(), product.getName(), product.getPrice(),product.getImage(), quant);
                            CommerceActivity.cart.add(item);
                        }



                        //CommerceActivity.cartTest.add(addItem);
                        //CommerceActivity.cart.get(0).setName("perro");
                        totalProducts = CommerceActivity.cart.size();
                        Log.d(TAGA,"agregado: "+ String.valueOf(CommerceActivity.cart.size()));
                        CommerceActivity.counterFab.setCount(getTotalItems());
                        Toast.makeText(context, "Product added to cart", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                });

                //btn to close the dialog

                closeDialog.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });



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

    public int getTotalItems(){
        int totalI = 0;

        for(int i = 0; i < CommerceActivity.cart.size(); i++){

            totalI = totalI + CommerceActivity.cart.get(i).getQuantity();
        }

        return totalI;
    }




}
