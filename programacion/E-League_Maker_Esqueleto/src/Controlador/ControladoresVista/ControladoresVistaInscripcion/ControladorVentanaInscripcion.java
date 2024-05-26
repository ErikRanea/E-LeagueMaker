package Controlador.ControladoresVista.ControladoresVistaInscripcion;

import Controlador.ControladoresVista.ControladorVista;
import Modelo.*;
import Vista.VentanaInscripcion;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ControladorVentanaInscripcion {

    // Controladores
    private ControladorVista cv;
    private ControladorPanelBaja cpb;
    private ControladorPanelConsultarT cpct;
    private ControladorPanelJugador cpjd;
    private ControladorPanelJuego cpje;
    private ControladorPanelAbrir cpa;
    private ControladorPanelEquipo cpe;
    private ControladorPanelStaff cps;
    private ControladorPanelPatrocinador cpp;
    private ControladorPanelCompeticion cpc;
    private ControladorPanelUsuario cpu;
    private ControladorPanelInscribir cpi;


    // Vistas
    private VentanaInscripcion vi;


    //Varibles de la clase

    /**
     * Estos booleanos sirven para saber que panel hay que printar y con que tipo de objetos y datos vamos a tratar.
     * Variables de tipos de objetos
     */
    private int opcionTabla;
    private int opcionAccion;

    public ControladorVentanaInscripcion(ControladorVista cv)
    {
        this.cv = cv;


    }

    /** Metodo que distribulle los action listeners del panel horizontal de arriba
     *
     * @author Rodrigo
     * @version 1.0 14/05/2024
     */
    public void crearMostrarInscripcion()
    {
        // Creación de la ventana
        vi = new VentanaInscripcion();

        cargarControladoresPaneles();

        // Listeners
        vi.addBJugadoresAL(new BJugadores());
        vi.addBJuegosAL(new BJuegos());
        vi.addBEquiposAL(new BEquipos());
        vi.addBPatrocinadoresAL(new BPatrocinadores());
        vi.addBCompeticionesAL(new BCompeticiones());
        vi.addBStaffsAL(new BStaffs());
        vi.addBUsuariosAL(new BUsuarios());

        vi.addBLogOutAL(new BLogOut());

        mostrarPVacio(); // Mostrar panel vacio

        vi.setVisible(true);
    }

    public class BLogOut implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            vi.dispose();
            cv.mostrarVentanaLogin();
        }
    }

    public void cargarControladoresPaneles()
    {
        cpb = new ControladorPanelBaja(this, vi);
        cpct = new ControladorPanelConsultarT(this, vi);
        cpjd = new ControladorPanelJugador(this, vi);
        cpje = new ControladorPanelJuego(this, vi);
        cpa = new ControladorPanelAbrir(this, vi);
        cpe = new ControladorPanelEquipo(this, vi);
        cps = new ControladorPanelStaff(this, vi);
        cpp = new ControladorPanelPatrocinador(this,vi);
        cpc = new ControladorPanelCompeticion(this, vi);
        cpu = new ControladorPanelUsuario(this, vi);
        cpi = new ControladorPanelInscribir(this, vi);
    }

    /**Metodo para que cuando cambia de tabla se muestre un panel vacio
     *
     * @author Oskar
     * @version 2.0 18/05/2024
     */
    public void mostrarPVacio()
    {
        vi.getpInicio().setVisible(true);
        vi.getpAltaModiConsulJd().setVisible(false);
        vi.getpBaja().setVisible(false);
        vi.getpAmcJe().setVisible(false);
        vi.getpAmcP().setVisible(false);
        vi.getpAmcS().setVisible(false);
        vi.getpAmcC().setVisible(false);
        vi.getpAmcU().setVisible(false);
        vi.getpConsultarTodos().setVisible(false);
        vi.getpAbrir().setVisible(false);
        vi.getpAmcE().setVisible(false);
        vi.getpInscribir().setVisible(false);
        vi.getbExtra().setVisible(false);
    }

    /** Action listener del botón jugador asignado con el valor 1 que dentro tiene los
     * action listeners de las acciones que se pueden cometer
     *
     * @author Rodrigo
     * @version 1.0 13/05/2024
     */
    public class BJugadores implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            // Que tipo de objeto es
            opcionTabla = 1;

            // Cambiar el color al seleccionarlo
            Color accionado = Color.decode("#1B2F39");
            vi.getbJugadores().setBackground(accionado);

            mostrarPVacio(); // Mostrar panel vacio

            vi.getbExtra().setVisible(false); // Que no se vea el botón extra

            // Cambiar el resto de botones al color estandar
            Color estandar = Color.decode("#7B8B8D");
            vi.getbJuegos().setBackground(estandar);
            vi.getbEquipos().setBackground(estandar);
            vi.getbStaffs().setBackground(estandar);
            vi.getbPatrocinadores().setBackground(estandar);
            vi.getbCompeticiones().setBackground(estandar);
            vi.getbUsuarios().setBackground(estandar);

            vi.getbAlta().setBackground(accionado);
            vi.getbBaja().setBackground(accionado);
            vi.getbModificar().setBackground(accionado);
            vi.getbConsultar().setBackground(accionado);
            vi.getbConsultarT().setBackground(accionado);

            // Listeners de los botones del panel izquieros
            vi.addBAltaAL(new BAlta());
            vi.addBBjaAL(new BBaja());
            vi.addBModificarAL(new BModificar());
            vi.addBConsultarAL(new BConsultar());
            vi.addBConsultarTAL(new BConsultarT());
        }
    }

    /** Action listener del botón juego asignado con el valor 2 que dentro tiene los
     * action listeners de las acciones que se pueden cometer
     *
     * @author Rodrigo
     * @version 1.0 13/05/2024
     */
    public class BJuegos implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            // Que tipo de objeto es
            opcionTabla = 2;

            // Cambiar el color al seleccionarlo
            Color accionado = Color.decode("#1B2F39");
            vi.getbJuegos().setBackground(accionado);

            mostrarPVacio(); // Mostrar panel vacio

            vi.getbExtra().setVisible(false); // Que no se vea el botón extra

            // Cambiar el resto de botones al color estandar
            Color estandar = Color.decode("#7B8B8D");
            vi.getbJugadores().setBackground(estandar);
            vi.getbEquipos().setBackground(estandar);
            vi.getbStaffs().setBackground(estandar);
            vi.getbPatrocinadores().setBackground(estandar);
            vi.getbCompeticiones().setBackground(estandar);
            vi.getbUsuarios().setBackground(estandar);

            vi.getbAlta().setBackground(accionado);
            vi.getbBaja().setBackground(accionado);
            vi.getbModificar().setBackground(accionado);
            vi.getbConsultar().setBackground(accionado);
            vi.getbConsultarT().setBackground(accionado);

            // Listeners de los botones del panel izquieros
            vi.addBAltaAL(new BAlta());
            vi.addBBjaAL(new BBaja());
            vi.addBModificarAL(new BModificar());
            vi.addBConsultarAL(new BConsultar());
            vi.addBConsultarTAL(new BConsultarT());
        }
    }

    /** Action listener del botón equipo asignado con el valor 3 que dentro tiene los
     * action listeners de las acciones que se pueden cometer
     *
     * @author Rodrigo
     * @version 1.0 13/05/2024
     */
    public class BEquipos implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            // Que tipo de objeto es
            opcionTabla = 3;

            // Cambiar el color al seleccionarlo
            Color accionado = Color.decode("#1B2F39");
            vi.getbEquipos().setBackground(accionado);

            mostrarPVacio(); // Mostrar panel vacio

            // Que se vea el botón extra
            vi.getbExtra().setVisible(true);
            vi.getbExtra().setText("Inscribir");

            // Cambiar el resto de botones al color estandar
            Color estandar = Color.decode("#7B8B8D");
            vi.getbJuegos().setBackground(estandar);
            vi.getbJugadores().setBackground(estandar);
            vi.getbStaffs().setBackground(estandar);
            vi.getbPatrocinadores().setBackground(estandar);
            vi.getbCompeticiones().setBackground(estandar);
            vi.getbUsuarios().setBackground(estandar);

            vi.getbAlta().setBackground(accionado);
            vi.getbBaja().setBackground(accionado);
            vi.getbModificar().setBackground(accionado);
            vi.getbConsultar().setBackground(accionado);
            vi.getbConsultarT().setBackground(accionado);
            vi.getbExtra().setBackground(accionado);

            // Listeners de los botones del panel izquieros
            vi.addBAltaAL(new BAlta());
            vi.addBBjaAL(new BBaja());
            vi.addBModificarAL(new BModificar());
            vi.addBConsultarAL(new BConsultar());
            vi.addBConsultarTAL(new BConsultarT());
            vi.addBExtraAL(new BExtra());
        }
    }

    /** Action listener del botón staff asignado con el valor 4 que dentro tiene los
     * action listeners de las acciones que se pueden cometer
     *
     * @author Rodrigo
     * @version 1.0 13/05/2024
     */
    public class BStaffs implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            // Que tipo de objeto es
            opcionTabla = 4;

            // Cambiar el color al seleccionarlo
            Color accionado = Color.decode("#1B2F39");
            vi.getbStaffs().setBackground(accionado);

            mostrarPVacio(); // Mostrar panel vacio

            vi.getbExtra().setVisible(false); // Que no se vea el botón extra

            // Cambiar el resto de botones al color estandar
            Color estandar = Color.decode("#7B8B8D");
            vi.getbJuegos().setBackground(estandar);
            vi.getbEquipos().setBackground(estandar);
            vi.getbJugadores().setBackground(estandar);
            vi.getbPatrocinadores().setBackground(estandar);
            vi.getbCompeticiones().setBackground(estandar);
            vi.getbUsuarios().setBackground(estandar);

            vi.getbAlta().setBackground(accionado);
            vi.getbBaja().setBackground(accionado);
            vi.getbModificar().setBackground(accionado);
            vi.getbConsultar().setBackground(accionado);
            vi.getbConsultarT().setBackground(accionado);

            // Listeners de los botones del panel izquieros
            vi.addBAltaAL(new BAlta());
            vi.addBBjaAL(new BBaja());
            vi.addBModificarAL(new BModificar());
            vi.addBConsultarAL(new BConsultar());
            vi.addBConsultarTAL(new BConsultarT());
        }
    }

    /** Action listener del botón patrocinador asignado con el valor 5 que dentro tiene los
     * action listeners de las acciones que se pueden cometer
     *
     * @author Rodrigo
     * @version 1.0 13/05/2024
     */
    public class BPatrocinadores implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            // Que tipo de objeto es
            opcionTabla = 5;

            // Cambiar el color al seleccionarlo
            Color accionado = Color.decode("#1B2F39");
            vi.getbPatrocinadores().setBackground(accionado);

            mostrarPVacio(); // Mostrar panel vacio

            vi.getbExtra().setVisible(false); // Que no se vea el botón extra

            // Cambiar el resto de botones al color estandar
            Color estandar = Color.decode("#7B8B8D");
            vi.getbJuegos().setBackground(estandar);
            vi.getbEquipos().setBackground(estandar);
            vi.getbStaffs().setBackground(estandar);
            vi.getbJugadores().setBackground(estandar);
            vi.getbCompeticiones().setBackground(estandar);
            vi.getbUsuarios().setBackground(estandar);

            vi.getbAlta().setBackground(accionado);
            vi.getbBaja().setBackground(accionado);
            vi.getbModificar().setBackground(accionado);
            vi.getbConsultar().setBackground(accionado);
            vi.getbConsultarT().setBackground(accionado);

            // Listeners de los botones del panel izquieros
            vi.addBAltaAL(new BAlta());
            vi.addBBjaAL(new BBaja());
            vi.addBModificarAL(new BModificar());
            vi.addBConsultarAL(new BConsultar());
            vi.addBConsultarTAL(new BConsultarT());
        }
    }

    /** Action listener del botón competicion asignado con el valor 6 que dentro tiene los
     * action listeners de las acciones que se pueden cometer
     *
     * @author Rodrigo
     * @version 1.0 13/05/2024
     */
    public class BCompeticiones implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            // Que tipo de objeto es
            opcionTabla = 6;

            // Cambiar el color al seleccionarlo
            Color accionado = Color.decode("#1B2F39");
            vi.getbCompeticiones().setBackground(accionado);

            mostrarPVacio(); // Mostrar panel vacio

            // Que se vea el botón extra
            vi.getbExtra().setVisible(true);
            vi.getbExtra().setText("Abrir/Cerrar");

            // Cambiar el resto de botones al color estandar
            Color estandar = Color.decode("#7B8B8D");
            vi.getbJuegos().setBackground(estandar);
            vi.getbEquipos().setBackground(estandar);
            vi.getbStaffs().setBackground(estandar);
            vi.getbPatrocinadores().setBackground(estandar);
            vi.getbJugadores().setBackground(estandar);
            vi.getbUsuarios().setBackground(estandar);

            vi.getbAlta().setBackground(accionado);
            vi.getbBaja().setBackground(accionado);
            vi.getbModificar().setBackground(accionado);
            vi.getbConsultar().setBackground(accionado);
            vi.getbConsultarT().setBackground(accionado);
            vi.getbExtra().setBackground(accionado);

            // Listeners de los botones del panel izquieros
            vi.addBAltaAL(new BAlta());
            vi.addBBjaAL(new BBaja());
            vi.addBModificarAL(new BModificar());
            vi.addBConsultarAL(new BConsultar());
            vi.addBConsultarTAL(new BConsultarT());
            vi.addBExtraAL(new BExtra());
        }
    }

    /** Action listener del botón usuario asignado con el valor 7 que dentro tiene los
     * action listeners de las acciones que se pueden cometer
     *
     * @author Rodrigo
     * @version 1.0 13/05/2024
     */
    public class BUsuarios implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            // Que tipo de objeto es
            opcionTabla = 7;

            // Cambiar el color al seleccionarlo
            Color accionado = Color.decode("#1B2F39");
            vi.getbUsuarios().setBackground(accionado);

            mostrarPVacio(); // Mostrar panel vacio

            vi.getbExtra().setVisible(false); // Que no se vea el botón extra

            // Cambiar el resto de botones al color estandar
            Color estandar = Color.decode("#7B8B8D");
            vi.getbJuegos().setBackground(estandar);
            vi.getbEquipos().setBackground(estandar);
            vi.getbStaffs().setBackground(estandar);
            vi.getbPatrocinadores().setBackground(estandar);
            vi.getbJugadores().setBackground(estandar);
            vi.getbCompeticiones().setBackground(estandar);

            vi.getbAlta().setBackground(accionado);
            vi.getbBaja().setBackground(accionado);
            vi.getbModificar().setBackground(accionado);
            vi.getbConsultar().setBackground(accionado);
            vi.getbConsultarT().setBackground(accionado);

            // Listeners de los botones del panel izquieros
            vi.addBAltaAL(new BAlta());
            vi.addBBjaAL(new BBaja());
            vi.addBModificarAL(new BModificar());
            vi.addBConsultarAL(new BConsultar());
            vi.addBConsultarTAL(new BConsultarT());
        }
    }

    /** Action listener del botón alta asignado con el valor 1 que dependiendo el valor recibido de los
     * botones de las tablas se visibilizará el panel correspondiente llevando las acciones adecuadas
     *
     * @author Oskar
     * @version 2.0 15/05/2024
     */
    public class BAlta implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            // Cambiar el color al seleccionarlo
            Color accionado = Color.decode("#7B8B8D");
            vi.getbAlta().setBackground(accionado);

            // Cambiar el resto de botones al color estandar
            Color estandar = Color.decode("#1B2F39");
            vi.getbBaja().setBackground(estandar);
            vi.getbModificar().setBackground(estandar);
            vi.getbConsultar().setBackground(estandar);
            vi.getbConsultarT().setBackground(estandar);
            vi.getbExtra().setBackground(estandar);

            switch (opcionTabla)
            {
                case 1: // Jugadores
                    opcionAccion = 1; // Que tipo de acción es
                    try {
                        cpjd.BAmcJd(opcionAccion, opcionTabla);
                    } catch (Exception ex) {
                        throw new RuntimeException(ex);
                    }
                    break;
                case 2: // Juegos
                    opcionAccion = 1; // Que tipo de acción es
                    cpje.BAmcJe(opcionAccion, opcionTabla);
                    break;
                case 3: // Equipos
                    opcionAccion = 1; // Que tipo de acción es
                    try {
                        cpe.BAmcE(opcionAccion, opcionTabla);
                    } catch (Exception ex) {
                        throw new RuntimeException(ex);
                    }
                    break;
                case 4: // Staffs
                    opcionAccion = 1; // Que tipo de acción es
                    try {
                        cps.BAmcS(opcionAccion, opcionTabla);
                    } catch (Exception ex) {
                        throw new RuntimeException(ex);
                    }
                    break;
                case 5: // Patrocinadores
                    opcionAccion = 1; // Que tipo de acción es
                    try {
                        cpp.BAmcP(opcionAccion, opcionTabla);
                    } catch (Exception ex) {
                        throw new RuntimeException(ex);
                    }
                    break;
                case 6: // Competiciones
                    opcionAccion = 1; // Que tipo de acción es
                    try {
                        cpc.BAmcC(opcionAccion, opcionTabla);
                    } catch (Exception ex) {
                        throw new RuntimeException(ex);
                    }
                    break;
                case 7: // Usuarios
                    opcionAccion = 1; // Que tipo de acción es
                    try {
                        cpu.BAmcU(opcionAccion, opcionTabla);
                    } catch (Exception ex) {
                        throw new RuntimeException(ex);
                    }
                    break;
            }
        }
    }

    /** Action listener del botón baja asignado con el valor 2 que dependiendo el valor recibido de los
     * botones de las tablas se visibilizará el panel correspondiente llevando las acciones adecuadas.
     *
     * @author Oskar
     * @version 2.0 15/05/2024
     */
    public class BBaja implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            // Cambiar el color al seleccionarlo
            Color accionado = Color.decode("#7B8B8D");
            vi.getbBaja().setBackground(accionado);

            // Cambiar el resto de botones al color estandar
            Color estandar = Color.decode("#1B2F39");
            vi.getbAlta().setBackground(estandar);
            vi.getbModificar().setBackground(estandar);
            vi.getbConsultar().setBackground(estandar);
            vi.getbConsultarT().setBackground(estandar);
            vi.getbExtra().setBackground(estandar);

            try {
                cpb.BBaja(opcionAccion, opcionTabla);
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    /** Action listener del botón modificar asignado con el valor 3 que dependiendo el valor recibido de los
     * botones de las tablas se visibilizará el panel correspondiente llevando las acciones adecuadas
     *
     * @author Oskar
     * @version 2.0 15/05/2024
     */
    public class BModificar implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            // Cambiar el color al seleccionarlo
            Color accionado = Color.decode("#7B8B8D");
            vi.getbModificar().setBackground(accionado);

            // Cambiar el resto de botones al color estandar
            Color estandar = Color.decode("#1B2F39");
            vi.getbBaja().setBackground(estandar);
            vi.getbAlta().setBackground(estandar);
            vi.getbConsultar().setBackground(estandar);
            vi.getbConsultarT().setBackground(estandar);
            vi.getbExtra().setBackground(estandar);

            switch (opcionTabla)
            {
                case 1: // Jugadores
                    opcionAccion = 3; // Que tipo de acción es
                    try {
                        cpjd.BAmcJd(opcionAccion, opcionTabla);
                    } catch (Exception ex) {
                        throw new RuntimeException(ex);
                    }
                    break;
                case 2: // Juegos
                    opcionAccion = 3; // Que tipo de acción es
                    cpje.BAmcJe(opcionAccion, opcionTabla);
                    break;
                case 3: // Equipos
                    opcionAccion = 3; // Que tipo de acción es
                    try {
                        cpe.BAmcE(opcionAccion, opcionTabla);
                    } catch (Exception ex) {
                        throw new RuntimeException(ex);
                    }
                    break;
                case 4: // Staffs
                    opcionAccion = 3; // Que tipo de acción es
                    try {
                        cps.BAmcS(opcionAccion, opcionTabla);
                    } catch (Exception ex) {
                        throw new RuntimeException(ex);
                    }
                    break;
                case 5: // Patrocinadores
                    opcionAccion = 3; // Que tipo de acción es
                    try {
                        cpp.BAmcP(opcionAccion, opcionTabla);
                    } catch (Exception ex) {
                        throw new RuntimeException(ex);
                    }
                    break;
                case 6: // Competiciones
                    opcionAccion = 3; // Que tipo de acción es
                    try {
                        cpc.BAmcC(opcionAccion, opcionTabla);
                    } catch (Exception ex) {
                        throw new RuntimeException(ex);
                    }
                    break;
                case 7: // Usuarios
                    opcionAccion = 3; // Que tipo de acción es
                    try {
                        cpu.BAmcU(opcionAccion, opcionTabla);
                    } catch (Exception ex) {
                        throw new RuntimeException(ex);
                    }
                    break;
            }

        }
    }

    /** Action listener del botón consulta asignado con el valor 4 que dependiendo el valor recibido de los
     * botones de las tablas se visibilizará el panel correspondiente llevando las acciones adecuadas
     *
     * @author Oskar
     * @version 2.0 15/05/2024
     */
    public class BConsultar implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            // Cambiar el color al seleccionarlo
            Color accionado = Color.decode("#7B8B8D");
            vi.getbConsultar().setBackground(accionado);

            // Cambiar el resto de botones al color estandar
            Color estandar = Color.decode("#1B2F39");
            vi.getbBaja().setBackground(estandar);
            vi.getbModificar().setBackground(estandar);
            vi.getbAlta().setBackground(estandar);
            vi.getbConsultarT().setBackground(estandar);
            vi.getbExtra().setBackground(estandar);

            switch (opcionTabla)
            {
                case 1: // Jugadores
                    opcionAccion = 4; // Que tipo de acción es
                    try {
                        cpjd.BAmcJd(opcionAccion, opcionTabla);
                    } catch (Exception ex) {
                        throw new RuntimeException(ex);
                    }
                    break;
                case 2: // Juegos
                    opcionAccion = 4; // Que tipo de acción es
                    cpje.BAmcJe(opcionAccion, opcionTabla);
                break;
                case 3: // Equipos
                    opcionAccion = 4; // Que tipo de acción es
                    try {
                        cpe.BAmcE(opcionAccion, opcionTabla);
                    } catch (Exception ex) {
                        throw new RuntimeException(ex);
                    }
                    break;
                case 4: // Staffs
                    opcionAccion = 4; // Que tipo de acción es
                    try {
                        cps.BAmcS(opcionAccion, opcionTabla);
                    } catch (Exception ex) {
                        throw new RuntimeException(ex);
                    }
                    break;
                case 5: // Patrocinadores
                    opcionAccion = 4; // Que tipo de acción es
                    try {
                        cpp.BAmcP(opcionAccion, opcionTabla);
                    } catch (Exception ex) {
                        throw new RuntimeException(ex);
                    }
                    break;
                case 6: // Competiciones
                    opcionAccion = 4; // Que tipo de acción es
                    try {
                        cpc.BAmcC(opcionAccion, opcionTabla);
                    } catch (Exception ex) {
                        throw new RuntimeException(ex);
                    }
                    break;
                case 7: // Usuarios
                    opcionAccion = 4; // Que tipo de acción es
                    try {
                        cpu.BAmcU(opcionAccion, opcionTabla);
                    } catch (Exception ex) {
                        throw new RuntimeException(ex);
                    }
                    break;
            }
        }
    }

    /** Action listener del botón consultar Todos asignado con el valor 5 que dependiendo el valor recibido de los
     * botones de las tablas se visibilizará el panel correspondiente llevando las acciones adecuadas
     *
     * @author Oskar
     * @version 2.0 19/05/2024
     */
    public class BConsultarT implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            // Cambiar el color al seleccionarlo
            Color accionado = Color.decode("#7B8B8D");
            vi.getbConsultarT().setBackground(accionado);

            // Cambiar el resto de botones al color estandar
            Color estandar = Color.decode("#1B2F39");
            vi.getbBaja().setBackground(estandar);
            vi.getbModificar().setBackground(estandar);
            vi.getbAlta().setBackground(estandar);
            vi.getbConsultar().setBackground(estandar);
            vi.getbExtra().setBackground(estandar);

            try {
                cpct.BConsultarT(opcionAccion, opcionTabla);
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    /** Action listener del botón extra asignado con el valor 6 que dependiendo el valor recibido de los
     * botones de las tablas se visibilizará el panel correspondiente llevando las acciones adecuadas
     *
     * @author Oskar
     * @version 2.0 19/05/2024
     */
    public class BExtra implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Cambiar el color al seleccionarlo
            Color accionado = Color.decode("#7B8B8D");
            vi.getbExtra().setBackground(accionado);

            // Cambiar el resto de botones al color estandar
            Color estandar = Color.decode("#1B2F39");
            vi.getbBaja().setBackground(estandar);
            vi.getbModificar().setBackground(estandar);
            vi.getbAlta().setBackground(estandar);
            vi.getbConsultar().setBackground(estandar);
            vi.getbConsultarT().setBackground(estandar);

            switch (opcionTabla)
            {
                case 3:
                    try {
                        cpi.administrar();
                    } catch (Exception ex) {
                        throw new RuntimeException(ex);
                    }
                    break;
                case 6:
                    try {
                        cpa.administrar();
                    } catch (Exception ex) {
                        throw new RuntimeException(ex);
                    }
                    break;
            }
        }
    }

    /**Metodos para hacer consultas a la tabla con todos los objetos paar llenar las combo box
     *
     * @return retorna la lista con todos los objetos de la tabla ala que se le ha hecho la consulta
     * @author Oskar
     * @version 2.0 16/05/2024
     */
    public List buscarJugadores() throws Exception {
        return cv.buscarJugadores();
    }
    public List buscarJuegos() throws Exception {
        return cv.buscarJuegos();
    }
    public List buscarEquipos() throws Exception {
        return cv.buscarEquipos();
    }
    public List buscarStaffs() throws Exception {
        return cv.buscarStaffs();
    }
    public List buscarPatrocinadores() throws Exception {
        return cv.buscarPatrocinadores();
    }
    public List buscarCompeticiones() throws Exception {
        return cv.buscarCompeticiones();
    }
    public List buscarUsuarios() throws Exception {
        return cv.buscarUsuarios();
    }

    public List buscarCompeticionesAbiertas() throws Exception {
        return cv.buscarCompeticionesAbiertas();
    }
    public List buscarEquiposInscribir(int cod) throws Exception {
        return cv.buscarEquiposInscribir(cod);
    }
    public List buscarEquiposRescindir(int cod) throws Exception {
        return cv.buscarEquiposRescindir(cod);
    }

    /** Metodo en el cual pasa los datos necesario para borrar elementos de la tabla
     *
     * @param opcion es un int para saber a que tabla pertenece el elemento a eliminar
     * @param cod es el codigo del objeto que se va a eliminar
     * @author Oskar
     * @version 2.0 16/05/2024
     */
    public void borrarDeTabla (int opcion, int cod) throws Exception {
        cv.borrarDeTabla(opcion, cod);
    }


    public void insertarJugador (Jugador jd) throws Exception {
        cv.insertarJugador(jd);
    }
    public void insertarJuego (Juego je) throws Exception {
        cv.insertarJuego(je);
    }
    public void insertarEquipo (Equipo e) throws Exception {
        cv.insertarEquipo(e);
    }
    public void insertarStaff (Staff s) throws Exception {
        cv.insertarStaff(s);
    }
    public void insertarPatrocinador (Patrocinador p) throws Exception {
        cv.insertarPatrocinador(p);
    }
    public void insertarCompeticion (Competicion c) throws Exception {
        cv.insertarCompeticion(c);
    }
    public void insertarUsuario (Usuario u) throws Exception {
        cv.insertarUsuario(u);
    }


    public void modificarJugador (Jugador jd) throws Exception {
        cv.modificarJugador(jd);
    }
    public void modificarJuego (Juego je) throws Exception {
        cv.modificarJuego(je);
    }
    public void modificarEquipo (Equipo e) throws Exception {
        cv.modificarEquipo(e);
    }
    public void modificarStaff (Staff s) throws Exception {
        cv.modificarStaff(s);
    }
    public void modificarPatrocinador (Patrocinador p) throws Exception {
        cv.modificarPatrocinador(p);
    }
    public void modificarCompeticion (Competicion c) throws Exception {
        cv.modificarCompeticion(c);
    }
    public void modificarUsuario (Usuario u) throws Exception {
        cv.modificarUsuario(u);
    }

    public void modificarCompeticionEstado(int cod, int estado) throws Exception {
        cv.modificarCompeticionEstado (cod, estado);
    }

    public void inscribirEquipo(int codCompeti, int codEquipo) throws Exception {
        cv.inscribirEquipo(codCompeti, codEquipo);
    }
    public void rescindirEquipo(int codCompeti, int codEquipo) throws Exception {
        cv.rescindirEquipo(codCompeti, codEquipo);
    }

}