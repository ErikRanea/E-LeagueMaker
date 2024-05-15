/**
 * Esta clase se encagará del apartado lógico de la VentanaCompetición
 * @author Erik
 * @version 1.0
 * @since 15/05/2024
 */
package Controlador.ControladoresVista;

import Vista.VentanaCompeticion;

public class ControladorVentanaCompeticion {

    /**
     * Atributo que conecta controlador superior con el mismo.
     * @param cv
     */
    private ControladorVista cv;


    /**
     * Atributo que almacena la VentanaCompeticion
     * @param vCompeti
     */
    private VentanaCompeticion vCompeti;


    public ControladorVentanaCompeticion(ControladorVista cv)
    {
        this.cv = cv;
    }

    public void crearMostrar()
    {
        vCompeti = new VentanaCompeticion();

        vCompeti.setVisible(true);
        //vCompeti.iniciarComponentes();
    }

}
