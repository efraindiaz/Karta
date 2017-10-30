package com.example.android.karta.API;

import com.example.android.karta.Models.Commerce;
import com.example.android.karta.Models.LocationU;
import com.example.android.karta.Models.Response.CommerceResponse;
import com.example.android.karta.Models.Response.GenericResponse;
import com.example.android.karta.Models.Response.LocationResponse;
import com.example.android.karta.Models.Response.OrderDetailResponse;
import com.example.android.karta.Models.Response.OrderResponse;
import com.example.android.karta.Models.Response.ProductResponse;
import com.example.android.karta.Models.Response.UserResponse;
import com.example.android.karta.Models.User;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

/**
 * Created by Root on 12/07/2017.
 */

public interface Service {

    //ServiceResponse res = new ServiceResponse();

    //ServiceResponse.ProductResponse resP = res.new ProductResponse();

    //@GET("commerce/1/product")
    //Call<List<Product>> getProductData();

    /******************ACCOUNT*********************/
    //New Account
    @POST("client/new-profile")
    Call<UserResponse> newAccount(@Body User newUser);

    //Auth
    @FormUrlEncoded
    @POST("client/login")
    Call<UserResponse> getUserData(@Field("email") String email,
                                   @Field("password") String password);
    //Change Password
    //Restore password

    /******************COMMERCE*********************/
    //Obtain a list of all commerce
    @GET("free/commerces")
    Call<CommerceResponse> getCommerceData();

    //Obtain the detail info of commerce
    @GET("commerce/{id_commerce}")
    Call<CommerceResponse> getCommerceDetail(@Path("id_commerce") int id_commerce);

    //Obtain a list of products
    @GET("commerce/{id_commerce}/product")
    Call<ProductResponse> getProductData(@Path("id_commerce") int id_commerce);

    //obtain an specific product
    @GET("commerce/{id_commerce}/product/cat/{id_cat}")
    Call<ProductResponse> getCatProductData(@Path("id_commerce") int id_commerce,
                                            @Path("id_cat") int id_cat);

    /******************LOCATIONS*********************/

    //Obtain a list of locations

    @GET("client/{id_user}/location")
    Call<LocationResponse> getLocations(@Path("id_user") int id_user);

    //New LocationU

    @POST("client/{id_user}/create-location")
    Call<GenericResponse> newLocation(@Body LocationU newLocationU,
                                      @Path("id_user") int id_user);

    //Update LocationU
    @PUT("client/{id_user}/update-location/{id_location}")
    Call<GenericResponse> updateLocation(@Body LocationU updateLocationU,
                                  @Path("id_user") int id_user,
                                  @Path("id_location") int id_location);

    //Delete LocationU
    @DELETE("client/{id_user}/delete-location/{id_location}")
    Call<GenericResponse> deleteLocation(@Path("id_user") int id_user,
                                  @Path("id_location") int id_location);

    /********************ORDERS***********************/
    //obtain the list of orders

    @GET("order/{id_user}/list-orders")
    Call<OrderResponse> getOrders(@Path("id_user") int id_user);

    //Obtain the order detail

    @GET("order/detail-order/{id_order}")
    Call<OrderDetailResponse> getOrderDetail(@Path("id_order") int id_order);

    //Genera una nueva orden
    @FormUrlEncoded
    @POST("order/{id_commerce}/create-order")
    Call<GenericResponse> sendOrder(@Path("id_commerce") int id_path_commerce,
                                    @Field("id_commerce") int id_commerce,
                                    @Field("id_info_user_consumer") int id_user,
                                    @Field("total") Double total,
                                    @Field("total_items") int total_items,
                                    @Field("id_consumer_address") int id_address,
                                    @Field("items") String cart);



}
