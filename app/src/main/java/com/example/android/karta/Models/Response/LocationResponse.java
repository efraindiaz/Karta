package com.example.android.karta.Models.Response;

import com.example.android.karta.Models.LocationU;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by Root on 07/08/2017.
 */

public class LocationResponse {

    @SerializedName("data")
    ArrayList<LocationU> dataLocationU;

    int code;

    public LocationResponse(){}

    public LocationResponse(ArrayList<LocationU> dataLocationUs, int code) {
        this.dataLocationU = dataLocationUs;
        this.code = code;
    }

    public ArrayList<LocationU> getDataLocationUs() {
        return dataLocationU;
    }

    public void setDataLocationUs(ArrayList<LocationU> dataLocationUs) {
        this.dataLocationU = dataLocationUs;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
