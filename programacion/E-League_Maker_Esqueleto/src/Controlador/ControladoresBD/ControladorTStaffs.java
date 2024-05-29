/** Esta clase se encarga de hacer llamadas a las metodos que hay creados en el paquete PL/SQL
 * crud_Staffs
 * @author Erik
 * @since 17/05/2024
 */
package Controlador.ControladoresBD;
import Modelo.Staff;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class ControladorTStaffs {

    /**
     * Este atributo se encarga de conectar con el controlador superior
     * @param cdb
     */
    private ControladorBD cbd;

    private Connection con;

    /**
     * Este atributo permite instanciar el objeto de la tabla
     * @param staff
     */
    private Staff staff;
    private List<Staff> listaStaffs;


    public ControladorTStaffs(ControladorBD cbd)
    {
        this.cbd = cbd;
        listaStaffs = new ArrayList<>();
    }


    /**
     * Este metodo busca el objeto y lo instancia en la clase
     * @param nombre
     * @return staff
     * @throws Exception
     */


    public Staff buscarStaff(String nombre) throws Exception {
        con = cbd.abrirConexion();
        System.out.println("\nBuscando staff con nombre "+nombre);
        staff = new Staff();
        try {
            String llamada = "{ ? = call crud_Staffs.consultar_Staffs_nombre(?) }";
            CallableStatement cs = con.prepareCall(llamada);

            cs.registerOutParameter(1, oracle.jdbc.OracleTypes.CURSOR);
            cs.setString(2, nombre);

            cs.execute();

            ResultSet rs = (ResultSet) cs.getObject(1);

            if (rs.next()) {
                staff.setCod(rs.getInt("cod"));
                staff.setNombre(rs.getString("nombre"));
                staff.setApellido((rs.getString("apellido")));
                staff.setPuesto(rs.getString("puesto"));
                staff.setSalario(rs.getInt("salario"));
                staff.setEquipo(cbd.buscarEquipo(rs.getInt("cod_equipo")));

            }

            rs.close();
            cs.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Error al consultar el staff", e);
        } finally {
            if (con != null) {
                try {
                    cbd.cerrarConexion(con);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return staff;
    }

    /**
     * Es el mismo metodo que el de arriba pero te permite buscar por código
     * @author Erik
     * @param cod
     * @return staff
     * @throws Exception
     */
    public Staff buscarStaff(int cod) throws Exception {
        con = cbd.abrirConexion();
        System.out.println("\nBuscando Staff con cod "+cod);
        staff = new Staff();
        try {
            String llamada = "{ ? = call crud_Staffs.consultar_Staffs_cod(?) }";
            CallableStatement cs = con.prepareCall(llamada);

            cs.registerOutParameter(1, oracle.jdbc.OracleTypes.CURSOR);
            cs.setInt(2, cod);

            cs.execute();

            ResultSet rs = (ResultSet) cs.getObject(1);

            if (rs.next()) {
                staff.setCod(rs.getInt("cod"));
                staff.setNombre(rs.getString("nombre"));
                staff.setApellido((rs.getString("apellido")));
                staff.setPuesto(rs.getString("puesto"));
                staff.setSalario(rs.getInt("salario"));
                staff.setEquipo(cbd.buscarEquipo(rs.getInt("cod_equipo")));

            }

            rs.close();
            cs.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Error al consultar el staff", e);
        } finally {
            if (con != null) {
                try {
                    cbd.cerrarConexion(con);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return staff;
    }

    /**
     * Este metodo se encarga de borrar el objeto instanciado previamente en este controlador.
     * IMPORTANTE:
     *          PREVIAMENTE HAY QUE BUSCAR EL OBJETO PARA QUE BORRE EL OBJETO QUE SE DESEA
     * @author Erik
     * @return String
     * @throws Exception
     */
    public void borrarStaff(int cod) throws Exception {
        con = cbd.abrirConexion();
        System.out.println("\nBorrando staff con nombre "+staff.getNombre());
        try {
            String llamada = "{ call crud_Staffs.borrar_Staffs(?) }";
            CallableStatement cs = con.prepareCall(llamada);


            cs.setInt(1, cod);

            cs.execute();
            cs.close();

        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Error al borrar staff", e);
        } finally {
            if (con != null) {
                try {
                    cbd.cerrarConexion(con);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        System.out.println("Staff borrado!");
    }

    /**
     * Este metodo se encarga de modificar el objeto que le pasamos.
     * Este objeto se instancia en la clase por si se necesita juegar tras cerrar la transacción.
     * IMPORTANTE:
     *            PREVIAMENTE HAY QUE BUSCAR EL OBJETO PARA OBTENER EL COD DEL OBJETO DEBIDO A QUE SE GENERA A TRAVÉS
     *            DE UNA SECUENCIA.
     * @author Erik
     * @param staff
     * @return string
     * @throws Exception
     */

    public void modificarStaff(Staff staff) throws Exception
    {
        con = cbd.abrirConexion();
        System.out.println("\nModificando staff con nombre " + staff.getNombre());
        try {
            String llamada = "{ call crud_Staffs.modificar_Staffs(?,?,?,?,?) }";
            CallableStatement cs = con.prepareCall(llamada);

            this.staff = staff;
            cs.setInt(1,staff.getCod());
            cs.setString(2, staff.getNombre());
            cs.setString(3,staff.getApellido());
            cs.setString(4,staff.getPuesto());
            cs.setInt(5,staff.getSalario());
            cs.setInt(6, staff.getEquipo().getCod());





            cs.execute();
            cs.close();

        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Error al modificar el staff", e);
        } finally {
            if (con != null) {
                try {
                    cbd.cerrarConexion(con);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        System.out.println("Staff modificado!");
    }

    /**
     * Este metodo se encarga de insertar el objeto que le pasemos.
     * Este objeto se instancia en la clase por si se necesita juegar tras cerrar la transacción.
     * @author Erik
     * @param staff
     * @return string
     * @throws Exception
     */
    public void insertarStaff(Staff staff) throws Exception {
        con = cbd.abrirConexion();
        System.out.println("\nInsertando Staff con nickname" + staff.getNombre() );
        try {
            String llamada = "{ call crud_Staffs.insertar_Staffs(?,?,?,?,?)}";
            CallableStatement cs = con.prepareCall(llamada);

            this.staff = staff;
            cs.setString(1, staff.getNombre());
            cs.setString(2,staff.getApellido());
            cs.setString(3,staff.getPuesto());
            cs.setInt(4,staff.getSalario());
            cs.setInt(5,staff.getEquipo().getCod());


            cs.execute();
            cs.close();

        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Error al insertar el staff", e);
        } finally {
            if (con != null) {
                try {
                    cbd.cerrarConexion(con);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        System.out.println("Staff insertado!");
    }

    public List buscarStaffs() throws Exception {
        con = cbd.abrirConexion();
        System.out.println("\nBuscando todos los miembros del staff");
        try {
            listaStaffs.clear();
            String llamada = "{ ? = call crud_Staffs.consultar_todos_staffs() }";
            CallableStatement cs = con.prepareCall(llamada);

            cs.registerOutParameter(1, oracle.jdbc.OracleTypes.CURSOR);

            cs.execute();

            ResultSet rs = (ResultSet) cs.getObject(1);

            while (rs.next()) {
                staff =new Staff();
                staff.setCod(rs.getInt("cod"));
                staff.setNombre(rs.getString("nombre"));
                staff.setApellido((rs.getString("apellido")));
                staff.setPuesto(rs.getString("puesto"));
                staff.setSalario(rs.getInt("salario"));
                staff.setEquipo(cbd.buscarEquipo(rs.getInt("cod_equipo")));
                listaStaffs.add(staff);
            }

            rs.close();
            cs.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Error al consultar el staff", e);
        } finally {
            if (con != null) {
                try {
                    cbd.cerrarConexion(con);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return listaStaffs;
    }




}
