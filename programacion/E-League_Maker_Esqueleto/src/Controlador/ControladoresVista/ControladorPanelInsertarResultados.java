/**
 * Esta clase se encarga de controlar lo necesario para insertar los resultados en la base de datos.
 * @author Erik
 * @version 2.0
 */
package Controlador.ControladoresVista;

import Modelo.Competicion;
import Modelo.Enfrentamiento;
import Modelo.Jornada;
import Vista.VentanaCompeticion;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ControladorPanelInsertarResultados {

    /**
     * Enlace con el controlador superior
     */
    private ControladorVentanaCompeticion cvCompeti;
    /**
     * Instancia de VentanaCompeticion
     */
    private VentanaCompeticion vCompeti;




    private boolean hayJornadas;

    /**
     * Lista de enfrentamientos que sacamos de la jornada seleccionada
     */
    private ArrayList<Enfrentamiento> listaEnfrentamientos;

    /**
     * El constructor es iniciado en el ControladorVentanaCompeticion.
     * @param cvCompeti enlace con el controlador superior
     * @param vCompeti correlativdad entre la ventana creada en el controlador superior
     */
    public ControladorPanelInsertarResultados(ControladorVentanaCompeticion cvCompeti, VentanaCompeticion vCompeti) {
        this.cvCompeti = cvCompeti;
        this.vCompeti = vCompeti;

    }

    /**
     * Este metodo se encarga de hacer visible el panel los objetos visibles necesarios, cargar los datos y asignar
     * los Action Listeners necesarios para realizar las inserciones.
     */
    public void iniciarComponentes() {
        if (!vCompeti.getpBotones().isVisible()) {
            cargarDatos();
            vCompeti.verPanelBotonesLateralIzq();
        } else {
            vCompeti.quitarPanelBotonesLateralIzq();
        }

        vCompeti.getbCalendario().setVisible(true);
        vCompeti.getCbJornadas().setVisible(true);
        vCompeti.getLabelJornada().setVisible(true);

        vCompeti.addBBuscarAL(new BBuscar());
        vCompeti.addBGenerarCalendarioAL(new BGenerarCalendario());
    }

    /**
     * Encargado de hacer la llamada a la base de datos y asignar la lista de competiciones.
     */
    public void tarearRellenarListas() {
        try
        {
            //Vaciamos las listas
            cvCompeti.getListaCompetis().clear();
            cvCompeti.getListaJornadas().clear();

            //Rellanamos la lista competiciones
            cvCompeti.setListaCompetis(cvCompeti.pedirCompeticionesCerradas());
            System.out.println("Carga de competiciones hecha");

            if (!cvCompeti.getListaCompetis().isEmpty())
            {
                //Rellenamos la lista de jornadas
                cvCompeti.setListaJornadas(cvCompeti.consultarTablaJornadas(cvCompeti.getListaCompetis().get(0)));
                System.out.println("Carga de jornadas hecha");
            } else {
                cvCompeti.getListaJornadas().clear();
            }
        } catch (Exception ex) {
            System.out.println("\nHa salido un error en panelInserta cargarListas()\n" + ex.getMessage());
        }
    }


    /**
     * Encargado de rellenar las combo box con los datos de las listas
     */
    public void tareaRellenarCB() {
        try {
            vCompeti.getCbCompeticiones().removeAllItems();
            vCompeti.getCbJornadas().removeAllItems();

            for (Competicion c : cvCompeti.getListaCompetis()) {
                vCompeti.getCbCompeticiones().addItem(c.getNombre());
            }
            if (!cvCompeti.getListaCompetis().isEmpty()) {
                cvCompeti.setCompeticion( cvCompeti.getListaCompetis().get(0));

                for (Jornada j : cvCompeti.getListaJornadas()) {
                    vCompeti.getCbJornadas().addItem(j.getnJornada());
                }
                if (!cvCompeti.getListaJornadas().isEmpty()) {
                    cvCompeti.setJornada(cvCompeti.getListaJornadas().get(0));
                    hayJornadas = true;
                    vCompeti.getbCalendario().setVisible(false);
                } else {
                    hayJornadas = false;
                    vCompeti.getbCalendario().setVisible(true);
                }
            }
        } catch (Exception ex) {
            System.out.println("\nHa habido un error en tareaRellenarCBPanelInsertar\n" + ex.getMessage());
        }
    }

    /**
     * Metodo encargado de mostrar la ventana de carga mientras en segundo plano crea consulta los botones
     */
    public void cargarDatos()
    {
        cvCompeti.mostrarVentanaCargaYRealizarTarea(new Runnable() {
            @Override
            public void run() {
                tarearRellenarListas();
                tareaRellenarCB();
            }
        },10000);
    }

    /**
     * Interfaz encargada de llamar al procedimiento almacenado que genera la base de datos
     */
    public class BGenerarCalendario implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                cvCompeti.generarCalendario();
                cvCompeti.mostrarVentanaCargaYRealizarTarea(new Runnable()
                    {
                        @Override
                        public void run() {
                            tarearRellenarListas();
                            rellenarCBJornadas();
                        }
                    }
                        , 6000);
                vCompeti.getbCalendario().setVisible(false);
            } catch (Exception ex) {
                System.out.println("Ha sucedido el siguiente error generando el calendario:\n\n" + ex.getMessage());
            }
        }
    }


    /**
     * Metodo utilizado por BGenerarCalendario para que al generar el calendario introduzca las jornadas recien creadas,
     * en la CB.
     */
    public void rellenarCBJornadas() {
        try {
            if (!cvCompeti.getListaJornadas().isEmpty()) {
                for (Jornada j : cvCompeti.getListaJornadas()) {
                    vCompeti.getCbJornadas().addItem(j.getnJornada());
                }
                if (!cvCompeti.getListaJornadas().isEmpty()) {
                    cvCompeti.setJornada(cvCompeti.getListaJornadas().get(0));
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

    /**
     * Interfaz encargada de llamar al metodo que carga
     * los enfrentamientos pendientes por poner las fechas en el panel visualizar
     */
    public class BBuscar implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            mostrarEnfrentamientos();
        }
    }

    /**
     * Metodo encargado de mostrar los enfrentamientos en el panel visualizar y asignarle su propio Action Listener
     */
    public void mostrarEnfrentamientos() {
        //Vaciamos el panel visualizar
        vCompeti.getpVisualizar().removeAll();

        if(cvCompeti.getJornada() != null&&cvCompeti.getJornada().getListaEnfrentamientos().size() > 0)
        {
            //Instanciamos los enfrentamientos
            listaEnfrentamientos = cvCompeti.getJornada().getListaEnfrentamientos();

            //Especificamos el layout del panel y le ponemos una única columna
            vCompeti.getpVisualizar().setLayout(new GridLayout(listaEnfrentamientos.size(), 1));

            System.out.println("\n*****************************************\nCantidadDeEnfrentamientos para cargar" +
                    "en el pVisualizar:\n" +
                    listaEnfrentamientos.size());

            //For each
            for (Enfrentamiento enfrentamiento : listaEnfrentamientos) {
                //Creamos un panel para cada enfrentamiento para que esté alineado
                JPanel enfrentamientoPanel = new JPanel(new FlowLayout());
                //Label del texto con el nombre de los equipos

                JLabel label = new JLabel(enfrentamiento.getEquipoLocal().getNombre() + " vs " +
                        enfrentamiento.getEquipoVisitante().getNombre());

                //Crear botón del equipo local
                JButton localButton = new JButton("Gana Local");
                //Le asignamos un ActionListener que al ser seleccionado asigne el resultado del enfrentamientos
                //Y borre el panel que tiene el enfrentamiento
                localButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        try {
                            cvCompeti.actualizarResultados(enfrentamiento.getCod(), 1);
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
                            cvCompeti.actualizarResultados(enfrentamiento.getCod(), 0);
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

                System.out.println("\nAñadido panel " + enfrentamiento.getEquipoLocal().getNombre() + " vs " +
                        enfrentamiento.getEquipoVisitante().getNombre());
            }

            vCompeti.getpVisualizar().setVisible(true);
            vCompeti.repaint();
            vCompeti.revalidate();

            System.out.println("\nTerminado pVisualizar con enfrentamientos.");
        }
        else
        {
            JPanel noEnfrentamientosPanel = new JPanel(new FlowLayout());
            JLabel textoNoEnfre = new JLabel("No hay enfrentamientos para esta jornada");
            textoNoEnfre.setFont(new Font(null,1,34));
            noEnfrentamientosPanel.add(textoNoEnfre);

            //Especificamos el layout del panel y le ponemos una única columna
            vCompeti.getpVisualizar().setLayout(new GridLayout());
            vCompeti.getpVisualizar().add(noEnfrentamientosPanel);
            vCompeti.getpVisualizar().setVisible(true);
            vCompeti.repaint();
            vCompeti.revalidate();
        }


    }
}
