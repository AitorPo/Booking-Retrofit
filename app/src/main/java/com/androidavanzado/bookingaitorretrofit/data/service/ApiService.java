package com.androidavanzado.bookingaitorretrofit.data.service;

import com.androidavanzado.bookingaitorretrofit.beans.Ciudad;
import com.androidavanzado.bookingaitorretrofit.beans.Habitacion;
import com.androidavanzado.bookingaitorretrofit.beans.Hotel;
import com.androidavanzado.bookingaitorretrofit.beans.Usuario;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

public interface ApiService {

    @GET("/BookingAitor/Controller")
    Call<List<Hotel>> getAllHotels(
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
    Call<Ciudad> getCiudadById(
            @QueryMap Map<String, String> params);

    @GET("BookingAitor/Controller")
    Call<ArrayList<Ciudad>> getAllCiudades(
            @QueryMap Map<String, String> params);

    @GET("BookingAitor/Controller")
    Call<ArrayList<Hotel>> getHotelesByCiudad(
            @QueryMap Map<String, String> params);

    @GET("BookingAitor/Controller")
    Call<ArrayList<Hotel>> getHotelesByDestacado(
            @QueryMap Map<String, String> params);

    @GET("BookingAitor/Controller")
    Call<ArrayList<Hotel>> getHotelesByPuntuacion(
            @QueryMap Map<String, String> params);

    @GET("BookingAitor/Controller")
    Call<ArrayList<Hotel>> getHotelesByReservas(
            @QueryMap Map<String, String> params);

    @GET("BookingAitor/Controller")
    Call<ArrayList<Habitacion>> getHabitacionByPrecioAsc(
            @QueryMap Map<String, String> params);

    @GET("BookingAitor/Controller")
    Call<ArrayList<Habitacion>> getHabitacionByPrecioDesc(
            @QueryMap Map<String, String> params);

    @GET("BookingAitor/Controller")
    Call<ArrayList<Usuario>> login(
            @QueryMap Map<String, String> params);


}
