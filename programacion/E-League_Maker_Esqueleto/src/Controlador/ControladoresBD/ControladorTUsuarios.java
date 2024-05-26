/** Esta clase se encarga de hacer llamadas a las metodos que hay creados en el paquete PL/SQL
 * crud_Usuario
 * @author Erik
 * @since 17/05/2024
 */
package Controlador.ControladoresBD;

import Modelo.Usuario;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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
    private List<Usuario> listaUsuario;



    public ControladorTUsuarios(ControladorBD cbd)
    {
        this.cbd = cbd;
        listaUsuario = new ArrayList<>();
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
            System.out.println("\n Buscando");
            if (rs.next()) {
                usuario.setCod(rs.getInt("cod_usuario"));
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
    public void borrarUsuario(int cod) throws Exception {
        con = cbd.abrirConexion();
        System.out.println("\nBorrando Usuario con nombre "+usuario.getNickname());
        try {
            String llamada = "{ call crud_Usuarios.borrar_Usuarios(?) }";
            CallableStatement cs = con.prepareCall(llamada);


            cs.setInt(1, cod);

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
        System.out.println("Usuario borrado");
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

    public void modificarUsuario(Usuario usuario) throws Exception
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
            cs.setInt(4,usuario.isAdminInt());

            cs.execute();
            cs.close();

        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Error al modificar el usuario", e);
        } finally {
            if (con != null) {
                try {
                    cbd.cerrarConexion(con);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        System.out.println("Usuario modificado!");
    }

    /**
     * Este metodo se encarga de insertar el objeto que le pasemos.
     * Este objeto se instancia en la clase por si se necesita juegar tras cerrar la transacción.
     * @author Erik
     * @param usuario
     * @return
     * @throws Exception
     */
    public void insertarUsuario(Usuario usuario) throws Exception {
        con = cbd.abrirConexion();
        System.out.println("\nInsertando usuario con nombre" + usuario.getNickname());
        try {
            String llamada = "{ call crud_Usuarios.insertar_Usuarios(?,?,?) }";
            CallableStatement cs = con.prepareCall(llamada);

            this.usuario = usuario;

            cs.setString(1, usuario.getNickname());
            cs.setString(2,usuario.getPassword());
            cs.setInt(3,usuario.isAdminInt());

            cs.execute();
            cs.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Error al insertar el usuario", e);
        } finally {
            if (con != null) {
                try {
                    cbd.cerrarConexion(con);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        System.out.println("Usuario insertado");
    }

    public List buscarUsuarios() throws Exception {
        con = cbd.abrirConexion();
        System.out.println("\nBuscando todos los usuario");
        try {
            listaUsuario.clear();
            String llamada = "{ ? = call crud_Usuarios.consultar_todos_usuarios }";
            CallableStatement cs = con.prepareCall(llamada);

            cs.registerOutParameter(1, oracle.jdbc.OracleTypes.CURSOR);

            cs.execute();

            ResultSet rs = (ResultSet) cs.getObject(1);
            System.out.println("\n Buscando");
            while (rs.next()) {
                usuario = new Usuario();
                usuario.setCod(rs.getInt("cod_usuario"));
                usuario.setNickname(rs.getString("nickname"));
                usuario.setPassword(rs.getString("password"));
                usuario.setAdmin(rs.getBoolean("es_admin"));
                listaUsuario.add(usuario);
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

        return listaUsuario;
    }

}
