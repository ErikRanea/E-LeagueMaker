package Modelo;

import java.time.LocalDate;
import java.util.List;

public class Jugador {
    private int codigo;
    private String nombre;
    private String apellido;
    private String rol;
    private int salario;
    private String nacionalidad;
    private LocalDate fechaNacimiento;
    private String nickname;
    private Equipo equipo;

    // Controlador

    public Jugador(int codigo, String nombre, String apellido, String rol, int salario, String nacionalidad, LocalDate fechaNacimiento, String nickname, Equipo equipo) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.apellido = apellido;
        this.rol = rol;
        this.salario = salario;
        this.nacionalidad = nacionalidad;
        this.fechaNacimiento = fechaNacimiento;
        this.nickname = nickname;
        this.equipo = equipo;
    }

    // Getters & Setters
    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
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
