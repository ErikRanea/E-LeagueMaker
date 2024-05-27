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

    private ControladorVentanaCompeticion cvCompeti;
    private VentanaCompeticion vCompeti;




    private boolean hayJornadas;

    private ArrayList<Enfrentamiento> listaEnfrentamientos;

    public ControladorPanelInsertarResultados(ControladorVentanaCompeticion cvCompeti, VentanaCompeticion vCompeti) {
        this.cvCompeti = cvCompeti;
        this.vCompeti = vCompeti;

    }

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

    public void tarearRellenarListas() {
        try {
            cvCompeti.setListaCompetis(cvCompeti.pedirCompeticionesCerradas());
            System.out.println("Carga de competiciones hecha");

            if (!cvCompeti.getListaCompetis().isEmpty()) {
                cvCompeti.setListaJornadas(cvCompeti.consultarTablaJornadas(cvCompeti.getListaCompetis().get(0)));
                System.out.println("Carga de jornadas hecha");
            } else {
                cvCompeti.getListaJornadas().clear();
            }
        } catch (Exception ex) {
            System.out.println("\nHa salido un error en panelInserta cargarListas()\n" + ex.getMessage());
        }
    }



    public void tareaRellenarCB() {
        try {
            vCompeti.getCbCompeticiones().removeAllItems();
            vCompeti.getCbJornadas().removeAllItems();

            for (Competicion c : cvCompeti.getListaCompetis()) {
                vCompeti.getCbCompeticiones().addItem(c.getNombre());
            }
            if (!cvCompeti.getListaCompetis().isEmpty()) {
                cvCompeti.setCompeticion( cvCompeti.getListaCompetis().get(0));
            }

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
            System.out.println("\nHa habido un error en tareaRellenarCBPanelInsertar\n" + ex.getMessage());
        }
    }


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


    public class BGenerarCalendario implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                cvCompeti.generarCalendario();
                cvCompeti.mostrarVentanaCargaYRealizarTarea(() -> rellenarCBJornadas(), 6000);
            } catch (Exception ex) {
                System.out.println("Ha sucedido el siguiente error generando el calendario:\n\n" + ex.getMessage());
            }
        }
    }



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

    public class BBuscar implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            mostrarEnfrentamientos();
        }
    }

    public void mostrarEnfrentamientos() {
        vCompeti.getpVisualizar().removeAll();
        listaEnfrentamientos = cvCompeti.getJornada() != null ? cvCompeti.getJornada().getListaEnfrentamientos() : new ArrayList<>();
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

            System.out.println("Añadido panel " + enfrentamiento.getEquipoLocal().getNombre() + " vs " + enfrentamiento.getEquipoVisitante().getNombre());
        }

        vCompeti.getpVisualizar().setVisible(true);
        vCompeti.repaint();
        vCompeti.revalidate();

        System.out.println("Terminado pVisualizar con enfrentamientos.");
    }
}
