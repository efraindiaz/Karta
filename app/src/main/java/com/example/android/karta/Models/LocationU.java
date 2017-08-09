package com.example.android.karta.Models;

/**
 * Created by Root on 12/07/2017.
 */

public class LocationU {

    int id_consumer_address;
    int id_info_user_consumer;
    String name;
    Double latitude;
    Double longitude;
    String references;
    String manual_address;

    public LocationU(){

    }

    public LocationU(int id_consumer_address, int id_info_user_consumer, String name, Double latitude, Double longitude, String references, String manual_address) {
        this.id_consumer_address = id_consumer_address;
        this.id_info_user_consumer = id_info_user_consumer;
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.references = references;
        this.manual_address = manual_address;
    }

    public int getId_consumer_address() {
        return id_consumer_address;
    }

    public void setId_consumer_address(int id_consumer_address) {
        this.id_consumer_address = id_consumer_address;
    }

    public int getId_info_user_consumer() {
        return id_info_user_consumer;
    }

    public void setId_info_user_consumer(int id_info_user_consumer) {
        this.id_info_user_consumer = id_info_user_consumer;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public String getReferences() {
        return references;
    }

    public void setReferences(String references) {
        this.references = references;
    }

    public String getManual_address() {
        return manual_address;
    }

    public void setManual_address(String manual_address) {
        this.manual_address = manual_address;
    }
}
