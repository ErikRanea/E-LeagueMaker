package Controlador.ControladoresVista;

import Modelo.Clasificacion;
import Modelo.Competicion;
import Vista.VentanaCompeticion;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ControladorPanelVerClasificaciones {

    private ControladorVentanaCompeticion cCompeti;
    private VentanaCompeticion vCompeti;

    private JPanel pVisualizar;

    private ArrayList<Clasificacion> clasificaciones;

    public ControladorPanelVerClasificaciones(ControladorVentanaCompeticion cCompeti, VentanaCompeticion vCompeti) {
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
        cCompeti.mostrarVentanaCargaYRealizarTarea(() -> {
            cCompeti.cargarCompeticiones();
            cargarCBCompeticiones();
            buscarCompeticion(cCompeti.getCompeticion());
            generarTablaClasificacion();
        }, 30000);
    }

    public void cargarCBCompeticiones() {
        cCompeti.rellenarCBCompeticiones();
    }

    public void buscarCompeticion(Competicion competicion) {
        try {
            clasificaciones = cCompeti.obtenerClasificacion(competicion);
        } catch (Exception ex) {
            System.out.println("\nHa salido un error en buscarCompeticion controladorPanelVerClasificaciones \n" + ex.getMessage());
        }
    }

    public void generarTablaClasificacion() {
        String[] columnNames = {"Posición", "Equipo", "Puntos"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);

        for (Clasificacion c : clasificaciones) {
            Object[] rowData = {c.getPosicion(), c.getEquipo().getNombre(), c.getPuntos()};
            tableModel.addRow(rowData);
        }

        JTable table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);

        pVisualizar.removeAll();
        pVisualizar.setLayout(new BorderLayout());
        pVisualizar.add(scrollPane, BorderLayout.CENTER);
        pVisualizar.revalidate();
        pVisualizar.repaint();
    }

    public void vaciarPVisualizar() {
        pVisualizar.removeAll();
        pVisualizar.revalidate();
        pVisualizar.repaint();
    }
}
