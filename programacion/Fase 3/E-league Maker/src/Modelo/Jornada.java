package Modelo;

import java.util.List;

public class Jornada {
    private int codJornada;
    private int nJornada;
    private Competicion competicion;

    // Constructor
    public Jornada(int codJornada, int nJornada, Competicion competicion) {
        this.codJornada= codJornada;
        this.nJornada = nJornada;
        this.competicion = competicion;
    }

    // Getters & Setters
    public int getCodJornada() {
        return codJornada;
    }

    public void setCodJornada(int codJornada) {
        this.codJornada = codJornada;
    }

    public int getnJornada() {
        return nJornada;
    }

    public void setnJornada(int nJornada) {
        this.nJornada = nJornada;
    }

    public Modelo.Competicion getCompeticion() {
        return competicion;
    }

    public void setCompeticion(Modelo.Competicion competicion) {
        competicion = competicion;
    }
}
