package Modelo;

import java.time.LocalDate;

public class Juego {
    private int codJuego;
    private String nombre;
    private String desarrolladora;
    private LocalDate fechaLanzamiento;

    // Constructores
    public Juego(int codJuego, String nombre, String desarrolladora, LocalDate fechaLanzamiento) {
        this.codJuego = codJuego;
        this.nombre = nombre;
        this.desarrolladora = desarrolladora;
        this.fechaLanzamiento = fechaLanzamiento;
    }


    // Getters & Setters
    public int getCodJuego() {
        return codJuego;
    }

    public void setCodJuego(int codJuego) {
        this.codJuego = codJuego;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDesarrolladora() {
        return desarrolladora;
    }

    public void setDesarrolladora(String desarrolladora) {
        this.desarrolladora = desarrolladora;
    }

    public LocalDate getFechaLanzamiento() {
        return fechaLanzamiento;
    }

    public void setFechaLanzamiento(LocalDate fechaLanzamiento) {
        this.fechaLanzamiento = fechaLanzamiento;
    }
}
