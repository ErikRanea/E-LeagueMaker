package Modelo;

import java.time.LocalDate;
import java.util.List;

public class Competicion {
    private int codCompeticion;
    private String nombre;
    private LocalDate fechaIncio;
    private LocalDate fechaFin;
    private boolean estadoAbierto;
    private Juego juego;

    // Constructor
    public Competicion(int codCompeticion, String nombre, LocalDate fechaIncio, LocalDate fechaFin, boolean estadoAbierto, Juego juego) {
        this.codCompeticion = codCompeticion;
        this.nombre = nombre;
        this.fechaIncio = fechaIncio;
        this.fechaFin = fechaFin;
        this.estadoAbierto = estadoAbierto;
        this.juego = juego;
    }

    // Getter & Setters
    public int getCodCompeticion() {
        return codCompeticion;
    }

    public void setCodCompeticion(int codCompeticion) {
        this.codCompeticion = codCompeticion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public LocalDate getFechaIncio() {
        return fechaIncio;
    }

    public void setFechaIncio(LocalDate fechaIncio) {
        this.fechaIncio = fechaIncio;
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

    public void setEstadoAbierto(boolean estadoAbierto) {
        this.estadoAbierto = estadoAbierto;
    }

    public Modelo.Juego getJuego() {
        return juego;
    }

    public void setJuego(Modelo.Juego juego) {
        juego = juego;
    }
}
