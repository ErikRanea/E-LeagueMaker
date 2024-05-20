/**
 * Clase encargada de controlar to do el apartado lógico de la ventana de logon
 * @author Erik
 * @since 18/05/2024
 */
package Controlador.ControladoresVista;

import Excepciones.ExcepcionErrorUsuario;
import Modelo.Usuario;
import Vista.VentanaLogin;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class ControladorVentanaLogin {

    /**
     * Atributo que conecta la clase con el conector superior
     * @param cv
     */
    private ControladorVista cv;

    /**
     * Atributo de la ventana
     * @param vLogin
     */
    private VentanaLogin vLogin;

    /**
     * Atriburo para instaciar objetos de la base de datos
     * @param usuario
     */
    private Usuario usuario;


    public ControladorVentanaLogin(ControladorVista cv)
    {
        this.cv = cv;
    }

    /**
     * Metodo encargado de crear la ventana y mostrarla
     * @author Erik
     */
    public void crearMostrar()
    {
        try
        {
            vLogin = new VentanaLogin();

            vLogin.addBSalirAL(new BSalir());
            vLogin.addBLogInAL(new BLogIn());
            vLogin.setVisible(true);
        }
        catch (Exception ex)
        {
            System.out.println("\nHa sucedido el siguiente error: \n"+ ex.getMessage());
        }
    }


    /**
     * Interfaz utilizada utiliza para detectar la acción sobre el botón de lógin y verificar si el usuario y contraseña
     * introducido son correctos.
     * @author Erik
     */
    public class BLogIn implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            try
            {
                vLogin.quitarMensajeError();
                if (validarTF())
                {
                    if(usuario.isAdmin())
                    {
                        vLogin.dispose();
                        cv.mostrarVentanaSeleccion();
                    }
                }
                else
                {
                    throw new ExcepcionErrorUsuario("Datos incorrectos");
                }
            }
            catch (ExcepcionErrorUsuario eUsuario)
            {
                vLogin.mensajeErrorUsuario(eUsuario.getMensaje());
            }
            catch (Exception ex)
            {
                System.out.println("\n Ha sucedido el siguiente error:\n"+ ex.getMessage());
            }
        }
    }

    public class BSalir implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            vLogin.dispose();
        }
    }

    //Metodos propios

    /**
     * Este metedo sirve para validar los datos introducidos por el usuario.Tanto si el usuario no es correcto o la
     * contraseña ponen un mensaje de error
     * @return boolean si es true todos los datos son correctos. Si no, no te permite acceder
     * @throws Exception
     */
    public boolean validarTF() throws Exception
    {
        boolean esCorrecto = false;


        if(!vLogin.getTfUsuario().getText().isEmpty() && !vLogin.getTfPassword().getPassword().toString().isEmpty())
        {
            String nickname = vLogin.getTfUsuario().getText();
            usuario = cv.buscarUsuario(nickname);
            if(usuario.getPassword() != null)
            {
                char[] passwordArray = vLogin.getTfPassword().getPassword();
                StringBuilder password = new StringBuilder("");
                for (int x = 0; x < passwordArray.length; x++)
                {
                    password.append(passwordArray[x]);
                }
                String pass = password.toString();
                if(usuario.getPassword().equals(pass))
                {
                    esCorrecto = true;
                }
            }
        }

        return esCorrecto;
    }

}
