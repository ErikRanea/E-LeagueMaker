package Modelo;

public class Puntos_Equipos {
    private Equipo equipo;
    private Competicion competicion;
    private int puntos;

    // Constructor
    public Puntos_Equipos(Equipo equipo, Competicion competicion, int puntos) {
        this.equipo = equipo;
        this.competicion = competicion;
        this.puntos = puntos;
    }

    // Getters & Setters
    public Equipo getEquipo() {
        return equipo;
    }

    public void setEquipo(Equipo equipo) {
        this.equipo = equipo;
    }

    public Competicion getCompeticion() {
        return competicion;
    }

    public void setCompeticion(Competicion competicion) {
        this.competicion = competicion;
    }

    public int getPuntos() {
        return puntos;
    }

    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }
}
