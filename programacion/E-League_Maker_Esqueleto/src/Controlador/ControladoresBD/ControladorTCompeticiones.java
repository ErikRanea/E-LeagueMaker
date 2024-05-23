/** Esta clase se encarga de hacer llamadas a las metodos que hay creados en el paquete PL/SQL
 * crud_Competiciones
 * @author Erik
 * @since 17/05/2024
 */
package Controlador.ControladoresBD;

import Modelo.Competicion;


import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class ControladorTCompeticiones {

    /**
     * Este atributo se encarga de conectar con el controlador superior
     * @param cdb
     */

    private ControladorBD cbd;

    private Connection con;
    /**
     * Este atributo permite instanciar el objeto de la tabla
     * @param competicion
     */
    private Competicion competicion;

    private ArrayList<Competicion> listaCompeticiones;

    public ControladorTCompeticiones(ControladorBD cbd)
    {
        this.cbd = cbd;listaCompeticiones = new ArrayList<>();
    }

    /**
     * Este metodo busca el objeto y lo instancia en la clase
     * @param cod
     * @return Competicion
     * @throws Exception
     */

    public Competicion buscarCompeticion(int cod) throws Exception {
        con = cbd.abrirConexion();
        System.out.println("\nBuscando competicion con cod "+cod);
        competicion = new Competicion();
        try {
            String llamada = "{ ? = call crud_Competiciones.consultar_Competiciones(?) }";
            CallableStatement cs = con.prepareCall(llamada);

            cs.registerOutParameter(1, oracle.jdbc.OracleTypes.CURSOR);
            cs.setInt(2, cod);

            cs.execute();

            ResultSet rs = (ResultSet) cs.getObject(1);

            if (rs.next()) {
               competicion.setCod(rs.getInt("cod"));
               competicion.setNombre(rs.getString("nombre"));
               competicion.setFechaInicio(rs.getDate("fecha_inicio").toLocalDate());
               competicion.setFechaFin(rs.getDate("fecha_fin").toLocalDate());
               competicion.setEstadoAbierto(rs.getBoolean("estado_abierto"));
               competicion.setJuego(cbd.buscarJuego(rs.getInt("cod_juego")));
            }

            rs.close();
            cs.close();
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

        return competicion;
    }

    /**
     * Este metodo se encarga de borrar el objeto instanciado previamente en este controlador.
     * IMPORTANTE:
     *          PREVIAMENTE HAY QUE BUSCAR EL OBJETO PARA QUE BORRE EL OBJETO QUE SE DESEA
     * @author Erik
     * @return String
     * @throws Exception
     */
    public String borrarCompeticion() throws Exception {
        con = cbd.abrirConexion();
        System.out.println("\nBorrando Competicion con nombre "+ competicion.getNombre());
        try {
            String llamada = "{ call crud_Competiciones.borrar_Competiciones(?) }";
            CallableStatement cs = con.prepareCall(llamada);


            cs.setInt(1, competicion.getCod());

            cs.execute();
            cs.close();

        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Error al borrar Competicion", e);
        } finally {
            if (con != null) {
                try {
                    cbd.cerrarConexion(con);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return "Competicion borrado!";
    }

    /**
     * Este metodo se encarga de modificar el objeto que le pasamos.
     * Este objeto se instancia en la clase por si se necesita juegar tras cerrar la transacción.
     * IMPORTANTE:
     *            PREVIAMENTE HAY QUE BUSCAR EL OBJETO PARA OBTENER EL COD DEL OBJETO DEBIDO A QUE SE GENERA A TRAVÉS
     *            DE UNA SECUENCIA.
     * @author Erik
     * @param competicion
     * @return string
     * @throws Exception
     */

    public String modificarCompeticion(Competicion competicion) throws Exception
    {
        con = cbd.abrirConexion();
        System.out.println("\nModificando compiticion con nombre " + competicion.getNombre());
        try {
            String llamada = "{ call crud_Competiciones.modificar_Competiciones(?,?,?,?,?) }";
            CallableStatement cs = con.prepareCall(llamada);

            this.competicion = competicion;
            cs.setInt(1,competicion.getCod());
            cs.setString(2,competicion.getNombre());
            if(competicion.getFechaInicio() != null)
                cs.setDate(3, Date.valueOf(competicion.getFechaInicio()));
            if(competicion.getFechaInicio() == null)
                cs.setDate(3, Date.valueOf(LocalDate.now()));
            if(competicion.getFechaFin() != null)
                cs.setDate(4, Date.valueOf(competicion.getFechaFin()));
            if (competicion.getFechaFin() == null)
                cs.setDate(4, Date.valueOf(LocalDate.now().plusMonths(1)));
            cs.setBoolean(5,competicion.isEstadoAbierto());

            cs.execute();
            cs.close();

        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Error al modificar competicion", e);
        } finally {
            if (con != null) {
                try {
                    cbd.cerrarConexion(con);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return "Competicion modificado!";
    }

    /**
     * Este metodo se encarga de insertar el objeto que le pasemos.
     * Este objeto se instancia en la clase por si se necesita juegar tras cerrar la transacción.
     * @author Erik
     * @param competicion
     * @return string
     * @throws Exception
     */
    public String insertarCompeticion(Competicion competicion) throws Exception {
        con = cbd.abrirConexion();
        System.out.println("\nInsertando competicion con nickname" + competicion.getNombre() );
        try {
            String llamada = "{ call crud_Competiciones.insertar_Competiciones(?,?,?,?,?)}";
            CallableStatement cs = con.prepareCall(llamada);

            this.competicion = competicion;

            cs.setString(1,competicion.getNombre());
            if(competicion.getFechaInicio() != null)
                cs.setDate(2, Date.valueOf(competicion.getFechaInicio()));
            if(competicion.getFechaInicio() == null)
                cs.setDate(2, Date.valueOf(LocalDate.now()));
            if(competicion.getFechaFin() != null)
                cs.setDate(3, Date.valueOf(competicion.getFechaFin()));
            if (competicion.getFechaFin() == null)
                cs.setDate(3, Date.valueOf(LocalDate.now().plusMonths(1)));
            cs.setInt(4,competicion.isEstadoAbiertoInt());
            cs.setInt(5,competicion.getJuego().getCod());


            cs.execute();
            cs.close();

        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Error al inserta Competicion", e);
        } finally {
            if (con != null) {
                try {
                    cbd.cerrarConexion(con);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return "Competicion insertado!";
    }


    /**
     * Metodo que devuelve todos los objetos de la tabla
     * @author Erik
     */
    public ArrayList<Competicion> pedirListaCompeticiones() throws Exception {



        try {
            con = cbd.abrirConexion();
            String llamada = "{ ? = call crud_Competiciones.todas_Competiciones }";
            CallableStatement cs = con.prepareCall(llamada);

            cs.registerOutParameter(1, oracle.jdbc.OracleTypes.CURSOR);

            cs.execute();

            ResultSet rs = (ResultSet) cs.getObject(1);

            while (rs.next()) {
                Competicion competi = new Competicion();
                competi.setCod(rs.getInt("cod"));
                competi.setNombre(rs.getString("nombre"));
                competi.setFechaInicio(rs.getDate("fecha_inicio").toLocalDate());
                competi.setFechaFin(rs.getDate("fecha_fin").toLocalDate());
                competi.setEstadoAbierto(rs.getBoolean("estado_abierto"));
                competi.setJuego(cbd.buscarJuego(rs.getInt("cod_juego")));
                listaCompeticiones.add(competi);
            }

            rs.close();
            cs.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Error al consultar las competiciones", e);
        } finally {
            if (con != null) {
                try {
                    cbd.cerrarConexion(con);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return listaCompeticiones;
    }

    public ArrayList<Competicion> pedirCompeticionesCerradas()throws Exception
    {
        try {
            System.out.println("\nPidiendo Competiciones cerradas\n");
            con = cbd.abrirConexion();
            String llamada = "{ ? = call crud_Competiciones.Competiciones_cerradas }";
            CallableStatement cs = con.prepareCall(llamada);

            cs.registerOutParameter(1, oracle.jdbc.OracleTypes.CURSOR);




            cs.execute();

            ResultSet rs = (ResultSet) cs.getObject(1);

            while (rs.next()) {
                Competicion competi = new Competicion();
                competi.setCod(rs.getInt("cod"));
                competi.setNombre(rs.getString("nombre"));
                competi.setFechaInicio(rs.getDate("fecha_inicio").toLocalDate());
                competi.setFechaFin(rs.getDate("fecha_fin").toLocalDate());
                competi.setEstadoAbierto(rs.getBoolean("estado_abierto"));
                competi.setJuego(cbd.buscarJuego(rs.getInt("cod_juego")));
                listaCompeticiones.add(competi);
            }

            rs.close();
            cs.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Error al consultar las competiciones", e);
        } finally {
            if (con != null) {
                try {
                    cbd.cerrarConexion(con);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return listaCompeticiones;
    }


    public String generarCalendario() throws Exception
    {
        String respuesta = "";
        try {
            con = cbd.abrirConexion();
            String llamada = "{ call generar_calendario }";
            CallableStatement cs = con.prepareCall(llamada);

            cs.execute();

            System.out.println("\nCalendario generado\n");


            cs.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Error generar el calendario", e);
        } finally {
            if (con != null) {
                try {
                    cbd.cerrarConexion(con);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return respuesta;
    }

}
