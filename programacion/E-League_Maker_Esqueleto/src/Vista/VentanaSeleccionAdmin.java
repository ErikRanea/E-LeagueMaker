/**
 * Clase encarga de crea la ventana donde el administrador selecciona el modo
 * @author Erik
 * @sincer 20/05/2024
 */

package Vista;

import javax.swing.*;
import java.awt.event.ActionListener;

public class VentanaSeleccionAdmin extends JFrame {


    private JPanel panelPrincipal;
    private JButton bCompeti;
    private JButton bInscrip;


    public VentanaSeleccionAdmin()
    {
        setContentPane(panelPrincipal);
        setSize(700,700);
        setResizable(false); //Este parametro no permite redimensionar la ventana
        setLocationRelativeTo(null);
        iniciarComponentes();
    }

    public void iniciarComponentes(){ponerIconoPrograma();}
    /**
     * Este metodo se encarga de ponerle la imagen de marca a la aplicación
     */
    public void ponerIconoPrograma()
    {
        ImageIcon icono = new ImageIcon("./src/Img/Logo_mas_cerca.jpg");
        super.setIconImage(icono.getImage());
    }

    public void addBCompetiAL(ActionListener listener){bCompeti.addActionListener(listener);}
    public void addBInscripcionAL(ActionListener listener){bInscrip.addActionListener(listener);}

}
