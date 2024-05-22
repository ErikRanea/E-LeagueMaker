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
        this.cbd = cbd;listaJornadas = new ArrayList<>();
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
                Jornada jor = new Jornada();
                jor.setCod(rs.getInt("cod"));
                jor.setnJornada(rs.getInt("n_jornada"));
                jor.setCompeticion(cbd.buscarCompeticion(rs.getInt("cod_competicion")));
                jor.setListaEnfrentamientos(cbd.consultarEnfrentamientosSinResultado(jor.getCod()));
                listaJornadas.add(jor);
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


