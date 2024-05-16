package Controlador;

import Controlador.ControladoresBD.ControladorBD;
import Controlador.ControladoresVista.ControladorVista;
import Modelo.Juego;

public class ControladorPrincipal {

    private ControladorBD cbd;
    private ControladorVista cv;



    public ControladorPrincipal()
    {
        cbd = new ControladorBD(this);
        cv = new ControladorVista(this);
    }





    //Tabla Juegos
    public Juego buscarJuego(String nombre) throws Exception { return cbd.buscarJuego(nombre);}
    public String insertarJuego(Juego juego) throws Exception { return cbd.insertarJuego(juego);}
    public String borrarJuego() throws Exception{ return cbd.borrarJuego();}
    public String modificarJuego(Juego juego) throws Exception{return cbd.modificarJuego(juego);}

}
