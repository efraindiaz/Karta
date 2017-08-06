package com.example.android.karta.Models.Response;

import com.example.android.karta.Models.Order;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by Root on 04/08/2017.
 */

public class OrderResponse {

    @SerializedName("data")
    ArrayList<Order> dataOrders;

    int code;

    public OrderResponse(){}

    public OrderResponse(ArrayList<Order> dataOrders, int code) {
        this.dataOrders = dataOrders;
        this.code = code;
    }

    public ArrayList<Order> getDataOrders() {
        return dataOrders;
    }

    public void setDataOrders(ArrayList<Order> dataOrders) {
        this.dataOrders = dataOrders;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
