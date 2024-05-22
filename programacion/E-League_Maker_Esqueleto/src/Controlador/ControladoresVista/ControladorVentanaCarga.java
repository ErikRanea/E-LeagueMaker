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


    public ControladorVentanaCarga(ControladorVista cv)
    {
        this.cv = cv;
    }


    public void crearMostrar(int milisegundos,JFrame ventanaPadre)
    {
        vCarga = new VentanaCarga(ventanaPadre);
        vCarga.iniciarBarra(milisegundos);
        vCarga.dispose();
    }





}
