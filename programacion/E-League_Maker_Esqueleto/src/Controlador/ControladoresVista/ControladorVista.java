/**
 * Esta clase se encarga de controlar los diferentes controladores que controlan la vista.
 * A su vez en un nexo de diferentes metodos de la vista.
 * @author Erik
 * @version 1.0
 */
package Controlador.ControladoresVista;

import Controlador.ControladorPrincipal;
import Controlador.ControladoresVista.ControladoresVistaInscripcion.ControladorVentanaInscripcion;
import Modelo.*;

import javax.swing.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ControladorVista {

    /**
     * Con la siguiente variable buscamos relacionar el controlador superior, el controlador principal, con este
     * controlador.
     */
    private ControladorPrincipal cp;
    private ControladorVentanaUsuario cvUsuario;

    /**
     * Los siguientes atributos serán objetos de cada uno de los controladores de la vista
     *
     * @param cvCometi
     */
    private ControladorVentanaCompeticion cvCompeti;
    private ControladorVentanaLogin cvLogin;

    private ControladorVentanaSeleccion cvSeleccion;
    private ControladorVentanaInscripcion cvInscripcion;

    private ControladorVentanaCarga cvCarga;

    public ControladorVista(ControladorPrincipal cp) {
        this.cp = cp;

        crearControladoresVista();
        mostrarVentanaLogin();

    }

    public void mostrarVentanaCompeti() {
        cvCompeti.crearMostrar();
    }

    public void mostrarVentanaLogin() {
        cvLogin.crearMostrar();
    }

    public void mostrarVentanaSeleccion() {
        cvSeleccion.crearMostrar();
    }

    public void mostrarVentanaCarga(int milisegundos, JFrame ventanaPadre) {
        cvCarga.crearMostrar(milisegundos, ventanaPadre);
    }

    public void mostrarVentanaUsuario() {
        cvUsuario.crearMostrar();
    }

    public void ocultarVentanaCarga() {
        cvCarga.ocultarVentanaCarga();
    }

    public void mostrarVentanaInscripcion() {
        cvInscripcion.crearMostrarInscripcion();
    }


    public void crearControladoresVista() {
        cvLogin = new ControladorVentanaLogin(this);
        cvCompeti = new ControladorVentanaCompeticion(this);
        cvSeleccion = new ControladorVentanaSeleccion(this);
        cvInscripcion = new ControladorVentanaInscripcion(this);
        cvUsuario = new ControladorVentanaUsuario(this);
        cvCarga = new ControladorVentanaCarga(this);
    }


    //Juegos
    public Juego buscarJuego(String nombre) throws Exception {
        return cp.buscarJuego(nombre);
    }

    public Juego buscarJuego(int cod) throws Exception {
        return cp.buscarJuego(cod);
    }

    public void insertarJuego(Juego je) throws Exception {
        cp.insertarJuego(je);
    }

    public void modificarJuego(Juego je) throws Exception {
        cp.modificarJuego(je);
    }

    //Equipos
    public Equipo buscarEquipo(int cod) throws Exception {
        return cp.buscarEquipo(cod);
    }

    public Equipo buscarEquipo(String nombre) throws Exception {
        return cp.buscarEquipo(nombre);
    }

    public void modificarEquipo(Equipo e) throws Exception {
        cp.modificarEquipo(e);
    }

    public void insertarEquipo(Equipo e) throws Exception {
        cp.insertarEquipo(e);
    }


    //Patrocinadores
    public void insertarPatrocinador(Patrocinador p) throws Exception {
        cp.insertarPatrocinador(p);
    }

    public Patrocinador buscarPatrocinador(String nombre) throws Exception {
        return cp.buscarPatrocinador(nombre);
    }

    public Patrocinador buscarPatrocinador(int cod) throws Exception {
        return cp.buscarPatrocinador(cod);
    }

    public void modificarPatrocinador(Patrocinador p) throws Exception {
        cp.modificarPatrocinador(p);
    }

    //Jugadores
    public Jugador buscarJugador(String nickname) throws Exception {
        return cp.buscarJugador(nickname);
    }

    public Jugador buscarJugador(int cod) throws Exception {
        return cp.buscarJugador(cod);
    }

    public void modificarJugador(Jugador jd) throws Exception {
        cp.modificarJugador(jd);
    }

    public void insertarJugador(Jugador jd) throws Exception {
        cp.insertarJugador(jd);
    }


    //Staff
    public Staff buscarStaff(String nombre) throws Exception {
        return cp.buscarStaff(nombre);
    }

    public Staff buscarStaff(int cod) throws Exception {
        return cp.buscarStaff(cod);
    }

    public void modificarStaff(Staff s) throws Exception {
        cp.modificarStaff(s);
    }

    public void insertarStaff(Staff s) throws Exception {
        cp.insertarStaff(s);
    }


    //Competiciones
    public Competicion buscarCompeticion(int cod) throws Exception {
        return cp.buscarCompeticion(cod);
    }

    public void modificarCompeticion(Competicion c) throws Exception {
        cp.modificarCompeticion(c);
    }

    public void insertarCompeticion(Competicion c) throws Exception {
        cp.insertarCompeticion(c);
    }

    public ArrayList<Competicion> pedirCompeticionesCerradas() throws Exception {
        return cp.pedirCompeticionesCerradas();
    }

    public List<Competicion> pedirListaCompeticiones() throws Exception {
        return cp.pedirListaCompeticiones();
    }


    //Usuario
    public Usuario buscarUsuario(String nickname) throws Exception {
        return cp.buscarUsuario(nickname);
    }

    public void modificarUsuario(Usuario u) throws Exception {
        cp.modificarUsuario(u);
    }

    public void insertarUsuario(Usuario u) throws Exception {
        cp.insertarUsuario(u);
    }


    /**
     * Metodo en el cual pasa los datos necesario para borrar elementos de la tabla
     *
     * @param opcion es un int para saber a que tabla pertenece el elemento a eliminar
     * @param cod    es el codigo del objeto que se va a eliminar
     * @author Oskar
     * @version 2.0 16/05/2024
     */
    public void borrarDeTabla(int opcion, int cod) throws Exception {
        cp.borrarDeTabla(opcion, cod);
    }

    public List buscarJugadores() throws Exception {
        return cp.buscarJugadores();
    }

    public List buscarJuegos() throws Exception {
        return cp.buscarJuegos();
    }

    public List buscarEquipos() throws Exception {
        return cp.buscarEquipos();
    }

    public List buscarStaffs() throws Exception {
        return cp.buscarStaffs();
    }

    public List buscarPatrocinadores() throws Exception {
        return cp.buscarPatrocinadores();
    }

    public List buscarCompeticiones() throws Exception {
        return cp.buscarCompeticiones();
    }

    public List buscarUsuarios() throws Exception {
        return cp.buscarUsuarios();
    }


    public List buscarCompeticionesAbiertas() throws Exception {
        return cp.buscarCompeticionesAbiertas();
    }


    public ArrayList<Enfrentamiento> consultarEnfrentamientosConResultados(int codJornada) throws Exception {
        return cp.consultarEnfrentamientosConResultados(codJornada);
    }


    public boolean actualizarResultados(int cod, int resultado) throws Exception {
        return cp.actualizarResultados(cod, resultado);

    }

    public List buscarEquiposInscribir(int cod) throws Exception {
        return cp.buscarEquiposInscribir(cod);
    }

    public List buscarEquiposRescindir(int cod) throws Exception {
        return cp.buscarEquiposRescindir(cod);
    }

    public void modificarCompeticionEstado(int cod, int estado) throws Exception {
        cp.modificarCompeticionEstado(cod, estado);
    }

    public void inscribirEquipo(int codCompeti, int codEquipo) throws Exception {
        cp.inscribirEquipo(codCompeti, codEquipo);
    }

    public void rescindirEquipo(int codCompeti, int codEquipo) throws Exception {
        cp.rescindirEquipo(codCompeti, codEquipo);
    }


    public ArrayList<Clasificacion> obtenerClasificacion(int codCompeticion) throws Exception {
        return cp.obtenerClasificacion(codCompeticion);
    }

    /**
     * Exportar CLASIFICACION XML
     */
    public String exportarClasificacionXML() throws Exception {
        return cp.exportarClasificacionXML();
    }
}
