package com.example.android.karta.API;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Root on 12/07/2017.
 */

public class API {

    public static final String BASE_URL = "http://192.168.0.6/karta-ws/public/api/";

    private static Retrofit retrofit = null;

    public static Retrofit getRetrofit() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return  retrofit;
    }
}
