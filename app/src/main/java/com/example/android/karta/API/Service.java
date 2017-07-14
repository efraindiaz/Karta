package com.example.android.karta.API;

import com.example.android.karta.Models.Product;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Root on 12/07/2017.
 */

public interface Service {

    @GET("commerce/1/product")
    Call<List<Product>> getProductData();

}
