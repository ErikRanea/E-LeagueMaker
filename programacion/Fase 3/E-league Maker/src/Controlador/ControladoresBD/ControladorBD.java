package Controlador.ControladoresBD;

import Controlador.ControladorPrincipal;

public class ControladorBD {

    //Controladores
    private ControladorPrincipal cp;


    private ControladorTCompeticiones ctCompeti;
    private ControladorTEnfrentamientos ctEnfren;
    private ControladorTEquipos ctEquipos;
    private ControladorTJornadas ctJornadas;
    private ControladorTJuegos ctJuegos;
    private ControladorTJugadores ctJugadores;
    private ControladorTPatrocinadores ctPatro;
    private ControladorTPuntos_Equipos ctPuntosEquipos;
    private ControladorTStaffs ctStaff;
    private ControladorTUsuarios ctUsuarios;



    public ControladorBD(ControladorPrincipal cp)
    {
        this.cp = cp;
        iniciarControladoresDeTablas();
    }

    



    public void iniciarControladoresDeTablas()
    {
        ctCompeti = new ControladorTCompeticiones();
        ctEnfren = new ControladorTEnfrentamientos();
        ctEquipos = new ControladorTEquipos();
        ctJornadas = new ControladorTJornadas();
        ctJuegos = new ControladorTJuegos();
        ctJugadores = new ControladorTJugadores();
        ctPatro = new ControladorTPatrocinadores();
        ctPuntosEquipos = new ControladorTPuntos_Equipos();
        ctStaff = new ControladorTStaffs();
        ctUsuarios = new ControladorTUsuarios();
    }

}
