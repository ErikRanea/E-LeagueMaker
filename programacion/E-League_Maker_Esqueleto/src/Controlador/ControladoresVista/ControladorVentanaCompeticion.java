/**
 * Esta clase se encagará del apartado lógico de la VentanaCompetición
 * @author Erik
 * @version 1.0
 * @since 15/05/2024
 */
package Controlador.ControladoresVista;

import Vista.VentanaCompeticion;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
        try
        {
            vCompeti = new VentanaCompeticion();


            vCompeti.addBInsertarResultAL(new BIntroResult());
            vCompeti.setVisible(true);
        }
        catch (Exception ex)
        {
            System.out.println("\n"+ex.getMessage());
        }
    }


    /**
     * Las siguientes interfaces son las correspondientes a los listener relacionados con insertar resultados
     */


    public class BIntroResult implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) {
            vCompeti.verPanelBotonesLateralIzq();
        }
    }


}
