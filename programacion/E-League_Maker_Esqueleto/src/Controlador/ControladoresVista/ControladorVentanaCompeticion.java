package Controlador.ControladoresVista;

import Controlador.ControladoresVista.ControladorVista;
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
            vCompeti.addBBuscarAL(new BBuscar());
            vCompeti.addBLogOutAL(new BLogOut());
            vCompeti.addBInsertarResultAL(new BIntroResult());
            vCompeti.addBGenerarCalendarioAL(new BGenerarCalendario());
            vCompeti.addCBCompeticionAL(new CBCompeticion());
            vCompeti.addCBJornadaAL(new CBJornadas());

            vCompeti.addBVerClasifiAL(new BVerClasificaciones());

            vCompeti.setVisible(true);
            crearControladorPanelClasificacion();
        } catch (Exception ex) {
            System.out.println("\n" + ex.getMessage());
        }
    }

    public class BIntroResult implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (!vCompeti.getpBotones().isVisible()) {
                cargarDatosDeManeraAsincrona();
                vCompeti.verPanelBotonesLateralIzq();
            } else {
                vaciarCB();
                vCompeti.quitarPanelBotonesLateralIzq();
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

    public void cargarDatosDeManeraAsincrona() {
        mostrarVentanaCargaYRealizarTarea(() -> {
            cargarCompeticiones();
            cargarJornadasEnfrentamientos();
            cargarDatosComboBox();
        }, 30000);
    }

    public void cargarDatosPVisualizarAsinc() {
        mostrarVentanaCargaYRealizarTarea(new Runnable() {
            @Override
            public void run() {
                cargarCompeticiones();
                cargarJornadasEnfrentamientos();
                cargarDatosComboBox();
                mostrarEnfrentamientos();
            }
        },30000);

    }

    public class BLogOut implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            vCompeti.dispose();
            cv.mostrarVentanaLogin();
        }
    }

    public class BBuscar implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                cargarDatosPVisualizarAsinc();
            } catch (Exception ex) {
                System.out.println("Ha sucedido el siguiente error en BBuscar\n\n" + ex.getMessage());
            }
        }
    }

    public class CBCompeticion implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                competicion = listaCompetis.get(vCompeti.getCbCompeticiones().getSelectedIndex());
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

    public class BGenerarCalendario implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                cv.generarCalendario();
                mostrarVentanaCargaYRealizarTarea(ControladorVentanaCompeticion.this::rellenarCBJornadas, 6000);
            } catch (Exception ex) {
                System.out.println("Ha sucedido el siguiente error generando el calendario:\n\n" + ex.getMessage());
            }
        }
    }

    public void cargarCompeticiones() {
        try {
            vaciarCB();
            listaCompetis = cv.pedirCompeticionesCerradas();
            System.out.println("Carga de competiciones hecha");
        } catch (Exception ex) {
            System.out.println("\nHa sucedido un error en el proceso de cargarCompeticiones\n" + ex.getMessage());
        }
    }

    public void cargarJornadasEnfrentamientos() {
        try {
            if (!listaCompetis.isEmpty()) {
                listaJornadas = cv.consultarTablaJornadas(listaCompetis.get(0));
                System.out.println("Carga de jornadas hecha");
            } else {
                listaJornadas.clear();
            }
        } catch (Exception ex) {
            System.out.println("\nHa sucedido un error en el proceso de cargarJornadasEnfrentamientos en el ControladorVentanaCompeticion\n" + ex.getMessage());
        }
    }

    public void cargarDatosComboBox() {
        vaciarCBCompeticiones();
        vaciarCBJornadas();
        rellenarCBCompeticiones();
        rellenarCBJornadas();
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

    public void mostrarEnfrentamientos() {
        bSeleccionado = false;

        vCompeti.getpVisualizar().removeAll();
        listaEnfrentamientos = jornada != null ? jornada.getListaEnfrentamientos() : new ArrayList<>();
        vCompeti.getpVisualizar().setLayout(new GridLayout(listaEnfrentamientos.size(), 1));
        System.out.println("\n*****************************************\nCantidadDeEnfrentamientos:\n" + listaEnfrentamientos.size());

        for (Enfrentamiento enfrentamiento : listaEnfrentamientos) {
            JPanel enfrentamientoPanel = new JPanel(new FlowLayout());
            JLabel label = new JLabel(enfrentamiento.getEquipoLocal().getNombre() + " vs " + enfrentamiento.getEquipoVisitante().getNombre());
            JButton localButton = new JButton("Gana Local");
            localButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        cv.actualizarResultados(enfrentamiento.getCod(), 1);
                        enfrentamientoPanel.setVisible(false);
                        vCompeti.getpVisualizar().remove(enfrentamientoPanel);
                        vCompeti.repaint();
                        vCompeti.revalidate();
                    } catch (Exception ex) {
                        System.out.println("\nError en el AL de localButton \n" + ex.getMessage());
                    }
                }
            });

            JButton visitanteButton = new JButton("Gana Visitante");
            visitanteButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        cv.actualizarResultados(enfrentamiento.getCod(), 0);
                        enfrentamientoPanel.setVisible(false);
                        vCompeti.getpVisualizar().remove(enfrentamientoPanel);
                        vCompeti.repaint();
                        vCompeti.revalidate();
                    } catch (Exception ex) {
                        System.out.println("\nError en el AL de visitanteButton \n" + ex.getMessage());
                    }
                }
            });

            enfrentamientoPanel.add(label);
            enfrentamientoPanel.add(localButton);
            enfrentamientoPanel.add(visitanteButton);
            vCompeti.getpVisualizar().add(enfrentamientoPanel);


            System.out.println("Añadido panel " + enfrentamiento.getEquipoLocal().getNombre() + " vs " + enfrentamiento.getEquipoVisitante().getNombre());
        }

        vCompeti.getpVisualizar().setVisible(true);
        vCompeti.repaint();
        vCompeti.revalidate();


        System.out.println("Terminado pVisualizar con enfrentamientos.");
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





    //BOTON VER CLASIFICACION
    public class BVerCompeti implements  ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            crearControladorPanelClasificacion();
        }
    }











    //Clasificaciones

    public void crearControladorPanelClasificacion()
    {
        cpClasifi = new ControladorPanelVerClasificaciones(this,vCompeti);


    }
    public class BVerClasificaciones implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) {
            cpClasifi.iniciarComponentes();
        }
    }
    public ArrayList<Clasificacion> obtenerClasificacion(Competicion competicion) throws Exception
    {
        return  cv.obtenerClasificacion(competicion);
    }



    //Resultados

    public void crearControladorPanelVerResultados()
    {
        cpVerResult = new ControladorPanelVerTodosResultados(this,vCompeti);
    }

    public class BVerResultadosJornadas implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) {
            cpVerResult.iniciarComponentes();
        }
    }




    //Getter y setter


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

    //Jornadas
    public ArrayList<Jornada> consultarTablaJornadas(Competicion competicion)throws Exception
    {
        return cv.consultarTablaJornadas(competicion);
    }
    public Jornada buscarJornada(int cod) throws Exception{ return cv.buscarJornada(cod);}



    //Enfrentamientos
    public ArrayList<Enfrentamiento> consultarEnfrentamientosSinResultado(Jornada jornada)throws Exception
    {
        return cv.consultarEnfrentamientosSinResultado(jornada);
    }

    public ArrayList<Enfrentamiento> consultarEnfrentamientosConResultado(Jornada jornada)throws Exception
    {
        return cv.consultarEnfrentamientosConResultado(jornada);
    }

    public boolean actualizarResultados(int cod,int resultado) throws Exception
    {
        return cv.actualizarResultados(cod,resultado);
    }


}
