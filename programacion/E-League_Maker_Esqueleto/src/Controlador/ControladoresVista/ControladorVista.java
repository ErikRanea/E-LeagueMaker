/**
 * Esta clase se encarga de controlar los diferentes controladores que controlan la vista.
 * A su vez en un nexo de diferentes metodos de la vista.
 * @author Erik
 * @version 1.0
 */
package Controlador.ControladoresVista;

import Controlador.ControladorPrincipal;
import Modelo.*;

import java.time.LocalDate;
import java.util.ArrayList;

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
        //probarCrudJuegos();
        //probarCrudEquipos();
       // probarCrudPatrocinadores();
       // probarCrudJugadores();
        //probarCrudStaffs();
        probarCrudCompeticiones();
    }

    public void probarCrudJuegos()
    {
        try
        {
            //Busco juego
            Juego juego = buscarJuego(1);
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
            System.out.println("\nEl equipo a "+ equipo.getNombre()+" es del "+ equipo.getFechaFundacion());

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


    public void probarCrudPatrocinadores()
    {
        try
        {
            //Busco Patrocinador
            Patrocinador patrocinador =buscarPatrocinador(1);
            System.out.println("\nEl patrocinador a "+ patrocinador.getNombre()+" es del "+ patrocinador.getEquipo().getNombre());

            //Inserto equipo
            Patrocinador patrocinador2 = new Patrocinador();
            patrocinador2.setNombre("Inca cola");
            patrocinador2.setEquipo(buscarEquipo(1));
            System.out.println("\n"+insertarEPatrocinador(patrocinador2));

            //Modifico el equipo
            patrocinador2 = buscarPatrocinador(patrocinador2.getNombre());
            patrocinador2.setNombre("Pepsi");
            modificarPatrocinador(patrocinador2);

            //Borro Equipo

            System.out.println("\n"+borrarPatrocinador());




        }
        catch (Exception ex)
        {
            System.out.println("\nHa salido el siguiente error:\n"+ex.getMessage());
        }
    }


    public void probarCrudJugadores()
    {
        try
        {
            //Busco jugador
            Jugador jugador =buscarJugador(1);
            String equipoS = jugador.getEquipo().getNombre();
            System.out.println("\nEl jugador  "+ jugador.getNombre()+" es del "+ equipoS);

            //Inserto jugador
            Jugador jugador2 = new Jugador();
            jugador2.setNombre("Rodrigo ");
            jugador2.setEquipo(buscarEquipo(1));
            jugador2.setNickname("ShadowP");
            jugador2.setSalario(1135);
            System.out.println("\n"+insertarJugador(jugador2));

            //Modifico el jugador
            jugador2 = buscarJugador(jugador2.getNickname());
            jugador2.setNombre("Rodrigo goes");
            modificarJugador(jugador2);

            //Borro Equipo

            System.out.println("\n"+borrarJugador());




        }
        catch (Exception ex)
        {
            System.out.println("\nHa salido el siguiente error:\n"+ex.getMessage());
        }
    }

    public void probarCrudStaffs()
    {
        try
        {
            //Busco STAFF
            Staff staff =buscarStaff(1);
            String equipoS = staff.getEquipo().getNombre();
            System.out.println("\nEl jugador  "+ staff.getNombre()+" es del "+ equipoS);

            //Inserto STAFF
            Staff staff2 = new Staff();
            staff2.setCod(150);
            staff2.setNombre("Ancelloti");
            staff2.setEquipo(buscarEquipo(1));
            staff2.setSalario(1135);
            System.out.println("\n"+insertarJStaff(staff2));

            //Modifico STAFF
            staff2 = buscarStaff(staff2.getNombre());
            staff2.setNombre("Pep Guardiola");
            modificarStaff(staff2);

            //Borro Staff

            System.out.println("\n"+borrarStaff());




        }
        catch (Exception ex)
        {
            System.out.println("\nHa salido el siguiente error:\n"+ex.getMessage());
        }
    }

    public void probarCrudCompeticiones()
    {
        try
        {
            //Busco Competi
            Competicion competicion = buscarCompeticion(1);
            String juego = competicion.getJuego().getNombre();
            System.out.println("\nLa competicion  "+ competicion.getNombre()+" es del juego "+ juego);

            //Inserto Competi
            Competicion competicion2 = new Competicion();
            competicion2.setNombre("La liga genuine");
            competicion2.setJuego(buscarJuego(1));
            competicion2.setFechaInicio(LocalDate.now().plusWeeks(1));
            competicion2.setFechaFin(LocalDate.now().plusMonths(1));
            competicion2.setEstadoAbierto(false);
            System.out.println("\n"+insertarCompeticion(competicion2));

            System.out.println("\n"+borrarCompeticion());

            String lista = "";
            for(Competicion com : pedirListaCompeticiones())
            {
                lista= lista + "\n" + com.getNombre();
            }

            System.out.println("\nLista de competiciones: "+lista);
        }
        catch (Exception ex)
        {
            System.out.println("\nHa salido el siguiente error:\n"+ex.getMessage());
        }
    }

    public Juego buscarJuego(String nombre) throws Exception { return cp.buscarJuego(nombre);}
    public Juego buscarJuego(int cod) throws Exception{return cp.buscarJuego(cod);}
    public String insertarJuego(Juego juego) throws Exception { return cp.insertarJuego(juego);}
    public String borrarJuego() throws Exception{ return cp.borrarJuego();}
    public String modificarJuego(Juego juego) throws Exception{return cp.modificarJuego(juego);}

    //Equipos
    public Equipo buscarEquipo(int cod) throws Exception{return cp.buscarEquipo(cod);}
    public Equipo buscarEquipo(String nombre) throws Exception{return cp.buscarEquipo(nombre);}
    public String borrarEquipo() throws Exception {return cp.borrarEquipo();}
    public String modificarEquipo(Equipo equipo) throws Exception{return cp.modificarEquipo(equipo);}
    public String insertarEquipo(Equipo equipo) throws Exception{return cp.insertarEquipo(equipo);}


    //Patrocinadores
    public String insertarEPatrocinador(Patrocinador patrocinador) throws Exception
    { return cp.insertarEPatrocinador(patrocinador);}
    public Patrocinador buscarPatrocinador(String nombre) throws Exception
    {return cp.buscarPatrocinador(nombre);}
    public Patrocinador buscarPatrocinador(int cod) throws Exception
    {return cp.buscarPatrocinador(cod);}
    public String borrarPatrocinador() throws Exception
    {
        return cp.borrarPatrocinador();
    }
    public String modificarPatrocinador(Patrocinador patrocinador) throws Exception
    {
        return cp.modificarPatrocinador(patrocinador);
    }

    //Jugadores
    public Jugador buscarJugador(String nickname) throws Exception{return cp.buscarJugador(nickname);}
    public Jugador buscarJugador(int cod) throws Exception{return cp.buscarJugador(cod);}
    public String borrarJugador() throws Exception{return cp.borrarJugador();}
    public String modificarJugador(Jugador jugador) throws Exception{return cp.modificarJugador(jugador);}
    public String insertarJugador(Jugador jugador) throws Exception{return cp.insertarJugador(jugador);}


    //Staff
    public Staff buscarStaff(String nombre) throws Exception{return cp.buscarStaff(nombre);}
    public Staff buscarStaff(int cod) throws Exception{return cp.buscarStaff(cod);}
    public String borrarStaff() throws Exception{return cp.borrarStaff();}
    public String modificarStaff(Staff staff) throws Exception{return cp.modificarStaff(staff);}
    public String insertarJStaff(Staff staff) throws Exception{return cp.insertarJStaff(staff);}



    //Competiciones
    public Competicion buscarCompeticion(int cod) throws Exception
    {return cp.buscarCompeticion(cod);}
    public String borrarCompeticion() throws Exception
    {return cp.borrarCompeticion();}
    public String modificarCompeticion(Competicion competicion) throws Exception
    {return cp.modificarCompeticion(competicion);}
    public String insertarCompeticion(Competicion competicion) throws Exception
    {return cp.insertarCompeticion(competicion);}

    public ArrayList<Competicion> pedirListaCompeticiones() throws Exception
    {return cp.pedirListaCompeticiones();}


}
