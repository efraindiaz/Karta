package com.example.android.karta.Models;

/**
 * Created by Root on 12/07/2017.
 */

public class Order {

    int id_order_consumer;
    int id_commerce;
    int id_info_user_consumer;
    int id_consumer_address;
    String date;
    Double total;
    int id_status_order;


    String status_order;
    String status_color;
    String name;

    public Order(){}

    public Order(int id_order_consumer, int id_commerce, int id_info_user_consumer, int id_consumer_address, String date, Double total, int id_status_order, String status_order, String status_color, String name) {
        this.id_order_consumer = id_order_consumer;
        this.id_commerce = id_commerce;
        this.id_info_user_consumer = id_info_user_consumer;
        this.id_consumer_address = id_consumer_address;
        this.date = date;
        this.total = total;
        this.id_status_order = id_status_order;
        this.status_order = status_order;
        this.status_color = status_color;
        this.name = name;
    }

    public int getId_order_consumer() {
        return id_order_consumer;
    }

    public void setId_order_consumer(int id_order_consumer) {
        this.id_order_consumer = id_order_consumer;
    }

    public int getId_commerce() {
        return id_commerce;
    }

    public void setId_commerce(int id_commerce) {
        this.id_commerce = id_commerce;
    }

    public int getId_info_user_consumer() {
        return id_info_user_consumer;
    }

    public void setId_info_user_consumer(int id_info_user_consumer) {
        this.id_info_user_consumer = id_info_user_consumer;
    }

    public int getId_consumer_address() {
        return id_consumer_address;
    }

    public void setId_consumer_address(int id_consumer_address) {
        this.id_consumer_address = id_consumer_address;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public int getId_status_order() {
        return id_status_order;
    }

    public void setId_status_order(int id_status_order) {
        this.id_status_order = id_status_order;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus_order() {
        return status_order;
    }

    public void setStatus_order(String status_order) {
        this.status_order = status_order;
    }

    public String getStatus_color() {
        return status_color;
    }

    public void setStatus_color(String status_color) {
        this.status_color = status_color;
    }
}
