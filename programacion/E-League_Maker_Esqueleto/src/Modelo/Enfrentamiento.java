package Modelo;

import java.time.LocalTime;
import java.util.List;

public class Enfrentamiento {
    private int cod;
    private LocalTime hora;
    private boolean ganaLocal;
    private Equipo equipoLocal;
    private Equipo equipoVisitante;
    private Jornada jornada;

    // Constructor
    public Enfrentamiento(int cod, LocalTime hora, boolean ganaLocal, Equipo equipoLocal, Equipo equipoVisitante, Jornada jornada) {
        this.cod = cod;
        this.hora = hora;
        this.ganaLocal = ganaLocal;
        this.equipoLocal = equipoLocal;
        this.equipoVisitante = equipoVisitante;
        this.jornada = jornada;
    }

    public En

    // Getters & Setters
    public int getCod() {
        return cod;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }

    public LocalTime getHora() {
        return hora;
    }

    public void setHora(LocalTime hora) {
        this.hora = hora;
    }

    public boolean isGanaLocal() {
        return ganaLocal;
    }

    public void setGanaLocal(boolean ganaLocal) {
        this.ganaLocal = ganaLocal;
    }

    public Equipo getEquipoLocal() {
        return equipoLocal;
    }

    public void setEquipoLocal(Equipo equipoLocal) {
        this.equipoLocal = equipoLocal;
    }

    public Equipo getEquipoVisitante() {
        return equipoVisitante;
    }

    public void setEquipoVisitante(Equipo equipoVisitante) {
        this.equipoVisitante = equipoVisitante;
    }

    public Modelo.Jornada getJornada() {
        return jornada;
    }

    public void setJornada(Modelo.Jornada jornada) {
        jornada = jornada;
    }
}
