package com.example.android.karta.Models;

/**
 * Created by Root on 14/08/2017.
 */

public class ProductOrder {

    int id_order_consumer;
    int id_order_detail;
    String name;
    Double price;
    String image;


    public ProductOrder(){}

    public ProductOrder(int id_order_consumer, int id_order_detail, String name, Double price, String image) {
        this.id_order_consumer = id_order_consumer;
        this.id_order_detail = id_order_detail;
        this.name = name;
        this.price = price;
        this.image = image;
    }

    public int getId_order_consumer() {
        return id_order_consumer;
    }

    public void setId_order_consumer(int id_order_consumer) {
        this.id_order_consumer = id_order_consumer;
    }

    public int getId_order_detail() {
        return id_order_detail;
    }

    public void setId_order_detail(int id_order_detail) {
        this.id_order_detail = id_order_detail;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
