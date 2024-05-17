package Modelo;

import java.util.List;

public class Patrocinador {
    private int cod;
    private String nombre;
    private Equipo equipo;
    //Constructor

    public Patrocinador(int cod, String nombre, Equipo equipo) {
        this.cod = cod;
        this.nombre = nombre;
        this.equipo = equipo;
    }

    public Patrocinador(){equipo = new Equipo();}

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

    public Equipo getEquipo() {
        return equipo;
    }

    public void setEquipo(Equipo equipo) {
        this.equipo = equipo;
    }
}
