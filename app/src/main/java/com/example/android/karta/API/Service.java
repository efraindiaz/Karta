package com.example.android.karta.API;

import com.example.android.karta.Models.Response.CommerceResponse;
import com.example.android.karta.Models.Response.ProductResponse;
import com.example.android.karta.Models.Response.UserResponse;
import com.example.android.karta.Models.ServiceResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by Root on 12/07/2017.
 */

public interface Service {

    //ServiceResponse res = new ServiceResponse();

    //ServiceResponse.ProductResponse resP = res.new ProductResponse();

    //@GET("commerce/1/product")
    //Call<List<Product>> getProductData();

    //Auth
    @FormUrlEncoded
    @POST("client/login")
    Call<UserResponse> getUserData(@Field("email") String email,
                                   @Field("password") String password);

    //Obtain a list of products
    @GET("commerce/1/product")
    Call<ProductResponse> getProductData();

    //Obtain a list of all commerce
    @GET("free/commerces")
    Call<CommerceResponse> getCommerceData();



}
