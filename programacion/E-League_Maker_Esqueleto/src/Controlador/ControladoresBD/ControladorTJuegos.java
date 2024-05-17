/** Esta clase se encarga de hacer llamadas a las metodos que hay creados en el paquete PL/SQL
 * crud_Juegos
 * @author Erik
 * @since 16/05/2024
 */
package Controlador.ControladoresBD;

import Modelo.Juego;

import java.sql.*;

public class ControladorTJuegos {

    /**
     * Este atributo se encarga de conectar con el controlador superior
     * @param cdb
     */
    private ControladorBD cbd;

    private Connection con;

    /**
     * Este atributo se encarga de permitir instaciar el objeto de la tabla
     * @param juego
     */
    private Juego juego;

    public ControladorTJuegos(ControladorBD cbd)
    {
        this.cbd = cbd;

    }

    /**
     * Este metodo se encarga de buscar un juego por su nombre y devolver un objeto de Juego
     * @author Erik
     * @param nombre
     * @return juego
     * @throws Exception
     */
    public Juego buscarJuego(String nombre) throws Exception {
        con = cbd.abrirConexion();
        System.out.println("\nBuscando Juego con nombre "+nombre);
        juego = new Juego();
        try {
            String llamada = "{ ? = call crud_Juegos.consultar_juego_nombre(?) }";
            CallableStatement cs = con.prepareCall(llamada);

            cs.registerOutParameter(1, oracle.jdbc.OracleTypes.CURSOR);
            cs.setString(2, nombre);

            cs.execute();

            ResultSet rs = (ResultSet) cs.getObject(1);

            if (rs.next()) {
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
    /**
     * Es el mismo metodo que el de arriba pero te permite buscar por código
     * @author Erik
     * @param cod
     * @return juego
     * @throws Exception
     */
    public Juego buscarJuego(int cod) throws Exception {
        con = cbd.abrirConexion();
        System.out.println("\nBuscando Juego con código "+cod);
        juego = new Juego();
        try {
            String llamada = "{ ? = call crud_Juegos.consultar_juego_cod(?) }";
            CallableStatement cs = con.prepareCall(llamada);

            cs.registerOutParameter(1, oracle.jdbc.OracleTypes.CURSOR);
            cs.setInt(2, cod);

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

    /**
     * Este metodo se encarga de borrar el objeto instanciado previamente en este controlador.
     * IMPORTANTE:
     *          PREVIAMENTE HAY QUE BUSCAR EL OBJETO PARA QUE BORRE EL OBJETO QUE SE DESEA
     * @author Erik
     * @return String
     * @throws Exception
     */
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

    /**
     * Este metodo se encarga de modificar el juego que le pasamos.
     * Este juego se instancia en la clase por si se necesita juegar tras cerrar la transacción.
     * IMPORTANTE:
     *            PREVIAMENTE HAY QUE BUSCAR EL OBJETO PARA OBTENER EL COD DE JUEGO DEBIDO A QUE SE GENERA A TRAVÉS
     *            DE UNA SECUENCIA.
     * @author Erik
     * @param juego
     * @return
     * @throws Exception
     */

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

    /**
     * Este metodo se encarga de insertar el juego que le pasemos.
     * Este juego se instancia en la clase por si se necesita juegar tras cerrar la transacción.
     * @author Erik
     * @param juego
     * @return
     * @throws Exception
     */
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
