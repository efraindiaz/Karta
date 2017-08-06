package com.example.android.karta.API;

import com.example.android.karta.Models.Response.CommerceResponse;
import com.example.android.karta.Models.Response.OrderResponse;
import com.example.android.karta.Models.Response.ProductResponse;
import com.example.android.karta.Models.Response.UserResponse;
import com.example.android.karta.Models.ServiceResponse;
import com.example.android.karta.Models.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by Root on 12/07/2017.
 */

public interface Service {

    //ServiceResponse res = new ServiceResponse();

    //ServiceResponse.ProductResponse resP = res.new ProductResponse();

    //@GET("commerce/1/product")
    //Call<List<Product>> getProductData();

    //New Account
    @POST("client/new-profile")
    Call<UserResponse> newAccount(@Body User newUser);

    //Auth
    @FormUrlEncoded
    @POST("client/login")
    Call<UserResponse> getUserData(@Field("email") String email,
                                   @Field("password") String password);

    //Obtain a list of all commerce
    @GET("free/commerces")
    Call<CommerceResponse> getCommerceData();

    //Obtain a list of products
    @GET("commerce/{id_commerce}/product")
    Call<ProductResponse> getProductData(@Path("id_commerce") int id_commerce);

    //obtain an specific product
    @GET("commerce/{id_commerce}/product/cat/{id_cat}")
    Call<ProductResponse> getCatProductData(@Path("id_commerce") int id_commerce,
                                            @Path("id_cat") int id_cat);

    //obtain the list of orders

    @GET("order/{id_user}/list-orders")
    Call<OrderResponse> getOrders(@Path("id_user") int id_user);



}
