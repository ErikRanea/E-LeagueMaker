package Modelo;

import java.util.List;

public class Staff {
    private int cod;
    private String nombre;
    private String apellido;
    private String puesto;
    private Equipo equipo;
    // Constructor
    public Staff(int cod, String nombre, String apellido, String puesto, Equipo equipo) {
        this.cod = cod;
        this.nombre = nombre;
        this.apellido = apellido;
        this.puesto = puesto;
        this.equipo = equipo;
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

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getPuesto() {
        return puesto;
    }

    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }

    public Equipo getEquipo() {
        return equipo;
    }

    public void setEquipo(Equipo equipo) {
        this.equipo = equipo;
    }
}
