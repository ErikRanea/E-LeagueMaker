package Controlador.ControladoresVista.ControladoresVistaInscripcion;

import Modelo.*;
import Vista.VentanaInscripcion;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ControladorPanelEquipo {
    private ControladorVentanaInscripcion cvi;
    private VentanaInscripcion vi;

    private int opcionTabla;
    private int opcionAccion;

    private List<Equipo> listaEquipos;

    public ControladorPanelEquipo(ControladorVentanaInscripcion cvi, VentanaInscripcion vi)
    {
        this.cvi = cvi;
        this.vi = vi;
        listaEquipos = new ArrayList<>();
    }

    public void BAmcE(int opcionAccion, int opcionTabla) throws Exception {
        this.opcionTabla = opcionTabla;
        this.opcionAccion = opcionAccion;

        switch (opcionAccion)
        {
            case 1:
                vi.crearElementos(opcionTabla, opcionAccion); // Modificar el panel
                vi.addJbE(new JbE());
                break;
            case 3:
                vi.crearElementos(opcionTabla, opcionAccion); // Modificar el panel
                rellenarCbElegirE(); // Rellenar la cb
                vi.addcbElegirE(new cbElegirE()); // Verificar que hay algo seleccionado y mostrar los datos
                vi.addJbE(new JbE());
                break;
            case 4:
                vi.crearElementos(opcionTabla, opcionAccion); // Modificar el panel
                rellenarCbElegirE(); // Rellenar la cb
                vi.addcbElegirE(new cbElegirE()); // Verificar que hay algo seleccionado y mostrar los datos
        }
    }

    /** Action listener del botón de la ventana de alta modificar y consultar de Equipos
     * para verificar que los datos están correctamente
     *
     * @author Oskar
     * @version 2.0 19/05/2024
     */
    public class JbE implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            validarEquipos(); // Hacer todas las validaciones de los atributos del objeto equipo
        }
    }

    /** Hacer todas las validaciones de los atributos del objeto Equipo
     *
     * @author Oskar
     * @version 2.0 19/05/2024
     */
    public void validarEquipos()
    {
        try
        {
            // Validar Nombre
            if (vi.getTfNombreE().getText().isEmpty())
                throw new Exception("El nombre no puede estar vacio");
            Pattern pat1 = Pattern.compile("^[A-Z].*"); // Pattern
            Matcher mat1 = pat1.matcher(vi.getTfNombreE().getText());
            if (!mat1.matches())
                throw new Exception("El nombre no cumple el formato deseado");

            // Validar Fecha de creacion
            if (vi.getTfFechaE().getText().isEmpty())
                throw new Exception("La fecha de creación no puede estar vacia");
            Pattern pat2 = Pattern.compile("^[0-3][0-9]/[0-1][0-9]/[0-9]{4}$"); // Pattern
            Matcher mat2 = pat2.matcher(vi.getTfFechaE().getText());
            if (!mat2.matches())
                throw new Exception("La fecha de creación no cumple el formato deseado");

            DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate fecha = LocalDate.parse(vi.getTfFechaE().getText(), formato);

            // Crear el objeto Equipo para introducirlo o modificarlo
            int cod = 0;
            ArrayList<Jugador> listaJugadorE = new ArrayList<>();
            ArrayList<Staff> listaStaffE = new ArrayList<>();
            ArrayList<Patrocinador> listaPatrocinadorE = new ArrayList<>();

            if (opcionAccion == 3)
            {
                cod = listaEquipos.get(vi.getCbElegirE().getSelectedIndex() - 1).getCod();
                listaJugadorE = listaEquipos.get(vi.getCbElegirE().getSelectedIndex() - 1).getListaJugadores();
                listaStaffE = listaEquipos.get(vi.getCbElegirE().getSelectedIndex() - 1).getListaStaffs();
                listaPatrocinadorE = listaEquipos.get(vi.getCbElegirE().getSelectedIndex() - 1).getListaPatrocinadores();
                listaEquipos.get(vi.getCbElegirE().getSelectedIndex() - 1).setNombre(vi.getTfNombreE().getText());
                listaEquipos.get(vi.getCbElegirE().getSelectedIndex() - 1).setFechaFundacion(fecha);
            }

            Equipo e = new Equipo(cod, vi.getTfNombreE().getText(), fecha, listaPatrocinadorE, listaStaffE, listaJugadorE);

            if (opcionAccion == 1)
                cvi.insertarEquipo(e); // Insertar equipo en la tabla
            else
                cvi.modificarEquipo(e); // Modificar equipo en la tabla

            vi.limpiar(); // Limpiar to/do
        }
        catch (Exception ex)
        {
            vi.mostrarError(ex.getMessage());
        }
    }

    /** Rellenar la cb con todos los nombres de los equipos para elegir uno
     *
     * @author Oskar
     * @version 2.0 19/05/2024
     */
    public void rellenarCbElegirE() throws Exception {
        listaEquipos.clear();
        listaEquipos = cvi.buscarEquipos(); // Llamada a tabla para recibir todos los equipos
        vi.getCbElegirE().removeAllItems(); // Vaciar la cb
        if (opcionAccion == 3) // Modificar
            vi.getCbElegirE().addItem("Elija el equipo que desea modificar");
        else // Consultar
            vi.getCbElegirE().addItem("Elija el equipo que desea consultar");
        // Rellenar la cb
        for (int x = 0; x < listaEquipos.size(); x ++)
        {
            vi.getCbElegirE().addItem(listaEquipos.get(x).getNombre());
        }
    }

    /** Metodo vara verificar que haya un equipo seleccionado en la cb y mostrar los atributos del
     * equipo seleccionada para consultarla o modificarla
     *
     * @author Oskar
     * @version 2.0 19/05/2024
     */
    public class cbElegirE implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            try
            {
                // Verificación de que haya algo seleccionado
                if (vi.getCbElegirE().getSelectedIndex() == 0)
                    throw new Exception("Elija un Equipo");

                // Una vez ya seleccionado el equipo en la combo box mostrar los atributos en las text field
                vi.getTfNombreE().setText(listaEquipos.get(vi.getCbElegirE().getSelectedIndex() - 1).getNombre());
                vi.getTfFechaE().setText(String.valueOf(listaEquipos.get(vi.getCbElegirE().getSelectedIndex() - 1).getFechaFundacion()));
            }
            catch (Exception ex)
            {
                vi.mostrarError(ex.getMessage());
            }
        }
    }
}
