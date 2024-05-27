package Controlador.ControladoresVista;

import Modelo.Clasificacion;
import Modelo.Competicion;
import Modelo.Enfrentamiento;
import Modelo.Jornada;
import Vista.VentanaCompeticion;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ControladorVentanaCompeticion {

    private ControladorVista cv;
    private ControladorPanelVerClasificaciones cpClasifi;
    private ControladorPanelVerTodosResultados cpVerResult;
    private ControladorPanelInsertarResultados cpInsert;

    private VentanaCompeticion vCompeti;
    private ArrayList<Competicion> listaCompetis;
    private ArrayList<Jornada> listaJornadas;
    private ArrayList<Enfrentamiento> listaEnfrentamientos;
    private Competicion competicion;
    private Jornada jornada;
    private Enfrentamiento enfrentamiento;
    private boolean hayJornadas;

    boolean bSeleccionado = false;

    public ControladorVentanaCompeticion(ControladorVista cv) {
        this.cv = cv;
        listaCompetis = new ArrayList<>();
        listaJornadas = new ArrayList<>();
        listaEnfrentamientos = new ArrayList<>();
    }

    public void crearMostrar() {
        try {
            vCompeti = new VentanaCompeticion();

            vCompeti.addBLogOutAL(new BLogOut());
            vCompeti.addBInsertarResultAL(new BIntroResult());
            vCompeti.addBVerClasifiAL(new BVerClasificaciones());
            vCompeti.addVerResultadosAL(new BVerResultadosJornadas());

            vCompeti.addCBCompeticionAL(new CBCompeticion());
            vCompeti.addCBJornadaAL(new CBJornadas());

            vCompeti.setVisible(true);
            crearControladorPanelClasificacion();
            crearControladorPanelVerResultados();
            crearControladorPanelInsertarResultados();
        } catch (Exception ex) {
            System.out.println("\n" + ex.getMessage());
        }
    }

    public class BIntroResult implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            cpInsert.iniciarComponentes();
        }
    }

    public class BLogOut implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            vCompeti.dispose();
            cv.mostrarVentanaLogin();
        }
    }

    public class CBCompeticion implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                competicion = listaCompetis.get(vCompeti.getCbCompeticiones().getSelectedIndex());
                cargarJornadasEnfrentamientos();
            } catch (Exception ex) {
                System.out.println("Ha salido el siguiente error en el ActionListener de la ComboBox de competicion\n" + ex.getMessage());
            }
        }
    }

    public class CBJornadas implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                jornada = listaJornadas.get(vCompeti.getCbJornadas().getSelectedIndex());
            } catch (Exception ex) {
                System.out.println("Ha salido el siguiente error en el ActionListener de la ComboBox de jornadas\n" + ex.getMessage());
            }
        }
    }

    public void mostrarVentanaCargaYRealizarTarea(Runnable tarea, int duracionCarga) {
        cv.mostrarVentanaCarga(duracionCarga, vCompeti);

        SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
            @Override
            protected Void doInBackground() {
                tarea.run();
                return null;
            }

            @Override
            protected void done() {
                try {
                    cv.ocultarVentanaCarga();
                    System.out.println("Tarea completada");
                } catch (Exception ex) {
                    System.out.println("\nHa sucedido el siguiente error:\n\n" + ex.getMessage());
                }
            }
        };

        worker.execute();
    }

    public void cargarCompeticiones() {
        try {
            vaciarCB();
            listaCompetis = cv.pedirCompeticionesCerradas();
            System.out.println("Carga de competiciones hecha");
            rellenarCBCompeticiones();
        } catch (Exception ex) {
            System.out.println("\nHa sucedido un error en el proceso de cargarCompeticiones\n" + ex.getMessage());
        }
    }

    public void cargarJornadasEnfrentamientos() {
        try {
            if (competicion != null) {
                listaJornadas = cv.consultarTablaJornadas(competicion);
                System.out.println("Carga de jornadas hecha");
                rellenarCBJornadas();
            } else {
                System.out.println("No hay competicion seleccionada.");
                listaJornadas.clear();
                vaciarCBJornadas();
            }
        } catch (Exception ex) {
            System.out.println("\nHa sucedido un error en el proceso de cargarJornadasEnfrentamientos\n" + ex.getMessage());
        }
    }

    public void vaciarCBCompeticiones() {
        vCompeti.getCbCompeticiones().removeAllItems();
    }

    public void vaciarCBJornadas() {
        vCompeti.getCbJornadas().removeAllItems();
    }

    public void rellenarCBCompeticiones() {
        try {
            for (Competicion c : listaCompetis) {
                vCompeti.getCbCompeticiones().addItem(c.getNombre());
            }
            if (!listaCompetis.isEmpty()) {
                competicion = listaCompetis.get(0);
                cargarJornadasEnfrentamientos();
            }
        } catch (Exception ex) {
            System.out.println("\nHa salido el siguiente error:\n" + ex.getMessage());
        }
    }

    public void rellenarCBJornadas() {
        try {
            if (!listaJornadas.isEmpty()) {
                for (Jornada j : listaJornadas) {
                    vCompeti.getCbJornadas().addItem(j.getnJornada());
                }
                if (!listaJornadas.isEmpty()) {
                    jornada = listaJornadas.get(0);
                    hayJornadas = true;
                    vCompeti.getbCalendario().setVisible(false);
                } else {
                    hayJornadas = false;
                    vCompeti.getbCalendario().setVisible(true);
                }
            }
        } catch (Exception ex) {
            System.out.println("\nHa salido el siguiente error en rellenarCBJornadas:\n" + ex.getMessage());
        }
    }

    public void vaciarCB() {
        try {
            listaCompetis.clear();
            listaJornadas.clear();
            listaEnfrentamientos.clear();
            vaciarCBCompeticiones();
            vaciarCBJornadas();
        } catch (Exception ex) {
            System.out.println("\nHa salido un error en vaciarCB\n " + ex.getMessage());
        }
    }

    public void crearControladorPanelInsertarResultados() {
        cpInsert = new ControladorPanelInsertarResultados(this, vCompeti);
    }

    public void crearControladorPanelClasificacion() {
        cpClasifi = new ControladorPanelVerClasificaciones(this, vCompeti);
    }

    public class BVerClasificaciones implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            cpClasifi.iniciarComponentes();
        }
    }

    public ArrayList<Clasificacion> obtenerClasificacion(Competicion competicion) throws Exception {
        return cv.obtenerClasificacion(competicion);
    }

    public void crearControladorPanelVerResultados() {
        cpVerResult = new ControladorPanelVerTodosResultados(this, vCompeti);
    }

    public class BVerResultadosJornadas implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            cpVerResult.iniciarComponentes();
        }
    }

    public ArrayList<Competicion> getListaCompetis() {
        return listaCompetis;
    }

    public void setListaCompetis(ArrayList<Competicion> listaCompetis) {
        this.listaCompetis = listaCompetis;
    }

    public ArrayList<Jornada> getListaJornadas() {
        return listaJornadas;
    }

    public void setListaJornadas(ArrayList<Jornada> listaJornadas) {
        this.listaJornadas = listaJornadas;
    }

    public ArrayList<Enfrentamiento> getListaEnfrentamientos() {
        return listaEnfrentamientos;
    }

    public void setListaEnfrentamientos(ArrayList<Enfrentamiento> listaEnfrentamientos) {
        this.listaEnfrentamientos = listaEnfrentamientos;
    }

    public Competicion getCompeticion() {
        return competicion;
    }

    public void setCompeticion(Competicion competicion) {
        this.competicion = competicion;
    }

    public Jornada getJornada() {
        return jornada;
    }

    public void setJornada(Jornada jornada) {
        this.jornada = jornada;
    }

    public ArrayList<Jornada> consultarTablaJornadasConResultado(Competicion competicion) throws Exception {
        return cv.consultarTablaJornadasConResultado(competicion);
    }

    public Jornada buscarJornada(int cod) throws Exception {
        return cv.buscarJornada(cod);
    }

    public ArrayList<Enfrentamiento> consultarEnfrentamientosSinResultado(Jornada jornada) throws Exception {
        return cv.consultarEnfrentamientosSinResultado(jornada);
    }

    public ArrayList<Enfrentamiento> consultarEnfrentamientosConResultado(Jornada jornada) throws Exception {
        return cv.consultarEnfrentamientosConResultado(jornada);
    }

    public boolean actualizarResultados(int cod, int resultado) throws Exception {
        return cv.actualizarResultados(cod, resultado);
    }

    public ArrayList<Competicion> pedirCompeticionesCerradas() throws Exception {
        return cv.pedirCompeticionesCerradas();
    }

    public ArrayList<Jornada> consultarTablaJornadas(Competicion competicion) throws Exception {
        return cv.consultarTablaJornadas(competicion);
    }

    public String generarCalendario() throws Exception {
        return cv.generarCalendario();
    }
}
