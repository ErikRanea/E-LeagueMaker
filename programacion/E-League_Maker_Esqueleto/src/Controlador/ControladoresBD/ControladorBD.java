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
import Modelo.Equipo;
import Modelo.Juego;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ControladorBD {

    final ControladorPrincipal cp;



    private ControladorTJuegos ctJuegos;
    private ControladorTEquipos ctEquipos;


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
        con.close();
    }
    public void inicializarTablas()
    {
        ctJuegos = new ControladorTJuegos(this);
        ctEquipos = new ControladorTEquipos(this);
    }




    public Juego buscarJuego(String nombre) throws Exception { return ctJuegos.buscarJuego(nombre);}
    public String insertarJuego(Juego juego) throws Exception { return ctJuegos.insertarJuego(juego);}
    public String borrarJuego() throws Exception{ return ctJuegos.borrarJuego();}
    public String modificarJuego(Juego juego) throws Exception{return ctJuegos.modificarJuego(juego);}



    //Equipos
    public Equipo buscarEquipo(int cod) throws Exception{return ctEquipos.buscarEquipo(cod);}
    public Equipo buscarEquipo(String nombre) throws Exception{return ctEquipos.buscarEquipo(nombre);}
    public String borrarEquipo() throws Exception {return ctEquipos.borrarEquipo();}
    public String modificarEquipo(Equipo equipo) throws Exception{return ctEquipos.modificarEquipo(equipo);}
    public String insertarEquipo(Equipo equipo) throws Exception{return ctEquipos.insertarEquipo(equipo);}


}
