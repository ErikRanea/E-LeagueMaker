package Modelo;

import java.time.LocalDate;
import java.util.List;

public class Equipo {
    private int cod;
    private String nombre;
    private LocalDate fechaFundacion;
    private List<Patrocinador> listaPatrocinadores;
    private List<Staff> listaStaffs;
    private List<Jugador> listaJugadores;

    // Constructor


    public Equipo(int cod, String nombre, LocalDate fechaFundacion, List<Patrocinador> listaPatrocinadores, List<Staff> listaStaffs, List<Jugador> listaJugadores) {
        this.cod = cod;
        this.nombre = nombre;
        this.fechaFundacion = fechaFundacion;
        this.listaPatrocinadores = listaPatrocinadores;
        this.listaStaffs = listaStaffs;
        this.listaJugadores = listaJugadores;
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

    public List<Patrocinador> getListaPatrocinadores() {
        return listaPatrocinadores;
    }

    public void setListaPatrocinadores(List<Patrocinador> listaPatrocinadores) {
        this.listaPatrocinadores = listaPatrocinadores;
    }

    public List<Staff> getListaStaffs() {
        return listaStaffs;
    }

    public void setListaStaffs(List<Staff> listaStaffs) {
        this.listaStaffs = listaStaffs;
    }

    public List<Jugador> getListaJugadores() {
        return listaJugadores;
    }

    public void setListaJugadores(List<Jugador> listaJugadores) {
        this.listaJugadores = listaJugadores;
    }
}
