/**
 * Esta clase se encarga de hacer las llamadas los procedimientos de la bases de datos
 * @author Erik
 * @since 16/05/2024
 */
package Controlador.ControladoresBD;

import Modelo.Juego;

import java.sql.*;

public class ControladorTJuegos {

    /**
     * Este atributo se encarga de conectar con el controlador superior
     */
    private ControladorBD cbd;

    private Connection con;

    private Juego juego;

    public ControladorTJuegos(ControladorBD cbd)
    {
        this.cbd = cbd;

    }

    public Juego buscarJuego(String nombre) throws Exception {
        con = cbd.abrirConexion();
        System.out.println("\nBuscando Juego con nombre "+nombre);
        juego = new Juego();
        try {
            String llamada = "{ ? = call crud_Juegos.consultar_juego(?) }";
            CallableStatement cs = con.prepareCall(llamada);

            cs.registerOutParameter(1, oracle.jdbc.OracleTypes.CURSOR);
            cs.setString(2, nombre);

            cs.execute();

            ResultSet rs = (ResultSet) cs.getObject(1);

            if (rs.next()) {
                juego = new Juego();
                juego.setCod(rs.getInt("cod"));
                juego.setNombre(rs.getString("nombre"));
                juego.setDesarrolladora(rs.getString("desarrolladora"));
                juego.setFechaLanzamiento(rs.getDate("fecha_lanzamiento").toLocalDate());
            }

            rs.close();
            cs.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Error al consultar el juego", e);
        } finally {
            if (con != null) {
                try {
                    cbd.cerrarConexion(con);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return juego;
    }
    public String borrarJuego() throws Exception {
        con = cbd.abrirConexion();
        System.out.println("\nBorrando Juego con nombre "+juego.getNombre());
        try {
            String llamada = "{ call crud_Juegos.borrar_juego(?) }";
            CallableStatement cs = con.prepareCall(llamada);


            cs.setInt(1, juego.getCod());

            cs.execute();
            cs.close();

        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Error al borrar juego", e);
        } finally {
            if (con != null) {
                try {
                    cbd.cerrarConexion(con);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return "Juego borrado";
    }

    public String modificarJuego(Juego juego) throws Exception
    {
        con = cbd.abrirConexion();
        System.out.println("\nModificando juego con nombre " + juego.getNombre());
        try {
            String llamada = "{ call crud_Juegos.modificar_juego(?,?,?,?) }";
            CallableStatement cs = con.prepareCall(llamada);

            this.juego = juego;
            cs.setInt(1,juego.getCod());
            cs.setString(2, juego.getNombre());
            cs.setString(3,juego.getDesarrolladora());
            cs.setDate(4, Date.valueOf(juego.getFechaLanzamiento()));

            cs.execute();
            cs.close();

        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Error al modificar juego", e);
        } finally {
            if (con != null) {
                try {
                    cbd.cerrarConexion(con);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return "Juego modificado!";
    }
    public String insertarJuego(Juego juego) throws Exception {
        con = cbd.abrirConexion();
        System.out.println("\nInsertando juego con nombre" + juego.getNombre());
        try {
            String llamada = "{ call crud_Juegos.insertar_juego(?,?,?) }";
            CallableStatement cs = con.prepareCall(llamada);

            this.juego = juego;
            cs.setString(1, juego.getNombre());
            cs.setString(2,juego.getDesarrolladora());
            cs.setDate(3, Date.valueOf(juego.getFechaLanzamiento()));

            cs.execute();
            cs.close();

        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Error al inserta juego", e);
        } finally {
            if (con != null) {
                try {
                    cbd.cerrarConexion(con);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return "Juego insertado";
    }



}
