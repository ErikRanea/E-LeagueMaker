/**
 * Esta clase se encarga de controlar los procedimientos almacenados de la tabla Enfrentamientos
 * @author Erik
 * @since 19/05/2024
 */


package Controlador.ControladoresBD;

import Modelo.Competicion;
import Modelo.Enfrentamiento;
import Modelo.Jornada;
import oracle.jdbc.internal.OracleTypes;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class ControladorTEnfrentamientos {

    private ControladorBD cbd;

    private Connection con;

    private Enfrentamiento enfrentamiento;
    private ArrayList<Enfrentamiento> listaEnfrentamientos;

    public ControladorTEnfrentamientos(ControladorBD cbd){this.cbd = cbd;}



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

    public ArrayList<Enfrentamiento> consultarEnfrentamientosSinResultado(Jornada jornada)throws Exception
    {
        try {
            listaEnfrentamientos = new ArrayList<>();
            con = cbd.abrirConexion();
            System.out.println("\nConsultando enfrentamientos sin resultado de la jornada con cod "+jornada.getCod());
            String llamada = "{ call  CONSULTAR_ENFRENTAMIENTOS_SIN_RESULTADOS(?,?) }";
            CallableStatement cs = con.prepareCall(llamada);

            cs.setInt(1,jornada.getCod());
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
                enfre.setJornada(jornada);
                //    System.out.println("\nEnfrentamiento con cod "+enfre.getCod()+" es de la jornada "+
                //          jornada.getCod());
                listaEnfrentamientos.add(enfre);
            }
            System.out.println("\nEn tabla enfrentamientos devuelve "+listaEnfrentamientos.size()+
                    " elementos\n");

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
        System.out.println("\nLista enfrentamientos en envío\nNumero de elementos "+listaEnfrentamientos.size());
        return listaEnfrentamientos;
    }

    public ArrayList<Enfrentamiento> consultarEnfrentamientosConResultado(Jornada jornada)throws Exception
    {
        try {
            listaEnfrentamientos = new ArrayList<>();
            con = cbd.abrirConexion();
            System.out.println("\nConsultando enfrentamientos sin resultado de la jornada con cod "+jornada.getCod());
            String llamada = "{ call  CONSULTAR_ENFRENTAMIENTOS_con_RESULTADOS(?,?) }";
            CallableStatement cs = con.prepareCall(llamada);

            cs.setInt(1,jornada.getCod());
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
                enfre.setJornada(jornada);
                //    System.out.println("\nEnfrentamiento con cod "+enfre.getCod()+" es de la jornada "+
                //          jornada.getCod());
                listaEnfrentamientos.add(enfre);
            }
            System.out.println("\nEn tabla enfrentamientos devuelve "+listaEnfrentamientos.size()+
                    " elementos\n");

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
        System.out.println("\nLista enfrentamientos en envío\nNumero de elementos "+listaEnfrentamientos.size());
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


}
