package com.example.android.karta.Models.Response;

import com.example.android.karta.Models.Commerce;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by Root on 14/07/2017.
 */

public class CommerceResponse {

    @SerializedName("data")
    ArrayList<Commerce> dataCommerces;

    int code;

    public CommerceResponse(){}

    public CommerceResponse(ArrayList<Commerce> dataCommerces, int code) {
        this.dataCommerces = dataCommerces;
        this.code = code;
    }

    public ArrayList<Commerce> getDataCommerces() {
        return dataCommerces;
    }

    public void setDataCommerces(ArrayList<Commerce> dataCommerces) {
        this.dataCommerces = dataCommerces;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
