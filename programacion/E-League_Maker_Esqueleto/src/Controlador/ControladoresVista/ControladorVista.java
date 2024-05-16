/**
 * Esta clase se encarga de controlar los diferentes controladores que controlan la vista.
 * A su vez en un nexo de diferentes metodos de la vista.
 * @author Erik
 * @version 1.0
 */
package Controlador.ControladoresVista;

import Controlador.ControladorPrincipal;
import Modelo.Equipo;
import Modelo.Juego;

import java.time.LocalDate;

public class ControladorVista {

    /**
     * Con la siguiente variable buscamos relacionar el controlador superior, el controlador principal, con este
     * controlador.
     *
     */
    private ControladorPrincipal cp;

    /**
     * Los siguientes atributos serán objetos de cada uno de los controladores de la vista
     * @param cvCometi
     */
    private ControladorVentanaCompeticion cvCompeti;
    private ControladorVentanaRegistro cvRegistro;

    public ControladorVista(ControladorPrincipal cp)
    {
        this.cp = cp;
        cvRegistro = new ControladorVentanaRegistro(this);
        cvCompeti = new ControladorVentanaCompeticion(this);
        probarTablas();
        //mostrarVentanaRegistro();

    }

    public void mostrarVentanaCompeti()
    {
        cvCompeti.crearMostrar();
    }
    public void mostrarVentanaRegistro(){cvRegistro.crearMostrar();}



    public void probarTablas()
    {
        probarCrudJuegos();
        probarCrudEquipos();
    }

    public void probarCrudJuegos()
    {
        try
        {
            //Busco juego
            Juego juego = buscarJuego("Valorant");
            System.out.println("\nEl juego a "+ juego.getNombre()+" es de "+ juego.getDesarrolladora());

            //Inserto juego
            Juego juego2 = new Juego();
            juego2.setNombre("Fifa");
            juego2.setDesarrolladora("EA");
            juego2.setFechaLanzamiento(LocalDate.now());
            System.out.println("\n"+insertarJuego(juego2));

            //Modifico el juego
            juego2 = buscarJuego(juego2.getNombre());
            juego2.setNombre("Battlefield");
            modificarJuego(juego2);

            //Borro Juego

            System.out.println("\n"+borrarJuego());




        }
        catch (Exception ex)
        {
            System.out.println("\nHa salido el siguiente error:\n"+ex.getMessage());
        }

    }

    public void probarCrudEquipos()
    {
        try
        {
            //Busco Equipo
            Equipo equipo = buscarEquipo(1);
            System.out.println("\nEl juego a "+ equipo.getNombre()+" es del "+ equipo.getFechaFundacion());

            //Inserto equipo
            Equipo equipo2 = new Equipo();
            equipo2.setNombre("Los fiferos");
            equipo2.setFechaFundacion(LocalDate.now());
            System.out.println("\n"+insertarEquipo(equipo2));

            //Modifico el equipo
            equipo2 = buscarEquipo(equipo2.getNombre());
            equipo2.setNombre("Wow enjoyer");
            modificarEquipo(equipo2);

            //Borro Equipo

            System.out.println("\n"+borrarEquipo());




        }
        catch (Exception ex)
        {
            System.out.println("\nHa salido el siguiente error:\n"+ex.getMessage());
        }

    }



    public Juego buscarJuego(String nombre) throws Exception { return cp.buscarJuego(nombre);}
    public String insertarJuego(Juego juego) throws Exception { return cp.insertarJuego(juego);}
    public String borrarJuego() throws Exception{ return cp.borrarJuego();}
    public String modificarJuego(Juego juego) throws Exception{return cp.modificarJuego(juego);}

    //Equipos
    public Equipo buscarEquipo(int cod) throws Exception{return cp.buscarEquipo(cod);}
    public Equipo buscarEquipo(String nombre) throws Exception{return cp.buscarEquipo(nombre);}
    public String borrarEquipo() throws Exception {return cp.borrarEquipo();}
    public String modificarEquipo(Equipo equipo) throws Exception{return cp.modificarEquipo(equipo);}
    public String insertarEquipo(Equipo equipo) throws Exception{return cp.insertarEquipo(equipo);}



}
