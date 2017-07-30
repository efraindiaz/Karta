package com.example.android.karta.Models.Response;

import com.example.android.karta.Models.User;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by Root on 25/07/2017.
 */

public class UserResponse {

    @SerializedName("data")
    ArrayList<User> dataUser;

    int code;

    public  UserResponse(){}

    public UserResponse(ArrayList<User> dataUser, int code) {
        this.dataUser = dataUser;
        this.code = code;
    }

    public ArrayList<User> getDataUser() {
        return dataUser;
    }

    public void setDataUser(ArrayList<User> dataUser) {
        this.dataUser = dataUser;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
