package com.example.android.karta.Models;

/**
 * Created by Root on 12/07/2017.
 */

public class User {


    int id_info_user_consumer;
    String name;
    String last_name;
    int phone;
    String email;
    String password;
    String token;

    public User(){}

    public User(int id_info_user_consumer, String name, String last_name, int phone, String email, String password, String token) {
        this.id_info_user_consumer = id_info_user_consumer;
        this.name = name;
        this.last_name = last_name;
        this.phone = phone;
        this.email = email;
        this.password = password;
        this.token = token;
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

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
