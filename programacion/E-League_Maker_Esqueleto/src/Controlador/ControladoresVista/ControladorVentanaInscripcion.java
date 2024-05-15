/**
 * Este controlador se encarga de to-do el apartado lógico de la VentanaInscripcion.
 * @author Erik
 * @version 1.0
 */
package Controlador.ControladoresVista;

import Vista.VentanaInscripcion;

public class ControladorVentanaInscripcion {

    /**
     * Varible para conectar con la controlador superior. En este caso el controlador de la vista
     */
    final ControladorVista cv;

    /**
     * Ahora crearemos todas las variables relacionadas con la ventana
     */
    private VentanaInscripcion vInscrip;

    public ControladorVentanaInscripcion(ControladorVista cv)
    {
        this.cv = cv;
    }


    /**
     * Metodo para poder crear la VentanaInscripcion
     */
    public void crearMostrar()
    {
        vInscrip = new VentanaInscripcion();
        



        vInscrip.setVisible(true);
    }
}
