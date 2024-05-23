package Controlador.ControladoresVista;
import Controlador.ControladoresVista.ControladorVista;
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
    private VentanaCompeticion vCompeti;
    private ArrayList<Competicion> listaCompetis;
    private ArrayList<Jornada> listaJornadas;
    private ArrayList<Enfrentamiento> listaEnfrentamientos;
    private Competicion competicion;
    private Jornada jornada;
    private Enfrentamiento enfrentamiento;
    private boolean hayJornadas;

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
            vCompeti.setVisible(true);
        } catch (Exception ex) {
            System.out.println("\n" + ex.getMessage());
        }
    }

    /**
     * Esta interfaz se encarga de cargar y mostrar los datos de competiciones,jornadas y enfrentamientos para poder
     * insertar el resultado en la ventana
     * @author Erik
     */
    public class BIntroResult implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (!vCompeti.getpBotones().isVisible()) {
                cargarDatosDeManeraAsincrona();
                vCompeti.verPanelBotonesLateralIzq();
            } else {
                vCompeti.quitarPanelBotonesLateralIzq();
            }
        }
    }

    /**
     * Esta función se encarga de crear Runnables o tareas que hacen que tanto la carga de datos como la carga
     * diferentes modificaciones en la VentanaCompetición sea de manera asincrona.
     * @param tarea es necesario pasarle un runnable con la tarea que queremos que haga en segundo plano
     * @param duracionCarga indica el tiempo que debe estar la VentanaCarga en pantalla mientras se cargan todos los
     *                      datos.
     * @author Erik
     */
    private void mostrarVentanaCargaYRealizarTarea(Runnable tarea, int duracionCarga) {
        // Mostrar la ventana de carga
        cv.mostrarVentanaCarga(duracionCarga, vCompeti);

        // Ejecutar la tarea en segundo plano
        SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
            @Override
            protected Void doInBackground() {
                tarea.run();
                return null;
            }

            @Override
            protected void done() {
                try {
                    cv.ocultarVentanaCarga(); // Asegúrate de tener este método para ocultar la ventana de carga
                    System.out.println("Tarea completada");
                } catch (Exception ex) {
                    System.out.println("\nHa sucedido el siguiente error:\n\n" + ex.getMessage());
                }
            }
        };

        // Ejecuta el worker
        worker.execute();
    }

    /**
     * Esta función crea un runable con las tres tareas que queremos que cargue.
     * 1.- Cargar las competiciones.
     * 2.- Cargar las jornadas y sus correspondientes enfrentamientos
     * 3.- Cargar las comboBox
     */
    public void cargarDatosDeManeraAsincrona() {
        mostrarVentanaCargaYRealizarTarea(new Runnable() {
            @Override
            public void run() {
                cargarCompeticiones();
                cargarJornadasEnfrentamientos();
                cargarDatosComboBox();
            }
        }, 20000);
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
                mostrarEnfrentamientos();
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
                mostrarVentanaCargaYRealizarTarea(new Runnable() {
                    @Override
                    public void run() {
                        rellenarCBJornadas();
                    }
                }, 6000);
            } catch (Exception ex) {
                System.out.println("Ha sucedido el siguiente error generando el calendario:\n\n" + ex.getMessage());
            }
        }
    }




    public void cargarCompeticiones() {
        try {
            listaCompetis = cv.pedirCompeticionesCerradas();
            System.out.println("Carga de competiciones hecha");
        } catch (Exception ex) {
            System.out.println("\nHa sucedido un error en el proceso de cargarCompeticiones\n" + ex.getMessage());
        }
    }

    public void cargarJornadasEnfrentamientos() {
        try {
            listaJornadas = cv.consultarTablaJornadas(listaCompetis.get(0).getCod());
            System.out.println("Carga de jornadas hecha");
        } catch (Exception ex) {
            System.out.println("\nHa sucedido un error en el proceso de cargarJornadasEnfrentamientos\n" + ex.getMessage());
        }
    }

    public void cargarDatosComboBox() {
        rellenarCBCompeticiones();
        rellenarCBJornadas();
    }

    public void rellenarCBCompeticiones() {
        try {
            for (Competicion c : listaCompetis) {
                vCompeti.getCbCompeticiones().addItem(c.getNombre());
            }
            competicion = listaCompetis.get(0);
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
                jornada = listaJornadas.get(0);
                hayJornadas = true;
                vCompeti.getbCalendario().setVisible(false);
            } else {
                hayJornadas = false;
                vCompeti.getbCalendario().setVisible(true);
            }
        } catch (Exception ex) {
            System.out.println("\nHa salido el siguiente error en rellenarCBJornadas:\n" + ex.getMessage());
        }
    }

    public void mostrarEnfrentamientos() {
        vCompeti.getpVisualizar().removeAll();
        listaEnfrentamientos = jornada.getListaEnfrentamientos();
        vCompeti.getpVisualizar().setLayout(new GridLayout(listaEnfrentamientos.size(), 1));
        System.out.println("\n*****************************************\nCantidadDeEnfrentamientos:\n" + listaEnfrentamientos.size());

        for (Enfrentamiento enfrentamiento : listaEnfrentamientos) {
            JPanel enfrentamientoPanel = new JPanel(new FlowLayout());
            JLabel label = new JLabel(enfrentamiento.getEquipoLocal().getNombre() + " vs " + enfrentamiento.getEquipoVisitante().getNombre());
            JButton localButton = new JButton("Gana Local");
            localButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try
                    {
                        cv.actualizarResultados(enfrentamiento.getCod(),1);
                    }
                    catch (Exception ex){}

                }
            });
            JButton visitanteButton = new JButton("Gana Visitante");
            visitanteButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try
                    {
                        cv.actualizarResultados(enfrentamiento.getCod(),0);
                    }
                    catch (Exception ex){}
                }
            });

            enfrentamientoPanel.add(label);
            enfrentamientoPanel.add(localButton);
            enfrentamientoPanel.add(visitanteButton);
            vCompeti.getpVisualizar().add(enfrentamientoPanel);
        }

        vCompeti.repaint();
        vCompeti.revalidate();
    }
}
