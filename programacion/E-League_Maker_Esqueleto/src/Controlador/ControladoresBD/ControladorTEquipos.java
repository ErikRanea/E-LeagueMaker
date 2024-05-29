/** Esta clase se encarga de hacer llamadas a las metodos que hay creados en el paquete PL/SQL
 * crud_Equipos
 * @author Erik
 * @since 16/05/2024
 */
package Controlador.ControladoresBD;

import Modelo.Equipo;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ControladorTEquipos {


    /**
     * Este atributo se encarga de conectar con el controlador superior
     * @param cdb
     */
    private ControladorBD cbd;

    private Connection con;
    /**
     * Este atributo permite instanciar el objeto de la tabla
     * @param equipo
     */
    private Equipo equipo;
    private List<Equipo> listaEquipos;

    public ControladorTEquipos(ControladorBD cbd)
    {
        this.cbd = cbd;
        listaEquipos = new ArrayList<>();
    }

    /**
     * Este metodo busca el objeto y lo instancia en la clase
     * @param nombre
     * @return equipo
     * @throws Exception
     */
    public Equipo buscarEquipo(String nombre) throws Exception {
        con = cbd.abrirConexion();
        System.out.println("\nBuscando equipo con nombre "+nombre);
        equipo = new Equipo();
        try {
            String llamada = "{ ? = call crud_Equipos.consultar_Equipo_nombre(?) }";
            CallableStatement cs = con.prepareCall(llamada);

            cs.registerOutParameter(1, oracle.jdbc.OracleTypes.CURSOR);
            cs.setString(2, nombre);

            cs.execute();

            ResultSet rs = (ResultSet) cs.getObject(1);

            if (rs.next()) {
                equipo.setCod(rs.getInt("cod"));
                equipo.setNombre(rs.getString("nombre"));
                equipo.setFechaFundacion(rs.getDate("fecha_fundacion").toLocalDate());
            }

            rs.close();
            cs.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Error al consultar el equipo", e);
        } finally {
            if (con != null) {
                try {
                    cbd.cerrarConexion(con);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return equipo;
    }

    /**
     * Es el mismo metodo que el de arriba pero te permite buscar por código
     * @author Erik
     * @param cod
     * @return equipo
     * @throws Exception
     */
    public Equipo buscarEquipo(int cod) throws Exception {
        con = cbd.abrirConexion();
        System.out.println("\nBuscando equipo con código "+cod);
        equipo = new Equipo();
        try {
            String llamada = "{ ? = call crud_Equipos.consultar_Equipo_cod(?) }";
            CallableStatement cs = con.prepareCall(llamada);

            cs.registerOutParameter(1, oracle.jdbc.OracleTypes.CURSOR);
            cs.setInt(2, cod);

            cs.execute();

            ResultSet rs = (ResultSet) cs.getObject(1);

            if (rs.next()) {
                equipo.setCod(rs.getInt("cod"));
                equipo.setNombre(rs.getString("nombre"));
                equipo.setFechaFundacion(rs.getDate("fecha_fundacion").toLocalDate());
            }

            rs.close();
            cs.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Error al consultar el equipo", e);
        } finally {
            if (con != null) {
                try {
                    cbd.cerrarConexion(con);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return equipo;
    }

    /**
     * Este metodo se encarga de borrar el objeto instanciado previamente en este controlador.
     * IMPORTANTE:
     *          PREVIAMENTE HAY QUE BUSCAR EL OBJETO PARA QUE BORRE EL OBJETO QUE SE DESEA
     * @author Erik
     * @return String
     * @throws Exception
     */
    public void borrarEquipo(int cod) throws Exception {
        con = cbd.abrirConexion();
        System.out.println("\nBorrando Equipo con nombre "+equipo.getNombre());
        try {
            String llamada = "{ call crud_Equipos.borrar_equipo(?) }";
            CallableStatement cs = con.prepareCall(llamada);


            cs.setInt(1, cod);

            cs.execute();
            cs.close();

        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Error al borrar equipo", e);
        } finally {
            if (con != null) {
                try {
                    cbd.cerrarConexion(con);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        System.out.println("equipo borrado");
    }

    /**
     * Este metodo se encarga de modificar el objeto que le pasamos.
     * Este objeto se instancia en la clase por si se necesita juegar tras cerrar la transacción.
     * IMPORTANTE:
     *            PREVIAMENTE HAY QUE BUSCAR EL OBJETO PARA OBTENER EL COD DEL OBJETO DEBIDO A QUE SE GENERA A TRAVÉS
     *            DE UNA SECUENCIA.
     * @author Erik
     * @param equipo
     * @return
     * @throws Exception
     */

    public void modificarEquipo(Equipo equipo) throws Exception
    {
        con = cbd.abrirConexion();
        System.out.println("\nModificando equipo con nombre " + equipo.getNombre());
        try {
            String llamada = "{ call crud_Equipos.modificar_equipo(?,?,?) }";
            CallableStatement cs = con.prepareCall(llamada);

            this.equipo = equipo;
            cs.setInt(1,equipo.getCod());
            cs.setString(2, equipo.getNombre());
            cs.setDate(3, Date.valueOf(equipo.getFechaFundacion()));

            cs.execute();
            cs.close();

        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Error al modificar el equipo", e);
        } finally {
            if (con != null) {
                try {
                    cbd.cerrarConexion(con);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        System.out.println("Equipo modificado!");
    }

    /**
     * Este metodo se encarga de insertar el objeto que le pasemos.
     * Este objeto se instancia en la clase por si se necesita juegar tras cerrar la transacción.
     * @author Erik
     * @param equipo
     * @return
     * @throws Exception
     */
    public void insertarEquipo(Equipo equipo) throws Exception {
        con = cbd.abrirConexion();
        System.out.println("\nInsertando equipo con nombre" + equipo.getNombre());
        try {
            String llamada = "{ call crud_Equipos.insertar_equipo(?,?) }";
            CallableStatement cs = con.prepareCall(llamada);

            this.equipo = equipo;
            cs.setString(1, equipo.getNombre());
            cs.setDate(2, Date.valueOf(equipo.getFechaFundacion()));

            cs.execute();
            cs.close();

        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Error al insertar el equipo", e);
        } finally {
            if (con != null) {
                try {
                    cbd.cerrarConexion(con);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        System.out.println("Equipo insertado");
    }

    public List buscarEquipos() throws Exception {
        con = cbd.abrirConexion();
        try {
            listaEquipos.clear();
            String llamada = "{ ? = call crud_Equipos.consultar_todos_equipos() }";
            CallableStatement cs = con.prepareCall(llamada);

            cs.registerOutParameter(1, oracle.jdbc.OracleTypes.CURSOR);

            cs.execute();

            ResultSet rs = (ResultSet) cs.getObject(1);

            while (rs.next()) {
                equipo = new Equipo();
                equipo.setCod(rs.getInt("cod"));
                equipo.setNombre(rs.getString("nombre"));
                equipo.setFechaFundacion(rs.getDate("fecha_fundacion").toLocalDate());
                listaEquipos.add(equipo);
            }

            rs.close();
            cs.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Error al consultar el equipo", e);
        } finally {
            if (con != null) {
                try {
                    cbd.cerrarConexion(con);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return listaEquipos;
    }

    public List buscarEquiposInscribir(int cod) throws Exception {
        con = cbd.abrirConexion();
        System.out.println("\nBuscando equipos que no esten en la competición "+cod);
        try {
            listaEquipos.clear();
            String llamada = "{ ? = call crud_Equipos.equipos_en_competicion(?) }";
            CallableStatement cs = con.prepareCall(llamada);

            cs.registerOutParameter(1, oracle.jdbc.OracleTypes.CURSOR);
            cs.setInt(2, cod);

            cs.execute();

            ResultSet rs = (ResultSet) cs.getObject(1);

            while (rs.next()) {
                equipo = new Equipo();
                equipo.setCod(rs.getInt("cod"));
                equipo.setNombre(rs.getString("nombre"));
                equipo.setFechaFundacion(rs.getDate("fecha_fundacion").toLocalDate());
                listaEquipos.add(equipo);
            }

            rs.close();
            cs.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Error al consultar el equipo", e);
        } finally {
            if (con != null) {
                try {
                    cbd.cerrarConexion(con);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return listaEquipos;
    }

    public List buscarEquiposRescindir(int cod) throws Exception {
        con = cbd.abrirConexion();
        System.out.println("\nBuscando equipos que esten en la competición "+cod);
        try {
            listaEquipos.clear();
            String llamada = "{ ? = call crud_Equipos.equipos_sin_competicion(?) }";
            CallableStatement cs = con.prepareCall(llamada);

            cs.registerOutParameter(1, oracle.jdbc.OracleTypes.CURSOR);
            cs.setInt(2, cod);

            cs.execute();

            ResultSet rs = (ResultSet) cs.getObject(1);

            while (rs.next()) {
                equipo = new Equipo();
                equipo.setCod(rs.getInt("cod"));
                equipo.setNombre(rs.getString("nombre"));
                equipo.setFechaFundacion(rs.getDate("fecha_fundacion").toLocalDate());
                listaEquipos.add(equipo);
            }

            rs.close();
            cs.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Error al consultar el equipo", e);
        } finally {
            if (con != null) {
                try {
                    cbd.cerrarConexion(con);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return listaEquipos;
    }




}
