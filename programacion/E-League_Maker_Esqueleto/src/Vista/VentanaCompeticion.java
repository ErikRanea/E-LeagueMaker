/**
 * Esta clase se encargar de crear un JFrame para el Administrador una vez cerrada la etapa de inscripcion
 * @author Erik
 * @since 15/05/2024
 */
package Vista;

import javax.swing.*;
import java.awt.*;

public class VentanaCompeticion extends JFrame{
    private JPanel pPrincipal;
    private JPanel pCabecera;
    private JPanel pAcciones;
    private JPanel pvisualizar;
    private JPanel pBotones;
    private JPanel pDatos;
    private JButton bVerClasifi;
    private JButton bVerResultado;
    private JButton bIntroResult;
    private JComboBox cbCompeti;
    private JComboBox cbJornada;
    private JButton bLogOut;


    public VentanaCompeticion()
    {
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setContentPane(pPrincipal);
        setLocationRelativeTo(null);
        iniciarComponentes();

    }


    public void iniciarComponentes()
    {
        setImagenLogOutBoton(bLogOut,"Img/log-out-icon.png");
    }

    public void setImagenLogOutBoton(JButton botton, String ruta)
    {
        ImageIcon imagen = new ImageIcon(ruta);
        Icon icono = new ImageIcon(imagen.getImage().getScaledInstance(botton.getWidth(),botton.getHeight(), Image.SCALE_DEFAULT));
        botton.setIcon(icono);

    }
}
