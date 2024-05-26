package Controlador.ControladoresVista.ControladoresVistaInscripcion;

import Modelo.*;
import Vista.VentanaInscripcion;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ControladorPanelUsuario {
    private ControladorVentanaInscripcion cvi;
    private VentanaInscripcion vi;

    private int opcionTabla;
    private int opcionAccion;

    private List<Usuario> listaUsuarios;

    public ControladorPanelUsuario(ControladorVentanaInscripcion cvi, VentanaInscripcion vi)
    {
        this.cvi = cvi;
        this.vi = vi;
        listaUsuarios = new ArrayList<>();
    }

    public void BAmcU(int opcionAccion, int opcionTabla) throws Exception {
        this.opcionTabla = opcionTabla;
        this.opcionAccion = opcionAccion;

        switch (opcionAccion)
        {
            case 1:
                vi.crearElementos(opcionTabla, opcionAccion); // Modificar el panel
                vi.addJbU(new JbU());
                break;
            case 3:
                vi.crearElementos(opcionTabla, opcionAccion); // Modificar el panel
                rellenarCbElegirU(); // Rellenar la cb
                vi.addcbElegirU(new cbElegirU()); // Verificar que hay algo seleccionado y mostrar los datos
                vi.addJbU(new JbU());
                break;
            case 4:
                vi.crearElementos(opcionTabla, opcionAccion); // Modificar el panel
                rellenarCbElegirU(); // Rellenar la cb
                vi.addcbElegirU(new cbElegirU()); // Verificar que hay algo seleccionado y mostrar los datos
                break;
        }
    }

    /** Action listener del botón de la ventana de alta modificar y consultar de Usuarios
     * para verificar que los datos están correctamente
     *
     * @author Oskar
     * @version 2.0 19/05/2024
     */
    public class JbU implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            validarUsuarios(); // Hacer todas las validaciones de los atributos del objeto usuario
        }
    }

    /** Hacer todas las validaciones de los atributos del objeto Usuario
     *
     * @author Oskar
     * @version 2.0 19/05/2024
     */
    public void validarUsuarios()
    {
        try
        {
            // Validar Nickname
            if (vi.getTfNicknameU().getText().isEmpty())
                throw new Exception("El nickname no puede estar vacio");
            Pattern pat1 = Pattern.compile("^[A-Z].*"); // Pattern
            Matcher mat1 = pat1.matcher(vi.getTfNicknameU().getText());
            if (!mat1.matches())
                throw new Exception("El nickname no cumple el formato deseado");

            // Validar Contraseña
            if (vi.getTfContraU().getText().isEmpty())
                throw new Exception("La contraseña no puede estar vacio");
            Pattern pat2 = Pattern.compile(""); // Pattern
            Matcher mat2 = pat2.matcher(vi.getTfContraU().getText());
            if (!mat2.matches())
                throw new Exception("La contraseña no cumple el formato deseado");

            // Validar Privilegios
            Boolean privilegios;
            if (vi.getRbEstandar().isSelected())
                privilegios = false;
            else
            if (vi.getRbAdmin().isSelected())
                privilegios= true;
            else
                throw new Exception("Elija que permisos tiene este usuario");

            // Crear el objeto Usuario para introducirlo o modificarlo
            int cod = 0;

            if (opcionAccion == 3)
            {
                cod = listaUsuarios.get(vi.getCbElegirU().getSelectedIndex() - 1).getCod();
                listaUsuarios.get(vi.getCbElegirP().getSelectedIndex() - 1).setNickname(vi.getTfNicknameU().getText());
                listaUsuarios.get(vi.getCbElegirS().getSelectedIndex() - 1).setPassword(vi.getTfContraU().getText());
                listaUsuarios.get(vi.getCbElegirS().getSelectedIndex() - 1).setAdmin(privilegios);
            }

            Usuario u = new Usuario(cod, vi.getTfNicknameU().getText(), vi.getTfContraU().getText(), privilegios);

            if (opcionAccion == 1)
                cvi.insertarUsuario(u); // Insertar usuario en la tabla
            else
                cvi.modificarUsuario(u); // Modificar usuario en la tabla

            vi.limpiar(); // Limpiar to/do
        }
        catch (Exception ex)
        {
            vi.mostrarError(ex.getMessage());
        }
    }

    /** Rellenar la cb con todos los nicknames de los usuarios para elegir uno
     *
     * @author Oskar
     * @version 2.0 19/05/2024
     */
    public void rellenarCbElegirU() throws Exception {
        listaUsuarios.clear();
        listaUsuarios = cvi.buscarUsuarios(); // Llamada a tabla para recibir todos los usuarios
        vi.getCbElegirU().removeAllItems(); // Vaciar la cb
        if (opcionAccion == 3) // Modificar
            vi.getCbElegirU().addItem("Elija el usuario que desea modificar");
        else // Consultar
            vi.getCbElegirU().addItem("Elija el usuario que desea consultar");
        // Rellenar la cb
        for (int x = 0; x < listaUsuarios.size(); x ++)
        {
            vi.getCbElegirU().addItem(listaUsuarios.get(x).getNickname());
        }
    }

    /** Metodo vara verificar que haya un usuario seleccionado en la cb y mostrar los atributos del
     * usuario seleccionada para consultarla o modificarla
     *
     * @author Oskar
     * @version 2.0 19/05/2024
     */
    public class cbElegirU implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            try
            {
                // Verificación de que haya algo seleccionado
                if (vi.getCbElegirU().getSelectedIndex() == 0)
                    throw new Exception("Elija un Usuario");

                // Una vez ya seleccionado el usuario en la combo box mostrar los atributos en las text field
                vi.getTfNicknameU().setText(listaUsuarios.get(vi.getCbElegirU().getSelectedIndex() - 1).getNickname());
                vi.getTfContraU().setText(listaUsuarios.get(vi.getCbElegirU().getSelectedIndex() - 1).getPassword());

                if (opcionAccion == 3) // Modificar
                {
                    // Que se seleccione los permisos que tienen
                    if (listaUsuarios.get(vi.getCbElegirU().getSelectedIndex()).isAdmin())
                        vi.getRbAdmin().setSelected(true);
                    else
                        vi.getRbEstandar().setSelected(true);
                }
                else // Consultar
                {
                    // Mostrar en la tf los permisos del usuario
                    String permisos;
                    if (listaUsuarios.get(vi.getCbElegirU().getSelectedIndex()).isAdmin())
                        permisos = "Administrador";
                    else
                        permisos= "Estandar";
                    vi.getTfPriviU().setText(permisos);
                }
            }
            catch (Exception ex)
            {
                vi.mostrarError(ex.getMessage());
            }
        }
    }
}
