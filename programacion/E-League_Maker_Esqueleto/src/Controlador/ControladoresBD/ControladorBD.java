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
import java.util.List;

public class ControladorBD {

    final ControladorPrincipal cp;



    private ControladorTJuegos ctJuegos;
    private ControladorTEquipos ctEquipos;
    private ControladorTPatrocinadores ctPatrocinadores;
    private ControladorTJugadores ctJugadores;
    private ControladorTStaffs ctStaffs;
    private ControladorTCompeticiones ctCompeticiones;
    private ControladorTUsuarios ctUsuarios;
    private ControladorTPuntosEquipos ctPuntos;


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
        ctPuntos = new ControladorTPuntosEquipos(this);
    }



    // Juegos
    public Juego buscarJuego(String nombre) throws Exception { return ctJuegos.buscarJuego(nombre);}
    public Juego buscarJuego(int cod) throws Exception{return ctJuegos.buscarJuego(cod);}
    public void insertarJuego (Juego je) throws Exception {
        ctJuegos.insertarJuego(je);
    }
    public void modificarJuego (Juego je) throws Exception {
        ctJuegos.modificarJuego(je);
    }
    public List buscarJuegos() throws Exception {
        return ctJuegos.buscarJuegos();
    }




    //Equipos
    public Equipo buscarEquipo(int cod) throws Exception{return ctEquipos.buscarEquipo(cod);}
    public Equipo buscarEquipo(String nombre) throws Exception{return ctEquipos.buscarEquipo(nombre);}
    public void modificarEquipo (Equipo e) throws Exception {
        ctEquipos.modificarEquipo(e);
    }
    public void insertarEquipo (Equipo e) throws Exception {
        ctEquipos.insertarEquipo(e);
    }
    public List buscarEquipos() throws Exception {
        return ctEquipos.buscarEquipos();
    }
    public List buscarEquiposInscribir(int cod) throws Exception {
        return ctEquipos.buscarEquiposInscribir(cod);
    }
    public List buscarEquiposRescindir(int cod) throws Exception {
        return ctEquipos.buscarEquiposRescindir(cod);
    }



    //Patrocinadores
    public void insertarPatrocinador (Patrocinador p) throws Exception {
        ctPatrocinadores.insertarPatrocinador(p);
    }
    public Patrocinador buscarPatrocinador(String nombre) throws Exception
    {return ctPatrocinadores.buscarPatrocinador(nombre);}
    public Patrocinador buscarPatrocinador(int cod) throws Exception
    {return ctPatrocinadores.buscarPatrocinador(cod);}
    public void modificarPatrocinador (Patrocinador p) throws Exception {
        ctPatrocinadores.modificarPatrocinador(p);
    }
    public List buscarPatrocinadores() throws Exception {
        return ctPatrocinadores.buscarPatrocinadores();
    }


    //Jugadores
    public Jugador buscarJugador(String nickname) throws Exception{return ctJugadores.buscarJugador(nickname);}
    public Jugador buscarJugador(int cod) throws Exception{return ctJugadores.buscarJugador(cod);}
    public void modificarJugador (Jugador jd) throws Exception {
        ctJugadores.modificarJugador(jd);
    }
    public void insertarJugador (Jugador jd) throws Exception {
        ctJugadores.insertarJugador(jd);
    }
    public List buscarJugadores() throws Exception {
        return ctJugadores.buscarJugadores();
    }


    //Staff
    public Staff buscarStaff(String nombre) throws Exception{return ctStaffs.buscarStaff(nombre);}
    public Staff buscarStaff(int cod) throws Exception{return ctStaffs.buscarStaff(cod);}
    public void modificarStaff (Staff s) throws Exception {
        ctStaffs.modificarStaff(s);
    }
    public void insertarStaff (Staff s) throws Exception {
        ctStaffs.insertarStaff(s);
    }
    public List buscarStaffs() throws Exception {
        return ctStaffs.buscarStaffs();
    }


    //Competiciones
    public Competicion buscarCompeticion(int cod) throws Exception
    {return ctCompeticiones.buscarCompeticion(cod);}
    public void modificarCompeticion (Competicion c) throws Exception {
        ctCompeticiones.modificarCompeticion(c);
    }
    public void insertarCompeticion (Competicion c) throws Exception {
        ctCompeticiones.insertarCompeticion(c);
    }
    public List<Competicion> pedirListaCompeticiones() throws Exception
    {return ctCompeticiones.pedirListaCompeticiones();}
    public ArrayList<Competicion> pedirCompeticionesCerradas()throws Exception
    {return ctCompeticiones.pedirCompeticionesCerradas();}
    public List buscarCompeticiones() throws Exception {
        return ctCompeticiones.pedirListaCompeticiones();
    }
    public List buscarCompeticionesAbiertas() throws Exception {
        return ctCompeticiones.pedirCompeticionesAbiertas();
    }
    public void modificarCompeticionEstado(int cod, int estado) throws Exception {
        ctCompeticiones.modificarCompeticionEstado (cod, estado);
    }


    //Usuario
    public Usuario buscarUsuario(String nickname) throws Exception{return ctUsuarios.buscarUsuario(nickname);}
    public void modificarUsuario (Usuario u) throws Exception {
        ctUsuarios.modificarUsuario(u);
    }
    public void insertarUsuario (Usuario u) throws Exception {
        ctUsuarios.insertarUsuario(u);
    }
    public List buscarUsuarios() throws Exception {
        return ctUsuarios.buscarUsuarios();
    }


    // Puntos_equipos
    public void inscribirEquipo(int codCompeti, int codEquipo) throws Exception {
        ctPuntos.inscribirEquipo(codCompeti, codEquipo);
    }
    public void rescindirEquipo(int codCompeti, int codEquipo) throws Exception {
        ctPuntos.rescindirEquipo(codCompeti, codEquipo);
    }



    /** Metodo en el cual pasa los datos necesario para borrar elementos de la tabla
     *
     * @param opcion es un int para saber a que tabla pertenece el elemento a eliminar
     * @param cod es el codigo del objeto que se va a eliminar
     * @author Oskar
     * @version 2.0 16/05/2024
     */
    public void borrarDeTabla (int opcion, int cod) throws Exception {
        switch (opcion)
        {
            case 1: // Jugadores
                 ctJugadores.borrarJugador(cod);
                break;
            case 2: // Juegos
                 ctJuegos.borrarJuego(cod);
                break;
            case 3: // Equipos
                 ctEquipos.borrarEquipo(cod);
                break;
            case 4: // Staffs
                 ctStaffs.borrarStaff(cod);
                break;
            case 5: // Patrocinadores
                 ctPatrocinadores.borrarPatrocinador(cod);
                break;
            case 6: // Competiciones
                 ctCompeticiones.borrarCompeticion(cod);
                break;
            case 7: // Usuarios
                ctUsuarios.borrarUsuario(cod);
                break;
        }
    }


}
