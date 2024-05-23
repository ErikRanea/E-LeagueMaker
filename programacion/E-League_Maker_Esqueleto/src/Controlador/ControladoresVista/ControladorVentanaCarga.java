/**
 * Esta clase en encarga de to.do el aspecto lógico de la ventana carga
 * @author Erik
 */
package Controlador.ControladoresVista;

import Vista.VentanaCarga;

import javax.swing.*;

public class ControladorVentanaCarga {

    private ControladorVista cv;

    private VentanaCarga vCarga;
    private JFrame ventanaPadre;


    public ControladorVentanaCarga(ControladorVista cv)
    {
        this.cv = cv;
    }


    public void crearMostrar(int milisegundos,JFrame ventanaPadre)
    {
        vCarga = new VentanaCarga(ventanaPadre);
        mostrarVentanaCarga(milisegundos);

    }


    public void mostrarVentanaCarga(int milisegundos)
    {
        if (vCarga == null) {
            vCarga = new VentanaCarga(ventanaPadre);
        }
        vCarga.iniciarBarra(milisegundos);
    }

    public void ocultarVentanaCarga()
    {
        if (vCarga != null) {
            vCarga.detenerCarga();
        }
    }
}
