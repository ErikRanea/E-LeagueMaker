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
            vCompeti.getpCargaDeDatos().setVisible(false);



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

    public class BIntroResult implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            try
            {
                vCompeti.verPanelCarga();//Primera llamada para poner el panel de carga
                Thread.sleep(2000);
                vCompeti.verPanelBotonesLateralIzq();
                rellenarCBCompeticiones();
                vCompeti.verPanelCarga(); //Segunda llamada para que tras la carga de las competiciones se quite



            }
            catch (Exception ex)
            {
                System.out.println("\nHa sucedido el siguiente error:\n\n"+ex.getMessage());
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
                int seleccionCompeti = vCompeti.getCbCompeticiones().getSelectedIndex();
                competicion  = listaCompetis.get(seleccionCompeti);
                int seleccionJornada = vCompeti.getCbJornadas().getSelectedIndex();
                jornada = listaJornadas.get(seleccionJornada);
                listaEnfrentamientos = jornada.getListaEnfrentamientos();
                for (Jornada j : listaJornadas)
                {
                    System.out.println(j.getnJornada()+" es  el número de jornada");
                }


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
                vCompeti.verPanelCarga();
                Thread.sleep(2000);
                cv.generarCalendario();
                rellenarCBJornadas();
                Thread.sleep(2000);
                vCompeti.verPanelCarga();

            }
            catch (Exception ex)
            {
                System.out.println("Ha sucedido el siguiente error:\n\n");
            }

        }
    }



    public void rellenarCBCompeticiones()
    {
        try
        {
            listaCompetis = cv.pedirCompeticionesCerradas();

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
            listaJornadas = cv.consultarTablaJornadas(competicion.getCod());
            for(Jornada j : listaJornadas)
            {
                vCompeti.getCbJornadas().addItem(j.getnJornada());
            }
            jornada = listaJornadas.get(0);
        }
        catch (Exception ex)
        {
            System.out.println("\nHa salido el siguiente error:\n"+ex.getMessage());
        }
    }




/*
  public  void crearEnfrentamientos() {

        int numeroJornadas = listaJornadas.size();
        int numeroEnfrentamientosPorJornada = numeroEquipos / 2;

        int yOffset = PADDING;

        for (int jornada = 1; jornada <= numeroJornadas; jornada++) {
            for (int enfrentamiento = 1; enfrentamiento <= numeroEnfrentamientosPorJornada; enfrentamiento++) {
                int equipoLocal = (enfrentamiento * 2) - 1;
                int equipoVisitante = enfrentamiento * 2;

                // Crear JLabel para el enfrentamiento
                JLabel label = new JLabel("Equipo " + equipoLocal + " vs Equipo " + equipoVisitante);
                label.setBounds(PADDING, yOffset, PANEL_WIDTH / 3, COMPONENT_HEIGHT);
                pVisualizar.add(label);

                // Crear JButton para "Gana Local"
                JButton btnGanaLocal = new JButton("Gana Local");
                btnGanaLocal.setBounds(PANEL_WIDTH / 3 + PADDING, yOffset, PANEL_WIDTH / 6, COMPONENT_HEIGHT);
                btnGanaLocal.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        System.out.println("Gana Local: Equipo " + equipoLocal);
                    }
                });
                pVisualizar.add(btnGanaLocal);

                // Crear JButton para "Gana Visitante"
                JButton btnGanaVisitante = new JButton("Gana Visitante");
                btnGanaVisitante.setBounds(PANEL_WIDTH / 2 + PADDING, yOffset, PANEL_WIDTH / 6, COMPONENT_HEIGHT);
                btnGanaVisitante.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        System.out.println("Gana Visitante: Equipo " + equipoVisitante);
                    }
                });
                pVisualizar.add(btnGanaVisitante);

                // Incrementar el yOffset para la siguiente fila de componentes
                yOffset += COMPONENT_HEIGHT + PADDING;

                // Verificar si hemos alcanzado el límite del panel
                if (yOffset > PANEL_HEIGHT - COMPONENT_HEIGHT - PADDING) {
                    System.out.println("No hay suficiente espacio para agregar más enfrentamientos sin redimensionar el panel.");
                    return;
                }
            }
        }
    }


*/





}
