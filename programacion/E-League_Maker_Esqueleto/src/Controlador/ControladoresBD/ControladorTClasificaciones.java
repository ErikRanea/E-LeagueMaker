package Controlador.ControladoresBD;

import Modelo.Clasificacion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ControladorTClasificaciones {

    private ControladorBD cbd;
    private Connection con;

    private Clasificacion clasificacion;
    private ArrayList<Clasificacion> listaClasificacion;


    public ControladorTClasificaciones(ControladorBD cbd)
    {
        this.cbd = cbd;
        listaClasificacion = new ArrayList<>();
    }

    /**
     * Obtenemos la clasificacion haciendo una select desde la base de datos
     * @author Rodrigo
     */

    public ArrayList<Clasificacion> obtenerClasificacion(int codCompeticion) throws Exception
    {

        con = cbd.abrirConexion();
        System.out.println("\nBuscando competicion con cod "+ codCompeticion);
        ArrayList<Clasificacion> clasificaciones = new ArrayList<>();
        try {
            String sql = " SELECT * FROM Clasificacion WHERE cod_competicion = ? ";

            PreparedStatement statement = con.prepareStatement(sql);
            statement.setInt(1,codCompeticion);
            ResultSet rs = statement.executeQuery();



            while (rs.next()) {
                Clasificacion clasificacion = new Clasificacion();
                clasificacion.setPosicion(rs.getInt("posicion"));
                clasificacion.setEquipo(cbd.buscarEquipo(rs.getInt("cod_equipo")));
                clasificacion.setCompeticion(cbd.buscarCompeticion(rs.getInt("cod_competicion")));
                clasificacion.setPuntos(rs.getInt("puntos"));
                clasificaciones.add(clasificacion);
            }

            rs.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Error al consultar el competicion", e);
        } finally {
            if (con != null) {
                try {
                    cbd.cerrarConexion(con);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return clasificaciones;
    }





}
