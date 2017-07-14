package com.example.android.karta.Models;

/**
 * Created by Root on 12/07/2017.
 */

public class Commerce {

    int id_commerce;
    String name;
    int phone;
    String state;
    String city;
    String colony;
    String postal_code;
    String address;
    String url_img;

    public Commerce(){

    }

    public Commerce(int id_commerce, String name, int phone, String state, String city, String colony, String postal_code, String address, String url_img) {
        this.id_commerce = id_commerce;
        this.name = name;
        this.phone = phone;
        this.state = state;
        this.city = city;
        this.colony = colony;
        this.postal_code = postal_code;
        this.address = address;
        this.url_img = url_img;
    }

    public int getId_commerce() {
        return id_commerce;
    }

    public void setId_commerce(int id_commerce) {
        this.id_commerce = id_commerce;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getColony() {
        return colony;
    }

    public void setColony(String colony) {
        this.colony = colony;
    }

    public String getPostal_code() {
        return postal_code;
    }

    public void setPostal_code(String postal_code) {
        this.postal_code = postal_code;
    }

    public String getAddress() {
        return address;
    }

    public void setAdress(String adress) {
        this.address = adress;
    }

    public String getUrl_img() {
        return url_img;
    }

    public void setUrl_img(String url_img) {
        this.url_img = url_img;
    }
}
