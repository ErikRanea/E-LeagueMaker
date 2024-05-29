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

public class ControladorPanelJugador {
    private ControladorVentanaInscripcion cvi;
    private VentanaInscripcion vi;

    private int opcionTabla;
    private int opcionAccion;

    private java.util.List<Jugador> listaJugadores;
    private java.util.List<Equipo> listaEquipos;

    public ControladorPanelJugador(ControladorVentanaInscripcion cvi, VentanaInscripcion vi)
    {
        this.cvi = cvi;
        this.vi = vi;

        listaEquipos = new ArrayList<>();
        listaJugadores = new ArrayList<>();
    }

    public void BAmcJd(int opcionAccion, int opcionTabla) throws Exception {
        this.opcionTabla = opcionTabla;
        this.opcionAccion = opcionAccion;

        switch (opcionAccion)
        {
            case 1:
                vi.crearElementos(opcionTabla, opcionAccion); // Modificar el panel
                rellenarCbEquipoJd(); // Rellenar la cb
                vi.addBJdAL(new BJd());
                break;
            case 3:
                vi.crearElementos(opcionTabla, opcionAccion); // Modificar el panel
                rellenarCbElegirJd(); // Rellenar la cb
                vi.addcbElegirJd(new cbElegirJd()); // Verificar que hay algo seleccionado y mostrar los datos
                vi.addBJdAL(new BJd());
                break;
            case 4:
                vi.crearElementos(opcionTabla, opcionAccion); // Modificar el panel
                rellenarCbElegirJd(); // Rellenar la cb
                vi.addcbElegirJd(new cbElegirJd()); // Verificar que hay algo seleccionado y mostrar los datos
                break;
        }
    }

    /** Action listener del botón de la ventana de alta modificar y consultar de jugadores
     * para verificar que los datos están correctamente
     *
     * @author Oskar
     * @version 2.0 16/05/2024
     */
    public class BJd implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            validarJugador(); // Hacer todas las validaciones de los atributos del objeto jugador
        }
    }

    /** Hacer todas las validaciones de los atributos del objeto jugador
     *
     * @author Oskar
     * @version 2.0 16/05/2024
     */
    public void validarJugador()
    {
        try
        {
            // Validacion Nombre
            if (vi.getTfNombreJd().getText().isEmpty())
                throw new Exception("El nombre no puede estar vacio");
            Pattern pat1 = Pattern.compile("^[A-Z].*"); // Pattern
            Matcher mat1 = pat1.matcher(vi.getTfNombreJd().getText());
            if (!mat1.matches())
                throw new Exception("El nombre no cumple el formato deseado");

            // Validacion Apellido
            if (vi.getTfApellidoJd().getText().isEmpty())
                throw new Exception("El apellido no puede estar vacio");
            Pattern pat2 = Pattern.compile("^[A-Z].*"); // Pattern
            Matcher mat2 = pat2.matcher(vi.getTfApellidoJd().getText());
            if (!mat2.matches())
                throw new Exception("El apellido no cumple el formato deseado");

            // Validacion Rol
            if (vi.getTfRolJd().getText().isEmpty())
                throw new Exception("El rol no puede estar vacio");
            Pattern pat3 = Pattern.compile("^[A-Z].*"); // Pattern
            Matcher mat3 = pat3.matcher(vi.getTfRolJd().getText());
            if (!mat3.matches())
                throw new Exception("El rol no cumple el formato deseado");

            // Validacion Salario
            if (vi.getTfSalarioJd().getText().isEmpty())
                throw new Exception("El salario no puede estar vacio");
            Pattern pat4 = Pattern.compile("^[1-9]{5}]$"); // Pattern
            Matcher mat4 = pat4.matcher(vi.getTfSalarioJd().getText());
            if (!mat4.matches())
                throw new Exception("El salario no cumple el formato deseado");

            int salario = Integer.parseInt(vi.getTfSalarioJd().getText());

            // Validacion Nacionalidad
            if (vi.getTfNacionalidadJd().getText().isEmpty())
                throw new Exception("La nacionalidad no puede estar vacia");
            Pattern pat5 = Pattern.compile("^[A-Z].*"); // Pattern
            Matcher mat5 = pat5.matcher(vi.getTfSalarioJd().getText());
            if (!mat5.matches())
                throw new Exception("La nacionalidad no cumple el formato deseado");

            // Validacion Fecha de nacimiento
            if (vi.getTfFechaJd().getText().isEmpty())
                throw new Exception("La fecha de nacimeinto no puede estar vacia");
            Pattern pat6 = Pattern.compile("^[0-3][0-9]/[0-1][0-9]/[0-9]{4}$"); // Pattern
            Matcher mat6 = pat6.matcher(vi.getTfFechaJd().getText());
            if (!mat6.matches())
                throw new Exception("La fecha de nacimiento no cumple el formato deseado");

            DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate fecha = LocalDate.parse(vi.getTfFechaJd().getText(), formato);

            // Validacion Nickname
            if (vi.getTfNicknameJd().getText().isEmpty())
                throw new Exception("El nickname no puede estar vacio");
            Pattern pat7 = Pattern.compile("^[A-Z].*"); // Pattern
            Matcher mat7 = pat7.matcher(vi.getTfNicknameJd().getText());
            if (!mat7.matches())
                throw new Exception("El nickname no cumple el formato deseado");

            // Validacion de la cb del Equipo en el que juega
            if (vi.getCbEquipoJd().getSelectedIndex() == 0)
                throw new Exception("Tiene que elegir un equipo");

            // Crear el objeto Jugador para insertarlo o modificarlo
            int cod = 0;

            if (opcionAccion == 3)
            {
                cod = listaJugadores.get(vi.getCbElegirJd().getSelectedIndex() - 1).getCod();
                listaJugadores.get(vi.getCbElegirJd().getSelectedIndex() - 1).setNombre(vi.getTfNombreJe().getText());
                listaJugadores.get(vi.getCbElegirJd().getSelectedIndex() - 1).setApellido(vi.getTfApellidoJd().getText());
                listaJugadores.get(vi.getCbElegirJd().getSelectedIndex() - 1).setRol(vi.getTfRolJd().getText());
                listaJugadores.get(vi.getCbElegirJd().getSelectedIndex() - 1).setSalario(salario);
                listaJugadores.get(vi.getCbElegirJd().getSelectedIndex() - 1).setNacionalidad(vi.getTfNacionalidadJd().getText());
                listaJugadores.get(vi.getCbElegirJd().getSelectedIndex() - 1).setFechaNacimiento(fecha);
                listaJugadores.get(vi.getCbElegirJd().getSelectedIndex() - 1).setNickname(vi.getTfNicknameJd().getText());
                listaJugadores.get(vi.getCbElegirJd().getSelectedIndex() - 1).setEquipo(listaEquipos.get(vi.getCbEquipoJd().getSelectedIndex()-1));
            }

            Jugador jd = new Jugador(cod, vi.getTfNombreJd().getText(), vi.getTfApellidoJd().getText(), vi.getTfRolJd().getText(), salario, vi.getTfNacionalidadJd().getText(), fecha, vi.getTfNicknameJd().getText(), listaEquipos.get(vi.getCbEquipoJd().getSelectedIndex()-1));

            if (opcionAccion == 1)
                cvi.insertarJugador(jd); // Insertar jugador en la tabla
            else
                cvi.modificarJugador(jd); // Modificar jugador en la tabla

            vi.limpiar(); // Resetear to/do
        }
        catch (Exception ex)
        {
            vi.mostrarError(ex.getMessage());
        }
    }

    /** Metodo en el cual se llena la cb con todos los equipos del panel de Alta,
     * Baja y Modificar del objeto jugador
     *
     * @author Oskar
     * @verison 2.0 16/05/2024
     */
    public void rellenarCbEquipoJd() throws Exception {
        vi.getCbEquipoJd().removeAllItems(); // Vaciar la combo box
        listaEquipos.clear();
        listaEquipos = cvi.buscarEquipos(); // Llamada a tabla para recibir todos los equipos
        // Rellenar la cb
        vi.getCbEquipoJd().addItem("Elija el equipo del jugador que está inscribiendo");
        for (int x = 0; x < listaEquipos.size(); x++)
        {
            vi.getCbEquipoJd().addItem(listaEquipos.get(x).getNombre());
        }
    }

    /** Rellenar la cb con todos los nicknames de los jugadores para elegir un jugador
     *
     * @author Oskar
     * @version 2.0 16/05/2024
     */
    public void rellenarCbElegirJd()
    {
        try
        {
            vi.getCbElegirJd().removeAllItems(); // Vaciar la combo box
            listaJugadores.clear();
            listaJugadores = cvi.buscarJugadores(); // Llamada a tabla para recibir todos los jugadores
            // Modificacion del enunciado dependiendo la acción
            if (opcionAccion == 3) // Modificar
                vi.getCbElegirJd().addItem("Elija el jugador que desea modificar");
            else // Consultar
                vi.getCbElegirJd().addItem("Elija el jugador que desea consultar");
            // Rellenar la cb
            for (int x = 0; x < listaJugadores.size(); x++)
            {
                vi.getCbElegirJd().addItem(listaJugadores.get(x));
            }
        }
        catch (Exception ex)
        {
            vi.mostrarError(ex.getMessage());
        }
    }

    /** Metodo vara verificar que haya un jugador seleccionado en la cb y mostrar los atributos del
     * jugador seleccionado para consultarlo o modificarlo
     *
     * @author Oskar
     * @version 2.0 16/05/2024
     */
    public class cbElegirJd implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            try
            {
                // Verificación de que haya algo seleccionado
                if (vi.getCbElegirJd().getSelectedIndex() == 0)
                    throw new Exception("Elija un jugador para modificarlo");

                // Una vez ya seleccionado el jugador en la combo box mostrar los atributos en las text fieled
                vi.getTfNombreJd().setText(listaJugadores.get(vi.getCbElegirJd().getSelectedIndex()-1).getNombre());
                vi.getTfApellidoJd().setText(listaJugadores.get(vi.getCbElegirJd().getSelectedIndex()-1).getApellido());
                vi.getTfRolJd().setText(listaJugadores.get(vi.getCbElegirJd().getSelectedIndex()-1).getRol());
                vi.getTfSalarioJd().setText(String.valueOf(listaJugadores.get(vi.getCbElegirJd().getSelectedIndex()-1).getSalario()));
                vi.getTfNacionalidadJd().setText(listaJugadores.get(vi.getCbElegirJd().getSelectedIndex()-1).getNacionalidad());
                vi.getTfFechaJd().setText(String.valueOf(listaJugadores.get(vi.getCbElegirJd().getSelectedIndex()-1).getFechaNacimiento()));
                vi.getTfNicknameJd().setText(listaJugadores.get(vi.getCbElegirJd().getSelectedIndex()-1).getNickname());

                if (opcionAccion == 3) // Modificar
                {
                    rellenarCbEquipoJd(); // Rellenar la cb de equipo con todos los equipos

                    // Que se seleccione el equipo al que pertenece el jugador por si desea modificarlo
                    int posicionEquipo = 0;
                    boolean salir = true;
                    for (int x = 0; x < listaEquipos.size() && salir; x++)
                    {
                        if (listaEquipos.get(x).getCod() == listaJugadores.get(vi.getCbElegirJd().getSelectedIndex()-1).getEquipo().getCod())
                        {
                            posicionEquipo = x;
                            salir = false;
                        }
                    }

                    vi.getCbEquipoJd().setSelectedItem(posicionEquipo);
                }
                else // Consultar
                    // Mostrar en la tf el equipo al que pertenece
                    vi.getTfEquipoJd().setText(listaJugadores.get(vi.getCbElegirJd().getSelectedIndex()-1).getEquipo().getNombre());
            }
            catch (Exception ex)
            {
                vi.mostrarError(ex.getMessage());
            }
        }
    }
}
