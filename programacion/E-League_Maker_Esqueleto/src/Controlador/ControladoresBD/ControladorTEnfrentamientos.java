/**
 * Esta clase se encarga de controlar los procedimientos almacenados de la tabla Enfrentamientos
 * @author Erik
 * @since 19/05/2024
 */


package Controlador.ControladoresBD;

import Modelo.Competicion;
import Modelo.Enfrentamiento;
import Modelo.Jornada;
import oracle.jdbc.OracleTypes;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class ControladorTEnfrentamientos {

    private ControladorBD cbd;

    private Connection con;

    private Enfrentamiento enfrentamiento;
    private ArrayList<Enfrentamiento> listaEnfrentamientos;

    public ControladorTEnfrentamientos(ControladorBD cbd){this.cbd = cbd;listaEnfrentamientos = new ArrayList<>();}

    public ArrayList<Enfrentamiento> consultarEnfrentamientosSinResultado(int codJornada)throws Exception
    {
        try {
            con = cbd.abrirConexion();
            String llamada = "{ call  CONSULTAR_ENFRENTAMIENTOS_SIN_RESULTADOS(?,?) }";
            CallableStatement cs = con.prepareCall(llamada);

            cs.setInt(1,codJornada);
            cs.registerOutParameter(2, OracleTypes.CURSOR);
            cs.execute();

            ResultSet rs = (ResultSet) cs.getObject(2);


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
                enfre.setJornada(cbd.buscarJo);
                listaEnfrentamientos.add(enfre);
            }


            rs.close();
            cs.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Error al consultar los enfrentamientos vacíos\n"+e.getMessage(), e);
        } finally {
            if (con != null) {
                try {
                    cbd.cerrarConexion(con);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        System.out.println("Lista enfrentamientos en envío\nNumero de elementos "+listaEnfrentamientos.size());
        return listaEnfrentamientos;
    }



}
