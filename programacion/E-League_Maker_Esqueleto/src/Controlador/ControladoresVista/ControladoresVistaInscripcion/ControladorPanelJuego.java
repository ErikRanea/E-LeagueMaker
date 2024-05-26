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

public class ControladorPanelJuego {
    private ControladorVentanaInscripcion cvi;
    private VentanaInscripcion vi;

    private int opcionTabla;
    private int opcionAccion;

    private List<Juego> listaJuegos;

    public ControladorPanelJuego(ControladorVentanaInscripcion cvi, VentanaInscripcion vi)
    {
        this.cvi = cvi;
        this.vi = vi;
        listaJuegos = new ArrayList<>();
    }

    public void BAmcJe(int opcionAccion, int opcionTabla)
    {
        this.opcionTabla = opcionTabla;
        this.opcionAccion = opcionAccion;

        switch (opcionAccion)
        {
            case 1:
                vi.crearElementos(opcionTabla, opcionAccion); // Modificar el panel
                vi.addJbJe(new JbJe());
                break;
            case 3:
                vi.crearElementos(opcionTabla, opcionAccion); // Modificar el panel
                rellenarCbElegirJe(); // Rellenar la cb y verificar que hay algo seleccionado
                vi.addcbElegitJe(new cbElegirJeAl()); // Verificar que hay algo seleccionado y mostrar los datos
                vi.addJbJe(new JbJe());
                break;
            case 4:
                vi.crearElementos(opcionTabla, opcionAccion); // Modificar el panel
                rellenarCbElegirJe(); // Rellenar la cb y verificar que hay algo seleccionado
                vi.addcbElegitJe(new cbElegirJeAl()); // Verificar que hay algo seleccionado y mostrar los datos
                break;
        }
    }

    /** Action listener del botón de la ventana de alta modificar y consultar de juegos
     * para verificar que los datos están correctamente
     *
     * @author Oskar
     * @version 2.0 17/05/2024
     */
    public class JbJe implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            validarJuego(); // Hacer todas las validaciones de los atributos del objeto juego
        }
    }

    /** Hacer todas las validaciones de los atributos del objeto juego
     *
     * @author Oskar
     * @version 2.0 17/05/2024
     */
    private void validarJuego()
    {
        try
        {
            // Validar Nombre
            if (vi.getTfNombreJe().getText().isEmpty())
                throw new Exception("El nombre no puede estar vacio");
            Pattern pat1 = Pattern.compile("^[A-Z].*"); // Pattern
            Matcher mat1 = pat1.matcher(vi.getTfNombreJe().getText());
            if (!mat1.matches())
                throw new Exception("El nombre no cumple el formato deseado");

            // Validar Desarroladora
            if (vi.getTfDesarroladoraJe().getText().isEmpty())
                throw new Exception("La desarrolladora no puede estar vacia");
            Pattern pat2 = Pattern.compile("^[A-Z].*"); // Pattern
            Matcher mat2 = pat2.matcher(vi.getTfDesarroladoraJe().getText());
            if (!mat2.matches())
                throw new Exception("La desarrolladora no cumple el formato deseado");

            // Validar Fecha de lanzamiento
            if (vi.getTfFechaJe().getText().isEmpty())
                throw new Exception("La fecha de lanzamiento no puede estar vacia");
            Pattern pat3 = Pattern.compile("^[0-3][0-9]/[0-1][0-9]/[0-9]{4}$"); // Pattern
            Matcher mat3 = pat3.matcher(vi.getTfFechaJe().getText());
            if (!mat3.matches())
                throw new Exception("La fecha de lanzamiento no cumple el formato deseado");

            DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate fecha = LocalDate.parse(vi.getTfFechaJe().getText(), formato);

            // Crear el objeto Juegor para insertarlo o modificarlo
            int cod = 0;
            List<Competicion> listaCompeticionesJe = null;

            if (opcionAccion == 3)
            {
                cod = listaJuegos.get(vi.getCbElegirJe().getSelectedIndex() - 1).getCod();
                listaCompeticionesJe = listaJuegos.get(vi.getCbElegirJe().getSelectedIndex() - 1).getListaCompeticiones();
                listaJuegos.get(vi.getCbElegirJe().getSelectedIndex() - 1).setNombre(vi.getTfNombreJe().getText());
                listaJuegos.get(vi.getCbElegirJe().getSelectedIndex() - 1).setDesarrolladora(vi.getTfDesarroladoraJe().getText());
                listaJuegos.get(vi.getCbElegirJe().getSelectedIndex() - 1).setFechaLanzamiento(fecha);
            }

            Juego je = new Juego(cod, vi.getTfNombreJe().getText(), vi.getTfDesarroladoraJe().getText(), fecha, listaCompeticionesJe);

            if (opcionAccion == 1)
                cvi.insertarJuego(je); // Insertar juego en la tabla
            else
                cvi.modificarJuego(je); // Modificar juego en la tabla

            vi.limpiar(); // Resetear to/do
        }
        catch (Exception ex)
        {
            vi.mostrarError(ex.getMessage());
        }
    }

    /** Rellenar la cb con todos los nombres de los juegos para elegir un juego
     *
     * @author Oskar
     * @version 2.0 17/05/2024
     */
    public void rellenarCbElegirJe() {
        try {
            vi.getCbElegirJe().removeAllItems(); // Vaciar todos los elementos de la cb
            listaJuegos.clear();
            listaJuegos = cvi.buscarJuegos(); // Llamada a tabla para recibir todos los jugadores
            // Modificacion del enunciado dependiendo la acción
            if (opcionAccion == 3) // Modificar
                vi.getCbElegirJe().addItem("Elija el juego que desea modificar");
            else // Consultar
                vi.getCbElegirJe().addItem("Elija el juego que desea consultar");
            // Rellenar la cb
            for (int x = 0; x < listaJuegos.size(); x++)
            {
                vi.getCbElegirJe().addItem(listaJuegos.get(x).getNombre());
            }
        } catch (Exception ex) {
            vi.mostrarError(ex.getMessage());
        }
    }

    /** Metodo vara verificar que haya un juego seleccionado en la cb y mostrar los atributos del
     * juego seleccionado para consultarlo o modificarlo
     *
     * @author Oskar
     * @version 2.0 17/05/2024
     */
    public class cbElegirJeAl implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            try
            {
                // Verificación de que haya algo seleccionado
                if (vi.getCbElegirJe().getSelectedIndex() == 0)
                    throw new Exception("Elija un juego para modificarlo");

                // Una vez ya seleccionado el juego en la combo box mostrar los atributos en las text field
                vi.getTfNombreJe().setText(listaJuegos.get(vi.getCbElegirJe().getSelectedIndex()-1).getNombre());
                vi.getTfDesarroladoraJe().setText(listaJuegos.get(vi.getCbElegirJe().getSelectedIndex()-1).getDesarrolladora());
                vi.getTfFechaJe().setText(String.valueOf(listaJuegos.get(vi.getCbElegirJe().getSelectedIndex()-1).getFechaLanzamiento()));
            }
            catch (Exception ex)
            {
                vi.mostrarError(ex.getMessage());
            }
        }
    }
}
