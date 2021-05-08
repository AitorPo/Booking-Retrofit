package com.androidavanzado.bookingaitorretrofit.beans;

import java.sql.Date;
import java.time.LocalDate;

public class Reserva {

    private int idReserva, idUsuario, idHabitacion;
    private Date in;
    private Date out;

    public Reserva() {
    }



    public Reserva(int idUsuario, int idHabitacion, Date in, Date out) {
        this.idUsuario = idUsuario;
        this.idHabitacion = idHabitacion;
        this.in = in;
        this.out = out;
    }

    @Override
    public String toString() {
        return "Reserva{" +
                "idReserva=" + idReserva +
                ", idUsuario=" + idUsuario +
                ", idHabitacion=" + idHabitacion +
                ", in=" + in +
                ", out=" + out +
                '}';
    }

    public int getIdReserva() {
        return idReserva;
    }

    public void setIdReserva(int idReserva) {
        this.idReserva = idReserva;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getIdHabitacion() {
        return idHabitacion;
    }

    public void setIdHabitacion(int idHabitacion) {
        this.idHabitacion = idHabitacion;
    }

    public Date getIn() {
        return in;
    }

    public void setIn(Date in) {
        this.in = in;
    }

    public Date getOut() {
        return out;
    }

    public void setOut(Date out) {
        this.out = out;
    }
}
