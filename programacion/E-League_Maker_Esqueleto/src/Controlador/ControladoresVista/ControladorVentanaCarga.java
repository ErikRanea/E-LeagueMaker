package Controlador.ControladoresVista;

import Vista.VentanaCarga;

import javax.swing.*;

public class ControladorVentanaCarga {

    private ControladorVista cv;
    private VentanaCarga ventanaCarga;

    public ControladorVentanaCarga(ControladorVista cv) {
        this.cv = cv;
    }

    public void crearMostrar(int milisegundos, JFrame ventanaPadre) {
        if (ventanaCarga == null || !ventanaCarga.isShowing()) {
            ventanaCarga = new VentanaCarga(ventanaPadre);
        }

        ventanaCarga.iniciarBarra();

        // Stop the loading screen after the specified milliseconds
        Timer timer = new Timer(milisegundos, e -> ocultarVentanaCarga());
        timer.setRepeats(false);
        timer.start();
    }



    public void ocultarVentanaCarga() {
        if (ventanaCarga != null) {
            ventanaCarga.detenerBarra();
        }
    }
}
