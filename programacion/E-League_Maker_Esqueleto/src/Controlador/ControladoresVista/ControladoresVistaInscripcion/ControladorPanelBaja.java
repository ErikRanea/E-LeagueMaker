package Controlador.ControladoresVista.ControladoresVistaInscripcion;

import Modelo.*;
import Vista.VentanaInscripcion;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class ControladorPanelBaja {
    private VentanaInscripcion vi;
    private ControladorVentanaInscripcion cvi;

    private int opcionTabla;
    private int opcionAccion;

    private List<Jugador> listaJugadores;
    private List<Juego> listaJuegos;
    private List<Equipo> listaEquipos;
    private List<Staff> listaStaffs;
    private List<Patrocinador> listaPatrocinadores;
    private List<Competicion> listaCompeticiones;
    private List<Usuario> listaUsuarios;

    public ControladorPanelBaja(ControladorVentanaInscripcion cvi, VentanaInscripcion vi)
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

    /** Action listener del botón baja asignado con el valor 2 que dependiendo el valor recibido de los
     * botones de las tablas se visibilizará el panel correspondiente llevando las acciones adecuadas.
     *
     * @author Oskar
     * @version 2.0 15/05/2024
     */
    public void BBaja(int opcionAccion, int opcionTabla) throws Exception {
        this.opcionTabla = opcionTabla;
        this.opcionAccion = opcionAccion;

        vi.getCbOpciones().removeAllItems();
        switch (opcionTabla)
        {
            case 1: // Jugadores
                opcionAccion = 2; // Que tipo de acción es
                vi.crearElementos(opcionTabla, opcionAccion); // Modificar el panel
                listaJugadores.clear();
                listaJugadores = cvi.buscarJugadores(); // Llamar y rellenar la lista

                // Llenar la Combo box
                vi.getCbOpciones().addItem("Elija el jugador que desea eliminar");
                for (int x = 0; listaJugadores.size() > x; x++)
                {
                    vi.getCbOpciones().addItem(listaJugadores.get(x).getNickname());
                }
                break;
            case 2: // Juegos
                opcionAccion = 2; // Que tipo de acción es
                vi.crearElementos(opcionTabla, opcionAccion); // Modificar el panel
                listaJuegos.clear();
                listaJuegos = cvi.buscarJuegos(); // Llamar y rellenar la lista

                // Llenar la Combo box
                vi.getCbOpciones().addItem("Elija el juego que desea eliminar");
                for (int x = 0; listaJuegos.size() > x; x++)
                {
                    vi.getCbOpciones().addItem(listaJuegos.get(x).getNombre());
                }
                break;
            case 3: // Equipos
                opcionAccion = 2; // Que tipo de acción es
                vi.crearElementos(opcionTabla, opcionAccion); // Modificar el panel
                listaEquipos.clear();
                listaEquipos = cvi.buscarEquipos(); // Llamar y rellenar la lista

                // Llenar la Combo box
                vi.getCbOpciones().addItem("Elija el equipo que desea eliminar");
                for (int x = 0; listaEquipos.size() > x; x++)
                {
                    vi.getCbOpciones().addItem(listaEquipos.get(x).getNombre());
                }
                break;
            case 4: // Staffs
                opcionAccion = 2; // Que tipo de acción es
                vi.crearElementos(opcionTabla, opcionAccion); // Modificar el panel
                listaStaffs.clear();
                listaStaffs = cvi.buscarStaffs(); // Llamar y rellenar la lista

                // Llenar la Combo box
                vi.getCbOpciones().addItem("Elija el miembro del staff que desea eliminar");
                for (int x = 0; listaStaffs.size() > x; x++)
                {
                    vi.getCbOpciones().addItem(listaStaffs.get(x).getNombre() + " " + listaStaffs.get(x).getApellido());
                }
                break;
            case 5: // Patrocinadores
                opcionAccion = 2; // Que tipo de acción es
                vi.crearElementos(opcionTabla, opcionAccion); // Modificar el panel
                listaPatrocinadores.clear();
                listaPatrocinadores = cvi.buscarPatrocinadores(); // Llamar y rellenar la lista

                // Llenar la Combo box
                vi.getCbOpciones().addItem("Elija el patrocinador que desea eliminar");
                for (int x = 0; listaPatrocinadores.size() > x; x++)
                {
                    vi.getCbOpciones().addItem(listaPatrocinadores.get(x).getNombre());
                }
                break;
            case 6: // Competiciones
                opcionAccion = 2; // Que tipo de acción es
                vi.crearElementos(opcionTabla, opcionAccion); // Modificar el panel
                listaCompeticiones.clear();
                listaCompeticiones = cvi.buscarCompeticiones(); // Llamar y rellenar la lista

                // Llenar la Combo box
                vi.getCbOpciones().addItem("Elija la competición que desea eliminar");
                for (int x = 0; listaCompeticiones.size() > x; x++)
                {
                    vi.getCbOpciones().addItem(listaCompeticiones.get(x).getNombre());
                }
                break;
            case 7: // Usuarios
                opcionAccion = 2; // Que tipo de acción es
                vi.crearElementos(opcionTabla, opcionAccion); // Modificar el panel
                listaUsuarios.clear();
                listaUsuarios = cvi.buscarUsuarios(); // Llamar y rellenar la lista

                // Llenar la Combo box
                vi.getCbOpciones().addItem("Elija el usuario que desea eliminar");
                for (int x = 0; listaUsuarios.size() > x; x++)
                {
                    vi.getCbOpciones().addItem(listaUsuarios.get(x).getNickname());
                }
                break;
        }
            vi.addBBorrarAL(new JbBorrar());
    }

    /** Action listener del botón borrar del panel baja en el que se verifica que haya algo seleccionado
     * y depues se elimina el objeto deseado de la base de datos
     *
     * @author Oskar
     * @version 2.0 16/05/2024
     */
    public class JbBorrar implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            try
            {
                int cod = 0;
                int seleccionado;
                switch (opcionTabla)
                {
                    case 1: // Jugadores
                        // Validación
                        if (vi.getCbOpciones().getSelectedIndex() == 0)
                            throw new Exception("Tiene que seleccionar un jugador");

                        // Obtener el código del objeto que se quiere borrar
                        seleccionado = vi.getCbOpciones().getSelectedIndex()-1;
                        cod = listaJugadores.get(seleccionado).getCod();
                        listaJugadores.remove(vi.getCbOpciones().getSelectedIndex()-1); // Borrar el jugador de la lista
                        break;
                    case 2: // Juegos
                        // Validación
                        if (vi.getCbOpciones().getSelectedIndex() == 0)
                            throw new Exception("Tiene que seleccionar un juego");

                        // Obtener el código del objeto que se quiere borrar
                        seleccionado = vi.getCbOpciones().getSelectedIndex()-1;
                        cod = listaJuegos.get(seleccionado).getCod();
                        listaJuegos.remove(vi.getCbOpciones().getSelectedIndex()-1); // Borrar el juego de la lista
                        break;
                    case 3: // Equipos
                        // Validación
                        if (vi.getCbOpciones().getSelectedIndex() == 0)
                            throw new Exception("Tiene que seleccionar un equipo");

                        // Obtener el código del objeto que se quiere borrar
                        seleccionado = vi.getCbOpciones().getSelectedIndex()-1;
                        cod = listaEquipos.get(seleccionado).getCod();
                        listaEquipos.remove(vi.getCbOpciones().getSelectedIndex()-1); // Borrar el equipo de la lista
                        break;
                    case 4: // Staffs
                        // Validación
                        if (vi.getCbOpciones().getSelectedIndex() == 0)
                            throw new Exception("Tiene que seleccionar un miembro del staff");

                        // Obtener el código del objeto que se quiere borrar
                        seleccionado = vi.getCbOpciones().getSelectedIndex()-1;
                        cod = listaStaffs.get(seleccionado).getCod();
                        listaStaffs.remove(vi.getCbOpciones().getSelectedIndex()-1); // Borrar el miembro del staff de la lista
                        break;
                    case 5: // Patrocinadores
                        // Validación
                        if (vi.getCbOpciones().getSelectedIndex() == 0)
                            throw new Exception("Tiene que seleccionar un patrocinador");

                        // Obtener el código del objeto que se quiere borrar
                        seleccionado = vi.getCbOpciones().getSelectedIndex()-1;
                        cod = listaPatrocinadores.get(seleccionado).getCod();
                        listaPatrocinadores.remove(vi.getCbOpciones().getSelectedIndex()-1); // Borrar el patrocinador de la lista
                        break;
                    case 6: // Competiciones
                        // Validación
                        if (vi.getCbOpciones().getSelectedIndex() == 0)
                            throw new Exception("Tiene que seleccionar una competición");

                        // Obtener el código del objeto que se quiere borrar
                        seleccionado = vi.getCbOpciones().getSelectedIndex()-1;
                        cod = listaCompeticiones.get(seleccionado).getCod();
                        listaCompeticiones.remove(vi.getCbOpciones().getSelectedIndex()-1); // Borrar la competición de la lista
                        break;
                    case 7: // Usuarios
                        // Validación
                        if (vi.getCbOpciones().getSelectedIndex() == 0)
                            throw new Exception("Tiene que seleccionar un usuario");

                        // Obtener el código del objeto que se quiere borrar
                        seleccionado = vi.getCbOpciones().getSelectedIndex()-1;
                        cod = listaUsuarios.get(seleccionado).getCod();
                        listaUsuarios.remove(vi.getCbOpciones().getSelectedIndex()-1); // Borrar el usuario de la lista
                        break;
                }
                // Se llama a la función borrar tabla del controlador vista con opcion tabla para identificar
                // a que tabla corresponde y con el codigo del objeto que se quiere borrar
                cvi.borrarDeTabla(opcionTabla, cod);
            }
            catch (Exception ex)
            {
                vi.mostrarError(ex.getMessage());
            }
        }
    }
}
