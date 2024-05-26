package Vista;


import javax.swing.*;
import java.awt.event.ActionListener;

public class VentanaInscripcion extends JFrame{
    private JPanel pPrincipal;
    private JPanel pCabecera;
    private JPanel pAcciones;
    private JPanel pBotones;
    private JPanel pDatos;
    private JButton bAlta;
    private JButton bBaja;
    private JButton bModificar;
    private JButton bConsultarI;
    private JPanel pvisualizar;
    private JButton bJugadores;
    private JButton bEquipos;
    private JButton bStaffs;
    private JButton bJuegos;
    private JButton bCompeticiones;
    private JButton bPatrocinadores;
    private JButton bConsultarT;
    private JPanel pAltaModiConsulJd;
    private JPanel pBaja;
    private JLabel lTituloB;
    private JComboBox cbOpciones;
    private JButton jbBorrar;
    private JComboBox cbElegirJd;
    private JComboBox cbEquipoJd;
    private JTextField tfNombreJd;
    private JTextField tfApellidoJd;
    private JTextField tfRolJd;
    private JTextField tfSalarioJd;
    private JTextField tfNacionalidadJd;
    private JTextField tfFechaJd;
    private JTextField tfNicknameJd;
    private JTextField tfEquipoJd;
    private JLabel lTituloAmcJd;
    private JLabel lEquipoJd;
    private JButton BJd;
    private JPanel pAmcJe;
    private JComboBox cbElegirJe;
    private JTextField tfNombreJe;
    private JTextField tfDesarroladoraJe;
    private JTextField tfFechaJe;
    private JLabel lTituloJe;
    private JButton jbJe;
    private JPanel pInicio;
    private JPanel pAmcP;
    private JComboBox cbElegirP;
    private JComboBox cbEquipoP;
    private JTextField tfNombreP;
    private JTextField tfEquipoP;
    private JLabel lTituloP;
    private JLabel lEquipoP;
    private JButton jbP;
    private JPanel pAmcS;
    private JComboBox cbElegirS;
    private JComboBox cbEquipoS;
    private JTextField tfNombreS;
    private JTextField tfApellidoS;
    private JTextField tfRolS;
    private JTextField tfEquipoS;
    private JRadioButton rbEntenador;
    private JRadioButton rbAsis;
    private JLabel lTituloS;
    private JLabel lRolS;
    private JLabel lEquipoS;
    private JButton jbS;
    private JPanel pAmcC;
    private JComboBox cbElegirC;
    private JComboBox cbJuegosC;
    private JTextField tfNombreC;
    private JTextField tfInicoC;
    private JTextField tfFinC;
    private JTextField tfEstadoC;
    private JTextField tfJuegoC;
    private JLabel lTituloC;
    private JLabel lEstadoC;
    private JLabel lJuegoC;
    private JButton jbC;
    private JButton bUsuarios;
    private JComboBox cbElegirU;
    private JTextField tfNicknameU;
    private JTextField tfContraU;
    private JTextField tfPriviU;
    private JRadioButton rbEstandar;
    private JRadioButton rbAdmin;
    private JPanel pAmcU;
    private JLabel lTituloU;
    private JLabel lPriviU;
    private JButton jbU;
    private JPanel pConsultarTodos;
    private JComboBox cbElegirTodos;
    private JTextArea taTodos;
    private JLabel lTituloTodos;
    private JButton bExtra;
    private JPanel pAbrir;
    private JComboBox cbAbrir;
    private JRadioButton rbAbierto;
    private JRadioButton rbCerrado;
    private JButton jbAbrir;
    private JComboBox cbElegirE;
    private JTextField tfNombreE;
    private JTextField tfFechaE;
    private JPanel pAmcE;
    private JLabel lTituloE;
    private JButton jbE;
    private JComboBox cbCompeticionesI;
    private JComboBox cbEquiposI;
    private JButton jbInscribir;
    private JRadioButton rbInscribir;
    private JRadioButton rbRescindir;
    private JPanel pInscribir;
    private JTextField tfSalarioS;
    private JButton bLogOut;

    public VentanaInscripcion(){
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        initComponets();
        setContentPane(pPrincipal);
        setLocationRelativeTo(null);
        bConsultarT.setVisible(true);
    }

    /**
     * Los listener de la ventana de Admin Inscripción
     *
     * @author Rodrigo
     * @version 1.0 12/05/2024
     */
    public void addBLogOutAL(ActionListener listener){
        bLogOut.addActionListener(listener);
    }



    // Botones de los objetos
    public void addBAltaAL(ActionListener listener){
        bAlta.addActionListener(listener);
    }
    public void addBBjaAL(ActionListener listener){
        bBaja.addActionListener(listener);
    }
    public void addBModificarAL(ActionListener listener){
        bModificar.addActionListener(listener);
    }
    public void addBConsultarAL(ActionListener listener){
        bConsultarI.addActionListener(listener);
    }
    public void addBConsultarTAL(ActionListener listener){
        bConsultarT.addActionListener(listener);
    }
    public void addBExtraAL(ActionListener listener){
        bExtra.addActionListener(listener);
    }

    // Botones de las acciones
    public void addBJugadoresAL(ActionListener listener){
        bJugadores.addActionListener(listener);
    }
    public void addBEquiposAL(ActionListener listener){
        bEquipos.addActionListener(listener);
    }
    public void addBStaffsAL(ActionListener listener){
        bStaffs.addActionListener(listener);
    }
    public void addBJuegosAL(ActionListener listener){
        bJuegos.addActionListener(listener);
    }
    public void addBCompeticionesAL(ActionListener listener){
        bCompeticiones.addActionListener(listener);
    }
    public void addBPatrocinadoresAL(ActionListener listener){
        bPatrocinadores.addActionListener(listener);
    }
    public void addBUsuariosAL(ActionListener listener){
        bUsuarios.addActionListener(listener);
    }

    // Botones de los paneles
    public void addBBorrarAL(ActionListener listener){
        jbBorrar.addActionListener(listener);
    }
    public void addBJdAL(ActionListener listener){BJd.addActionListener(listener);}
    public void addJbJe(ActionListener listener)
    {
        jbJe.addActionListener(listener);
    }
    public void addJbP(ActionListener listener)
    {
        jbP.addActionListener(listener);
    }
    public void addJbS(ActionListener listener)
    {
        jbS.addActionListener(listener);
    }
    public void addJbC(ActionListener listener)
    {
        jbC.addActionListener(listener);
    }
    public void addJbU(ActionListener listener)
    {
        jbU.addActionListener(listener);
    }
    public void addJbAbrir(ActionListener listener)
    {
        jbAbrir.addActionListener(listener);
    }
    public void addJbE(ActionListener listener)
    {
        jbE.addActionListener(listener);
    }
    public void addJbInscribir(ActionListener listener)
    {
        jbInscribir.addActionListener(listener);
    }

    // Listas de los paneles
    public void addcbElegirJd(ActionListener listener)
    {
        cbElegirJd.addActionListener(listener);
    }
    public void addcbElegitJe(ActionListener listener)
    {
        cbElegirJe.addActionListener(listener);
    }
    public void addcbElegirP(ActionListener listener)
    {
        cbElegirP.addActionListener(listener);
    }
    public void addcbElegirS(ActionListener listener)
    {
        cbElegirS.addActionListener(listener);
    }
    public void addcbElegirC(ActionListener listener)
    {
        cbElegirC.addActionListener(listener);
    }
    public void addcbElegirU(ActionListener listener)
    {
        cbElegirU.addActionListener(listener);
    }
    public void addcbAbrir(ActionListener listener)
    {
        cbAbrir.addActionListener(listener);
    }
    public void addcbElegirE(ActionListener listener)
    {
        cbElegirE.addActionListener(listener);
    }

    // Radio buttons
    public void addrbInscribir(ActionListener listener)
    {
        rbInscribir.addActionListener(listener);
    }
    public void addrbRescindir(ActionListener listener)
    {
        rbRescindir.addActionListener(listener);
    }

    /**Quitar los bordes a los botones de las tablas y las acciones
     *
     * @author Rodrigo
     * @version 2.0 12/05/2024
     */
    public void initComponets() {
        // Botones de las acciones
        quitarBordesBotones(bAlta);
        quitarBordesBotones(bBaja);
        quitarBordesBotones(bModificar);
        quitarBordesBotones(bConsultarI);
        quitarBordesBotones(bConsultarT);
        quitarBordesBotones(bExtra);

        // Botones de los objetos
        quitarBordesBotones(bJugadores);
        quitarBordesBotones(bJuegos);
        quitarBordesBotones(bStaffs);
        quitarBordesBotones(bEquipos);
        quitarBordesBotones(bCompeticiones);
        quitarBordesBotones(bPatrocinadores);
        quitarBordesBotones(bUsuarios);

        quitarBordesBotones(bLogOut);

        setVisible(true);
    }


    /** Opciones para quitar los bordes de los botones
     *
     * @author Rodrigo
     * @version 2.0 12/05/2024
     */
    public void quitarBordesBotones (JButton boton){
        boton.setBorderPainted(false);
        boton.setContentAreaFilled(true);
        boton.setFocusPainted(false);
        //boton.setOpaque(false);
    }

    public void limpiar()
    {
        // Panel Jugadores
        tfNombreJd.setText("");
        tfApellidoJd.setText("");
        tfRolJd.setText("");
        tfNicknameJd.setText("");
        tfFechaJd.setText("");
        tfSalarioJd.setText("");
        tfNacionalidadJd.setText("");
        tfEquipoJd.setText("");
        cbEquipoJd.setSelectedItem(0);
        cbElegirJd.setSelectedItem(0);

        // Panel de Juegos
        tfNombreJe.setText("");
        tfDesarroladoraJe.setText("");
        tfFechaJe.setText("");
        cbElegirJe.setSelectedIndex(0);

        // Panel de Equipos
        tfNombreE.setText("");
        tfFechaE.setText("");
        cbElegirE.setSelectedItem(0);

        // Panel de Staffs
        tfSalarioS.setText("");
        tfRolS.setText("");
        tfNombreS.setText("");
        tfApellidoS.setText("");
        cbElegirS.setSelectedItem(0);
        cbEquipoS.setSelectedItem(0);
        rbAsis.setSelected(false);
        rbEntenador.setSelected(false);

        // Panel de Patrocinadores
        tfNombreP.setText("");
        tfEquipoP.setText("");
        cbEquipoP.setSelectedItem(0);
        cbElegirP.setSelectedItem(0);

        // Panel de Competiciones
        tfNombreC.setText("");
        tfInicoC.setText("");
        tfFinC.setText("");
        cbEquipoS.setSelectedItem(0);
        cbElegirS.setSelectedItem(0);

        // Panel de Usuarios
        tfNicknameU.setText("");
        tfContraU.setText("");
        cbElegirU.setSelectedItem(0);
        rbEstandar.setSelected(false);
        rbAdmin.setSelected(false);
    }


    /**Metodo para ocultar y visibilizar los paneles que se requieren en al pulsar cada botón
     * y tambien modificarlos atributos comunes. EJ: el texto de los labels
     *
     * @param opcionTabla recibe el int con el que sabemos para que tabla trabajamos
     * @param opcionAccion recibe el int con el que sabemos que acción quiere llevar a cabo el usuario
     * @author Oskar
     * @version 2.0 16/05/2024
     */
    public void crearElementos(int opcionTabla, int opcionAccion)
    {
        switch (opcionTabla)
        {
            case 1: // Jugadores == panel pAltaModiConsulJd
                switch (opcionAccion)
                {
                    case 1: // Alta
                        mostrarPanel(opcionTabla);

                        cbEquipoJd.setVisible(true);
                        lTituloAmcJd.setText("Alta de Jugadores");
                        BJd.setText("Inscribir");
                        lEquipoJd.setVisible(false);
                        tfEquipoJd.setVisible(false);
                        cbElegirJd.setVisible(false);
                        BJd.setVisible(true);

                        tfNombreJd.setEnabled(true);
                        tfApellidoJd.setEnabled(true);
                        tfRolJd.setEnabled(true);
                        tfSalarioJd.setEnabled(true);
                        tfEquipoJd.setEnabled(true);
                        tfNacionalidadJd.setEnabled(true);
                        tfNicknameJd.setEnabled(true);
                    break;
                    case 2: // Baja
                        mostraPBaja(); // Mostrar la ventana que se quiere

                        lTituloB.setText("Baja de Jugadores");
                    break;
                    case 3: // Modificar
                        mostrarPanel(opcionTabla);

                        cbElegirJd.setVisible(true);
                        cbEquipoJd.setVisible(true);
                        lTituloAmcJd.setText("Modificar Jugadores");
                        BJd.setText("Modificar");
                        lEquipoJd.setVisible(false);
                        tfEquipoJd.setVisible(false);
                        BJd.setVisible(true);

                        tfNombreJd.setEnabled(true);
                        tfApellidoJd.setEnabled(true);
                        tfRolJd.setEnabled(true);
                        tfSalarioJd.setEnabled(true);
                        tfEquipoJd.setEnabled(true);
                        tfNacionalidadJd.setEnabled(true);
                        tfNicknameJd.setEnabled(true);
                    break;
                    case 4: // Consultar
                        mostrarPanel(opcionTabla);

                        lTituloAmcJd.setText("Consultar un Jugador");
                        BJd.setVisible(false);
                        cbEquipoJd.setVisible(false);
                        lEquipoJd.setVisible(true);
                        tfEquipoJd.setVisible(true);
                        cbElegirJd.setVisible(true);

                        tfNombreJd.setEnabled(false);
                        tfApellidoJd.setEnabled(false);
                        tfRolJd.setEnabled(false);
                        tfSalarioJd.setEnabled(false);
                        tfEquipoJd.setEnabled(false);
                        tfNacionalidadJd.setEnabled(false);
                        tfNicknameJd.setEnabled(false);
                    break;
                    case 5: // Consultar Todos
                        mostrarPConsultarT();

                        lTituloTodos.setText("Todos los Jugadores");
                    break;
                }
            break;
            case 2: // Juegos == panel pAmcJe
                switch (opcionAccion)
                {
                    case 1: // Alta
                        mostrarPanel(opcionTabla);

                        lTituloJe.setText("Alta de Juegos");
                        jbJe.setText("Añadir");
                        cbElegirJe.setVisible(false);
                        jbJe.setVisible(true);

                        tfNombreJe.setEnabled(true);
                        tfDesarroladoraJe.setEnabled(true);
                        tfFechaJe.setEnabled(true);
                    break;
                    case 2: // Baja
                        mostraPBaja(); // Mostrar la ventana que se quiere

                        lTituloB.setText("Baja de Juegos");
                    break;
                    case 3: // Modificar
                        mostrarPanel(opcionTabla);

                        cbElegirJe.setVisible(true);
                        lTituloJe.setText("Modificar Juegos");
                        jbJe.setText("Modificar");
                        jbJe.setVisible(true);

                        tfNombreJe.setEnabled(true);
                        tfDesarroladoraJe.setEnabled(true);
                        tfFechaJe.setEnabled(true);
                    break;
                    case 4: // Consultar
                        mostrarPanel(opcionTabla);

                        cbElegirJe.setVisible(true);
                        lTituloJe.setText("Consultar Juegos");
                        jbJe.setVisible(false);

                        tfNombreJe.setEnabled(false);
                        tfDesarroladoraJe.setEnabled(false);
                        tfFechaJe.setEnabled(false);
                    break;
                    case 5: // Consultar Todos
                        mostrarPConsultarT();

                        lTituloTodos.setText("Todos los Juegos");
                    break;
                }
            break;
            case 3: // Equipos == panel pAmcE
                switch (opcionAccion)
                {
                    case 1: // Alta
                        mostrarPanel(opcionTabla);

                        cbElegirE.setVisible(false);
                        jbE.setVisible(true);
                        lTituloE.setText("Alta de Equipos");
                        jbE.setText("Inscribir");

                        tfNombreE.setEnabled(true);
                        tfFechaE.setEnabled(true);
                    break;
                    case 2: // Baja
                        mostraPBaja(); // Mostrar la ventana que se quiere

                        lTituloB.setText("Baja de Equipos");
                    break;
                    case 3: // Modificar
                        mostrarPanel(opcionTabla);

                        cbElegirE.setVisible(true);
                        jbE.setVisible(true);
                        lTituloE.setText("Modificar Equipos");
                        jbE.setText("Modificar");

                        tfNombreE.setEnabled(true);
                        tfFechaE.setEnabled(true);
                    break;
                    case 4: // Consultar
                        mostrarPanel(opcionTabla);

                        cbElegirE.setVisible(true);
                        jbE.setVisible(false);
                        lTituloE.setText("Comsultar Equipos");

                        tfNombreE.setEnabled(false);
                        tfFechaE.setEnabled(false);
                    break;
                    case 5: // Consultar Todos
                        mostrarPConsultarT();

                        lTituloTodos.setText("Todos los Equipos");
                    break;
                }
            break;
            case 4: // Staffs == panel pAmcS
                switch (opcionAccion)
                {
                    case 1: // Alta
                        mostrarPanel(opcionTabla);

                        lTituloS.setText("Alta de Staff");
                        jbS.setText("Inscribir");
                        lRolS.setVisible(false);
                        lEquipoS.setVisible(false);
                        tfRolS.setVisible(false);
                        tfEquipoS.setVisible(false);
                        cbElegirS.setVisible(false);
                        cbEquipoS.setVisible(true);
                        rbEntenador.setVisible(true);
                        rbAsis.setVisible(true);
                        jbS.setVisible(true);

                        tfSalarioS.setEnabled(true);
                        tfNombreS.setEnabled(true);
                        tfApellidoS.setEnabled(true);
                    break;
                    case 2: // Baja
                        mostraPBaja(); // Mostrar la ventana que se quiere

                        lTituloB.setText("Baja de Staffs");
                    break;
                    case 3: // Modificar
                        mostrarPanel(opcionTabla);

                        lTituloS.setText("Modificar Staff");
                        jbS.setText("Modificar");
                        lRolS.setVisible(false);
                        lEquipoS.setVisible(false);
                        tfRolS.setVisible(false);
                        tfEquipoS.setVisible(false);
                        cbElegirS.setVisible(true);
                        cbEquipoS.setVisible(true);
                        rbEntenador.setVisible(true);
                        rbAsis.setVisible(true);
                        jbS.setVisible(true);

                        tfSalarioS.setEnabled(true);
                        tfNombreS.setEnabled(true);
                        tfApellidoS.setEnabled(true);
                    break;
                    case 4: // Consultar
                        mostrarPanel(opcionTabla);

                        lTituloS.setText("Consultar Staff");
                        jbS.setVisible(false);
                        lRolS.setVisible(true);
                        lEquipoS.setVisible(true);
                        tfRolS.setVisible(true);
                        tfEquipoS.setVisible(true);
                        cbElegirS.setVisible(true);
                        cbEquipoS.setVisible(false);
                        rbEntenador.setVisible(false);
                        rbAsis.setVisible(false);

                        tfSalarioS.setEnabled(false);
                        tfNombreS.setEnabled(false);
                        tfApellidoS.setEnabled(false);
                        tfRolS.setEnabled(false);
                        tfEquipoS.setEnabled(false);
                    break;
                    case 5: // Consultar Todos
                        mostrarPConsultarT();

                        lTituloTodos.setText("Todos los Miembros de Staff");
                    break;
                }
            break;
            case 5: // Patrocinadores == panel pAmcP
                switch (opcionAccion)
                {
                    case 1: // Alta
                        mostrarPanel(opcionTabla);

                        lTituloP.setText("Alta de Patrocinador");
                        cbElegirP.setVisible(false);
                        lEquipoP.setVisible(false);
                        tfEquipoP.setVisible(false);
                        jbP.setText("Patrocinar");
                        cbEquipoP.setVisible(true);
                        jbP.setVisible(true);

                        tfNombreP.setEnabled(true);
                    break;
                    case 2: // Baja
                        mostraPBaja(); // Mostrar la ventana que se quiere

                        lTituloB.setText("Baja de Patrocinadores");
                    break;
                    case 3: // Modificar
                        mostrarPanel(opcionTabla);

                        lTituloP.setText("Modificar Patrocinador");
                        cbElegirP.setVisible(true);
                        lEquipoP.setVisible(false);
                        tfEquipoP.setVisible(false);
                        jbP.setText("Modificar");
                        cbEquipoP.setVisible(true);
                        jbP.setVisible(true);

                        tfNombreP.setEnabled(true);
                    break;
                    case 4: // Consultar
                        mostrarPanel(opcionTabla);

                        cbElegirP.setVisible(true);
                        cbEquipoP.setVisible(false);
                        lEquipoP.setVisible(true);
                        tfEquipoP.setVisible(true);
                        lTituloP.setText("Consultar Patrocindaor");
                        jbP.setVisible(false);

                        tfEquipoP.setEnabled(false);
                        tfNombreP.setEnabled(false);
                    break;
                    case 5: // Consultar Todos
                        mostrarPConsultarT();

                        lTituloTodos.setText("Todos los Patrocinadores");
                    break;
                }
            break;
            case 6: // Competiciones == panel pAmcC
                switch (opcionAccion)
                {
                    case 1: // Alta
                        mostrarPanel(opcionTabla);

                        cbElegirC.setVisible(false);
                        lJuegoC.setVisible(false);
                        lEstadoC.setVisible(false);
                        tfJuegoC.setVisible(false);
                        tfEstadoC.setVisible(false);
                        cbJuegosC.setVisible(true);
                        lTituloC.setText("Alta de Competicion");
                        jbC.setText("Crear");
                        jbC.setVisible(true);

                        tfNombreC.setEnabled(true);
                        tfInicoC.setEnabled(true);
                        tfFinC.setEnabled(true);
                    break;
                    case 2: // Baja
                        mostraPBaja(); // Mostrar la ventana que se quiere

                        lTituloB.setText("Baja de Competiciones");
                    break;
                    case 3: // Modificar
                        mostrarPanel(opcionTabla);

                        cbElegirC.setVisible(true);
                        lJuegoC.setVisible(false);
                        lEstadoC.setVisible(false);
                        tfJuegoC.setVisible(false);
                        tfEstadoC.setVisible(false);
                        cbJuegosC.setVisible(true);
                        lTituloC.setText("Modificar Competicion");
                        jbC.setText("Modificar");
                        jbC.setVisible(true);

                        tfNombreC.setEnabled(true);
                        tfInicoC.setEnabled(true);
                        tfFinC.setEnabled(true);
                    break;
                    case 4: // Consultar
                        mostrarPanel(opcionTabla);

                        cbElegirC.setVisible(true);
                        lJuegoC.setVisible(true);
                        lEstadoC.setVisible(true);
                        tfJuegoC.setVisible(true);
                        tfEstadoC.setVisible(true);
                        cbJuegosC.setVisible(false);
                        lTituloC.setText("Consultar Competicion");
                        jbC.setVisible(false);

                        tfNombreC.setEnabled(false);
                        tfInicoC.setEnabled(false);
                        tfFinC.setEnabled(false);
                        tfEstadoC.setEnabled(false);
                        tfJuegoC.setEnabled(false);
                    break;
                    case 5: // Consultar Todos
                        mostrarPConsultarT();

                        lTituloTodos.setText("Todas las Competiciones");
                    break;
                }
            break;
            case 7: // Usuarios == pane pAmc
                switch (opcionAccion)
                {
                    case 1: // Alta
                        mostrarPanel(opcionTabla);

                        cbElegirU.setVisible(false);
                        rbAdmin.setVisible(true);
                        rbEstandar.setVisible(true);
                        lPriviU.setVisible(false);
                        tfPriviU.setVisible(false);
                        lTituloU.setText("Alta de Usuario");
                        jbU.setText("Crear");
                        jbU.setVisible(true);

                        tfNicknameU.setEnabled(true);
                        tfContraU.setEnabled(true);
                    break;
                    case 2: // Baja
                        mostraPBaja(); // Mostrar la ventana que se quiere

                        lTituloB.setText("Baja de Usuarios");
                    break;
                    case 3: // Modificar
                        mostrarPanel(opcionTabla);

                        cbElegirU.setVisible(true);
                        rbAdmin.setVisible(true);
                        rbEstandar.setVisible(true);
                        lPriviU.setVisible(false);
                        tfPriviU.setVisible(false);
                        lTituloU.setText("Modificar Usuario");
                        jbU.setText("Modificar");
                        jbU.setVisible(true);

                        tfNicknameU.setEnabled(true);
                        tfContraU.setEnabled(true);
                    break;
                    case 4: // Consultar
                        mostrarPanel(opcionTabla);

                        cbElegirU.setVisible(true);
                        rbAdmin.setVisible(false);
                        rbEstandar.setVisible(false);
                        lPriviU.setVisible(true);
                        tfPriviU.setVisible(true);
                        lTituloU.setText("Consultar Usuario");
                        jbU.setVisible(false);

                        tfNicknameU.setEnabled(false);
                        tfContraU.setEnabled(false);
                        tfPriviU.setEnabled(false);
                    break;
                    case 5: // Consultar Todos
                        mostrarPConsultarT();

                        lTituloTodos.setText("Todos los Usuarios");
                    break;
                }
            break;
        }
    }

    public void mostraPBaja()
    {
        pAltaModiConsulJd.setVisible(false);
        pBaja.setVisible(true);
        pAmcJe.setVisible(false);
        pInicio.setVisible(false);
        pAmcP.setVisible(false);
        pAmcS.setVisible(false);
        pAmcC.setVisible(false);
        pAmcU.setVisible(false);
        pConsultarTodos.setVisible(false);
        pAbrir.setVisible(false);
        pAmcE.setVisible(false);
        pInscribir.setVisible(false);
    }

    public void mostrarPConsultarT()
    {
        pAltaModiConsulJd.setVisible(false);
        pBaja.setVisible(false);
        pAmcJe.setVisible(false);
        pInicio.setVisible(false);
        pAmcP.setVisible(false);
        pAmcS.setVisible(false);
        pAmcC.setVisible(false);
        pAmcU.setVisible(false);
        pConsultarTodos.setVisible(true);
        pAbrir.setVisible(false);
        pAmcE.setVisible(false);
        pInscribir.setVisible(false);
    }

    public void mostrarPanel(int opcionTabla)
    {
        switch (opcionTabla)
        {
            case 1: // Jugadores
                pAltaModiConsulJd.setVisible(true);
                pBaja.setVisible(false);
                pAmcJe.setVisible(false);
                pInicio.setVisible(false);
                pAmcP.setVisible(false);
                pAmcS.setVisible(false);
                pAmcC.setVisible(false);
                pAmcU.setVisible(false);
                pConsultarTodos.setVisible(false);
                pAbrir.setVisible(false);
                pAmcE.setVisible(false);
                pInscribir.setVisible(false);
            break;
            case 2: // Juegos
                pAltaModiConsulJd.setVisible(false);
                pBaja.setVisible(false);
                pAmcJe.setVisible(true);
                pInicio.setVisible(false);
                pAmcP.setVisible(false);
                pAmcS.setVisible(false);
                pAmcC.setVisible(false);
                pAmcU.setVisible(false);
                pConsultarTodos.setVisible(false);
                pAbrir.setVisible(false);
                pAmcE.setVisible(false);
                pInscribir.setVisible(false);
            break;
            case 3: // Equipos
                pAltaModiConsulJd.setVisible(false);
                pBaja.setVisible(false);
                pAmcJe.setVisible(false);
                pInicio.setVisible(false);
                pAmcP.setVisible(false);
                pAmcS.setVisible(false);
                pAmcC.setVisible(false);
                pAmcU.setVisible(false);
                pConsultarTodos.setVisible(false);
                pAbrir.setVisible(false);
                pAmcE.setVisible(true);
                pInscribir.setVisible(false);
            break;
            case 4: // Staffs
                pAltaModiConsulJd.setVisible(false);
                pBaja.setVisible(false);
                pAmcJe.setVisible(false);
                pInicio.setVisible(false);
                pAmcP.setVisible(false);
                pAmcS.setVisible(true);
                pAmcC.setVisible(false);
                pAmcU.setVisible(false);
                pConsultarTodos.setVisible(false);
                pAbrir.setVisible(false);
                pAmcE.setVisible(false);
                pInscribir.setVisible(false);
            break;
            case 5: // Patrocinadores
                pAltaModiConsulJd.setVisible(false);
                pBaja.setVisible(false);
                pAmcJe.setVisible(false);
                pInicio.setVisible(false);
                pAmcP.setVisible(true);
                pAmcS.setVisible(false);
                pAmcC.setVisible(false);
                pAmcU.setVisible(false);
                pConsultarTodos.setVisible(false);
                pAbrir.setVisible(false);
                pAmcE.setVisible(false);
                pInscribir.setVisible(false);
            break;
            case 6: // Competiciones
                pAltaModiConsulJd.setVisible(false);
                pBaja.setVisible(false);
                pAmcJe.setVisible(false);
                pInicio.setVisible(false);
                pAmcP.setVisible(false);
                pAmcS.setVisible(false);
                pAmcC.setVisible(true);
                pAmcU.setVisible(false);
                pConsultarTodos.setVisible(false);
                pAbrir.setVisible(false);
                pAmcE.setVisible(false);
                pInscribir.setVisible(false);
            break;
            case 7: // Usuarios
                pAltaModiConsulJd.setVisible(false);
                pBaja.setVisible(false);
                pAmcJe.setVisible(false);
                pInicio.setVisible(false);
                pAmcP.setVisible(false);
                pAmcS.setVisible(false);
                pAmcC.setVisible(false);
                pAmcU.setVisible(true);
                pConsultarTodos.setVisible(false);
                pAbrir.setVisible(false);
                pAmcE.setVisible(false);
                pInscribir.setVisible(false);
            break;
        }
    }

    /** Metdodo para mostrar por pantalla los errores recibidos en el controlador
     *
     * @param mensaje recibe un string con el mensaje del error
     * @author Oskar
     * @version 2.0 16/05/2024
     */
    public void mostrarError(String mensaje)
    {
        JOptionPane.showMessageDialog(null, mensaje);
    }



    // 893 lineas de Getters & Setters
    public JButton getbAlta() {
        return bAlta;
    }

    public void setbAlta(JButton bAlta) {
        this.bAlta = bAlta;
    }

    public JButton getbBaja() {
        return bBaja;
    }

    public void setbBaja(JButton bBaja) {
        this.bBaja = bBaja;
    }

    public JButton getbModificar() {
        return bModificar;
    }

    public void setbModificar(JButton bModificar) {
        this.bModificar = bModificar;
    }

    public JButton getbConsultar() {
        return bConsultarI;
    }

    public void setbConsultar(JButton bConsultar) {
        this.bConsultarI = bConsultar;
    }

    public JComboBox getCbElegirTodos() {
        return cbElegirTodos;
    }

    public void setCbElegirTodos(JComboBox cbElegirTodos) {
        this.cbElegirTodos = cbElegirTodos;
    }

    public JButton getbExtra() {
        return bExtra;
    }

    public void setbExtra(JButton bExtra) {
        this.bExtra = bExtra;
    }

    public JButton getbJugadores() {
        return bJugadores;
    }

    public void setbJugadores(JButton bJugadores) {
        this.bJugadores = bJugadores;
    }

    public JButton getbEquipos() {
        return bEquipos;
    }

    public void setbEquipos(JButton bEquipos) {
        this.bEquipos = bEquipos;
    }

    public JButton getbStaffs() {
        return bStaffs;
    }

    public void setbStaffs(JButton bStaffs) {
        this.bStaffs = bStaffs;
    }

    public JButton getbJuegos() {
        return bJuegos;
    }

    public void setbJuegos(JButton bJuegos) {
        this.bJuegos = bJuegos;
    }

    public JButton getbCompeticiones() {
        return bCompeticiones;
    }

    public void setbCompeticiones(JButton bCompeticiones) {
        this.bCompeticiones = bCompeticiones;
    }

    public JButton getbPatrocinadores() {
        return bPatrocinadores;
    }

    public void setbPatrocinadores(JButton bPatrocinadores) {
        this.bPatrocinadores = bPatrocinadores;
    }

    public JButton getbAbrirCerrarCompeti() {
        return bConsultarT;
    }

    public void setbAbrirCerrarCompeti(JButton bAbrirCerrarCompeti) {
        this.bConsultarT = bAbrirCerrarCompeti;
    }

    public JComboBox getCbOpciones() {
        return cbOpciones;
    }

    public void setCbOpciones(JComboBox cbOpciones) {
        this.cbOpciones = cbOpciones;
    }

    public JButton getJbBorrar() {
        return jbBorrar;
    }

    public void setJbBorrar(JButton jbBorrar) {
        this.jbBorrar = jbBorrar;
    }

    public JPanel getpPrincipal() {
        return pPrincipal;
    }

    public void setpPrincipal(JPanel pPrincipal) {
        this.pPrincipal = pPrincipal;
    }

    public JLabel getlTituloB() {
        return lTituloB;
    }

    public void setlTituloB(JLabel lTituloB) {
        this.lTituloB = lTituloB;
    }

    public JComboBox getCbElegirJd() {
        return cbElegirJd;
    }

    public void setCbElegirJd(JComboBox cbElegirJd) {
        this.cbElegirJd = cbElegirJd;
    }

    public JComboBox getCbEquipoJd() {
        return cbEquipoJd;
    }

    public void setCbEquipoJd(JComboBox cbEquipoJd) {
        this.cbEquipoJd = cbEquipoJd;
    }

    public JTextField getTfNombreJd() {
        return tfNombreJd;
    }

    public void setTfNombreJd(JTextField tfNombreJd) {
        this.tfNombreJd = tfNombreJd;
    }

    public JTextField getTfApellidoJd() {
        return tfApellidoJd;
    }

    public void setTfApellidoJd(JTextField tfApellidoJd) {
        this.tfApellidoJd = tfApellidoJd;
    }

    public JTextField getTfRolJd() {
        return tfRolJd;
    }

    public void setTfRolJd(JTextField tfRolJd) {
        this.tfRolJd = tfRolJd;
    }

    public JTextField getTfSalarioJd() {
        return tfSalarioJd;
    }

    public void setTfSalarioJd(JTextField tfSalarioJd) {
        this.tfSalarioJd = tfSalarioJd;
    }

    public JTextField getTfNacionalidadJd() {
        return tfNacionalidadJd;
    }

    public void setTfNacionalidadJd(JTextField tfNacionalidadJd) {
        this.tfNacionalidadJd = tfNacionalidadJd;
    }

    public JTextField getTfFechaJd() {
        return tfFechaJd;
    }

    public void setTfFechaJd(JTextField tfFechaJd) {
        this.tfFechaJd = tfFechaJd;
    }

    public JTextField getTfNicknameJd() {
        return tfNicknameJd;
    }

    public void setTfNicknameJd(JTextField tfNicknameJd) {
        this.tfNicknameJd = tfNicknameJd;
    }

    public JTextField getTfEquipoJd() {
        return tfEquipoJd;
    }

    public void setTfEquipoJd(JTextField tfEquipoJd) {
        this.tfEquipoJd = tfEquipoJd;
    }

    public JLabel getlTituloAmcJd() {
        return lTituloAmcJd;
    }

    public void setlTituloAmcJd(JLabel lTituloAmcJd) {
        this.lTituloAmcJd = lTituloAmcJd;
    }

    public JLabel getlEquipoJd() {
        return lEquipoJd;
    }

    public void setlEquipoJd(JLabel lEquipoJd) {
        this.lEquipoJd = lEquipoJd;
    }

    public JButton getBJd() {
        return BJd;
    }

    public void setBJd(JButton BJd) {
        this.BJd = BJd;
    }

    public JPanel getpCabecera() {
        return pCabecera;
    }

    public void setpCabecera(JPanel pCabecera) {
        this.pCabecera = pCabecera;
    }

    public JPanel getpAcciones() {
        return pAcciones;
    }

    public void setpAcciones(JPanel pAcciones) {
        this.pAcciones = pAcciones;
    }

    public JPanel getpBotones() {
        return pBotones;
    }

    public void setpBotones(JPanel pBotones) {
        this.pBotones = pBotones;
    }

    public JPanel getpDatos() {
        return pDatos;
    }

    public void setpDatos(JPanel pDatos) {
        this.pDatos = pDatos;
    }

    public JButton getbConsultarI() {
        return bConsultarI;
    }

    public void setbConsultarI(JButton bConsultarI) {
        this.bConsultarI = bConsultarI;
    }

    public JPanel getPvisualizar() {
        return pvisualizar;
    }

    public void setPvisualizar(JPanel pvisualizar) {
        this.pvisualizar = pvisualizar;
    }

    public JButton getbConsultarT() {
        return bConsultarT;
    }

    public void setbConsultarT(JButton bConsultarT) {
        this.bConsultarT = bConsultarT;
    }

    public JPanel getpAltaModiConsulJd() {
        return pAltaModiConsulJd;
    }

    public void setpAltaModiConsulJd(JPanel pAltaModiConsulJd) {
        this.pAltaModiConsulJd = pAltaModiConsulJd;
    }

    public JPanel getpBaja() {
        return pBaja;
    }

    public void setpBaja(JPanel pBaja) {
        this.pBaja = pBaja;
    }

    public JPanel getpAmcJe() {
        return pAmcJe;
    }

    public void setpAmcJe(JPanel pAmcJe) {
        this.pAmcJe = pAmcJe;
    }

    public JComboBox getCbElegirJe() {
        return cbElegirJe;
    }

    public void setCbElegirJe(JComboBox cbElegirJe) {
        this.cbElegirJe = cbElegirJe;
    }

    public JTextField getTfNombreJe() {
        return tfNombreJe;
    }

    public void setTfNombreJe(JTextField tfNombreJe) {
        this.tfNombreJe = tfNombreJe;
    }

    public JTextField getTfDesarroladoraJe() {
        return tfDesarroladoraJe;
    }

    public void setTfDesarroladoraJe(JTextField tfDesarroladoraJe) {
        this.tfDesarroladoraJe = tfDesarroladoraJe;
    }

    public JTextField getTfFechaJe() {
        return tfFechaJe;
    }

    public void setTfFechaJe(JTextField tfFechaJe) {
        this.tfFechaJe = tfFechaJe;
    }

    public JLabel getlTituloJe() {
        return lTituloJe;
    }

    public void setlTituloJe(JLabel lTituloJe) {
        this.lTituloJe = lTituloJe;
    }

    public JButton getJbJe() {
        return jbJe;
    }

    public void setJbJe(JButton jbJe) {
        this.jbJe = jbJe;
    }

    public JPanel getpInicio() {
        return pInicio;
    }

    public void setpInicio(JPanel pInicio) {
        this.pInicio = pInicio;
    }

    public JPanel getpAmcP() {
        return pAmcP;
    }

    public void setpAmcP(JPanel pAmcP) {
        this.pAmcP = pAmcP;
    }

    public JComboBox getCbElegirP() {
        return cbElegirP;
    }

    public void setCbElegirP(JComboBox cbElegirP) {
        this.cbElegirP = cbElegirP;
    }

    public JComboBox getCbEquipoP() {
        return cbEquipoP;
    }

    public void setCbEquipoP(JComboBox cbEquipoP) {
        this.cbEquipoP = cbEquipoP;
    }

    public JTextField getTfNombreP() {
        return tfNombreP;
    }

    public void setTfNombreP(JTextField tfNombreP) {
        this.tfNombreP = tfNombreP;
    }

    public JTextField getTfEquipoP() {
        return tfEquipoP;
    }

    public void setTfEquipoP(JTextField tfEquipoP) {
        this.tfEquipoP = tfEquipoP;
    }

    public JLabel getlTituloP() {
        return lTituloP;
    }

    public void setlTituloP(JLabel lTituloP) {
        this.lTituloP = lTituloP;
    }

    public JLabel getlEquipoP() {
        return lEquipoP;
    }

    public void setlEquipoP(JLabel lEquipoP) {
        this.lEquipoP = lEquipoP;
    }

    public JButton getJbP() {
        return jbP;
    }

    public void setJbP(JButton jbP) {
        this.jbP = jbP;
    }

    public JPanel getpAmcS() {
        return pAmcS;
    }

    public void setpAmcS(JPanel pAmcS) {
        this.pAmcS = pAmcS;
    }

    public JComboBox getCbElegirS() {
        return cbElegirS;
    }

    public void setCbElegirS(JComboBox cbElegirS) {
        this.cbElegirS = cbElegirS;
    }

    public JComboBox getCbEquipoS() {
        return cbEquipoS;
    }

    public void setCbEquipoS(JComboBox cbEquipoS) {
        this.cbEquipoS = cbEquipoS;
    }

    public JTextField getTfNombreS() {
        return tfNombreS;
    }

    public void setTfNombreS(JTextField tfNombreS) {
        this.tfNombreS = tfNombreS;
    }

    public JTextField getTfApellidoS() {
        return tfApellidoS;
    }

    public void setTfApellidoS(JTextField tfApellidoS) {
        this.tfApellidoS = tfApellidoS;
    }

    public JTextField getTfRolS() {
        return tfRolS;
    }

    public void setTfRolS(JTextField tfRolS) {
        this.tfRolS = tfRolS;
    }

    public JTextField getTfEquipoS() {
        return tfEquipoS;
    }

    public void setTfEquipoS(JTextField tfEquipoS) {
        this.tfEquipoS = tfEquipoS;
    }

    public JRadioButton getRbEntenador() {
        return rbEntenador;
    }

    public void setRbEntenador(JRadioButton rbEntenador) {
        this.rbEntenador = rbEntenador;
    }

    public JRadioButton getRbAsis() {
        return rbAsis;
    }

    public void setRbAsis(JRadioButton rbAsis) {
        this.rbAsis = rbAsis;
    }

    public JLabel getlTituloS() {
        return lTituloS;
    }

    public void setlTituloS(JLabel lTituloS) {
        this.lTituloS = lTituloS;
    }

    public JLabel getlRolS() {
        return lRolS;
    }

    public void setlRolS(JLabel lRolS) {
        this.lRolS = lRolS;
    }

    public JLabel getlEquipoS() {
        return lEquipoS;
    }

    public void setlEquipoS(JLabel lEquipoS) {
        this.lEquipoS = lEquipoS;
    }

    public JButton getJbS() {
        return jbS;
    }

    public void setJbS(JButton jbS) {
        this.jbS = jbS;
    }

    public JPanel getpAmcC() {
        return pAmcC;
    }

    public void setpAmcC(JPanel pAmcC) {
        this.pAmcC = pAmcC;
    }

    public JComboBox getCbElegirC() {
        return cbElegirC;
    }

    public void setCbElegirC(JComboBox cbElegirC) {
        this.cbElegirC = cbElegirC;
    }

    public JComboBox getCbJuegosC() {
        return cbJuegosC;
    }

    public void setCbJuegosC(JComboBox cbJuegosC) {
        this.cbJuegosC = cbJuegosC;
    }

    public JTextField getTfNombreC() {
        return tfNombreC;
    }

    public void setTfNombreC(JTextField tfNombreC) {
        this.tfNombreC = tfNombreC;
    }

    public JTextField getTfInicoC() {
        return tfInicoC;
    }

    public void setTfInicoC(JTextField tfInicoC) {
        this.tfInicoC = tfInicoC;
    }

    public JTextField getTfFinC() {
        return tfFinC;
    }

    public void setTfFinC(JTextField tfFinC) {
        this.tfFinC = tfFinC;
    }

    public JTextField getTfEstadoC() {
        return tfEstadoC;
    }

    public void setTfEstadoC(JTextField tfEstadoC) {
        this.tfEstadoC = tfEstadoC;
    }

    public JTextField getTfJuegoC() {
        return tfJuegoC;
    }

    public void setTfJuegoC(JTextField tfJuegoC) {
        this.tfJuegoC = tfJuegoC;
    }

    public JLabel getlTituloC() {
        return lTituloC;
    }

    public void setlTituloC(JLabel lTituloC) {
        this.lTituloC = lTituloC;
    }

    public JLabel getlEstadoC() {
        return lEstadoC;
    }

    public void setlEstadoC(JLabel lEstadoC) {
        this.lEstadoC = lEstadoC;
    }

    public JLabel getlJuegoC() {
        return lJuegoC;
    }

    public void setlJuegoC(JLabel lJuegoC) {
        this.lJuegoC = lJuegoC;
    }

    public JButton getJbC() {
        return jbC;
    }

    public void setJbC(JButton jbC) {
        this.jbC = jbC;
    }

    public JButton getbUsuarios() {
        return bUsuarios;
    }

    public void setbUsuarios(JButton bUsuarios) {
        this.bUsuarios = bUsuarios;
    }

    public JComboBox getCbElegirU() {
        return cbElegirU;
    }

    public void setCbElegirU(JComboBox cbElegirU) {
        this.cbElegirU = cbElegirU;
    }

    public JTextField getTfNicknameU() {
        return tfNicknameU;
    }

    public void setTfNicknameU(JTextField tfNicknameU) {
        this.tfNicknameU = tfNicknameU;
    }

    public JTextField getTfContraU() {
        return tfContraU;
    }

    public void setTfContraU(JTextField tfContraU) {
        this.tfContraU = tfContraU;
    }

    public JTextField getTfPriviU() {
        return tfPriviU;
    }

    public void setTfPriviU(JTextField tfPriviU) {
        this.tfPriviU = tfPriviU;
    }

    public JRadioButton getRbEstandar() {
        return rbEstandar;
    }

    public void setRbEstandar(JRadioButton rbEstandar) {
        this.rbEstandar = rbEstandar;
    }

    public JRadioButton getRbAdmin() {
        return rbAdmin;
    }

    public void setRbAdmin(JRadioButton rbAdmin) {
        this.rbAdmin = rbAdmin;
    }

    public JPanel getpAmcU() {
        return pAmcU;
    }

    public void setpAmcU(JPanel pAmcU) {
        this.pAmcU = pAmcU;
    }

    public JLabel getlTituloU() {
        return lTituloU;
    }

    public void setlTituloU(JLabel lTituloU) {
        this.lTituloU = lTituloU;
    }

    public JLabel getlPriviU() {
        return lPriviU;
    }

    public void setlPriviU(JLabel lPriviU) {
        this.lPriviU = lPriviU;
    }

    public JButton getJbU() {
        return jbU;
    }

    public void setJbU(JButton jbU) {
        this.jbU = jbU;
    }

    public JPanel getpConsultarTodos() {
        return pConsultarTodos;
    }

    public void setpConsultarTodos(JPanel pConsultarTodos) {
        this.pConsultarTodos = pConsultarTodos;
    }

    public JTextArea getTaTodos() {
        return taTodos;
    }

    public void setTaTodos(JTextArea taTodos) {
        this.taTodos = taTodos;
    }

    public JLabel getlTituloTodos() {
        return lTituloTodos;
    }

    public void setlTituloTodos(JLabel lTituloTodos) {
        this.lTituloTodos = lTituloTodos;
    }

    public JPanel getpAbrir() {
        return pAbrir;
    }

    public void setpAbrir(JPanel pAbrir) {
        this.pAbrir = pAbrir;
    }

    public JComboBox getCbAbrir() {
        return cbAbrir;
    }

    public void setCbAbrir(JComboBox cbAbrir) {
        this.cbAbrir = cbAbrir;
    }

    public JRadioButton getRbAbierto() {
        return rbAbierto;
    }

    public void setRbAbierto(JRadioButton rbAbierto) {
        this.rbAbierto = rbAbierto;
    }

    public JRadioButton getRbCerrado() {
        return rbCerrado;
    }

    public void setRbCerrado(JRadioButton rbCerrado) {
        this.rbCerrado = rbCerrado;
    }

    public JButton getJbAbrir() {
        return jbAbrir;
    }

    public void setJbAbrir(JButton jbAbrir) {
        this.jbAbrir = jbAbrir;
    }

    public JComboBox getCbElegirE() {
        return cbElegirE;
    }

    public void setCbElegirE(JComboBox cbElegirE) {
        this.cbElegirE = cbElegirE;
    }

    public JTextField getTfNombreE() {
        return tfNombreE;
    }

    public void setTfNombreE(JTextField tfNombreE) {
        this.tfNombreE = tfNombreE;
    }

    public JTextField getTfFechaE() {
        return tfFechaE;
    }

    public void setTfFechaE(JTextField tfFechaE) {
        this.tfFechaE = tfFechaE;
    }

    public JPanel getpAmcE() {
        return pAmcE;
    }

    public void setpAmcE(JPanel pAmcE) {
        this.pAmcE = pAmcE;
    }

    public JLabel getlTituloE() {
        return lTituloE;
    }

    public void setlTituloE(JLabel lTituloE) {
        this.lTituloE = lTituloE;
    }

    public JButton getJbE() {
        return jbE;
    }

    public void setJbE(JButton jbE) {
        this.jbE = jbE;
    }

    public JComboBox getCbCompeticionesI() {
        return cbCompeticionesI;
    }

    public void setCbCompeticionesI(JComboBox cbCompeticionesI) {
        this.cbCompeticionesI = cbCompeticionesI;
    }

    public JComboBox getCbEquiposI() {
        return cbEquiposI;
    }

    public void setCbEquiposI(JComboBox cbEquiposI) {
        this.cbEquiposI = cbEquiposI;
    }

    public JButton getJbInscribir() {
        return jbInscribir;
    }

    public void setJbInscribir(JButton jbInscribir) {
        this.jbInscribir = jbInscribir;
    }

    public JRadioButton getRbInscribir() {
        return rbInscribir;
    }

    public void setRbInscribir(JRadioButton rbInscribir) {
        this.rbInscribir = rbInscribir;
    }

    public JRadioButton getRbRescindir() {
        return rbRescindir;
    }

    public void setRbRescindir(JRadioButton rbRescindir) {
        this.rbRescindir = rbRescindir;
    }

    public JPanel getpInscribir() {
        return pInscribir;
    }

    public void setpInscribir(JPanel pInscribir) {
        this.pInscribir = pInscribir;
    }

    public JTextField getTfSalarioS() {
        return tfSalarioS;
    }

    public void setTfSalarioS(JTextField tfSalarioS) {
        this.tfSalarioS = tfSalarioS;
    }

    public JButton getbLogOut() {
        return bLogOut;
    }

    public void setbLogOut(JButton bLogOut) {
        this.bLogOut = bLogOut;
    }
}
