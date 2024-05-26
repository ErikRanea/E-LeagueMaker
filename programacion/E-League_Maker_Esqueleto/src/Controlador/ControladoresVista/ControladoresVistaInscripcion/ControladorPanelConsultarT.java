package Controlador.ControladoresVista.ControladoresVistaInscripcion;

import Modelo.*;
import Vista.VentanaInscripcion;

import java.util.ArrayList;
import java.util.List;

public class ControladorPanelConsultarT {
    private ControladorVentanaInscripcion cvi;
    private VentanaInscripcion vi;

    private int opcionTabla;
    private int opcionAccion;

    private List<Jugador> listaJugadores;
    private List<Juego> listaJuegos;
    private List<Equipo> listaEquipos;
    private List<Staff> listaStaffs;
    private List<Patrocinador> listaPatrocinadores;
    private List<Competicion> listaCompeticiones;
    private List<Usuario> listaUsuarios;

    public ControladorPanelConsultarT(ControladorVentanaInscripcion cvi, VentanaInscripcion vi)
    {
        this.cvi = cvi;
        this.vi = vi;

        listaCompeticiones = new ArrayList<>();
        listaEquipos = new ArrayList<>();
        listaJuegos = new ArrayList<>();
        listaJugadores = new ArrayList<>();
        listaUsuarios = new ArrayList<>();
        listaStaffs = new ArrayList<>();
        listaPatrocinadores = new ArrayList<>();
    }

    /** Action listener del botón consultar Todos asignado con el valor 5 que dependiendo el valor recibido de los
     * botones de las tablas se visibilizará el panel correspondiente llevando las acciones adecuadas
     *
     * @author Oskar
     * @version 2.0 19/05/2024
     */
    public void BConsultarT(int opcionAccion, int opcionTabla) throws Exception {
        this.opcionTabla = opcionTabla;
        this.opcionAccion = opcionAccion;
        switch (opcionTabla)
        {
            case 1: // Jugadores
                opcionAccion = 5; // Que tipo de acción es
                vi.crearElementos(opcionTabla, opcionAccion); // Modificar el panel
                listaJugadores.clear();
                listaJugadores = cvi.buscarJugadores(); // Llamar y rellenar la lista

                String jugadores = "";
                for (int x = 0; x < listaJugadores.size(); x++)
                {
                    jugadores = jugadores + listaJugadores.get(x).toString() + "\n";
                }
                vi.getTaTodos().setText(jugadores);
                break;
            case 2: // Juegos
                opcionAccion = 5; // Que tipo de acción es
                vi.crearElementos(opcionTabla, opcionAccion); // Modificar el panel
                listaJuegos.clear();
                listaJuegos = cvi.buscarJuegos(); // Llamar y rellenar la lista

                String juegos = "";
                for (int x = 0; x < listaJuegos.size(); x++)
                {
                    juegos = juegos + listaJuegos.get(x).toString() + "\n";
                }
                vi.getTaTodos().setText(juegos);
                break;
            case 3: // Equipos
                opcionAccion = 5; // Que tipo de acción es
                vi.crearElementos(opcionTabla, opcionAccion); // Modificar el panel
                listaEquipos.clear();
                listaEquipos = cvi.buscarEquipos(); // Llamar y rellenar la lista

                String equipos = "";
                for (int x = 0; x < listaEquipos.size(); x++)
                {
                    equipos = equipos + listaEquipos.get(x).toString() + "\n";
                }
                vi.getTaTodos().setText(equipos);
                break;
            case 4: // Staffs
                opcionAccion = 5; // Que tipo de acción es
                vi.crearElementos(opcionTabla, opcionAccion); // Modificar el panel
                listaStaffs.clear();
                listaStaffs = cvi.buscarStaffs(); // Llamar y rellenar la lista

                String staffs = "";
                for (int x = 0; x < listaStaffs.size(); x++)
                {
                    staffs = staffs + listaStaffs.get(x).toString() + "\n";
                }
                vi.getTaTodos().setText(staffs);
                break;
            case 5: // Patrocinadores
                opcionAccion = 5; // Que tipo de acción es
                vi.crearElementos(opcionTabla, opcionAccion); // Modificar el panel
                listaPatrocinadores.clear();
                listaPatrocinadores = cvi.buscarPatrocinadores(); // Llamar y rellenar la lista

                String patrocinadores = "";
                for (int x = 0; x < listaPatrocinadores.size(); x++)
                {
                    patrocinadores = patrocinadores + listaPatrocinadores.get(x).toString() + "\n";
                }
                vi.getTaTodos().setText(patrocinadores);
                break;
            case 6: // Competiciones
                opcionAccion = 5; // Que tipo de acción es
                vi.crearElementos(opcionTabla, opcionAccion); // Modificar el panel
                listaCompeticiones.clear();
                listaCompeticiones = cvi.buscarCompeticiones(); // Llamar y rellenar la lista

                String competiciones = "";
                for (int x = 0; x < listaCompeticiones.size(); x++)
                {
                    String estado;
                    if (listaCompeticiones.get(x).isEstadoAbierto())
                        estado = "Abierto";
                    else
                        estado = "Cerrado";

                    competiciones = competiciones + listaCompeticiones.get(x).toString() +  ", Estado:'" + estado + '\'' +  "\n";
                }
                vi.getTaTodos().setText(competiciones);
                break;
            case 7: // Usuarios
                opcionAccion = 5; // Que tipo de acción es
                vi.crearElementos(opcionTabla, opcionAccion); // Modificar el panel
                listaUsuarios.clear();
                listaUsuarios = cvi.buscarUsuarios(); // Llamar y rellenar la lista

                String usuarios = "";
                for (int x = 0; x < listaUsuarios.size(); x++)
                {
                    String permisos;
                    if (listaUsuarios.get(x).isAdmin())
                        permisos = "Administrador";
                    else
                        permisos = "Estandar";

                    usuarios = usuarios + listaUsuarios.get(x).toString() + ", Contraseña:'" + permisos + '\'' + "\n";
                }
                vi.getTaTodos().setText(usuarios);
                break;
        }
    }
}
