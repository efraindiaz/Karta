package com.example.android.karta.Models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Root on 10/08/2017.
 */

public class ProdTest implements Parcelable{

    int id_product;
    int id_commerce;
    String name;
    String description;
    double price;
    int id_product_type;
    String image;
    int quantity;

    public ProdTest(){

    }

    public ProdTest(int id_product, String name, double price, String image, int quantity) {
        this.id_product = id_product;
        this.name = name;
        this.price = price;
        this.image = image;
        this.quantity = quantity;
    }

    protected ProdTest(Parcel in) {
        id_product = in.readInt();
        id_commerce = in.readInt();
        name = in.readString();
        description = in.readString();
        price = in.readDouble();
        id_product_type = in.readInt();
        image = in.readString();
        quantity = in.readInt();
    }

    public static final Creator<ProdTest> CREATOR = new Creator<ProdTest>() {
        @Override
        public ProdTest createFromParcel(Parcel in) {
            return new ProdTest(in);
        }

        @Override
        public ProdTest[] newArray(int size) {
            return new ProdTest[size];
        }
    };

    public int getId_product() {
        return id_product;
    }

    public void setId_product(int id_product) {
        this.id_product = id_product;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
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
        dest.writeDouble(price);
        dest.writeInt(id_product_type);
        dest.writeString(image);
        dest.writeInt(quantity);
    }
}
