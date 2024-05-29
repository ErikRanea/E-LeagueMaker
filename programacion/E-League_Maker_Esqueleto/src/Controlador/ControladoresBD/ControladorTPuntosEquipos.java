package Controlador.ControladoresBD;

import Modelo.Patrocinador;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

public class ControladorTPuntosEquipos {

    /**
     * Este atributo se encarga de conectar con el controlador superior
     * @param cdb
     */

    private ControladorBD cbd;

    private Connection con;

    public ControladorTPuntosEquipos(ControladorBD cbd) {
        this.cbd = cbd;
    }

    public void inscribirEquipo(int codCompeti, int codEquipo) throws Exception {
        con = cbd.abrirConexion();
        System.out.println("\nInsertando equipo " + codEquipo + " en la competición " + codEquipo);
        try {
            String llamada = "{ call crud_Patrocinadores.insertar_puntos_equipos(?,?) }";
            CallableStatement cs = con.prepareCall(llamada);

            cs.setInt(1, codCompeti);
            cs.setInt(2,codEquipo);

            cs.execute();
            cs.close();

        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Error al inscribir el equipo", e);
        } finally {
            if (con != null) {
                try {
                    cbd.cerrarConexion(con);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        System.out.println("Equipo inscrito");
    }

    public void rescindirEquipo(int codCompeti, int codEquipo) throws Exception {
        con = cbd.abrirConexion();
        System.out.println("\nRescindiendo equipo " + codEquipo + " en la competición " + codEquipo);
        try {
            String llamada = "{ call crud_Patrocinadores.borrar_puntos_equipos(?,?) }";
            CallableStatement cs = con.prepareCall(llamada);

            cs.setInt(1, codEquipo);
            cs.setInt(2,codCompeti);

            cs.execute();
            cs.close();

        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Error al rescindir el equipo", e);
        } finally {
            if (con != null) {
                try {
                    cbd.cerrarConexion(con);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        System.out.println("Equipo rescindido");
    }
}
