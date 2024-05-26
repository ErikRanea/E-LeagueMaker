package Controlador;

import Controlador.ControladoresBD.ControladorBD;
import Controlador.ControladoresVista.ControladorVista;
import Modelo.*;

import java.util.ArrayList;
import java.util.List;

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
    public Juego buscarJuego(int cod) throws Exception{return cbd.buscarJuego(cod);}
    public void insertarJuego (Juego je) throws Exception {
        cbd.insertarJuego(je);
    }
    public void modificarJuego (Juego je) throws Exception {
        cbd.modificarJuego(je);
    }

    //Equipos
    public Equipo buscarEquipo(int cod) throws Exception{return cbd.buscarEquipo(cod);}
    public Equipo buscarEquipo(String nombre) throws Exception{return cbd.buscarEquipo(nombre);}
    public void modificarEquipo (Equipo e) throws Exception {
        cbd.modificarEquipo(e);
    }
    public void insertarEquipo (Equipo e) throws Exception {
        cbd.insertarEquipo(e);
    }



    //Patrocinadores
    public void insertarPatrocinador (Patrocinador p) throws Exception {
        cbd.insertarPatrocinador(p);
    }
    public Patrocinador buscarPatrocinador(String nombre) throws Exception
    {return cbd.buscarPatrocinador(nombre);}
    public Patrocinador buscarPatrocinador(int cod) throws Exception
    {return cbd.buscarPatrocinador(cod);}
    public void modificarPatrocinador (Patrocinador p) throws Exception {
        cbd.modificarPatrocinador(p);
    }


    //Jugadores
    public Jugador buscarJugador(String nickname) throws Exception{return cbd.buscarJugador(nickname);}
    public Jugador buscarJugador(int cod) throws Exception{return cbd.buscarJugador(cod);}
    public void modificarJugador (Jugador jd) throws Exception {
        cbd.modificarJugador(jd);
    }
    public void insertarJugador (Jugador jd) throws Exception {
        cbd.insertarJugador(jd);
    }


    //Staff
    public Staff buscarStaff(String nombre) throws Exception{return cbd.buscarStaff(nombre);}
    public Staff buscarStaff(int cod) throws Exception{return cbd.buscarStaff(cod);}
    public void modificarStaff (Staff s) throws Exception {
        cbd.modificarStaff(s);
    }
    public void insertarStaff (Staff s) throws Exception {
        cbd.insertarStaff(s);
    }


    //Competiciones
    public Competicion buscarCompeticion(int cod) throws Exception
    {return cbd.buscarCompeticion(cod);}
    public void modificarCompeticion (Competicion c) throws Exception {
        cbd.modificarCompeticion(c);
    }
    public void insertarCompeticion (Competicion c) throws Exception {
        cbd.insertarCompeticion(c);
    }
    public List<Competicion> pedirListaCompeticiones() throws Exception
    {return cbd.pedirListaCompeticiones();}
    public ArrayList<Competicion> pedirCompeticionesCerradas()throws Exception
    {return cbd.pedirCompeticionesCerradas();}



    //Usuario
    public Usuario buscarUsuario(String nickname) throws Exception{return cbd.buscarUsuario(nickname);}
    public void modificarUsuario (Usuario u) throws Exception {
        cbd.modificarUsuario(u);
    }
    public void insertarUsuario (Usuario u) throws Exception {
        cbd.insertarUsuario(u);
    }


    
    /** Metodo en el cual pasa los datos necesario para borrar elementos de la tabla
     *
     * @param opcion es un int para saber a que tabla pertenece el elemento a eliminar
     * @param cod es el codigo del objeto que se va a eliminar
     * @author Oskar
     * @version 2.0 16/05/2024
     */
    public void borrarDeTabla (int opcion, int cod) throws Exception {
        cbd.borrarDeTabla(opcion, cod);
    }

    public List buscarJugadores() throws Exception {
        return cbd.buscarJugadores();
    }

    public List buscarJuegos() throws Exception {
        return cbd.buscarJuegos();
    }
    public List buscarEquipos() throws Exception {
        return cbd.buscarEquipos();
    }
    public List buscarStaffs() throws Exception {
        return cbd.buscarStaffs();
    }
    public List buscarPatrocinadores() throws Exception {
        return cbd.buscarPatrocinadores();
    }
    public List buscarCompeticiones() throws Exception {
        return cbd.buscarCompeticiones();
    }
    public List buscarUsuarios() throws Exception {
        return cbd.buscarUsuarios();


  
  
  
      
      
      
    public ArrayList<Enfrentamiento> consultarEnfrentamientosConResultados(int codJornada) throws Exception{
        return cbd.consultarEnfrentamientosConResultados(codJornada);
    }

    public ArrayList<Clasificacion> obtenerClasificacion(int codCompeticion) throws Exception
    {
        return cbd.obtenerClasificacion(codCompeticion);
    }
    public boolean actualizarResultados(int cod,int resultado) throws Exception
    {
        return cbd.actualizarResultados(cod,resultado);

    }

    /**
     * Exportar CLASIFICACIONES XML
     */
    public String exportarClasificacionXML() throws Exception {
        return cbd.exportarClasificacionXML();
    }




    public List buscarCompeticionesAbiertas() throws Exception {
        return cbd.buscarCompeticionesAbiertas();
    }
    public List buscarEquiposInscribir(int cod) throws Exception {
        return cbd.buscarEquiposInscribir(cod);
    }
    public List buscarEquiposRescindir(int cod) throws Exception {
        return cbd.buscarEquiposRescindir(cod);
    }

    public void modificarCompeticionEstado(int cod, int estado) throws Exception {
        cbd.modificarCompeticionEstado (cod, estado);
    }

    public void inscribirEquipo(int codCompeti, int codEquipo) throws Exception {
        cbd.inscribirEquipo(codCompeti, codEquipo);
    }
    public void rescindirEquipo(int codCompeti, int codEquipo) throws Exception {
        cbd.rescindirEquipo(codCompeti, codEquipo);
    }
}
}
