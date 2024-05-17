/** Esta clase se encarga de hacer llamadas a las metodos que hay creados en el paquete PL/SQL
 * crud_Usuario
 * @author Erik
 * @since 17/05/2024
 */
package Controlador.ControladoresBD;

import Modelo.Usuario;

import java.sql.*;

public class ControladorTUsuarios {

    /**
     * Este atributo se encarga de conectar con el controlador superior
     * @param cdb
     */
    private ControladorBD cbd;

    private Connection con;
    /**
     * Este atributo permite instanciar el objeto de la tabla
     * @param usuario
     */
    private Usuario usuario;



    public ControladorTUsuarios(ControladorBD cbd)
    {
        this.cbd = cbd;
    }

    /**
     * Este metodo busca el objeto y lo instancia en la clase
     * @param nickname
     * @return Usuario
     * @throws Exception
     */

    public Usuario buscarUsuario(String nickname) throws Exception {
        con = cbd.abrirConexion();
        System.out.println("\nBuscando usuario con nickname "+nickname);
        usuario = new Usuario();
        try {
            String llamada = "{ ? = call crud_Usuarios.consultar_Usuarios(?) }";
            CallableStatement cs = con.prepareCall(llamada);

            cs.registerOutParameter(1, oracle.jdbc.OracleTypes.CURSOR);
            cs.setString(2, nickname);

            cs.execute();

            ResultSet rs = (ResultSet) cs.getObject(1);

            if (rs.next()) {
                usuario.setCod(rs.getInt("cod"));
                usuario.setNickname(rs.getString("nickname"));
                usuario.setPassword(rs.getString("password"));
                usuario.setAdmin(rs.getBoolean("es_admin"));
            }

            rs.close();
            cs.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Error al consultar el usuario", e);
        } finally {
            if (con != null) {
                try {
                    cbd.cerrarConexion(con);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return usuario;
    }



    /**
     * Este metodo se encarga de borrar el objeto instanciado previamente en este controlador.
     * IMPORTANTE:
     *          PREVIAMENTE HAY QUE BUSCAR EL OBJETO PARA QUE BORRE EL OBJETO QUE SE DESEA
     * @author Erik
     * @return String
     * @throws Exception
     */
    public String borrarUsuario() throws Exception {
        con = cbd.abrirConexion();
        System.out.println("\nBorrando Usuario con nombre "+usuario.getNickname());
        try {
            String llamada = "{ call crud_Usuarios.borrar_Usuarios(?) }";
            CallableStatement cs = con.prepareCall(llamada);


            cs.setInt(1, usuario.getCod());

            cs.execute();
            cs.close();

        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Error al borrar usuario", e);
        } finally {
            if (con != null) {
                try {
                    cbd.cerrarConexion(con);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return "Usuario borrado";
    }

    /**
     * Este metodo se encarga de modificar el objeto que le pasamos.
     * Este objeto se instancia en la clase por si se necesita juegar tras cerrar la transacción.
     * IMPORTANTE:
     *            PREVIAMENTE HAY QUE BUSCAR EL OBJETO PARA OBTENER EL COD DEL OBJETO DEBIDO A QUE SE GENERA A TRAVÉS
     *            DE UNA SECUENCIA.
     * @author Erik
     * @param usuario
     * @return
     * @throws Exception
     */

    public String modificarUsuario(Usuario usuario) throws Exception
    {
        con = cbd.abrirConexion();
        System.out.println("\nModificando usuario con nombre " + usuario.getNickname());
        try {
            String llamada = "{ call crud_Usuarios.modificar_Usuarios(?,?,?,?) }";
            CallableStatement cs = con.prepareCall(llamada);

            this.usuario = usuario;
            cs.setInt(1,usuario.getCod());
            cs.setString(2, usuario.getNickname());
            cs.setString(3,usuario.getPassword());
            cs.setInt(4,usuario.isEstadoAbiertoInt());

            cs.execute();
            cs.close();

        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Error al modificar usuario", e);
        } finally {
            if (con != null) {
                try {
                    cbd.cerrarConexion(con);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return "Usuario modificado!";
    }

    /**
     * Este metodo se encarga de insertar el objeto que le pasemos.
     * Este objeto se instancia en la clase por si se necesita juegar tras cerrar la transacción.
     * @author Erik
     * @param usuario
     * @return
     * @throws Exception
     */
    public String insertarUsuario(Usuario usuario) throws Exception {
        con = cbd.abrirConexion();
        System.out.println("\nInsertando usuario con nombre" + usuario.getNickname());
        try {
            String llamada = "{ call crud_Usuario.insertar_Usuario(?,?,?) }";
            CallableStatement cs = con.prepareCall(llamada);

            this.usuario = usuario;

            cs.setString(1, usuario.getNickname());
            cs.setString(2,usuario.getPassword());
            cs.setInt(3,usuario.isEstadoAbiertoInt());

            cs.execute();
            cs.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Error al inserta usuario", e);
        } finally {
            if (con != null) {
                try {
                    cbd.cerrarConexion(con);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return "Usuario insertado";
    }



}
