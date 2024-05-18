/**
 * Esta clase se encagará del apartado lógico de la VentanaCompetición
 * @author Erik
 * @version 1.0
 * @since 15/05/2024
 */
package Controlador.ControladoresVista;

import Modelo.Competicion;
import Vista.VentanaCompeticion;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

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

    /**
     * Atributos para instanciar objetos de la base de datos
     * @param listaCompetis
     * @param competicion
     */
    private ArrayList<Competicion> listaCompetis;
    private Competicion competicion;

    public ControladorVentanaCompeticion(ControladorVista cv)
    {
        this.cv = cv;listaCompetis = new ArrayList<>();
    }

    public void crearMostrar()
    {
        try
        {

            vCompeti = new VentanaCompeticion();

            vCompeti.addBLogOutAL(new BLogOut());
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


    public class BLogOut implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            vCompeti.dispose();
            cv.mostrarVentanaLogin();
        }
    }

    public class BIntroResult implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            vCompeti.verPanelBotonesLateralIzq();
            rellenarCBCompeticiones();
        }
    }


    public void rellenarCBCompeticiones()
    {
        try
        {
            listaCompetis = cv.pedirCompeticionesCerradas();

            for(Competicion c : listaCompetis)
            {
                vCompeti.getCbCompeticiones().addItem(c.getNombre());
            }
        }
        catch (Exception ex)
        {
            System.out.println("\nHa salido el siguiente error:\n"+ex.getMessage());
        }

    }

}
