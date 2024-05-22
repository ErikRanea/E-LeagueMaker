/**
 * Esta clase se encarga de controlar los diferentes controladores que controlan la vista.
 * A su vez en un nexo de diferentes metodos de la vista.
 * @author Erik
 * @version 1.0
 */
package Controlador.ControladoresVista;

import Controlador.ControladorPrincipal;
import Modelo.*;

import javax.swing.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class ControladorVista {

    /**
     * Con la siguiente variable buscamos relacionar el controlador superior, el controlador principal, con este
     * controlador.
     *
     */
    private ControladorPrincipal cp;

    /**
     * Los siguientes atributos serán objetos de cada uno de los controladores de la vista
     * @param cvCometi
     */
    private ControladorVentanaCompeticion cvCompeti;
    private ControladorVentanaLogin cvLogin;

    private ControladorVentanaSeleccion cvSeleccion;

    private ControladorVentanaCarga cvCarga;


    public ControladorVista(ControladorPrincipal cp)
    {
        this.cp = cp;

        crearControladoresVista();
        mostrarVentanaLogin();

    }

    public void mostrarVentanaCompeti()
    {
        cvCompeti.crearMostrar();
    }

    public void mostrarVentanaLogin(){cvLogin.crearMostrar();}
    public void mostrarVentanaSeleccion(){cvSeleccion.crearMostrar();}
    public void mostrarVentanaCarga(int milisegundos,JFrame ventanaPadre)
    {
        cvCarga.crearMostrar(milisegundos,ventanaPadre);
    }

    public void crearControladoresVista()
    {
        cvLogin = new ControladorVentanaLogin(this);
        cvCompeti = new ControladorVentanaCompeticion(this);
        cvSeleccion = new ControladorVentanaSeleccion(this);
        cvCarga = new ControladorVentanaCarga(this);
    }










    public Juego buscarJuego(String nombre) throws Exception { return cp.buscarJuego(nombre);}
    public Juego buscarJuego(int cod) throws Exception{return cp.buscarJuego(cod);}
    public String insertarJuego(Juego juego) throws Exception { return cp.insertarJuego(juego);}
    public String borrarJuego() throws Exception{ return cp.borrarJuego();}
    public String modificarJuego(Juego juego) throws Exception{return cp.modificarJuego(juego);}

    //Equipos
    public Equipo buscarEquipo(int cod) throws Exception{return cp.buscarEquipo(cod);}
    public Equipo buscarEquipo(String nombre) throws Exception{return cp.buscarEquipo(nombre);}
    public String borrarEquipo() throws Exception {return cp.borrarEquipo();}
    public String modificarEquipo(Equipo equipo) throws Exception{return cp.modificarEquipo(equipo);}
    public String insertarEquipo(Equipo equipo) throws Exception{return cp.insertarEquipo(equipo);}


    //Patrocinadores
    public String insertarEPatrocinador(Patrocinador patrocinador) throws Exception
    { return cp.insertarEPatrocinador(patrocinador);}
    public Patrocinador buscarPatrocinador(String nombre) throws Exception
    {return cp.buscarPatrocinador(nombre);}
    public Patrocinador buscarPatrocinador(int cod) throws Exception
    {return cp.buscarPatrocinador(cod);}
    public String borrarPatrocinador() throws Exception
    {
        return cp.borrarPatrocinador();
    }
    public String modificarPatrocinador(Patrocinador patrocinador) throws Exception
    {
        return cp.modificarPatrocinador(patrocinador);
    }

    //Jugadores
    public Jugador buscarJugador(String nickname) throws Exception{return cp.buscarJugador(nickname);}
    public Jugador buscarJugador(int cod) throws Exception{return cp.buscarJugador(cod);}
    public String borrarJugador() throws Exception{return cp.borrarJugador();}
    public String modificarJugador(Jugador jugador) throws Exception{return cp.modificarJugador(jugador);}
    public String insertarJugador(Jugador jugador) throws Exception{return cp.insertarJugador(jugador);}


    //Staff
    public Staff buscarStaff(String nombre) throws Exception{return cp.buscarStaff(nombre);}
    public Staff buscarStaff(int cod) throws Exception{return cp.buscarStaff(cod);}
    public String borrarStaff() throws Exception{return cp.borrarStaff();}
    public String modificarStaff(Staff staff) throws Exception{return cp.modificarStaff(staff);}
    public String insertarJStaff(Staff staff) throws Exception{return cp.insertarJStaff(staff);}



    //Competiciones
    public Competicion buscarCompeticion(int cod) throws Exception
    {return cp.buscarCompeticion(cod);}
    public String borrarCompeticion() throws Exception
    {return cp.borrarCompeticion();}
    public String modificarCompeticion(Competicion competicion) throws Exception
    {return cp.modificarCompeticion(competicion);}
    public String insertarCompeticion(Competicion competicion) throws Exception
    {return cp.insertarCompeticion(competicion);}
    public ArrayList<Competicion> pedirCompeticionesCerradas()throws Exception
    {return cp.pedirCompeticionesCerradas();}

    public ArrayList<Competicion> pedirListaCompeticiones() throws Exception
    {return cp.pedirListaCompeticiones();}
    public String generarCalendario() throws Exception
    {
        return cp.generarCalendario();
    }



    //Usuario
    public Usuario buscarUsuario(String nickname) throws Exception{return cp.buscarUsuario(nickname);}
    public String borrarUsuario() throws Exception{return cp.borrarUsuario();}
    public String modificarUsuario(Usuario usuario) throws Exception{return cp.modificarUsuario(usuario);}
    public String insertarUsuario(Usuario usuario) throws Exception{return cp.insertarUsuario(usuario);}

    //Jornadas
    public ArrayList<Jornada> consultarTablaJornadas(int codCompeticion)throws Exception
    {
        return cp.consultarTablaJornadas(codCompeticion);
    }
    public Jornada buscarJornada(int cod) throws Exception{ return cp.buscarJornada(cod);}



    //Enfrentamientos
    public ArrayList<Enfrentamiento> consultarEnfrentamientosSinResultado(int codJornada)throws Exception
    {
        return cp.consultarEnfrentamientosSinResultado(codJornada);
    }



}
