package com.example.android.karta.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.karta.API.API;
import com.example.android.karta.API.Service;
import com.example.android.karta.Activities.CommerceActivity;
import com.example.android.karta.Activities.MainActivity;
import com.example.android.karta.Adapters.AdapterProduct;
import com.example.android.karta.Models.Product;
import com.example.android.karta.Models.Response.ProductResponse;
import com.example.android.karta.R;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * A simple {@link Fragment} subclass.
 */
public class Tab1Fragment extends Fragment {

    View view;

    public Tab1Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_tab1, container, false);

        //CommerceActivity activity = (CommerceActivity) getActivity();

        int id_commerce = CommerceActivity.id_commerce;

        //Example to add product in the cart Array from CommerceActivity Thorught a public variable
       /* Product product = new Product(1,"Perro","precio","img",2);
        Product product2 = new Product(1,"Gato","precio","img",2);
        CommerceActivity.cart.add(product);
        CommerceActivity.cart.add(product2);

        Toast.makeText(getContext(), ""+CommerceActivity.cart.get(0).getName(), Toast.LENGTH_SHORT).show();*/


        getProductList(id_commerce);

        return view;


    }

    private void getProductList(int id_commerce) {

        Retrofit retrofit = API.getRetrofit();

        Service service = retrofit.create(Service.class);

        Call<ProductResponse> productCall = service.getCatProductData(id_commerce, 1);

        productCall.enqueue(new Callback<ProductResponse>() {
            @Override
            public void onResponse(Call<ProductResponse> call, Response<ProductResponse> response) {

                if(response.isSuccessful()) {

                    ProductResponse productRes = response.body();

                    if(productRes.getCode() == 200){

                        List<Product> products = productRes.getProducts();

                        RecyclerView rv = (RecyclerView) view.findViewById(R.id.recyclerTab1);

                        rv.setLayoutManager(new LinearLayoutManager(getActivity()));

                        AdapterProduct adapter = new AdapterProduct(products);

                        rv.setAdapter(adapter);
                    }

                }
                else {
                    Toast.makeText(getContext(), "Error", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ProductResponse> call, Throwable t) {
                Toast.makeText(getContext(), "OnFailure", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
