package Modelo;

import java.time.LocalDate;

public class Equipo {
    private int cod;
    private String nombre;
    private LocalDate fechaFundacion;

    // Constructor
    public Equipo(int cod, String nombre, LocalDate fechaFundacion) {
        this.cod = cod;
        this.nombre = nombre;
        this.fechaFundacion = fechaFundacion;
    }

    // Getters & Setters
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

    public LocalDate getFechaFundacion() {
        return fechaFundacion;
    }

    public void setFechaFundacion(LocalDate fechaFundacion) {
        this.fechaFundacion = fechaFundacion;
    }
}
