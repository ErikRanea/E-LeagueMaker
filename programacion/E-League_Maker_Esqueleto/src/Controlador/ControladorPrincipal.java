package Controlador;

import Controlador.ControladoresBD.ControladorBD;
import Controlador.ControladoresVista.ControladorVista;
import Modelo.Equipo;
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

    //Equipos
    public Equipo buscarEquipo(int cod) throws Exception{return cbd.buscarEquipo(cod);}
    public Equipo buscarEquipo(String nombre) throws Exception{return cbd.buscarEquipo(nombre);}
    public String borrarEquipo() throws Exception {return cbd.borrarEquipo();}
    public String modificarEquipo(Equipo equipo) throws Exception{return cbd.modificarEquipo(equipo);}
    public String insertarEquipo(Equipo equipo) throws Exception{return cbd.insertarEquipo(equipo);}

}
