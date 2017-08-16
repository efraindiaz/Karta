package com.example.android.karta.Models.Response;

import com.example.android.karta.Models.ProductOrder;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by Root on 14/08/2017.
 */

public class OrderDetailResponse {

    int code;
    @SerializedName("data")
    ArrayList<ProductOrder> dataProducts;

    public  OrderDetailResponse(){}

    public OrderDetailResponse(int code, ArrayList<ProductOrder> dataProducts) {
        this.code = code;
        this.dataProducts = dataProducts;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public ArrayList<ProductOrder> getDataProducts() {
        return dataProducts;
    }

    public void setDataProducts(ArrayList<ProductOrder> dataProducts) {
        this.dataProducts = dataProducts;
    }
}
