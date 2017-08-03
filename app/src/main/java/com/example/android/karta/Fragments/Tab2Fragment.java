package com.example.android.karta.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.android.karta.API.API;
import com.example.android.karta.API.Service;
import com.example.android.karta.Activities.CommerceActivity;
import com.example.android.karta.Adapters.AdapterProduct;
import com.example.android.karta.Models.Product;
import com.example.android.karta.Models.Response.ProductResponse;
import com.example.android.karta.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * A simple {@link Fragment} subclass.
 */
public class Tab2Fragment extends Fragment {

    View view;


    public Tab2Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_tab2, container, false);

        int id_commerce = CommerceActivity.id_commerce;

        getComplements(id_commerce);

        return view;
    }

    private void getComplements(int id_commerce) {

        Retrofit retrofit = API.getRetrofit();

        Service service = retrofit.create(Service.class);

        Call<ProductResponse> productCall = service.getCatProductData(id_commerce, 2);

        productCall.enqueue(new Callback<ProductResponse>() {
            @Override
            public void onResponse(Call<ProductResponse> call, Response<ProductResponse> response) {

                if(response.isSuccessful()) {

                    ProductResponse productRes = response.body();

                    if(productRes.getCode() == 200){

                        List<Product> complements = productRes.getProducts();

                        RecyclerView rv2 = (RecyclerView) view.findViewById(R.id.recyclerTab2);

                        rv2.setLayoutManager(new LinearLayoutManager(getActivity()));

                        AdapterProduct adapter2 = new AdapterProduct(complements);

                        rv2.setAdapter(adapter2);
                    }

                }
                else {
                    Toast.makeText(getContext(), "Error", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ProductResponse> call, Throwable t) {

            }
        });
    }

}
