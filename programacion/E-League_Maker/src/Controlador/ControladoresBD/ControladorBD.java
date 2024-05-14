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

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ControladorBD {

    final ControladorPrincipal cp;
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
            String url = "jdbc:oracle:thin:@172.20.225.114:1521:orcl";


            String login="eqdaw05"; // usuario
            String password = "eqdaw05"; // Preguntar a Eider como guardar una password de manera segura

            this.con = DriverManager.getConnection (url ,login , password );
            System.out.print("Conexión realizada");


        }
        catch (ClassNotFoundException | SQLException e)
        {
            // cp.mostrarMensajeVP(e.getMessage());

            System.out.print("Ha salido un error\n "+e.getMessage());
        }
    }


    public void cerrarConexion() throws SQLException {
        con.close();
    }
    /*public void inicializarTablas()
    {
    }*/

}
