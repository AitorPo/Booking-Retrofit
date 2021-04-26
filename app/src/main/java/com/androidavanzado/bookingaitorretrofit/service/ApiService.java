package com.androidavanzado.bookingaitorretrofit.service;

import com.androidavanzado.bookingaitorretrofit.beans.Ciudad;
import com.androidavanzado.bookingaitorretrofit.beans.Habitacion;
import com.androidavanzado.bookingaitorretrofit.beans.Hotel;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public interface ApiService {

    @GET("/BookingAitor/Controller")
    Call<ArrayList<Hotel>> getAllHotels(
            @QueryMap Map<String, String> params);

    @GET("/BookingAitor/Controller")
    Call<Hotel> getHotelById(
            @QueryMap Map<String, String> params);

    @GET("/BookingAitor/Controller")
    Call<ArrayList<Habitacion>> getAllHabitaciones(
            @QueryMap Map<String, String> params);

    @GET("/BookingAitor/Controller")
    Call<Habitacion> getHabitacionById(
            @QueryMap Map<String, String> params);

    @GET("BookingAitor/Controller")
    Call<Ciudad> getCiudadById (
            @QueryMap Map<String, String> params);

    @GET("BookingAitor/Controller")
    Call<ArrayList<Ciudad>> getAllCiudades (
            @QueryMap Map<String, String> params);

    @GET("BookingAitor/Controller")
    Call<ArrayList<Hotel>> getHotelesByCiudad (
            @QueryMap Map<String, String> params);


}
