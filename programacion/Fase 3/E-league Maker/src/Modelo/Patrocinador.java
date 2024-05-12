package Modelo;

import java.util.List;

public class Patrocinador {
    private int codPatrocinador;
    private String nombre;
    private Equipo equipo;
    //Constructor

    public Patrocinador(int codPatrocinador, String nombre, Equipo equipo) {
        this.codPatrocinador = codPatrocinador;
        this.nombre = nombre;
        this.equipo = equipo;
    }

    // Getters & Setters
    public int getCodPatrocinador() {
        return codPatrocinador;
    }

    public void setCodPatrocinador(int codPatrocinador) {
        this.codPatrocinador = codPatrocinador;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Equipo getEquipo() {
        return equipo;
    }

    public void setEquipo(Equipo equipo) {
        this.equipo = equipo;
    }
}
