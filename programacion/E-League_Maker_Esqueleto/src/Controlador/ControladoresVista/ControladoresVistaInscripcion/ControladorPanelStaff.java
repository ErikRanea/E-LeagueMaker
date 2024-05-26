package Controlador.ControladoresVista.ControladoresVistaInscripcion;

import Modelo.*;
import Vista.VentanaInscripcion;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ControladorPanelStaff {
    private ControladorVentanaInscripcion cvi;
    private VentanaInscripcion vi;

    private int opcionTabla;
    private int opcionAccion;

    private List<Equipo> listaEquipos;
    private List<Staff> listaStaffs;


    public ControladorPanelStaff(ControladorVentanaInscripcion cvi, VentanaInscripcion vi)
    {
        this.cvi = cvi;
        this.vi = vi;

        listaEquipos = new ArrayList<>();
        listaStaffs = new ArrayList<>();
    }

    public void BAmcS(int opcionAccion, int opcionTabla) throws Exception {
        this.opcionTabla = opcionTabla;
        this.opcionAccion = opcionAccion;

        switch (opcionAccion)
        {
            case 1:
                vi.crearElementos(opcionTabla, opcionAccion); // Modificar el panel
                rellenarCbEquipoS(); // Rellenar la cb
                vi.addJbS(new JbS());
                break;
            case 3:
                vi.crearElementos(opcionTabla, opcionAccion); // Modificar el panel
                rellenarCbElegirS(); // Rellenar la cb
                vi.addcbElegirS(new cbElegirS()); // Verificar que hay algo seleccionado y mostrar los datos
                vi.addJbS(new JbS());
                break;
            case 4:
                vi.crearElementos(opcionTabla, opcionAccion); // Modificar el panel
                rellenarCbElegirS(); // Rellenar la cb
                vi.addcbElegirS(new cbElegirS()); // Verificar que hay algo seleccionado y mostrar los datos
                break;
        }
    }

    /** Action listener del botón de la ventana de alta modificar y consultar de Staffs
     * para verificar que los datos están correctamente
     *
     * @author Oskar
     * @version 2.0 18/05/2024
     */
    public class JbS implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            validarStaff(); // Hacer todas las validaciones de los atributos del objeto staff
        }
    }

    /** Metodo en el cual se llena la cb con todos los equipos del panel de Alta,
     * Baja y Modificar del objeto Staff
     *
     * @author Oskar
     * @verison 2.0 18/05/2024
     */
    public void rellenarCbEquipoS() throws Exception {
        listaEquipos.clear();
        listaEquipos = cvi.buscarEquipos(); // Llamada a tabla para recibir todos los equipos
        vi.getCbEquipoS().removeAllItems(); // Vaciar la cb
        // Rellenar la cb
        vi.getCbEquipoS().addItem("Seleccione el equipo al que pertenece");
        for(int x = 0; x < listaEquipos.size(); x++)
        {
            vi.getCbEquipoS().addItem(listaEquipos.get(x).getNombre());
        }
    }

    /** Hacer todas las validaciones de los atributos del objeto staff
     *
     * @author Oskar
     * @version 2.0 18/05/2024
     */
    public void validarStaff()
    {
        try
        {
            // Validar Nombre
            if (vi.getTfNombreS().getText().isEmpty())
                throw new Exception("El nombre no puede estar vacio");
            Pattern pat1 = Pattern.compile("^[A-Z].*"); // Pattern
            Matcher mat1 = pat1.matcher(vi.getTfNombreS().getText());
            if (!mat1.matches())
                throw new Exception("El nombre no cumple el formato deseado");

            // Validar Apellido
            if (vi.getTfApellidoS().getText().isEmpty())
                throw new Exception("El apellido no puede estar vacio");
            Pattern pat2 = Pattern.compile("^[A-Z].*"); // Pattern
            Matcher mat2 = pat2.matcher(vi.getTfNombreJe().getText());
            if (!mat2.matches())
                throw new Exception("El apellido no cumple el formato deseado");

            // Validacion Salario
            if (vi.getTfSalarioS().getText().isEmpty())
                throw new Exception("El salario no puede estar vacio");
            Pattern pat3 = Pattern.compile("^[1-9]{5}]$"); // Pattern
            Matcher mat3 = pat3.matcher(vi.getTfSalarioS().getText());
            if (!mat3.matches())
                throw new Exception("El salario no cumple el formato deseado");

            int salario = Integer.parseInt(vi.getTfSalarioJd().getText());

            // Validar los radio button de Puesto
            String puesto;
            if (vi.getRbEntenador().isSelected())
                puesto = "Entrenador";
            else
            if (vi.getRbAsis().isSelected())
                puesto = "Entrendor Asistente";
            else
                throw new Exception("Es necesario determinar el puesto de trabajo");

            // Validar la cb de Equipo
            if (vi.getCbElegirS().getSelectedIndex() == 0)
                throw new Exception("Elije a que equipo pertenece");

            // Crear el objeto Staff para introducirlo o modificarlo
            int cod = 0;

            if (opcionAccion == 3)
            {
                cod = listaStaffs.get(vi.getCbElegirS().getSelectedIndex() - 1).getCod();
                listaStaffs.get(vi.getCbElegirS().getSelectedIndex() - 1).setNombre(vi.getTfNombreS().getText());
                listaStaffs.get(vi.getCbElegirS().getSelectedIndex() - 1).setApellido(vi.getTfApellidoS().getText());
                listaStaffs.get(vi.getCbElegirS().getSelectedIndex() - 1).setPuesto(puesto);
                listaStaffs.get(vi.getCbElegirS().getSelectedIndex() - 1).setSalario(salario);
            }

            Staff s = new Staff(cod, vi.getTfNombreS().getText(), vi.getTfApellidoS().getText(), puesto, salario, listaEquipos.get(vi.getCbEquipoP().getSelectedIndex()-1));

            if (opcionAccion == 1)
                cvi.insertarStaff(s); // Insertar staff en la tabla
            else
                cvi.modificarStaff(s); // Modificar staff en la tabla

            vi.limpiar(); // Limpiar to/do
        }
        catch (Exception ex)
        {
            vi.mostrarError(ex.getMessage());
        }
    }

    /** Rellenar la cb con todos los nombres y apellidos de los miembros del Staff para elegir un miembro
     *
     * @author Oskar
     * @version 2.0 18/05/2024
     */
    public void rellenarCbElegirS() throws Exception {
        listaStaffs.clear();
        listaStaffs = cvi.buscarStaffs(); // Llamada a tabla para recibir todos los Miembros del Staff
        vi.getCbElegirS().removeAllItems(); // Vaciar la cb
        if (opcionAccion == 3) // Modificar
            vi.getCbElegirS().addItem("Elija el miembro del staff que desea modificar");
        else // Consultar
            vi.getCbElegirS().addItem("Elija el miembro del staff que desea consultar");
        // Rellenar la cb
        for (int x = 0; x < listaStaffs.size(); x ++)
        {
            vi.getCbElegirS().addItem(listaStaffs.get(x).getNombre() + listaStaffs.get(x).getApellido());
        }
    }

    /** Metodo vara verificar que haya un miembro del staff seleccionado en la cb y mostrar los atributos del
     * miembro seleccionado para consultarlo o modificarlo
     *
     * @author Oskar
     * @version 2.0 18/05/2024
     */
    public class cbElegirS implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            try
            {
                // Verificación de que haya algo seleccionado
                if (vi.getCbElegirS().getSelectedIndex() == 0)
                    throw new Exception("Elija un miembro del staff");

                // Una vez ya seleccionado el miembro en la combo box mostrar los atributos en las text field
                vi.getTfNombreS().setText(listaStaffs.get(vi.getCbElegirS().getSelectedIndex() - 1).getNombre());
                vi.getTfApellidoS().setText(listaStaffs.get(vi.getCbElegirS().getSelectedIndex() - 1).getApellido());
                vi.getTfSalarioS().setText(String.valueOf(listaStaffs.get(vi.getCbElegirS().getSelectedIndex() - 1).getSalario()));

                if (opcionAccion == 3) // Modificar
                {
                    rellenarCbEquipoS(); // Rellenar la cb de equipo con todos los equipos

                    // Que se seleccione el cargo del miembro del staff
                    if (listaStaffs.get(vi.getCbElegirS().getSelectedIndex() - 1).getPuesto().equals("Entrenador"))
                        vi.getRbEntenador().setSelected(true);
                    else
                        vi.getRbAsis().setSelected(true);

                    // Que se seleccione el equipo al que pertenece el miembro del staff por si desea modificarlo
                    int posicionEquipo = 0;
                    boolean salir = true;
                    for (int x = 0; x < listaEquipos.size() && salir; x++)
                    {
                        if (listaEquipos.get(x).getCod() == listaStaffs.get(vi.getCbElegirS().getSelectedIndex() - 1).getEquipo().getCod())
                        {
                            posicionEquipo = x;
                            salir = false;
                        }
                    }

                    vi.getCbEquipoS().setSelectedItem(posicionEquipo);
                }
                else // Consultar
                {
                    // Mostrar en la tf el cargo que tiene
                    vi.getTfRolS().setText(listaStaffs.get(vi.getCbElegirS().getSelectedIndex() - 1).getPuesto());

                    // Mostrar en la tf el equipo al que pertenece
                    vi.getTfEquipoS().setText(listaStaffs.get(vi.getCbElegirS().getSelectedIndex() - 1).getEquipo().getNombre());
                }
            }
            catch (Exception ex)
            {
                vi.mostrarError(ex.getMessage());
            }
        }
    }
}
