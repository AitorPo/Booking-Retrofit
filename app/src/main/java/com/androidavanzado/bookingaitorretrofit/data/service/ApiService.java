package com.androidavanzado.bookingaitorretrofit.data.service;

import com.androidavanzado.bookingaitorretrofit.beans.Ciudad;
import com.androidavanzado.bookingaitorretrofit.beans.Habitacion;
import com.androidavanzado.bookingaitorretrofit.beans.Hotel;
import com.androidavanzado.bookingaitorretrofit.beans.Reserva;
import com.androidavanzado.bookingaitorretrofit.beans.Usuario;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.FieldMap;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

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

    @GET("BookingAitor/Controller")
    Call<ArrayList<Reserva>> getMyReservas(
            @QueryMap Map<String, String> params);

    @GET("BookingAitor/Controller")
    Call<Reserva> getReserva(
            @QueryMap Map<String, String> params);

    @POST("BookingAitor/Controller")
    Call<Void> reservar(
            @QueryMap Map<String, String> params);

    /*@DELETE()
    Call<Void> deleteReserva(@Url String url,
           @QueryMap Map<String, String> params);*/
    
    @GET()
    Call<Void> deleteReserva(@Url String url,
                             @QueryMap Map<String, String> params);

}
