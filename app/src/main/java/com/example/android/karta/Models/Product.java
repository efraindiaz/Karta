package com.example.android.karta.Models;

/**
 * Created by Root on 12/07/2017.
 */

public class Product {

    int id_product;
    int id_commerce;
    String name;
    String description;
    String price;
    int id_product_type;
    String image;

    public Product(){

    }


    public Product(int id_product, int id_commerce, String name, String description, String price, int id_product_type, String image) {
        this.id_product = id_product;
        this.id_commerce = id_commerce;
        this.name = name;
        this.description = description;
        this.price = price;
        this.id_product_type = id_product_type;
        this.image = image;
    }

    public int getId_product() {
        return id_product;
    }

    public void setId_product(int id_product) {
        this.id_product = id_product;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public int getId_product_type() {
        return id_product_type;
    }

    public void setId_product_type(int id_product_type) {
        this.id_product_type = id_product_type;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
