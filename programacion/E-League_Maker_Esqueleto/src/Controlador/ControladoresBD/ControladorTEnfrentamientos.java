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

    public ArrayList<Enfrentamiento> consultarEnfrentamientosConResultados(int codJornada) throws Exception {
        ArrayList<Enfrentamiento> listaEnfrentamientos = new ArrayList<>();
        try {
            con = cbd.abrirConexion();

            String llamada = "{ call con_enfre_ulti_jornada(?) }";
            CallableStatement cs = con.prepareCall(llamada);
            cs.registerOutParameter(1, oracle.jdbc.OracleTypes.CURSOR);
            //cs.setInt(2, codJornada);
            cs.execute();

            ResultSet rs = (ResultSet) cs.getObject(1);

            while (rs.next()) {
                Enfrentamiento enfrentamiento = new Enfrentamiento();
                enfrentamiento.setCod(rs.getInt("cod"));
                enfrentamiento.setEquipoLocal(cbd.buscarEquipo(rs.getInt("cod_equipo_local")));
                enfrentamiento.setEquipoVisitante(cbd.buscarEquipo(rs.getInt("cod_equipo_visitante")));

                // Convertir Date a LocalDateTime
                Timestamp timestamp = rs.getTimestamp("hora");
                if (timestamp != null) {
                    LocalDateTime hora = timestamp.toLocalDateTime();
                    enfrentamiento.setHora(hora);
                }


                String ganaLocalStr = rs.getString("gana_local");
                boolean ganaLocal = false;
                if (ganaLocalStr != null) {
                    ganaLocal = ganaLocalStr.equalsIgnoreCase("true") || ganaLocalStr.equals("1") || ganaLocalStr.equalsIgnoreCase("yes");
                }
                enfrentamiento.setGanaLocal(ganaLocal);

                listaEnfrentamientos.add(enfrentamiento);

            }

            rs.close();
            cs.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Error al consultar los enfrentamientos", e);
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

    public boolean actualizarResultados(int cod,int resultado) throws Exception
    {
        boolean okey = false;
        try {
            listaEnfrentamientos = new ArrayList<>();
            con = cbd.abrirConexion();
            String llamada = "{ call  insertar_resultado(?,?) }";
            CallableStatement cs = con.prepareCall(llamada);

            cs.setInt(1,cod);
            cs.setInt(2,resultado);
            cs.execute();

            System.out.println("Se ha insertado correctamente el resultado del enfrentamiento "+cod);
            cs.close();
            okey = false;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Error al insertar resultado \n"+e.getMessage(), e);
        } finally {
            if (con != null) {
                try {
                    cbd.cerrarConexion(con);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return okey;
    }



}
