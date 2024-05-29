package Controlador.ControladoresVista.ControladoresVistaInscripcion;

import Modelo.Competicion;
import Modelo.Equipo;
import Vista.VentanaInscripcion;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class ControladorPanelInscribir {
    private VentanaInscripcion vi;
    private ControladorVentanaInscripcion cvi;

    private List<Competicion> listaCompeticiones;
    private List<Equipo> listaEquipos;
    private boolean accion;


    public ControladorPanelInscribir(ControladorVentanaInscripcion cvi, VentanaInscripcion vi)
    {
        this.cvi = cvi;
        this.vi = vi;
        listaCompeticiones = new ArrayList<>();
        listaEquipos = new ArrayList<>();
    }

    public void administrar() throws Exception {
        mostrarPInscribir();
        rellenarCbCompeticiones();
        vi.addrbInscribir(new rbInscribir());
        vi.addrbRescindir(new rbRescindir());
    }

    /**Metodo para que cuando elija la opcion inscribir se muestre su panel
     *
     * @author Oskar
     * @version 2.0 20/05/2024
     */
    public void mostrarPInscribir()
    {
        vi.getpInicio().setVisible(false);
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
        vi.getpInscribir().setVisible(true);
    }

    /** Metodo en el cual se llena la cb con todos las competiciones abiertas
     *
     * @author Oskar
     * @verison 2.0 20/05/2024
     */
    public void rellenarCbCompeticiones() throws Exception {
        vi.getCbCompeticionesI().removeAllItems(); // Vaciar la combo box
        listaCompeticiones.clear();
        listaCompeticiones = cvi.buscarCompeticionesAbiertas(); // Llamada a tabla para recibir todos las competiciones
        // Rellenar la cb
        vi.getCbCompeticionesI().addItem("Elija la competición a la que desea inscribir o rescindir un equipo");
        for (int x = 0; x < listaCompeticiones.size(); x++)
        {
            vi.getCbCompeticionesI().addItem(listaCompeticiones.get(x).getNombre());
        }
    }

    /** Listener del rb de inscribir para que cuando se pulse se llene la cb contodos los equipos
     * que se pueden inscribir en la competición seleccionada
     *
     * @author Oskar
     * @version 2.0 20/05/2024
     */
    public class rbInscribir implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            try
            {
                if (vi.getCbCompeticionesI().getSelectedIndex() == 0)
                    throw new Exception("Tiene que elegir una competición primero para inscribir o rescindir un equipo");

                vi.getCbEquiposI().removeAllItems(); // Vaciar la combo box
                listaEquipos.clear();
                listaEquipos = cvi.buscarEquiposInscribir(listaCompeticiones.get(vi.getCbCompeticionesI().getSelectedIndex()-1).getCod()); // Llamada a tabla para recibir todos las competiciones
                // Rellenar la cb
                for (int x = 0; x < listaEquipos.size(); x++)
                {
                    vi.getCbEquiposI().addItem(listaEquipos.get(x).getNombre());
                }
                accion = true;
                vi.addJbInscribir(new jbInscribir());
            }
            catch (Exception ex)
            {
                vi.mostrarError(ex.getMessage());
            }
        }
    }

    /** Listener del rb de rescindir para que cuando se pulse se llene la cb contodos los equipos
     * que se pueden rescindir en la competición seleccionada
     *
     * @author Oskar
     * @version 2.0 20/05/2024
     */
    public class rbRescindir implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            try
            {
                if (vi.getCbCompeticionesI().getSelectedIndex() == 0)
                    throw new Exception("Tiene que elegir una competición primero para inscribir o rescindir un equipo");

                vi.getCbEquiposI().removeAllItems(); // Vaciar la combo box
                listaEquipos.clear();
                listaEquipos = cvi.buscarEquiposRescindir(listaCompeticiones.get(vi.getCbCompeticionesI().getSelectedIndex()-1).getCod()); // Llamada a tabla para recibir todos las competiciones
                // Rellenar la cb
                for (int x = 0; x < listaEquipos.size(); x++)
                {
                    vi.getCbEquiposI().addItem(listaEquipos.get(x).getNombre());
                }
                accion = false;
                vi.addJbInscribir(new jbInscribir());
            }
            catch (Exception ex)
            {
                vi.mostrarError(ex.getMessage());
            }
        }
    }

    /** Listener del botón de ejecutar para que cuando se pulse se llene lleve a cabo la
     * acción seleccionada en el rb
     *
     * @author Oskar
     * @version 2.0 20/05/2024
     */
    public class jbInscribir implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            try
            {
                if (vi.getCbEquiposI().getSelectedIndex() == 0)
                    throw new Exception("Elija el equipo para llevar la acción pertinente");

                if (accion)
                    cvi.inscribirEquipo(listaCompeticiones.get(vi.getCbCompeticionesI().getSelectedIndex()-1).getCod(), listaEquipos.get(vi.getCbEquiposI().getSelectedIndex()-1).getCod());
                else
                    cvi.rescindirEquipo(listaCompeticiones.get(vi.getCbCompeticionesI().getSelectedIndex()-1).getCod(), listaEquipos.get(vi.getCbEquiposI().getSelectedIndex()-1).getCod());
            }
            catch (Exception ex)
            {
                vi.mostrarError(ex.getMessage());
            }
        }
    }
}
