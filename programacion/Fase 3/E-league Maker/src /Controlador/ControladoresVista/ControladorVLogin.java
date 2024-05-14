package Controlador.ControladoresVista;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.sql.SQLException;

public class ControladorVLogin {

    /**
     * Clase para hacer el login de los usuarios y asi identificarlos como admin o usuario normal
     * @author Oskar
     * @version 1.0 10/05/2024
     */
    private Vista.VentanaRegistro r;

    // Controlador
    public ControladorVLogin() {
        r = new Vista.VentanaRegistro();
        r.setVisible(true);

        r.bAceptarAL(new BAceptarAL());
        r.tfUsuarioFL(new TfUsuarioFL());
    }

    /**
     * Metodo para que cuando el ususario pulse el botón se verifique si el nombre del usuario y la
     * contraseña coinciden con usuarios ya creados y si no decirle que hay un error.
     * @author Oskar
     * @version 1.0 10/05/2024
     */
    public class BAceptarAL implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) {

            try
            {
                if (r.getTfUsuario().getText().isEmpty())
                {
                    r.getTfUsuario().setText("El usuario o la contraseña está mal");

                    r.getTfUsuario().setForeground(Color.RED);
                    r.getPfContra().setText("");
                }
            }
            catch (ClassNotFoundException | SQLException eSQL)
            {
                r.getTfUsuario().setText("El usuario o la contraseña está mal");

                r.getTfUsuario().setForeground(Color.RED);
                r.getPfContra().setText("");
                System.out.println(eSQL.getMessage());
            }



        }
    }

    /**
     * Metodo para quitar el mensaje de error cuanod quiera volver a meter el usuario
     * @author Oskar
     * @version 1.0 10/05/2024
     */
    public class TfUsuarioFL implements FocusListener
    {
        @Override
        public void focusGained(FocusEvent e) {
            r.getTfUsuario().setText("");
    }

        @Override
        public void focusLost(FocusEvent e) {

        }
    }

}
