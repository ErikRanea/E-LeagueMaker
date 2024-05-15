package Modelo;

import java.util.List;

public class Jornada {
    private int cod;
    private int nJornada;
    private Competicion competicion;

    // Constructor
    public Jornada(int cod, int nJornada, Competicion competicion) {
        this.cod = cod;
        this.nJornada = nJornada;
        this.competicion = competicion;
    }

    // Getters & Setters
    public int getCod() {
        return cod;
    }

    public void setCod(int cod) {
        this.cod = cod;
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
