package Controlador;

import Controlador.ControladoresBD.ControladorBD;
import Controlador.ControladoresVista.ControladorVista;
import Modelo.*;

import java.util.ArrayList;

public class ControladorPrincipal {

    private ControladorBD cbd;
    private ControladorVista cv;



    public ControladorPrincipal()
    {
        cbd = new ControladorBD(this);
        cv = new ControladorVista(this);
    }





    //Tabla Juegos
    public Juego buscarJuego(String nombre) throws Exception { return cbd.buscarJuego(nombre);}
    public Juego buscarJuego(int cod) throws Exception{return cbd.buscarJuego(cod);}
    public String insertarJuego(Juego juego) throws Exception { return cbd.insertarJuego(juego);}
    public String borrarJuego() throws Exception{ return cbd.borrarJuego();}
    public String modificarJuego(Juego juego) throws Exception{return cbd.modificarJuego(juego);}

    //Equipos
    public Equipo buscarEquipo(int cod) throws Exception{return cbd.buscarEquipo(cod);}
    public Equipo buscarEquipo(String nombre) throws Exception{return cbd.buscarEquipo(nombre);}
    public String borrarEquipo() throws Exception {return cbd.borrarEquipo();}
    public String modificarEquipo(Equipo equipo) throws Exception{return cbd.modificarEquipo(equipo);}
    public String insertarEquipo(Equipo equipo) throws Exception{return cbd.insertarEquipo(equipo);}



    //Patrocinadores
    public String insertarEPatrocinador(Patrocinador patrocinador) throws Exception
    { return cbd.insertarEPatrocinador(patrocinador);}
    public Patrocinador buscarPatrocinador(String nombre) throws Exception
    {return cbd.buscarPatrocinador(nombre);}
    public Patrocinador buscarPatrocinador(int cod) throws Exception
    {return cbd.buscarPatrocinador(cod);}
    public String borrarPatrocinador() throws Exception
    {
        return cbd.borrarPatrocinador();
    }
    public String modificarPatrocinador(Patrocinador patrocinador) throws Exception
    {
        return cbd.modificarPatrocinador(patrocinador);
    }


    //Jugadores
    public Jugador buscarJugador(String nickname) throws Exception{return cbd.buscarJugador(nickname);}
    public Jugador buscarJugador(int cod) throws Exception{return cbd.buscarJugador(cod);}
    public String borrarJugador() throws Exception{return cbd.borrarJugador();}
    public String modificarJugador(Jugador jugador) throws Exception{return cbd.modificarJugador(jugador);}
    public String insertarJugador(Jugador jugador) throws Exception{return cbd.insertarJugador(jugador);}


    //Staff
    public Staff buscarStaff(String nombre) throws Exception{return cbd.buscarStaff(nombre);}
    public Staff buscarStaff(int cod) throws Exception{return cbd.buscarStaff(cod);}
    public String borrarStaff() throws Exception{return cbd.borrarStaff();}
    public String modificarStaff(Staff staff) throws Exception{return cbd.modificarStaff(staff);}
    public String insertarJStaff(Staff staff) throws Exception{return cbd.insertarJStaff(staff);}


    //Competiciones
    public Competicion buscarCompeticion(int cod) throws Exception
    {return cbd.buscarCompeticion(cod);}
    public String borrarCompeticion() throws Exception
    {return cbd.borrarCompeticion();}
    public String modificarCompeticion(Competicion competicion) throws Exception
    {return cbd.modificarCompeticion(competicion);}
    public String insertarCompeticion(Competicion competicion) throws Exception
    {return cbd.insertarCompeticion(competicion);}
    public ArrayList<Competicion> pedirListaCompeticiones() throws Exception
    {return cbd.pedirListaCompeticiones();}
    public ArrayList<Competicion> pedirCompeticionesCerradas()throws Exception
    {return cbd.pedirCompeticionesCerradas();}
    public String generarCalendario() throws Exception
    {
        return cbd.generarCalendario();
    }

    //Usuario
    public Usuario buscarUsuario(String nickname) throws Exception{return cbd.buscarUsuario(nickname);}
    public String borrarUsuario() throws Exception{return cbd.borrarUsuario();}
    public String modificarUsuario(Usuario usuario) throws Exception{return cbd.modificarUsuario(usuario);}
    public String insertarUsuario(Usuario usuario) throws Exception{return cbd.insertarUsuario(usuario);}



    //Jornadas
    public ArrayList<Jornada> consultarTablaJornadas(int codCompeticion)throws Exception
    {
        return cbd.consultarTablaJornadas(codCompeticion);
    }

    public Jornada buscarJornada(int cod) throws Exception{ return cbd.buscarJornada(cod);}

    //Enfrentamientos
    public ArrayList<Enfrentamiento> consultarEnfrentamientosSinResultado(int codJornada)throws Exception
    {
        return cbd.consultarEnfrentamientosSinResultado(codJornada);
    }
    public boolean actualizarResultados(int cod) throws Exception{return cbd.actualizarResultados(cod);}





}
