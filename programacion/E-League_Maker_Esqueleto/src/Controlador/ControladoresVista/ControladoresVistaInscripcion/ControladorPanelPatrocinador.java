package Controlador.ControladoresVista.ControladoresVistaInscripcion;

import Modelo.*;
import Vista.VentanaInscripcion;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ControladorPanelPatrocinador {
    private ControladorVentanaInscripcion cvi;
    private VentanaInscripcion vi;

    private int opcionTabla;
    private int opcionAccion;

    private List<Equipo> listaEquipos;
    private List<Patrocinador> listaPatrocinadores;

    public ControladorPanelPatrocinador(ControladorVentanaInscripcion cvi, VentanaInscripcion vi)
    {
        this.cvi = cvi;
        this.vi = vi;

        listaEquipos = new ArrayList<>();
        listaPatrocinadores = new ArrayList<>();
    }

    public void BAmcP(int opcionAccion, int opcionTabla) throws Exception {
        this.opcionTabla = opcionTabla;
        this.opcionAccion = opcionAccion;

        switch (opcionAccion)
        {
            case 1:
                vi.crearElementos(opcionTabla, opcionAccion); // Modificar el panel
                rellenarCbEquipoP(); // Rellenar la cb
                vi.addJbP(new JbP());
                break;
            case 3:
                vi.crearElementos(opcionTabla, opcionAccion); // Modificar el panel
                rellenarCbElegirP(); // Rellenar la cb
                vi.addcbElegirP(new cbElegirP()); // Verificar que hay algo seleccionado y mostrar los datos
                vi.addJbP(new JbP());
                break;
            case 4:
                vi.crearElementos(opcionTabla, opcionAccion); // Modificar el panel
                rellenarCbElegirP(); // Rellenar la cb
                vi.addcbElegirP(new cbElegirP()); // Verificar que hay algo seleccionado y mostrar los datos
                break;
        }
    }

    /** Action listener del botón de la ventana de alta modificar y consultar de patrocinadores
     * para verificar que los datos están correctamente
     *
     * @author Oskar
     * @version 2.0 17/05/2024
     */
    public class JbP implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            validarPatrocinador(); // Hacer todas las validaciones de los atributos del objeto patrocinador
        }
    }

    /** Metodo en el cual se llena la cb con todos los equipos del panel de Alta,
     * Baja y Modificar del objeto patrocinador
     *
     * @author Oskar
     * @verison 2.0 16/05/2024
     */
    public void rellenarCbEquipoP() throws Exception {
        listaEquipos.clear();
        listaEquipos = cvi.buscarEquipos(); // Vaciar la combo box
        vi.getCbEquipoP().removeAllItems(); // Llamada a tabla para recibir todos los equipos
        // Rellenar la cb
        vi.getCbEquipoP().addItem("Elija el equipo que quiere patrocinar");
        for(int x = 0; x < listaEquipos.size(); x++)
        {
            vi.getCbEquipoP().addItem(listaEquipos.get(x).getNombre());
        }
    }

    /** Hacer todas las validaciones de los atributos del objeto patrocinador
     *
     * @author Oskar
     * @version 2.0 17/05/2024
     */
    public void validarPatrocinador()
    {
        try
        {
            // Validar Nombre
            if (vi.getTfNombreP().getText().isEmpty())
                throw new Exception("El nombre no puede estar vacio");
            Pattern pat1 = Pattern.compile("^[A-Z][a-z]*$"); // Pattern
            Matcher mat1 = pat1.matcher(vi.getTfNombreP().getText());
            if (!mat1.matches())
                throw new Exception("El nombre no cumple el formato deseado");

            // Validar cb Equipo
            if (vi.getCbEquipoP().getSelectedIndex() == 0)
                throw new Exception("Elija un equipo al que patrocinar");

            // Creacion del objeto patrocinador para insertarlo o modificarlo
            int cod = 0;

            if (opcionAccion == 3)
            {
                cod = listaPatrocinadores.get(vi.getCbElegirP().getSelectedIndex() - 1).getCod();
                listaPatrocinadores.get(vi.getCbElegirP().getSelectedIndex() - 1).setNombre(vi.getTfNombreP().getText());
                listaPatrocinadores.get(vi.getCbElegirP().getSelectedIndex() - 1).setEquipo(listaEquipos.get(vi.getCbEquipoP().getSelectedIndex()-1));
            }

            Patrocinador p = new Patrocinador(cod, vi.getTfNombreP().getText(), listaEquipos.get(vi.getCbEquipoP().getSelectedIndex()-1));

            if (opcionAccion == 1)
                cvi.insertarPatrocinador(p); // Insertar patrocinador en la tabla
            else
                cvi.modificarPatrocinador(p); // Modificar patrocinador en la tabla

            vi.limpiar(); // Limpiar to/do
        }
        catch (Exception ex)
        {
            vi.mostrarError(ex.getMessage());
        }
    }

    /** Rellenar la cb con todos los nombre de los patrocinadores para elegir un patrocinadores
     *
     * @author Oskar
     * @version 2.0 17/05/2024
     */
    public void rellenarCbElegirP()
    {
        try
        {
            listaPatrocinadores.clear();
            listaPatrocinadores = cvi.buscarPatrocinadores(); // Llamada a tabla para recibir todos los Patrocinadores
            vi.getCbElegirP().removeAllItems(); // Vaciar la combo box
            if (opcionAccion == 3) // Modificar
                vi.getCbElegirP().addItem("Elija el patrocinador que desea modificar");
            else // Consultar
                vi.getCbElegirP().addItem("Elija el patrocinador que desea consultar");
            // Rellenar la cb
            for (int x = 0; x < listaPatrocinadores.size(); x++)
            {
                vi.getCbElegirP().addItem(listaPatrocinadores.get(x).getNombre());
            }
        }
        catch (Exception ex)
        {
            vi.mostrarError(ex.getMessage());
        }

    }

    /** Metodo vara verificar que haya un patrocinador seleccionado en la cb y mostrar los atributos del
     * patrocinador seleccionado para consultarlo o modificarlo
     *
     * @author Oskar
     * @version 2.0 17/05/2024
     */
    public class cbElegirP implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            try
            {
                // Verificación de que haya algo seleccionado
                if (vi.getCbElegirP().getSelectedIndex() == 0)
                    throw new Exception("Elija un jugador");

                // Una vez ya seleccionado el patrocinador en la combo box mostrar los atributos en las text field
                vi.getTfNombreP().setText(listaPatrocinadores.get(vi.getCbElegirP().getSelectedIndex()-1).getNombre());

                if (opcionAccion == 3) // Modificar
                {
                    rellenarCbEquipoP();// Rellenar la cb de equipo con todos los equipos

                    // Que se seleccione el equipo al que pertenece el patrocinador por si desea modificarlo
                    String nombreEquipo = "";
                    boolean salir = true;
                    for (int x = 0; x < listaEquipos.size() && salir; x++)
                    {
                        if (listaEquipos.get(x).getCod() == listaPatrocinadores.get(vi.getCbElegirJd().getSelectedIndex()-1).getEquipo().getCod())
                        {
                            nombreEquipo = listaEquipos.get(x).getNombre();
                            salir = false;
                        }
                    }

                    vi.getCbEquipoP().setSelectedItem(nombreEquipo);
                }
                else // Consultar
                    // Mostrar en la tf el equipo al que pertenece
                    vi.getTfEquipoP().setText(listaPatrocinadores.get(vi.getCbElegirP().getSelectedIndex()-1).getEquipo().getNombre());
            }
            catch (Exception ex)
            {
                vi.mostrarError(ex.getMessage());
            }
        }
    }

}
