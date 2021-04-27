package com.androidavanzado.bookingaitorretrofit.beans;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Usuario {


    private String email, password;
    private int id;

    public Usuario(){}

    @Override
    public String toString() {
        return "Usuario{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", id=" + id +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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


}
