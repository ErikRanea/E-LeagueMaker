package Controlador.ControladoresVista;

import Modelo.Clasificacion;
import Modelo.Competicion;
import Modelo.Enfrentamiento;
import Modelo.Jornada;
import Vista.VentanaCompeticion;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ControladorPanelVerTodosResultados {

    private ControladorVentanaCompeticion cCompeti;
    private VentanaCompeticion vCompeti;
    private JPanel pVisualizar;



    private ArrayList<Enfrentamiento> listaEnfrentamientos;
    private ArrayList<Jornada> listaJornadas;
    private Competicion competicion;

    public ControladorPanelVerTodosResultados(ControladorVentanaCompeticion cCompeti, VentanaCompeticion vCompeti) {
        this.cCompeti = cCompeti;
        this.vCompeti = vCompeti;
    }

    public void iniciarComponentes() {
        if (!vCompeti.getpBotones().isVisible()) {
            cargarDatosclasificacionPVisualizarAsinc();
            vCompeti.verPanelBotonesLateralIzq();
        } else {
            vCompeti.quitarPanelBotonesLateralIzq();
        }

        vCompeti.getbCalendario().setVisible(false);
        vCompeti.getCbJornadas().setVisible(false);
        vCompeti.getLabelJornada().setVisible(false);

        vCompeti.addBBuscarAL(new BBuscar());

        pVisualizar = vCompeti.getpVisualizar();
        vaciarPVisualizar();
    }

    public class BBuscar implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                cargarDatosclasificacionPVisualizarAsinc();
            } catch (Exception ex) {
                System.out.println("Ha sucedido el siguiente error en BBuscar\n\n" + ex.getMessage());
            }
        }
    }

    public void cargarDatosclasificacionPVisualizarAsinc() {
        cCompeti.mostrarVentanaCargaYRealizarTarea(new Runnable() {
            @Override
            public void run() {
                cCompeti.cargarCompeticiones();
                cargarCBCompeticiones();
                cargarJornadasEnfrentamientosConresultados();
                buscarCompeticion(cCompeti.getCompeticion());
                generarTablasPorJornada();
            }
        }, 30000);
    }

    public void cargarCBCompeticiones() {
        cCompeti.rellenarCBCompeticiones();
    }

    public void buscarCompeticion(Competicion competicion) {
        try {
            listaJornadas = cCompeti.consultarTablaJornadasConResultado(competicion);
            listaEnfrentamientos = new ArrayList<>();
            for (Jornada jornada : listaJornadas) {
                listaEnfrentamientos.addAll(jornada.getListaEnfrentamientos());
            }
        } catch (Exception ex) {
            System.out.println("\nHa salido un error en buscarCompeticion controladorPanelVerClasificaciones \n" + ex.getMessage());
        }
    }

    public void generarTablasPorJornada() {
        pVisualizar.removeAll();
        pVisualizar.setLayout(new GridLayout(listaJornadas.size(), 1));

        for (Jornada jornada : listaJornadas) {
            JPanel jornadaPanel = new JPanel(new BorderLayout());
            JLabel label = new JLabel("Jornada " + jornada.getnJornada());
            jornadaPanel.add(label, BorderLayout.NORTH);

            String[] columnNames = {"Equipo Local", "Equipo Visitante", "Ganador"};
            DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);

            for (Enfrentamiento enfrentamiento : jornada.getListaEnfrentamientos()) {
                String ganador = "";
                if(enfrentamiento.getGanaLocal() == 0)
                {
                    ganador = enfrentamiento.getEquipoVisitante().getNombre();
                }
                else if(enfrentamiento.getGanaLocal() == 1)
                {
                    ganador = enfrentamiento.getEquipoLocal().getNombre();
                }
                else
                {
                    ganador = "No decidido ";
                }
                Object[] rowData = {enfrentamiento.getEquipoLocal().getNombre(), enfrentamiento.getEquipoVisitante().getNombre(), ganador};
                tableModel.addRow(rowData);
            }

            JTable table = new JTable(tableModel);
            JScrollPane scrollPane = new JScrollPane(table);
            jornadaPanel.add(scrollPane, BorderLayout.CENTER);

            pVisualizar.add(jornadaPanel);
        }

        pVisualizar.revalidate();
        pVisualizar.repaint();
    }

    public void vaciarPVisualizar() {
        pVisualizar.removeAll();
        pVisualizar.revalidate();
        pVisualizar.repaint();
    }



    public void cargarJornadasEnfrentamientosConresultados()
    {

        try {
            if (!cCompeti.getListaJornadas().isEmpty()) {
                listaJornadas = cCompeti.consultarTablaJornadas(cCompeti.getListaCompetis().get(0));
                System.out.println("Carga de jornadas hecha");
            } else {
                listaJornadas.clear();
            }
        } catch (Exception ex) {
            System.out.println("\nHa sucedido un error en el proceso de cargarJornadasEnfrentamientos en el ControladorVentanaCompeticion\n" + ex.getMessage());
     }
    }

}
