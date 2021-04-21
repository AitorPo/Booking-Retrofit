package com.androidavanzado.bookingaitorretrofit.beans;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Hotel {
    
    private int idHotel, puntuacion, numHabitaciones, numReservas, ciudad;
    private String nombre, foto, direccion, descripcion, nombreCiudad;
    private boolean destacado;

    public Hotel(String nombre){
        this.nombre = nombre;
    }

    public Hotel(){}

    @Override
    public String toString() {
        return "Hotel{" +
                "idHotel=" + idHotel +
                ", puntuacion=" + puntuacion +
                ", numHabitaciones=" + numHabitaciones +
                ", numReservas=" + numReservas +
                ", nombre='" + nombre + '\'' +
                ", foto='" + foto + '\'' +
                ", direccion='" + direccion + '\'' +
                ", ciudad='" + ciudad + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", destacado=" + destacado +
                '}';
    }

    public String getNombreCiudad() {
        return nombreCiudad;
    }

    public void setNombreCiudad(String nombreCiudad) {
        this.nombreCiudad = nombreCiudad;
    }

    public boolean isDestacado() {
        return destacado;
    }

    public int getIdHotel() {
        return idHotel;
    }

    public void setIdHotel(int idHotel) {
        this.idHotel = idHotel;
    }

    public int getPuntuacion() {
        return puntuacion;
    }

    public void setPuntuacion(int puntuacion) {
        this.puntuacion = puntuacion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public int getCiudad() {
        return ciudad;
    }

    public void setCiudad(int ciudad) {
        this.ciudad = ciudad;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public int getNumHabitaciones() { return numHabitaciones; }

    public void setNumHabitaciones(int numHabitaciones) { this.numHabitaciones = numHabitaciones; }

    public int getNumReservas() { return numReservas; }

    public void setNumReservas(int numReservas) { this.numReservas = numReservas; }

    public boolean getDestacado() { return destacado; }

    public void setDestacado(boolean destacado) { this.destacado = destacado; }


}
