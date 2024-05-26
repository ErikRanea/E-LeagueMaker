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

public class ControladorPanelCompeticion {
    private ControladorVentanaInscripcion cvi;
    private VentanaInscripcion vi;

    private int opcionTabla;
    private int opcionAccion;


    private List<Juego> listaJuegos;
    private List<Competicion> listaCompeticiones;

    public ControladorPanelCompeticion(ControladorVentanaInscripcion cvi, VentanaInscripcion vi)
    {
        this.cvi = cvi;
        this.vi = vi;
        listaJuegos = new ArrayList<>();
        listaCompeticiones = new ArrayList<>();
    }

    public void BAmcC(int opcionAccion, int opcionTabla) throws Exception {
        this.opcionTabla = opcionTabla;
        this.opcionAccion = opcionAccion;

        switch (opcionAccion)
        {
            case 1:
                vi.crearElementos(opcionTabla, opcionAccion); // Modificar el panel
                rellenarCbJuegoC(); // Rellenar la cb
                vi.addJbC(new JbC());
                break;
            case 3:
                vi.crearElementos(opcionTabla, opcionAccion); // Modificar el panel
                rellenarCbElegirC(); // Rellenar la cb
                vi.addcbElegirC(new cbElegirC()); // Verificar que hay algo seleccionado y mostrar los datos
                vi.addJbC(new JbC());
                break;
            case 4:
                vi.crearElementos(opcionTabla, opcionAccion); // Modificar el panel
                rellenarCbElegirC(); // Rellenar la cb
                vi.addcbElegirC(new cbElegirC()); // Verificar que hay algo seleccionado y mostrar los datos
                break;
        }
    }

    /** Action listener del botón de la ventana de alta modificar y consultar de Competiciones
     * para verificar que los datos están correctamente
     *
     * @author Oskar
     * @version 2.0 18/05/2024
     */
    public class JbC implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            validarCompeticiones(); // Hacer todas las validaciones de los atributos del objeto competición
        }
    }

    /** Metodo en el cual se llena la cb con todos los juegos del panel de Alta,
     * Baja y Modificar del objeto Competición
     *
     * @author Oskar
     * @verison 2.0 18/05/2024
     */
    public void rellenarCbJuegoC() throws Exception {
        listaJuegos.clear();
        listaJuegos = cvi.buscarJuegos(); // Llamada a tabla para recibir todos los juegos
        vi.getCbJuegosC().removeAllItems(); // Vaciar la cb
        // Rellenar la cb
        vi.getCbJuegosC().addItem("Seleccione de que juego es la competición");
        for(int x = 0; x < listaJuegos.size(); x++)
        {
            vi.getCbJuegosC().addItem(listaJuegos.get(x).getNombre());
        }
    }

    /** Hacer todas las validaciones de los atributos del objeto Competición
     *
     * @author Oskar
     * @version 2.0 18/05/2024
     */
    public void validarCompeticiones()
    {
        try
        {
            // Validar Nombre
            if (vi.getTfNombreC().getText().isEmpty())
                throw new Exception("El nombre no puede estar vacio");
            Pattern pat1 = Pattern.compile("^[A-Z].*"); // Pattern
            Matcher mat1 = pat1.matcher(vi.getTfNombreC().getText());
            if (!mat1.matches())
                throw new Exception("El nombre no cumple el formato deseado");

            // Validar Fecha de Inicio
            if (vi.getTfInicoC().getText().isEmpty())
                throw new Exception("La fecha de inicio no puede estar vacio");
            Pattern pat2 = Pattern.compile("^[0-3][0-9]/[0-1][0-9]/[0-9]{4}$"); // Pattern
            Matcher mat2 = pat2.matcher(vi.getTfInicoC().getText());
            if (!mat2.matches())
                throw new Exception("La fecha de inicio no cumple el formato deseado");

            DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate fechaInicio = LocalDate.parse(vi.getTfInicoC().getText(), formato);

            // Validar Fecha de Fin
            if (vi.getTfFinC().getText().isEmpty())
                throw new Exception("La fecha de fin no puede estar vacio");
            Pattern pat3 = Pattern.compile("^[0-3][0-9]/[0-1][0-9]/[0-9]{4}$"); // Pattern
            Matcher mat3 = pat3.matcher(vi.getTfFinC().getText());
            if (!mat3.matches())
                throw new Exception("La fecha de fin no cumple el formato deseado");

            LocalDate fechaFin = LocalDate.parse(vi.getTfFinC().getText(), formato);

            // Validar la cb de Juego
            if (vi.getCbJuegosC().getSelectedIndex() == 0)
                throw new Exception("Elije el juego al que pertenece");

            // Crear el objeto Competición para introducirlo o modificarlo
            int cod = 0;
            List<Jornada> listaJornadasC = new ArrayList<>();
            if (opcionAccion == 3)
            {
                cod = listaCompeticiones.get(vi.getCbElegirC().getSelectedIndex() - 1).getCod();
                listaJornadasC = listaCompeticiones.get(vi.getCbElegirC().getSelectedIndex() - 1).getListaJornada();
                listaCompeticiones.get(vi.getCbElegirC().getSelectedIndex() - 1).setNombre(vi.getTfNombreC().getText());
                listaCompeticiones.get(vi.getCbElegirC().getSelectedIndex() - 1).setFechaInicio(fechaInicio);
                listaCompeticiones.get(vi.getCbElegirC().getSelectedIndex() - 1).setFechaFin(fechaFin);
                listaCompeticiones.get(vi.getCbElegirC().getSelectedIndex() - 1).setJuego(listaJuegos.get(vi.getCbJuegosC().getSelectedIndex()-1));
            }

            Competicion c = new Competicion(cod, vi.getTfNombreS().getText(), fechaInicio, fechaFin, true, listaJuegos.get(vi.getCbJuegosC().getSelectedIndex()-1), listaJornadasC);

            if (opcionAccion == 1)
                cvi.insertarCompeticion(c); // Insertar competición en la tabla
            else
                cvi.modificarCompeticion(c); // Modificar competición en la tabla

            vi.limpiar(); // Limpiar to/do
        }
        catch (Exception ex)
        {
            vi.mostrarError(ex.getMessage());
        }
    }

    /** Rellenar la cb con todos los nombres de las competiciones para elegir una
     *
     * @author Oskar
     * @version 2.0 18/05/2024
     */
    public void rellenarCbElegirC() throws Exception {
        listaCompeticiones.clear();
        listaCompeticiones = cvi.buscarCompeticiones(); // Llamada a tabla para recibir todos las competiciones
        vi.getCbElegirC().removeAllItems(); // Vaciar la cb
        if (opcionAccion == 3) // Modificar
            vi.getCbElegirC().addItem("Elija la competición que desea modificar");
        else // Consultar
            vi.getCbElegirC().addItem("Elija la competición que desea consultar");
        // Rellenar la cb
        for (int x = 0; x < listaCompeticiones.size(); x ++)
        {
            vi.getCbElegirC().addItem(listaCompeticiones.get(x).getNombre());
        }
    }

    /** Metodo vara verificar que haya una competición seleccionada en la cb y mostrar los atributos de la
     * competición seleccionada para consultarla o modificarla
     *
     * @author Oskar
     * @version 2.0 18/05/2024
     */
    public class cbElegirC implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            try
            {
                // Verificación de que haya algo seleccionado
                if (vi.getCbElegirC().getSelectedIndex() == 0)
                    throw new Exception("Elija una competición");

                // Una vez ya seleccionada la competición en la combo box mostrar los atributos en las text field
                vi.getTfNombreC().setText(listaCompeticiones.get(vi.getCbElegirC().getSelectedIndex() - 1).getNombre());
                vi.getTfInicoC().setText(String.valueOf(listaCompeticiones.get(vi.getCbElegirC().getSelectedIndex() - 1).getFechaInicio()));
                vi.getTfFinC().setText(String.valueOf(listaCompeticiones.get(vi.getCbElegirC().getSelectedIndex()-1).getFechaFin()));

                if (opcionAccion == 3) // Modificar
                {
                    rellenarCbJuegoC(); // Rellenar la cb de juego con todos los juegos

                    // Que se seleccione el juego al que pertenece la competición por si desea modificarlo
                    int posicionJuego = 0;
                    boolean salir = true;
                    for (int x = 0; x < listaJuegos.size() && salir; x++)
                    {
                        if (listaJuegos.get(x).getCod() == listaCompeticiones.get(vi.getCbElegirC().getSelectedIndex() - 1).getJuego().getCod())
                        {
                            posicionJuego = x;
                            salir = false;
                        }
                    }

                    vi.getCbJuegosC().setSelectedItem(posicionJuego);
                }
                else // Consultar
                {
                    // Mostrar en la tf el estado de la competición
                    String estado;
                    if (listaCompeticiones.get(vi.getCbJuegosC().getSelectedIndex()).isEstadoAbierto())
                        estado = "Abierto";
                    else
                        estado = "Cerrado";
                    vi.getTfEstadoC().setText(estado);

                    // Mostrar en la tf el juego al que pertenece
                    vi.getTfJuegoC().setText(listaCompeticiones.get(vi.getCbElegirC().getSelectedIndex() - 1).getJuego().getNombre());
                }
            }
            catch (Exception ex)
            {
                vi.mostrarError(ex.getMessage());
            }
        }
    }
}
