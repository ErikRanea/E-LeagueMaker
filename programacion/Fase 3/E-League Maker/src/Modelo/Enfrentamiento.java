package Modelo;

import java.time.LocalTime;
import java.util.List;

public class Enfrentamiento {
    private int cod;
    private LocalTime hora;
    private boolean ganaLocal;
    private List<Equipo> listaEquiposLocales;
    private List<Equipo> listaEquiposVisitantes;
    private Jornada jornada;

    // Constructor
    public Enfrentamiento(int cod, LocalTime hora, boolean ganaLocal, List<Equipo> listaEquiposLocales, List<Equipo> listaEquiposVisitantes, Jornada jornada) {
        this.cod = cod;
        this.hora = hora;
        this.ganaLocal = ganaLocal;
        this.listaEquiposLocales = listaEquiposLocales;
        this.listaEquiposVisitantes = listaEquiposVisitantes;
        this.jornada = jornada;
    }

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

    public List<Equipo> getListaEquiposLocales() {
        return listaEquiposLocales;
    }

    public void setListaEquiposLocales(List<Equipo> listaEquiposLocales) {
        this.listaEquiposLocales = listaEquiposLocales;
    }

    public List<Equipo> getListaEquiposVisitantes() {
        return listaEquiposVisitantes;
    }

    public void setListaEquiposVisitantes(List<Equipo> listaEquiposVisitantes) {
        this.listaEquiposVisitantes = listaEquiposVisitantes;
    }

    public Modelo.Jornada getJornada() {
        return jornada;
    }

    public void setJornada(Modelo.Jornada jornada) {
        jornada = jornada;
    }
}
