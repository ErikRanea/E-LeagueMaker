package Modelo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Competicion {
    private int cod;
    private String nombre;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private boolean estadoAbierto;
    private Juego juego;
    private List<Jornada> listaJornada;

    // Constructor
    public Competicion(int cod, String nombre, LocalDate fechaInicio, LocalDate fechaFin, boolean estadoAbierto, Juego juego, List<Jornada> listaJornada) {
        this.cod = cod;
        this.nombre = nombre;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.estadoAbierto = estadoAbierto;
        this.juego = juego;
        this.listaJornada = listaJornada;
    }
    public Competicion()
    {
        juego = new Juego();
        listaJornada = new ArrayList<>();
    }

    // Getter & Setters

    public int getCod() {
        return cod;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(LocalDate fechaFin) {
        this.fechaFin = fechaFin;
    }

    public boolean isEstadoAbierto() {
        return estadoAbierto;
    }

    public int isEstadoAbiertoInt()
    {
        int abierto = 0;
        if(estadoAbierto)
            abierto = 1;
        return abierto;
    }
    public void setEstadoAbierto(boolean estadoAbierto) {
        this.estadoAbierto = estadoAbierto;
    }

    public Modelo.Juego getJuego() {
        return juego;
    }

    public void setJuego(Modelo.Juego juego) {
        this.juego = juego;
    }

    public List<Jornada> getListaJornada() {
        return listaJornada;
    }

    public void setListaJornada(List<Jornada> listaJornada) {
        this.listaJornada = listaJornada;
    }
}
