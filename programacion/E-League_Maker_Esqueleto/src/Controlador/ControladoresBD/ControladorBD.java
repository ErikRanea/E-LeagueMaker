/**
 * Esta clase se encargará de controlar la conexión hacía la base de datos y de conectar los controladores de las tablas
 * con el controlador principal
 *
 * @author Erik
 * @version 1.0
 * @since 13/05/2024
 */
package Controlador.ControladoresBD;

import Controlador.ControladorPrincipal;
import Modelo.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

public class ControladorBD {

    final ControladorPrincipal cp;



    private ControladorTJuegos ctJuegos;
    private ControladorTEquipos ctEquipos;
    private ControladorTPatrocinadores ctPatrocinadores;
    private ControladorTJugadores ctJugadores;
    private ControladorTStaffs ctStaffs;
    private ControladorTCompeticiones ctCompeticiones;
    private ControladorTUsuarios ctUsuarios;

    private ControladorTJornadas ctJornadas;
    private ControladorTEnfrentamientos ctEnfrentamientos;

    public ControladorBD(ControladorPrincipal cp)
    {
        this.cp = cp;
        inicializarTablas();

    }

    public Connection  abrirConexion()
    {
        try
        {
            //Registrar el driver
            Class.forName("oracle.jdbc.driver.OracleDriver");
            String url = "jdbc:oracle:thin:@a8150ad3dbd3.sn.mynetname.net:33150:xe";


            String login="proyecto1"; // usuario
            String password = "MasTrabajo24"; // Preguntar a Eider como guardar una password de manera segura

            System.out.println("\n*******************************");
            System.out.print("\nConexión realizada");
            return DriverManager.getConnection (url ,login , password );

        }
        catch (ClassNotFoundException | SQLException e)
        {
            // cp.mostrarMensajeVP(e.getMessage());

            System.out.print("Ha salido un error\n "+e.getMessage());
        }
        return null;
    }


    public void cerrarConexion(Connection con) throws SQLException {
        System.out.println("Conexión cerrada");
        System.out.println("\n*******************************");
        con.close();
    }
    public void inicializarTablas()
    {
        ctJuegos = new ControladorTJuegos(this);
        ctEquipos = new ControladorTEquipos(this);
        ctPatrocinadores = new ControladorTPatrocinadores(this);
        ctJugadores = new ControladorTJugadores(this);
        ctStaffs = new ControladorTStaffs(this);
        ctCompeticiones = new ControladorTCompeticiones(this);
        ctUsuarios = new ControladorTUsuarios(this);
        ctJornadas = new ControladorTJornadas(this);
        ctEnfrentamientos = new ControladorTEnfrentamientos(this);
    }




    public Juego buscarJuego(String nombre) throws Exception { return ctJuegos.buscarJuego(nombre);}
    public Juego buscarJuego(int cod) throws Exception{return ctJuegos.buscarJuego(cod);}
    public String insertarJuego(Juego juego) throws Exception { return ctJuegos.insertarJuego(juego);}
    public String borrarJuego() throws Exception{ return ctJuegos.borrarJuego();}
    public String modificarJuego(Juego juego) throws Exception{return ctJuegos.modificarJuego(juego);}



    //Equipos
    public Equipo buscarEquipo(int cod) throws Exception{return ctEquipos.buscarEquipo(cod);}
    public Equipo buscarEquipo(String nombre) throws Exception{return ctEquipos.buscarEquipo(nombre);}
    public String borrarEquipo() throws Exception {return ctEquipos.borrarEquipo();}
    public String modificarEquipo(Equipo equipo) throws Exception{return ctEquipos.modificarEquipo(equipo);}
    public String insertarEquipo(Equipo equipo) throws Exception{return ctEquipos.insertarEquipo(equipo);}


    //Patrocinadores
    public String insertarEPatrocinador(Patrocinador patrocinador) throws Exception
    { return ctPatrocinadores.insertarEPatrocinador(patrocinador);}
    public Patrocinador buscarPatrocinador(String nombre) throws Exception
    {return ctPatrocinadores.buscarPatrocinador(nombre);}
    public Patrocinador buscarPatrocinador(int cod) throws Exception
    {return ctPatrocinadores.buscarPatrocinador(cod);}
    public String borrarPatrocinador() throws Exception
    {
        return ctPatrocinadores.borrarPatrocinador();
    }
    public String modificarPatrocinador(Patrocinador patrocinador) throws Exception
    {
        return ctPatrocinadores.modificarPatrocinador(patrocinador);
    }


    //Jugadores
    public Jugador buscarJugador(String nickname) throws Exception{return ctJugadores.buscarJugador(nickname);}
    public Jugador buscarJugador(int cod) throws Exception{return ctJugadores.buscarJugador(cod);}
    public String borrarJugador() throws Exception{return ctJugadores.borrarJugador();}
    public String modificarJugador(Jugador jugador) throws Exception{return ctJugadores.modificarJugador(jugador);}
    public String insertarJugador(Jugador jugador) throws Exception{return ctJugadores.insertarJugador(jugador);}


    //Staff
    public Staff buscarStaff(String nombre) throws Exception{return ctStaffs.buscarStaff(nombre);}
    public Staff buscarStaff(int cod) throws Exception{return ctStaffs.buscarStaff(cod);}
    public String borrarStaff() throws Exception{return ctStaffs.borrarStaff();}
    public String modificarStaff(Staff staff) throws Exception{return ctStaffs.modificarStaff(staff);}
    public String insertarJStaff(Staff staff) throws Exception{return ctStaffs.insertarJStaff(staff);}

    //Competiciones
    public Competicion buscarCompeticion(int cod) throws Exception
    {return ctCompeticiones.buscarCompeticion(cod);}
    public String borrarCompeticion() throws Exception
    {return ctCompeticiones.borrarCompeticion();}
    public String modificarCompeticion(Competicion competicion) throws Exception
    {return ctCompeticiones.modificarCompeticion(competicion);}
    public String insertarCompeticion(Competicion competicion) throws Exception
    {return ctCompeticiones.insertarCompeticion(competicion);}
    public ArrayList<Competicion> pedirListaCompeticiones() throws Exception
    {return ctCompeticiones.pedirListaCompeticiones();}
    public ArrayList<Competicion> pedirCompeticionesCerradas()throws Exception
    {return ctCompeticiones.pedirCompeticionesCerradas();}
    public String generarCalendario() throws Exception
    {
        return ctCompeticiones.generarCalendario();
    }



    //Usuario

    public Usuario buscarUsuario(String nickname) throws Exception{return ctUsuarios.buscarUsuario(nickname);}
    public String borrarUsuario() throws Exception{return ctUsuarios.borrarUsuario();}
    public String modificarUsuario(Usuario usuario) throws Exception{return ctUsuarios.modificarUsuario(usuario);}
    public String insertarUsuario(Usuario usuario) throws Exception{return ctUsuarios.insertarUsuario(usuario);}


    //Jornadas
    public ArrayList<Jornada> consultarTablaJornadas(int codCompeticion)throws Exception
    {
        return ctJornadas.consultarTablaJornadas(codCompeticion);
    }

    //Enfrentamientos
    public ArrayList<Enfrentamiento> consultarEnfrentamientosSinResultado(int codJornada)throws Exception
    {
        return ctEnfrentamientos.consultarEnfrentamientosSinResultado(codJornada);
    }

}
