package Controlador.ControladoresBD;

import Controlador.ControladorPrincipal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Esta clase se encargará de controlar la conexión hacía la base de datos y de conectar los controladores de las tablas
 * con el controlador principal
 *
 * @author Erik
 * @version 1.0
 * @since 13/05/2024
 */
public class ControladorBD {

    private ControladorTCompeticiones ctCompeti;

    private ControladorTEnfrentamientos ctEnfren;

    private ControladorTEquipos ctEquipos;

    private ControladorTJornadas ctJornadas;

    private ControladorTJuegos ctJuegos;

    private ControladorTJugadores ctJugadores;

    private ControladorTPatrocinadores ctPatro;

    private ControladorTPuntos_Equipos ctPuntosEquipos;

    private ControladorTStaffs ctStaffs;

    private ControladorTUsuarios ctUsuarios;

    //Conexion con el controlador superior
    final  ControladorPrincipal cp;

    //Variables privadas de la clase propias
    private Connection con;


    public ControladorBD(ControladorPrincipal cp)
    {
        this.cp = cp;
        abrirConexion();

    }

    public void abrirConexion()
    {
        try
        {
            //Registrar el driver
            Class.forName("oracle.jdbc.driver.OracleDriver");
            String url = "jdbc:oracle:thin:@a8150ad3dbd3.sn.mynetname.net:33150:xe";


            String login="proyecto1"; // usuario
            String password = "MasTrabajo24"; // Preguntar a Eider como guardar una password de manera segura

            this.con = DriverManager.getConnection (url ,login , password );
            System.out.print("Conexión realizada");


        }
        catch (ClassNotFoundException | SQLException e)
        {
           // cp.mostrarMensajeVP(e.getMessage());
            System.out.print("Ha salido un error "+e.getMessage() );
        }
    }
    public void inicializarTablas()
    {
        ctCompeti = new ControladorTCompeticiones();
        ctEnfren = new ControladorTEnfrentamientos();
        ctEquipos = new ControladorTEquipos();
        ctJornadas = new ControladorTJornadas();
        ctJuegos = new ControladorTJuegos();
        ctJugadores = new ControladorTJugadores();
        ctPatro = new ControladorTPatrocinadores();
        ctPuntosEquipos = new ControladorTPuntos_Equipos();
        ctStaffs = new ControladorTStaffs();
        ctUsuarios = new ControladorTUsuarios();
    }

}
