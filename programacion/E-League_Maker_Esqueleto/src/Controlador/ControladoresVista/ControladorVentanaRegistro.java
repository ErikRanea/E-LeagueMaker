/**
 * Clase encargada del apartado lógico de la VentanaRegistro
 * @author Erik
 * @since 16/05/2024
 * @version 1.0
 */
package Controlador.ControladoresVista;

import Vista.VentanaRegistro;

public class ControladorVentanaRegistro {

    private ControladorVista cv;

    private VentanaRegistro vRegistro;



    public ControladorVentanaRegistro(ControladorVista cv)
    {
        this.cv = cv;
    }

    public void crearMostrar()
    {
        vRegistro = new VentanaRegistro();

        vRegistro.setVisible(true);
    }
}
