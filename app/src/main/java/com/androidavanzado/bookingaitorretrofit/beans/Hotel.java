package com.androidavanzado.bookingaitorretrofit.beans;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;

@Entity(tableName = "hotel")

public class Hotel {

    @PrimaryKey(autoGenerate = true)

    private int idHotel;

    private int puntuacion;

    private int numHabitaciones;

    private int numReservas;

    private int idCiudad;

    private String nombre;

    private String foto;

    private String direccion;

    private String descripcion;


    @Ignore
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
                ", ciudad='" + idCiudad + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", destacado=" + destacado +
                '}';
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

    public int getIdCiudad() {
        return idCiudad;
    }

    public void setIdCiudad(int idCiudad) {
        this.idCiudad = idCiudad;
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
