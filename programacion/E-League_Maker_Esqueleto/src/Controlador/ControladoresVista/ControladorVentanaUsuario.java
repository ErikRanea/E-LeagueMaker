package Controlador.ControladoresVista;

import Modelo.Clasificacion;
import Modelo.Competicion;
import Modelo.Enfrentamiento;
import Vista.VentanaUsuario;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class ControladorVentanaUsuario {
    private VentanaUsuario vUsuario;
    private JTextArea textArea;
    private ControladorVista cv;

    private ArrayList<Competicion> listaCompeticiones;
    private Clasificacion clasificacion;
    private ArrayList<Clasificacion> listaClasificacion;
    private Competicion competicion;

    public ControladorVentanaUsuario(ControladorVista cv) {
        this.cv = cv;

        listaCompeticiones = new ArrayList<>();
        listaClasificacion = new ArrayList<>();

    }

    public void crearMostrar() {
        vUsuario = new VentanaUsuario();

        rellenarCBCompeticiones();
        vUsuario.addBAceptar(new BAceptarALU());
        vUsuario.addBSalirAl(new BSalirALU());
        vUsuario.addBExportar(new BExportarALU());
        vUsuario.setVisible(true);
    }

    /**
     * Esto rellena la comboBox
     */
    public void rellenarCBCompeticiones() {
        try {
            listaCompeticiones = cv.pedirCompeticionesCerradas();

            for (Competicion c : listaCompeticiones) {
                vUsuario.getCombobClasifiU().addItem(c.getNombre());
            }
        } catch (Exception ex) {
            System.out.printf("\nHa salido el siguiente error:\n" + ex.getMessage());
        }
    }
    /**
     *Al momento de clickear el boton aceptar carga la base de datos y en el textArea nos muestra las competiciones(clasificaciones)
     * de la última jornada
     * @author Rodrigo
     */
    public class BAceptarALU implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            competicion = listaCompeticiones.get(vUsuario.getCombobClasifiU().getSelectedIndex());
            vUsuario.getTaUsuario().setText("");

            try {
                mostrarClasificaciones();

                mostrarResultadosEnfrentamientos();
            }catch (Exception ex){
                ex.printStackTrace();

                vUsuario.getTaUsuario().append("Error al obtener clasificaciones o resultados de enfrentamientos.");
            }
        }
    }

    /**
     * Muestra todas las competiciones(clasificaciones) de la ultima jornada, con sus guiones y espacios para que
     * sea legible por el usuario
     * @author Rodrigo
     */

    private void mostrarClasificaciones() throws Exception
    {
        String encabezado = String.format("%-10s | %-20s | %-10s", "Posicion", "Equipo", "Puntos");
        vUsuario.getTaUsuario().append("    " + encabezado + "\n");
        StringBuilder guiones = new StringBuilder();
        for (int i = 0; i < encabezado.length() + 3; i++) {
            guiones.append("-");
        }
        vUsuario.getTaUsuario().append("    " + guiones.toString() + "\n");

        ArrayList<Clasificacion> clasificaciones = cv.obtenerClasificacion(competicion.getCod());
        for (Clasificacion clasificacion : clasificaciones) {
            String linea = String.format("%-10d | %-20s | %-10d",
                    clasificacion.getPosicion(),
                    clasificacion.getEquipo().getNombre(),
                    clasificacion.getPuntos());
            vUsuario.getTaUsuario().append("    " + linea + "\n");
        }
        vUsuario.getTaUsuario().append("\n");
    }

    /**
     * Muestra los resultados de los enfrentamientos de la ultima jornada en el textArea, con los guiones para que sea
     * mas legible para el usuario
     * @author Rodrigo
     */

    private void mostrarResultadosEnfrentamientos() throws Exception
    {
        String encabezado2 = String.format("%-10s | %-20s | %-20s | %-20s | %-20s | %-10s","Cod", "EquipoLocal", "EquipoVisitante", "Hora", "Jornada", "Ganador");

        vUsuario.getTaUsuario().append("    " + encabezado2 + "\n");
        StringBuilder guiones2 = new StringBuilder();
        for (int i = 0; i < encabezado2.length(); i++) {
            guiones2.append("-");
        }
        vUsuario.getTaUsuario().append("    " + guiones2.toString() + "\n");

        ArrayList<Enfrentamiento> enfrentamientos = cv.consultarEnfrentamientosConResultados(competicion.getCod());
        for (Enfrentamiento enfrentamiento : enfrentamientos) {
            String ganador;
            if (enfrentamiento.isGanaLocal()){
                ganador = enfrentamiento.getEquipoLocal().getNombre();
            } else {
                ganador = enfrentamiento.getEquipoVisitante().getNombre();
            }

            String linea2 = String.format("%-10s | %-20s | %-20s | %-20s | %-20s | %-10s",
                    enfrentamiento.getCod(),
                    enfrentamiento.getEquipoLocal().getNombre(),
                    enfrentamiento.getEquipoVisitante().getNombre(),
                    enfrentamiento.getHora().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")),
                    enfrentamiento.getJornada() != null ? enfrentamiento.getJornada().getCod() : "Útlima Jornada",
                    ganador);
            vUsuario.getTaUsuario().append("    " + linea2 + "\n");
        }
    }

    /**
     * Boton para exportar el XML
     */
    public class BExportarALU implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                String xmlData = cv.exportarClasificacionXML();
                vUsuario.getTaUsuario().setText(xmlData);
                System.out.println(xmlData);
            } catch (Exception ex) {
                ex.printStackTrace();
                vUsuario.getTaUsuario().setText("Error al exportar. :(");
            }
        }
    }

    public class BSalirALU implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            vUsuario.dispose();
            cv.mostrarVentanaLogin();
        }
    }












}
