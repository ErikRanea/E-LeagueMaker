package Modelo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Equipo {
    private int cod;
    private String nombre;
    private LocalDate fechaFundacion;
    private ArrayList<Patrocinador> listaPatrocinadores;
    private ArrayList<Staff> listaStaffs;
    private ArrayList<Jugador> listaJugadores;

    // Constructor


    public Equipo(int cod, String nombre, LocalDate fechaFundacion, ArrayList<Patrocinador> listaPatrocinadores, ArrayList<Staff> listaStaffs, ArrayList<Jugador> listaJugadores) {
        this.cod = cod;
        this.nombre = nombre;
        this.fechaFundacion = fechaFundacion;
        this.listaPatrocinadores = listaPatrocinadores;
        this.listaStaffs = listaStaffs;
        this.listaJugadores = listaJugadores;
    }
    public Equipo()
    {listaJugadores = new ArrayList<>();listaPatrocinadores = new ArrayList<>();listaStaffs = new ArrayList<>();}
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

    public ArrayList<Patrocinador> getListaPatrocinadores() {
        return listaPatrocinadores;
    }

    public void setListaPatrocinadores(ArrayList<Patrocinador> listaPatrocinadores) {
        this.listaPatrocinadores = listaPatrocinadores;
    }

    public ArrayList<Staff> getListaStaffs() {
        return listaStaffs;
    }

    public void setListaStaffs(ArrayList<Staff> listaStaffs) {
        this.listaStaffs = listaStaffs;
    }

    public ArrayList<Jugador> getListaJugadores() {
        return listaJugadores;
    }

    public void setListaJugadores(ArrayList<Jugador> listaJugadores) {
        this.listaJugadores = listaJugadores;
    }
}
