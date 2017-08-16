package com.example.android.karta.Models.Response;

import com.example.android.karta.Models.Product;

import java.util.ArrayList;

/**
 * Created by Root on 09/08/2017.
 */

public class CartResponse {

    int id_commerce;
    double sub_total;
    double delivery_price;
    double total;
    int count_products;

    ArrayList<Product> dataProducts;

    public CartResponse(){}

    public CartResponse(int id_commerce, double sub_total, double delivery_price, double total, int count_products, ArrayList<Product> dataProducts) {
        this.id_commerce = id_commerce;
        this.sub_total = sub_total;
        this.delivery_price = delivery_price;
        this.total = total;
        this.count_products = count_products;
        this.dataProducts = dataProducts;
    }

    public int getId_commerce() {
        return id_commerce;
    }

    public void setId_commerce(int id_commerce) {
        this.id_commerce = id_commerce;
    }

    public double getSub_total() {
        return sub_total;
    }

    public void setSub_total(double sub_total) {
        this.sub_total = sub_total;
    }

    public double getDelivery_price() {
        return delivery_price;
    }

    public void setDelivery_price(double delivery_price) {
        this.delivery_price = delivery_price;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public int getCount_products() {
        return count_products;
    }

    public void setCount_products(int count_products) {
        this.count_products = count_products;
    }

    public ArrayList<Product> getDataProducts() {
        return dataProducts;
    }

    public void setDataProducts(ArrayList<Product> dataProducts) {
        this.dataProducts = dataProducts;
    }
}
