/**
 * Esta clase se encagará del apartado lógico de la VentanaCompetición
 * @author Erik
 * @version 1.0
 * @since 15/05/2024
 */
package Controlador.ControladoresVista;

import Vista.VentanaAMano;
import Vista.VentanaCompeticion;
import Vista.VentanaTrasteo;

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
    private VentanaTrasteo vTrasteo;

    public ControladorVentanaCompeticion(ControladorVista cv)
    {
        this.cv = cv;
    }

    public void crearMostrar()
    {
        try
        {
            //vCompeti = new VentanaCompeticion();

            //vCompeti.setVisible(true);
            // vCompeti.iniciarComponentes();

            vTrasteo = new VentanaTrasteo();


            vTrasteo.pack();
            vTrasteo.setVisible(true);

        }
        catch (Exception ex)
        {
            System.out.println("\n"+ex.getMessage());
        }
    }

}
