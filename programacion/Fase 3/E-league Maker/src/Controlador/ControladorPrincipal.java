package Controlador;

import Controlador.ControladoresBD.ControladorBD;
import Controlador.ControladoresVistas.ControladorVentanas;

public class ControladorPrincipal {


    //CONTROLADORES

    private ControladorBD cbd;
    private ControladorVentanas cv;

    public ControladorPrincipal()
    {
        cbd = new ControladorBD(this);
        cv = new ControladorVentanas();
    }

}
