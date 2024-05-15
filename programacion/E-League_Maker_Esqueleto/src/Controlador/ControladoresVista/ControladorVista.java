/**
 * Esta clase se encarga de controlar los diferentes controladores que controlan la vista.
 * A su vez en un nexo de diferentes metodos de la vista.
 * @author Erik
 * @version 1.0
 */
package Controlador.ControladoresVista;

import Controlador.ControladorPrincipal;

public class ControladorVista {

    /**
     * Con la siguiente variable buscamos relacionar el controlador superior, el controlador principal, con este
     * controlador.
     *
     */
    private ControladorPrincipal cp;

    /**
     * Los siguientes atributos serán objetos de cada uno de los controladores de la vista
     */
    private ControladorVentanaInscripcion cVIncrip;

    public ControladorVista(ControladorPrincipal cp)
    {
        this.cp = cp;

    }







}
