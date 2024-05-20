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

}


