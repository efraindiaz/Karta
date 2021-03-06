package com.example.android.karta.Models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Root on 12/07/2017.
 */

public class Product implements Parcelable{

    int id_product;
    int id_commerce;
    String name;
    String description;
    Double price;
    int id_product_type;
    String image;
    int quantity;

    public Product(){

    }


    public Product(int id_product, int id_commerce, String name, String description, Double price, int id_product_type, String image) {
        this.id_product = id_product;
        this.id_commerce = id_commerce;
        this.name = name;
        this.description = description;
        this.price = price;
        this.id_product_type = id_product_type;
        this.image = image;
    }

    public Product(int id_product, String name, Double price, String image, int quantity) {
        this.id_product = id_product;
        this.name = name;
        this.price = price;
        this.image = image;
        this.quantity = quantity;
    }

    protected Product(Parcel in) {
        id_product = in.readInt();
        id_commerce = in.readInt();
        name = in.readString();
        description = in.readString();
        id_product_type = in.readInt();
        image = in.readString();
        quantity = in.readInt();
    }

    public static final Creator<Product> CREATOR = new Creator<Product>() {
        @Override
        public Product createFromParcel(Parcel in) {
            return new Product(in);
        }

        @Override
        public Product[] newArray(int size) {
            return new Product[size];
        }
    };

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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
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

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id_product);
        dest.writeInt(id_commerce);
        dest.writeString(name);
        dest.writeString(description);
        dest.writeInt(id_product_type);
        dest.writeString(image);
        dest.writeInt(quantity);
    }
}
