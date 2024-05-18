package Controlador.ControladoresVista;

import Vista.VentanaLogin;

public class ControladorVentanaLogin {


    private ControladorVista cv;


    private VentanaLogin vLogin;

    public ControladorVentanaLogin(ControladorVista cv)
    {
        this.cv = cv;
    }

    public void crearMostrar()
    {
        try
        {
            vLogin = new VentanaLogin();



            vLogin.setVisible(true);
        }
        catch (Exception ex)
        {
            System.out.println("\nHa sucedido el siguiente error: \n"+ ex.getMessage());
        }
    }


}
