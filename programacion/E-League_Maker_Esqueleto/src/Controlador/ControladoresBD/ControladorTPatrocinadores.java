/** Esta clase se encarga de hacer llamadas a las metodos que hay creados en el paquete PL/SQL
 * crud_Pratrocinadores
 * @author Erik
 * @since 17/05/2024
 */
package Controlador.ControladoresBD;

import Modelo.Patrocinador;

import java.sql.*;

public class ControladorTPatrocinadores {


    private ControladorBD cbd;


    private Connection con;

    /**
     * Este atributo permite instanciar el objeto de la tabla
     * @param patrocinador
     */
    private Patrocinador patrocinador;


    public ControladorTPatrocinadores(ControladorBD cbd)
    {
        this.cbd = cbd;
    }



    /**
     * Este metodo busca el objeto y lo instancia en la clase
     * @param nombre
     * @return patrocinador
     * @throws Exception
     */
    public Patrocinador buscarPatrocinador(String nombre) throws Exception {
        con = cbd.abrirConexion();
        System.out.println("\nBuscando patrocinador con nombre "+nombre);
        patrocinador = new Patrocinador();
        try {
            String llamada = "{ ? = call crud_Patrocinadores.consultar_Patrocinadores_nombre(?) }";
            CallableStatement cs = con.prepareCall(llamada);

            cs.registerOutParameter(1, oracle.jdbc.OracleTypes.CURSOR);
            cs.setString(2, nombre);

            cs.execute();

            ResultSet rs = (ResultSet) cs.getObject(1);

            if (rs.next()) {
                patrocinador.setCod(rs.getInt("cod_Patrocinador"));
                patrocinador.setNombre(rs.getString("nombre"));
                patrocinador.setEquipo(cbd.buscarEquipo(rs.getInt("cod_equipo")));
            }

            rs.close();
            cs.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Error al consultar el patrocinador", e);
        } finally {
            if (con != null) {
                try {
                    cbd.cerrarConexion(con);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return patrocinador;
    }

    /**
     * Es el mismo metodo que el de arriba pero te permite buscar por código
     * @author Erik
     * @param cod
     * @return patrocinador
     * @throws Exception
     */
    public Patrocinador buscarPatrocinador(int cod) throws Exception {
        con = cbd.abrirConexion();
        System.out.println("\nBuscando patrocinador con código "+cod);
        patrocinador = new Patrocinador();
        try {
            String llamada = "{ ? = call crud_Patrocinadores.consultar_Patrocinadores_cod(?) }";
            CallableStatement cs = con.prepareCall(llamada);

            cs.registerOutParameter(1, oracle.jdbc.OracleTypes.CURSOR);
            cs.setInt(2, cod);

            cs.execute();

            ResultSet rs = (ResultSet) cs.getObject(1);

            if (rs.next()) {
                patrocinador.setCod(rs.getInt("cod_Patrocinador"));
                patrocinador.setNombre(rs.getString("nombre"));
                patrocinador.setEquipo(cbd.buscarEquipo(rs.getInt("cod_equipo")));
            }

            rs.close();
            cs.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Error al consultar el patrocinador", e);
        } finally {
            if (con != null) {
                try {
                    cbd.cerrarConexion(con);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return patrocinador;
    }

    /**
     * Este metodo se encarga de borrar el objeto instanciado previamente en este controlador.
     * IMPORTANTE:
     *          PREVIAMENTE HAY QUE BUSCAR EL OBJETO PARA QUE BORRE EL OBJETO QUE SE DESEA
     * @author Erik
     * @return String
     * @throws Exception
     */
    public String borrarPatrocinador() throws Exception {
        con = cbd.abrirConexion();
        System.out.println("\nBorrando patrocinador con nombre "+patrocinador.getNombre());
        try {
            String llamada = "{ call crud_Patrocinadores.borrar_Patrocinadores(?) }";
            CallableStatement cs = con.prepareCall(llamada);


            cs.setInt(1, patrocinador.getCod());

            cs.execute();
            cs.close();

        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Error al borrar patrocinador", e);
        } finally {
            if (con != null) {
                try {
                    cbd.cerrarConexion(con);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return "patrocinador borrado";
    }

    /**
     * Este metodo se encarga de modificar el objeto que le pasamos.
     * Este objeto se instancia en la clase por si se necesita juegar tras cerrar la transacción.
     * IMPORTANTE:
     *            PREVIAMENTE HAY QUE BUSCAR EL OBJETO PARA OBTENER EL COD DEL OBJETO DEBIDO A QUE SE GENERA A TRAVÉS
     *            DE UNA SECUENCIA.
     * @author Erik
     * @param patrocinador
     * @return
     * @throws Exception
     */

    public String modificarPatrocinador(Patrocinador patrocinador) throws Exception
    {
        con = cbd.abrirConexion();
        System.out.println("\nModificando patrocinador con nombre " + patrocinador.getNombre());
        try {
            String llamada = "{ call crud_Patrocinadores.modificar_Patrocinadores(?,?,?) }";
            CallableStatement cs = con.prepareCall(llamada);

            this.patrocinador = patrocinador;
            cs.setInt(1,patrocinador.getCod());
            cs.setString(2, patrocinador.getNombre());
            cs.setInt(3,patrocinador.getEquipo().getCod());
            cs.execute();
            cs.close();

        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Error al modificar patrocinador", e);
        } finally {
            if (con != null) {
                try {
                    cbd.cerrarConexion(con);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return "Patrocinador modificado!";
    }

    /**
     * Este metodo se encarga de insertar el objeto que le pasemos.
     * Este objeto se instancia en la clase por si se necesita juegar tras cerrar la transacción.
     * @author Erik
     * @param patrocinador
     * @return
     * @throws Exception
     */
    public String insertarEPatrocinador(Patrocinador patrocinador) throws Exception {
        con = cbd.abrirConexion();
        System.out.println("\nInsertando patrocinador con nombre" + patrocinador.getNombre());
        try {
            String llamada = "{ call crud_Patrocinadores.insertar_Patrocinadores(?,?) }";
            CallableStatement cs = con.prepareCall(llamada);

            this.patrocinador = patrocinador;
            cs.setString(1, patrocinador.getNombre());
            cs.setInt(2,patrocinador.getEquipo().getCod());

            cs.execute();
            cs.close();

        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Error al inserta Patrocinador", e);
        } finally {
            if (con != null) {
                try {
                    cbd.cerrarConexion(con);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return "Patrocinador insertado";
    }











}
