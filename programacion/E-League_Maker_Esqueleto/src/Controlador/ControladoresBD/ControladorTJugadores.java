/** Esta clase se encarga de hacer llamadas a las metodos que hay creados en el paquete PL/SQL
 * crud_Jugadores
 * @author Erik
 * @since 17/05/2024
 */
package Controlador.ControladoresBD;


import Modelo.Jugador;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ControladorTJugadores {
    /**
     * Este atributo se encarga de conectar con el controlador superior
     * @param cdb
     */

    private ControladorBD cbd;

    private Connection con;

    /**
     * Este atributo permite instanciar el objeto de la tabla
     * @param jugador
     */
    private Jugador jugador;
    private List<Jugador> listaJugadores;
    public ControladorTJugadores(ControladorBD cbd)
    {
        this.cbd = cbd;
        listaJugadores = new ArrayList<>();
    }


    /**
     * Este metodo busca el objeto y lo instancia en la clase
     * @param nickname
     * @return jugador
     * @throws Exception
     */


    public Jugador buscarJugador(String nickname) throws Exception {
        con = cbd.abrirConexion();
        System.out.println("\nBuscando jugador con nickname "+nickname);
        jugador = new Jugador();
        try {
            String llamada = "{ ? = call crud_Jugadores.consultar_Jugadores_nickname(?) }";
            CallableStatement cs = con.prepareCall(llamada);

            cs.registerOutParameter(1, oracle.jdbc.OracleTypes.CURSOR);
            cs.setString(2, nickname);

            cs.execute();

            ResultSet rs = (ResultSet) cs.getObject(1);

            if (rs.next()) {
                jugador.setCod(rs.getInt("cod"));
                jugador.setNombre(rs.getString("nombre"));
                jugador.setApellido((rs.getString("apellido")));
                jugador.setRol(rs.getString("rol"));
                jugador.setSalario(rs.getInt("salario"));
                jugador.setNacionalidad(rs.getString("nacionalidad"));
                jugador.setFechaNacimiento(rs.getDate("fecha_nacimiento").toLocalDate());
                jugador.setNickname(rs.getString("nickname"));
                jugador.setEquipo(cbd.buscarEquipo(rs.getInt("cod_equipo")));
            }

            rs.close();
            cs.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Error al consultar el jugador", e);
        } finally {
            if (con != null) {
                try {
                    cbd.cerrarConexion(con);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return jugador;
    }

    /**
     * Es el mismo metodo que el de arriba pero te permite buscar por código
     * @author Erik
     * @param cod
     * @return jugador
     * @throws Exception
     */
    public Jugador buscarJugador(int cod) throws Exception {
        con = cbd.abrirConexion();
        System.out.println("\nBuscando jugador con cod "+cod);
        jugador = new Jugador();
        try {
            String llamada = "{ ? = call crud_Jugadores.consultar_Jugadores_cod(?) }";
            CallableStatement cs = con.prepareCall(llamada);

            cs.registerOutParameter(1, oracle.jdbc.OracleTypes.CURSOR);
            cs.setInt(2, cod);

            cs.execute();

            ResultSet rs = (ResultSet) cs.getObject(1);

            if (rs.next()) {
                jugador.setCod(rs.getInt("cod"));
                jugador.setNombre(rs.getString("nombre"));
                jugador.setApellido((rs.getString("apellido")));
                jugador.setRol(rs.getString("rol"));
                jugador.setSalario(rs.getInt("salario"));
                jugador.setNacionalidad(rs.getString("nacionalidad"));
                jugador.setFechaNacimiento(rs.getDate("fecha_nacimiento").toLocalDate());
                jugador.setNickname(rs.getString("nickname"));
                jugador.setEquipo(cbd.buscarEquipo(rs.getInt("cod_equipo")));
            }

            rs.close();
            cs.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Error al consultar el jugador", e);
        } finally {
            if (con != null) {
                try {
                    cbd.cerrarConexion(con);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return jugador;
    }

    /**
     * Este metodo se encarga de borrar el objeto instanciado previamente en este controlador.
     * IMPORTANTE:
     *          PREVIAMENTE HAY QUE BUSCAR EL OBJETO PARA QUE BORRE EL OBJETO QUE SE DESEA
     * @author Erik
     * @return String
     * @throws Exception
     */
    public void borrarJugador(int cod) throws Exception {
        con = cbd.abrirConexion();
        System.out.println("\nBorrando Jugador con nombre "+jugador.getNickname());
        try {
            String llamada = "{ call crud_Jugadores.borrar_Jugadores(?) }";
            CallableStatement cs = con.prepareCall(llamada);


            cs.setInt(1, cod);

            cs.execute();
            cs.close();

        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Error al borrar jugador", e);
        } finally {
            if (con != null) {
                try {
                    cbd.cerrarConexion(con);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        System.out.println("Jugador borrado");
    }

    /**
     * Este metodo se encarga de modificar el objeto que le pasamos.
     * Este objeto se instancia en la clase por si se necesita juegar tras cerrar la transacción.
     * IMPORTANTE:
     *            PREVIAMENTE HAY QUE BUSCAR EL OBJETO PARA OBTENER EL COD DEL OBJETO DEBIDO A QUE SE GENERA A TRAVÉS
     *            DE UNA SECUENCIA.
     * @author Erik
     * @param jugador
     * @return
     * @throws Exception
     */

    public void modificarJugador(Jugador jugador) throws Exception
    {
        con = cbd.abrirConexion();
        System.out.println("\nModificando Jugador con nombre " + jugador.getNickname());
        try {
            String llamada = "{ call crud_Jugadores.modificar_Jugadores(?,?,?,?,?,?,?,?,?) }";
            CallableStatement cs = con.prepareCall(llamada);

            this.jugador = jugador;
            cs.setInt(1,jugador.getCod());
            cs.setString(2, jugador.getNombre());
            cs.setString(3,jugador.getApellido());
            cs.setString(4,jugador.getRol());
            cs.setInt(5,jugador.getSalario());
            cs.setString(6,jugador.getNacionalidad());
            cs.setDate(7,Date.valueOf(jugador.getFechaNacimiento()));
            cs.setString(8,jugador.getNickname());
            cs.setInt(9,jugador.getEquipo().getCod());




            cs.execute();
            cs.close();

        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Error al modificar el jugador", e);
        } finally {
            if (con != null) {
                try {
                    cbd.cerrarConexion(con);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        System.out.println("Jugador modificado!");
    }

    /**
     * Este metodo se encarga de insertar el objeto que le pasemos.
     * Este objeto se instancia en la clase por si se necesita juegar tras cerrar la transacción.
     * @author Erik
     * @param jugador
     * @return
     * @throws Exception
     */
    public void insertarJugador(Jugador jugador) throws Exception {
        con = cbd.abrirConexion();
        System.out.println("\nInsertando jugador con nickname" + jugador.getNickname());
        try {
            String llamada = "{ call crud_Jugadores.insertar_Jugadores(?,?,?,?,?,?,?,?)}";
            CallableStatement cs = con.prepareCall(llamada);

            this.jugador = jugador;
            cs.setString(1, jugador.getNombre());
            cs.setString(2,jugador.getApellido());
            cs.setString(3,jugador.getRol());
            cs.setInt(4,jugador.getSalario());
            cs.setString(5,jugador.getNacionalidad());
            cs.setDate(6,Date.valueOf(jugador.getFechaNacimiento()));
            cs.setString(7,jugador.getNickname());
            cs.setInt(8,jugador.getEquipo().getCod());

            cs.execute();
            cs.close();

        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Error al insertar el jugador", e);
        } finally {
            if (con != null) {
                try {
                    cbd.cerrarConexion(con);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        System.out.println("Jugador insertado");
    }

    public List buscarJugadores() throws Exception {
        con = cbd.abrirConexion();
        System.out.println("\nBuscando todos los jugadores");
        try {
            listaJugadores.clear();
            String llamada = "{ ? = call crud_Jugadores.consultar_todos_jugadores() }";
            CallableStatement cs = con.prepareCall(llamada);

            cs.registerOutParameter(1, oracle.jdbc.OracleTypes.CURSOR);

            cs.execute();

            ResultSet rs = (ResultSet) cs.getObject(1);

            while (rs.next()) {
                jugador = new Jugador();
                jugador.setCod(rs.getInt("cod"));
                jugador.setNombre(rs.getString("nombre"));
                jugador.setApellido((rs.getString("apellido")));
                jugador.setRol(rs.getString("rol"));
                jugador.setSalario(rs.getInt("salario"));
                jugador.setNacionalidad(rs.getString("nacionalidad"));
                jugador.setFechaNacimiento(rs.getDate("fecha_nacimiento").toLocalDate());
                jugador.setNickname(rs.getString("nickname"));
                jugador.setEquipo(cbd.buscarEquipo(rs.getInt("cod_equipo")));
                listaJugadores.add(jugador);
            }

            rs.close();
            cs.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Error al consultar el jugador", e);
        } finally {
            if (con != null) {
                try {
                    cbd.cerrarConexion(con);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return listaJugadores;
    }





}
