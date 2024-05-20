/**
 * Esta clase se encarga de controlar los procedimientos almacenados de la tabla Enfrentamientos
 * @author Erik
 * @since 19/05/2024
 */


package Controlador.ControladoresBD;

import Modelo.Competicion;
import Modelo.Enfrentamiento;
import Modelo.Jornada;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class ControladorTEnfrentamientos {

    private ControladorBD cbd;

    private Connection con;

    private Enfrentamiento enfrentamiento;
    private ArrayList<Enfrentamiento> listaEnfrentamientos;

    public ControladorTEnfrentamientos(ControladorBD cbd){this.cbd = cbd;}

    public ArrayList<Enfrentamiento> consultarEnfrentamientosSinResultado(int codJornada)throws Exception
    {
        try {
            con = cbd.abrirConexion();
            String llamada = "{ ? = call  CONSULTAR_ENFRENTAMIENTOS_SIN_RESULTADOS(?)}";
            CallableStatement cs = con.prepareCall(llamada);

            cs.registerOutParameter(1, oracle.jdbc.OracleTypes.CURSOR);
            cs.setInt(2,codJornada);

            cs.execute();

            ResultSet rs = (ResultSet) cs.getObject(1);

            while (rs.next()) {
                Enfrentamiento enfre = new Enfrentamiento();
                enfre.setCod(rs.getInt("cod"));
                enfre.setEquipoLocal(cbd.buscarEquipo(rs.getInt("cod_equipo_local")));
                // Convertir Date a LocalDateTime
                Timestamp timestamp = rs.getTimestamp("hora");
                if (timestamp != null) {
                    LocalDateTime hora = timestamp.toLocalDateTime();
                    enfre.setHora(hora);
                }
                enfre.setEquipoVisitante(cbd.buscarEquipo(rs.getInt("cod_equipo_visitante")));
                listaEnfrentamientos.add(enfre);
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
