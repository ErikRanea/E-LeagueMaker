/**
 * Esta clase se encarga de llamar a los procedimientos almacenados de jornadas
 * @author Erik
 * @since 19/05/2024
 */
package Controlador.ControladoresBD;

import Modelo.Competicion;
import Modelo.Enfrentamiento;
import Modelo.Jornada;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ControladorTJornadas {

    /**
     * Este atributo sirve para conecta con el controlador superior
     * @param cbd
     */
    private ControladorBD cbd;

    private Connection con;

    /**
     * Este atributo sirve para instanciar el objeto de la tabla
     * @param jornada
     */
    private Jornada jornada;
    private ArrayList<Jornada> listaJornadas;



    public ControladorTJornadas(ControladorBD cbd)
    {
        this.cbd = cbd;
        listaJornadas = new ArrayList<>();
    }


    public ArrayList<Jornada> consultarTablaJornadas(int codCompeticion)throws Exception
    {
        try {
            con = cbd.abrirConexion();
            String llamada = "{ ? = call crud_Jornadas.consultar_Jornadas(?) }";
            CallableStatement cs = con.prepareCall(llamada);

            cs.registerOutParameter(1, oracle.jdbc.OracleTypes.CURSOR);
            cs.setInt(2,codCompeticion);

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
               // listaJornadas.add(competi);
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

        return listaJornadas;
    }

    public ArrayList<Jornada> consultarTablaJornadas(Competicion competicion)throws Exception
    {
        try {
            listaJornadas.clear();
            con = cbd.abrirConexion();
            System.out.println("\nConsultado tabla de jornadas");
            String llamada = "{ ? = call crud_Jornadas.consultar_Jornadas(?) }";
            CallableStatement cs = con.prepareCall(llamada);

            cs.registerOutParameter(1, oracle.jdbc.OracleTypes.CURSOR);
            cs.setInt(2,competicion.getCod());

            cs.execute();

            ResultSet rs = (ResultSet) cs.getObject(1);

            while (rs.next()) {
                Jornada jor = new Jornada();
                jor.setCod(rs.getInt("cod"));
                jor.setnJornada(rs.getInt("n_jornada"));
                jor.setCompeticion(competicion);
                if (cbd.consultarEnfrentamientosSinResultado(jor) != null)
                {
                    jor.setListaEnfrentamientos(cbd.consultarEnfrentamientosSinResultado(jor));
                }
                //todo hacer el procedimiento de consulta de resultado

                listaJornadas.add(jor);
            }


            rs.close();
            cs.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("\nError al consultar las competiciones"+e.getMessage() ,e);
        } finally {
            if (con != null) {
                try {
                    cbd.cerrarConexion(con);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return listaJornadas;
    }

    public ArrayList<Jornada> consultarTablaJornadasConResultado(Competicion competicion)throws Exception
    {
        try {
            listaJornadas.clear();
            con = cbd.abrirConexion();
            System.out.println("\nConsultado tabla de jornadas");
            String llamada = "{ ? = call crud_Jornadas.consultar_Jornadas(?) }";
            CallableStatement cs = con.prepareCall(llamada);

            cs.registerOutParameter(1, oracle.jdbc.OracleTypes.CURSOR);
            cs.setInt(2,competicion.getCod());

            cs.execute();

            ResultSet rs = (ResultSet) cs.getObject(1);

            while (rs.next()) {
                Jornada jor = new Jornada();
                jor.setCod(rs.getInt("cod"));
                jor.setnJornada(rs.getInt("n_jornada"));
                jor.setCompeticion(competicion);
                if (cbd.consultarEnfrentamientosConResultado(jor) != null)
                {
                    jor.setListaEnfrentamientos(cbd.consultarEnfrentamientosConResultado(jor));
                }


                listaJornadas.add(jor);
            }


            rs.close();
            cs.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("\nError al consultar las competiciones"+e.getMessage() ,e);
        } finally {
            if (con != null) {
                try {
                    cbd.cerrarConexion(con);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return listaJornadas;
    }


    public Jornada buscarJornada(int cod) throws Exception
    {
        con = cbd.abrirConexion();
        System.out.println("\nBuscando Jornada con código "+cod);
        jornada = new Jornada();
        try {
            String llamada = "{ ? = call crud_jornadas.buscar_jornada(?) }";
            CallableStatement cs = con.prepareCall(llamada);

            cs.registerOutParameter(1, oracle.jdbc.OracleTypes.CURSOR);
            cs.setInt(2, cod);

            cs.execute();

            ResultSet rs = (ResultSet) cs.getObject(1);

            if (rs.next()) {
                Jornada jor = new Jornada();
                jor.setCod(rs.getInt("cod"));
                jor.setnJornada(rs.getInt("n_jornada"));
                jor.setCompeticion(cbd.buscarCompeticion(rs.getInt("cod_competicion")));

            }
            System.out.println("\n"+jornada.getCod()+"Jornada encontrada, sin lista enfrentamientos");
            rs.close();
            cs.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("\nError al buscar la jornada\n\n", e);
        } finally {
            if (con != null) {
                try {
                    cbd.cerrarConexion(con);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return jornada;
    }


}


