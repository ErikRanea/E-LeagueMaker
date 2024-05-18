package Controlador.ControladoresVista;

import Excepciones.ExcepcionErrorUsuario;
import Modelo.Usuario;
import Vista.VentanaLogin;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class ControladorVentanaLogin {


    private ControladorVista cv;


    private VentanaLogin vLogin;


    private Usuario usuario;


    public ControladorVentanaLogin(ControladorVista cv)
    {
        this.cv = cv;
    }

    public void crearMostrar()
    {
        try
        {
            vLogin = new VentanaLogin();


            vLogin.addBLogInAL(new BLogIn());
            vLogin.setVisible(true);
        }
        catch (Exception ex)
        {
            System.out.println("\nHa sucedido el siguiente error: \n"+ ex.getMessage());
        }
    }



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
                    vLogin.dispose();
                    cv.mostrarVentanaCompeti();
                }
                else
                {
                    throw new ExcepcionErrorUsuario("Usuario o contraseña no validos.");
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


    //Metodos propios

    public boolean validarTF() throws Exception
    {
        boolean esCorrecto = false;


        if(!vLogin.getTfUsuario().getText().isEmpty() && !vLogin.getTfPassword().getPassword().toString().isEmpty())
        {
            String nickname = vLogin.getTfUsuario().getText();
            usuario = cv.buscarUsuario(nickname);
            if(usuario != null)
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
