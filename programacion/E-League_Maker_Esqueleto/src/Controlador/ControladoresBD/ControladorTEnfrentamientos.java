/**
 * Esta clase se encarga de controlar los procedimientos almacenados de la tabla Enfrentamientos
 * @author Erik
 * @since 19/05/2024
 */


package Controlador.ControladoresBD;

import Modelo.Competicion;
import Modelo.Enfrentamiento;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ControladorTEnfrentamientos {

    private ControladorBD cbd;

    private Connection con;

    private Enfrentamiento enfrentamiento;
    private ArrayList<Enfrentamiento> listaEnfrentamientos;

    public ControladorTEnfrentamientos(ControladorBD cbd){this.cbd = cbd;}

    public ArrayList<Enfrentamiento> consultarEnfrentamientosSinResultado()throws Exception
    {
        try {
            con = cbd.abrirConexion();
            String llamada = "{ ? = call  }";
            CallableStatement cs = con.prepareCall(llamada);

            cs.registerOutParameter(1, oracle.jdbc.OracleTypes.CURSOR);

            cs.execute();

            ResultSet rs = (ResultSet) cs.getObject(1);

            while (rs.next()) {
                Competicion competi = new Competicion();
                competi.setCod(rs.getInt("cod"));
                competi.setNombre(rs.getString("nombre"));
                competi.setFechaInicio(rs.getDate("fecha_inicio").toLocalDate());
                competi.setFechaFin(rs.getDate("fecha_fin").toLocalDate());
                competi.setEstadoAbierto(rs.getBoolean("estado_abierto"));
                competi.setJuego(cbd.buscarJuego(rs.getInt("cod_juego")));
      //          listaEnfrentamientos.add();
            }

            rs.close();
            cs.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Error al consultar las competiciones", e);
        } finally {
            if (con != null) {
                try {
                    cbd.cerrarConexion(con);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return listaEnfrentamientos;
    }



}
