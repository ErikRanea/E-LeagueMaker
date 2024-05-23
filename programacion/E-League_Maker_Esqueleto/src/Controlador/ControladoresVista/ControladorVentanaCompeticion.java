/**
 * Esta clase se encagará del apartado lógico de la VentanaCompetición
 * @author Erik
 * @version 1.0
 * @since 15/05/2024
 */
package Controlador.ControladoresVista;

import Modelo.Competicion;
import Modelo.Enfrentamiento;
import Modelo.Jornada;
import Vista.VentanaCompeticion;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import static javax.swing.text.html.CSS.Attribute.PADDING;

public class ControladorVentanaCompeticion {

    /**
     * Atributo que conecta controlador superior con el mismo.
     * @param cv
     */
    private ControladorVista cv;


    /**
     * Atributo que almacena la VentanaCompeticion
     * @param vCompeti
     */
    private VentanaCompeticion vCompeti;

    /**
     * Atributos para instanciar objetos de la base de datos
     * @param listaCompetis
     * @param competicion
     */
    private ArrayList<Competicion> listaCompetis;
    private ArrayList<Jornada> listaJornadas;
    private ArrayList<Enfrentamiento> listaEnfrentamientos;
    private Competicion competicion;
    private Jornada jornada;
    private Enfrentamiento enfrentamiento;

    /**
     * Variables de control
     * @author Erik
     */
    private boolean hayJornadas;


    public ControladorVentanaCompeticion(ControladorVista cv)
    {
        this.cv = cv;listaCompetis = new ArrayList<>();listaJornadas = new ArrayList<>();
        listaEnfrentamientos = new ArrayList<>();
    }

    public void crearMostrar()
    {
        try
        {

            vCompeti = new VentanaCompeticion();




            vCompeti.addBBuscarAL(new BBuscar());
            vCompeti.addBLogOutAL(new BLogOut());
            vCompeti.addBInsertarResultAL(new BIntroResult());
            vCompeti.addBGenerarCalendarioAL(new BGenerarCalendario());
            vCompeti.addCBCompeticionAL(new CBCompeticion());
            vCompeti.addCBJornadaAL(new CBJornadas());

            vCompeti.setVisible(true);
        }
        catch (Exception ex)
        {
            System.out.println("\n"+ex.getMessage());
        }
    }


    /**
     * Las siguientes interfaces son las correspondientes a los listener relacionados con insertar resultados
     */


    public class BLogOut implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            vCompeti.dispose();
            cv.mostrarVentanaLogin();
        }
    }

    /**

     *
     * Esta interfaz se va a encargar de llamar al metedo que carga los datos.
     * @author Erik
     */
    public class BIntroResult implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            if(vCompeti.getpBotones().isVisible())
            {
                cargarDatosDeManeraAsincrona();
                vCompeti.verPanelBotonesLateralIzq();
            }
            else
            {
                vCompeti.quitarPanelBotonesLateralIzq();
            }
        }
    }

    public class BBuscar implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            try
            {
                mostrarEnfrentamientos();


            }
            catch (Exception ex)
            {
                System.out.println("Ha sucedido el siguiente error en BBuscar\n\n"+ex.getMessage());
            }


        }
    }

    /**
     * Esta interfaz se crea con la finalidad de no tener que estar constantemente pidiendo la competición a la lista
     * estonces cada vez que se seleccione alguno, se instanciara en la variable competicion lo indexado en el combobox
     * de competicion
     * @author Erik
     */
    public class CBCompeticion implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            try
            {competicion = listaCompetis.get(vCompeti.getCbCompeticiones().getSelectedIndex());}
            catch (Exception ex)
            {
                System.out.println("Ha salido el siguiente error en el ActionListener de la ComboBox de competicion" +
                        "\n" +ex.getMessage());
            }

        }
    }

    /**
     * Esta interfaz se crea con la finalidad de no tener que estar constantemente pidiendo la competición a la lista
     * estonces cada vez que se seleccione alguno, se instanciara en la variable jornada lo indexado en el combobox
     * de jornada
     * @author Erik
     */
    public class CBJornadas implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            try
            {jornada = listaJornadas.get(vCompeti.getCbJornadas().getSelectedIndex());}
            catch (Exception ex)
            {
                System.out.println("Ha salido el siguiente error en el ActionListener de la ComboBox de jornadas" +
                        "\n" +ex.getMessage());
            }

        }
    }

    public class BGenerarCalendario implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            try
            {

                cv.generarCalendario();
                cv.mostrarVentanaCarga(6000,vCompeti);
                rellenarCBJornadas();

            }
            catch (Exception ex)
            {
                System.out.println("Ha sucedido el siguiente error:\n\n");
            }

        }
    }


    /**
     * En este metodo, debido al gran volumen de carga de datos. Se genera una ventana de carga de datos.
     *Al generar error de asincronia, se decide hacer un segundo hilo de ejecución para que trabajen a la vez.
     * erik tonto
     * Por una parte la ventana de carga. Y por otro lado el controlador de la base de datos.
     */
/*    public void cargaDeDatosenLaVentana()
    {
        // Muestra la ventana de carga
        cv.mostrarVentanaCarga(30000, vCompeti);

        // Ejecuta la tarea en segundo plano
        SwingWorker<Void, Void> worker = new SwingWorker<Void,Void>() {


            @Override
            protected Void doInBackground()  {
                try
                {
                    // Realiza las consultas a la base de datos
                    listaCompetis = cv.pedirCompeticionesCerradas();


                }
                catch (Exception ex)
                {
                    System.out.println("Ha salido el siguento error en el segundo hilo: \n "+
                            ex.getMessage());
                }

                return null;
            }

            @Override
            protected void done() {
                try {
                    // Oculta la ventana de carga después de que se completan las consultas
                    vCompeti.verPanelBotonesLateralIzq();
                    cargarDatosComboBox();
                } catch (Exception ex) {
                    System.out.println("\nHa sucedido el siguiente error:\n\n" + ex.getMessage());
                }
            }
        };

        // Ejecuta el worker
        worker.execute();




    }*/

    public void cargarDatosComboBox()
    {

        rellenarCBCompeticiones();
        rellenarCBJornadas();

        // Ejecuta la tarea en segundo plano
        SwingWorker<Void, Void> worker = new SwingWorker<Void,Void>() {


            @Override
            protected Void doInBackground()  {
                try
                {
                    // Realiza las consultas a la base de datos
                    // Muestra la ventana de carga
                    cv.mostrarVentanaCarga(5000, vCompeti);

                }
                catch (Exception ex)
                {
                    System.out.println("Ha salido el siguento error en el segundo hilo: \n "+
                            ex.getMessage());
                }

                return null;
            }

            @Override
            protected void done() {
                try {
                    System.out.println("La carga de los combo box ha terminado");
                } catch (Exception ex) {
                    System.out.println("\nHa sucedido el siguiente error:\n\n" + ex.getMessage());
                }
            }
        };

        // Ejecuta el worker
        worker.execute();
    }


    public void cargarDatosDeManeraAsincrona()
    {
        cargarCompeticiones();
        cargarJornadasEnfrentamientos();
        cargarDatosComboBox();
    }



    public void cargarCompeticiones()
    {
        try
        {
            // En el Hilo principal hago la consulta de competiciones
            listaCompetis = cv.pedirCompeticionesCerradas();


            // Crear hilo en segundo plano
            SwingWorker<Void, Void> worker = new SwingWorker<Void,Void>() {


                @Override
                protected Void doInBackground()  {
                    try
                    {
                        //En segundo muestra la ventana de carga
                        cv.mostrarVentanaCarga(5000, vCompeti);


                    }
                    catch (Exception ex)
                    {
                        System.out.println("Ha salido el siguento error en el segundo hilo: \n "+
                                ex.getMessage());
                    }

                    return null;
                }

                @Override
                protected void done() {
                    try {
                        // Oculta la ventana de carga después de que se completan las consultas
                        System.out.println("Carga de competiciones hecha");
                    } catch (Exception ex) {
                        System.out.println("\nHa sucedido el siguiente error:\n\n" + ex.getMessage());
                    }
                }
            };

            // Ejecuta el worker
            worker.execute();
        }
        catch (Exception ex)
        {
            System.out.println("\nHa sucedido un error en el proceso de cargarCompeticiones\n" +ex.getMessage());
        }

    }

    public void cargarJornadasEnfrentamientos()
    {
        try
        {
            // En el Hilo principal hago la consulta de competiciones
            listaJornadas = cv.consultarTablaJornadas(listaCompetis.get(0).getCod());


            // Crear hilo en segundo plano
            SwingWorker<Void, Void> worker = new SwingWorker<Void,Void>() {


                @Override
                protected Void doInBackground()  {
                    try
                    {
                        //En segundo muestra la ventana de carga
                        cv.mostrarVentanaCarga(10000, vCompeti);


                    }
                    catch (Exception ex)
                    {
                        System.out.println("Ha salido el siguento error en el segundo hilo cargar jornadas: \n "+
                                ex.getMessage());
                    }

                    return null;
                }

                @Override
                protected void done() {
                    try {
                        // Oculta la ventana de carga después de que se completan las consultas
                        System.out.println( "Carga de jornadas hechas");
                    } catch (Exception ex) {
                        System.out.println("\nHa sucedido el siguiente error en el done al cargar el panel lateral:\n\n" + ex.getMessage());
                    }
                }
            };

            // Ejecuta el worker
            worker.execute();
        }
        catch (Exception ex)
        {
            System.out.println("\nHa sucedido un error en el proceso de cargarCompeticiones\n" +ex.getMessage());
        }

    }


    public void rellenarCBCompeticiones()
    {
        try
        {
            for(Competicion c : listaCompetis)
            {
                vCompeti.getCbCompeticiones().addItem(c.getNombre());
            }
            competicion = listaCompetis.get(0);
        }
        catch (Exception ex)
        {
            System.out.println("\nHa salido el siguiente error:\n"+ex.getMessage());
        }

    }

    public void rellenarCBJornadas()
    {
        try
        {
            if(!listaJornadas.isEmpty())
            {
                for(Jornada j : listaJornadas)
                {
                    vCompeti.getCbJornadas().addItem(j.getnJornada());
                }
                jornada = listaJornadas.get(0);
                hayJornadas = true;
            }
            else
            {
                hayJornadas = false;
            }

        }
        catch (Exception ex)
        {
            System.out.println("\nHa salido el siguiente error en rellenarCBJornadas:\n"+ex.getMessage());
        }
    }


    public void mostrarEnfrentamientos() {
        vCompeti.getpVisualizar().removeAll(); // Limpiar el panel antes de agregar los nuevos componentes
        listaEnfrentamientos = jornada.getListaEnfrentamientos();

        ArrayList<Enfrentamiento> listaEnfreNJornada = new ArrayList<>();

       /* for (Enfrentamiento e : listaEnfrentamientos)
        {
            if(e.get)
        }*/

        // Establecer el layout del panel
        vCompeti.getpVisualizar().setLayout(new GridLayout(listaEnfrentamientos.size(), 1));
        System.out.println("\n*****************************************" +
                "\nCantidadDeEnfrentamientos:\n" +
                +listaEnfrentamientos.size());
        // Generar componentes para cada enfrentamiento y agregarlos al panel
        for (Enfrentamiento enfrentamiento : listaEnfrentamientos) {
            JPanel enfrentamientoPanel = new JPanel(new FlowLayout());
            JLabel label = new JLabel(enfrentamiento.getEquipoLocal().getNombre() + " vs " + enfrentamiento.getEquipoVisitante().getNombre());
            JButton localButton = new JButton("Gana Local");
            JButton visitanteButton = new JButton("Gana Visitante");

            // Agregar los componentes al panel del enfrentamiento
            enfrentamientoPanel.add(label);
            enfrentamientoPanel.add(localButton);
            enfrentamientoPanel.add(visitanteButton);

            // Agregar el panel del enfrentamiento al panel pVisualizar
            vCompeti.getpVisualizar().add(enfrentamientoPanel);
        }

        vCompeti.repaint(); // Repintar la ventana para reflejar los cambios
        vCompeti.revalidate(); // Volver a validar la ventana
    }















}









