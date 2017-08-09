package com.example.android.karta.Models.Response;

/**
 * Created by Root on 08/08/2017.
 */

public class GenericResponse {

    int code;
    String data;

    public GenericResponse(){}

    public GenericResponse(int code, String data) {
        this.code = code;
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
