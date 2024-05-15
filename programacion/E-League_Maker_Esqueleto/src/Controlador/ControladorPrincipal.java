package Controlador;

import Controlador.ControladoresBD.ControladorBD;
import Controlador.ControladoresVista.ControladorVista;

public class ControladorPrincipal {

    private ControladorBD cbd;
    private ControladorVista cv;



    public ControladorPrincipal()
    {
        cbd = new ControladorBD(this);
       // cv = new ControladorVista(this);
    }

}
