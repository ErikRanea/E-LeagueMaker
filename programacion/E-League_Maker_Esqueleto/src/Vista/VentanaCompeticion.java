/**
 * Esta clase se encargar de crear un JFrame para el Administrador una vez cerrada la etapa de inscripcion
 * @author Erik
 * @since 15/05/2024
 */
package Vista;

import javax.swing.*;
//import org.imgscalr.Scalr;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class VentanaCompeticion extends JFrame{
    private JPanel pPrincipal;
    private JPanel pCabecera;
    private JPanel pvisualizar;
    private JButton bVerClasifi;
    private JButton bVerResultado;
    private JButton bIntroResult;
    private JButton bLogOut;
    private JPanel pLogOut;
    private JPanel pLogo;
    private JPanel pIzqCabeVacio;
    private JPanel pLateralIzq;


    public VentanaCompeticion()
    {
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setContentPane(pPrincipal);
        setLocationRelativeTo(null);
        iniciarComponentes();

    }


 public void iniciarComponentes()
    {
        bLogOut.setFocusPainted(false);
        bLogOut.setBorderPainted(false);
        bLogOut.setContentAreaFilled(false);


        bLogOut.setPressedIcon(new ImageIcon("/Img/logOutBlanco30x30.png"));

    }



 /*   public void setImagenLogOutBoton(JButton botton, String ruta)
    {
        ImageIcon imagen = new ImageIcon(ruta);
        System.out.println("\nAncho del boton"+ botton.getWidth() +"\n Alto "+ botton.getHeight() +"\n último parametro"+ Image.SCALE_DEFAULT);
        Icon icono = new ImageIcon(imagen.getImage().getScaledInstance(100,100, Image.SCALE_DEFAULT));
        botton.setIcon(icono);
        this.repaint();

    }


    public Icon setIcono (String url, JButton boton)
    {
        ImageIcon icon = new ImageIcon(getClass().getResource(url));

        int ancho = boton.getWidth();
        int alto = boton.getHeight();

        ImageIcon icono = new ImageIcon(icon.getImage().getScaledInstance(ancho,alto,Image.SCALE_DEFAULT));
        return icono;

    }*/

}
