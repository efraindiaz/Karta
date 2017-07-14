package com.example.android.karta.Models.Response;

import com.example.android.karta.Models.Product;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by Root on 14/07/2017.
 */

public class ProductResponse {

        @SerializedName("data")
        ArrayList<Product> dataProducts;

        int code;

        public ProductResponse(){}

        public ProductResponse(ArrayList<Product> products, int code) {
            this.dataProducts = products;
            this.code = code;
        }

        public ArrayList<Product> getProducts() {
            return dataProducts;
        }

        public void setProducts(ArrayList<Product> products) {
            this.dataProducts = products;
        }

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }
}
