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

    public ControladorVentanaCompeticion(ControladorVista cv)
    {
        this.cv = cv;listaCompetis = new ArrayList<>();
    }

    public void crearMostrar()
    {
        try
        {

            vCompeti = new VentanaCompeticion();

            vCompeti.addBBuscarAL(new BBuscar());
            vCompeti.addBLogOutAL(new BLogOut());
            vCompeti.addBInsertarResultAL(new BIntroResult());
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
            vCompeti.verPanelBotonesLateralIzq();
            rellenarCBCompeticiones();
            rellenarCBJornadas();
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
                /*todo hay que generar el calendario */
            }
            catch (Exception ex)
            {
                System.out.println("Ha sucedido el siguiente error\n\n"+ex.getMessage());
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
                vCompeti.getCbCompeticiones().addItem(j.getnJornada());
            }
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
