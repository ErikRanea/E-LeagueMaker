/**
 * Esta clase se encarga de ser el centro de los controladores. Conectando el controlador principal de la base de datos
 * y el controlador principal de la vista.
 *
 * @author Erik
 * @version 1.0
 */

package Controlador;
import Controlador.ControladoresBD.*;
import Controlador.ControladoresVista.*;

import java.sql.SQLException;

public class ControladorPrincipal {

    /**
     * Controlador principal de la vista
     */
    private ControladorVentanas cv;
    /**
     * Controlador principal del modelo
     */
    private ControladorBD cbd;

    /**
     * Constructor por defecto
     */
    public ControladorPrincipal()
    {
        //Creamos primero la base de datos para evitarnos errores por la latencia
        cbd = new ControladorBD(this);
        cv = new ControladorVentanas();
    }





}


