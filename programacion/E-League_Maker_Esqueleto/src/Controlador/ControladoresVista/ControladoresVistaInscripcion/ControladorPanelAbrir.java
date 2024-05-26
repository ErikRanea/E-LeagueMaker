package Controlador.ControladoresVista.ControladoresVistaInscripcion;

import Modelo.*;
import Vista.VentanaInscripcion;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ControladorPanelAbrir {
    private VentanaInscripcion vi;
    private ControladorVentanaInscripcion cvi;

    private List<Competicion> listaCompeticiones;


    public ControladorPanelAbrir(ControladorVentanaInscripcion cvi, VentanaInscripcion vi)
    {
        this.cvi = cvi;
        this.vi = vi;
    }

    public void administrar() throws Exception {
        mostrarPAbrir();
        rellenarCbAbrir();
        vi.addcbAbrir(new cbAbrir());
        vi.addJbAbrir(new jbAbrir());
    }

    /**Metodo para que cuando elija la opcion abrir se muestre su panel
     *
     * @author Oskar
     * @version 2.0 19/05/2024
     */
    public void mostrarPAbrir()
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
        vi.getpAbrir().setVisible(true);
        vi.getpAmcE().setVisible(false);
        vi.getpInscribir().setVisible(false);
    }

    /** Metodo en el cual se llena la cb con todos las competiciones
     *
     * @author Oskar
     * @verison 2.0 19/05/2024
     */
    public void rellenarCbAbrir() throws Exception {
        vi.getCbAbrir().removeAllItems(); // Vaciar la combo box
        listaCompeticiones = cvi.buscarCompeticiones(); // Llamada a tabla para recibir todos las competiciones
        // Rellenar la cb
        vi.getCbAbrir().addItem("Elija la competición a la que desea cambiar el estado");
        for (int x = 0; x < listaCompeticiones.size(); x++)
        {
            vi.getCbAbrir().addItem(listaCompeticiones.get(x).getNombre());
        }
    }

    /** Metodo para verificar que haya una competición seleccionada en la cb y mostrar en los
     * rb si está abierto o no
     *
     * @author Oskar
     * @version 2.0 19/05/2024
     */
    public class cbAbrir implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            try
            {
                if (vi.getCbAbrir().getSelectedIndex() -1 == 0)
                    throw new Exception("Seleccione una competición");

                if (listaCompeticiones.get(vi.getCbAbrir().getSelectedIndex()-1).isEstadoAbierto())
                    vi.getRbAbierto().setSelected(true);
                else
                    vi.getRbCerrado().setSelected(true);
            }
            catch (Exception ex)
            {
                vi.mostrarError(ex.getMessage());
            }
        }
    }

    /** Action listener del botón ejecutar del panel Abrir/Cerrar en el que se modifica el estado si es necesario
     *
     * @author Oskar
     * @version 2.0 19/05/2024
     */
    public class jbAbrir implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            int estado;
            if (vi.getRbAbierto().isSelected())
                estado = 1;
            else
                estado = 0;

            int cod = listaCompeticiones.get(vi.getCbAbrir().getSelectedIndex()-1).getCod();

            try {
                cvi.modificarCompeticionEstado(cod, estado); // Modificar el estado en la tabla
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        }
    }
}
