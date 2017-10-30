package com.example.android.karta.Models;

/**
 * Created by Root on 29/10/2017.
 */

public class ProdOrd {

    int id_product;
    int quantity;

    public ProdOrd(){}

    public ProdOrd(int id_product, int quantity) {
        this.id_product = id_product;
        this.quantity = quantity;
    }

    public int getId_product() {
        return id_product;
    }

    public void setId_product(int id_product) {
        this.id_product = id_product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
