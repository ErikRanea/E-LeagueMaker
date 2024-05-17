package Modelo;

import java.time.LocalDate;
import java.util.List;

public class Jugador {
    private int cod;
    private String nombre;
    private String apellido;
    private String rol;
    private int salario;
    private String nacionalidad;
    private LocalDate fechaNacimiento;
    private String nickname;
    private Equipo equipo;

    // Controlador

    public Jugador(int cod, String nombre, String apellido, String rol, int salario, String nacionalidad, LocalDate fechaNacimiento, String nickname, Equipo equipo) {
        this.cod = cod;
        this.nombre = nombre;
        this.apellido = apellido;
        this.rol = rol;
        this.salario = salario;
        this.nacionalidad = nacionalidad;
        this.fechaNacimiento = fechaNacimiento;
        this.nickname = nickname;
        this.equipo = equipo;
    }

    /**
     * Este constructor permite crear instancias del objeto sin necesidad de no tener ningún dato
     * @author Erik
     *
     */
    public Jugador(){equipo = new Equipo();}

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

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public int getSalario() {
        return salario;
    }

    public void setSalario(int salario) {
        this.salario = salario;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Equipo getEquipo() {
        return equipo;
    }

    public void setEquipo(Equipo equipo) {
        this.equipo = equipo;
    }
}
